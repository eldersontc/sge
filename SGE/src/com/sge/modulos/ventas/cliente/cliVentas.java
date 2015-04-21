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
    
    public void close() {
        client.close();
    }
}
