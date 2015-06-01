package com.sge.modulos.inventarios.cliente;

import com.sge.base.cliente.cliBase;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST<br>
 * USAGE:
 * <pre>
 *        cliInventarios client = new cliInventarios();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author elderson
 */
public class cliInventarios extends cliBase {

    private Client client;
    private String BASE_URI;

    public cliInventarios() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        BASE_URI = "http://" + getIpServidor() + "/SGE_REST//Servicios";
    }

    ////////////////////////////// ALMACEN /////////////////////////////////////
    public String ObtenerAlmacenes(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("AlmacenSRV");
        resource = resource.path("ObtenerAlmacenes");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarAlmacen(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("AlmacenSRV");
        resource = resource.path("RegistrarAlmacen");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarAlmacen(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("AlmacenSRV");
        resource = resource.path("ActualizarAlmacen");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarAlmacen(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("AlmacenSRV");
        resource = resource.path("EliminarAlmacen");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// UNIDAD //////////////////////////////////////
    public String ObtenerUnidades(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UnidadSRV");
        resource = resource.path("ObtenerUnidades");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarUnidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UnidadSRV");
        resource = resource.path("RegistrarUnidad");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarUnidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UnidadSRV");
        resource = resource.path("ActualizarUnidad");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarUnidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UnidadSRV");
        resource = resource.path("EliminarUnidad");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// PRODUCTO ////////////////////////////////
    public String ObtenerProductos(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProductoSRV");
        resource = resource.path("ObtenerProductos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerProductosPorAlmacen(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProductoSRV");
        resource = resource.path("ObtenerProductosPorAlmacen");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProductoSRV");
        resource = resource.path("ObtenerProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProductoSRV");
        resource = resource.path("RegistrarProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProductoSRV");
        resource = resource.path("ActualizarProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarProducto(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProductoSRV");
        resource = resource.path("EliminarProducto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerUnidadesPorProductos(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProductoSRV");
        resource = resource.path("ObtenerUnidadesPorProductos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerProductoUnidades(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProductoSRV");
        resource = resource.path("ObtenerProductoUnidades");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////// ENTRADA DE INVENTARIO //////////////////////////
    public String ObtenerEntradaInventarios(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntradaInventarioSRV");
        resource = resource.path("ObtenerEntradaInventarios");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEntradaInventario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntradaInventarioSRV");
        resource = resource.path("ObtenerEntradaInventario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEntradaInventario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntradaInventarioSRV");
        resource = resource.path("RegistrarEntradaInventario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEntradaInventario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntradaInventarioSRV");
        resource = resource.path("EliminarEntradaInventario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////// SALIDA DE INVENTARIO //////////////////////////
    public String ObtenerSalidaInventarios(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaInventarioSRV");
        resource = resource.path("ObtenerSalidaInventarios");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerSalidaInventario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaInventarioSRV");
        resource = resource.path("ObtenerSalidaInventario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarSalidaInventario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaInventarioSRV");
        resource = resource.path("RegistrarSalidaInventario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarSalidaInventario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaInventarioSRV");
        resource = resource.path("EliminarSalidaInventario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerStockFisicoItems(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("SalidaInventarioSRV");
        resource = resource.path("ObtenerStockFisicoItems");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
