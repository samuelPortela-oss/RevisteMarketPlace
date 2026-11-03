package app.service.inputports;

public interface PrendaUseCase {

    // Solo declaramos los métodos que se van a usar (sin código dentro)
    void createPrenda(int id, String nombre, String marca, String talla, String estado, double precio);

    void showPrendas();

    void showPrendaById(int id);

    void deletePrenda(int id);
}
