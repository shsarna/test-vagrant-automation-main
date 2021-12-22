package actions;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.WeatherPage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.ConditionConstants.*;

public class WeatherActions extends BaseAction {
    WeatherPage weatherPage;

    public WeatherActions(WebDriver driver) {
        super(driver);
        weatherPage = new WeatherPage(driver);
        PageFactory.initElements(driver, weatherPage);
    }

    @Step
    public void selectCityAndOpenDetailsInMap(String cityName) {
        weatherPage.setSearchCity(cityName)
                .selectCityFromList(cityName)
                .clickOnCityInMap(cityName)
                .waitForWeatherPopup();
    }

    @Step
    public Map<String, String> getWeatherDetailForCity(String cityName) {
        selectCityAndOpenDetailsInMap(cityName);
        List<String> weatherParameters = Arrays.asList(TEMP_IN_DEGREES, TEMP_IN_FAHRENHEIT, HUMIDITY, WIND_SPEED, WEATHER_CONDITION);
        Map<String, String> weatherDetailsMap = new HashMap<>();
        List<String> weatherDetails = weatherPage.getWeatherDetailsForCity();
        System.out.println(weatherDetails);
        //storing content of the weather information in a map.

        for (String weatherInfo : weatherDetails) {
            String[] split = weatherInfo.split(":");
            if(split.length > 0)
                weatherDetailsMap.put(split[0].trim(), split[1].trim());
            if(!weatherParameters.contains(split[0].trim()))
                Assert.fail("The Map details did not display one of the primary conditions");
        }
        return weatherDetailsMap;
    }
}
