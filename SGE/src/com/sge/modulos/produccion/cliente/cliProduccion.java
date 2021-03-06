package com.sge.modulos.produccion.cliente;

import com.sge.base.cliente.cliBase;
import com.sge.base.excepciones.Excepciones;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
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
public class cliProduccion extends cliBase {

    private Client client;
    private String BASE_URI;

    public cliProduccion() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        BASE_URI = "http://" + getIpServidor() + "/SGE_REST//Servicios";
    }

    ////////////////////////////// ORDEN TRABAJO ///////////////////////////////
    public String ObtenerOrdenesTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("ObtenerOrdenesTrabajo" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("ObtenerOrdenTrabajo" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("RegistrarOrdenTrabajo" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("ActualizarOrdenTrabajo" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarOrdenTrabajo(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("EliminarOrdenTrabajo" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String GenerarSalidaInventario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("GenerarSalidaInventario" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String GenerarSalidaCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("GenerarSalidaCaja" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////// ORDEN PRODUCCION //////////////////////////////
    public String ObtenerOrdenesProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("ObtenerOrdenesProduccion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerOrdenProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("ObtenerOrdenProduccion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarOrdenProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("RegistrarOrdenProduccion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarOrdenProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("ActualizarOrdenProduccion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarOrdenProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("EliminarOrdenProduccion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
