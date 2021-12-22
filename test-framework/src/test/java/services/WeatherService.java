package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.UnitsOfMeasurement;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import restassured_framework.RestAssuredHandler;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class WeatherService extends BaseService {
    String apiUrl = restAssuredHandler.getBaseUrl();
    Map<String, String> queryParams;
    public WeatherService(RestAssuredHandler restAssuredHandler) {
        super(restAssuredHandler);
    }

    public Map getWeatherDetailsFromApi(String cityName, UnitsOfMeasurement units) {
        queryParams = new HashMap() {{
            put("q", cityName);
            put("appid", restAssuredHandler.getApiKey());
            put("units", units.label);
        }};
        Response response = restAssuredHandler.callGetRequest(apiUrl, queryParams);
        //have to create two separate maps since the wind and temperature data points are inside different json objects within the main json
        //I know I can figure something better here, might revisit this if I have time
        Map tempDetails = JsonUtils.getJsonObjectAsMap(response.getBody().asString(), "/main");
        Map windDetails = JsonUtils.getJsonObjectAsMap(response.getBody().asString(), "/wind");
        tempDetails.putAll(windDetails);
        return tempDetails;
    }

    public int getWeatherServiceAPIStatusCode(String cityName, UnitsOfMeasurement units, String apiKey) {
        queryParams = new HashMap() {{
            put("q", cityName);
            put("appid", apiKey);
            put("units", units.label);
        }};
        Response response = restAssuredHandler.callGetRequest(apiUrl, queryParams);
        return response.getStatusCode();
    }
}
