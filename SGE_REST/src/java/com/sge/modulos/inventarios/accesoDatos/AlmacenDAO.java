package com.sge.modulos.inventarios.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class AlmacenDAO extends InventariosDAO {

    public List<Object[]> ObtenerAlmacenes() {
        String sql = "SELECT \n"
                + "Almacen.idAlmacen, Almacen.codigo, Almacen.descripcion, Almacen.activo \n"
                + "FROM \n"
                + "Inventarios.Almacen \n";
        return super.ObtenerLista(sql);
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
