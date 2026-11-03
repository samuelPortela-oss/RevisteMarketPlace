package app.service.helpers;

import app.domain.enums.EstadoPrendaEnum;
import app.utils.FormRuleValidator;

public class SetEstadoPrenda {

    public static String getEstadoPrenda() {

        while (true) {

            int option = FormRuleValidator.validateInt("""
                    Seleccione el estado de la prenda:
                    1. Excelente
                    2. Bueno
                    3. Regular
                    """);

            switch (option) {
                case 1:
                    return EstadoPrendaEnum.EXCELENTE.getValue();
                case 2:
                    return EstadoPrendaEnum.BUENO.getValue();
                case 3:
                    return EstadoPrendaEnum.REGULAR.getValue();
                default:
                    System.out.println("Seleccione una opcion valida");
            }
        }
    }
}
