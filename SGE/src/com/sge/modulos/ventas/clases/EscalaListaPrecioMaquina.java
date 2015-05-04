package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class EscalaListaPrecioMaquina {
    
    private int idEscalaListaPrecioMaquina;
    private int idItemListaPrecioMaquina;
    private int desde;
    private int hasta;
    private double precio;

    public EscalaListaPrecioMaquina() {
    }

    public int getIdEscalaListaPrecioMaquina() {
        return idEscalaListaPrecioMaquina;
    }

    public void setIdEscalaListaPrecioMaquina(int idEscalaListaPrecioMaquina) {
        this.idEscalaListaPrecioMaquina = idEscalaListaPrecioMaquina;
    }

    public int getIdItemListaPrecioMaquina() {
        return idItemListaPrecioMaquina;
    }

    public void setIdItemListaPrecioMaquina(int idUnidadListaPrecioMaquina) {
        this.idItemListaPrecioMaquina = idUnidadListaPrecioMaquina;
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
