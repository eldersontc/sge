package com.sge.modulos.finanzas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.finanzas.entidades.EntradaCaja;
import com.sge.modulos.finanzas.negocios.EntradaCajaDTO;
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
@Path("EntradaCajaSRV")
public class EntradaCajaSRV {

    @Context
    private UriInfo context;

    public EntradaCajaSRV() {
    }

    @POST
    @Path("ObtenerEntradasCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntradasCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            EntradaCajaDTO EntradaCajaDTO = new EntradaCajaDTO(idUsuario);
            List<EntradaCaja> lista = EntradaCajaDTO.ObtenerEntradasCaja(filtro);
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
    @Path("ObtenerEntradaCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntradaCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idEntradaCaja = new Gson().fromJson(json, int.class);
        try {
            EntradaCajaDTO entradaInventarioDTO = new EntradaCajaDTO(idUsuario);
            EntradaCaja entradaInventario = entradaInventarioDTO.ObtenerEntradaCaja(idEntradaCaja);
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
    @Path("RegistrarEntradaCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEntradaCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        EntradaCaja caja = new Gson().fromJson(json, EntradaCaja.class);
        try {
            EntradaCajaDTO EntradaCajaDTO = new EntradaCajaDTO(idUsuario);
            EntradaCajaDTO.RegistrarEntradaCaja(caja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEntradaCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEntradaCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idEntradaCaja = new Gson().fromJson(json, int.class);
        try {
            EntradaCajaDTO EntradaCajaDTO = new EntradaCajaDTO(idUsuario);
            EntradaCajaDTO.EliminarEntradaCaja(idEntradaCaja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
