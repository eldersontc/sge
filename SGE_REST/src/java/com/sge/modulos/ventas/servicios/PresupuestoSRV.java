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
    @Path("ObtenerPresupuestos")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPresupuestos(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            List<Presupuesto> lista = PresupuestoDTO.ObtenerPresupuestos(filtro);
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
    @Path("ObtenerPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            Presupuesto presupuesto = PresupuestoDTO.ObtenerPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(presupuesto));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        Presupuesto presupuesto = new Gson().fromJson(json, Presupuesto.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.RegistrarPresupuesto(presupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        Presupuesto presupuesto = new Gson().fromJson(json, Presupuesto.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.ActualizarPresupuesto(presupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.EliminarPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("AprobarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String AprobarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.AprobarPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("DesaprobarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String DesaprobarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.DesaprobarPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EnviarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String EnviarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.EnviarPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("AceptarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String AceptarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.AceptarPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RechazarPresupuesto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RechazarPresupuesto(String json) {
        List<String> resultado = new ArrayList<>();
        int idPresupuesto = new Gson().fromJson(json, int.class);
        try {
            PresupuestoDTO PresupuestoDTO = new PresupuestoDTO();
            PresupuestoDTO.RechazarPresupuesto(idPresupuesto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("GenerarOrdenTrabajo")
    @Consumes("application/json")
    @Produces("application/json")
    public String GenerarOrdenTrabajo(String json) {
        List<String> resultado = new ArrayList<>();
        int[] ids = new Gson().fromJson(json, int[].class);
        try {
            CotizacionDTO cotizacionDTO = new CotizacionDTO();
            List<Cotizacion> cotizaciones = cotizacionDTO.ObtenerCotizacionesPorPresupuesto(ids[0]);
            ValorDefinidoDTO valorDefinidoDTO = new ValorDefinidoDTO();
            ValorDefinido valorDefinido = valorDefinidoDTO.ObtenerValorDefinidoPorUsuarioYEntidad(ids[1], 6);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(new Date()));
            resultado.add(new Gson().toJson(cotizaciones));
            resultado.add(valorDefinido == null ? "" : valorDefinido.getJson());
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
