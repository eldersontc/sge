package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.inventarios.entidades.Producto;
import com.sge.modulos.inventarios.entidades.SeleccionProducto;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ProductoDAO extends BaseDAO {

    public List<Producto> ObtenerProductos(String filtro) {
        String sql = "SELECT \n"
                + "Producto.* \n"
                + "FROM \n"
                + "Inventarios.Producto AS Producto " + filtro;
        return super.ObtenerLista(sql, Producto.class);
    }

    public List<SeleccionProducto> ObtenerProductosPorAlmacen(String filtro) {
        String sql = "SELECT \n"
                + "	Producto.idProducto, Producto.codigo AS codigoProducto, Producto.descripcion AS descripcionProducto, \n"
                + "	Producto.idUnidadBase, Producto.abreviacionUnidadBase, Producto.factorUnidadBase, Producto.costoUltimaCompra,\n"
                + "	Producto.costoPromedio, Producto.costoReferencia, ProductoAlmacen.stockFisico AS stock\n"
                + "FROM \n"
                + "	Inventarios.Producto AS Producto INNER JOIN \n"
                + "	Inventarios.ProductoAlmacen AS ProductoAlmacen \n"
                + "	ON Producto.idProducto = ProductoAlmacen.idProducto " + filtro;
        return super.ObtenerLista(sql, SeleccionProducto.class);
    }

    public int ActualizarProducto(Producto producto) {
        String sql = String.format("UPDATE Inventarios.Producto SET codigo = '%s', descripcion = '%s', inventarios = %b, compras = %b, ventas = %b, alto = %s, largo = %s, idUnidadBase = %d, abreviacionUnidadBase = '%s', factorUnidadBase = %d, activo = %b WHERE idProducto = %d", producto.getCodigo(), producto.getDescripcion(), producto.isInventarios(), producto.isCompras(), producto.isVentas(), producto.getAlto(), producto.getLargo(), producto.getIdUnidadBase(), producto.getAbreviacionUnidadBase(), producto.getFactorUnidadBase(), producto.isActivo(), producto.getIdProducto());
        return super.Ejecutar(sql);
    }

    public int EliminarProducto(int idProducto) {
        String sql = "DELETE FROM Inventarios.Producto WHERE idProducto = " + idProducto;
        return super.Ejecutar(sql);
    }
}
