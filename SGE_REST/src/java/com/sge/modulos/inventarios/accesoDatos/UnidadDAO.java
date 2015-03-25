package com.sge.modulos.inventarios.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class UnidadDAO extends InventariosDAO {

    public List<Object[]> ObtenerUnidades() {
        String sql = "SELECT \n"
                + "Unidad.idUnidad, Unidad.abreviacion, Unidad.descripcion, Unidad.activo \n"
                + "FROM \n"
                + "Inventarios.Unidad \n";
        return super.ObtenerLista(sql);
    }

    public int ActualizarUnidad(int idUnidad, String abreviacion, String descripcion, boolean activo) {
        String sql = String.format("UPDATE Inventarios.Unidad SET abreviacion = '%s', descripcion = '%s', activo = %b WHERE idUnidad = %d", abreviacion, descripcion, activo, idUnidad);
        return super.Ejecutar(sql);
    }
    
    public int EliminarUnidad(int idUnidad) {
        String sql = "DELETE FROM Inventarios.Unidad WHERE idUnidad = " + idUnidad;
        return super.Ejecutar(sql);
    }
}
