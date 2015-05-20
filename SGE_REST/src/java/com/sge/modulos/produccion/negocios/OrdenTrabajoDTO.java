package com.sge.modulos.produccion.negocios;

import com.sge.modulos.produccion.accesoDatos.ItemOrdenTrabajoDAO;
import com.sge.modulos.produccion.accesoDatos.OrdenTrabajoDAO;
import com.sge.modulos.produccion.entidades.ItemOrdenTrabajo;
import com.sge.modulos.produccion.entidades.OrdenTrabajo;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author elderson
 */
public class OrdenTrabajoDTO {
    
    OrdenTrabajoDAO ordenTrabajoDAO;
    ItemOrdenTrabajoDAO itemOrdenTrabajoDAO;
    
    public List<OrdenTrabajo> ObtenerOrdenesTrabajo(String filtro) {
        List<OrdenTrabajo> lista;
        try {
            ordenTrabajoDAO = new OrdenTrabajoDAO();
            ordenTrabajoDAO.AbrirSesion();
            lista = ordenTrabajoDAO.ObtenerOrdenesTrabajo(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            ordenTrabajoDAO.CerrarSesion();
        }
        return lista;
    }

    public OrdenTrabajo ObtenerOrdenTrabajo(int idOrdenTrabajo) throws Exception {
        OrdenTrabajo ordenTrabajo = null;
        try {
            ordenTrabajoDAO = new OrdenTrabajoDAO();
            ordenTrabajoDAO.AbrirSesion();
            ordenTrabajo = ordenTrabajoDAO.ObtenerPorId(OrdenTrabajo.class, idOrdenTrabajo);
            
            itemOrdenTrabajoDAO = new ItemOrdenTrabajoDAO();
            itemOrdenTrabajoDAO.AsignarSesion(ordenTrabajoDAO);
            
            String carpetaGraficos = "/home/elderson/GRAFICOS/";
            
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idOrdenTrabajo", idOrdenTrabajo});
            List<ItemOrdenTrabajo> items = itemOrdenTrabajoDAO.ObtenerLista(ItemOrdenTrabajo.class, filtros);
            
            for (ItemOrdenTrabajo item : items) {
                if (item.getUbicacionGraficoPrecorte() != null && !item.getUbicacionGraficoPrecorte().isEmpty()) {
                    BufferedImage grafico = ImageIO.read(new File(carpetaGraficos + item.getUbicacionGraficoPrecorte()));
                    ByteArrayOutputStream arrayBytesOut = new ByteArrayOutputStream();
                    ImageIO.write(grafico, "jpg", arrayBytesOut);
                    item.setGraficoPrecorte(arrayBytesOut.toByteArray());
                }
                if (item.getUbicacionGraficoImpresion() != null && !item.getUbicacionGraficoImpresion().isEmpty()) {
                    BufferedImage grafico = ImageIO.read(new File(carpetaGraficos + item.getUbicacionGraficoImpresion()));
                    ByteArrayOutputStream arrayBytesOut = new ByteArrayOutputStream();
                    ImageIO.write(grafico, "jpg", arrayBytesOut);
                    item.setGraficoImpresion(arrayBytesOut.toByteArray());
                }
            }
            
            ordenTrabajo.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            ordenTrabajoDAO.CerrarSesion();
        }
        return ordenTrabajo;
    }

    public boolean RegistrarOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        try {
            ordenTrabajoDAO = new OrdenTrabajoDAO();
            ordenTrabajoDAO.IniciarTransaccion();
            ordenTrabajoDAO.Agregar(ordenTrabajo);

            itemOrdenTrabajoDAO = new ItemOrdenTrabajoDAO();
            itemOrdenTrabajoDAO.AsignarSesion(ordenTrabajoDAO);
            for (ItemOrdenTrabajo item : ordenTrabajo.getItems()) {
                item.setIdOrdenTrabajo(ordenTrabajo.getIdOrdenTrabajo());
                itemOrdenTrabajoDAO.Agregar(item);
            }

            ordenTrabajoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            ordenTrabajoDAO.AbortarTransaccion();
            throw e;
        } finally {
            ordenTrabajoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
        try {
            ordenTrabajoDAO = new OrdenTrabajoDAO();
            ordenTrabajoDAO.IniciarTransaccion();
            ordenTrabajoDAO.ActualizarOrdenTrabajo(ordenTrabajo);

            itemOrdenTrabajoDAO = new ItemOrdenTrabajoDAO();
            itemOrdenTrabajoDAO.AsignarSesion(ordenTrabajoDAO);
            for (ItemOrdenTrabajo item : ordenTrabajo.getItems()) {
                itemOrdenTrabajoDAO.ActualizarItemOrdenTrabajo(item);
            }

            ordenTrabajoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            ordenTrabajoDAO.AbortarTransaccion();
            throw e;
        } finally {
            ordenTrabajoDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarOrdenTrabajo(int idOrdenTrabajo) {
        try {
            ordenTrabajoDAO = new OrdenTrabajoDAO();
            ordenTrabajoDAO.IniciarTransaccion();
            ordenTrabajoDAO.EliminarOrdenTrabajo(idOrdenTrabajo);

            itemOrdenTrabajoDAO = new ItemOrdenTrabajoDAO();
            itemOrdenTrabajoDAO.AsignarSesion(ordenTrabajoDAO);
            itemOrdenTrabajoDAO.EliminarItemOrdenTrabajoPorIdOrdenTrabajo(idOrdenTrabajo);

            ordenTrabajoDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            ordenTrabajoDAO.AbortarTransaccion();
            throw e;
        } finally {
            ordenTrabajoDAO.CerrarSesion();
        }
        return true;
    }
}
