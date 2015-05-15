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
    @Path("ObtenerCotizaciones")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerCotizaciones(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            CotizacionDTO CotizacionDTO = new CotizacionDTO();
            List<Object[]> lista = CotizacionDTO.ObtenerCotizaciones(filtro);
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
    @Path("ObtenerCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        int idCotizacion = new Gson().fromJson(json, int.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO();
            Cotizacion cotizacion = CotizacionDTO.ObtenerCotizacion(idCotizacion);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(cotizacion));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        Cotizacion cotizacion = new Gson().fromJson(json, Cotizacion.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO();
            CotizacionDTO.RegistrarCotizacion(cotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        Cotizacion cotizacion = new Gson().fromJson(json, Cotizacion.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO();
            CotizacionDTO.ActualizarCotizacion(cotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarCotizacion")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarCotizacion(String json) {
        List<String> resultado = new ArrayList<>();
        int idCotizacion = new Gson().fromJson(json, int.class);
        try {
            CotizacionDTO CotizacionDTO = new CotizacionDTO();
            CotizacionDTO.EliminarCotizacion(idCotizacion);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerEscalasMaquinaYProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasMaquinaYProducto(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int[] ids = new Gson().fromJson(json, int[].class);
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            List<ItemListaPrecioMaquina> itemsMaquina = listaPrecioMaquinaDTO.ObtenerEscalasPorMaquina(ids[0], ids[1]);
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            List<EscalaListaPrecioProducto> escalasProducto = listaPrecioProductoDTO.ObtenerEscalasPorProducto(ids[2], ids[3], ids[4]);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(itemsMaquina));
            resultado.add(new Gson().toJson(escalasProducto));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
