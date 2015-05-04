package com.sge.modulos.ventas.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioMaquina {
    
    private int idListaPrecioMaquina;
    private String nombre;
    private boolean activo;
    private List<ItemListaPrecioMaquina> items;

    public ListaPrecioMaquina() {
        items = new ArrayList<>();
    }

    public int getIdListaPrecioMaquina() {
        return idListaPrecioMaquina;
    }

    public void setIdListaPrecioMaquina(int idListaPrecioMaquina) {
        this.idListaPrecioMaquina = idListaPrecioMaquina;
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

    public List<ItemListaPrecioMaquina> getItems() {
        return items;
    }

    public void setItems(List<ItemListaPrecioMaquina> items) {
        this.items = items;
    }
}
