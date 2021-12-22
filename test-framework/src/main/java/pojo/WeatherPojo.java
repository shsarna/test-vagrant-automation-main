package pojo;

public class WeatherPojo {
    public String scenarioName, scenarioDescription, execution, city, condition, variationAllowed;
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

    public String getCondition() {
        return condition;
    }

    public String getVariationAllowed() {
        return variationAllowed;
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

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setVariationAllowed(String variationAllowed) {
        this.variationAllowed = variationAllowed;
    }
}
