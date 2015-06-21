package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.Servicio;
import com.sge.modulos.ventas.entidades.ServicioUnidad;
import com.sge.modulos.ventas.negocios.ServicioDTO;
import java.util.ArrayList;
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
@Path("ServicioSRV")
public class ServicioSRV {

    @Context
    private UriInfo context;

    public ServicioSRV() {
    }

    @POST
    @Path("ObtenerServicios/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerServicios(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ServicioDTO ServicioDTO = new ServicioDTO(idUsuario);
            List<Servicio> lista = ServicioDTO.ObtenerServicios(filtro);
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
    @Path("ObtenerServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idServicio = new Gson().fromJson(json, int.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO(idUsuario);
            Servicio servicio = ServicioDTO.ObtenerServicio(idServicio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(servicio));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Servicio servicio = new Gson().fromJson(json, Servicio.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO(idUsuario);
            ServicioDTO.RegistrarServicio(servicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Servicio servicio = new Gson().fromJson(json, Servicio.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO(idUsuario);
            ServicioDTO.ActualizarServicio(servicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idServicio = new Gson().fromJson(json, int.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO(idUsuario);
            ServicioDTO.EliminarServicio(idServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerServicioUnidades/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerServicioUnidades(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        String filtros = new Gson().fromJson(json, String.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO(idUsuario);
            List<ServicioUnidad> lista = ServicioDTO.ObtenerServicioUnidades(filtros);
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
    @Path("ObtenerUnidadesPorServicios/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidadesPorServicios(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        String[] arrayJson = new Gson().fromJson(json, String[].class);
        Servicio[] servicios = new Gson().fromJson(arrayJson[0], Servicio[].class);
        int idListaPrecioServicio = new Gson().fromJson(arrayJson[1], int.class);
        try {
            ServicioDTO ServicioDTO = new ServicioDTO(idUsuario);
            List<Servicio> lista = ServicioDTO.ObtenerUnidadesPorServicios(servicios, idListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
