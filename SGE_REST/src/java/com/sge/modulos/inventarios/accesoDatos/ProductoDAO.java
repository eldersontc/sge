package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoDAO extends BaseDAO {
    
    public List<Object[]> ObtenerProductos() {
        String sql = "SELECT \n"
                + "Producto.idProducto, Producto.codigo, Producto.descripcion, Producto.alto, Producto.largo, Producto.activo \n"
                + "FROM \n"
                + "Inventarios.Producto \n";
        return super.ObtenerLista(sql);
    }

    public int ActualizarProducto(int idProducto, String codigo, String descripcion, boolean inventarios, boolean compras, boolean ventas, double alto, double largo, boolean activo) {
        String sql = String.format("UPDATE Inventarios.Producto SET codigo = '%s', descripcion = '%s', inventarios = %b, compras = %b, ventas = %b, alto = %s, largo = %s, activo = %b WHERE idProducto = %d", codigo, descripcion, inventarios, compras, ventas, alto, largo, activo, idProducto);
        return super.Ejecutar(sql);
    }
    
    public int EliminarProducto(int idProducto) {
        String sql = "DELETE FROM Inventarios.Producto WHERE idProducto = " + idProducto;
        return super.Ejecutar(sql);
    }
}
