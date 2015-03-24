package com.sge.modulos.compras.cliente;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST<br>
 * USAGE:
 * <pre>
 *        cliCompras client = new cliCompras();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author elderson
 */
public class cliCompras {
    
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SGE_REST//Servicios";

    public cliCompras() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
    }
    
    ////////////////////////////////// EMPLEADO ////////////////////////////////
    
    public String ObtenerProveedores(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("ObtenerProveedores");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ObtenerProveedor(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("ObtenerProveedor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String RegistrarProveedor(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("RegistrarProveedor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ActualizarProveedor(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("ActualizarProveedor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String EliminarProveedor(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("EliminarProveedor");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public void close() {
        client.close();
    }
}
