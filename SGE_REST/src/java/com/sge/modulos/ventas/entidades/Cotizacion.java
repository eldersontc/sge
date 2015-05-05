package com.sge.modulos.ventas.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author elderson
 */
public class Cotizacion {
    
    private int idCotizacion;
    private String numero;
    private boolean numeracionManual;
    private int idNumeracion;
    private String descripcionNumeracion;
    private Date fechaCreacion;
    private String descripcion;
    private int idCliente;
    private String razonSocialCliente;
    private int idListaPrecioProducto;
    private String nombreListaPrecioProducto;
    private int idListaPrecioServicio;
    private String nombreListaPrecioServicio;
    private int idListaPrecioMaquina;
    private String nombreListaPrecioMaquina;
    private int idMoneda;
    private String simboloMoneda;
    private int idCotizador;
    private String nombreCotizador;
    private int idVendedor;
    private String nombreVendedor;
    private int idFormaPago;
    private String descripcionFormaPago;
    private String lineaProduccion;
    private int cantidad;
    private String observacion;
    private int idContactoCliente;
    private String nombreContactoCliente;
    private double porcentajeUtilidad;
    private double subTotal;
    private double montoUtilidad;
    private double total;
    private String estado;
    private List<ItemCotizacion> items;

    public Cotizacion() {
        items = new ArrayList<>();
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazonSocialCliente() {
        return razonSocialCliente;
    }

    public void setRazonSocialCliente(String razonSocialCliente) {
        this.razonSocialCliente = razonSocialCliente;
    }

    public int getIdListaPrecioProducto() {
        return idListaPrecioProducto;
    }

    public void setIdListaPrecioProducto(int idListaPrecioProducto) {
        this.idListaPrecioProducto = idListaPrecioProducto;
    }

    public String getNombreListaPrecioProducto() {
        return nombreListaPrecioProducto;
    }

    public void setNombreListaPrecioProducto(String nombreListaPrecioProducto) {
        this.nombreListaPrecioProducto = nombreListaPrecioProducto;
    }

    public int getIdListaPrecioServicio() {
        return idListaPrecioServicio;
    }

    public void setIdListaPrecioServicio(int idListaPrecioServicio) {
        this.idListaPrecioServicio = idListaPrecioServicio;
    }

    public String getNombreListaPrecioServicio() {
        return nombreListaPrecioServicio;
    }

    public void setNombreListaPrecioServicio(String nombreListaPrecioServicio) {
        this.nombreListaPrecioServicio = nombreListaPrecioServicio;
    }

    public int getIdListaPrecioMaquina() {
        return idListaPrecioMaquina;
    }

    public void setIdListaPrecioMaquina(int idListaPrecioMaquina) {
        this.idListaPrecioMaquina = idListaPrecioMaquina;
    }

    public String getNombreListaPrecioMaquina() {
        return nombreListaPrecioMaquina;
    }

    public void setNombreListaPrecioMaquina(String nombreListaPrecioMaquina) {
        this.nombreListaPrecioMaquina = nombreListaPrecioMaquina;
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

    public int getIdCotizador() {
        return idCotizador;
    }

    public void setIdCotizador(int idCotizador) {
        this.idCotizador = idCotizador;
    }

    public String getNombreCotizador() {
        return nombreCotizador;
    }

    public void setNombreCotizador(String nombreCotizador) {
        this.nombreCotizador = nombreCotizador;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getDescripcionFormaPago() {
        return descripcionFormaPago;
    }

    public void setDescripcionFormaPago(String descripcionFormaPago) {
        this.descripcionFormaPago = descripcionFormaPago;
    }

    public String getLineaProduccion() {
        return lineaProduccion;
    }

    public void setLineaProduccion(String lineaProduccion) {
        this.lineaProduccion = lineaProduccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getIdContactoCliente() {
        return idContactoCliente;
    }

    public void setIdContactoCliente(int idContactoCliente) {
        this.idContactoCliente = idContactoCliente;
    }

    public String getNombreContactoCliente() {
        return nombreContactoCliente;
    }

    public void setNombreContactoCliente(String nombreContactoCliente) {
        this.nombreContactoCliente = nombreContactoCliente;
    }

    public double getPorcentajeUtilidad() {
        return porcentajeUtilidad;
    }

    public void setPorcentajeUtilidad(double porcentajeUtilidad) {
        this.porcentajeUtilidad = porcentajeUtilidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getMontoUtilidad() {
        return montoUtilidad;
    }

    public void setMontoUtilidad(double montoUtilidad) {
        this.montoUtilidad = montoUtilidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ItemCotizacion> getItems() {
        return items;
    }

    public void setItems(List<ItemCotizacion> items) {
        this.items = items;
    }
}
