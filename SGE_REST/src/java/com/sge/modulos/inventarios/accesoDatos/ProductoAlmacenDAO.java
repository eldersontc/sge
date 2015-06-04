package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.inventarios.entidades.ProductoAlmacen;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoAlmacenDAO extends BaseDAO {
    
    public List<ProductoAlmacen> ObtenerProductoAlmacenes(String filtro) {
        String sql = "SELECT \n"
                + "ProductoAlmacen.* \n"
                + "FROM \n"
                + "Inventarios.ProductoAlmacen AS ProductoAlmacen " + filtro;
        return super.ObtenerLista(sql, ProductoAlmacen.class);
    }
    
    public void ActualizarStockFisico(int idProducto, int idAlmacen, double cantidad){
        String sql = String.format("Inventarios.Sp_Actualizar_Stock_Fisico(%d, %d, %s)", idProducto, idAlmacen, cantidad);
        super.EjecutarFuncion(sql);
    }
    
    public int EliminarProductoAlmacen(int idProductoAlmacen) {
        String sql = "DELETE FROM Inventarios.ProductoAlmacen WHERE idProductoAlmacen = " + idProductoAlmacen;
        return super.Ejecutar(sql);
    }
    
    public int EliminarProductoAlmacenPorIdProducto(int idProducto) {
        String sql = "DELETE FROM Inventarios.ProductoAlmacen WHERE idProducto = " + idProducto;
        return super.Ejecutar(sql);
    }
}
