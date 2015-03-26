package com.sge.modulos.inventarios.servicios;

import com.google.gson.Gson;
import com.sge.modulos.inventarios.entidades.Producto;
import com.sge.modulos.inventarios.entidades.ProductoAlmacen;
import com.sge.modulos.inventarios.negocios.ProductoDTO;
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
@Path("ProductoSRV")
public class ProductoSRV {
    
    @Context
    private UriInfo context;

    public ProductoSRV() {
    }

    @POST
    @Path("ObtenerProductos")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProductos(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            List<Object[]> lista = ProductoDTO.ObtenerProductos();
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idProducto = new Gson().fromJson(json, int.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            List<Object> lista = ProductoDTO.ObtenerProducto(idProducto);
            resultado.add(new Gson().toJson(true));
            for (Object item : lista) {
                resultado.add(new Gson().toJson(item));
            }
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarProducto(String json) {
        List<String> resultado = new ArrayList<>();
        String[] arrayJson = new Gson().fromJson(json, String[].class);
        Producto producto = new Gson().fromJson(arrayJson[0], Producto.class);
        ProductoAlmacen[] productoAlmacenes = new Gson().fromJson(arrayJson[1], ProductoAlmacen[].class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            ProductoDTO.RegistrarProducto(producto, productoAlmacenes);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarProducto(String json) {
        List<String> resultado = new ArrayList<>();
        String[] arrayJson = new Gson().fromJson(json, String[].class);
        Producto producto = new Gson().fromJson(arrayJson[0], Producto.class);
        ProductoAlmacen[] productoAlmacenes = new Gson().fromJson(arrayJson[1], ProductoAlmacen[].class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            ProductoDTO.ActualizarProducto(producto, productoAlmacenes);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idProducto = new Gson().fromJson(json, int.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            ProductoDTO.EliminarProducto(idProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
}
