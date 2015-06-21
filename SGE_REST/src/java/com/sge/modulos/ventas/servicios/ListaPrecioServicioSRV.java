package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioServicio;
import com.sge.modulos.ventas.entidades.ItemListaPrecioServicio;
import com.sge.modulos.ventas.entidades.ListaPrecioServicio;
import com.sge.modulos.ventas.entidades.UnidadListaPrecioServicio;
import com.sge.modulos.ventas.negocios.ListaPrecioServicioDTO;
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
@Path("ListaPrecioServicioSRV")
public class ListaPrecioServicioSRV {

    @Context
    private UriInfo context;

    public ListaPrecioServicioSRV() {
    }

    @POST
    @Path("ObtenerListasPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListasPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            List<ListaPrecioServicio> lista = listaPrecioServicioDTO.ObtenerListasPrecio(filtro);
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
    @Path("ObtenerListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            ListaPrecioServicio listaPrecio = listaPrecioServicioDTO.ObtenerListaPrecioServicio(idListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(listaPrecio));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerUnidadesListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidadesListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            List<UnidadListaPrecioServicio> unidades = listaPrecioServicioDTO.ObtenerUnidadesListaPrecioServicio(idItemListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(unidades));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerEscalasListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            List<EscalaListaPrecioServicio> escalas = listaPrecioServicioDTO.ObtenerEscalasListaPrecioServicio(idUnidadListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalas));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerEscalasListaPrecioServicioPorUnidad/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasListaPrecioServicioPorUnidad(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int[] ids = new Gson().fromJson(json, int[].class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            List<EscalaListaPrecioServicio> escalas = listaPrecioServicioDTO.ObtenerEscalasListaPrecioServicioPorUnidad(ids[0], ids[1], ids[2]);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalas));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioServicio listaPrecio = new Gson().fromJson(json, ListaPrecioServicio.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.RegistrarListaPrecioServicio(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarItemsListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarItemsListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ItemListaPrecioServicio[] itemsListaPrecio = new Gson().fromJson(json, ItemListaPrecioServicio[].class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.RegistrarItemsListaPrecioServicio(itemsListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(itemsListaPrecio));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarUnidadesListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarUnidadesListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        UnidadListaPrecioServicio[] unidadesListaPrecio = new Gson().fromJson(json, UnidadListaPrecioServicio[].class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.RegistrarUnidadesListaPrecioServicio(unidadesListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(unidadesListaPrecio));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarEscalaListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEscalaListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioServicio escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioServicio.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.RegistrarEscalaListaPrecioServicio(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioServicio()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioServicio listaPrecio = new Gson().fromJson(json, ListaPrecioServicio.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.ActualizarListaPrecioServicio(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarEscalaListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarEscalaListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioServicio escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioServicio.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.ActualizarEscalaListaPrecioServicio(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioServicio()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.EliminarListaPrecioServicio(idListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarItemListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarItemListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.EliminarItemListaPrecioServicio(idItemListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarUnidadListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarUnidadListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.EliminarUnidadListaPrecioServicio(idUnidadListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEscalaListaPrecioServicio/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEscalaListaPrecioServicio(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idEscalaListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO(idUsuario);
            listaPrecioServicioDTO.EliminarEscalaListaPrecioServicio(idEscalaListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
