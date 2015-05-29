package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.entidades.Mensaje;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author elderson
 */
public class MensajeDAO extends BaseDAO {

    public int ObtenerMensajesSinLeer(int idUsuarioDestino) {
        String sql = String.format("SELECT COUNT(*) FROM  Administracion.Mensaje WHERE idUsuarioDestino = %d AND leido = FALSE", idUsuarioDestino);
        return ((BigInteger) super.ObtenerValor(sql)).intValue();
    }

    public int ObtenerMensajesSinLeer(int idUsuarioOrigen, int idUsuarioDestino) {
        String sql = String.format("SELECT COUNT(*) FROM  Administracion.Mensaje WHERE idUsuarioOrigen = %d AND idUsuarioDestino = %d AND leido = FALSE", idUsuarioOrigen, idUsuarioDestino);
        return ((BigInteger) super.ObtenerValor(sql)).intValue();
    }

    public List<Mensaje> ObtenerMensajesPorUsuarioOrigenYDestino(int idUsuarioOrigen, int idUsuarioDestino){
        String sql = String.format("SELECT \n" +
                                    "	Mensaje.* \n" +
                                    "FROM \n" +
                                    "	Administracion.Mensaje AS Mensaje \n" +
                                    "WHERE \n" +
                                    "	(Mensaje.idUsuarioOrigen = %d AND Mensaje.idUsuarioDestino = %d) \n" +
                                    "	OR (Mensaje.idUsuarioOrigen = %d AND Mensaje.idUsuarioDestino = %d) \n" +
                                    "ORDER BY fechaEnvio ", idUsuarioOrigen, idUsuarioDestino, idUsuarioDestino, idUsuarioOrigen);
        return super.ObtenerLista(sql, Mensaje.class);
    }
    
    public int CambiarALeido(int idUsuarioOrigen, int idUsuarioDestino) {
        String sql = String.format("UPDATE Administracion.Mensaje SET leido = TRUE  WHERE idUsuarioOrigen = %d AND idUsuarioDestino = %d AND leido = FALSE", idUsuarioOrigen, idUsuarioDestino);
        return super.Ejecutar(sql);
    }
}
