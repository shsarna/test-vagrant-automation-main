package actions;

import org.openqa.selenium.WebDriver;

public class BaseAction {
    WebDriver driver;

    BaseAction(WebDriver driver) {
        this.driver = driver;
    }

    public void launchApplication(String homeURL) {
        driver.get(homeURL);
        driver.manage().window().maximize();
        driver.switchTo().defaultContent();
    }
}
