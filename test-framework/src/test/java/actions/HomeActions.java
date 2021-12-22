package actions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.WeatherPage;

public class HomeActions extends BaseAction {
    HomePage homePage;
    WeatherPage weatherPage;

    public HomeActions(WebDriver driver) {
        super(driver);
        homePage = new HomePage(driver);
        PageFactory.initElements(driver, homePage);
        weatherPage = new WeatherPage(driver);
        PageFactory.initElements(driver, weatherPage);
    }

    @Step
    public void navigateToWeatherSection() {
        homePage.waitForHeaderLogo()
                .clickSubMenuOptions()
                .clickOnMenuLinkItem("Weather");
        //looping through for as long as loading bar is displayed
        do {
            weatherPage.waitForPageLogo();
        } while (weatherPage.loadingBarIsNotDisplayed());
    }
}
