package tests;

import actions.HomeActions;
import actions.WeatherActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import restassured_framework.RestAssuredHandler;
import selenium_framework.SeleniumBaseFramework;
import services.WeatherService;

public class BaseTest extends SeleniumBaseFramework {
    String baseURL;
    HomeActions homeActions;
    WeatherActions weatherActions;
    public RestAssuredHandler restAssuredHandler;
    WeatherService weatherService;

    @BeforeClass(alwaysRun = true)
    public void test() {
        //todo the url will eventually be picked from a configuration file.
        baseURL = getURL();
        initActions(driver);
        initServices();
    }

    private void initActions(WebDriver driver) {
        homeActions = new HomeActions(driver);
        weatherActions = new WeatherActions(driver);
    }

    private String getURL() {
        return properties.getProperty("APP.URL");
    }

    private void initServices() {
        restAssuredHandler = new RestAssuredHandler();
        restAssuredHandler.setBaseUrl(properties.getProperty("apiUrl"));
        restAssuredHandler.setApiKey(properties.getProperty("apiKey"));
        weatherService = new WeatherService(restAssuredHandler);
    }
}
