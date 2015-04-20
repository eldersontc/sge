package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemEntradaInventarioDAO extends BaseDAO {
    
    public int EliminarItemEntradaInventarioPorIdEntradaInventario(int idEntradaInventario) {
        String sql = "DELETE FROM Inventarios.ItemEntradaInventario WHERE idEntradaInventario = " + idEntradaInventario;
        return super.Ejecutar(sql);
    }
}
