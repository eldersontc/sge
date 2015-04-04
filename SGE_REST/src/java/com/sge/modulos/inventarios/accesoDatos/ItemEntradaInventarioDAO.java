package com.sge.modulos.inventarios.accesoDatos;

/**
 *
 * @author elderson
 */
public class ItemEntradaInventarioDAO extends InventariosDAO {
    
    public int EliminarItemEntradaInventarioPorIdEntradaInventario(int idEntradaInventario) {
        String sql = "DELETE FROM Inventarios.ItemEntradaInventario WHERE idEntradaInventario = " + idEntradaInventario;
        return super.Ejecutar(sql);
    }
}
