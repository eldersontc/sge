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
        resources.add(com.sge.modulos.administracion.servicios.EmpleadoSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.EntidadSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.MenuSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.MonedaSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.ReporteSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.UsuarioSRV.class);
        resources.add(com.sge.modulos.administracion.servicios.ValorDefinidoSRV.class);
        resources.add(com.sge.modulos.compras.servicios.ProveedorSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.AlmacenSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.EntradaInventarioSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.ProductoSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.SalidaInventarioSRV.class);
        resources.add(com.sge.modulos.inventarios.servicios.UnidadSRV.class);
    }
    
}
