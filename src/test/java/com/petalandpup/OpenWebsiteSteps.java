package com.petalandpup;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenWebsiteSteps {

    // Shared WebDriver across steps in this class
    private static WebDriver driver;
    private static final String BASE_URL = "https://petalandpup.com";

    public static WebDriver getDriver() {
        return driver;
    }

    @Given("I launch the browser")
    public void i_launch_the_browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Browser launched.");
    }

    @When("I navigate to the Petal & Pup website")
    public void i_navigate_to_the_petal_pup_website() {
        driver.get(BASE_URL);
        System.out.println("Navigated to: " + BASE_URL);
    }

    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedText) {
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        assertThat(title).containsIgnoringCase(expectedText);
    }

    @And("the current URL should contain {string}")
    public void the_current_url_should_contain(String expectedPart) {
        String url = driver.getCurrentUrl();
        System.out.println("Current URL: " + url);
        assertThat(url).contains(expectedPart);
    }

    @And("I close the browser")
    public void i_close_the_browser() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Browser closed.");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}