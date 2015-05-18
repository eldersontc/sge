package com.sge.modulos.facturacion.negocios;

import com.sge.modulos.facturacion.accesoDatos.FacturaDAO;
import com.sge.modulos.facturacion.accesoDatos.ItemFacturaDAO;
import com.sge.modulos.facturacion.entidades.Factura;
import com.sge.modulos.facturacion.entidades.ItemFactura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elderson
 */
public class FacturaDTO {
    
    FacturaDAO facturaDAO;
    ItemFacturaDAO itemFacturaDAO;
    
    public List<Factura> ObtenerFacturas(String filtro) {
        List<Factura> lista;
        try {
            facturaDAO = new FacturaDAO();
            facturaDAO.AbrirSesion();
            lista = facturaDAO.ObtenerFacturas(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            facturaDAO.CerrarSesion();
        }
        return lista;
    }

    public Factura ObtenerFactura(int idFactura) {
        Factura factura;
        try {
            facturaDAO = new FacturaDAO();
            facturaDAO.AbrirSesion();
            factura = facturaDAO.ObtenerPorId(Factura.class, idFactura);
            itemFacturaDAO = new ItemFacturaDAO();
            itemFacturaDAO.AsignarSesion(facturaDAO);
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idFactura", idFactura});
            List<ItemFactura> items = itemFacturaDAO.ObtenerLista(ItemFactura.class, filtros);
            factura.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            facturaDAO.CerrarSesion();
        }
        return factura;
    }

    public boolean RegistrarFactura(Factura factura) {
        try {
            facturaDAO = new FacturaDAO();
            facturaDAO.IniciarTransaccion();
            facturaDAO.Agregar(factura);

            itemFacturaDAO = new ItemFacturaDAO();
            itemFacturaDAO.AsignarSesion(facturaDAO);

            for (ItemFactura item : factura.getItems()) {
                item.setIdFactura(factura.getIdFactura());
                itemFacturaDAO.Agregar(item);
            }

            facturaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            facturaDAO.AbortarTransaccion();
            throw e;
        } finally {
            facturaDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarFactura(int idFactura) {
        try {
            facturaDAO = new FacturaDAO();
            facturaDAO.IniciarTransaccion();
            facturaDAO.EliminarFactura(idFactura);

            itemFacturaDAO = new ItemFacturaDAO();
            itemFacturaDAO.AsignarSesion(facturaDAO);
            itemFacturaDAO.EliminarItemFacturaPorFactura(idFactura);

            facturaDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            facturaDAO.AbortarTransaccion();
            throw e;
        } finally {
            facturaDAO.CerrarSesion();
        }
        return true;
    }
}
