package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Usuario;
import com.sge.modulos.administracion.negocios.UsuarioDTO;
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
@Path("UsuarioSRV")
public class UsuarioSRV {
    
    
    @Context
    private UriInfo context;

    public UsuarioSRV() {
    }

    @POST
    @Path("ObtenerUsuarios")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUsuarios(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            List<Object[]> lista = usuarioDTO.ObtenerUsuarios();
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
    @Path("RegistrarUsuario")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarUsuario(String json) {
        List<String> resultado = new ArrayList<>();
        Usuario usuario = new Gson().fromJson(json, Usuario.class);
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.RegistrarUsuario(usuario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarUsuario")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarUsuario(String json) {
        List<String> resultado = new ArrayList<>();
        Usuario usuario = new Gson().fromJson(json, Usuario.class);
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.ActualizarUsuario(usuario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarUsuario")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarUsuario(String json) {
        List<String> resultado = new ArrayList<>();
        int idUsuario = new Gson().fromJson(json, int.class);
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.EliminarUsuario(idUsuario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
