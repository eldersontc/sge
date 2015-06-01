package com.sge.modulos.finanzas.cliente;

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
 *        cliFinanzas client = new cliFinanzas();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author elderson
 */
public class cliFinanzas {

    private Client client;
    private String BASE_URI;

    public cliFinanzas() {
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

    //////////////////////////////////// CAJA //////////////////////////////////
    public String ObtenerCajas(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CajaSRV");
        resource = resource.path("ObtenerCajas");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CajaSRV");
        resource = resource.path("RegistrarCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CajaSRV");
        resource = resource.path("ActualizarCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("CajaSRV");
        resource = resource.path("EliminarCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// ENTRADA DE CAJA ////////////////////////////
    public String ObtenerEntradasCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntradaCajaSRV");
        resource = resource.path("ObtenerEntradasCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEntradaCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntradaCajaSRV");
        resource = resource.path("ObtenerEntradaCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEntradaCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntradaCajaSRV");
        resource = resource.path("RegistrarEntradaCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEntradaCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntradaCajaSRV");
        resource = resource.path("EliminarEntradaCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// SALIDA DE CAJA /////////////////////////////
    public String ObtenerSalidasCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaCajaSRV");
        resource = resource.path("ObtenerSalidasCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerSalidaCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaCajaSRV");
        resource = resource.path("ObtenerSalidaCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarSalidaCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaCajaSRV");
        resource = resource.path("RegistrarSalidaCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarSalidaCaja(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaCajaSRV");
        resource = resource.path("EliminarSalidaCaja");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
