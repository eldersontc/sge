package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Mensaje;
import com.sge.modulos.administracion.negocios.MensajeDTO;
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
@Path("MensajeSRV")
public class MensajeSRV {
    
    @Context
    private UriInfo context;

    public MensajeSRV() {
    }
    
    @POST
    @Path("ObtenerMensajesPorUsuarioOrigenYDestino")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerMensajesPorUsuarioOrigenYDestino(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            Mensaje mensaje = new Gson().fromJson(json, Mensaje.class);
            MensajeDTO mensajeDTO = new MensajeDTO();
            List<Mensaje> lista = mensajeDTO.ObtenerMensajesPorUsuarioOrigenYDestino(mensaje.getIdUsuarioOrigen(), mensaje.getIdUsuarioDestino());
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
    @Path("RegistrarMensaje")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarMensaje(String json) {
        List<String> resultado = new ArrayList<>();
        Mensaje mensaje = new Gson().fromJson(json, Mensaje.class);
        try {
            MensajeDTO mensajeDTO = new MensajeDTO();
            mensajeDTO.RegistrarMensaje(mensaje);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(mensaje.getFechaEnvio()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("CambiarALeido")
    @Consumes("application/json")
    @Produces("application/json")
    public String CambiarALeido(String json) {
        List<String> resultado = new ArrayList<>();
        Mensaje mensaje = new Gson().fromJson(json, Mensaje.class);
        try {
            MensajeDTO mensajeDTO = new MensajeDTO();
            mensajeDTO.CambiarALeido(mensaje.getIdUsuarioOrigen(), mensaje.getIdUsuarioDestino());
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
