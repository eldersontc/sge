package com.sge.modulos.administracion.servicios;

import com.google.gson.Gson;
import com.sge.modulos.administracion.entidades.Empleado;
import com.sge.modulos.administracion.negocios.EmpleadoDTO;
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
@Path("EmpleadoSRV")
public class EmpleadoSRV {

    @Context
    private UriInfo context;

    public EmpleadoSRV() {
    }

    @POST
    @Path("ObtenerEmpleados/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEmpleados(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            String filtro = new Gson().fromJson(json, String.class);
            EmpleadoDTO empleadoDTO = new EmpleadoDTO(idUsuario);
            List<Empleado> lista = empleadoDTO.ObtenerEmpleados(filtro);
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
    @Path("ObtenerEmpleado/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ObtenerEmpleado(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        try {
            int idEmpleado = new Gson().fromJson(json, int.class);
            EmpleadoDTO empleadoDTO = new EmpleadoDTO(idUsuario);
            Empleado empleado = empleadoDTO.ObtenerEmpleado(idEmpleado);
            resultado.add(new Gson().toJson(true));
            resultado.add(new Gson().toJson(empleado));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("RegistrarEmpleado/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String RegistrarEmpleado(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Empleado empleado = new Gson().fromJson(json, Empleado.class);
        try {
            EmpleadoDTO empleadoDTO = new EmpleadoDTO(idUsuario);
            empleadoDTO.RegistrarEmpleado(empleado);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("ActualizarEmpleado/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String ActualizarEmpleado(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        Empleado empleado = new Gson().fromJson(json, Empleado.class);
        try {
            EmpleadoDTO empleadoDTO = new EmpleadoDTO(idUsuario);
            empleadoDTO.ActualizarEmpleado(empleado);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }

    @POST
    @Path("EliminarEmpleado/{idUsuario}")
    @Consumes("application/json")
    @Produces("application/json")
    public String EliminarEmpleado(@PathParam("idUsuario") int idUsuario, String json) {
        List<String> resultado = new ArrayList<>();
        int idEmpleado = new Gson().fromJson(json, int.class);
        try {
            EmpleadoDTO empleadoDTO = new EmpleadoDTO(idUsuario);
            empleadoDTO.EliminarEmpleado(idEmpleado);
            resultado.add(new Gson().toJson(true));
        } catch (Exception e) {
            resultado.clear();
            resultado.add(new Gson().toJson(false));
            resultado.add(new Gson().toJson(e.getMessage()));
        }
        return new Gson().toJson(resultado);
    }
}
