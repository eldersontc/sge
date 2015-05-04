package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.EscalaListaPrecioServicioDAO;
import com.sge.modulos.ventas.accesoDatos.ItemListaPrecioServicioDAO;
import com.sge.modulos.ventas.accesoDatos.ListaPrecioServicioDAO;
import com.sge.modulos.ventas.accesoDatos.UnidadListaPrecioServicioDAO;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioServicio;
import com.sge.modulos.ventas.entidades.ItemListaPrecioServicio;
import com.sge.modulos.ventas.entidades.ListaPrecioServicio;
import com.sge.modulos.ventas.entidades.UnidadListaPrecioServicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioServicioDTO {
    
    ListaPrecioServicioDAO listaPrecioDAO;
    ItemListaPrecioServicioDAO itemListaPrecioDAO;
    UnidadListaPrecioServicioDAO unidadListaPrecioDAO;
    EscalaListaPrecioServicioDAO escalaListaPrecioDAO;
    
    public List<Object[]> ObtenerListasPrecio(String filtro) {
        List<Object[]> lista;
        try {
            listaPrecioDAO = new ListaPrecioServicioDAO();
            listaPrecioDAO.AbrirSesion();
            lista = listaPrecioDAO.ObtenerListasPrecio(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return lista;
    }

    public ListaPrecioServicio ObtenerListaPrecioServicio(int idListaPrecioServicio) {
        ListaPrecioServicio listaPrecio = null;
        try {
            listaPrecioDAO = new ListaPrecioServicioDAO();
            listaPrecioDAO.AbrirSesion();
            listaPrecio = listaPrecioDAO.ObtenerPorId(ListaPrecioServicio.class, idListaPrecioServicio);
            
            itemListaPrecioDAO = new ItemListaPrecioServicioDAO();
            itemListaPrecioDAO.AsignarSesion(listaPrecioDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idListaPrecioServicio", idListaPrecioServicio});
            List<ItemListaPrecioServicio> items = itemListaPrecioDAO.ObtenerLista(ItemListaPrecioServicio.class, filtros);
            listaPrecio.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return listaPrecio;
    }

    public List<UnidadListaPrecioServicio> ObtenerUnidadesListaPrecioServicio(int idItemListaPrecioServicio) {
        List<UnidadListaPrecioServicio> unidades = null;
        try {
            unidadListaPrecioDAO = new UnidadListaPrecioServicioDAO();
            unidadListaPrecioDAO.AbrirSesion();
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idItemListaPrecioServicio", idItemListaPrecioServicio});
            unidades = unidadListaPrecioDAO.ObtenerLista(UnidadListaPrecioServicio.class, filtros);
        } catch (Exception e) {
            throw e;
        } finally {
            unidadListaPrecioDAO.CerrarSesion();
        }
        return unidades;
    }
    
    public List<EscalaListaPrecioServicio> ObtenerEscalasListaPrecioServicio(int idUnidadListaPrecioServicio) {
        List<EscalaListaPrecioServicio> escalas = null;
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioServicioDAO();
            escalaListaPrecioDAO.AbrirSesion();
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idUnidadListaPrecioServicio", idUnidadListaPrecioServicio});
            escalas = escalaListaPrecioDAO.ObtenerLista(EscalaListaPrecioServicio.class, filtros);
        } catch (Exception e) {
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return escalas;
    }
    
    public boolean RegistrarListaPrecioServicio(ListaPrecioServicio listaPrecio) {
        try {
            listaPrecioDAO = new ListaPrecioServicioDAO();
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
    
    public ItemListaPrecioServicio[] RegistrarItemsListaPrecioServicio(ItemListaPrecioServicio[] itemsListaPrecio) {
        try {
            itemListaPrecioDAO = new ItemListaPrecioServicioDAO();
            itemListaPrecioDAO.IniciarTransaccion();
            for (ItemListaPrecioServicio itemListaPrecio : itemsListaPrecio) {
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
    
    public UnidadListaPrecioServicio[] RegistrarUnidadesListaPrecioServicio(UnidadListaPrecioServicio[] unidadesListaPrecio) {
        try {
            unidadListaPrecioDAO = new UnidadListaPrecioServicioDAO();
            unidadListaPrecioDAO.IniciarTransaccion();
            for (UnidadListaPrecioServicio unidadListaPrecio : unidadesListaPrecio) {
                unidadListaPrecioDAO.Agregar(unidadListaPrecio);
            }
            unidadListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            unidadListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            unidadListaPrecioDAO.CerrarSesion();
        }
        return unidadesListaPrecio;
    }
    
    public boolean RegistrarEscalaListaPrecioServicio(EscalaListaPrecioServicio escalaListaPrecio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioServicioDAO();
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

    public boolean ActualizarListaPrecioServicio(ListaPrecioServicio listaPrecio) {
        try {
            listaPrecioDAO = new ListaPrecioServicioDAO();
            listaPrecioDAO.IniciarTransaccion();
            listaPrecioDAO.ActualizarListaPrecioServicio(listaPrecio.getIdListaPrecioServicio(), listaPrecio.getNombre(), listaPrecio.isActivo());
            listaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            listaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarEscalaListaPrecioServicio(EscalaListaPrecioServicio escalaListaPrecio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioServicioDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.ActualizarEscalaListaPrecioServicio(escalaListaPrecio.getIdEscalaListaPrecioServicio(), escalaListaPrecio.getDesde(), escalaListaPrecio.getHasta(), escalaListaPrecio.getPrecio());
            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarListaPrecioServicio(int idListaPrecioServicio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioServicioDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioServicioPorIdListaPrecioServicio(idListaPrecioServicio);
            
            unidadListaPrecioDAO = new UnidadListaPrecioServicioDAO();
            unidadListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            unidadListaPrecioDAO.EliminarUnidadListaPrecioServicioPorIdListaPrecioServicio(idListaPrecioServicio);
            
            itemListaPrecioDAO = new ItemListaPrecioServicioDAO();
            itemListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            itemListaPrecioDAO.EliminarItemListaPrecioServicioPorIdListaPrecioServicio(idListaPrecioServicio);
            
            listaPrecioDAO = new ListaPrecioServicioDAO();
            listaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            listaPrecioDAO.EliminarListaPrecioServicio(idListaPrecioServicio);

            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarItemListaPrecioServicio(int idItemListaPrecioServicio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioServicioDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioServicioPorIdItemListaPrecioServicio(idItemListaPrecioServicio);
            
            unidadListaPrecioDAO = new UnidadListaPrecioServicioDAO();
            unidadListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            unidadListaPrecioDAO.EliminarUnidadListaPrecioServicioPorIdItemListaPrecioServicio(idItemListaPrecioServicio);
            
            itemListaPrecioDAO = new ItemListaPrecioServicioDAO();
            itemListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            itemListaPrecioDAO.EliminarItemListaPrecioServicio(idItemListaPrecioServicio);

            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarUnidadListaPrecioServicio(int idUnidadListaPrecioServicio) {
        try {
            unidadListaPrecioDAO = new UnidadListaPrecioServicioDAO();
            unidadListaPrecioDAO.IniciarTransaccion();
            unidadListaPrecioDAO.EliminarUnidadListaPrecioServicio(idUnidadListaPrecioServicio);
            
            escalaListaPrecioDAO = new EscalaListaPrecioServicioDAO();
            escalaListaPrecioDAO.AsignarSesion(unidadListaPrecioDAO);
            escalaListaPrecioDAO.EliminarEscalaListaPrecioServicioPorIdUnidadListaPrecioServicio(idUnidadListaPrecioServicio);
            
            unidadListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            unidadListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            unidadListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarEscalaListaPrecioServicio(int idEscalaListaPrecioServicio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioServicioDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioServicio(idEscalaListaPrecioServicio);
            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
}
