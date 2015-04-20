package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SalidaInventarioDAO extends BaseDAO {
    
    public List<Object[]> ObtenerSalidaInventarios() {
        String sql = "SELECT \n"
                + "SalidaInventario.idSalidaInventario, SalidaInventario.numero, SalidaInventario.fechaCreacion, \n"
                + "SalidaInventario.razonSocialCliente, SalidaInventario.nombreResponsable, SalidaInventario.simboloMoneda, \n"
                + "SalidaInventario.total \n"
                + "FROM \n"
                + "Inventarios.SalidaInventario \n";
        return super.ObtenerLista(sql);
    }
    
    public int EliminarSalidaInventario(int idSalidaInventario) {
        String sql = "DELETE FROM Inventarios.SalidaInventario WHERE idSalidaInventario = " + idSalidaInventario;
        return super.Ejecutar(sql);
    }
}
