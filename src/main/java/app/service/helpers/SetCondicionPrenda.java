package app.service.helpers;

import app.domain.enums.CondicionPrendaEnum;
import app.utils.FormRuleValidator;

public class SetCondicionPrenda {

    public static CondicionPrendaEnum getCondicionPrenda() {

        while (true) {

            int opcion = FormRuleValidator.validateInt(
                    "Condición (1. Excelente, 2. Bueno, 3. Regular):");

            switch (opcion) {

                case 1:
                    return CondicionPrendaEnum.EXCELENTE;

                case 2:
                    return CondicionPrendaEnum.BUENO;

                case 3:
                    return CondicionPrendaEnum.REGULAR;

                default:
                    System.out.println("Opción de condición inválida.");
            }
        }
    }
}
