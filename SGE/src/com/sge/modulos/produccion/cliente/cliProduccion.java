package com.sge.modulos.produccion.cliente;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST<br>
 * USAGE:
 * <pre>
 *        cliProduccion client = new cliProduccion();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author elderson
 */
public class cliProduccion {

    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SGE_REST//Servicios";

    public cliProduccion() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
    }

    ////////////////////////////// ORDEN TRABAJO ///////////////////////////////
    public String ObtenerOrdenesTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("ObtenerOrdenesTrabajo");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("ObtenerOrdenTrabajo");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("RegistrarOrdenTrabajo");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("ActualizarOrdenTrabajo");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("EliminarOrdenTrabajo");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
