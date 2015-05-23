package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.DepartamentoDAO;
import com.sge.modulos.administracion.entidades.Departamento;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DepartamentoDTO {
    
    DepartamentoDAO departamentoDAO;
    
    public List<Departamento> ObtenerDepartamentos(String filtro) {
        List<Departamento> lista;
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
