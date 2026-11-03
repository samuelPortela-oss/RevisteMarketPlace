package app.service.helpers;

import app.domain.enums.MetodoPagoEnum;
import app.utils.FormRuleValidator;

public class SetMetodoPago {

    public static String getMetodoPago() {

        while (true) {

            int option = FormRuleValidator.validateInt("""
                    Seleccione el metodo de pago:
                    1. Tarjeta de credito
                    2. Tarjeta de debito
                    3. PSE
                    4. Efectivo
                    """);

            switch (option) {
                case 1:
                    return MetodoPagoEnum.TARJETA_CREDITO.getValue();
                case 2:
                    return MetodoPagoEnum.TARJETA_DEBITO.getValue();
                case 3:
                    return MetodoPagoEnum.PSE.getValue();
                case 4:
                    return MetodoPagoEnum.EFECTIVO.getValue();
                default:
                    System.out.println("Seleccione una opcion valida");
            }
        }
    }
}
