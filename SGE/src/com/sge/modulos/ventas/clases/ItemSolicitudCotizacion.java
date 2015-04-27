package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class ItemSolicitudCotizacion {
    
    private int idItemSolicitudCotizacion;
    private int idSolicitudCotizacion;
    private String nombre;
    private int idServicioImpresion;
    private String nombreServicioImpresion;
    private int idMaquina;
    private String descripcionMaquina;
    private int idMaterial;
    private String nombreMaterial;
    private String nombreTipoUnidad;
    private String unidadMedidaAbierta;
    private boolean medidaAbierta;
    private boolean medidaCerrada;
    private boolean tiraRetira;
    private boolean grafico;
    private boolean material;
    private boolean servicioImpresion;
    private boolean fondo;
    private boolean tipoUnidad;
    private double largoMedidaAbierta;
    private double altoMedidaAbierta;
    private double largoMedidaCerrada;
    private double altoMedidaCerrada;
    private double tiraColor;
    private double retiraColor;
    private double dFondo;
    private String acabados;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public ItemSolicitudCotizacion() {
    }

    public int getIdItemSolicitudCotizacion() {
        return idItemSolicitudCotizacion;
    }

    public void setIdItemSolicitudCotizacion(int idItemSolicitudCotizacion) {
        this.idItemSolicitudCotizacion = idItemSolicitudCotizacion;
    }

    public int getIdSolicitudCotizacion() {
        return idSolicitudCotizacion;
    }

    public void setIdSolicitudCotizacion(int idSolicitudCotizacion) {
        this.idSolicitudCotizacion = idSolicitudCotizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdServicioImpresion() {
        return idServicioImpresion;
    }

    public void setIdServicioImpresion(int idServicioImpresion) {
        this.idServicioImpresion = idServicioImpresion;
    }

    public String getNombreServicioImpresion() {
        return nombreServicioImpresion;
    }

    public void setNombreServicioImpresion(String nombreServicioImpresion) {
        this.nombreServicioImpresion = nombreServicioImpresion;
    }

    public int getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(int idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getDescripcionMaquina() {
        return descripcionMaquina;
    }

    public void setDescripcionMaquina(String descripcionMaquina) {
        this.descripcionMaquina = descripcionMaquina;
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

    public String getNombreTipoUnidad() {
        return nombreTipoUnidad;
    }

    public void setNombreTipoUnidad(String nombreTipoUnidad) {
        this.nombreTipoUnidad = nombreTipoUnidad;
    }

    public String getUnidadMedidaAbierta() {
        return unidadMedidaAbierta;
    }

    public void setUnidadMedidaAbierta(String unidadMedidaAbierta) {
        this.unidadMedidaAbierta = unidadMedidaAbierta;
    }

    public boolean isMedidaAbierta() {
        return medidaAbierta;
    }

    public void setMedidaAbierta(boolean medidaAbierta) {
        this.medidaAbierta = medidaAbierta;
    }

    public boolean isMedidaCerrada() {
        return medidaCerrada;
    }

    public void setMedidaCerrada(boolean medidaCerrada) {
        this.medidaCerrada = medidaCerrada;
    }

    public boolean isTiraRetira() {
        return tiraRetira;
    }

    public void setTiraRetira(boolean tiraRetira) {
        this.tiraRetira = tiraRetira;
    }

    public boolean isGrafico() {
        return grafico;
    }

    public void setGrafico(boolean grafico) {
        this.grafico = grafico;
    }

    public boolean isMaterial() {
        return material;
    }

    public void setMaterial(boolean material) {
        this.material = material;
    }

    public boolean isServicioImpresion() {
        return servicioImpresion;
    }

    public void setServicioImpresion(boolean servicioImpresion) {
        this.servicioImpresion = servicioImpresion;
    }

    public boolean isFondo() {
        return fondo;
    }

    public void setFondo(boolean fondo) {
        this.fondo = fondo;
    }

    public boolean isTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(boolean tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public double getLargoMedidaAbierta() {
        return largoMedidaAbierta;
    }

    public void setLargoMedidaAbierta(double largoMedidaAbierta) {
        this.largoMedidaAbierta = largoMedidaAbierta;
    }

    public double getAltoMedidaAbierta() {
        return altoMedidaAbierta;
    }

    public void setAltoMedidaAbierta(double altoMedidaAbierta) {
        this.altoMedidaAbierta = altoMedidaAbierta;
    }

    public double getLargoMedidaCerrada() {
        return largoMedidaCerrada;
    }

    public void setLargoMedidaCerrada(double largoMedidaCerrada) {
        this.largoMedidaCerrada = largoMedidaCerrada;
    }

    public double getAltoMedidaCerrada() {
        return altoMedidaCerrada;
    }

    public void setAltoMedidaCerrada(double altoMedidaCerrada) {
        this.altoMedidaCerrada = altoMedidaCerrada;
    }

    public double getTiraColor() {
        return tiraColor;
    }

    public void setTiraColor(double tiraColor) {
        this.tiraColor = tiraColor;
    }

    public double getRetiraColor() {
        return retiraColor;
    }

    public void setRetiraColor(double retiraColor) {
        this.retiraColor = retiraColor;
    }

    public double getdFondo() {
        return dFondo;
    }

    public void setdFondo(double dFondo) {
        this.dFondo = dFondo;
    }

    public String getAcabados() {
        return acabados;
    }

    public void setAcabados(String acabados) {
        this.acabados = acabados;
    }

    public boolean isAgregar() {
        return agregar;
    }

    public void setAgregar(boolean agregar) {
        this.agregar = agregar;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
}
