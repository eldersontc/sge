package com.sge.modulos.produccion.negocios;

import com.sge.base.negocios.BaseDTO;
import com.sge.modulos.administracion.accesoDatos.NumeracionDAO;
import com.sge.modulos.produccion.accesoDatos.ItemOrdenTrabajoDAO;
import com.sge.modulos.produccion.accesoDatos.OrdenTrabajoDAO;
import com.sge.modulos.produccion.accesoDatos.ServicioOrdenTrabajoDAO;
import com.sge.modulos.produccion.entidades.ItemOrdenTrabajo;
import com.sge.modulos.produccion.entidades.OrdenTrabajo;
import com.sge.modulos.produccion.entidades.ServicioOrdenTrabajo;
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
public class OrdenTrabajoDTO extends BaseDTO {
    
    OrdenTrabajoDAO ordenTrabajoDAO;
    ItemOrdenTrabajoDAO itemOrdenTrabajoDAO;
    ServicioOrdenTrabajoDAO servicioOrdenTrabajoDAO;
    NumeracionDAO numeracionDAO;
    
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
            
            servicioOrdenTrabajoDAO = new ServicioOrdenTrabajoDAO();
            servicioOrdenTrabajoDAO.AsignarSesion(ordenTrabajoDAO);
            
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idOrdenTrabajo", idOrdenTrabajo});
            List<ItemOrdenTrabajo> items = itemOrdenTrabajoDAO.ObtenerLista(ItemOrdenTrabajo.class, filtros);
            
            for (ItemOrdenTrabajo item : items) {
                
                filtros = new ArrayList<>();
                filtros.add(new Object[]{"idItemOrdenTrabajo", item.getIdItemOrdenTrabajo()});
                List<ServicioOrdenTrabajo> acabados = servicioOrdenTrabajoDAO.ObtenerLista(ServicioOrdenTrabajo.class, filtros);
                
                item.setAcabados(acabados);
                
                if (item.getUbicacionGraficoPrecorte() != null && !item.getUbicacionGraficoPrecorte().isEmpty()) {
                    BufferedImage grafico = ImageIO.read(new File(getCarpetaGraficos() + item.getUbicacionGraficoPrecorte()));
                    ByteArrayOutputStream arrayBytesOut = new ByteArrayOutputStream();
                    ImageIO.write(grafico, "jpg", arrayBytesOut);
                    item.setGraficoPrecorte(arrayBytesOut.toByteArray());
                }
                
                if (item.getUbicacionGraficoImpresion() != null && !item.getUbicacionGraficoImpresion().isEmpty()) {
                    BufferedImage grafico = ImageIO.read(new File(getCarpetaGraficos() + item.getUbicacionGraficoImpresion()));
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
            
            numeracionDAO = new NumeracionDAO();
            numeracionDAO.AsignarSesion(ordenTrabajoDAO);
            
            if(!ordenTrabajo.isNumeracionManual()){
                ordenTrabajo.setNumero(numeracionDAO.GenerarNumeracion(ordenTrabajo.getIdNumeracion()));
            }
            
            ordenTrabajoDAO.Agregar(ordenTrabajo);

            itemOrdenTrabajoDAO = new ItemOrdenTrabajoDAO();
            itemOrdenTrabajoDAO.AsignarSesion(ordenTrabajoDAO);
            
            servicioOrdenTrabajoDAO = new ServicioOrdenTrabajoDAO();
            servicioOrdenTrabajoDAO.AsignarSesion(ordenTrabajoDAO);
            
            for (ItemOrdenTrabajo item : ordenTrabajo.getItems()) {
                
                item.setIdOrdenTrabajo(ordenTrabajo.getIdOrdenTrabajo());
                
                itemOrdenTrabajoDAO.Agregar(item);
                
                for (ServicioOrdenTrabajo acabado : item.getAcabados()) {
                    
                    acabado.setIdItemOrdenTrabajo(item.getIdItemOrdenTrabajo());
                    
                    servicioOrdenTrabajoDAO.Agregar(acabado);
                }
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
            
            servicioOrdenTrabajoDAO = new ServicioOrdenTrabajoDAO();
            servicioOrdenTrabajoDAO.AsignarSesion(ordenTrabajoDAO);
            
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idOrdenTrabajo", idOrdenTrabajo});
            List<ItemOrdenTrabajo> items = itemOrdenTrabajoDAO.ObtenerLista(ItemOrdenTrabajo.class, filtros);

            for (ItemOrdenTrabajo item : items) {
                
                servicioOrdenTrabajoDAO.EliminarServicioOrdenTrabajoPorIdItemOrdenTrabajo(item.getIdItemOrdenTrabajo());
                
                itemOrdenTrabajoDAO.EliminarItemOrdenTrabajo(item.getIdItemOrdenTrabajo());
            
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
}
