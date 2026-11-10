package app.domain;

public class FotoPrenda {

    private int idFoto;
    private int idPrenda;
    private String ruta;
    private int orden;

    public FotoPrenda() {
    }

    public FotoPrenda(int idFoto, int idPrenda, String ruta, int orden) {
        this.idFoto = idFoto;
        this.idPrenda = idPrenda;
        this.ruta = ruta;
        this.orden = orden;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "FotoPrenda{" +
                "idFoto=" + idFoto +
                ", idPrenda=" + idPrenda +
                ", ruta='" + ruta + '\'' +
                ", orden=" + orden +
                '}';
    }
}
