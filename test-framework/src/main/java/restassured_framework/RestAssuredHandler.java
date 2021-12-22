package restassured_framework;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public class RestAssuredHandler {
    @Setter
    @Getter
    private String baseUrl;

    @Getter
    @Setter
    protected String apiKey;

    @Getter
    @Setter
    protected Map<String, ?> cookies;

    public Response callGetRequest(String apiUrl, Map<String, String> queryParams) {
        return given()
                .queryParams(queryParams)
                .when()
                .get(apiUrl);
    }

    public Response callGetRequest(String apiUrl) {
        return given()
                .cookies(this.getCookies())
                .when()
                .get(apiUrl);
    }

    public Response callPostRequest(String apiUrl, Map<String, String> queryParams, Map<String, String> formParams) {
        return preparePutOrPostRequest(queryParams, formParams)
                .post(apiUrl);
    }

    public Response callPostRequest(String apiUrl, Map<String, String> queryParams, String requestBody) {
        return preparePutOrPostRequest(queryParams, requestBody)
                .post(apiUrl);
    }

    public Response callPostLogin(String apiUrl, Map<String, String> formParam) {
        return given()
                .formParams(formParam)
                .when()
                .post(apiUrl);
    }

    public Response callPostLogin(String apiUrl, Map<String, String> queryParam, Map<String, String> formParam) {
        return given()
                .queryParams(queryParam)
                .formParams(formParam)
                .when()
                .post(apiUrl);
    }

    public Response callPutRequest(String apiUrl, Map<String, String> queryParams, Map<String, String> formParams) {
        return preparePutOrPostRequest(queryParams, formParams)
                .put(apiUrl);
    }

    public Response callPutRequest(String apiUrl, Map<String, String> queryParams, String requestBody) {
        return preparePutOrPostRequest(queryParams, requestBody)
                .put(apiUrl);
    }

    public Response callPutRequest(String apiUrl, Map<String, String> formParams) {
        return given()
                .cookies(this.getCookies())
                .formParams(formParams)
                .when()
                .post(apiUrl);
    }

    public Response callDeleteRequest(String apiUrl, Map<String, String> queryParams) {
        return given()
                .cookies(this.getCookies())
                .queryParams(queryParams)
                .when()
                .delete(apiUrl);
    }

    public Response callDeleteRequest(String apiUrl) {
        return given()
                .cookies(this.getCookies())
                .when()
                .delete(apiUrl);
    }

    //and this
    private RequestSpecification preparePutOrPostRequest(Map<String, String> queryParams, Map<String, String> formParams) {
        return given()
                .cookies(this.getCookies())
                .queryParams(queryParams)
                .formParams(formParams)
                .when();
    }

    private RequestSpecification preparePutOrPostRequest(Map<String, String> queryParams, String requestBody) {
        return given()
                .cookies(this.getCookies())
                .contentType(ContentType.JSON)
                .queryParams(queryParams)
                .body(requestBody)
                .when();
    }

}
