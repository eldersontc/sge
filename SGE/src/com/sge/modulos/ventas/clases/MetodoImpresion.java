package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class MetodoImpresion {
    
    private int idMetodoImpresion;
    private String nombre;
    private int factorPases;
    private int factorCambios;
    private int factorHorizontal;
    private int factorVertical;
    private String letras;
    private boolean activo;

    public MetodoImpresion() {
    }

    public int getIdMetodoImpresion() {
        return idMetodoImpresion;
    }

    public void setIdMetodoImpresion(int idMetodoImpresion) {
        this.idMetodoImpresion = idMetodoImpresion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFactorPases() {
        return factorPases;
    }

    public void setFactorPases(int factorPases) {
        this.factorPases = factorPases;
    }

    public int getFactorCambios() {
        return factorCambios;
    }

    public void setFactorCambios(int factorCambios) {
        this.factorCambios = factorCambios;
    }

    public int getFactorHorizontal() {
        return factorHorizontal;
    }

    public void setFactorHorizontal(int factorHorizontal) {
        this.factorHorizontal = factorHorizontal;
    }

    public int getFactorVertical() {
        return factorVertical;
    }

    public void setFactorVertical(int factorVertical) {
        this.factorVertical = factorVertical;
    }

    public String getLetras() {
        return letras;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
