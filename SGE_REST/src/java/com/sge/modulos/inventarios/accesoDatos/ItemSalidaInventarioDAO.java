package com.sge.modulos.inventarios.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;

/**
 *
 * @author elderson
 */
public class ItemSalidaInventarioDAO extends BaseDAO {
    
    public int EliminarItemSalidaInventarioPorIdSalidaInventario(int idSalidaInventario) {
        String sql = "DELETE FROM Inventarios.ItemSalidaInventario WHERE idSalidaInventario = " + idSalidaInventario;
        return super.Ejecutar(sql);
    }
}
