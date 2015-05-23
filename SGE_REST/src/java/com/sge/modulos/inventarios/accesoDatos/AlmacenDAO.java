package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.inventarios.entidades.Almacen;
import java.util.List;

/**
 *
 * @author elderson
 */
public class AlmacenDAO extends BaseDAO {

    public List<Almacen> ObtenerAlmacenes(String filtro) {
        String sql = "SELECT \n"
                + "Almacen.idAlmacen, Almacen.codigo, Almacen.descripcion, Almacen.activo \n"
                + "FROM \n"
                + "Inventarios.Almacen AS Almacen " + filtro;
        return super.ObtenerLista(sql, Almacen.class);
    }

    public int ActualizarAlmacen(int idAlmacen, String codigo, String descripcion, boolean activo) {
        String sql = String.format("UPDATE Inventarios.Almacen SET codigo = '%s', descripcion = '%s', activo = %b WHERE idAlmacen = %d", codigo, descripcion, activo, idAlmacen);
        return super.Ejecutar(sql);
    }

    public int EliminarAlmacen(int idAlmacen) {
        String sql = "DELETE FROM Inventarios.Almacen WHERE idAlmacen = " + idAlmacen;
        return super.Ejecutar(sql);
    }
}
