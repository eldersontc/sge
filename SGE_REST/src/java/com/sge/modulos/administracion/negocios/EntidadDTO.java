package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.EntidadDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntidadDTO {
    
    EntidadDAO entidadDAO;
    
    public List<Object[]> ObtenerEntidades(String filtro) {
        List<Object[]> lista;
        try {
            entidadDAO = new EntidadDAO();
            entidadDAO.AbrirSesision();
            lista = entidadDAO.ObtenerEntidades(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            entidadDAO.CerrarSesion();
        }
        return lista;
    }
}
