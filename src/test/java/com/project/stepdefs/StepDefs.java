package com.project.stepdefs;


import com.saf.framework.*;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class StepDefs extends MyTestNGBaseClass {

    CommonLib commonLib = new CommonLib();

    int timeout = 30;






    public String object;


    public static HashMap<String, String> strings = new HashMap<String, String>();
    InputStream stringsis;
    TestUtils utils;

    @Before
    public void setReportName(Scenario scenario) {
        startTest(scenario.getName());
    }

    @Given("^Open the (.*) URL$")
    public void openUrl(String URL) {
        CommonLib.navigateToURL(oDriver, URL);
    }

    @Then("^I see (.*) page$")
    public void seePage(String page) {
        commonLib.seePage(page);
    }

    @When("^(?:I )?click element: (\\w+(?: \\w+)*) at index (\\d+)")
    public boolean clickElement(String element, int index) {
        WebElement object = commonLib.findElement(element, index);
        boolean flag = false;
        try {
            if (object != null) {
                object.click();
                System.out.println("Clicked on object-->" + element);
                Allure.addAttachment("Element is clicked.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                reportResult("PASS", "I clicked the element: " + element, true);
                return true;
            }
        } catch (Exception e) {
            reportResult("FAIL", "I cannot clicked the element: " + element, true);
            Allure.addAttachment("Element is not clicked.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            Assert.fail("Could not clicked the element:" + element);
            flag = false;
        }
        return flag;
    }

    @Then("I refresh page")
    public void RefreshPage() {
        oDriver.navigate().refresh();
    }

    @Then("^(?:I )?get and store (\\w+(?: \\w+)*) Director Text for The Circus at index (\\d+)")
    public String iGetAndStoreTheDirectorTextForTheCircus(String info1_TheCircus, int index){


            WebElement Director_TheCircus = commonLib.findElement(info1_TheCircus, index);

            String DirectorText_TheCircus = Director_TheCircus.getText();

            System.out.println("Director Text for The Circus is: " + DirectorText_TheCircus + "\n" + "Text was stored as a string in the return value of this method.");
            return DirectorText_TheCircus;
    }

    @Then("^(?:I )?get and store (\\w+(?: \\w+)*) Writer Text for The Circus at index (\\d+)")
    public String iGetAndStoreTheWriterTextForTheCircus(String info2_TheCircus, int index){


        WebElement Writer_TheCircus = commonLib.findElement(info2_TheCircus, index);

        String WriterText_TheCircus = Writer_TheCircus.getText();

        System.out.println("Writer Text for The Circus is: " + WriterText_TheCircus + "\n" + "Text was stored as a string in the return value of this method.");
        return WriterText_TheCircus;
    }

    @Then("^(?:I )?get and store (\\w+(?: \\w+)*) Stars Text for The Circus at index (\\d+)")
    public String iGetAndStoreTheStarsTextForTheCircus(String info3_TheCircus, int index){


        WebElement Stars_TheCircus = commonLib.findElement(info3_TheCircus, index);

        String StarsText_TheCircus = Stars_TheCircus.getText();

        System.out.println("Stars Text for The Circus is: " + StarsText_TheCircus + "\n" + "Text was stored as a string in the return value of this method.");
        return StarsText_TheCircus;
    }

    @Then("^(?:I )?get and store (\\w+(?: \\w+)*) Director Text for Jazz Singer at index (\\d+)")
    public String iGetAndStoreTheDirectorTextForTheJazzSinger(String info1_TheJazzSinger, int index){


        WebElement Director_TheJazzSinger = commonLib.findElement(info1_TheJazzSinger, index);

        String DirectorText_TheJazzSinger = Director_TheJazzSinger.getText();

        System.out.println("Director Text for The Jazz Singer is: " + DirectorText_TheJazzSinger + "\n" + "Text was stored as a string in the return value of this method.");
        return DirectorText_TheJazzSinger;
    }

    @Then("^(?:I )?get and store (\\w+(?: \\w+)*) Writers Text for The Jazz Singer at index (\\d+)")
    public String iGetAndStoreTheWritersTextForTheJazzSinger(String info2_TheJazzSinger, int index){


        WebElement Writers_TheJazzSinger = commonLib.findElement(info2_TheJazzSinger, index);

        String WritersText_JazzSinger = Writers_TheJazzSinger.getText();

        System.out.println("Writers Text for The Jazz Singer is: " + WritersText_JazzSinger + "\n" + "Text was stored as a string in the return value of this method.");
        return WritersText_JazzSinger;
    }

    @Then("^(?:I )?get and store (\\w+(?: \\w+)*) Stars Text for The Jazz Singer at index (\\d+)")
    public String iGetAndStoreTheStarsTextForTheJazzSinger(String info3_TheJazzSinger, int index){

        WebElement Stars_TheJazzSinger = commonLib.findElement(info3_TheJazzSinger, index);

        String StarsText_TheCircus = Stars_TheJazzSinger.getText();

        System.out.println("Stars Text for The Jazz Singer is: " + StarsText_TheCircus + "\n" + "Text was stored as a string in the return value of this method.");
        return StarsText_TheCircus;
    }

    @Then("^(?:I )?have to compare (\\w+(?: \\w+)*) element with stored Director text for The Circus at index (\\d+)")
    public void verifyTextDirectorTheCircus(String Director_TheCircus_Searchbar, int index){
            WebElement TheCircusDirectorSearchbar = commonLib.findElement(Director_TheCircus_Searchbar, index);
        String DirectorText_TheCircus_Searchbar = TheCircusDirectorSearchbar.getText();
        if (DirectorText_TheCircus_Searchbar.equals(iGetAndStoreTheDirectorTextForTheCircus("DirectorOfTheCircus", 1))){

            System.out.println("Director texts were MATCHED for The Circus.");}

            else{
                System.out.println("Director texts were NOT MATCHED for the Circus.");
                }
        }

    @Then("^(?:I )?have to compare (\\w+(?: \\w+)*) element with stored Writer text for The Circus at index (\\d+)")
    public void verifyTextWriterTheCircus(String Director_TheCircus_Searchbar, int index){
        WebElement TheCircusDirectorSearchbar = commonLib.findElement(Director_TheCircus_Searchbar, index);
        String DirectorText_TheCircus_Searchbar = TheCircusDirectorSearchbar.getText();
        if (DirectorText_TheCircus_Searchbar.equals(iGetAndStoreTheWriterTextForTheCircus("WriterOfTheCircus", 1))){

            System.out.println("Writer texts were MATCHED for The Circus.");}

        else{
            System.out.println("Writer texts were NOT MATCHED for The Circus.");
            }
    }

    @Then("^(?:I )?have to compare (\\w+(?: \\w+)*) element with stored Stars text for The Circus at index (\\d+)")
    public void verifyTextStarsTheCircus(String Director_TheCircus_Searchbar, int index){
        WebElement TheCircusDirectorSearchbar = commonLib.findElement(Director_TheCircus_Searchbar, index);
        String DirectorText_TheCircus_Searchbar = TheCircusDirectorSearchbar.getText();
        if (DirectorText_TheCircus_Searchbar.equals(iGetAndStoreTheStarsTextForTheCircus("StarsOfTheCircus", 1))){

            System.out.println("Stars texts were MATCHED for The Circus.");}

        else{
            System.out.println("Stars texts were NOT MATCHED for The Circus.");
            }
    }

    @Then("^(?:I )?have to compare (\\w+(?: \\w+)*) element with stored Director text for The Jazz Singer at index (\\d+)")
    public void verifyTextDirectorTheJazzSinger(String Director_TheCircus_Searchbar, int index){
        WebElement TheCircusDirectorSearchbar = commonLib.findElement(Director_TheCircus_Searchbar, index);
        String DirectorText_TheCircus_Searchbar = TheCircusDirectorSearchbar.getText();
        if (DirectorText_TheCircus_Searchbar.equals(iGetAndStoreTheDirectorTextForTheJazzSinger("DirectorOfTheJazzSinger", 1))){

            System.out.println("Director texts were MATCHED for The Jazz Singer.");}

        else{
            System.out.println("Director texts were NOT MATCHED for The Jazz Singer.");
            }
    }

    @Then("^(?:I )?have to compare (\\w+(?: \\w+)*) element with stored Writers text for The Jazz Singer at index (\\d+)")
    public void verifyTextWritersTheJazzSinger(String Director_TheCircus_Searchbar, int index){
        WebElement TheCircusDirectorSearchbar = commonLib.findElement(Director_TheCircus_Searchbar, index);
        String DirectorText_TheCircus_Searchbar = TheCircusDirectorSearchbar.getText();
        if (DirectorText_TheCircus_Searchbar.equals(iGetAndStoreTheWritersTextForTheJazzSinger("WritersOfTheJazzSinger", 1))){

            System.out.println("Writers texts were MATCHED for The Jazz Singer.");}

        else{
            System.out.println("Writers texts were NOT MATCHED for The Jazz Singer.");
            }
    }

    @Then("^(?:I )?have to compare (\\w+(?: \\w+)*) element with stored Stars text for The Jazz Singer at index (\\d+)")
    public void verifyTextStarsTheJazzSinger(String Director_TheCircus_Searchbar, int index){
        WebElement TheCircusDirectorSearchbar = commonLib.findElement(Director_TheCircus_Searchbar, index);
        String DirectorText_TheCircus_Searchbar = TheCircusDirectorSearchbar.getText();
        if (DirectorText_TheCircus_Searchbar.equals(iGetAndStoreTheStarsTextForTheJazzSinger("StarsOfTheJazzSinger", 1))){

            System.out.println("Stars texts were MATCHED for The Jazz Singer.");}

        else{
            System.out.println("Stars texts were NOT MATCHED for The Jazz Singer.");
            }
    }

    @Then("^I enter \"([^\"]*)\" text to (.*) at index (\\d+)")
    public boolean enterText(String text, String element, int index) throws InterruptedException {
        WebElement object;


        object = commonLib.waitElement(element, timeout, index);
        boolean flag = false;
        try {
            if (object != null) {
                object.sendKeys(text);
                System.out.println("The text has been entered:" + text);
                Allure.addAttachment("The text has been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                reportResult("PASS", "I entered the text: " + text, true);

                return true;
            }
        } catch (Exception e) {
            Allure.addAttachment("The text has not been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            reportResult("FAIL", "I cannot entered the element: " + text, true);
            Assert.fail("Could not entered the text:" + text);
            flag = false;
        }
        return flag;
    }

    @And("^I wait (.*) element (\\d+) seconds at index (\\d+)")
    public void waitElement(String element, int timeout, int index) throws InterruptedException {
        commonLib.waitElement(element, timeout, index);
    }

    @And("^I need to just wait")
    public void justWait() throws InterruptedException {
        Thread.sleep(15000);
    }

    @When("^(?:I )?double click element: (\\w+(?: \\w+)*) at index (\\d+)")
    public void doubleClickElement(String element, int index) {
        WebElement object = commonLib.findElement(element, index);
        commonLib.doubleClickElement(object);
    }

    @Then("^(?:I )?control the photo links that whether they are broken or not for The Circus at index (\\d+)")
    public void iControlThePhotoLinksThatWhetherTheyAreBrokenOrNotForTheCircus(int index) throws InterruptedException {
        WebDriver driver;

        String DemandedDomainPage = "https://www.imdb.com/title/tt0018773/mediaviewer/";
        //The for loop will create the link list according to this domain
        String controlPage1 = "https://www.imdb.com/title/tt0018773/mediaindex?ref_=ttmi_ql_1";
        String controlPage2 = "https://www.imdb.com/title/tt0018773/mediaindex?page=2&ref_=ttmi_mi_sm";
        //The link list will get its items from this page

        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver.manage().window().maximize();
        Thread.sleep(10000);
        driver.get(controlPage1);

        List<WebElement> links_1 = driver.findElements(By.tagName("a"));

        for (WebElement link : links_1) {

            String url = link.getAttribute("href");


            if (url == null || url.isEmpty()) {

                continue;
            }

            if (!url.startsWith(DemandedDomainPage)) {

                continue;
            }

            try {
                HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                int respCode = huc.getResponseCode();

                if (respCode == 200 || respCode == 403)
                //HTTP Code 200 means the link is valid.
                // HTTP Code 403 means the link is valid but not accessible for some reason.
                // Both HTTP codes represents a valid link.
                {
                    System.out.println(url + " is a valid link. The HTTP Response Code for this URL is: " + respCode);
                } else {
                    System.out.println(url + " is a broken link. The HTTP Response Code for this URL is: " + respCode);
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }




        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver.manage().window().maximize();
        Thread.sleep(10000);
        driver.get(controlPage2);

        List<WebElement> links_2 = driver.findElements(By.tagName("a"));

        for (WebElement link : links_2) {

            String url = link.getAttribute("href");


            if (url == null || url.isEmpty()) {

                continue;
            }

            if (!url.startsWith(DemandedDomainPage)) {

                continue;
            }

            try {
                HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                int respCode = huc.getResponseCode();

                if (respCode == 200 || respCode == 403)
                //HTTP Code 200 means the link is valid.
                // HTTP Code 403 means the link is valid but not accessible for some reason.
                // Both HTTP codes represents a valid link.
                {
                    System.out.println(url + " is a valid link. The HTTP Response Code for this URL is: " + respCode);
                } else {
                    System.out.println(url + " is a broken link. The HTTP Response Code for this URL is: " + respCode);
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        driver.quit();
    }

    @Then("^(?:I )?control the photo links that whether they are broken or not for The Jazz Singer at index (\\d+)")
    public void iControlThePhotoLinksThatWhetherTheyAreBrokenOrNotForTheJazzSinger(int index) throws InterruptedException {
        WebDriver driver;

        String DemandedDomainPage = "https://www.imdb.com/title/tt0018037/mediaviewer/";
        //The for loop will create the link list according to this domain
        String controlPage = "https://www.imdb.com/title/tt0018037/mediaindex/?ref_=tt_mi_sm";
        //The link list will get its items from this page

        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver.manage().window().maximize();
        Thread.sleep(10000);
        driver.get(controlPage);

        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement link : links) {

            String url = link.getAttribute("href");


            if (url == null || url.isEmpty()) {

                continue;
            }

            if (!url.startsWith(DemandedDomainPage)) {

                continue;
            }

            try {
                HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());

                huc.setRequestMethod("HEAD");

                huc.connect();

                int respCode = huc.getResponseCode();

                if (respCode == 200 || respCode == 403)
                //HTTP Code 200 means the link is valid.
                // HTTP Code 403 means the link is valid but not accessible for some reason.
                // Both HTTP codes represents a valid link.
                {
                    System.out.println(url + " is a valid link. The HTTP Response Code for this URL is: " + respCode);
                } else {
                    System.out.println(url + " is a broken link. The HTTP Response Code for this URL is: " + respCode);
                }

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        driver.quit();

    }
    }




