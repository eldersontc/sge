package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.MetodoImpresion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MetodoImpresionDAO extends BaseDAO {

    public List<MetodoImpresion> ObtenerMetodosImpresion(String filtro) {
        String sql = "SELECT \n"
                + "MetodoImpresion.* \n"
                + "FROM \n"
                + "Ventas.MetodoImpresion AS MetodoImpresion " + filtro;
        return super.ObtenerLista(sql, MetodoImpresion.class);
    }

    public int ActualizarMetodoImpresion(int idMetodoImpresion, String nombre, int factorPases, int factorCambios, int factorHorizontal, int factorVertical, String letras, boolean activo) {
        String sql = String.format("UPDATE Ventas.MetodoImpresion SET nombre = '%s', factorPases = %d, factorCambios = %d, factorHorizontal = %d, factorVertical = %d, letras = '%s', activo = %b WHERE idMetodoImpresion = %d", nombre, factorPases, factorCambios, factorHorizontal, factorVertical, letras, activo, idMetodoImpresion);
        return super.Ejecutar(sql);
    }

    public int EliminarMetodoImpresion(int idMetodoImpresion) {
        String sql = "DELETE FROM Ventas.MetodoImpresion WHERE idMetodoImpresion = " + idMetodoImpresion;
        return super.Ejecutar(sql);
    }
}
