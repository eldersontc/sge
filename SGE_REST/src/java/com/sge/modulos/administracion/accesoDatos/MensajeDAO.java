package com.sge.modulos.administracion.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.math.BigInteger;

/**
 *
 * @author elderson
 */
public class MensajeDAO extends BaseDAO {
    
    public int ObtenerMensajesSinLeer(int idUsuarioDestino){
        String sql = "SELECT nextval ('Ventas.Grupo_Seq')";
        return ((BigInteger) super.ObtenerValor(sql)).intValue();
    }
    
    public int ObtenerMensajesSinLeer(int idUsuarioOrigen, int idUsuarioDestino){
        String sql = "SELECT nextval ('Ventas.Grupo_Seq')";
        return ((BigInteger) super.ObtenerValor(sql)).intValue();
    }
}