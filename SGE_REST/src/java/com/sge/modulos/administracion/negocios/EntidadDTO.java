package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.EntidadDAO;
import com.sge.modulos.administracion.entidades.Entidad;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntidadDTO {
    
    EntidadDAO entidadDAO;
    
    public List<Entidad> ObtenerEntidades(String filtro) {
        List<Entidad> lista;
        try {
            entidadDAO = new EntidadDAO();
            entidadDAO.AbrirSesion();
            lista = entidadDAO.ObtenerEntidades(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            entidadDAO.CerrarSesion();
        }
        return lista;
    }
    
    public Entidad ObtenerEntidad(int idEntidad) {
        Entidad entidad;
        try {
            entidadDAO = new EntidadDAO();
            entidadDAO.AbrirSesion();
            entidad = entidadDAO.ObtenerPorId(Entidad.class, idEntidad);
        } catch (Exception e) {
            throw e;
        } finally {
            entidadDAO.CerrarSesion();
        }
        return entidad;
    }
}
