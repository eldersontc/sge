package com.sge.modulos.facturacion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.facturacion.entidades.Factura;
import com.sge.modulos.facturacion.negocios.FacturaDTO;
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
@Path("FacturaSRV")
public class FacturaSRV {

    @Context
    private UriInfo context;

    public FacturaSRV() {
    }

    @POST
    @Path("ObtenerFacturas/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerFacturas(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            FacturaDTO FacturaDTO = new FacturaDTO(idUsuario);
            List<Factura> lista = FacturaDTO.ObtenerFacturas(filtro);
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
    @Path("ObtenerFactura/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerFactura(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idFactura = new Gson().fromJson(json, int.class);
        try {
            FacturaDTO entradaInventarioDTO = new FacturaDTO(idUsuario);
            Factura entradaInventario = entradaInventarioDTO.ObtenerFactura(idFactura);
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
    @Path("RegistrarFactura/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarFactura(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Factura caja = new Gson().fromJson(json, Factura.class);
        try {
            FacturaDTO FacturaDTO = new FacturaDTO(idUsuario);
            FacturaDTO.RegistrarFactura(caja);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarFactura/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarFactura(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idFactura = new Gson().fromJson(json, int.class);
        try {
            FacturaDTO FacturaDTO = new FacturaDTO(idUsuario);
            FacturaDTO.EliminarFactura(idFactura);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
