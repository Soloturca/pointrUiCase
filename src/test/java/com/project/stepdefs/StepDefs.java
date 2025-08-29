package com.project.stepdefs;


import com.saf.framework.CommonLib;
import com.saf.framework.MyTestNGBaseClass;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class StepDefs extends MyTestNGBaseClass {


    static CommonLib commonLib = new CommonLib();

    int timeout = 30;
    private List<Map<String, Long>> results = new ArrayList<>();





    public String object;


    public static HashMap<String, String> strings = new HashMap<String, String>();
    public static Map<String, Long> getTopWordsFromElement(WebDriver driver, String elementName, int limit, int index) {
        // Find element via JSON locator mapping
        WebElement element = commonLib.findElement(elementName, index);

        if (element == null) {
            System.out.println("Element not found: " + elementName);
            return Collections.emptyMap();
        }

        String text = element.getText();

        if (text == null || text.isEmpty()) {
            return Collections.emptyMap();
        }

        // Normalize text
        text = text.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", " ");

        // Split into words
        List<String> words = Arrays.asList(text.split("\\s+"));

        // Count frequencies
        Map<String, Long> wordCounts = words.stream()

                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        // Sort and limit
        return wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }



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

    @Then("I find all articles on the page")

    public List<WebElement> getAllArticles() {
        return oDriver.findElements(By.xpath("//*[@id=\"hs_cos_wrapper_dnd_area-module-4\"]/section/div/div/div[2]/div"));
    }

    @Then("I verify that all articles are loaded")
    public void IVerifyThatAllArticlesAreLoaded() {
        List<WebElement> articles = getAllArticles();
        int count = articles.size();
        if (count==9){
            System.out.println("Total number of articles: " + count + "\n" + "All articles are loaded.");
        }
        else{

            System.out.println("Total number of articles: " + count + "\n" + "All articles are not loaded.");
        }
    }
    @When("I scroll vertically by {int} pixels")
    public void iScrollVerticallyByPixels(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) oDriver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
        System.out.println("Scrolled vertically by " + pixels + " pixels.");
    }

    @When("I scroll horizontally by {int} pixels")
    public void iScrollHorizontallyByPixels(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) oDriver;
        js.executeScript("window.scrollBy(" + pixels + ",0)");
        System.out.println("Scrolled horizontally by " + pixels + " pixels.");
    }
    @When("I go back to the previous page")
    public void iGoBackToThePreviousPage() {
        oDriver.navigate().back();
        System.out.println("Navigated back to the previous page.");
    }


    @And("I extract the top {int} repeated words from the {string} at index {int}")
    public void analyzeTopWordsFromJsonLocator(int limit, String elementName, int index) {
        Map<String, Long> topWords = getTopWordsFromElement(oDriver, elementName, limit, index);
        System.out.println("Top " + limit + " words from " + elementName + ": " + topWords);
        results.add(topWords);
    }

    @And("I create .txt file {string}")
    public void saveTopWordsForArticles(String fileName) {
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            int articleNum = 1;
            for (Map<String, Long> articleResult : results) {
                writer.write("Article " + articleNum + " top words:");
                writer.newLine();
                for (Map.Entry<String, Long> entry : articleResult.entrySet()) {
                    writer.write(entry.getKey() + " : " + entry.getValue());
                    writer.newLine();
                }
                writer.newLine(); // add space between articles
                articleNum++;
            }
            System.out.println("Results saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




