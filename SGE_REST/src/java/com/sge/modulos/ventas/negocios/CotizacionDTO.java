package com.sge.modulos.ventas.negocios;

import com.sge.modulos.ventas.accesoDatos.CotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.EscalaListaPrecioServicioDAO;
import com.sge.modulos.ventas.accesoDatos.ItemCotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.ServicioCotizacionDAO;
import com.sge.modulos.ventas.accesoDatos.ServicioUnidadDAO;
import com.sge.modulos.ventas.accesoDatos.SolicitudCotizacionDAO;
import com.sge.modulos.ventas.entidades.Cotizacion;
import com.sge.modulos.ventas.entidades.EscalaListaPrecioServicio;
import com.sge.modulos.ventas.entidades.ItemCotizacion;
import com.sge.modulos.ventas.entidades.ServicioCotizacion;
import com.sge.modulos.ventas.entidades.ServicioUnidad;
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
    ServicioCotizacionDAO servicioCotizacionDAO;
    SolicitudCotizacionDAO solicitudCotizacionDAO;
    ServicioUnidadDAO servicioUnidadDAO;
    EscalaListaPrecioServicioDAO escalaListaPrecioServicioDAO;

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
            
            servicioCotizacionDAO = new ServicioCotizacionDAO();
            servicioCotizacionDAO.AsignarSesion(cotizacionDAO);
            
            for (Cotizacion cotizacion : lista) {
                
                String carpetaGraficos = "/home/elderson/GRAFICOS/";
                
                List<Object[]> filtros = new ArrayList<>();
                filtros.add(new Object[]{"idCotizacion", cotizacion.getIdCotizacion()});
                List<ItemCotizacion> items = itemCotizacionDAO.ObtenerLista(ItemCotizacion.class, filtros);
                
                for (ItemCotizacion item : items) {
                    
                    filtros = new ArrayList<>();
                    filtros.add(new Object[]{"idItemCotizacion", item.getIdItemCotizacion()});
                    List<ServicioCotizacion> acabados = servicioCotizacionDAO.ObtenerLista(ServicioCotizacion.class, filtros);
                    
                    item.setAcabados(acabados);
                    
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

            servicioCotizacionDAO = new ServicioCotizacionDAO();
            servicioCotizacionDAO.AsignarSesion(cotizacionDAO);
            
            servicioUnidadDAO = new ServicioUnidadDAO();
            servicioUnidadDAO.AsignarSesion(cotizacionDAO);
            
            escalaListaPrecioServicioDAO = new EscalaListaPrecioServicioDAO();
            escalaListaPrecioServicioDAO.AsignarSesion(servicioUnidadDAO);
            
            String carpetaGraficos = "/home/elderson/GRAFICOS/";

            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idCotizacion", idCotizacion});
            List<ItemCotizacion> items = itemCotizacionDAO.ObtenerLista(ItemCotizacion.class, filtros);

            for (ItemCotizacion item : items) {
                
                filtros = new ArrayList<>();
                filtros.add(new Object[]{"idItemCotizacion", item.getIdItemCotizacion()});
                List<ServicioCotizacion> acabados = servicioCotizacionDAO.ObtenerLista(ServicioCotizacion.class, filtros);
                
                for (ServicioCotizacion acabado : acabados) {
                    
                    filtros = new ArrayList<>();
                    filtros.add(new Object[]{"idServicio", acabado.getIdServicio()});
                    List<ServicioUnidad> unidades = servicioUnidadDAO.ObtenerLista(ServicioUnidad.class, filtros);
                    
                    for (ServicioUnidad unidad : unidades) {
                        
                        List<EscalaListaPrecioServicio> escalas = escalaListaPrecioServicioDAO.ObtenerEscalasPorUnidad(cotizacion.getIdListaPrecioServicio(), acabado.getIdServicio(), unidad.getIdServicioUnidad());
                        
                        unidad.setEscalas(escalas);
                    }
                    
                    acabado.setUnidades(unidades);
                    
                    ServicioUnidad unidad = servicioUnidadDAO.ObtenerPorId(ServicioUnidad.class, acabado.getIdServicioUnidad());
                    
                    acabado.setUnidad(unidad);
                }
                
                item.setAcabados(acabados);
                
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

            servicioCotizacionDAO = new ServicioCotizacionDAO();
            servicioCotizacionDAO.AsignarSesion(cotizacionDAO);
            
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
                
                for (ServicioCotizacion acabado : item.getAcabados()) {
                    acabado.setIdItemCotizacion(item.getIdItemCotizacion());
                    servicioCotizacionDAO.Agregar(acabado);
                }
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

            servicioCotizacionDAO = new ServicioCotizacionDAO();
            servicioCotizacionDAO.AsignarSesion(cotizacionDAO);
            
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
                    
                    for (ServicioCotizacion acabado : item.getAcabados()) {
                        acabado.setIdItemCotizacion(item.getIdItemCotizacion());
                        servicioCotizacionDAO.Agregar(acabado);
                    }
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
                    
                    for (ServicioCotizacion acabado : item.getAcabados()) {
                        if(acabado.isAgregar()){
                            acabado.setIdItemCotizacion(item.getIdItemCotizacion());
                            servicioCotizacionDAO.Agregar(acabado);
                        }
                        if(acabado.isActualizar()){
                            servicioCotizacionDAO.ActualizarServicioCotizacion(acabado.getIdServicioCotizacion(), acabado.isPrecioManual(), acabado.getPrecio(), acabado.getCantidad(), acabado.getTotal());
                        }
                        if(acabado.isEliminar()){
                            servicioCotizacionDAO.EliminarServicioCotizacion(acabado.getIdServicioCotizacion());
                        }
                    }
                }
                if (item.isEliminar()) {
                    servicioCotizacionDAO.EliminarServicioCotizacionPorIdItemCotizacion(item.getIdItemCotizacion());
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
            
            List<Object[]> filtros = new ArrayList<>();
            filtros.add(new Object[]{"idCotizacion", idCotizacion});
            List<ItemCotizacion> items = itemCotizacionDAO.ObtenerLista(ItemCotizacion.class, filtros);
            
            servicioCotizacionDAO = new ServicioCotizacionDAO();
            servicioCotizacionDAO.AsignarSesion(cotizacionDAO);
            
            String carpetaGraficos = "/home/elderson/GRAFICOS/";
            
            for (ItemCotizacion item : items) {
                
                servicioCotizacionDAO.EliminarServicioCotizacionPorIdItemCotizacion(item.getIdItemCotizacion());
                
                if(item.getUbicacionGraficoPrecorte() != null && !item.getUbicacionGraficoPrecorte().isEmpty()){
                    File file = new File(carpetaGraficos + item.getUbicacionGraficoPrecorte());
                    if(file.exists()){
                        file.delete();
                    }
                }
                
                if(item.getUbicacionGraficoImpresion()!= null && !item.getUbicacionGraficoImpresion().isEmpty()){
                    File file = new File(carpetaGraficos + item.getUbicacionGraficoImpresion());
                    if(file.exists()){
                        file.delete();
                    }
                }
                
                itemCotizacionDAO.EliminarItemCotizacion(item.getIdItemCotizacion());
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
