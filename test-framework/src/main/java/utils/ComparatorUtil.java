package utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComparatorUtil {
    public static boolean compareTemperature(double temperatureFromUI, double temperatureFromAPI, double variance) {
        return compareValues(temperatureFromUI, temperatureFromAPI, variance);
    }

    public static boolean compareHumidity(String temperatureFromUI, double temperatureFromAPI, double variance) {
        int uiTemp = Integer.parseInt(temperatureFromUI.split("%")[0]);
        return compareValues(uiTemp, temperatureFromAPI, variance);
    }

    public static boolean compareWindSpeed(String windspeedFromUI, double windspeedFromAPI, double variance) {
        double uiWindspeed = Double.parseDouble(windspeedFromUI.split("KPH")[0].trim());
        return compareValues(uiWindspeed, windspeedFromAPI, variance);
    }

    private static boolean compareValues(double uiValue, double apiValue, double variance) {
        if (variance >= apiValue) {
            log.error("Value for variance should not be equal to or higher than the value returned from the API");
            return false;
        }
        return uiValue >= apiValue - variance && uiValue <= apiValue + variance;
    }
}
