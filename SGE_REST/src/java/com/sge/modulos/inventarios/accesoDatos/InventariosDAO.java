package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class InventariosDAO extends BaseDAO {

    private List<String> Recursos(){
        List<String> recursos = new ArrayList<>();
        recursos.add("com/sge/modulos/inventarios/mapeos/Almacen.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/Unidad.hbm.xml");
        return recursos;
    }
    
    public void AbrirSesision(){
        super.AbrirSesion(this.Recursos());
    }
    
    public void IniciarTransaccion(){
        super.IniciarTransaccion(this.Recursos());
    }
}
