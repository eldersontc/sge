package com.sge.modulos.finanzas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.finanzas.entidades.SalidaCaja;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SalidaCajaDAO extends BaseDAO {
    
    public List<SalidaCaja> ObtenerSalidasCaja(String filtro) {
        String sql = "SELECT \n"
                + "SalidaCaja.* \n"
                + "FROM \n"
                + "Finanzas.SalidaCaja " + filtro;
        return super.ObtenerLista(sql, SalidaCaja.class);
    }

    public int EliminarSalidaCaja(int idSalidaCaja) {
        String sql = "DELETE FROM Finanzas.SalidaCaja WHERE idSalidaCaja = " + idSalidaCaja;
        return super.Ejecutar(sql);
    }
}
