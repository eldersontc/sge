package com.sge.modulos.inventarios.accesoDatos;

import java.util.List;

/**
 *
 * @author elderson
 */
public class EntradaInventarioDAO extends InventariosDAO {
    
    public List<Object[]> ObtenerEntradaInventarios() {
        String sql = "SELECT \n"
                + "EntradaInventario.idEntradaInventario, EntradaInventario.numero, EntradaInventario.fechaCreacion, \n"
                + "EntradaInventario.razonSocialProveedor, EntradaInventario.nombreResponsable, EntradaInventario.simboloMoneda, \n"
                + "EntradaInventario.total \n"
                + "FROM \n"
                + "Inventarios.EntradaInventario \n";
        return super.ObtenerLista(sql);
    }
    
    public int EliminarEntradaInventario(int idEntradaInventario) {
        String sql = "DELETE FROM Inventarios.EntradaInventario WHERE idEntradaInventario = " + idEntradaInventario;
        return super.Ejecutar(sql);
    }
}
