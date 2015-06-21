package com.sge.modulos.inventarios.servicios;

import com.google.gson.Gson;
import com.sge.modulos.inventarios.entidades.Almacen;
import com.sge.modulos.inventarios.negocios.AlmacenDTO;
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
@Path("AlmacenSRV")
public class AlmacenSRV {

    @Context
    private UriInfo context;

    public AlmacenSRV() {
    }

    @POST
    @Path("ObtenerAlmacenes/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerAlmacenes(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            AlmacenDTO AlmacenDTO = new AlmacenDTO(idUsuario);
            List<Almacen> lista = AlmacenDTO.ObtenerAlmacenes(filtro);
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
    @Path("RegistrarAlmacen/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarAlmacen(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Almacen almacen = new Gson().fromJson(json, Almacen.class);
        try {
            AlmacenDTO AlmacenDTO = new AlmacenDTO(idUsuario);
            AlmacenDTO.RegistrarAlmacen(almacen);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarAlmacen/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarAlmacen(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Almacen almacen = new Gson().fromJson(json, Almacen.class);
        try {
            AlmacenDTO AlmacenDTO = new AlmacenDTO(idUsuario);
            AlmacenDTO.ActualizarAlmacen(almacen);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarAlmacen/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarAlmacen(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idAlmacen = new Gson().fromJson(json, int.class);
        try {
            AlmacenDTO AlmacenDTO = new AlmacenDTO(idUsuario);
            AlmacenDTO.EliminarAlmacen(idAlmacen);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
