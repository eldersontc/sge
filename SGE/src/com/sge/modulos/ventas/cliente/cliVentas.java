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
    
    ///////////////////// SOLICITUD COTIZACION ///////////////////////
    
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
    
    public void close() {
        client.close();
    }
}
