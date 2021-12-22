package utils;

import enums.SelectorTypes;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class ObjectIdentificationUtils {

    public static WebElement findDynamicElement(WebDriver driver, SelectorTypes selector, String locator) {
        try {
            return driver.findElement(returnLocatorBy(selector, locator));
        } catch (NoSuchElementException e) {
//            log.warn("Unable to find object of type {} having value {}", selector, locator);
            return null;
        }
    }

    public static List<WebElement> findDynamicElements(WebDriver driver, SelectorTypes selector, String locator) {
        try {
            return driver.findElements(returnLocatorBy(selector, locator));
        } catch (NoSuchElementException e) {
//            log.warn("Unable to find object of type {} having value {}", selector, locator);
            return null;
        }
    }

    public static By returnLocatorBy(SelectorTypes selector, String locator) {
        switch (selector) {
            case CSS:
                return By.cssSelector(locator);
            case ID:
                return By.id(locator);
            case NAME:
                return By.name(locator);
            case CLASS:
                return By.className(locator);
            case XPATH:
                return By.xpath(locator);
            case LINK_TEXT:
                return By.linkText(locator);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(locator);
            default:
                throw new IllegalStateException("Unexpected value: " + selector);
        }
    }
}
