package com.sge.modulos.ventas.entidades;

/**
 *
 * @author elderson
 */
public class ItemListaPrecioMaquina {
    
    private int idItemListaPrecioMaquina;
    private int idListaPrecioMaquina;
    private int idMaquina;
    private String nombreMaquina;
    
    public ItemListaPrecioMaquina() {
    }

    public int getIdItemListaPrecioMaquina() {
        return idItemListaPrecioMaquina;
    }

    public void setIdItemListaPrecioMaquina(int idItemListaPrecioMaquina) {
        this.idItemListaPrecioMaquina = idItemListaPrecioMaquina;
    }

    public int getIdListaPrecioMaquina() {
        return idListaPrecioMaquina;
    }

    public void setIdListaPrecioMaquina(int idListaPrecioMaquina) {
        this.idListaPrecioMaquina = idListaPrecioMaquina;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNombreMaquina() {
        return nombreMaquina;
    }

    public void setNombreMaquina(String nombreMaquina) {
        this.nombreMaquina = nombreMaquina;
    }
}
