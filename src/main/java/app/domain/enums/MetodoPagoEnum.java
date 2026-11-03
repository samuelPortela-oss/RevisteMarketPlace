package app.domain.enums;

public enum MetodoPagoEnum {

    TARJETA_CREDITO("Tarjeta de credito"),
    TARJETA_DEBITO("Tarjeta de debito"),
    PSE("PSE"),
    EFECTIVO("Efectivo");

    private final String value;

    MetodoPagoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
