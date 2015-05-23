package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.inventarios.entidades.SalidaInventario;
import java.util.List;

/**
 *
 * @author elderson
 */
public class SalidaInventarioDAO extends BaseDAO {
    
    public List<SalidaInventario> ObtenerSalidaInventarios(String filtro) {
        String sql = "SELECT \n"
                + "SalidaInventario.* \n"
                + "FROM \n"
                + "Inventarios.SalidaInventario AS SalidaInventario " + filtro;
        return super.ObtenerLista(sql, SalidaInventario.class);
    }
    
    public int EliminarSalidaInventario(int idSalidaInventario) {
        String sql = "DELETE FROM Inventarios.SalidaInventario WHERE idSalidaInventario = " + idSalidaInventario;
        return super.Ejecutar(sql);
    }
}
