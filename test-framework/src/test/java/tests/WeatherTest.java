package tests;

import dataprovider.WeatherInfoProvider;
import enums.ExecutionFlag;
import enums.UnitsOfMeasurement;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import selenium_framework.SoftAssertWrapper;
import utils.AllureUtils;
import utils.ComparatorUtil;
import utils.WindowsUtils;

import java.util.Map;

import static constants.ConditionConstants.*;


@Slf4j
public class WeatherTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        homeActions.launchApplication(baseURL);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        WindowsUtils.closeAllWindows(driver);
    }

    @Test(description = "Comparing whether the temperature displayed on screen matches with the temperate received from the api", dataProvider = "dataProviderForWeatherScenario", dataProviderClass = WeatherInfoProvider.class)
    public void verifyTemperatureForCity(String scenarioName, String scenarioDescription, String city, String condition, String variationAllowed) {
        SoftAssert softAssert = new SoftAssertWrapper(driver, allAssertsScreenshot);
        AllureUtils.updateTestCaseDetails(scenarioName, scenarioDescription);
        Map weatherDetailsApi;
        UnitsOfMeasurement unit;

        homeActions.navigateToWeatherSection();
        Map<String, String> weatherMap = weatherActions.getWeatherDetailForCity(city);
        if (condition.equalsIgnoreCase(TEMP_IN_FAHRENHEIT))
            unit = UnitsOfMeasurement.IMPERIAL;
        else
            unit = UnitsOfMeasurement.METRIC;
        weatherDetailsApi = weatherService.getWeatherDetailsFromApi(city, unit);

        switch (condition) {
            case TEMP_IN_DEGREES:
            case TEMP_IN_FAHRENHEIT:
                softAssert.assertTrue(ComparatorUtil.compareTemperature(Double.parseDouble(weatherMap.get(condition)), (double) weatherDetailsApi.get("temp"), Double.parseDouble(variationAllowed)), "The current temperature in the UI and the current temperature from the API do not match. " +
                        "Current Temperature (UI): " + weatherMap.get(condition) + " \n Current temperature (API): " + weatherDetailsApi.get("temp") + " with an accepted degree of difference of " + variationAllowed);
                break;
            case HUMIDITY:
                softAssert.assertTrue(ComparatorUtil.compareHumidity(weatherMap.get(condition), (int) weatherDetailsApi.get("humidity"), Double.parseDouble(variationAllowed)), "The current humidity in the UI and the current humidity from the API do not match. " +
                        "Current Humidity (UI): " + weatherMap.get(condition) + " \n Current Humidity (API): " + weatherDetailsApi.get("humidity") + " with an accepted degree of difference of " + variationAllowed);
                break;
            case WIND_SPEED:
                softAssert.assertTrue(ComparatorUtil.compareWindSpeed(weatherMap.get(condition), (double) weatherDetailsApi.get("speed"), Double.parseDouble(variationAllowed)), "The current wind speed in the UI and the current wind speed from the API do not match. " +
                        "Current Wind Speed (UI): " + weatherMap.get(condition) + " \n Current Wind Speed (API): " + weatherDetailsApi.get("speed") + " with an accepted degree of difference of " + variationAllowed);
                break;
            default:
                Assert.fail("Invalid input passed as condition");
        }

        softAssert.assertAll();
    }

}
