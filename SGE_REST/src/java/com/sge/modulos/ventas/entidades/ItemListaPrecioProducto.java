package com.sge.modulos.ventas.entidades;

/**
 *
 * @author elderson
 */
public class ItemListaPrecioProducto {
    
    private int idItemListaPrecioProducto;
    private int idListaPrecioProducto;
    private int idProducto;
    private String nombreProducto;
    
    public ItemListaPrecioProducto() {
    }

    public int getIdItemListaPrecioProducto() {
        return idItemListaPrecioProducto;
    }

    public void setIdItemListaPrecioProducto(int idItemListaPrecioProducto) {
        this.idItemListaPrecioProducto = idItemListaPrecioProducto;
    }

    public int getIdListaPrecioProducto() {
        return idListaPrecioProducto;
    }

    public void setIdListaPrecioProducto(int idListaPrecioProducto) {
        this.idListaPrecioProducto = idListaPrecioProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
