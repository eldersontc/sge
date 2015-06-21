package com.sge.modulos.ventas.servicios;

import com.google.gson.Gson;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioProducto;
import com.sge.modulos.ventas.entidades.ItemListaPrecioProducto;
import com.sge.modulos.ventas.entidades.ListaPrecioProducto;
import com.sge.modulos.ventas.entidades.UnidadListaPrecioProducto;
import com.sge.modulos.ventas.negocios.ListaPrecioProductoDTO;
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
@Path("ListaPrecioProductoSRV")
public class ListaPrecioProductoSRV {

    @Context
    private UriInfo context;

    public ListaPrecioProductoSRV() {
    }

    @POST
    @Path("ObtenerListasPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListasPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            List<ListaPrecioProducto> lista = listaPrecioProductoDTO.ObtenerListasPrecio(filtro);
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
    @Path("ObtenerListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            ListaPrecioProducto listaPrecio = listaPrecioProductoDTO.ObtenerListaPrecioProducto(idListaPrecioProducto);
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
    @Path("ObtenerUnidadesListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidadesListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            List<UnidadListaPrecioProducto> unidades = listaPrecioProductoDTO.ObtenerUnidadesListaPrecioProducto(idItemListaPrecioProducto);
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
    @Path("ObtenerEscalasListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            List<EscalaListaPrecioProducto> escalas = listaPrecioProductoDTO.ObtenerEscalasListaPrecioProducto(idUnidadListaPrecioProducto);
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
    @Path("RegistrarListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioProducto listaPrecio = new Gson().fromJson(json, ListaPrecioProducto.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.RegistrarListaPrecioProducto(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarItemsListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarItemsListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ItemListaPrecioProducto[] itemsListaPrecio = new Gson().fromJson(json, ItemListaPrecioProducto[].class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.RegistrarItemsListaPrecioProducto(itemsListaPrecio);
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
    @Path("RegistrarUnidadesListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarUnidadesListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        UnidadListaPrecioProducto[] unidadesListaPrecio = new Gson().fromJson(json, UnidadListaPrecioProducto[].class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.RegistrarUnidadesListaPrecioProducto(unidadesListaPrecio);
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
    @Path("RegistrarEscalaListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEscalaListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioProducto escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioProducto.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.RegistrarEscalaListaPrecioProducto(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioProducto()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioProducto listaPrecio = new Gson().fromJson(json, ListaPrecioProducto.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.ActualizarListaPrecioProducto(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarEscalaListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarEscalaListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioProducto escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioProducto.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.ActualizarEscalaListaPrecioProducto(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioProducto()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.EliminarListaPrecioProducto(idListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarItemListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarItemListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.EliminarItemListaPrecioProducto(idItemListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarUnidadListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarUnidadListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.EliminarUnidadListaPrecioProducto(idUnidadListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEscalaListaPrecioProducto/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEscalaListaPrecioProducto(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idEscalaListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO(idUsuario);
            listaPrecioProductoDTO.EliminarEscalaListaPrecioProducto(idEscalaListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
