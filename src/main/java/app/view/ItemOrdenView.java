package app.view;

import app.domain.ItemOrden;
import app.service.inputports.ItemOrdenUseCase;

import java.util.List;

public class ItemOrdenView {

    private ItemOrdenUseCase itemOrdenUseCase;

    public ItemOrdenView(
            ItemOrdenUseCase itemOrdenUseCase) {

        this.itemOrdenUseCase = itemOrdenUseCase;
    }

    public void mostrarItemOrden(ItemOrden itemOrden) {

        System.out.println(itemOrden);
    }

    public void mostrarItemsOrden(List<ItemOrden> itemsOrden) {

        if (itemsOrden.isEmpty()) {

            System.out.println("No se encontraron items de orden.");
            return;
        }

        System.out.println("\n========== ITEMS DE LA ORDEN ==========");

        for (ItemOrden itemOrden : itemsOrden) {

            System.out.println("ID item: " + itemOrden.getIdItemOrden()
                    + " | ID orden: " + itemOrden.getIdOrden()
                    + " | Prenda: " + itemOrden.getNombrePrenda()
                    + " | Cantidad: " + itemOrden.getCantidad()
                    + " | Subtotal: " + itemOrden.getSubtotal());
        }

        System.out.println("=====================");
    }

    public void mostrarMensaje(String mensaje) {

        System.out.println(mensaje);
    }
}
