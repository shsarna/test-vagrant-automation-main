package pages;

import org.openqa.selenium.WebDriver;

/**
 * @author karthiksharma
 * The purpose of this BasePage class is to act as the base for all common objects found in multiple areas of the website.
 * Objects that would typically be added here would include the header, logo, etc.
 * This would typically
 */
public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
