package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioMaquina;
import com.sge.modulos.ventas.entidades.ItemListaPrecioMaquina;
import com.sge.modulos.ventas.entidades.ListaPrecioMaquina;
import com.sge.modulos.ventas.negocios.ListaPrecioMaquinaDTO;
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
@Path("ListaPrecioMaquinaSRV")
public class ListaPrecioMaquinaSRV {

    @Context
    private UriInfo context;

    public ListaPrecioMaquinaSRV() {
    }

    @POST
    @Path("ObtenerListasPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListasPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            List<ListaPrecioMaquina> lista = listaPrecioMaquinaDTO.ObtenerListasPrecio(filtro);
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
    @Path("ObtenerListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            ListaPrecioMaquina listaPrecio = listaPrecioMaquinaDTO.ObtenerListaPrecioMaquina(idListaPrecioMaquina);
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
    @Path("ObtenerEscalasListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            List<EscalaListaPrecioMaquina> escalas = listaPrecioMaquinaDTO.ObtenerEscalasListaPrecioMaquina(idUnidadListaPrecioMaquina);
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
    @Path("RegistrarListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioMaquina listaPrecio = new Gson().fromJson(json, ListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.RegistrarListaPrecioMaquina(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarItemsListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarItemsListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ItemListaPrecioMaquina[] itemsListaPrecio = new Gson().fromJson(json, ItemListaPrecioMaquina[].class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.RegistrarItemsListaPrecioMaquina(itemsListaPrecio);
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
    @Path("RegistrarEscalaListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEscalaListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioMaquina escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.RegistrarEscalaListaPrecioMaquina(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioMaquina()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioMaquina listaPrecio = new Gson().fromJson(json, ListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.ActualizarListaPrecioMaquina(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarItemListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarItemListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ItemListaPrecioMaquina itemListaPrecio = new Gson().fromJson(json, ItemListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.ActualizarItemListaPrecioMaquina(itemListaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarEscalaListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarEscalaListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioMaquina escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.ActualizarEscalaListaPrecioMaquina(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioMaquina()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.EliminarListaPrecioMaquina(idListaPrecioMaquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarItemListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarItemListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.EliminarItemListaPrecioMaquina(idItemListaPrecioMaquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEscalaListaPrecioMaquina/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEscalaListaPrecioMaquina(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idEscalaListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO(idUsuario);
            listaPrecioMaquinaDTO.EliminarEscalaListaPrecioMaquina(idEscalaListaPrecioMaquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
