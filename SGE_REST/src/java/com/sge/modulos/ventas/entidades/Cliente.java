package com.sge.modulos.ventas.entidades;

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
    private List<DireccionCliente> direcciones;
    private List<Object[]> direccionesConNombres;
    private List<ContactoCliente> contactos;
    private boolean  activo;

    public Cliente() {
        direcciones = new ArrayList<>();
        direccionesConNombres = new ArrayList<>();
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

    public List<DireccionCliente> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionCliente> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Object[]> getDireccionesConNombres() {
        return direccionesConNombres;
    }

    public void setDireccionesConNombres(List<Object[]> direccionesConNombres) {
        this.direccionesConNombres = direccionesConNombres;
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