package com.sge.modulos.inventarios.servicios;

import com.google.gson.Gson;
import com.sge.modulos.inventarios.entidades.Producto;
import com.sge.modulos.inventarios.entidades.ProductoAlmacen;
import com.sge.modulos.inventarios.entidades.ProductoUnidad;
import com.sge.modulos.inventarios.entidades.SeleccionProducto;
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
            String filtro = new Gson().fromJson(json, String.class);
            ProductoDTO ProductoDTO = new ProductoDTO();
            List<Producto> lista = ProductoDTO.ObtenerProductos(filtro);
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
    @Path("ObtenerProductosPorAlmacen")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProductosPorAlmacen(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ProductoDTO ProductoDTO = new ProductoDTO();
            List<SeleccionProducto> lista = ProductoDTO.ObtenerProductosPorAlmacen(filtro);
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
    @Path("ObtenerProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idProducto = new Gson().fromJson(json, int.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            Producto producto = ProductoDTO.ObtenerProducto(idProducto);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(producto));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarProducto(String json) {
        List<String> resultado = new ArrayList<>();
        Producto producto = new Gson().fromJson(json, Producto.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            ProductoDTO.RegistrarProducto(producto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarProducto(String json) {
        List<String> resultado = new ArrayList<>();
        Producto producto = new Gson().fromJson(json, Producto.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            ProductoDTO.ActualizarProducto(producto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
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
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerUnidadesPorProductos")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidadesPorProductos(String json) {
        List<String> resultado = new ArrayList<>();
        Producto[] productos = new Gson().fromJson(json, Producto[].class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO();
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(ProductoDTO.ObtenerUnidadesPorProductos(productos)));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerProductoUnidades")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProductoUnidades(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ProductoDTO ProductoDTO = new ProductoDTO();
            List<ProductoUnidad> lista = ProductoDTO.ObtenerProductoUnidades(filtro);
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
    @Path("ObtenerProductoAlmacenes")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProductoAlmacenes(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ProductoDTO ProductoDTO = new ProductoDTO();
            List<ProductoAlmacen> lista = ProductoDTO.ObtenerProductoAlmacenes(filtro);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
