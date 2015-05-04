package com.sge.modulos.ventas.clases;

/**
 *
 * @author elderson
 */
public class ItemPlantillaSolicitudCotizacion {

    private int idItemPlantillaSolicitudCotizacion;
    private int idPlantillaSolicitudCotizacion;
    private String nombre;
    private int idServicioImpresion;
    private String nombreServicioImpresion;
    private int idMaterial;
    private String nombreMaterial;
    private double altoMaterial;
    private double largoMaterial;
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
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public ItemPlantillaSolicitudCotizacion() {
    }

    public int getIdItemPlantillaSolicitudCotizacion() {
        return idItemPlantillaSolicitudCotizacion;
    }

    public void setIdItemPlantillaSolicitudCotizacion(int idItemPlantillaSolicitudCotizacion) {
        this.idItemPlantillaSolicitudCotizacion = idItemPlantillaSolicitudCotizacion;
    }

    public int getIdPlantillaSolicitudCotizacion() {
        return idPlantillaSolicitudCotizacion;
    }

    public void setIdPlantillaSolicitudCotizacion(int idPlantillaSolicitudCotizacion) {
        this.idPlantillaSolicitudCotizacion = idPlantillaSolicitudCotizacion;
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

    @Override
    public String toString() {
        return nombre;
    }
}
