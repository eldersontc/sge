package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ServicioMaquinaDAO extends BaseDAO {

    public int EliminarServicioMaquina(int idServicioMaquina) {
        String sql = "DELETE FROM Ventas.ServicioMaquina WHERE idServicioMaquina = " + idServicioMaquina;
        return super.Ejecutar(sql);
    }

    public int EliminarServicioMaquinaPorIdServicio(int idServicio) {
        String sql = "DELETE FROM Ventas.ServicioMaquina WHERE idServicio = " + idServicio;
        return super.Ejecutar(sql);
    }
}
