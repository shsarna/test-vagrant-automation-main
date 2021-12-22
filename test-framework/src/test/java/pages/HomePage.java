package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.util.List;

public class HomePage extends BasePage{


    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "ndtvlogo")
    WebElement headerLogo;
    @FindBy(id = "h_sub_menu")
    WebElement subMenuOption;
    @FindBy(css = "div.topnav_cont a")
    List<WebElement> menuNavigationLinks;

    public HomePage waitForHeaderLogo() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(headerLogo));
        return this;
    }

    public HomePage clickSubMenuOptions() {
        WaitUtils.waitForElementToBeClickable(driver, subMenuOption, 5).click();
        return this;
    }

    public HomePage clickOnMenuLinkItem(String menuItem) {
        menuNavigationLinks.stream().filter(ele -> ele.getText().trim().equalsIgnoreCase(menuItem.trim()))
                .findFirst()
                .get()
                .click();
        return this;
    }
}
