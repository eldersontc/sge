package com.sge.modulos.inventarios.servicios;

import com.google.gson.Gson;
import com.sge.modulos.inventarios.entidades.ItemSalidaInventario;
import com.sge.modulos.inventarios.entidades.SalidaInventario;
import com.sge.modulos.inventarios.negocios.SalidaInventarioDTO;
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
@Path("SalidaInventarioSRV")
public class SalidaInventarioSRV {
    
    @Context
    private UriInfo context;

    public SalidaInventarioSRV() {
    }

    @POST
    @Path("ObtenerSalidaInventarios")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSalidaInventarios(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            SalidaInventarioDTO salidaInventarioDTO = new SalidaInventarioDTO();
            List<SalidaInventario> lista = salidaInventarioDTO.ObtenerSalidaInventarios(filtro);
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
    @Path("ObtenerSalidaInventario")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerSalidaInventario(String json) {
        List<String> resultado = new ArrayList<>();
        int idSalidaInventario = new Gson().fromJson(json, int.class);
        try {
            SalidaInventarioDTO salidaInventarioDTO = new SalidaInventarioDTO();
            SalidaInventario salidaInventario = salidaInventarioDTO.ObtenerSalidaInventario(idSalidaInventario);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(salidaInventario));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarSalidaInventario")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarSalidaInventario(String json) {
        List<String> resultado = new ArrayList<>();
        SalidaInventario salidaInventario = new Gson().fromJson(json, SalidaInventario.class);
        try {
            SalidaInventarioDTO salidaInventarioDTO = new SalidaInventarioDTO();
            salidaInventarioDTO.RegistrarSalidaInventario(salidaInventario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarSalidaInventario")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarSalidaInventario(String json) {
        List<String> resultado = new ArrayList<>();
        int idSalidaInventario = new Gson().fromJson(json, int.class);
        try {
            SalidaInventarioDTO salidaInventarioDTO = new SalidaInventarioDTO();
            salidaInventarioDTO.EliminarSalidaInventario(idSalidaInventario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerStockFisicoItems")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerStockFisicoItems(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            ItemSalidaInventario[] items = new Gson().fromJson(json, ItemSalidaInventario[].class);
            SalidaInventarioDTO salidaInventarioDTO = new SalidaInventarioDTO();
            ItemSalidaInventario[] lista = salidaInventarioDTO.ObtenerStockFisicoItems(items);
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
