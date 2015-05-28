package com.sge.modulos.ventas.clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ItemCotizacion {
    
    private int idItemCotizacion;
    private int idCotizacion;
    private String nombre;
    private int idServicioImpresion;
    private String nombreServicioImpresion;
    private boolean impresionVinil;
    private boolean impresionBanner;
    private int idMaquina;
    private String descripcionMaquina;
    private int largoMaximoPliegoMaquina;
    private int altoMaximoPliegoMaquina;
    private int idMaterial;
    private String codigoMaterial;
    private String nombreMaterial;
    private double largoMaterial;
    private double altoMaterial;
    private int idUnidadMaterial;
    private String abreviacionUnidadMaterial;
    private int factorUnidadMaterial;
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
    private int cantidadTipoUnidad;
    private double largoFormatoImpresion;
    private double altoFormatoImpresion;
    private double separacionX;
    private double separacionY;
    private int cantidadPliegos;
    private String ubicacionGraficoPrecorte;
    private String ubicacionGraficoImpresion;
    private byte[] graficoPrecorte;
    private byte[] graficoImpresion;
    private boolean graficoPrecorteGirado;
    private boolean graficoImpresionGirado;
    private int cantidadPiezasPrecorte;
    private int cantidadPiezasImpresion;
    private int idMetodoImpresion;
    private String descripcionMetodoImpresion;
    private int cantidadPases;
    private int cantidadCambios;
    private int factorHorizontal;
    private int factorVertical;
    private String letras;
    private int cantidadMaterial;
    private int cantidadDemasia;
    private int cantidadDemasiaMaterial;
    private int cantidadProduccion;
    private int cantidad;
    private int cantidadPaginasSobrantes;
    private String observacion;
    private boolean incluirEnPresupuesto;
    private boolean verPrecioEnPresupuesto;
    private double totalMaquina;
    private double totalMaterial;
    private double totalAcabados;
    private double total;
    private List<ServicioCotizacion> acabados;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public ItemCotizacion() {
        acabados = new ArrayList<>();
    }

    public int getIdItemCotizacion() {
        return idItemCotizacion;
    }

    public void setIdItemCotizacion(int idItemCotizacion) {
        this.idItemCotizacion = idItemCotizacion;
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
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

    public boolean isImpresionVinil() {
        return impresionVinil;
    }

    public void setImpresionVinil(boolean impresionVinil) {
        this.impresionVinil = impresionVinil;
    }

    public boolean isImpresionBanner() {
        return impresionBanner;
    }

    public void setImpresionBanner(boolean impresionBanner) {
        this.impresionBanner = impresionBanner;
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

    public int getLargoMaximoPliegoMaquina() {
        return largoMaximoPliegoMaquina;
    }

    public void setLargoMaximoPliegoMaquina(int largoMaximoPliegoMaquina) {
        this.largoMaximoPliegoMaquina = largoMaximoPliegoMaquina;
    }

    public int getAltoMaximoPliegoMaquina() {
        return altoMaximoPliegoMaquina;
    }

    public void setAltoMaximoPliegoMaquina(int altoMaximoPliegoMaquina) {
        this.altoMaximoPliegoMaquina = altoMaximoPliegoMaquina;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setCodigoMaterial(String codigoMaterial) {
        this.codigoMaterial = codigoMaterial;
    }
    
    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public double getLargoMaterial() {
        return largoMaterial;
    }

    public void setLargoMaterial(double largoMaterial) {
        this.largoMaterial = largoMaterial;
    }

    public double getAltoMaterial() {
        return altoMaterial;
    }

    public void setAltoMaterial(double altoMaterial) {
        this.altoMaterial = altoMaterial;
    }

    public int getIdUnidadMaterial() {
        return idUnidadMaterial;
    }

    public void setIdUnidadMaterial(int idUnidadMaterial) {
        this.idUnidadMaterial = idUnidadMaterial;
    }

    public String getAbreviacionUnidadMaterial() {
        return abreviacionUnidadMaterial;
    }

    public void setAbreviacionUnidadMaterial(String abreviacionUnidadMaterial) {
        this.abreviacionUnidadMaterial = abreviacionUnidadMaterial;
    }
    
    public int getFactorUnidadMaterial() {
        return factorUnidadMaterial;
    }

    public void setFactorUnidadMaterial(int factorUnidadMaterial) {
        this.factorUnidadMaterial = factorUnidadMaterial;
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

    public int getCantidadTipoUnidad() {
        return cantidadTipoUnidad;
    }

    public void setCantidadTipoUnidad(int cantidadTipoUnidad) {
        this.cantidadTipoUnidad = cantidadTipoUnidad;
    }
    
    public double getLargoFormatoImpresion() {
        return largoFormatoImpresion;
    }

    public void setLargoFormatoImpresion(double largoFormatoImpresion) {
        this.largoFormatoImpresion = largoFormatoImpresion;
    }

    public double getAltoFormatoImpresion() {
        return altoFormatoImpresion;
    }

    public void setAltoFormatoImpresion(double altoFormatoImpresion) {
        this.altoFormatoImpresion = altoFormatoImpresion;
    }

    public double getSeparacionX() {
        return separacionX;
    }

    public void setSeparacionX(double separacionX) {
        this.separacionX = separacionX;
    }

    public double getSeparacionY() {
        return separacionY;
    }

    public void setSeparacionY(double separacionY) {
        this.separacionY = separacionY;
    }

    public int getCantidadPliegos() {
        return cantidadPliegos;
    }

    public void setCantidadPliegos(int cantidadPliegos) {
        this.cantidadPliegos = cantidadPliegos;
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

    public boolean isGraficoPrecorteGirado() {
        return graficoPrecorteGirado;
    }

    public void setGraficoPrecorteGirado(boolean graficoPrecorteGirado) {
        this.graficoPrecorteGirado = graficoPrecorteGirado;
    }

    public boolean isGraficoImpresionGirado() {
        return graficoImpresionGirado;
    }

    public void setGraficoImpresionGirado(boolean graficoImpresionGirado) {
        this.graficoImpresionGirado = graficoImpresionGirado;
    }

    public int getCantidadPiezasPrecorte() {
        return cantidadPiezasPrecorte;
    }

    public void setCantidadPiezasPrecorte(int cantidadPiezasPrecorte) {
        this.cantidadPiezasPrecorte = cantidadPiezasPrecorte;
    }

    public int getCantidadPiezasImpresion() {
        return cantidadPiezasImpresion;
    }

    public void setCantidadPiezasImpresion(int cantidadPiezasImpresion) {
        this.cantidadPiezasImpresion = cantidadPiezasImpresion;
    }

    public int getIdMetodoImpresion() {
        return idMetodoImpresion;
    }

    public void setIdMetodoImpresion(int idMetodoImpresion) {
        this.idMetodoImpresion = idMetodoImpresion;
    }

    public String getDescripcionMetodoImpresion() {
        return descripcionMetodoImpresion;
    }

    public void setDescripcionMetodoImpresion(String descripcionMetodoImpresion) {
        this.descripcionMetodoImpresion = descripcionMetodoImpresion;
    }

    public int getCantidadPases() {
        return cantidadPases;
    }

    public void setCantidadPases(int cantidadPases) {
        this.cantidadPases = cantidadPases;
    }

    public int getCantidadCambios() {
        return cantidadCambios;
    }

    public void setCantidadCambios(int cantidadCambios) {
        this.cantidadCambios = cantidadCambios;
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

    public int getCantidadMaterial() {
        return cantidadMaterial;
    }

    public void setCantidadMaterial(int cantidadMaterial) {
        this.cantidadMaterial = cantidadMaterial;
    }

    public int getCantidadDemasia() {
        return cantidadDemasia;
    }

    public void setCantidadDemasia(int cantidadDemasia) {
        this.cantidadDemasia = cantidadDemasia;
    }

    public int getCantidadDemasiaMaterial() {
        return cantidadDemasiaMaterial;
    }

    public void setCantidadDemasiaMaterial(int cantidadDemasiaMaterial) {
        this.cantidadDemasiaMaterial = cantidadDemasiaMaterial;
    }
    
    public int getCantidadProduccion() {
        return cantidadProduccion;
    }

    public void setCantidadProduccion(int cantidadProduccion) {
        this.cantidadProduccion = cantidadProduccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadPaginasSobrantes() {
        return cantidadPaginasSobrantes;
    }

    public void setCantidadPaginasSobrantes(int cantidadPaginasSobrantes) {
        this.cantidadPaginasSobrantes = cantidadPaginasSobrantes;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public boolean isIncluirEnPresupuesto() {
        return incluirEnPresupuesto;
    }

    public void setIncluirEnPresupuesto(boolean incluirEnPresupuesto) {
        this.incluirEnPresupuesto = incluirEnPresupuesto;
    }

    public boolean isVerPrecioEnPresupuesto() {
        return verPrecioEnPresupuesto;
    }

    public void setVerPrecioEnPresupuesto(boolean verPrecioEnPresupuesto) {
        this.verPrecioEnPresupuesto = verPrecioEnPresupuesto;
    }

    public double getTotalMaquina() {
        return totalMaquina;
    }

    public void setTotalMaquina(double totalMaquina) {
        this.totalMaquina = totalMaquina;
    }

    public double getTotalMaterial() {
        return totalMaterial;
    }

    public void setTotalMaterial(double totalMaterial) {
        this.totalMaterial = totalMaterial;
    }

    public double getTotalAcabados() {
        return totalAcabados;
    }

    public void setTotalAcabados(double totalAcabados) {
        this.totalAcabados = totalAcabados;
    }
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ServicioCotizacion> getAcabados() {
        return acabados;
    }

    public void setAcabados(List<ServicioCotizacion> acabados) {
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
    
    @Override
    public String toString(){
        return nombre;
    }
}
