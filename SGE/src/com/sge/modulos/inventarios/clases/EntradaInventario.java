package com.sge.modulos.inventarios.clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntradaInventario {
    
    private int idEntradaInventario;
    private String numero;
    private int idProveedor;
    private String razonSocialProveedor;
    private int idResponsable;
    private String nombreResponsable;
    private Date fechaCreacion;
    private int idAlmacen;
    private String descripcionAlmacen;
    private int idMoneda;
    private String simboloMoneda;
    private String observacion;
    private double tipoCambio;
    private double subTotal;
    private double porcentajeImpuesto;
    private double montoImpuesto;
    private double total;
    private List<ItemEntradaInventario> items;

    public EntradaInventario() {
        this.items = new ArrayList<>();
    }

    public int getIdEntradaInventario() {
        return idEntradaInventario;
    }

    public void setIdEntradaInventario(int idEntradaInventario) {
        this.idEntradaInventario = idEntradaInventario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getDescripcionAlmacen() {
        return descripcionAlmacen;
    }

    public void setDescripcionAlmacen(String descripcionAlmacen) {
        this.descripcionAlmacen = descripcionAlmacen;
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

    public List<ItemEntradaInventario> getItems() {
        return items;
    }

    public void setItems(List<ItemEntradaInventario> items) {
        this.items = items;
    }
}
