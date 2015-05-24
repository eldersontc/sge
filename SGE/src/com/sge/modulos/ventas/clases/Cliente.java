package com.sge.modulos.ventas.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class Cliente {
    
    private int idCliente;
    private String razonSocial;
    private String tipoDocumentoIdentidad;
    private String documentoIdentidad;
    private String nombreComercial;
    private String fechaUltimaVenta;
    private String telefono;
    private String correo;
    private int idVendedor;
    private String nombreVendedor;
    private int idListaPrecioProducto;
    private String nombreListaPrecioProducto;
    private int idListaPrecioServicio;
    private String nombreListaPrecioServicio;
    private int idListaPrecioMaquina;
    private String nombreListaPrecioMaquina;
    private List<DireccionCliente> direcciones;
    private List<ContactoCliente> contactos;
    private boolean  activo;

    public Cliente() {
        direcciones = new ArrayList<>();
        contactos = new ArrayList<>();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getFechaUltimaVenta() {
        return fechaUltimaVenta;
    }

    public void setFechaUltimaVenta(String fechaUltimaVenta) {
        this.fechaUltimaVenta = fechaUltimaVenta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public List<DireccionCliente> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionCliente> direcciones) {
        this.direcciones = direcciones;
    }

    public List<ContactoCliente> getContactos() {
        return contactos;
    }

    public void setContactos(List<ContactoCliente> contactos) {
        this.contactos = contactos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
