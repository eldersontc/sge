package com.sge.modulos.ventas.cliente;

import com.sge.base.cliente.cliBase;
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
public class cliVentas extends cliBase {

    private Client client;
    private String BASE_URI;

    public cliVentas() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        BASE_URI = "http://" + getIpServidor() + "/SGE_REST//Servicios";
    }

    /////////////////////////////////// CLIENTE ////////////////////////////////
    public String ObtenerClientes(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("ObtenerClientes"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("ObtenerCliente"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("RegistrarCliente"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("ActualizarCliente"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("EliminarCliente"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerContactosCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("ObtenerContactosCliente"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerDireccionesCliente(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ClienteSRV");
        resource = resource.path("ObtenerDireccionesCliente"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////// PLANTILLA SOLICITUD COTIZACION ///////////////////////
    public String ObtenerPlantillasSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("ObtenerPlantillasSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerPlantillasSolicitudCotizacionConItems(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("ObtenerPlantillasSolicitudCotizacionConItems"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ObtenerPlantillaSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("ObtenerPlantillaSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarPlantillaSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("RegistrarPlantillaSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarPlantillaSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("ActualizarPlantillaSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarPlantillaSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PlantillaSolicitudCotizacionSRV");
        resource = resource.path("EliminarPlantillaSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// SERVICIO ////////////////////////////////
    public String ObtenerServicios(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ObtenerServicios"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ObtenerServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("RegistrarServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ActualizarServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("EliminarServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerServicioUnidades(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ObtenerServicioUnidades"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerUnidadesPorServicios(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ServicioSRV");
        resource = resource.path("ObtenerUnidadesPorServicios"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// MAQUINA ////////////////////////////////
    public String ObtenerMaquinas(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("ObtenerMaquinas"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("ObtenerMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("RegistrarMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("ActualizarMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MaquinaSRV");
        resource = resource.path("EliminarMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////// SOLICITUD COTIZACION ///////////////////////////
    public String ObtenerSolicitudesCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("ObtenerSolicitudesCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("ObtenerSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("RegistrarSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("ActualizarSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("EliminarSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String AprobarSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("AprobarSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String DesaprobarSolicitudCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("DesaprobarSolicitudCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String GenerarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SolicitudCotizacionSRV");
        resource = resource.path("GenerarCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// FORMA PAGO ///////////////////////////////
    public String ObtenerFormasPago(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FormaPagoSRV");
        resource = resource.path("ObtenerFormasPago"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarFormaPago(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FormaPagoSRV");
        resource = resource.path("RegistrarFormaPago"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarFormaPago(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FormaPagoSRV");
        resource = resource.path("ActualizarFormaPago"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarFormaPago(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FormaPagoSRV");
        resource = resource.path("EliminarFormaPago"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// COTIZACION ////////////////////////////////
    public String ObtenerCotizaciones(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("ObtenerCotizaciones"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("ObtenerCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("RegistrarCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("ActualizarCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("EliminarCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String AprobarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("AprobarCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String DesaprobarCotizacion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("DesaprobarCotizacion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEscalasMaquinaYProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CotizacionSRV");
        resource = resource.path("ObtenerEscalasMaquinaYProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////// LISTA PRECIO PRODUCTO ////////////////////////////
    public String ObtenerListasPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ObtenerListasPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ObtenerListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerUnidadesListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ObtenerUnidadesListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEscalasListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ObtenerEscalasListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("RegistrarListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarItemsListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("RegistrarItemsListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarUnidadesListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("RegistrarUnidadesListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEscalaListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("RegistrarEscalaListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ActualizarListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarEscalaListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("ActualizarEscalaListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("EliminarListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarItemListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("EliminarItemListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarUnidadListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("EliminarUnidadListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEscalaListaPrecioProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioProductoSRV");
        resource = resource.path("EliminarEscalaListaPrecioProducto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////// LISTA PRECIO SERVICIO ////////////////////////////
    public String ObtenerListasPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerListasPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerUnidadesListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerUnidadesListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEscalasListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerEscalasListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEscalasListaPrecioServicioPorUnidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ObtenerEscalasListaPrecioServicioPorUnidad"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("RegistrarListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarItemsListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("RegistrarItemsListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarUnidadesListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("RegistrarUnidadesListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEscalaListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("RegistrarEscalaListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ActualizarListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarEscalaListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("ActualizarEscalaListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("EliminarListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarItemListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("EliminarItemListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarUnidadListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("EliminarUnidadListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEscalaListaPrecioServicio(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioServicioSRV");
        resource = resource.path("EliminarEscalaListaPrecioServicio"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////// LISTA PRECIO MAQUINA ////////////////////////////
    public String ObtenerListasPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ObtenerListasPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ObtenerListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEscalasListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ObtenerEscalasListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("RegistrarListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarItemsListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("RegistrarItemsListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEscalaListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("RegistrarEscalaListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ActualizarListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarItemListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ActualizarItemListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarEscalaListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("ActualizarEscalaListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("EliminarListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarItemListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("EliminarItemListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEscalaListaPrecioMaquina(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ListaPrecioMaquinaSRV");
        resource = resource.path("EliminarEscalaListaPrecioMaquina"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// METODO IMPRESION ////////////////////////////
    public String ObtenerMetodosImpresion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MetodoImpresionSRV");
        resource = resource.path("ObtenerMetodosImpresion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarMetodoImpresion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MetodoImpresionSRV");
        resource = resource.path("RegistrarMetodoImpresion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarMetodoImpresion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MetodoImpresionSRV");
        resource = resource.path("ActualizarMetodoImpresion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarMetodoImpresion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MetodoImpresionSRV");
        resource = resource.path("EliminarMetodoImpresion"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// PRESUPUESTO ///////////////////////////////
    public String ObtenerPresupuestos(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PresupuestoSRV");
        resource = resource.path("ObtenerPresupuestos"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerPresupuesto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PresupuestoSRV");
        resource = resource.path("ObtenerPresupuesto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarPresupuesto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PresupuestoSRV");
        resource = resource.path("RegistrarPresupuesto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarPresupuesto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PresupuestoSRV");
        resource = resource.path("ActualizarPresupuesto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarPresupuesto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PresupuestoSRV");
        resource = resource.path("EliminarPresupuesto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String CambiarEstadoPresupuesto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PresupuestoSRV");
        resource = resource.path("CambiarEstadoPresupuesto"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String GenerarOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PresupuestoSRV");
        resource = resource.path("GenerarOrdenTrabajo"+ getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
