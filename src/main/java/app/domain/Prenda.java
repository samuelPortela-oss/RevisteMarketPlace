package app.domain;

public class Prenda {

    // 1. Atributos (las características de la prenda)
    private int id;
    private String nombre;
    private String marca;
    private String talla;
    private String estado;
    private double precio;

    // 2. Constructor vacío
    public Prenda() {
    }

    // 3. Constructor con parámetros
    public Prenda(int id, String nombre, String marca, String talla, String estado, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.talla = talla;
        this.estado = estado;
        this.precio = precio;
    }

    // 4. Getters y Setters (sirven para obtener y modificar los datos)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

