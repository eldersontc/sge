package com.sge.modulos.administracion.negocios;

import com.sge.modulos.administracion.accesoDatos.ProvinciaDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProvinciaDTO {
    
    ProvinciaDAO provinciaDAO;
    
    public List<Object[]> ObtenerProvincias(String filtro) {
        List<Object[]> lista;
        try {
            provinciaDAO = new ProvinciaDAO();
            provinciaDAO.AbrirSesion();
            lista = provinciaDAO.ObtenerProvincias(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            provinciaDAO.CerrarSesion();
        }
        return lista;
    }
}
