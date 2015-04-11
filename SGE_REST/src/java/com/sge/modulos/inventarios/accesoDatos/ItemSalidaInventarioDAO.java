package com.sge.modulos.inventarios.accesoDatos;

/**
 *
 * @author elderson
 */
public class ItemSalidaInventarioDAO extends InventariosDAO {
    
    public int EliminarItemSalidaInventarioPorIdSalidaInventario(int idSalidaInventario) {
        String sql = "DELETE FROM Inventarios.ItemSalidaInventario WHERE idSalidaInventario = " + idSalidaInventario;
        return super.Ejecutar(sql);
    }
}
