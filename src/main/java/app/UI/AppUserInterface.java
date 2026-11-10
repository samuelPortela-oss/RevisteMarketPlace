package app.UI;

import app.utils.FormRuleValidator;

public class AppUserInterface {

    private final UsuarioMenu usuarioMenu;
    private final CategoriaMenu categoriaMenu;
    private final PrendaMenu prendaMenu;
    private final CarritoMenu carritoMenu;
    private final OrdenMenu ordenMenu;
    private final PagoMenu pagoMenu;
    private final MetodoPagoMenu metodoPagoMenu;

    public AppUserInterface(
            UsuarioMenu usuarioMenu,
            CategoriaMenu categoriaMenu,
            PrendaMenu prendaMenu,
            CarritoMenu carritoMenu,
            OrdenMenu ordenMenu,
            PagoMenu pagoMenu,
            MetodoPagoMenu metodoPagoMenu) {

        this.usuarioMenu = usuarioMenu;
        this.categoriaMenu = categoriaMenu;
        this.prendaMenu = prendaMenu;
        this.carritoMenu = carritoMenu;
        this.ordenMenu = ordenMenu;
        this.pagoMenu = pagoMenu;
        this.metodoPagoMenu = metodoPagoMenu;
    }

    public void startApp() {

        int opcion;

        do {

            mostrarMenu();

            opcion = FormRuleValidator.validateInt(
                    "Seleccione una opción:");

            switch (opcion) {

                case 1:
                    usuarioMenu.abrir();
                    break;

                case 2:
                    categoriaMenu.abrir();
                    break;

                case 3:
                    prendaMenu.abrir();
                    break;

                case 4:
                    carritoMenu.abrir();
                    break;

                case 5:
                    ordenMenu.abrir();
                    break;

                case 6:
                    pagoMenu.abrir();
                    break;

                case 7:
                    metodoPagoMenu.abrir();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        System.out.println("\n=== REVISTE MARKETPLACE - ROPA DE SEGUNDA MANO ===");
        System.out.println("1. Módulo de usuarios");
        System.out.println("2. Módulo de categorías");
        System.out.println("3. Módulo de prendas");
        System.out.println("4. Módulo de carrito");
        System.out.println("5. Módulo de órdenes");
        System.out.println("6. Módulo de pagos");
        System.out.println("7. Módulo de métodos de pago");
        System.out.println("0. Salir");
    }
}
