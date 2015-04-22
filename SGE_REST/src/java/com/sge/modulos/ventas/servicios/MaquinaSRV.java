package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.Maquina;
import com.sge.modulos.ventas.negocios.MaquinaDTO;
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
@Path("MaquinaSRV")
public class MaquinaSRV {
    
    @Context
    private UriInfo context;

    public MaquinaSRV() {
    }

    @POST
    @Path("ObtenerMaquinas")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMaquinas(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            MaquinaDTO MaquinaDTO = new MaquinaDTO();
            List<Object[]> lista = MaquinaDTO.ObtenerMaquinas(filtro);
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
    @Path("ObtenerMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        int idMaquina = new Gson().fromJson(json, int.class);
        try {
            MaquinaDTO MaquinaDTO = new MaquinaDTO();
            Maquina maquina = MaquinaDTO.ObtenerMaquina(idMaquina);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(maquina));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        Maquina maquina = new Gson().fromJson(json, Maquina.class);
        try {
            MaquinaDTO MaquinaDTO = new MaquinaDTO();
            MaquinaDTO.RegistrarMaquina(maquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        Maquina maquina = new Gson().fromJson(json, Maquina.class);
        try {
            MaquinaDTO MaquinaDTO = new MaquinaDTO();
            MaquinaDTO.ActualizarMaquina(maquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        int idMaquina = new Gson().fromJson(json, int.class);
        try {
            MaquinaDTO MaquinaDTO = new MaquinaDTO();
            MaquinaDTO.EliminarMaquina(idMaquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
