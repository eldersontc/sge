package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.Cotizacion;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioProducto;
import com.sge.modulos.ventas.entidades.ItemListaPrecioMaquina;
import com.sge.modulos.ventas.negocios.CotizacionDTO;
import com.sge.modulos.ventas.negocios.ListaPrecioMaquinaDTO;
import com.sge.modulos.ventas.negocios.ListaPrecioProductoDTO;
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
@Path("CotizacionSRV")
public class CotizacionSRV {

    @Context
    private UriInfo context;

    public CotizacionSRV() {
    }

    @POST
    @Path("ObtenerCotizaciones/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerCotizaciones(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            CotizacionDTO CotizacionDTO = new CotizacionDTO(idUsuario);
            List<Cotizacion> lista = CotizacionDTO.ObtenerCotizaciones(filtro);
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
    @Path("ObtenerCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idCotizacion = new Gson().fromJson(json, int.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO(idUsuario);
            Cotizacion cotizacion = CotizacionDTO.ObtenerCotizacion(idCotizacion);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(cotizacion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Cotizacion cotizacion = new Gson().fromJson(json, Cotizacion.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO(idUsuario);
            CotizacionDTO.RegistrarCotizacion(cotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Cotizacion cotizacion = new Gson().fromJson(json, Cotizacion.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO(idUsuario);
            CotizacionDTO.ActualizarCotizacion(cotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idCotizacion = new Gson().fromJson(json, int.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO(idUsuario);
            CotizacionDTO.EliminarCotizacion(idCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("AprobarCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String AprobarCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idCotizacion = new Gson().fromJson(json, int.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO(idUsuario);
            CotizacionDTO.AprobarCotizacion(idCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("DesaprobarCotizacion/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String DesaprobarCotizacion(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idCotizacion = new Gson().fromJson(json, int.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO(idUsuario);
            CotizacionDTO.DesaprobarCotizacion(idCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerEscalasMaquinaYProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasMaquinaYProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int[] ids = new Gson().fromJson(json, int[].class);
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            List<ItemListaPrecioMaquina> itemsMaquina = listaPrecioMaquinaDTO.ObtenerEscalasPorMaquina(ids[0], ids[1]);
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            List<EscalaListaPrecioProducto> escalasProducto = listaPrecioProductoDTO.ObtenerEscalasPorProducto(ids[2], ids[3], ids[4]);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(itemsMaquina));
            resultado.add(new Gson().toJson(escalasProducto));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
