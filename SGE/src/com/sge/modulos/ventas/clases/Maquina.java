package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class Maquina {
    
    private int idMaquina;
    private String descripcion;
    private String tipoMaquina;
    private int cantidadCuerpos;
    private int minimoGramaje;
    private int maximoGramaje;
    private int largoMinimoPliego;
    private int largoMaximoPliego;
    private int altoMinimoPliego;
    private int altoMaximoPliego;
    private int margenPinza;
    private int margenSalida;
    private int margenEscuadra;
    private int margenContraEscuadra;
    private int margenCalle;
    private int minimaResolucion;
    private int maximaResolucion;
    private boolean activo;

    public Maquina() {
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoMaquina() {
        return tipoMaquina;
    }

    public void setTipoMaquina(String tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }

    public int getCantidadCuerpos() {
        return cantidadCuerpos;
    }

    public void setCantidadCuerpos(int cantidadCuerpos) {
        this.cantidadCuerpos = cantidadCuerpos;
    }

    public int getMinimoGramaje() {
        return minimoGramaje;
    }

    public void setMinimoGramaje(int minimoGramaje) {
        this.minimoGramaje = minimoGramaje;
    }

    public int getMaximoGramaje() {
        return maximoGramaje;
    }

    public void setMaximoGramaje(int maximoGramaje) {
        this.maximoGramaje = maximoGramaje;
    }

    public int getLargoMinimoPliego() {
        return largoMinimoPliego;
    }

    public void setLargoMinimoPliego(int largoMinimoPliego) {
        this.largoMinimoPliego = largoMinimoPliego;
    }

    public int getLargoMaximoPliego() {
        return largoMaximoPliego;
    }

    public void setLargoMaximoPliego(int largoMaximoPliego) {
        this.largoMaximoPliego = largoMaximoPliego;
    }

    public int getAltoMinimoPliego() {
        return altoMinimoPliego;
    }

    public void setAltoMinimoPliego(int altoMinimoPliego) {
        this.altoMinimoPliego = altoMinimoPliego;
    }

    public int getAltoMaximoPliego() {
        return altoMaximoPliego;
    }

    public void setAltoMaximoPliego(int altoMaximoPliego) {
        this.altoMaximoPliego = altoMaximoPliego;
    }

    public int getMargenPinza() {
        return margenPinza;
    }

    public void setMargenPinza(int margenPinza) {
        this.margenPinza = margenPinza;
    }

    public int getMargenSalida() {
        return margenSalida;
    }

    public void setMargenSalida(int margenSalida) {
        this.margenSalida = margenSalida;
    }

    public int getMargenEscuadra() {
        return margenEscuadra;
    }

    public void setMargenEscuadra(int margenEscuadra) {
        this.margenEscuadra = margenEscuadra;
    }

    public int getMargenContraEscuadra() {
        return margenContraEscuadra;
    }

    public void setMargenContraEscuadra(int margenContraEscuadra) {
        this.margenContraEscuadra = margenContraEscuadra;
    }

    public int getMargenCalle() {
        return margenCalle;
    }

    public void setMargenCalle(int margenCalle) {
        this.margenCalle = margenCalle;
    }

    public int getMinimaResolucion() {
        return minimaResolucion;
    }

    public void setMinimaResolucion(int minimaResolucion) {
        this.minimaResolucion = minimaResolucion;
    }

    public int getMaximaResolucion() {
        return maximaResolucion;
    }

    public void setMaximaResolucion(int maximaResolucion) {
        this.maximaResolucion = maximaResolucion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
