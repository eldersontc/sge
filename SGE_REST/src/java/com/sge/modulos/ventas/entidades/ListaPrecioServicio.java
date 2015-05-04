package com.sge.modulos.ventas.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioServicio {
    
    private int idListaPrecioServicio;
    private String nombre;
    private boolean activo;
    private List<ItemListaPrecioServicio> items;

    public ListaPrecioServicio() {
        items = new ArrayList<>();
    }

    public int getIdListaPrecioServicio() {
        return idListaPrecioServicio;
    }

    public void setIdListaPrecioServicio(int idListaPrecioServicio) {
        this.idListaPrecioServicio = idListaPrecioServicio;
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

    public List<ItemListaPrecioServicio> getItems() {
        return items;
    }

    public void setItems(List<ItemListaPrecioServicio> items) {
        this.items = items;
    }
}
