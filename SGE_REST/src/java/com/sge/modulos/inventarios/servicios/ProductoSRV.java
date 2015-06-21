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
import javax.ws.rs.PathParam;
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
    @Path("ObtenerProductos/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProductos(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            List<Producto> lista = ProductoDTO.ObtenerProductos(filtro);
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
    @Path("ObtenerProductosPorAlmacen/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProductosPorAlmacen(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            List<SeleccionProducto> lista = ProductoDTO.ObtenerProductosPorAlmacen(filtro);
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
    @Path("ObtenerProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idProducto = new Gson().fromJson(json, int.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            Producto producto = ProductoDTO.ObtenerProducto(idProducto);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(producto));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Producto producto = new Gson().fromJson(json, Producto.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            ProductoDTO.RegistrarProducto(producto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Producto producto = new Gson().fromJson(json, Producto.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            ProductoDTO.ActualizarProducto(producto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idProducto = new Gson().fromJson(json, int.class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            ProductoDTO.EliminarProducto(idProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerUnidadesPorProductos/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidadesPorProductos(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Producto[] productos = new Gson().fromJson(json, Producto[].class);
        try {
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(ProductoDTO.ObtenerUnidadesPorProductos(productos)));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerProductoUnidades/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProductoUnidades(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            List<ProductoUnidad> lista = ProductoDTO.ObtenerProductoUnidades(filtro);
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
    @Path("ObtenerProductoAlmacenes/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerProductoAlmacenes(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ProductoDTO ProductoDTO = new ProductoDTO(idUsuario);
            List<ProductoAlmacen> lista = ProductoDTO.ObtenerProductoAlmacenes(filtro);
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
