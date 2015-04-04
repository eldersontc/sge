
package com.sge.modulos.inventarios.servicios;

import com.google.gson.Gson;
import com.sge.modulos.inventarios.entidades.EntradaInventario;
import com.sge.modulos.inventarios.entidades.ItemEntradaInventario;
import com.sge.modulos.inventarios.negocios.EntradaInventarioDTO;
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
@Path("EntradaInventarioSRV")
public class EntradaInventarioSRV {
    
    @Context
    private UriInfo context;

    public EntradaInventarioSRV() {
    }

    @POST
    @Path("ObtenerEntradaInventarios")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntradaInventarios(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            EntradaInventarioDTO entradaInventarioDTO = new EntradaInventarioDTO();
            List<Object[]> lista = entradaInventarioDTO.ObtenerEntradaInventarios();
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerEntradaInventario")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntradaInventario(String json) {
        List<String> resultado = new ArrayList<>();
        int idEntradaInventario = new Gson().fromJson(json, int.class);
        try {
            EntradaInventarioDTO entradaInventarioDTO = new EntradaInventarioDTO();
            List<Object> lista = entradaInventarioDTO.ObtenerEntradaInventario(idEntradaInventario);
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
    @Path("RegistrarEntradaInventario")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEntradaInventario(String json) {
        List<String> resultado = new ArrayList<>();
        String[] arrayJson = new Gson().fromJson(json, String[].class);
        EntradaInventario entradaInventario = new Gson().fromJson(arrayJson[0], EntradaInventario.class);
        ItemEntradaInventario[] items = new Gson().fromJson(arrayJson[1], ItemEntradaInventario[].class);
        try {
            EntradaInventarioDTO entradaInventarioDTO = new EntradaInventarioDTO();
            entradaInventarioDTO.RegistrarEntradaInventario(entradaInventario, items);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEntradaInventario")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEntradaInventario(String json) {
        List<String> resultado = new ArrayList<>();
        int idEntradaInventario = new Gson().fromJson(json, int.class);
        try {
            EntradaInventarioDTO entradaInventarioDTO = new EntradaInventarioDTO();
            entradaInventarioDTO.EliminarProducto(idEntradaInventario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.add(new Gson().toJson(false));
        }
        return new Gson().toJson(resultado);
    }
}
