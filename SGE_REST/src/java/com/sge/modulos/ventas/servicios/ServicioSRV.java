package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.Servicio;
import com.sge.modulos.ventas.negocios.ServicioDTO;
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
@Path("ServicioSRV")
public class ServicioSRV {
    
    @Context
    private UriInfo context;

    public ServicioSRV() {
    }

    @POST
    @Path("ObtenerServicios")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerServicios(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ServicioDTO ServicioDTO = new ServicioDTO();
            List<Object[]> lista = ServicioDTO.ObtenerServicios(filtro);
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
    @Path("ObtenerServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idServicio = new Gson().fromJson(json, int.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO();
            Servicio servicio = ServicioDTO.ObtenerServicio(idServicio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(servicio));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarServicio(String json) {
        List<String> resultado = new ArrayList<>();
        Servicio servicio = new Gson().fromJson(json, Servicio.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO();
            ServicioDTO.RegistrarServicio(servicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarServicio(String json) {
        List<String> resultado = new ArrayList<>();
        Servicio servicio = new Gson().fromJson(json, Servicio.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO();
            ServicioDTO.ActualizarServicio(servicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idServicio = new Gson().fromJson(json, int.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO();
            ServicioDTO.EliminarServicio(idServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerServicioUnidades")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerServicioUnidades(String json) {
        List<String> resultado = new ArrayList<>();
        String filtros = new Gson().fromJson(json, String.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO();
            List<Object[]> lista = ServicioDTO.ObtenerServicioUnidades(filtros);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
