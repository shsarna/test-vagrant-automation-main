package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    @Step
    public static WebElement waitForElement(WebDriver driver, final WebElement webElement, int timePeriod) {
        new WebDriverWait(driver, timePeriod).until(ExpectedConditions.visibilityOf(webElement));
        return webElement;
    }

    @Step
    public static WebElement waitForElementVisibility(WebDriver driver, WebElement webElement, int timePeriod) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timePeriod))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return webElement;
    }

    @Step
    public static void waitForInvisibilityOfElement(WebDriver driver, String cssLocator, int timePeriod) {
        new WebDriverWait(driver, timePeriod)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssLocator)));
    }

    @Step
    public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement webElement, int timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }
}
