package com.sge.modulos.ventas.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ItemCotizacion2 {
    
    private int idItemCotizacion2;
    private int idCotizacion2;
    private String nombre;
    private String observacion;
    private boolean incluirEnPresupuesto;
    private boolean mostrarPrecioEnPresupuesto;
    private List<ItemMaterial> materiales;

    public ItemCotizacion2() {
        materiales = new ArrayList<>();
    }

    public int getIdItemCotizacion2() {
        return idItemCotizacion2;
    }

    public void setIdItemCotizacion2(int idItemCotizacion2) {
        this.idItemCotizacion2 = idItemCotizacion2;
    }

    public int getIdCotizacion2() {
        return idCotizacion2;
    }

    public void setIdCotizacion2(int idCotizacion2) {
        this.idCotizacion2 = idCotizacion2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isIncluirEnPresupuesto() {
        return incluirEnPresupuesto;
    }

    public void setIncluirEnPresupuesto(boolean incluirEnPresupuesto) {
        this.incluirEnPresupuesto = incluirEnPresupuesto;
    }

    public boolean isMostrarPrecioEnPresupuesto() {
        return mostrarPrecioEnPresupuesto;
    }

    public void setMostrarPrecioEnPresupuesto(boolean mostrarPrecioEnPresupuesto) {
        this.mostrarPrecioEnPresupuesto = mostrarPrecioEnPresupuesto;
    }

    public List<ItemMaterial> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<ItemMaterial> materiales) {
        this.materiales = materiales;
    }
}
