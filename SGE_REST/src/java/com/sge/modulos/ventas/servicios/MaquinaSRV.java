package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.Maquina;
import com.sge.modulos.ventas.negocios.MaquinaDTO;
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
@Path("MaquinaSRV")
public class MaquinaSRV {

    @Context
    private UriInfo context;

    public MaquinaSRV() {
    }

    @POST
    @Path("ObtenerMaquinas/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMaquinas(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            MaquinaDTO MaquinaDTO = new MaquinaDTO(idUsuario);
            List<Maquina> lista = MaquinaDTO.ObtenerMaquinas(filtro);
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
    @Path("ObtenerMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idMaquina = new Gson().fromJson(json, int.class);
        try {
            MaquinaDTO MaquinaDTO = new MaquinaDTO(idUsuario);
            Maquina maquina = MaquinaDTO.ObtenerMaquina(idMaquina);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(maquina));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Maquina maquina = new Gson().fromJson(json, Maquina.class);
        try {
            MaquinaDTO MaquinaDTO = new MaquinaDTO(idUsuario);
            MaquinaDTO.RegistrarMaquina(maquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Maquina maquina = new Gson().fromJson(json, Maquina.class);
        try {
            MaquinaDTO MaquinaDTO = new MaquinaDTO(idUsuario);
            MaquinaDTO.ActualizarMaquina(maquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idMaquina = new Gson().fromJson(json, int.class);
        try {
            MaquinaDTO MaquinaDTO = new MaquinaDTO(idUsuario);
            MaquinaDTO.EliminarMaquina(idMaquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
