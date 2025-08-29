# Pointr UI Automation Project

This is a **Selenium-based UI automation project** for Pointr that works with **Chrome** and **Firefox** browsers.

---

## Prerequisites

To run the project, make sure you have the following installed:

- Java 11 or higher  
- Maven 3.9.11 or higher  
- Chrome and Firefox browsers  

---

## How to Run the Project

### First Method: Using Command Prompt

1. Change the directory to the project folder:  
   ```bash
   cd C:\Users\user1\Desktop\pointrCase\pointrUiCase
   ```
2. Run the Maven command:  
   ```bash
   mvn clean test -DsuiteXmlFile=testng.xml
   ```

### Second Method: Using an IDE

- Open the project in **Eclipse**, **IntelliJ**, or other IDE.  
- Run the **testng.xml** file located in the project folder.

---

## Browser Support

This project supports both **Chrome** and **Firefox** browsers.  

To change the browser, edit **line 4** in the `testng.xml` file:

- **For Chrome:**  
  ```xml
  <parameter name="browserName" value="chrome"/>
  ```
- **For Firefox:**  
  ```xml
  <parameter name="browserName" value="firefox"/>
  ```

---

## CI/CD

The file named **`Jenkinsfile`** is responsible for the CI/CD design. In this setup:

1. Git repository checkout is done using the GitHub link.  
2. Tests are triggered using the command specified in the Jenkinsfile:  
   ```bash
   mvn test -DsuiteXmlFile=TestNG/CIBuild.xml -Dtest=pointr
   ```
3. **Allure reports** are generated for the test results and logs.

