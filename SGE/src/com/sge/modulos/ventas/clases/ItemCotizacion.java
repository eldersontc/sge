package com.sge.modulos.ventas.clases;

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
    private int idMaquina;
    private String descripcionMaquina;
    private int largoMaximoPliegoMaquina;
    private int altoMaximoPliegoMaquina;
    private int idMaterial;
    private String nombreMaterial;
    private double largoMaterial;
    private double altoMaterial;
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
    private int largoFormatoImpresion;
    private int altoFormatoImpresion;
    private double separacionX;
    private double separacionY;
    private boolean graficoPrecorteGirado;
    private boolean graficoImpresionGirado;
    private int cantidadPiezasPrecorte;
    private int cantidadPiezasImpresion;
    private int idMetodoImpresion;
    private String descripcionMetodoImpresion;
    private int cantidadPases;
    private int cantidadCambios;
    private int cantidadMaterial;
    private int cantidadDemasia;
    private int cantidadProduccion;
    private int cantidad;
    private int cantidadPaginasSobrantes;
    private boolean incluirEnPresupuesto;
    private boolean verPrecioEnPresupuesto;
    private double totalMaquina;
    private double totalMaterial;
    private double total;
    private boolean agregar;
    private boolean actualizar;
    private boolean eliminar;

    public ItemCotizacion() {
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

    public int getLargoFormatoImpresion() {
        return largoFormatoImpresion;
    }

    public void setLargoFormatoImpresion(int largoFormatoImpresion) {
        this.largoFormatoImpresion = largoFormatoImpresion;
    }

    public int getAltoFormatoImpresion() {
        return altoFormatoImpresion;
    }

    public void setAltoFormatoImpresion(int altoFormatoImpresion) {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
