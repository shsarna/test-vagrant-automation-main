package pojo;

public class WeatherAPIPojo {
    public String scenarioName, scenarioDescription, execution, city, unitMeasurement;
    boolean passAccessToken;
    int expectedResponse;

    public String getScenarioName() {
        return scenarioName;
    }

    public String getScenarioDescription() {
        return scenarioDescription;
    }

    public String getExecution() {
        return execution;
    }

    public String getCity() {
        return city;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public boolean getPassAccessToken() {
        return passAccessToken;
    }

    public int getExpectedResponse() {
        return expectedResponse;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public void setScenarioDescription(String scenarioDescription) {
        this.scenarioDescription = scenarioDescription;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public void setVariationAllowed(boolean passAccessToken) {
        this.passAccessToken = passAccessToken;
    }

    public void setExpectedResponse(int expectedResponse) {
        this.expectedResponse = expectedResponse;
    }
}
