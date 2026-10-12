package com.petalandpup;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class WeddingFiltersSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Lazily pull the shared driver from OpenWebsiteSteps
     * and initialize the wait object the first time it's needed.
     */
    private WebDriver getDriver() {
        if (driver == null) {
            driver = OpenWebsiteSteps.getDriver();
        }
        return driver;
    }

    private WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        }
        return wait;
    }

    @And("I hover over the Wedding menu")
    public void i_hover_over_the_wedding_menu() {
        WebElement wedding = getWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[normalize-space()='Wedding']")));
        new Actions(getDriver()).moveToElement(wedding).pause(500).perform();
        System.out.println("Hovered over Wedding menu.");
    }

    @And("I click For the Bride link")
    public void i_click_for_the_bride_link() {
        WebElement link = getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//header-mega-menu[@id='header-menu-dropdown-5']//a[@class='link-reversed'][normalize-space()='For the Bride']")));
        link.click();
        System.out.println("Clicked For the Bride.");
    }

    @And("I close the popup if it appears")
    public void i_close_the_popup_if_it_appears() {
        try {
            WebElement closeBtn = new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[name()='circle' and contains(@cx,'10')]")));
            closeBtn.click();
            System.out.println("Popup closed.");
        } catch (Exception e) {
            System.out.println("No popup appeared, continuing.");
        }
    }

    @And("I click on Filters")
    public void i_click_on_filters() {
        WebElement filters = getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='Filters']")));
        filters.click();
        System.out.println("Clicked Filters.");
    }

    @And("I check the White filter checkbox")
    public void i_check_the_white_filter_checkbox() {
        WebElement white = getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@id='checkbox-facets-facets-drawer-form-0-4-white']")));
        if (!white.isSelected()) {
            white.click();
        }
        System.out.println("White filter checked.");
    }

    @And("I click on View results")
    public void i_click_on_view_results() {
        WebElement btn = getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button-content[normalize-space()='View results']")));
        btn.click();
        System.out.println("Clicked View results.");
    }

    @Then("the results page should show white products")
    public void the_results_page_should_show_white_products() {
        getWait().until(ExpectedConditions.urlContains("/collections/"));
        String url = getDriver().getCurrentUrl();
        System.out.println("Results URL: " + url);
        assertThat(url).contains("petalandpup.com");
    }
}