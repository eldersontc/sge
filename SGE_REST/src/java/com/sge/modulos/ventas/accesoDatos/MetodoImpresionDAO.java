package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MetodoImpresionDAO extends BaseDAO {
    
    public List<Object[]> ObtenerMetodosImpresion(String filtro) {
        String sql = "SELECT \n"
                + "MetodoImpresion.idMetodoImpresion, MetodoImpresion.nombre, MetodoImpresion.factorPases, MetodoImpresion.factorCambios, MetodoImpresion.activo \n"
                + "FROM \n"
                + "Ventas.MetodoImpresion " + filtro;
        return super.ObtenerLista(sql);
    }

    public int ActualizarMetodoImpresion(int idMetodoImpresion, String nombre, int factorPases, int factorCambios, boolean activo) {
        String sql = String.format("UPDATE Ventas.MetodoImpresion SET nombre = '%s', factorPases = %d, factorCambios = %d, activo = %b WHERE idMetodoImpresion = %d", nombre, factorPases, factorCambios, activo, idMetodoImpresion);
        return super.Ejecutar(sql);
    }

    public int EliminarMetodoImpresion(int idMetodoImpresion) {
        String sql = "DELETE FROM Ventas.MetodoImpresion WHERE idMetodoImpresion = " + idMetodoImpresion;
        return super.Ejecutar(sql);
    }
}
