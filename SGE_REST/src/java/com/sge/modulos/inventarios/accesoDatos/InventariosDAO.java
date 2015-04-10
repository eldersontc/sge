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
        recursos.add("com/sge/modulos/inventarios/mapeos/Producto.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/ProductoAlmacen.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/ProductoUnidad.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/EntradaInventario.hbm.xml");
        recursos.add("com/sge/modulos/inventarios/mapeos/ItemEntradaInventario.hbm.xml");
        return recursos;
    }
    
    public void AbrirSesion(){
        super.AbrirSesion(this.Recursos());
    }
    
    public void IniciarTransaccion(){
        super.IniciarTransaccion(this.Recursos());
    }
}
