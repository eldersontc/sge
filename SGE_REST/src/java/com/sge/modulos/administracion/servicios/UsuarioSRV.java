package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Usuario;
import com.sge.modulos.administracion.negocios.UsuarioDTO;
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
            String filtro = new Gson().fromJson(json, String.class);
            UsuarioDTO usuarioDTO = new UsuarioDTO(0);
            List<Usuario> lista = usuarioDTO.ObtenerUsuarios(filtro);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
            resultado.add(new Gson().toJson(new Date()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerUsuariosConMensajesSinLeer")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUsuariosConMensajesSinLeer(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idUsuario = new Gson().fromJson(json, int.class);
            UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
            List<Usuario> lista = usuarioDTO.ObtenerUsuariosConMensajesSinLeer(idUsuario);
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
    @Path("RegistrarUsuario/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarUsuario(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Usuario usuario = new Gson().fromJson(json, Usuario.class);
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
            usuarioDTO.RegistrarUsuario(usuario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarUsuario/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarUsuario(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Usuario usuario = new Gson().fromJson(json, Usuario.class);
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
            usuarioDTO.ActualizarUsuario(usuario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarUsuario/{idUsuarioPath}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarUsuario(@PathParam("idUsuarioPath") int idUsuarioPath, String json) {
        List<String> resultado = new ArrayList<>();
        int idUsuario = new Gson().fromJson(json, int.class);
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuarioPath);
            usuarioDTO.EliminarUsuario(idUsuario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ConectarUsuario")
    @Consumes("application/json")
    @Produces("application/json")
    public String ConectarUsuario(String json) {
        List<String> resultado = new ArrayList<>();
        Usuario usuario = new Gson().fromJson(json, Usuario.class);
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getIdUsuario());
            usuarioDTO.ConectarUsuario(usuario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("DesconectarUsuario")
    @Consumes("application/json")
    @Produces("application/json")
    public String DesconectarUsuario(String json) {
        List<String> resultado = new ArrayList<>();
        int idUsuario = new Gson().fromJson(json, int.class);
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(idUsuario);
            usuarioDTO.DesconectarUsuario(idUsuario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
