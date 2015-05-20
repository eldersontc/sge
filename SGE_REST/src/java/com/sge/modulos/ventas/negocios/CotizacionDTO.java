package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.CotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.ItemCotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.SolicitudCotizacionDAO;
import com.sge.modulos.ventas.entidades.Cotizacion;
import com.sge.modulos.ventas.entidades.ItemCotizacion;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author elderson
 */
public class CotizacionDTO {

    CotizacionDAO cotizacionDAO;
    ItemCotizacionDAO itemCotizacionDAO;
    SolicitudCotizacionDAO solicitudCotizacionDAO;

    public List<Cotizacion> ObtenerCotizaciones(String filtro) {
        List<Cotizacion> lista;
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AbrirSesion();
            lista = cotizacionDAO.ObtenerCotizaciones(filtro);
        } catch (Exception e) {
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return lista;
    }

    public List<Cotizacion> ObtenerCotizacionesPorPresupuesto(int idPresupuesto) throws Exception {
        List<Cotizacion> lista;
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AbrirSesion();
            lista = cotizacionDAO.ObtenerCotizacionesPorPresupuesto(idPresupuesto);

            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);
            for (Cotizacion cotizacion : lista) {
                
                String carpetaGraficos = "/home/elderson/GRAFICOS/";
                
                List<Object[]> filtros = new ArrayList<>();
                filtros.add(new Object[]{"idCotizacion", cotizacion.getIdCotizacion()});
                List<ItemCotizacion> items = itemCotizacionDAO.ObtenerLista(ItemCotizacion.class, filtros);
                
                for (ItemCotizacion item : items) {
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
                
                cotizacion.setItems(items);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return lista;
    }

    public Cotizacion ObtenerCotizacion(int idCotizacion) throws Exception {
        Cotizacion cotizacion = null;
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.AbrirSesion();
            cotizacion = cotizacionDAO.ObtenerPorId(Cotizacion.class, idCotizacion);

            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);

            String carpetaGraficos = "/home/elderson/GRAFICOS/";

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idCotizacion", idCotizacion});
            List<ItemCotizacion> items = itemCotizacionDAO.ObtenerLista(ItemCotizacion.class, filtros);

            for (ItemCotizacion item : items) {
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

            cotizacion.setItems(items);
        } catch (Exception e) {
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return cotizacion;
    }

    public boolean RegistrarCotizacion(Cotizacion cotizacion) throws Exception {
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.IniciarTransaccion();
            cotizacion.setEstado("PENDIENTE DE APROBACIÓN");
            cotizacionDAO.Agregar(cotizacion);

            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);

            String carpetaGraficos = "/home/elderson/GRAFICOS/";

            for (ItemCotizacion item : cotizacion.getItems()) {
                item.setIdCotizacion(cotizacion.getIdCotizacion());
                if (item.getGraficoPrecorte() != null) {
                    String ubicacion = "PRECORTE-" + new Date().getTime() + ".JPG";
                    FileOutputStream fos = new FileOutputStream(carpetaGraficos + ubicacion);
                    fos.write(item.getGraficoPrecorte());
                    fos.close();
                    item.setUbicacionGraficoPrecorte(ubicacion);
                }
                if (item.getGraficoImpresion() != null) {
                    String ubicacion = "IMPRESION-" + new Date().getTime() + ".JPG";
                    FileOutputStream fos = new FileOutputStream(carpetaGraficos + ubicacion);
                    fos.write(item.getGraficoImpresion());
                    fos.close();
                    item.setUbicacionGraficoImpresion(ubicacion);
                }
                itemCotizacionDAO.Agregar(item);
            }

            if (cotizacion.getIdSolicitudCotizacion() > 0) {
                solicitudCotizacionDAO = new SolicitudCotizacionDAO();
                solicitudCotizacionDAO.AsignarSesion(cotizacionDAO);
                solicitudCotizacionDAO.ActualizarEstadoSolicitudCotizacion(cotizacion.getIdSolicitudCotizacion(), "COTIZACIÓN GENERADA");
            }

            cotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean ActualizarCotizacion(Cotizacion cotizacion) throws Exception {
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.IniciarTransaccion();
            cotizacionDAO.ActualizarCotizacion(cotizacion);

            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);

            String carpetaGraficos = "/home/elderson/GRAFICOS/";

            for (ItemCotizacion item : cotizacion.getItems()) {
                if (item.isAgregar()) {
                    item.setIdCotizacion(cotizacion.getIdCotizacion());
                    if (item.getGraficoPrecorte() != null) {
                        String ubicacion = "PRECORTE-" + new Date().getTime() + ".JPG";
                        FileOutputStream fos = new FileOutputStream(carpetaGraficos + ubicacion);
                        fos.write(item.getGraficoPrecorte());
                        fos.close();
                        item.setUbicacionGraficoPrecorte(ubicacion);
                    }
                    if (item.getGraficoImpresion() != null) {
                        String ubicacion = "IMPRESION-" + new Date().getTime() + ".JPG";
                        FileOutputStream fos = new FileOutputStream(carpetaGraficos + ubicacion);
                        fos.write(item.getGraficoImpresion());
                        fos.close();
                        item.setUbicacionGraficoImpresion(ubicacion);
                    }
                    itemCotizacionDAO.Agregar(item);
                }
                if (item.isActualizar()) {
                    itemCotizacionDAO.ActualizarItemCotizacion(item);
                    if (item.getGraficoPrecorte() != null) {
                        FileOutputStream fos = new FileOutputStream(carpetaGraficos + item.getUbicacionGraficoPrecorte());
                        fos.write(item.getGraficoPrecorte());
                        fos.close();
                    }
                    if (item.getGraficoImpresion() != null) {
                        FileOutputStream fos = new FileOutputStream(carpetaGraficos + item.getUbicacionGraficoImpresion());
                        fos.write(item.getGraficoImpresion());
                        fos.close();
                    }
                }
                if (item.isEliminar()) {
                    itemCotizacionDAO.EliminarItemCotizacion(item.getIdItemCotizacion());
                }
            }

            cotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean EliminarCotizacion(int idCotizacion) {
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.IniciarTransaccion();

            Cotizacion cotizacion = cotizacionDAO.ObtenerPorId(Cotizacion.class, idCotizacion);
            if (cotizacion.getIdSolicitudCotizacion() > 0) {
                solicitudCotizacionDAO = new SolicitudCotizacionDAO();
                solicitudCotizacionDAO.AsignarSesion(cotizacionDAO);
                solicitudCotizacionDAO.ActualizarEstadoSolicitudCotizacion(cotizacion.getIdSolicitudCotizacion(), "APROBADO");
            }

            cotizacionDAO.EliminarCotizacion(idCotizacion);

            itemCotizacionDAO = new ItemCotizacionDAO();
            itemCotizacionDAO.AsignarSesion(cotizacionDAO);
            itemCotizacionDAO.EliminarItemCotizacionPorIdCotizacion(idCotizacion);

            cotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean AprobarCotizacion(int idCotizacion) {
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.IniciarTransaccion();
            cotizacionDAO.ActualizarEstadoCotizacion(idCotizacion, "APROBADO");
            cotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return true;
    }

    public boolean DesaprobarCotizacion(int idCotizacion) {
        try {
            cotizacionDAO = new CotizacionDAO();
            cotizacionDAO.IniciarTransaccion();
            cotizacionDAO.ActualizarEstadoCotizacion(idCotizacion, "PENDIENTE DE APROBACIÓN");
            cotizacionDAO.ConfirmarTransaccion();
        } catch (Exception e) {
            cotizacionDAO.AbortarTransaccion();
            throw e;
        } finally {
            cotizacionDAO.CerrarSesion();
        }
        return true;
    }
}
