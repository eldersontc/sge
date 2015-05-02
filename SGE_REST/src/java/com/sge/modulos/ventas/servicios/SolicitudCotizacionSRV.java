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
    @Path("ObtenerSolicitudesCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSolicitudesCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO();
            List<Object[]> lista = SolicitudCotizacionDTO.ObtenerSolicitudesCotizacion(filtro);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        int idSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO();
            SolicitudCotizacion solicitudCotizacion = SolicitudCotizacionDTO.ObtenerSolicitudCotizacion(idSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(solicitudCotizacion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        SolicitudCotizacion solicitudCotizacion = new Gson().fromJson(json, SolicitudCotizacion.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO();
            SolicitudCotizacionDTO.RegistrarSolicitudCotizacion(solicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        SolicitudCotizacion solicitudCotizacion = new Gson().fromJson(json, SolicitudCotizacion.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO();
            SolicitudCotizacionDTO.ActualizarSolicitudCotizacion(solicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarSolicitudCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarSolicitudCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        int idSolicitudCotizacion = new Gson().fromJson(json, int.class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO();
            SolicitudCotizacionDTO.EliminarSolicitudCotizacion(idSolicitudCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("GenerarCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String GenerarCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        int[] ids = new Gson().fromJson(json, int[].class);
        try {
            SolicitudCotizacionDTO SolicitudCotizacionDTO = new SolicitudCotizacionDTO();
            SolicitudCotizacion solicitudCotizacion = SolicitudCotizacionDTO.ObtenerSolicitudCotizacion(ids[0]);
            ValorDefinidoDTO valorDefinidoDTO = new ValorDefinidoDTO();
            ValorDefinido valorDefinido = valorDefinidoDTO.ObtenerValorDefinidoPorUsuarioYEntidad(ids[1], 4);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(new Date()));
            resultado.add(new Gson().toJson(solicitudCotizacion));
            resultado.add(valorDefinido == null ? "" : valorDefinido.getJson());
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
