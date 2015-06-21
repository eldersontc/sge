package com.sge.modulos.compras.cliente;

import com.sge.base.cliente.cliBase;
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
public class cliCompras extends cliBase {

    private Client client;
    private String BASE_URI;

    public cliCompras() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        BASE_URI = "http://" + getIpServidor() + "/SGE_REST//Servicios";
    }

    ///////////////////////////////// PROVEEDOR ////////////////////////////////
    public String ObtenerProveedores(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("ObtenerProveedores" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerProveedor(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("ObtenerProveedor" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarProveedor(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("RegistrarProveedor" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarProveedor(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("ActualizarProveedor" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarProveedor(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProveedorSRV");
        resource = resource.path("EliminarProveedor" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
