package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Departamento;
import com.sge.modulos.administracion.negocios.DepartamentoDTO;
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
@Path("DepartamentoSRV")
public class DepartamentoSRV {

    @Context
    private UriInfo context;

    public DepartamentoSRV() {
    }

    @POST
    @Path("ObtenerDepartamentos/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerDepartamentos(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            DepartamentoDTO departamentoDTO = new DepartamentoDTO(idUsuario);
            List<Departamento> lista = departamentoDTO.ObtenerDepartamentos(filtro);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(lista));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
