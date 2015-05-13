package com.sge.modulos.ventas.entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ItemListaPrecioMaquina {
    
    private int idItemListaPrecioMaquina;
    private int idListaPrecioMaquina;
    private int idMaquina;
    private String nombreMaquina;
    private int factor;
    private List<EscalaListaPrecioMaquina> escalas;
    
    public ItemListaPrecioMaquina() {
        escalas = new ArrayList<>();
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
    
    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public List<EscalaListaPrecioMaquina> getEscalas() {
        return escalas;
    }

    public void setEscalas(List<EscalaListaPrecioMaquina> escalas) {
        this.escalas = escalas;
    }
}
