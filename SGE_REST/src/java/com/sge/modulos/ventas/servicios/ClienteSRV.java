package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.Cliente;
import com.sge.modulos.ventas.negocios.ClienteDTO;
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
@Path("ClienteSRV")
public class ClienteSRV {
    
    @Context
    private UriInfo context;

    public ClienteSRV() {
    }

    @POST
    @Path("ObtenerClientes")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerClientes(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ClienteDTO ClienteDTO = new ClienteDTO();
            List<Object[]> lista = ClienteDTO.ObtenerClientes(filtro);
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
    @Path("ObtenerCliente")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerCliente(String json) {
        List<String> resultado = new ArrayList<>();
        int idCliente = new Gson().fromJson(json, int.class);
        try {
            ClienteDTO ClienteDTO = new ClienteDTO();
            Cliente cliente = ClienteDTO.ObtenerCliente(idCliente);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(cliente));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarCliente")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarCliente(String json) {
        List<String> resultado = new ArrayList<>();
        Cliente cliente = new Gson().fromJson(json, Cliente.class);
        try {
            ClienteDTO ClienteDTO = new ClienteDTO();
            ClienteDTO.RegistrarCliente(cliente);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarCliente")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarCliente(String json) {
        List<String> resultado = new ArrayList<>();
        Cliente cliente = new Gson().fromJson(json, Cliente.class);
        try {
            ClienteDTO ClienteDTO = new ClienteDTO();
            ClienteDTO.ActualizarCliente(cliente);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarCliente")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarCliente(String json) {
        List<String> resultado = new ArrayList<>();
        int idCliente = new Gson().fromJson(json, int.class);
        try {
            ClienteDTO ClienteDTO = new ClienteDTO();
            ClienteDTO.EliminarCliente(idCliente);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
