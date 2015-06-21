package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.ValorDefinido;
import com.sge.modulos.administracion.negocios.ValorDefinidoDTO;
import com.sge.modulos.ventas.entidades.Cotizacion;
import com.sge.modulos.ventas.entidades.Presupuesto;
import com.sge.modulos.ventas.negocios.CotizacionDTO;
import com.sge.modulos.ventas.negocios.PresupuestoDTO;
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
@Path("PresupuestoSRV")
public class PresupuestoSRV {

    @Context
    private UriInfo context;

    public PresupuestoSRV() {
    }

    @POST
    @Path("ObtenerPresupuestos/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPresupuestos(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO(idUsuario);
            List<Presupuesto> lista = PresupuestoDTO.ObtenerPresupuestos(filtro);
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
    @Path("ObtenerPresupuesto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPresupuesto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO(idUsuario);
            Presupuesto presupuesto = PresupuestoDTO.ObtenerPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(presupuesto));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarPresupuesto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarPresupuesto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Presupuesto presupuesto = new Gson().fromJson(json, Presupuesto.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO(idUsuario);
            PresupuestoDTO.RegistrarPresupuesto(presupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarPresupuesto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarPresupuesto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Presupuesto presupuesto = new Gson().fromJson(json, Presupuesto.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO(idUsuario);
            PresupuestoDTO.ActualizarPresupuesto(presupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarPresupuesto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarPresupuesto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO(idUsuario);
            PresupuestoDTO.EliminarPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("CambiarEstadoPresupuesto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String CambiarEstadoPresupuesto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int[] ids = new Gson().fromJson(json, int[].class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO(idUsuario);
            PresupuestoDTO.CambiarEstadoPresupuesto(ids[0], ids[1]);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("GenerarOrdenTrabajo/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String GenerarOrdenTrabajo(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int[] ids = new Gson().fromJson(json, int[].class);
        try {
            CotizacionDTO cotizacionDTO = new CotizacionDTO(idUsuario);
            List<Cotizacion> cotizaciones = cotizacionDTO.ObtenerCotizacionesPorPresupuesto(ids[0]);
            ValorDefinidoDTO valorDefinidoDTO = new ValorDefinidoDTO(idUsuario);
            ValorDefinido valorDefinido = valorDefinidoDTO.ObtenerValorDefinidoPorUsuarioYEntidad(ids[1], 6);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(new Date()));
            resultado.add(new Gson().toJson(cotizaciones));
            resultado.add(valorDefinido == null ? "" : valorDefinido.getJson());
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
