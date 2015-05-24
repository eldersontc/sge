package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.EscalaListaPrecioProductoDAO;
import com.sge.modulos.ventas.accesoDatos.ItemListaPrecioProductoDAO;
import com.sge.modulos.ventas.accesoDatos.ListaPrecioProductoDAO;
import com.sge.modulos.ventas.accesoDatos.UnidadListaPrecioProductoDAO;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioProducto;
import com.sge.modulos.ventas.entidades.ItemListaPrecioProducto;
import com.sge.modulos.ventas.entidades.ListaPrecioProducto;
import com.sge.modulos.ventas.entidades.UnidadListaPrecioProducto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class ListaPrecioProductoDTO {
    
    ListaPrecioProductoDAO listaPrecioDAO;
    ItemListaPrecioProductoDAO itemListaPrecioDAO;
    UnidadListaPrecioProductoDAO unidadListaPrecioDAO;
    EscalaListaPrecioProductoDAO escalaListaPrecioDAO;
    
    public List<ListaPrecioProducto> ObtenerListasPrecio(String filtro) {
        List<ListaPrecioProducto> lista;
        try {
            listaPrecioDAO = new ListaPrecioProductoDAO();
            listaPrecioDAO.AbrirSesion();
            lista = listaPrecioDAO.ObtenerListasPrecio(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return lista;
    }

    public ListaPrecioProducto ObtenerListaPrecioProducto(int idListaPrecioProducto) {
        ListaPrecioProducto listaPrecio = null;
        try {
            listaPrecioDAO = new ListaPrecioProductoDAO();
            listaPrecioDAO.AbrirSesion();
            listaPrecio = listaPrecioDAO.ObtenerPorId(ListaPrecioProducto.class, idListaPrecioProducto);
            
            itemListaPrecioDAO = new ItemListaPrecioProductoDAO();
            itemListaPrecioDAO.AsignarSesion(listaPrecioDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idListaPrecioProducto", idListaPrecioProducto});
            List<ItemListaPrecioProducto> items = itemListaPrecioDAO.ObtenerLista(ItemListaPrecioProducto.class, filtros);
            listaPrecio.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return listaPrecio;
    }

    public List<UnidadListaPrecioProducto> ObtenerUnidadesListaPrecioProducto(int idItemListaPrecioProducto) {
        List<UnidadListaPrecioProducto> unidades = null;
        try {
            unidadListaPrecioDAO = new UnidadListaPrecioProductoDAO();
            unidadListaPrecioDAO.AbrirSesion();
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idItemListaPrecioProducto", idItemListaPrecioProducto});
            unidades = unidadListaPrecioDAO.ObtenerLista(UnidadListaPrecioProducto.class, filtros);
        } catch (Exception e) {
            throw e;
        } finally {
            unidadListaPrecioDAO.CerrarSesion();
        }
        return unidades;
    }
    
    public List<EscalaListaPrecioProducto> ObtenerEscalasListaPrecioProducto(int idUnidadListaPrecioProducto) {
        List<EscalaListaPrecioProducto> escalas = null;
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioProductoDAO();
            escalaListaPrecioDAO.AbrirSesion();
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idUnidadListaPrecioProducto", idUnidadListaPrecioProducto});
            escalas = escalaListaPrecioDAO.ObtenerLista(EscalaListaPrecioProducto.class, filtros);
        } catch (Exception e) {
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return escalas;
    }
    
    public boolean RegistrarListaPrecioProducto(ListaPrecioProducto listaPrecio) {
        try {
            listaPrecioDAO = new ListaPrecioProductoDAO();
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
    
    public ItemListaPrecioProducto[] RegistrarItemsListaPrecioProducto(ItemListaPrecioProducto[] itemsListaPrecio) {
        try {
            itemListaPrecioDAO = new ItemListaPrecioProductoDAO();
            itemListaPrecioDAO.IniciarTransaccion();
            for (ItemListaPrecioProducto itemListaPrecio : itemsListaPrecio) {
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
    
    public UnidadListaPrecioProducto[] RegistrarUnidadesListaPrecioProducto(UnidadListaPrecioProducto[] unidadesListaPrecio) {
        try {
            unidadListaPrecioDAO = new UnidadListaPrecioProductoDAO();
            unidadListaPrecioDAO.IniciarTransaccion();
            for (UnidadListaPrecioProducto unidadListaPrecio : unidadesListaPrecio) {
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
    
    public boolean RegistrarEscalaListaPrecioProducto(EscalaListaPrecioProducto escalaListaPrecio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioProductoDAO();
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

    public boolean ActualizarListaPrecioProducto(ListaPrecioProducto listaPrecio) {
        try {
            listaPrecioDAO = new ListaPrecioProductoDAO();
            listaPrecioDAO.IniciarTransaccion();
            listaPrecioDAO.ActualizarListaPrecioProducto(listaPrecio.getIdListaPrecioProducto(), listaPrecio.getNombre(), listaPrecio.isActivo());
            listaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            listaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            listaPrecioDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarEscalaListaPrecioProducto(EscalaListaPrecioProducto escalaListaPrecio) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioProductoDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.ActualizarEscalaListaPrecioProducto(escalaListaPrecio.getIdEscalaListaPrecioProducto(), escalaListaPrecio.getDesde(), escalaListaPrecio.getHasta(), escalaListaPrecio.getPrecio());
            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarListaPrecioProducto(int idListaPrecioProducto) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioProductoDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioProductoPorIdListaPrecioProducto(idListaPrecioProducto);
            
            unidadListaPrecioDAO = new UnidadListaPrecioProductoDAO();
            unidadListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            unidadListaPrecioDAO.EliminarUnidadListaPrecioProductoPorIdListaPrecioProducto(idListaPrecioProducto);
            
            itemListaPrecioDAO = new ItemListaPrecioProductoDAO();
            itemListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            itemListaPrecioDAO.EliminarItemListaPrecioProductoPorIdListaPrecioProducto(idListaPrecioProducto);
            
            listaPrecioDAO = new ListaPrecioProductoDAO();
            listaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            listaPrecioDAO.EliminarListaPrecioProducto(idListaPrecioProducto);

            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarItemListaPrecioProducto(int idItemListaPrecioProducto) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioProductoDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioProductoPorIdItemListaPrecioProducto(idItemListaPrecioProducto);
            
            unidadListaPrecioDAO = new UnidadListaPrecioProductoDAO();
            unidadListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            unidadListaPrecioDAO.EliminarUnidadListaPrecioProductoPorIdItemListaPrecioProducto(idItemListaPrecioProducto);
            
            itemListaPrecioDAO = new ItemListaPrecioProductoDAO();
            itemListaPrecioDAO.AsignarSesion(escalaListaPrecioDAO);
            itemListaPrecioDAO.EliminarItemListaPrecioProducto(idItemListaPrecioProducto);
            
            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarUnidadListaPrecioProducto(int idUnidadListaPrecioProducto) {
        try {
            unidadListaPrecioDAO = new UnidadListaPrecioProductoDAO();
            unidadListaPrecioDAO.IniciarTransaccion();
            unidadListaPrecioDAO.EliminarUnidadListaPrecioProducto(idUnidadListaPrecioProducto);
            
            escalaListaPrecioDAO = new EscalaListaPrecioProductoDAO();
            escalaListaPrecioDAO.AsignarSesion(unidadListaPrecioDAO);
            escalaListaPrecioDAO.EliminarEscalaListaPrecioProductoPorIdUnidadListaPrecioProducto(idUnidadListaPrecioProducto);
            
            unidadListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            unidadListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            unidadListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public boolean EliminarEscalaListaPrecioProducto(int idEscalaListaPrecioProducto) {
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioProductoDAO();
            escalaListaPrecioDAO.IniciarTransaccion();
            escalaListaPrecioDAO.EliminarEscalaListaPrecioProducto(idEscalaListaPrecioProducto);
            escalaListaPrecioDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            escalaListaPrecioDAO.AbortarTransaccion();
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return true;
    }
    
    public List<EscalaListaPrecioProducto> ObtenerEscalasPorProducto(int idListaPrecioProducto, int idProducto, int idUnidad) {
        List<EscalaListaPrecioProducto> escalas = null;
        try {
            escalaListaPrecioDAO = new EscalaListaPrecioProductoDAO();
            escalaListaPrecioDAO.AbrirSesion();
            escalas = escalaListaPrecioDAO.ObtenerEscalasPorProducto(idListaPrecioProducto, idProducto, idUnidad);
        } catch (Exception e) {
            throw e;
        } finally {
            escalaListaPrecioDAO.CerrarSesion();
        }
        return escalas;
    }
}
