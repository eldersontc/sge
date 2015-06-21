package com.sge.modulos.administracion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.ProvinciaDAO;
import com.sge.modulos.administracion.entidades.Provincia;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProvinciaDTO extends BaseDTO {

    ProvinciaDAO provinciaDAO;

    public ProvinciaDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Provincia> ObtenerProvincias(String filtro) {
        List<Provincia> lista;
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
