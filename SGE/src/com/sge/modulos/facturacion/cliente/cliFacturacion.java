package com.sge.modulos.facturacion.cliente;

import com.sge.base.cliente.cliBase;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST<br>
 * USAGE:
 * <pre>
 *        cliFacturacion client = new cliFacturacion();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author elderson
 */
public class cliFacturacion extends cliBase {

    private Client client;
    private String BASE_URI;

    public cliFacturacion() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        BASE_URI = "http://" + getIpServidor() + "/SGE_REST//Servicios";
    }

    /////////////////////////////////// FACTURA ////////////////////////////////
    public String ObtenerFacturas(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FacturaSRV");
        resource = resource.path("ObtenerFacturas");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerFactura(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FacturaSRV");
        resource = resource.path("ObtenerFactura");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarFactura(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FacturaSRV");
        resource = resource.path("RegistrarFactura");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarFactura(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("FacturaSRV");
        resource = resource.path("EliminarFactura");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// FACTURA ////////////////////////////////
    public String ObtenerGuiasRemision(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("GuiaRemisionSRV");
        resource = resource.path("ObtenerGuiasRemision");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerGuiaRemision(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("GuiaRemisionSRV");
        resource = resource.path("ObtenerGuiaRemision");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarGuiaRemision(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("GuiaRemisionSRV");
        resource = resource.path("RegistrarGuiaRemision");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarGuiaRemision(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("GuiaRemisionSRV");
        resource = resource.path("ActualizarGuiaRemision");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarGuiaRemision(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("GuiaRemisionSRV");
        resource = resource.path("EliminarGuiaRemision");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
