package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.DistritoDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DistritoDTO {
    
    DistritoDAO distritoDAO;
    
    public List<Object[]> ObtenerDistritos(String filtro) {
        List<Object[]> lista;
        try {
            distritoDAO = new DistritoDAO();
            distritoDAO.AbrirSesion();
            lista = distritoDAO.ObtenerDistritos(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            distritoDAO.CerrarSesion();
        }
        return lista;
    }
}
