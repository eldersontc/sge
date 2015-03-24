package com.sge.modulos.compras.servicios;

import com.google.gson.Gson;
import com.sge.modulos.compras.entidades.Proveedor;
import com.sge.modulos.compras.negocios.ProveedorDTO;
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
@Path("ProveedorSRV")
public class ProveedorSRV {
    
    @Context
    private UriInfo context;

    public ProveedorSRV() {
    }

    @POST
    @Path("ObtenerProveedores")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProveedores(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            ProveedorDTO proveedorDTO = new ProveedorDTO();
            List<Object[]> lista = proveedorDTO.ObtenerProveedores();
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerProveedor")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProveedor(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idProveedor = new Gson().fromJson(json, int.class);
            ProveedorDTO proveedorDTO = new ProveedorDTO();
            Proveedor proveedor = proveedorDTO.ObtenerProveedor(idProveedor);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(proveedor));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarProveedor")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarProveedor(String json) {
        List<String> resultado = new ArrayList<>();
        Proveedor proveedor = new Gson().fromJson(json, Proveedor.class);
        try {
            ProveedorDTO proveedorDTO = new ProveedorDTO();
            proveedorDTO.RegistrarProveedor(proveedor);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarProveedor")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarProveedor(String json) {
        List<String> resultado = new ArrayList<>();
        Proveedor proveedor = new Gson().fromJson(json, Proveedor.class);
        try {
            ProveedorDTO proveedorDTO = new ProveedorDTO();
            proveedorDTO.ActualizarProveedor(proveedor);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarProveedor")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarProveedor(String json) {
        List<String> resultado = new ArrayList<>();
        int idProveedor = new Gson().fromJson(json, int.class);
        try {
            ProveedorDTO proveedorDTO = new ProveedorDTO();
            proveedorDTO.EliminarProveedor(idProveedor);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
}
