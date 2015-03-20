package com.sge.modulos.administracion.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class MenuDAO extends AdministracionDAO{
    
    public List<Object[]> ObtenerMenus(){
        String sql = "SELECT * FROM Administracion.Menu";
        return super.Query(sql);
    }
}
