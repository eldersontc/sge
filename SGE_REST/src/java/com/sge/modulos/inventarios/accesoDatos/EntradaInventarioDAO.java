package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.inventarios.entidades.EntradaInventario;
import java.util.List;

/**
 *
 * @author elderson
 */
public class EntradaInventarioDAO extends BaseDAO {
    
    public List<EntradaInventario> ObtenerEntradaInventarios(String filtro) {
        String sql = "SELECT \n"
                + "EntradaInventario.* \n"
                + "FROM \n"
                + "Inventarios.EntradaInventario AS EntradaInventario " + filtro;
        return super.ObtenerLista(sql, EntradaInventario.class);
    }
    
    public int EliminarEntradaInventario(int idEntradaInventario) {
        String sql = "DELETE FROM Inventarios.EntradaInventario WHERE idEntradaInventario = " + idEntradaInventario;
        return super.Ejecutar(sql);
    }
}
