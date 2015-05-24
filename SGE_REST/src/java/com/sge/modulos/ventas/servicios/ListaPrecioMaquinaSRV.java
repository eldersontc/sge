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
    @Path("ObtenerListasPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListasPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            List<ListaPrecioMaquina> lista = listaPrecioMaquinaDTO.ObtenerListasPrecio(filtro);
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
    @Path("ObtenerListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            ListaPrecioMaquina listaPrecio = listaPrecioMaquinaDTO.ObtenerListaPrecioMaquina(idListaPrecioMaquina);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(listaPrecio));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ObtenerEscalasListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            List<EscalaListaPrecioMaquina> escalas = listaPrecioMaquinaDTO.ObtenerEscalasListaPrecioMaquina(idUnidadListaPrecioMaquina);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalas));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioMaquina listaPrecio = new Gson().fromJson(json, ListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.RegistrarListaPrecioMaquina(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarItemsListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarItemsListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        ItemListaPrecioMaquina[] itemsListaPrecio = new Gson().fromJson(json, ItemListaPrecioMaquina[].class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.RegistrarItemsListaPrecioMaquina(itemsListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(itemsListaPrecio));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarEscalaListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEscalaListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioMaquina escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.RegistrarEscalaListaPrecioMaquina(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioMaquina()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioMaquina listaPrecio = new Gson().fromJson(json, ListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.ActualizarListaPrecioMaquina(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarItemListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarItemListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        ItemListaPrecioMaquina itemListaPrecio = new Gson().fromJson(json, ItemListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.ActualizarItemListaPrecioMaquina(itemListaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarEscalaListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarEscalaListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioMaquina escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioMaquina.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.ActualizarEscalaListaPrecioMaquina(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioMaquina()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.EliminarListaPrecioMaquina(idListaPrecioMaquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarItemListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarItemListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.EliminarItemListaPrecioMaquina(idItemListaPrecioMaquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEscalaListaPrecioMaquina")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEscalaListaPrecioMaquina(String json) {
        List<String> resultado = new ArrayList<>();
        int idEscalaListaPrecioMaquina = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioMaquinaDTO listaPrecioMaquinaDTO = new ListaPrecioMaquinaDTO();
            listaPrecioMaquinaDTO.EliminarEscalaListaPrecioMaquina(idEscalaListaPrecioMaquina);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
