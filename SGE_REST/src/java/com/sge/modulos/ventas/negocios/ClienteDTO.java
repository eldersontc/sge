package com.sge.modulos.ventas.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.ventas.accesoDatos.ClienteDAO;
import com.sge.modulos.ventas.accesoDatos.ContactoClienteDAO;
import com.sge.modulos.ventas.accesoDatos.DireccionClienteDAO;
import com.sge.modulos.ventas.entidades.Cliente;
import com.sge.modulos.ventas.entidades.ContactoCliente;
import com.sge.modulos.ventas.entidades.DireccionCliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ClienteDTO extends BaseDTO {

    ClienteDAO clienteDAO;
    DireccionClienteDAO direccionClienteDAO;
    ContactoClienteDAO contactoClienteDAO;

    public ClienteDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<Cliente> ObtenerClientes(String filtro) {
        List<Cliente> lista;
        try {
            clienteDAO = new ClienteDAO();
            clienteDAO.AbrirSesion();
            lista = clienteDAO.ObtenerClientes(getFiltro(clienteDAO, 24, filtro));
        } catch (Exception e) {
            throw e;
        } finally {
            clienteDAO.CerrarSesion();
        }
        return lista;
    }

    public Cliente ObtenerCliente(int idCliente) {
        Cliente cliente = null;
        try {
            clienteDAO = new ClienteDAO();
            clienteDAO.AbrirSesion();
            cliente = clienteDAO.ObtenerPorId(Cliente.class, idCliente);

            contactoClienteDAO = new ContactoClienteDAO();
            contactoClienteDAO.AsignarSesion(clienteDAO);

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idCliente", idCliente});
            List<ContactoCliente> contactos = contactoClienteDAO.ObtenerLista(ContactoCliente.class, filtros);
            cliente.setContactos(contactos);

            direccionClienteDAO = new DireccionClienteDAO();
            direccionClienteDAO.AsignarSesion(clienteDAO);

            filtros = new ArrayList<>();
            filtros.add(new Object[]{"idCliente", idCliente});
            List<DireccionCliente> direcciones = direccionClienteDAO.ObtenerLista(DireccionCliente.class, filtros);
            cliente.setDirecciones(direcciones);

        } catch (Exception e) {
            throw e;
        } finally {
            clienteDAO.CerrarSesion();
        }
        return cliente;
    }

    public boolean RegistrarCliente(Cliente cliente) {
        try {
            clienteDAO = new ClienteDAO();
            clienteDAO.IniciarTransaccion();
            clienteDAO.Agregar(cliente);

            contactoClienteDAO = new ContactoClienteDAO();
            contactoClienteDAO.AsignarSesion(clienteDAO);
            for (ContactoCliente contactoCliente : cliente.getContactos()) {
                contactoCliente.setIdCliente(cliente.getIdCliente());
                contactoClienteDAO.Agregar(contactoCliente);
            }

            direccionClienteDAO = new DireccionClienteDAO();
            direccionClienteDAO.AsignarSesion(clienteDAO);
            for (DireccionCliente direccionCliente : cliente.getDirecciones()) {
                direccionCliente.setIdCliente(cliente.getIdCliente());
                direccionClienteDAO.Agregar(direccionCliente);
            }

            clienteDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            clienteDAO.AbortarTransaccion();
            throw e;
        } finally {
            clienteDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarCliente(Cliente cliente) {
        try {
            clienteDAO = new ClienteDAO();
            clienteDAO.IniciarTransaccion();
            clienteDAO.ActualizarCliente(cliente);

            contactoClienteDAO = new ContactoClienteDAO();
            contactoClienteDAO.AsignarSesion(clienteDAO);
            for (ContactoCliente contactoCliente : cliente.getContactos()) {
                if (contactoCliente.isAgregar()) {
                    contactoCliente.setIdCliente(cliente.getIdCliente());
                    contactoClienteDAO.Agregar(contactoCliente);
                }
                if (contactoCliente.isActualizar()) {
                    contactoClienteDAO.ActualizarContactoCliente(contactoCliente.getIdContactoCliente(), contactoCliente.getNombre(), contactoCliente.getCargo(), contactoCliente.getTelefono(), contactoCliente.getCorreo());
                }
                if (contactoCliente.isEliminar()) {
                    contactoClienteDAO.EliminarContactoCliente(contactoCliente.getIdContactoCliente());
                }
            }

            direccionClienteDAO = new DireccionClienteDAO();
            direccionClienteDAO.AsignarSesion(clienteDAO);
            for (DireccionCliente direccionCliente : cliente.getDirecciones()) {
                if (direccionCliente.isAgregar()) {
                    direccionCliente.setIdCliente(cliente.getIdCliente());
                    direccionClienteDAO.Agregar(direccionCliente);
                }
                if (direccionCliente.isActualizar()) {
                    direccionClienteDAO.ActualizarDireccionCliente(direccionCliente.getIdDireccionCliente(), direccionCliente.getIdDepartamento(), direccionCliente.getNombreDepartamento(), direccionCliente.getIdProvincia(), direccionCliente.getNombreProvincia(), direccionCliente.getIdDistrito(), direccionCliente.getNombreDistrito(), direccionCliente.getDireccion());
                }
                if (direccionCliente.isEliminar()) {
                    direccionClienteDAO.EliminarDireccionCliente(direccionCliente.getIdDireccionCliente());
                }
            }

            clienteDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            clienteDAO.AbortarTransaccion();
            throw e;
        } finally {
            clienteDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarCliente(int idCliente) {
        try {
            clienteDAO = new ClienteDAO();
            clienteDAO.IniciarTransaccion();
            clienteDAO.EliminarCliente(idCliente);

            contactoClienteDAO = new ContactoClienteDAO();
            contactoClienteDAO.AsignarSesion(clienteDAO);
            contactoClienteDAO.EliminarContactoClientePorIdCliente(idCliente);

            direccionClienteDAO = new DireccionClienteDAO();
            direccionClienteDAO.AsignarSesion(clienteDAO);
            direccionClienteDAO.EliminarDireccionClientePorIdCliente(idCliente);

            clienteDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            clienteDAO.AbortarTransaccion();
            throw e;
        } finally {
            clienteDAO.CerrarSesion();
        }
        return true;
    }

    public List<ContactoCliente> ObtenerContactosCliente(String filtro) {
        List<ContactoCliente> lista;
        try {
            contactoClienteDAO = new ContactoClienteDAO();
            contactoClienteDAO.AbrirSesion();
            lista = contactoClienteDAO.ObtenerContactosCliente(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            contactoClienteDAO.CerrarSesion();
        }
        return lista;
    }

    public List<DireccionCliente> ObtenerDireccionesCliente(String filtro) {
        List<DireccionCliente> lista;
        try {
            direccionClienteDAO = new DireccionClienteDAO();
            direccionClienteDAO.AbrirSesion();
            lista = direccionClienteDAO.ObtenerDireccionesCliente(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            direccionClienteDAO.CerrarSesion();
        }
        return lista;
    }
}
