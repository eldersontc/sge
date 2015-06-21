package com.sge.modulos.administracion.cliente;

import com.sge.base.cliente.cliBase;
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
public class cliAdministracion extends cliBase {

    private Client client;
    private String BASE_URI;

    public cliAdministracion() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        BASE_URI = "http://" + getIpServidor() + "/SGE_REST//Servicios";
    }

    ////////////////////////////// MENU ////////////////////////////////////////
    public String ObtenerMenusPorUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MenuSRV");
        resource = resource.path("ObtenerMenusPorUsuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerMenusPorPerfil(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MenuSRV");
        resource = resource.path("ObtenerMenusPorPerfil" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarPermisos(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MenuSRV");
        resource = resource.path("ActualizarPermisos" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// USUARIO //////////////////////////////////////
    public String ObtenerUsuarios(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("ObtenerUsuarios");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerUsuariosConMensajesSinLeer(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("ObtenerUsuariosConMensajesSinLeer");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("RegistrarUsuario" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("ActualizarUsuario" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("EliminarUsuario" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ConectarUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("ConectarUsuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String DesconectarUsuario(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("UsuarioSRV");
        resource = resource.path("DesconectarUsuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// PERFIL //////////////////////////////////////
    public String ObtenerPerfiles(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PerfilSRV");
        resource = resource.path("ObtenerPerfiles" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarPerfil(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PerfilSRV");
        resource = resource.path("RegistrarPerfil" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarPerfil(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PerfilSRV");
        resource = resource.path("ActualizarPerfil" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarPerfil(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("PerfilSRV");
        resource = resource.path("EliminarPerfil" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// EMPLEADO ////////////////////////////////
    public String ObtenerEmpleados(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("ObtenerEmpleados" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEmpleado(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("ObtenerEmpleado" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarEmpleado(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("RegistrarEmpleado" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarEmpleado(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("ActualizarEmpleado" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarEmpleado(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EmpleadoSRV");
        resource = resource.path("EliminarEmpleado" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// MONEDA //////////////////////////////////////
    public String ObtenerMonedas(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MonedaSRV");
        resource = resource.path("ObtenerMonedas" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarMoneda(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MonedaSRV");
        resource = resource.path("RegistrarMoneda" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarMoneda(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MonedaSRV");
        resource = resource.path("ActualizarMoneda" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarMoneda(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MonedaSRV");
        resource = resource.path("EliminarMoneda" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// REPORTE /////////////////////////////////
    public String ObtenerReportes(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("ObtenerReportes" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerReporte(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("ObtenerReporte" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarReporte(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("RegistrarReporte" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarReporte(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("ActualizarReporte" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarReporte(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("EliminarReporte" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String GenerarReporteConEntidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("GenerarReporteConEntidad" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String GenerarReporteSinEntidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ReporteSRV");
        resource = resource.path("GenerarReporteSinEntidad" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////// VALOR DEFINIDO ////////////////////////////////
    public String ObtenerValoresDefinidos(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("ObtenerValoresDefinidos" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerValorDefinido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("ObtenerValorDefinido" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarValorDefinido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("RegistrarValorDefinido" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarValorDefinido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("ActualizarValorDefinido" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarValorDefinido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("EliminarValorDefinido" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerValorDefinidoPorUsuarioYEntidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ValorDefinidoSRV");
        resource = resource.path("ObtenerValorDefinidoPorUsuarioYEntidad" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// ENTIDAD /////////////////////////////////
    public String ObtenerEntidades(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntidadSRV");
        resource = resource.path("ObtenerEntidades" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerEntidad(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("EntidadSRV");
        resource = resource.path("ObtenerEntidad" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// NUMERACION /////////////////////////////////
    public String ObtenerNumeraciones(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("ObtenerNumeraciones" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ObtenerNumeracion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("ObtenerNumeracion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarNumeracion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("RegistrarNumeracion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ActualizarNumeracion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("ActualizarNumeracion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String EliminarNumeracion(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("NumeracionSRV");
        resource = resource.path("EliminarNumeracion" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////// DEPARTAMENTO ////////////////////////////////
    public String ObtenerDepartamentos(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("DepartamentoSRV");
        resource = resource.path("ObtenerDepartamentos" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    //////////////////////////////// PROVINCIA /////////////////////////////////
    public String ObtenerProvincias(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("ProvinciaSRV");
        resource = resource.path("ObtenerProvincias" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// DISTRITO /////////////////////////////////
    public String ObtenerDistritos(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("DistritoSRV");
        resource = resource.path("ObtenerDistritos" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// MENSAJE //////////////////////////////////////
    public String ObtenerMensajesPorUsuarioOrigenYDestino(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MensajeSRV");
        resource = resource.path("ObtenerMensajesPorUsuarioOrigenYDestino" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String RegistrarMensaje(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MensajeSRV");
        resource = resource.path("RegistrarMensaje" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String CambiarALeido(Object requestEntity) throws ClientErrorException {
        WebTarget resource = client.target(BASE_URI).path("MensajeSRV");
        resource = resource.path("CambiarALeido" + getPathIdUsuario());
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void close() {
        client.close();
    }
}
