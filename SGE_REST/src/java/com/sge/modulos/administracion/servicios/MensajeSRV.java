package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Mensaje;
import com.sge.modulos.administracion.negocios.MensajeDTO;
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
@Path("MensajeSRV")
public class MensajeSRV {

    @Context
    private UriInfo context;

    public MensajeSRV() {
    }

    @POST
    @Path("ObtenerMensajesPorUsuarioOrigenYDestino/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMensajesPorUsuarioOrigenYDestino(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            Mensaje mensaje = new Gson().fromJson(json, Mensaje.class);
            MensajeDTO mensajeDTO = new MensajeDTO(idUsuario);
            List<Mensaje> lista = mensajeDTO.ObtenerMensajesPorUsuarioOrigenYDestino(mensaje.getIdUsuarioOrigen(), mensaje.getIdUsuarioDestino());
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
    @Path("RegistrarMensaje/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarMensaje(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Mensaje mensaje = new Gson().fromJson(json, Mensaje.class);
        try {
            MensajeDTO mensajeDTO = new MensajeDTO(idUsuario);
            mensajeDTO.RegistrarMensaje(mensaje);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(mensaje.getFechaEnvio()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("CambiarALeido/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String CambiarALeido(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Mensaje mensaje = new Gson().fromJson(json, Mensaje.class);
        try {
            MensajeDTO mensajeDTO = new MensajeDTO(idUsuario);
            mensajeDTO.CambiarALeido(mensaje.getIdUsuarioOrigen(), mensaje.getIdUsuarioDestino());
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
