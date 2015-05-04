package com.sge.modulos.ventas.entidades;

/**
 *
 * @author elderson
 */
public class ItemListaPrecioServicio {
    
    private int idItemListaPrecioServicio;
    private int idListaPrecioServicio;
    private int idServicio;
    private String nombreServicio;
    
    public ItemListaPrecioServicio() {
    }

    public int getIdItemListaPrecioServicio() {
        return idItemListaPrecioServicio;
    }

    public void setIdItemListaPrecioServicio(int idItemListaPrecioServicio) {
        this.idItemListaPrecioServicio = idItemListaPrecioServicio;
    }

    public int getIdListaPrecioServicio() {
        return idListaPrecioServicio;
    }

    public void setIdListaPrecioServicio(int idListaPrecioServicio) {
        this.idListaPrecioServicio = idListaPrecioServicio;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
}
