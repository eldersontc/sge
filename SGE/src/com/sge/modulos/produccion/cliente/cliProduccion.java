package com.sge.modulos.produccion.cliente;

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
public class cliProduccion {

    private Client client;
    private String BASE_URI;

    public cliProduccion() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        BASE_URI = "http://" + getIpServidor() + "/SGE_REST//Servicios";
    }

    public String getIpServidor() {
        Properties prop = new Properties();
        InputStream input = null;
        String ipServidor = null;
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "/SGE_CONF/config.properties");
            prop.load(input);
            ipServidor = prop.getProperty("ipServidor");
            input.close();
        } catch (IOException ex) {
            Excepciones.EscribirLog(ex);
        }
        return ipServidor;
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
    
    public String GenerarSalidaInventario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("GenerarSalidaInventario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String GenerarSalidaCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenTrabajoSRV");
        resource = resource.path("GenerarSalidaCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////// ORDEN PRODUCCION //////////////////////////////
    public String ObtenerOrdenesProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("ObtenerOrdenesProduccion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerOrdenProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("ObtenerOrdenProduccion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarOrdenProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("RegistrarOrdenProduccion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarOrdenProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("ActualizarOrdenProduccion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarOrdenProduccion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("OrdenProduccionSRV");
        resource = resource.path("EliminarOrdenProduccion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
