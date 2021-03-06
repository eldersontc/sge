package com.sge.modulos.administracion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.DepartamentoDAO;
import com.sge.modulos.administracion.entidades.Departamento;
import java.util.List;

/**
 *
 * @author elderson
 */
public class DepartamentoDTO extends BaseDTO {
    
    DepartamentoDAO departamentoDAO;

    public DepartamentoDTO(int idUsuario) {
        super(idUsuario);
    }
    
    public List<Departamento> ObtenerDepartamentos(String filtro) {
        List<Departamento> lista;
        try {
            departamentoDAO = new DepartamentoDAO();
            departamentoDAO.AbrirSesion();
            lista = departamentoDAO.ObtenerDepartamentos(getFiltro(departamentoDAO, 12, filtro));
        } catch (Exception e) {
            throw e;
        } finally {
            departamentoDAO.CerrarSesion();
        }
        return lista;
    }
}
