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
    @Path("ObtenerListasPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListasPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            List<Object[]> lista = listaPrecioServicioDTO.ObtenerListasPrecio(filtro);
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
    @Path("ObtenerListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            ListaPrecioServicio listaPrecio = listaPrecioServicioDTO.ObtenerListaPrecioServicio(idListaPrecioServicio);
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
    @Path("ObtenerUnidadesListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerUnidadesListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            List<UnidadListaPrecioServicio> unidades = listaPrecioServicioDTO.ObtenerUnidadesListaPrecioServicio(idItemListaPrecioServicio);
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
    @Path("ObtenerEscalasListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEscalasListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            List<EscalaListaPrecioServicio> escalas = listaPrecioServicioDTO.ObtenerEscalasListaPrecioServicio(idUnidadListaPrecioServicio);
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
    @Path("RegistrarListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioServicio listaPrecio = new Gson().fromJson(json, ListaPrecioServicio.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.RegistrarListaPrecioServicio(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("RegistrarItemsListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarItemsListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        ItemListaPrecioServicio[] itemsListaPrecio = new Gson().fromJson(json, ItemListaPrecioServicio[].class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.RegistrarItemsListaPrecioServicio(itemsListaPrecio);
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
    @Path("RegistrarUnidadesListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarUnidadesListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        UnidadListaPrecioServicio[] unidadesListaPrecio = new Gson().fromJson(json, UnidadListaPrecioServicio[].class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.RegistrarUnidadesListaPrecioServicio(unidadesListaPrecio);
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
    @Path("RegistrarEscalaListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEscalaListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioServicio escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioServicio.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.RegistrarEscalaListaPrecioServicio(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(escalaListaPrecio.getIdEscalaListaPrecioServicio()));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        ListaPrecioServicio listaPrecio = new Gson().fromJson(json, ListaPrecioServicio.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.ActualizarListaPrecioServicio(listaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("ActualizarEscalaListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarEscalaListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        EscalaListaPrecioServicio escalaListaPrecio = new Gson().fromJson(json, EscalaListaPrecioServicio.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.ActualizarEscalaListaPrecioServicio(escalaListaPrecio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.EliminarListaPrecioServicio(idListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarItemListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarItemListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idItemListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.EliminarItemListaPrecioServicio(idItemListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarUnidadListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarUnidadListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idUnidadListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.EliminarUnidadListaPrecioServicio(idUnidadListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
    
    @POST
    @Path("EliminarEscalaListaPrecioServicio")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEscalaListaPrecioServicio(String json) {
        List<String> resultado = new ArrayList<>();
        int idEscalaListaPrecioServicio = new Gson().fromJson(json, int.class);
        try {
            ListaPrecioServicioDTO listaPrecioServicioDTO = new ListaPrecioServicioDTO();
            listaPrecioServicioDTO.EliminarEscalaListaPrecioServicio(idEscalaListaPrecioServicio);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e));
        }
        return new Gson().toJson(resultado);
    }
}
