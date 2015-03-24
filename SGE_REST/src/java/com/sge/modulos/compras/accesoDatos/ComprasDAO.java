package com.sge.modulos.compras.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ComprasDAO extends BaseDAO {
    
    private List<String> Recursos(){
        List<String> recursos = new ArrayList<>();
        recursos.add("com/sge/modulos/compras/mapeos/Proveedor.hbm.xml");
        return recursos;
    }
    
    public void AbrirSesision(){
        super.AbrirSesion(this.Recursos());
    }
    
    public void IniciarTransaccion(){
        super.IniciarTransaccion(this.Recursos());
    }
}
