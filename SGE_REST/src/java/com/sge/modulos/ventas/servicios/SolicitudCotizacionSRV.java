package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.ValorDefinido;
import com.sge.modulos.administracion.negocios.ValorDefinidoDTO;
import com.sge.modulos.ventas.entidades.SolicitudCotizacion;
import com.sge.modulos.ventas.negocios.SolicitudCotizacionDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author elderson
 */
@Path("SolicitudCotizacionSRV")
public class SolicitudCotizacionSRV {

    @Context
    private UriInfo context;

    public SolicitudCotizacionSRV() {
    }

    @POST
    @Path("ObtenerSolicitudesCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSolicitudesCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO(idUsuario);
            List<SolicitudCotizacion> lista = SolicitudCotizacionDTO.ObtenerSolicitudesCotizacion(filtro);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO(idUsuario);
            SolicitudCotizacion solicitudCotizacion = SolicitudCotizacionDTO.ObtenerSolicitudCotizacion(idSolicitudCotizacion, false);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(solicitudCotizacion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        SolicitudCotizacion solicitudCotizacion = new Gson().fromJson(json, SolicitudCotizacion.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO(idUsuario);
            SolicitudCotizacionDTO.RegistrarSolicitudCotizacion(solicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        SolicitudCotizacion solicitudCotizacion = new Gson().fromJson(json, SolicitudCotizacion.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO(idUsuario);
            SolicitudCotizacionDTO.ActualizarSolicitudCotizacion(solicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO(idUsuario);
            SolicitudCotizacionDTO.EliminarSolicitudCotizacion(idSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("AprobarSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String AprobarSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO(idUsuario);
            SolicitudCotizacionDTO.AprobarSolicitudCotizacion(idSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("DesaprobarSolicitudCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String DesaprobarSolicitudCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO(idUsuario);
            SolicitudCotizacionDTO.DesaprobarSolicitudCotizacion(idSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("GenerarCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String GenerarCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int[] ids = new Gson().fromJson(json, int[].class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO(idUsuario);
            SolicitudCotizacion solicitudCotizacion = SolicitudCotizacionDTO.ObtenerSolicitudCotizacion(ids[0], true);
            ValorDefinidoDTO valorDefinidoDTO = new ValorDefinidoDTO(idUsuario);
            ValorDefinido valorDefinido = valorDefinidoDTO.ObtenerValorDefinidoPorUsuarioYEntidad(ids[1], 4);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(new Date()));
            resultado.add(new Gson().toJson(solicitudCotizacion));
            resultado.add(valorDefinido == null ? "" : valorDefinido.getJson());
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
