package com.saf.framework;


import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;

public class MyTestNGBaseClass {


    public static WebDriver oDriver;

    public static int ssNumber;
    public static String reportPath;
    public static boolean dbFlag;
    public static int testCaseId = 0;
    public static String sDriverName = "";

    HashMap<String, HashMap<String, String>> myMap = new HashMap<String, HashMap<String, String>>();



    @Parameters({"browserName"})
    @BeforeSuite
    //@BeforeTest
    public void BeforeSuite(@Optional("") String browserName) throws Throwable {
        //public void BeforeSuite() throws Throwable{
        reportPath = "Report_" + new Date().getDate() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getHours() + "-" + new Date().getMinutes() + "-" + new Date().getSeconds();
        File f = new File("Reports/" + reportPath);
        File ss = new File("Reports/" + reportPath + "/Screenshots");
        try {
            f.mkdir();
            ss.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }


        ssNumber = 0;

        if (browserName.equalsIgnoreCase("ie")) {
            sDriverName = "ie";
        } else if (browserName.equalsIgnoreCase("firefox")) {
            sDriverName = "firefox";
        } else if (browserName.equalsIgnoreCase("chrome")) {
            sDriverName = "chrome";
        } else if (browserName.equalsIgnoreCase("htmlunit")) {
            sDriverName = "htmlunit";
        } else {
            throw new Exception("Unknown driver name = " + sDriverName +
                    "Valid names are: ie, firefox, chrome, htmlunit");
        }

        oDriver = CommonLib.getDriver(sDriverName);
		/*DriverFactory driverFactory =  DriverFactory.getInstance();
		driverFactory.setDriver(sDriverName);
		oDriver = driverFactory.getDriver();*/
    }

    //********************************************************
    //preconditions
    //********************************************************

 /*	@Parameters({"CalendarName","BrowserToOpen","AppUrl","TestName"})
	@BeforeClass
	public void automationSetup(@Optional("")String calendarName, @Optional("") String sBrowserName,@Optional("") String sUrl,@Optional("") String sTestName) throws Exception
	{
		dbFlag = false;
		//myMap = oDataDriver.getData();
		//To fetch input data from calendar
		myMap = DataDriver.getData(calendarName, this.getClass().getSimpleName().toString(),sTestName);

		//dataMap has final input data
		dataMap = fConvertHashToHashNew(myMap.get("Dictionary"));
		//keysMap has keywords to be written in KR
		keysMap = fConvertHashToHashNew(myMap.get("KeysToWrite"));

		if(sBrowserName.equals("") || sUrl.equals("") ) {
			dbFlag = true;
		}else {
			oDriver = CommonLib.getDriver(sBrowserName);
			oDriver.get(sUrl);
		}

		//oDriver = CommonLib.getRemoteDriver("http://192.168.228.2:4444/wd/hub", sBrowserName);
		Thread.sleep(6000);
	}

	*/

    @Parameters({"CalendarName", "TestName"})
    //@BeforeClass

    public void automationSetup(@Optional("") String calendarName, @Optional("") String sTestName) throws Exception {
        // passing className to read from excel file

		/*StackTraceElement[] completeClassName = new Exception().getStackTrace();
		for (StackTraceElement e : completeClassName) {
			System.out.println(e);
		}*/
        //className = completeClassName.split("\\.")[completeClassName.split("\\.").length - 1];

	/*	System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());

		System.out.println(Thread.currentThread().getStackTrace()[2].getFileName());*/
        //

        dbFlag = false;
        //myMap = oDataDriver.getData();
        //To fetch input data from calendar

        //myMap = DataDriver.getData(calendarName, this.getClass().getSimpleName().toString(),className);

        //dataMap has final input data


        //keysMap has keywords to be written in KR


        //oDriver = CommonLib.getRemoteDriver("http://192.168.228.2:4444/wd/hub", sBrowserName);
        Thread.sleep(6000);

    }




    @Parameters({"VIDEO_RECORDING"})
    @AfterMethod
    public void stopRecording(@Optional("") String VIDEO_RECORDING, ITestResult result) throws Exception {
        if (VIDEO_RECORDING.equalsIgnoreCase("true")) {

            String media = ((CanRecordScreen) oDriver).stopRecordingScreen();

            File video = new File("Reports/" + reportPath + "/Videos");

            if (!video.exists()) {
                video.mkdirs();
            }

            FileOutputStream stream = new FileOutputStream(video + "/" + result.getName() + ".mp4");
            stream.write(Base64.decodeBase64(media));
        }

    }


    @AfterClass
    public void automationTeardown() throws Exception {
	  /*	if(!dbFlag) {
			oDriver.quit();
		}
	  */


        testCaseId = 0;

    }


    @AfterSuite

    public void afterSuite() throws Throwable {



        oDriver.quit();

    }





    public static boolean reportResult(String status, String message, boolean ssFlag) {
        try {
            String dest = "";
            if (ssFlag) {
                ssNumber++;
                TakesScreenshot ts = (TakesScreenshot) oDriver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                dest = System.getProperty("user.dir") + "/Reports/" + reportPath + "/Screenshots/" + ssNumber + ".png";
                File destination = new File(dest);
                FileUtils.copyFile(source, destination);
            }



            //oExtentTest.log(LogStatus.INFO, oExtentTest.addScreenCapture(dest));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void startTest(String scenarioName) {

        //String completeClassName = new Exception().getStackTrace()[1].getClassName();
        //className = completeClassName.split("\\.")[completeClassName.split("\\.").length - 1];

        //MyTestNGBaseClass.
        //Create entry in the Test Execution table for the test started
        //testCaseId = DBReporting.insertExecutionRecord(className);
        //testCaseId = DBReporting.insertExecutionRecordInGrafana(className);
        //AutomationConstants.itestCaseID = testCaseId;


    }

    public static void allureReport(String status, String message, boolean ssFlag) {
        try {
            if (ssFlag) {
                Allure.addAttachment("Screenshot : " + message, new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            }
            if (status.equalsIgnoreCase("PASS")) {
                Allure.step(message, Status.PASSED);
            } else if (status.equalsIgnoreCase("FAIL")) {
                Allure.step(message, Status.FAILED);
            } else {
                Allure.step(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
