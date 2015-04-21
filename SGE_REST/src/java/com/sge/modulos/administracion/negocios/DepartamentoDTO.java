package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.DepartamentoDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DepartamentoDTO {
    
    DepartamentoDAO departamentoDAO;
    
    public List<Object[]> ObtenerDepartamentos(String filtro) {
        List<Object[]> lista;
        try {
            departamentoDAO = new DepartamentoDAO();
            departamentoDAO.AbrirSesion();
            lista = departamentoDAO.ObtenerDepartamentos(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            departamentoDAO.CerrarSesion();
        }
        return lista;
    }
}
