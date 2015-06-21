package com.sge.modulos.produccion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.ValorDefinido;
import com.sge.modulos.administracion.negocios.ValorDefinidoDTO;
import com.sge.modulos.produccion.entidades.OrdenTrabajo;
import com.sge.modulos.produccion.negocios.OrdenTrabajoDTO;
import java.util.ArrayList;
import java.util.Date;
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
@Path("OrdenTrabajoSRV")
public class OrdenTrabajoSRV {

    @Context
    private UriInfo context;

    public OrdenTrabajoSRV() {
    }

    @POST
    @Path("ObtenerOrdenesTrabajo/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerOrdenesTrabajo(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO(idUsuario);
            List<OrdenTrabajo> lista = OrdenTrabajoDTO.ObtenerOrdenesTrabajo(filtro);
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
    @Path("ObtenerOrdenTrabajo/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerOrdenTrabajo(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idOrdenTrabajo = new Gson().fromJson(json, int.class);
        try {
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO(idUsuario);
            OrdenTrabajo ordenTrabajo = OrdenTrabajoDTO.ObtenerOrdenTrabajo(idOrdenTrabajo);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(ordenTrabajo));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarOrdenTrabajo/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarOrdenTrabajo(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        OrdenTrabajo ordenTrabajo = new Gson().fromJson(json, OrdenTrabajo.class);
        try {
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO(idUsuario);
            OrdenTrabajoDTO.RegistrarOrdenTrabajo(ordenTrabajo);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarOrdenTrabajo/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarOrdenTrabajo(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        OrdenTrabajo ordenTrabajo = new Gson().fromJson(json, OrdenTrabajo.class);
        try {
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO(idUsuario);
            OrdenTrabajoDTO.ActualizarOrdenTrabajo(ordenTrabajo);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarOrdenTrabajo/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarOrdenTrabajo(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idOrdenTrabajo = new Gson().fromJson(json, int.class);
        try {
            OrdenTrabajoDTO OrdenTrabajoDTO = new OrdenTrabajoDTO(idUsuario);
            OrdenTrabajoDTO.EliminarOrdenTrabajo(idOrdenTrabajo);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("GenerarSalidaInventario/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String GenerarSalidaInventario(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int[] ids = new Gson().fromJson(json, int[].class);
        try {
            OrdenTrabajoDTO ordenTrabajoDTO = new OrdenTrabajoDTO(idUsuario);
            OrdenTrabajo ordenTrabajo = ordenTrabajoDTO.ObtenerOrdenTrabajo(ids[0]);
            ValorDefinidoDTO valorDefinidoDTO = new ValorDefinidoDTO(idUsuario);
            ValorDefinido valorDefinido = valorDefinidoDTO.ObtenerValorDefinidoPorUsuarioYEntidad(ids[1], 2);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(new Date()));
            resultado.add(new Gson().toJson(ordenTrabajo));
            resultado.add(valorDefinido == null ? "" : valorDefinido.getJson());
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("GenerarSalidaCaja/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String GenerarSalidaCaja(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int[] ids = new Gson().fromJson(json, int[].class);
        try {
            OrdenTrabajoDTO ordenTrabajoDTO = new OrdenTrabajoDTO(idUsuario);
            OrdenTrabajo ordenTrabajo = ordenTrabajoDTO.ObtenerOrdenTrabajo(ids[0]);
            ValorDefinidoDTO valorDefinidoDTO = new ValorDefinidoDTO(idUsuario);
            ValorDefinido valorDefinido = valorDefinidoDTO.ObtenerValorDefinidoPorUsuarioYEntidad(ids[1], 9);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(new Date()));
            resultado.add(new Gson().toJson(ordenTrabajo));
            resultado.add(valorDefinido == null ? "" : valorDefinido.getJson());
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
