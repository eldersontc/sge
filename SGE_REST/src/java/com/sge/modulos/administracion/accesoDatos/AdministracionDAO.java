package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author elderson
 */
public class AdministracionDAO extends BaseDAO {
    
    private List<String> Recursos(){
        List<String> recursos = new ArrayList<>();
//        recursos.add("com/sge/modulos/administracion/mapeos/Menu.hbm.xml");
//        recursos.add("com/sge/modulos/administracion/mapeos/Perfil.hbm.xml");
//        recursos.add("com/sge/modulos/administracion/mapeos/PerfilUsuario.hbm.xml");
//        recursos.add("com/sge/modulos/administracion/mapeos/PerfilMenu.hbm.xml");
        recursos.add("com/sge/modulos/administracion/mapeos/Usuario.hbm.xml");
        recursos.add("com/sge/modulos/administracion/mapeos/Empleado.hbm.xml");
        return recursos;
    }
    
    public void AbrirSesision(){
        super.AbrirSesion(this.Recursos());
    }
    
    public void IniciarTransaccion(){
        super.IniciarTransaccion(this.Recursos());
    }
}
