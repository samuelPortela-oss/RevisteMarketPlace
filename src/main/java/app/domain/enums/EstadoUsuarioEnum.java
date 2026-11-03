package app.domain.enums;

public enum EstadoUsuarioEnum {

    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    BLOQUEADO("Bloqueado");

    private final String value;

    EstadoUsuarioEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
