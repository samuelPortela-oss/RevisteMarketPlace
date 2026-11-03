package app.domain.enums;

public enum EstadoPrendaEnum {

    EXCELENTE("Excelente"),
    BUENO("Bueno"),
    REGULAR("Regular");

    private final String value;

    EstadoPrendaEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
