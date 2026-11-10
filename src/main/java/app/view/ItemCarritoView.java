package app.view;

import app.domain.ItemCarrito;
import app.service.inputports.ItemCarritoUseCase;

import java.util.List;

public class ItemCarritoView {

    private ItemCarritoUseCase itemCarritoUseCase;

    public ItemCarritoView(
            ItemCarritoUseCase itemCarritoUseCase) {

        this.itemCarritoUseCase = itemCarritoUseCase;
    }

    public void mostrarItemCarrito(ItemCarrito itemCarrito) {

        System.out.println(itemCarrito);
    }

    public void mostrarItemsCarrito(List<ItemCarrito> itemsCarrito) {

        if (itemsCarrito.isEmpty()) {

            System.out.println("No se encontraron items de carrito.");
            return;
        }

        System.out.println("\n========== ITEMS DEL CARRITO ==========");

        for (ItemCarrito itemCarrito : itemsCarrito) {

            System.out.println("ID item: " + itemCarrito.getIdItemCarrito()
                    + " | ID carrito: " + itemCarrito.getIdCarrito()
                    + " | ID prenda: " + itemCarrito.getIdPrenda()
                    + " | Cantidad: " + itemCarrito.getCantidad());
        }

        System.out.println("=====================");
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
