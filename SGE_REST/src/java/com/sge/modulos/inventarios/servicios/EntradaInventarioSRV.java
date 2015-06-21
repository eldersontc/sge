package com.sge.modulos.inventarios.servicios;

import com.google.gson.Gson;
import com.sge.modulos.inventarios.entidades.EntradaInventario;
import com.sge.modulos.inventarios.negocios.EntradaInventarioDTO;
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
@Path("EntradaInventarioSRV")
public class EntradaInventarioSRV {

    @Context
    private UriInfo context;

    public EntradaInventarioSRV() {
    }

    @POST
    @Path("ObtenerEntradaInventarios/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntradaInventarios(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            EntradaInventarioDTO entradaInventarioDTO = new EntradaInventarioDTO(idUsuario);
            List<EntradaInventario> lista = entradaInventarioDTO.ObtenerEntradaInventarios(filtro);
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
    @Path("ObtenerEntradaInventario/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntradaInventario(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idEntradaInventario = new Gson().fromJson(json, int.class);
        try {
            EntradaInventarioDTO entradaInventarioDTO = new EntradaInventarioDTO(idUsuario);
            EntradaInventario entradaInventario = entradaInventarioDTO.ObtenerEntradaInventario(idEntradaInventario);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(entradaInventario));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarEntradaInventario/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEntradaInventario(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        EntradaInventario entradaInventario = new Gson().fromJson(json, EntradaInventario.class);
        try {
            EntradaInventarioDTO entradaInventarioDTO = new EntradaInventarioDTO(idUsuario);
            entradaInventarioDTO.RegistrarEntradaInventario(entradaInventario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEntradaInventario/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEntradaInventario(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idEntradaInventario = new Gson().fromJson(json, int.class);
        try {
            EntradaInventarioDTO entradaInventarioDTO = new EntradaInventarioDTO(idUsuario);
            entradaInventarioDTO.EliminarEntradaInventario(idEntradaInventario);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
