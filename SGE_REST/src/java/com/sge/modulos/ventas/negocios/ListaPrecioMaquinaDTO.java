package com.sge.modulos.ventas.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.ventas.accesoDatos.EscalaListaPrecioMaquinaDAO;
import com.sge.modulos.ventas.accesoDatos.ListaPrecioMaquinaDAO;
import com.sge.modulos.ventas.accesoDatos.ItemListaPrecioMaquinaDAO;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioMaquina;
import com.sge.modulos.ventas.entidades.ListaPrecioMaquina;
import com.sge.modulos.ventas.entidades.ItemListaPrecioMaquina;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioMaquinaDTO extends BaseDTO {

    ListaPrecioMaquinaDAO listaPrecioDAO;
    ItemListaPrecioMaquinaDAO itemListaPrecioDAO;
    EscalaListaPrecioMaquinaDAO escalaListaPrecioDAO;

    public ListaPrecioMaquinaDTO(int idUsuario) {
        super(idUsuario);
    }

    public List<ListaPrecioMaquina> ObtenerListasPrecio(String filtro) {
        List<ListaPrecioMaquina> lista;
        try {
            listaPrecioDAO = new ListaPrecioMaquinaDAO();
            listaPrecioDAO.AbrirSesion();
            lista = listaPrecioDAO.ObtenerListasPrecio(getFiltro(listaPrecioDAO, 26, filtro));
        } catch (Exception e) {
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return lista;
    }

    public ListaPrecioMaquina ObtenerListaPrecioMaquina(int idListaPrecioMaquina) {
        ListaPrecioMaquina listaPrecio = null;
        try {
            listaPrecioDAO = new ListaPrecioMaquinaDAO();
            listaPrecioDAO.AbrirSesion();
            listaPrecio = listaPrecioDAO.ObtenerPorId(ListaPrecioMaquina.class, idListaPrecioMaquina);

            itemListaPrecioDAO = new ItemListaPrecioMaquinaDAO();
            itemListaPrecioDAO.AsignarSesion(listaPrecioDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idListaPrecioMaquina", idListaPrecioMaquina});
            List<ItemListaPrecioMaquina> items = itemListaPrecioDAO.ObtenerLista(ItemListaPrecioMaquina.class, filtros);
            listaPrecio.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return listaPrecio;
    }

    public List<EscalaListaPrecioMaquina> ObtenerEscalasListaPrecioMaquina(int idItemListaPrecioMaquina) {
        List<EscalaListaPrecioMaquina> escalas = null;
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioMaquinaDAO();
            escalaListaPrecioDAO.AbrirSesion();
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idItemListaPrecioMaquina", idItemListaPrecioMaquina});
            escalas = escalaListaPrecioDAO.ObtenerLista(EscalaListaPrecioMaquina.class, filtros);
        } catch (Exception e) {
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return escalas;
    }

    public boolean RegistrarListaPrecioMaquina(ListaPrecioMaquina listaPrecio) {
        try {
            listaPrecioDAO = new ListaPrecioMaquinaDAO();
            listaPrecioDAO.IniciarTransaccion();
            listaPrecioDAO.Agregar(listaPrecio);
            listaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            listaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public ItemListaPrecioMaquina[] RegistrarItemsListaPrecioMaquina(ItemListaPrecioMaquina[] itemsListaPrecio) {
        try {
            itemListaPrecioDAO = new ItemListaPrecioMaquinaDAO();
            itemListaPrecioDAO.IniciarTransaccion();
            for (ItemListaPrecioMaquina itemListaPrecio : itemsListaPrecio) {
                itemListaPrecioDAO.Agregar(itemListaPrecio);
            }
            itemListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            itemListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            itemListaPrecioDAO.CerrarSesion();
        }
        return itemsListaPrecio;
    }

    public boolean RegistrarEscalaListaPrecioMaquina(EscalaListaPrecioMaquina escalaListaPrecio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioMaquinaDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.Agregar(escalaListaPrecio);
            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarListaPrecioMaquina(ListaPrecioMaquina listaPrecio) {
        try {
            listaPrecioDAO = new ListaPrecioMaquinaDAO();
            listaPrecioDAO.IniciarTransaccion();
            listaPrecioDAO.ActualizarListaPrecioMaquina(listaPrecio.getIdListaPrecioMaquina(), listaPrecio.getNombre(), listaPrecio.isActivo());
            listaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            listaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarItemListaPrecioMaquina(ItemListaPrecioMaquina itemListaPrecio) {
        try {
            itemListaPrecioDAO = new ItemListaPrecioMaquinaDAO();
            itemListaPrecioDAO.IniciarTransaccion();
            itemListaPrecioDAO.ActualizarItemListaPrecioMaquina(itemListaPrecio.getIdItemListaPrecioMaquina(), itemListaPrecio.getFactor());
            itemListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            itemListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            itemListaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarEscalaListaPrecioMaquina(EscalaListaPrecioMaquina escalaListaPrecio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioMaquinaDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.ActualizarEscalaListaPrecioMaquina(escalaListaPrecio.getIdEscalaListaPrecioMaquina(), escalaListaPrecio.getDesde(), escalaListaPrecio.getHasta(), escalaListaPrecio.getPrecio());
            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarListaPrecioMaquina(int idListaPrecioMaquina) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioMaquinaDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioMaquinaPorIdListaPrecioMaquina(idListaPrecioMaquina);

            itemListaPrecioDAO = new ItemListaPrecioMaquinaDAO();
            itemListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            itemListaPrecioDAO.EliminarItemListaPrecioMaquinaPorIdListaPrecioMaquina(idListaPrecioMaquina);

            listaPrecioDAO = new ListaPrecioMaquinaDAO();
            listaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            listaPrecioDAO.EliminarListaPrecioMaquina(idListaPrecioMaquina);

            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarItemListaPrecioMaquina(int idItemListaPrecioMaquina) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioMaquinaDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioMaquinaPorIdItemListaPrecioMaquina(idItemListaPrecioMaquina);

            itemListaPrecioDAO = new ItemListaPrecioMaquinaDAO();
            itemListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            itemListaPrecioDAO.EliminarItemListaPrecioMaquina(idItemListaPrecioMaquina);

            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarEscalaListaPrecioMaquina(int idEscalaListaPrecioMaquina) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioMaquinaDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioMaquina(idEscalaListaPrecioMaquina);
            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public List<ItemListaPrecioMaquina> ObtenerEscalasPorMaquina(int idListaPrecioMaquina, int idMaquina) {
        List<ItemListaPrecioMaquina> items = new ArrayList<>();
        try {
            itemListaPrecioDAO = new ItemListaPrecioMaquinaDAO();
            itemListaPrecioDAO.AbrirSesion();

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idListaPrecioMaquina", idListaPrecioMaquina});
            filtros.add(new Object[]{"idMaquina", idMaquina});
            items = itemListaPrecioDAO.ObtenerLista(ItemListaPrecioMaquina.class, filtros);

            escalaListaPrecioDAO = new EscalaListaPrecioMaquinaDAO();
            escalaListaPrecioDAO.AsignarSesion(itemListaPrecioDAO);

            for (ItemListaPrecioMaquina item : items) {
                filtros = new ArrayList<>();
                filtros.add(new Object[]{"idItemListaPrecioMaquina", item.getIdItemListaPrecioMaquina()});
                List<EscalaListaPrecioMaquina> escalas = escalaListaPrecioDAO.ObtenerEscalasPorMaquina(idListaPrecioMaquina, idMaquina);
                item.setEscalas(escalas);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            itemListaPrecioDAO.CerrarSesion();
        }
        return items;
    }
}
