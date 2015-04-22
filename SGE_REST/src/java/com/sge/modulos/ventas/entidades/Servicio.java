package com.sge.modulos.ventas.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class Servicio {
    
    private int idServicio;
    private String codigo;
    private String descripcion;
    private boolean servicioImpresion;
    private List<ServicioUnidad> unidades;
    private List<ServicioMaquina> maquinas;
    private boolean activo;

    public Servicio() {
        unidades = new ArrayList<>();
        maquinas = new ArrayList<>();
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isServicioImpresion() {
        return servicioImpresion;
    }

    public void setServicioImpresion(boolean servicioImpresion) {
        this.servicioImpresion = servicioImpresion;
    }

    public List<ServicioUnidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<ServicioUnidad> unidades) {
        this.unidades = unidades;
    }

    public List<ServicioMaquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<ServicioMaquina> maquinas) {
        this.maquinas = maquinas;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
