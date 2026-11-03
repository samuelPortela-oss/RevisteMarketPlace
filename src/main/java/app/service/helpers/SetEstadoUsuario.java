package app.service.helpers;

import app.domain.enums.EstadoUsuarioEnum;
import app.utils.FormRuleValidator;

public class SetEstadoUsuario {

    public static String getEstadoUsuario() {

        while (true) {

            int option = FormRuleValidator.validateInt("""
                    Seleccione el estado del usuario:
                    1. Activo
                    2. Inactivo
                    3. Bloqueado
                    """);

            switch (option) {
                case 1:
                    return EstadoUsuarioEnum.ACTIVO.getValue();
                case 2:
                    return EstadoUsuarioEnum.INACTIVO.getValue();
                case 3:
                    return EstadoUsuarioEnum.BLOQUEADO.getValue();
                default:
                    System.out.println("Seleccione una opcion valida");
            }
        }
    }
}
