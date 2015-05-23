package com.sge.modulos.finanzas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.finanzas.entidades.EntradaCaja;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntradaCajaDAO extends BaseDAO {
    
    public List<EntradaCaja> ObtenerEntradasCaja(String filtro) {
        String sql = "SELECT \n"
                + "EntradaCaja.* \n"
                + "FROM \n"
                + "Finanzas.EntradaCaja AS EntradaCaja " + filtro;
        return super.ObtenerLista(sql, EntradaCaja.class);
    }

    public int EliminarEntradaCaja(int idEntradaCaja) {
        String sql = "DELETE FROM Finanzas.EntradaCaja WHERE idEntradaCaja = " + idEntradaCaja;
        return super.Ejecutar(sql);
    }
}
