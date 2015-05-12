package com.sge.modulos.ventas.cliente;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST<br>
 * USAGE:
 * <pre>
 *        cliVentas client = new cliVentas();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author elderson
 */
public class cliVentas {

    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SGE_REST//Servicios";

    public cliVentas() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
    }

    /////////////////////////////////// CLIENTE ////////////////////////////////
    public String ObtenerClientes(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("ObtenerClientes");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("ObtenerCliente");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("RegistrarCliente");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("ActualizarCliente");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("EliminarCliente");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////// PLANTILLA SOLICITUD COTIZACION ///////////////////////
    public String ObtenerPlantillasSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("ObtenerPlantillasSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerPlantillaSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("ObtenerPlantillaSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarPlantillaSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("RegistrarPlantillaSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarPlantillaSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("ActualizarPlantillaSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarPlantillaSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("EliminarPlantillaSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// SERVICIO ////////////////////////////////
    public String ObtenerServicios(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ObtenerServicios");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ObtenerServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("RegistrarServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ActualizarServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("EliminarServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerServicioUnidades(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ObtenerServicioUnidades");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// MAQUINA ////////////////////////////////
    public String ObtenerMaquinas(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("ObtenerMaquinas");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("ObtenerMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("RegistrarMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("ActualizarMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("EliminarMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////// SOLICITUD COTIZACION ///////////////////////////
    public String ObtenerSolicitudesCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("ObtenerSolicitudesCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("ObtenerSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("RegistrarSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("ActualizarSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("EliminarSolicitudCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String GenerarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("GenerarCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// FORMA PAGO ///////////////////////////////
    public String ObtenerFormasPago(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FormaPagoSRV");
        resource = resource.path("ObtenerFormasPago");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarFormaPago(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FormaPagoSRV");
        resource = resource.path("RegistrarFormaPago");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarFormaPago(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FormaPagoSRV");
        resource = resource.path("ActualizarFormaPago");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarFormaPago(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FormaPagoSRV");
        resource = resource.path("EliminarFormaPago");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// COTIZACION ////////////////////////////////
    public String ObtenerCotizaciones(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("ObtenerCotizaciones");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("ObtenerCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("RegistrarCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("ActualizarCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("EliminarCotizacion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ObtenerEscalasMaquinaYProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("ObtenerEscalasMaquinaYProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////// LISTA PRECIO PRODUCTO ////////////////////////////
    public String ObtenerListasPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ObtenerListasPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ObtenerListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerUnidadesListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ObtenerUnidadesListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEscalasListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ObtenerEscalasListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("RegistrarListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarItemsListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("RegistrarItemsListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarUnidadesListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("RegistrarUnidadesListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEscalaListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("RegistrarEscalaListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ActualizarListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarEscalaListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ActualizarEscalaListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("EliminarListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarItemListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("EliminarItemListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarUnidadListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("EliminarUnidadListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEscalaListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("EliminarEscalaListaPrecioProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////// LISTA PRECIO SERVICIO ////////////////////////////
    public String ObtenerListasPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerListasPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerUnidadesListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerUnidadesListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEscalasListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerEscalasListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("RegistrarListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarItemsListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("RegistrarItemsListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarUnidadesListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("RegistrarUnidadesListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEscalaListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("RegistrarEscalaListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ActualizarListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarEscalaListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ActualizarEscalaListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("EliminarListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarItemListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("EliminarItemListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarUnidadListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("EliminarUnidadListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEscalaListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("EliminarEscalaListaPrecioServicio");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////// LISTA PRECIO MAQUINA ////////////////////////////
    public String ObtenerListasPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ObtenerListasPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ObtenerListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEscalasListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ObtenerEscalasListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("RegistrarListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarItemsListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("RegistrarItemsListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEscalaListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("RegistrarEscalaListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ActualizarListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarEscalaListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ActualizarEscalaListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("EliminarListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarItemListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("EliminarItemListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEscalaListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("EliminarEscalaListaPrecioMaquina");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// METODO IMPRESION ////////////////////////////
    public String ObtenerMetodosImpresion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MetodoImpresionSRV");
        resource = resource.path("ObtenerMetodosImpresion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarMetodoImpresion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MetodoImpresionSRV");
        resource = resource.path("RegistrarMetodoImpresion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarMetodoImpresion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MetodoImpresionSRV");
        resource = resource.path("ActualizarMetodoImpresion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarMetodoImpresion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MetodoImpresionSRV");
        resource = resource.path("EliminarMetodoImpresion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
