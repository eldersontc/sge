package com.sge.modulos.finanzas.clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntradaCaja {
    
    private int idEntradaCaja;
    private String numero;
    private boolean numeracionManual;
    private int idNumeracion;
    private String descripcionNumeracion;
    private Date fechaCreacion;
    private int idResponsable;
    private String nombreResponsable;
    private int idMoneda;
    private String simboloMoneda;
    private int idCaja;
    private String descripcionCaja;
    private String observacion;
    private double tipoCambio;
    private double subTotal;
    private double porcentajeImpuesto;
    private double montoImpuesto;
    private double total;
    private List<ItemEntradaCaja> items;

    public EntradaCaja() {
        items = new ArrayList<>();
    }

    public int getIdEntradaCaja() {
        return idEntradaCaja;
    }

    public void setIdEntradaCaja(int idEntradaCaja) {
        this.idEntradaCaja = idEntradaCaja;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isNumeracionManual() {
        return numeracionManual;
    }

    public void setNumeracionManual(boolean numeracionManual) {
        this.numeracionManual = numeracionManual;
    }

    public int getIdNumeracion() {
        return idNumeracion;
    }

    public void setIdNumeracion(int idNumeracion) {
        this.idNumeracion = idNumeracion;
    }

    public String getDescripcionNumeracion() {
        return descripcionNumeracion;
    }

    public void setDescripcionNumeracion(String descripcionNumeracion) {
        this.descripcionNumeracion = descripcionNumeracion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public int getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(int idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getSimboloMoneda() {
        return simboloMoneda;
    }

    public void setSimboloMoneda(String simboloMoneda) {
        this.simboloMoneda = simboloMoneda;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public String getDescripcionCaja() {
        return descripcionCaja;
    }

    public void setDescripcionCaja(String descripcionCaja) {
        this.descripcionCaja = descripcionCaja;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public void setPorcentajeImpuesto(double porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public double getMontoImpuesto() {
        return montoImpuesto;
    }

    public void setMontoImpuesto(double montoImpuesto) {
        this.montoImpuesto = montoImpuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ItemEntradaCaja> getItems() {
        return items;
    }

    public void setItems(List<ItemEntradaCaja> items) {
        this.items = items;
    }
}
