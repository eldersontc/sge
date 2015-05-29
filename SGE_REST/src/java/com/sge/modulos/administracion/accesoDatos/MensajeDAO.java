package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.math.BigInteger;

/**
 *
 * @author elderson
 */
public class MensajeDAO extends BaseDAO {

    public int ObtenerMensajesSinLeer(int idUsuarioDestino) {
        String sql = String.format("SELECT COUNT(*) FROM  Administracion.Mensaje WHERE idUsuarioDestino = %d AND leido = TRUE", idUsuarioDestino);
        return ((BigInteger) super.ObtenerValor(sql)).intValue();
    }

    public int ObtenerMensajesSinLeer(int idUsuarioOrigen, int idUsuarioDestino) {
        String sql = String.format("SELECT COUNT(*) FROM  Administracion.Mensaje WHERE idUsuarioOrigen = %d, idUsuarioDestino = %d AND leido = TRUE", idUsuarioOrigen, idUsuarioDestino);
        return ((BigInteger) super.ObtenerValor(sql)).intValue();
    }

    public int CambiarALeido(int idUsuarioOrigen, int idUsuarioDestino) {
        String sql = String.format("UPDATE Administracion.Mensaje SET leido = TRUE  WHERE idUsuarioOrigen = %d AND idUsuarioDestino = %d AND leido = FALSE", idUsuarioOrigen, idUsuarioDestino);
        return super.Ejecutar(sql);
    }
}
