This is a Selenium based UI automation project for Pointr that works with Chrome and Cirefox browsers.

Prerequisites to run the project:
-Java 11 or higher
-Maven 3.9.11 or higher
-Chrome and firefox browsers

How to run the project:

First method:
-Within cmd change the directory to project folder (Example: cd C:\Users\user1\Desktop\pointrCase\pointrUiCase)
-Then use the command: mvn clean test -DsuiteXmlFile=testng.xml

Second method:
-In an IDE (Eclipse, IntelliJ etc.) run the testng.xml file in the project folder.

Browser support:
This project supports both Chrome and Firefox browsers. To change the browser that project uses change line 4 in testng.xml file:

For Chrome: <parameter name="browserName" value="chrome"/>
For Firefox: <parameter name="browserName" value="firefox"/>
