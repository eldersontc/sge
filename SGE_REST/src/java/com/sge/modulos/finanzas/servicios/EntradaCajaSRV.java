package com.sge.modulos.finanzas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.finanzas.entidades.EntradaCaja;
import com.sge.modulos.finanzas.negocios.EntradaCajaDTO;
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
@Path("EntradaCajaSRV")
public class EntradaCajaSRV {
    
    @Context
    private UriInfo context;

    public EntradaCajaSRV() {
    }

    @POST
    @Path("ObtenerEntradasCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntradasCaja(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            EntradaCajaDTO EntradaCajaDTO = new EntradaCajaDTO();
            List<EntradaCaja> lista = EntradaCajaDTO.ObtenerEntradasCaja(filtro);
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
    @Path("ObtenerEntradaCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEntradaCaja(String json) {
        List<String> resultado = new ArrayList<>();
        int idEntradaCaja = new Gson().fromJson(json, int.class);
        try {
            EntradaCajaDTO entradaInventarioDTO = new EntradaCajaDTO();
            EntradaCaja entradaInventario = entradaInventarioDTO.ObtenerEntradaCaja(idEntradaCaja);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(entradaInventario));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarEntradaCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEntradaCaja(String json) {
        List<String> resultado = new ArrayList<>();
        EntradaCaja caja = new Gson().fromJson(json, EntradaCaja.class);
        try {
            EntradaCajaDTO EntradaCajaDTO = new EntradaCajaDTO();
            EntradaCajaDTO.RegistrarEntradaCaja(caja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEntradaCaja")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEntradaCaja(String json) {
        List<String> resultado = new ArrayList<>();
        int idEntradaCaja = new Gson().fromJson(json, int.class);
        try {
            EntradaCajaDTO EntradaCajaDTO = new EntradaCajaDTO();
            EntradaCajaDTO.EliminarEntradaCaja(idEntradaCaja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
