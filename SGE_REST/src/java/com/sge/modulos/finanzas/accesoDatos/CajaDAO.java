package com.sge.modulos.finanzas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.finanzas.entidades.Caja;
import java.util.List;

/**
 *
 * @author elderson
 */
public class CajaDAO extends BaseDAO {
    
    public List<Caja> ObtenerCajas(String filtro) {
        String sql = "SELECT \n"
                + "Caja.* \n"
                + "FROM \n"
                + "Finanzas.Caja " + filtro;
        return super.ObtenerLista(sql, Caja.class);
    }

    public int ActualizarCaja(int idCaja, String descripcion, int idMoneda, String simboloMoneda, boolean activo) {
        String sql = String.format("UPDATE Finanzas.Caja SET descripcion = '%s', idMoneda = %d, simboloMoneda = '%s', activo = %b WHERE idCaja = %d", descripcion, idMoneda, simboloMoneda, activo, idCaja);
        return super.Ejecutar(sql);
    }

    public int EliminarCaja(int idCaja) {
        String sql = "DELETE FROM Finanzas.Caja WHERE idCaja = " + idCaja;
        return super.Ejecutar(sql);
    }
}
