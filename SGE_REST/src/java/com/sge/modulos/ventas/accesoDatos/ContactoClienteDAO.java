package com.sge.modulos.ventas.accesoDatos;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.ventas.entidades.ContactoCliente;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ContactoClienteDAO extends BaseDAO {

    public List<ContactoCliente> ObtenerContactosCliente(String filtro) {
        String sql = "SELECT "
                + "ContactoCliente.* "
                + "FROM "
                + "Ventas.ContactoCliente AS ContactoCliente " + filtro;
        return super.ObtenerLista(sql, ContactoCliente.class);
    }

    public int ActualizarContactoCliente(int idContactoCliente, String nombre, String cargo, String telefono, String correo) {
        String sql = String.format("UPDATE Ventas.ContactoCliente SET nombre = '%s', cargo = '%s', telefono = '%s', correo = '%s' WHERE idContactoCliente = %d", nombre, cargo, telefono, correo, idContactoCliente);
        return super.Ejecutar(sql);
    }

    public int EliminarContactoCliente(int idContactoCliente) {
        String sql = "DELETE FROM Ventas.ContactoCliente WHERE idContactoCliente = " + idContactoCliente;
        return super.Ejecutar(sql);
    }

    public int EliminarContactoClientePorIdCliente(int idCliente) {
        String sql = "DELETE FROM Ventas.ContactoCliente WHERE idCliente = " + idCliente;
        return super.Ejecutar(sql);
    }
}
