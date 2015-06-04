package com.sge.modulos.ventas.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ItemMaterial {
    
    private int idItemMaterial;
    private int idItemCotizacion2;
    private String nombre;
    private int idMaterial;
    private String nombreMaterial;
    private double altoMaterial;
    private double largoMaterial;
    private int cantidad;
    private boolean medidaAbierta;
    private double altoMedidaAbierta;
    private double largoMedidaAbierta;
    private boolean medidaCerrada;
    private double altoMedidaCerrada;
    private double largoMedidaCerrada;
    private boolean tiraYRetira;
    private int tira;
    private int retira;
    private int idServicioImpresion;
    private String descripcionServicioImpresion;
    private int idMaquina;
    private String nombreMaquina;
    private int altoMaquina;
    private int largoMaquina;
    private String ubicacionGraficoPrecorte;
    private String ubicacionGraficoImpresion;
    private byte[] graficoPrecorte;
    private byte[] graficoImpresion;
    private List<ItemAcabado> acabados;

    public ItemMaterial() {
        acabados = new ArrayList<>();
    }
    
    public int getIdItemMaterial() {
        return idItemMaterial;
    }

    public void setIdItemMaterial(int idItemMaterial) {
        this.idItemMaterial = idItemMaterial;
    }

    public int getIdItemCotizacion2() {
        return idItemCotizacion2;
    }

    public void setIdItemCotizacion2(int idItemCotizacion2) {
        this.idItemCotizacion2 = idItemCotizacion2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isMedidaAbierta() {
        return medidaAbierta;
    }

    public void setMedidaAbierta(boolean medidaAbierta) {
        this.medidaAbierta = medidaAbierta;
    }

    public double getAltoMedidaAbierta() {
        return altoMedidaAbierta;
    }

    public void setAltoMedidaAbierta(double altoMedidaAbierta) {
        this.altoMedidaAbierta = altoMedidaAbierta;
    }

    public double getLargoMedidaAbierta() {
        return largoMedidaAbierta;
    }

    public void setLargoMedidaAbierta(double largoMedidaAbierta) {
        this.largoMedidaAbierta = largoMedidaAbierta;
    }

    public boolean isMedidaCerrada() {
        return medidaCerrada;
    }

    public void setMedidaCerrada(boolean medidaCerrada) {
        this.medidaCerrada = medidaCerrada;
    }

    public double getAltoMedidaCerrada() {
        return altoMedidaCerrada;
    }

    public void setAltoMedidaCerrada(double altoMedidaCerrada) {
        this.altoMedidaCerrada = altoMedidaCerrada;
    }

    public double getLargoMedidaCerrada() {
        return largoMedidaCerrada;
    }

    public void setLargoMedidaCerrada(double largoMedidaCerrada) {
        this.largoMedidaCerrada = largoMedidaCerrada;
    }

    public boolean isTiraYRetira() {
        return tiraYRetira;
    }

    public void setTiraYRetira(boolean tiraYRetira) {
        this.tiraYRetira = tiraYRetira;
    }

    public int getTira() {
        return tira;
    }

    public void setTira(int tira) {
        this.tira = tira;
    }

    public int getRetira() {
        return retira;
    }

    public void setRetira(int retira) {
        this.retira = retira;
    }

    public int getIdServicioImpresion() {
        return idServicioImpresion;
    }

    public void setIdServicioImpresion(int idServicioImpresion) {
        this.idServicioImpresion = idServicioImpresion;
    }

    public String getDescripcionServicioImpresion() {
        return descripcionServicioImpresion;
    }

    public void setDescripcionServicioImpresion(String descripcionServicioImpresion) {
        this.descripcionServicioImpresion = descripcionServicioImpresion;
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

    public String getUbicacionGraficoPrecorte() {
        return ubicacionGraficoPrecorte;
    }

    public void setUbicacionGraficoPrecorte(String ubicacionGraficoPrecorte) {
        this.ubicacionGraficoPrecorte = ubicacionGraficoPrecorte;
    }

    public String getUbicacionGraficoImpresion() {
        return ubicacionGraficoImpresion;
    }

    public void setUbicacionGraficoImpresion(String ubicacionGraficoImpresion) {
        this.ubicacionGraficoImpresion = ubicacionGraficoImpresion;
    }

    public byte[] getGraficoPrecorte() {
        return graficoPrecorte;
    }

    public void setGraficoPrecorte(byte[] graficoPrecorte) {
        this.graficoPrecorte = graficoPrecorte;
    }

    public byte[] getGraficoImpresion() {
        return graficoImpresion;
    }

    public void setGraficoImpresion(byte[] graficoImpresion) {
        this.graficoImpresion = graficoImpresion;
    }

    public double getAltoMaterial() {
        return altoMaterial;
    }

    public void setAltoMaterial(double altoMaterial) {
        this.altoMaterial = altoMaterial;
    }

    public double getLargoMaterial() {
        return largoMaterial;
    }

    public void setLargoMaterial(double largoMaterial) {
        this.largoMaterial = largoMaterial;
    }

    public int getAltoMaquina() {
        return altoMaquina;
    }

    public void setAltoMaquina(int altoMaquina) {
        this.altoMaquina = altoMaquina;
    }

    public int getLargoMaquina() {
        return largoMaquina;
    }

    public void setLargoMaquina(int largoMaquina) {
        this.largoMaquina = largoMaquina;
    }

    public List<ItemAcabado> getAcabados() {
        return acabados;
    }

    public void setAcabados(List<ItemAcabado> acabados) {
        this.acabados = acabados;
    }
}
