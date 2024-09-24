/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
 package org.yourcompany.yourproject;
 /********************
  * BeachAnimation
  * Authors: 
  * Fall 24: CSC345/CSC645
  *
  * This file is our first assignment for Computer Graphics, where we create a simple beach animation
  * consisting of a still images for background, the sky, a picnic scene, and trees, as well as
  * animation for the sun, a bezier-curve bird, and even a functioning seesaw!
  *
  * Additions for extra credit are as such: 
  * 
  * This class illustrates transformations on a scene using Java's
  * Graphics2D class
  ********************/
 import java.awt.*;        // import statements to make necessary classes available
 import java.awt.event.*;
 import java.awt.geom.*;
 
 import javax.swing.*;
 import java.lang.Math;
  
 public class BeachAnim extends JPanel {
      /**
       * This main() routine makes it possible to display each frame within this animation
       * within a JFrame window, using a Timer object to drive the animation over repeated time intervals.
       */
      public static void main(String[] args) {
        
            JFrame window;
            window = new JFrame("Java Animation");  // The parameter shows in the window title bar.
            final BeachAnim panel = new BeachAnim(); // The drawing area.
            window.setContentPane( panel ); // Show the panel in the window.
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End program when window closes.
            window.pack();  // Set window size based on the preferred sizes of its contents.
            window.setResizable(false); // Don't let user resize window.
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            window.setLocation( // Center window on screen.
                               (screen.width - window.getWidth())/2, 
                               (screen.height - window.getHeight())/2 );
            Timer animationTimer;  // A Timer that will emit events to drive the animation.
            final long startTime = System.currentTimeMillis();
            animationTimer = new Timer(16, new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        panel.frameNumber++;
                        panel.elapsedTimeMillis = System.currentTimeMillis() - startTime;
                        panel.repaint();
                    }
                });
            window.setVisible(true); // Open the window, making it visible on the screen.
            animationTimer.start();  // Start the animation running.
        
      }
      private int frameNumber;  // A counter that increases by one in each frame.
      private long elapsedTimeMillis;  // The time, in milliseconds, since the animation started.
     
      private float pixelSize;  // This is the measure of a pixel in the coordinate system using applyLimits()
                                
      
      /**
       * This constructor sets up a Beach animation of a given size
       */
      public BeachAnim() {
          setPreferredSize(new Dimension(1000,1000) ); // Set size of drawing area, in pixels.
      }
      
      //The paintComponent method draws the content of the JPanel using graphics component g
      protected void paintComponent(Graphics g) {
          // First, create a Graphics2D drawing component for panel
          Graphics2D g2 = (Graphics2D) g.create();
          
          // Turn on antialiasing
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          
          // Fill in the entire drawing area with sky color
          g2.setPaint(new Color(57, 204, 237));
          g2.fillRect(0,0,getWidth(),getHeight()); // From the old graphics API!
          
          // Here, I set up a new coordinate system on the drawing area by updating pixel size
          applyWindowToViewportTransformation(g2, -8, 8, -8, 8, true);
  
          //Show animation
          drawScene(g2);
      }
  
      //draw scene
      private void drawScene(Graphics2D g2) {
          //drawCoordinateFrame(g2, 10);  // 10 is the number of "ticks" to show
          // Scene version 1
          AffineTransform cs = g2.getTransform();   // Save current "coordinate system" transform
          g2.scale(1,1);  // No scaling yet, but setting it up to make a tad bigger
          drawMainScene(g2);
          g2.setTransform(cs);  // Restore previous coordinate system
      }
  
      //Method which draws each component
      private void drawMainScene(Graphics2D g2) {
          drawBackground(g2);
          drawSun(g2);
      }
  
      
    //Draw Background
    private void drawBackground(Graphics2D g2){
        Path2D ground = new Path2D.Double();
        Path2D water = new Path2D.Double();

        ground.moveTo(-10,3);
        ground.lineTo(10,3);
        ground.lineTo(10,-10);
        ground.lineTo(-10,-10);
        ground.closePath();

        water.moveTo(-6,3);
        water.curveTo(-.5,0,0,-3, 5,3);
        water.closePath();
        g2.setPaint(new Color(7, 168, 26));
        g2.fill(ground);

        g2.setPaint(new Color(0,0,255));
        g2.fill(water);
    }

    //Method to draw a sun which lightly pulstates.  Uses frame number to handle changes for the animation.
    private void drawSun(Graphics2D g2){
        
        g2.setPaint(new Color(255,255,0,51));
        double radial = Math.sin(frameNumber *.1);
        for (int i = 0; i < 5; i++) {
            g2.fill(new Ellipse2D.Double(3 + (i*.2) - (.04 * radial),5 + (i*.2) - (.04 * radial),5 - (i*.4) + (.08 * radial),5 - (i*.4) + (.08 * radial)));
        }
    }
      /**
       * Method changes the pixelsize of the animation to match the viewport size, effectively proportioning our resulting animation
       */
      private void applyWindowToViewportTransformation(Graphics2D g2,double left, double right, double bottom, double top, boolean preserveAspect) {
          int width = getWidth();   // The width of this drawing area, in pixels.
          int height = getHeight(); // The height of this drawing area, in pixels.
          
          g2.scale( width / (right-left), height / (bottom-top) );
          g2.translate( -left, -top );
          double pixelWidth = Math.abs(( right - left ) / width);
          double pixelHeight = Math.abs(( bottom - top ) / height);
          pixelSize = (float)Math.max(pixelWidth,pixelHeight);
      }
  }