package com.sge.modulos.administracion.cliente;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST<br>
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
    
    ////////////////////////////// MONEDA //////////////////////////////////////
    
    public String ObtenerMonedas(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MonedaSRV");
        resource = resource.path("ObtenerMonedas");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String RegistrarMoneda(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MonedaSRV");
        resource = resource.path("RegistrarMoneda");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ActualizarMoneda(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MonedaSRV");
        resource = resource.path("ActualizarMoneda");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String EliminarMoneda(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MonedaSRV");
        resource = resource.path("EliminarMoneda");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////// REPORTE /////////////////////////////////
    
    public String ObtenerReportes(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("ObtenerReportes");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ObtenerReporte(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("ObtenerReporte");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String RegistrarReporte(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("RegistrarReporte");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ActualizarReporte(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("ActualizarReporte");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String EliminarReporte(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("EliminarReporte");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String GenerarReporteConEntidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("GenerarReporteConEntidad");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String GenerarReporteSinEntidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("GenerarReporteSinEntidad");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////// VALOR DEFINIDO ////////////////////////////////
    
    public String ObtenerValoresDefinidos(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("ObtenerValoresDefinidos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ObtenerValorDefinido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("ObtenerValorDefinido");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String RegistrarValorDefinido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("RegistrarValorDefinido");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ActualizarValorDefinido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("ActualizarValorDefinido");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String EliminarValorDefinido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("EliminarValorDefinido");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////// ENTIDAD /////////////////////////////////
    
    public String ObtenerEntidades(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntidadSRV");
        resource = resource.path("ObtenerEntidades");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////// NUMERACION /////////////////////////////////
    
    public String ObtenerNumeraciones(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("ObtenerNumeraciones");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ObtenerNumeracion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("ObtenerNumeracion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String RegistrarNumeracion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("RegistrarNumeracion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String ActualizarNumeracion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("ActualizarNumeracion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    public String EliminarNumeracion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("EliminarNumeracion");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    public void close() {
        client.close();
    }
}
