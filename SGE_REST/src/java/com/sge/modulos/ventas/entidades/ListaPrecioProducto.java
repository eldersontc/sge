package com.sge.modulos.ventas.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioProducto {
    
    private int idListaPrecioProducto;
    private String nombre;
    private boolean activo;
    private List<ItemListaPrecioProducto> items;

    public ListaPrecioProducto() {
        items = new ArrayList<>();
    }

    public int getIdListaPrecioProducto() {
        return idListaPrecioProducto;
    }

    public void setIdListaPrecioProducto(int idListaPrecioProducto) {
        this.idListaPrecioProducto = idListaPrecioProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<ItemListaPrecioProducto> getItems() {
        return items;
    }

    public void setItems(List<ItemListaPrecioProducto> items) {
        this.items = items;
    }
}
