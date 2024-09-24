If you are running this Java program on a UNIX-system: use the following CLI syntax to compile
because compilation otherwise is pretty much impossible.

/usr/bin/env /Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home/bin/java -cp /Users/[INSERT FILE PATH DIRECTORY TO FOLDER CONTAINING PROJECT]/target/classes org.yourcompany.yourproject.BeachAnim

If you are running this Java program on a Windows-system: use this.

"C:\Program Files\Java\jdk-22\bin\java.exe" -cp "C:\Users\[INSERT FILE PATH DIRECTORY TO FOLDER CONTAINING PROJECT]\target\classes" org.yourcompany.yourproject.BeachAnim

You'll get a runtime error otherwise due to class-pathing. THAT being said, if you still
get the error it may be because you need to install Java globally (which you can do through Oracle website).
OR it's because this line:

MacOS: /usr/bin/env /Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home/bin/java
Windows: "C:\Program Files\Java\jdk-22\bin\java.exe" 

is pointing towards jdk-22 and not, jdk-23 which is what you might have. So change it accordingly.

- Aban