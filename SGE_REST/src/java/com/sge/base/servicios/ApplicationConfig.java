package com.sge.base.servicios;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author elderson
 */
@javax.ws.rs.ApplicationPath("/Servicios")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.sge.modulos.administracion.servicios.DepartamentoSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.DistritoSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.EmpleadoSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.EntidadSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.MenuSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.MonedaSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.NumeracionSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.ProvinciaSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.ReporteSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.UsuarioSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.ValorDefinidoSRV.class);
        resources.add(com.sge.modulos.compras.servicios.ProveedorSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.AlmacenSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.EntradaInventarioSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.ProductoSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.SalidaInventarioSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.UnidadSRV.class);
        resources.add(com.sge.modulos.produccion.servicios.OrdenTrabajoSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.ClienteSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.CotizacionSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.FormaPagoSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.ListaPrecioMaquinaSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.ListaPrecioProductoSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.ListaPrecioServicioSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.MaquinaSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.MetodoImpresionSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.PlantillaSolicitudCotizacionSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.PresupuestoSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.ServicioSRV.class);
        resources.add(com.sge.modulos.ventas.servicios.SolicitudCotizacionSRV.class);
    }
    
}
