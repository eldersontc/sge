package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Reporte;
import com.sge.modulos.administracion.negocios.ReporteDTO;
import java.util.ArrayList;
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
@Path("ReporteSRV")
public class ReporteSRV {
    
    @Context
    private UriInfo context;

    public ReporteSRV() {
    }

    @POST
    @Path("ObtenerReportes")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerReportes(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ReporteDTO reporteDTO = new ReporteDTO();
            List<Object[]> lista = reporteDTO.ObtenerReportes(filtro);
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
    @Path("ObtenerReporte")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerReporte(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idReporte = new Gson().fromJson(json, int.class);
            ReporteDTO reporteDTO = new ReporteDTO();
            Reporte reporte = reporteDTO.ObtenerReporte(idReporte);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(reporte));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarReporte")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarReporte(String json) {
        List<String> resultado = new ArrayList<>();
        Reporte reporte = new Gson().fromJson(json, Reporte.class);
        try {
            ReporteDTO reporteDTO = new ReporteDTO();
            reporteDTO.RegistrarReporte(reporte);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarReporte")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarReporte(String json) {
        List<String> resultado = new ArrayList<>();
        Reporte reporte = new Gson().fromJson(json, Reporte.class);
        try {
            ReporteDTO reporteDTO = new ReporteDTO();
            reporteDTO.ActualizarReporte(reporte);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarReporte")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarReporte(String json) {
        List<String> resultado = new ArrayList<>();
        int idReporte = new Gson().fromJson(json, int.class);
        try {
            ReporteDTO reporteDTO = new ReporteDTO();
            reporteDTO.EliminarReporte(idReporte);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
