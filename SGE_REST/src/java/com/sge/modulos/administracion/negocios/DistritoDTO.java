package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.DistritoDAO;
import com.sge.modulos.administracion.entidades.Distrito;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DistritoDTO {
    
    DistritoDAO distritoDAO;
    
    public List<Distrito> ObtenerDistritos(String filtro) {
        List<Distrito> lista;
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
