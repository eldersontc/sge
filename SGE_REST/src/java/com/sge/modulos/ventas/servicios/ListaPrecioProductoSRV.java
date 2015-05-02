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
    @Path("ObtenerListasPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListasPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            List<Object[]> lista = listaPrecioProductoDTO.ObtenerListasPrecio(filtro);
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
    @Path("ObtenerListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            ListaPrecioProducto listaPrecio = listaPrecioProductoDTO.ObtenerListaPrecioProducto(idListaPrecioProducto);
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
    @Path("ObtenerUnidadesListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidadesListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            List<UnidadListaPrecioProducto> unidades = listaPrecioProductoDTO.ObtenerUnidadesListaPrecioProducto(idItemListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(unidades));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ObtenerEscalasListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            List<EscalaListaPrecioProducto> escalas = listaPrecioProductoDTO.ObtenerEscalasListaPrecioProducto(idUnidadListaPrecioProducto);
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
    @Path("RegistrarListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioProducto listaPrecio = new Gson().fromJson(json, ListaPrecioProducto.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.RegistrarListaPrecioProducto(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarItemsListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarItemsListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        ItemListaPrecioProducto[] itemsListaPrecio = new Gson().fromJson(json, ItemListaPrecioProducto[].class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.RegistrarItemsListaPrecioProducto(itemsListaPrecio);
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
    @Path("RegistrarUnidadesListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarUnidadesListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        UnidadListaPrecioProducto[] unidadesListaPrecio = new Gson().fromJson(json, UnidadListaPrecioProducto[].class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.RegistrarUnidadesListaPrecioProducto(unidadesListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(unidadesListaPrecio));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarEscalaListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEscalaListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioProducto escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioProducto.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.RegistrarEscalaListaPrecioProducto(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioProducto()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioProducto listaPrecio = new Gson().fromJson(json, ListaPrecioProducto.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.ActualizarListaPrecioProducto(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarEscalaListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarEscalaListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioProducto escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioProducto.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.ActualizarEscalaListaPrecioProducto(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.EliminarListaPrecioProducto(idListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarItemListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarItemListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.EliminarItemListaPrecioProducto(idItemListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarUnidadListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarUnidadListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.EliminarUnidadListaPrecioProducto(idUnidadListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarEscalaListaPrecioProducto")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEscalaListaPrecioProducto(String json) {
        List<String> resultado = new ArrayList<>();
        int idEscalaListaPrecioProducto = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioProductoDTO listaPrecioProductoDTO = new ListaPrecioProductoDTO();
            listaPrecioProductoDTO.EliminarEscalaListaPrecioProducto(idEscalaListaPrecioProducto);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
