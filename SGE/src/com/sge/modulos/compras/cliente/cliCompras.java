package com.sge.modulos.compras.cliente;

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
    private String BASE_URI;

    public cliCompras() {
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

    ///////////////////////////////// PROVEEDOR ////////////////////////////////
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
