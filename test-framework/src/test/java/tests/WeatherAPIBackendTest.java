package tests;

import dataprovider.WeatherAPIInfoProvider;
import enums.UnitsOfMeasurement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.WeatherAPIPojo;
import selenium_framework.SoftAssertWrapper;
import utils.AllureUtils;

public class WeatherAPIBackendTest extends BaseTest {

    @Test(description = "Some scenarios to test the backend", dataProvider = "dataProviderForWeatherAPIs", dataProviderClass = WeatherAPIInfoProvider.class)
    public void testNegativeScenario(String scenarioName, String scenarioDescription, String city, boolean passAccessToken, String unitOfMeasurement, int expectedResponse) {
        SoftAssert softAssert = new SoftAssert();
        AllureUtils.updateTestCaseDetails(scenarioName, scenarioDescription);
        int responseCode;
        UnitsOfMeasurement units = UnitsOfMeasurement.valueOf(unitOfMeasurement);
        if (passAccessToken)
            responseCode = weatherService.getWeatherServiceAPIStatusCode(city, units, restAssuredHandler.getApiKey());
        else
            responseCode = weatherService.getWeatherServiceAPIStatusCode(city, units, "");
        softAssert.assertTrue(expectedResponse == responseCode, "Expected response code : " + expectedResponse + "\nActual Code : " + responseCode);
        softAssert.assertAll();
    }
}
