package enums;

public enum UnitsOfMeasurement {
    STANDARD("standard"),
    METRIC("metric"),
    IMPERIAL("imperial");
    public final String label;
    UnitsOfMeasurement(String label) {
        this.label = label;
    }
}
