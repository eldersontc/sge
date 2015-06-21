package com.sge.modulos.compras.servicios;

import com.google.gson.Gson;
import com.sge.modulos.compras.entidades.Proveedor;
import com.sge.modulos.compras.negocios.ProveedorDTO;
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
@Path("ProveedorSRV")
public class ProveedorSRV {

    @Context
    private UriInfo context;

    public ProveedorSRV() {
    }

    @POST
    @Path("ObtenerProveedores/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProveedores(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ProveedorDTO proveedorDTO = new ProveedorDTO(idUsuario);
            List<Proveedor> lista = proveedorDTO.ObtenerProveedores(filtro);
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
    @Path("ObtenerProveedor/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProveedor(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idProveedor = new Gson().fromJson(json, int.class);
            ProveedorDTO proveedorDTO = new ProveedorDTO(idUsuario);
            Proveedor proveedor = proveedorDTO.ObtenerProveedor(idProveedor);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(proveedor));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarProveedor/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarProveedor(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Proveedor proveedor = new Gson().fromJson(json, Proveedor.class);
        try {
            ProveedorDTO proveedorDTO = new ProveedorDTO(idUsuario);
            proveedorDTO.RegistrarProveedor(proveedor);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarProveedor/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarProveedor(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Proveedor proveedor = new Gson().fromJson(json, Proveedor.class);
        try {
            ProveedorDTO proveedorDTO = new ProveedorDTO(idUsuario);
            proveedorDTO.ActualizarProveedor(proveedor);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarProveedor/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarProveedor(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idProveedor = new Gson().fromJson(json, int.class);
        try {
            ProveedorDTO proveedorDTO = new ProveedorDTO(idUsuario);
            proveedorDTO.EliminarProveedor(idProveedor);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
