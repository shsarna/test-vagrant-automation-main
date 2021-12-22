package services;

import restassured_framework.RestAssuredHandler;

public class BaseService {
    RestAssuredHandler restAssuredHandler;

    public BaseService(RestAssuredHandler restAssuredHandler) {
        this.restAssuredHandler = restAssuredHandler;
    }
}
