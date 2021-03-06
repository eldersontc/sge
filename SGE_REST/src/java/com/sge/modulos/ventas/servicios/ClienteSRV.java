package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.Cliente;
import com.sge.modulos.ventas.entidades.ContactoCliente;
import com.sge.modulos.ventas.entidades.DireccionCliente;
import com.sge.modulos.ventas.negocios.ClienteDTO;
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
@Path("ClienteSRV")
public class ClienteSRV {

    @Context
    private UriInfo context;

    public ClienteSRV() {
    }

    @POST
    @Path("ObtenerClientes/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerClientes(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ClienteDTO ClienteDTO = new ClienteDTO(idUsuario);
            List<Cliente> lista = ClienteDTO.ObtenerClientes(filtro);
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
    @Path("ObtenerCliente/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerCliente(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idCliente = new Gson().fromJson(json, int.class);
        try {
            ClienteDTO ClienteDTO = new ClienteDTO(idUsuario);
            Cliente cliente = ClienteDTO.ObtenerCliente(idCliente);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(cliente));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarCliente/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarCliente(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Cliente cliente = new Gson().fromJson(json, Cliente.class);
        try {
            ClienteDTO ClienteDTO = new ClienteDTO(idUsuario);
            ClienteDTO.RegistrarCliente(cliente);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarCliente/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarCliente(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Cliente cliente = new Gson().fromJson(json, Cliente.class);
        try {
            ClienteDTO ClienteDTO = new ClienteDTO(idUsuario);
            ClienteDTO.ActualizarCliente(cliente);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarCliente/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarCliente(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idCliente = new Gson().fromJson(json, int.class);
        try {
            ClienteDTO ClienteDTO = new ClienteDTO(idUsuario);
            ClienteDTO.EliminarCliente(idCliente);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerContactosCliente/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerContactosCliente(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ClienteDTO ClienteDTO = new ClienteDTO(idUsuario);
            List<ContactoCliente> lista = ClienteDTO.ObtenerContactosCliente(filtro);
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
    @Path("ObtenerDireccionesCliente/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerDireccionesCliente(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ClienteDTO ClienteDTO = new ClienteDTO(idUsuario);
            List<DireccionCliente> lista = ClienteDTO.ObtenerDireccionesCliente(filtro);
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
