package pages;

import enums.SelectorTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ObjectIdentificationUtils;
import utils.WaitUtils;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherPage extends BasePage {
    public WeatherPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "logo")
    WebElement weatherPageLogo;
    @FindBy(id = "loading")
    WebElement loadingBar;
    @FindBy(css = "input[type='text'][id='searchBox']")
    WebElement citySearchBox;
    @FindBy(className = "leaflet-popup-content")
    WebElement cityWeatherContent;

    public WeatherPage waitForPageLogo() {
        WaitUtils.waitForElementVisibility(driver, weatherPageLogo, 5);
        return this;
    }

    public WeatherPage setSearchCity(String cityName) {
        citySearchBox.sendKeys(cityName);
        return this;
    }

    public WeatherPage selectCityFromList(String cityName) {
        WebElement dynamicElement = ObjectIdentificationUtils.findDynamicElement(driver, SelectorTypes.ID, cityName);
        if (!dynamicElement.isSelected())
            dynamicElement.click();
        return this;
    }

    public WeatherPage clickOnCityInMap(String cityName) {
        ObjectIdentificationUtils.findDynamicElement(driver, SelectorTypes.CSS, "div[title='"+cityName+"']").click();
        return this;
    }

    public WeatherPage waitForWeatherPopup() {
        WaitUtils.waitForElementVisibility(driver, cityWeatherContent, 10);
        return this;
    }

    public List<String> getWeatherDetailsForCity() {
        WaitUtils.waitForElementVisibility(driver, cityWeatherContent, 5);
        return cityWeatherContent.findElements(By.cssSelector("span.heading"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean loadingBarIsNotDisplayed() {
        return loadingBar.isDisplayed();
    }
}
