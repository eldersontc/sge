package com.sge.modulos.administracion.cliente;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:MenuSRV [MenuSRV]<br>
 * USAGE:
 * <pre>
 *        cliAdministracion client = new cliAdministracion();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author elderson
 */
public class cliAdministracion {
    
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/SGE_REST//Servicios";

    public cliAdministracion() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
    }

    ////////////////////////////// MENU ////////////////////////////////////////
    
    public String ObtenerMenus(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MenuSRV");
        resource = resource.path("ObtenerMenus");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    ///////////////////////////// USUARIO //////////////////////////////////////
    
    public String ObtenerUsuarios(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("ObtenerUsuarios");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String RegistrarUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("RegistrarUsuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ActualizarUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("ActualizarUsuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String EliminarUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("EliminarUsuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// EMPLEADO ////////////////////////////////
    
    public String ObtenerEmpleados(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("ObtenerEmpleados");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ObtenerEmpleado(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("ObtenerEmpleado");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String RegistrarEmpleado(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("RegistrarEmpleado");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ActualizarEmpleado(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("ActualizarEmpleado");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String EliminarEmpleado(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("EliminarEmpleado");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public void close() {
        client.close();
    }
}
