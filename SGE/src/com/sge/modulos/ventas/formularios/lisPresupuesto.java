package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.produccion.clases.ItemOrdenTrabajo;
import com.sge.modulos.produccion.clases.OrdenTrabajo;
import com.sge.modulos.produccion.clases.ServicioOrdenTrabajo;
import com.sge.modulos.produccion.formularios.regOrdenTrabajo;
import com.sge.modulos.ventas.clases.Cotizacion;
import com.sge.modulos.ventas.clases.ItemCotizacion;
import com.sge.modulos.ventas.clases.Presupuesto;
import com.sge.modulos.ventas.clases.ServicioCotizacion;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisPresupuesto extends frameBase<Presupuesto> {

    /**
     * Creates new form lisPresupuesto
     *
     * @param modo
     */
    public lisPresupuesto(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form lisPresupuesto
     *
     * @param modo
     * @param filtro
     */
    public lisPresupuesto(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        switch (getModo()) {
            case 0:
                OcultarColumna(tbPresupuestos, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbPresupuestos, new int[]{0, 7, 8});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerPresupuestos().execute();
    }

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarPresupuesto();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarPresupuesto();
        }
    };

    Action refr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swObtenerPresupuestos().execute();
        }
    };

    public class swObtenerPresupuestos extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                json = cliente.ObtenerPresupuestos(new Gson().toJson(getFiltro()));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    EliminarTodasFilas(tbPresupuestos);
                    Presupuesto[] presupuestos = new Gson().fromJson(resultado[1], Presupuesto[].class);
                    for (Presupuesto presupuesto : presupuestos) {
                        AgregarFila(tbPresupuestos, new Object[]{false, presupuesto.getIdPresupuesto(), presupuesto.getNumero(), presupuesto.getFechaCreacionString(), presupuesto.getRazonSocialCliente(), presupuesto.getTotal(), presupuesto.getEstado(), Icon_Edit, Icon_Dele});
                    }
                    AgregarBoton(tbPresupuestos, edit, 7);
                    AgregarBoton(tbPresupuestos, dele, 8);
                    AgregarOrdenamiento(tbPresupuestos);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarPresupuesto extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas plantillaSC = new cliVentas();
            String json = "";
            try {
                int idPresupuesto = ObtenerValorCelda(tbPresupuestos, 1);
                json = plantillaSC.EliminarPresupuesto(new Gson().toJson(idPresupuesto));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                plantillaSC.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    new swObtenerPresupuestos().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swAprobarPresupuesto extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas plantillaSC = new cliVentas();
            String json = "";
            try {
                int idPresupuesto = ObtenerValorCelda(tbPresupuestos, 1);
                //json = plantillaSC.AprobarPresupuesto(new Gson().toJson(idPresupuesto));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                plantillaSC.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    new swObtenerPresupuestos().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swDesaprobarPresupuesto extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas plantillaSC = new cliVentas();
            String json = "";
            try {
                int idPresupuesto = ObtenerValorCelda(tbPresupuestos, 1);
                //json = plantillaSC.DesaprobarPresupuesto(new Gson().toJson(idPresupuesto));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                plantillaSC.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    new swObtenerPresupuestos().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEnviarPresupuesto extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas plantillaSC = new cliVentas();
            String json = "";
            try {
                int idPresupuesto = ObtenerValorCelda(tbPresupuestos, 1);
                //json = plantillaSC.EnviarPresupuesto(new Gson().toJson(idPresupuesto));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                plantillaSC.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    new swObtenerPresupuestos().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swAceptarPresupuesto extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas plantillaSC = new cliVentas();
            String json = "";
            try {
                int idPresupuesto = ObtenerValorCelda(tbPresupuestos, 1);
                //json = plantillaSC.AceptarPresupuesto(new Gson().toJson(idPresupuesto));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                plantillaSC.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    new swObtenerPresupuestos().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swRechazarPresupuesto extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas plantillaSC = new cliVentas();
            String json = "";
            try {
                int idPresupuesto = ObtenerValorCelda(tbPresupuestos, 1);
                //json = plantillaSC.RechazarPresupuesto(new Gson().toJson(idPresupuesto));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                plantillaSC.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    new swObtenerPresupuestos().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGenerarOrdenTrabajo extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                int idPresupuesto = ObtenerValorCelda(tbPresupuestos, 1);
                String json = cliVentas.GenerarOrdenTrabajo(new Gson().toJson(new int[]{idPresupuesto, getIdUsuario()}));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Cotizacion[] cotizaciones = new Gson().fromJson(resultado[2], Cotizacion[].class);
                    JComboBox combo = new JComboBox();
                    for (Cotizacion cotizacion : cotizaciones) {
                        combo.addItem(cotizacion);
                    }
                    int confirmacion = VerModal(frame, combo, "SELECCIONE UNA COTIZACION");
                    if (confirmacion == 0) {
                        Date fechaServidor = new Gson().fromJson(resultado[1], Date.class);
                        Cotizacion cotizacion = (Cotizacion) combo.getSelectedItem();
                        OrdenTrabajo ordenTrabajo = null;
                        if (resultado[3].isEmpty()) {
                            ordenTrabajo = new OrdenTrabajo();
                        } else {
                            ordenTrabajo = new Gson().fromJson(resultado[3], OrdenTrabajo.class);
                        }
                        ordenTrabajo.setIdCliente(cotizacion.getIdCliente());
                        ordenTrabajo.setRazonSocialCliente(cotizacion.getRazonSocialCliente());
                        ordenTrabajo.setIdListaPrecioProducto(cotizacion.getIdListaPrecioProducto());
                        ordenTrabajo.setNombreListaPrecioProducto(cotizacion.getNombreListaPrecioProducto());
                        ordenTrabajo.setIdListaPrecioServicio(cotizacion.getIdListaPrecioServicio());
                        ordenTrabajo.setNombreListaPrecioServicio(cotizacion.getNombreListaPrecioServicio());
                        ordenTrabajo.setIdListaPrecioMaquina(cotizacion.getIdListaPrecioMaquina());
                        ordenTrabajo.setNombreListaPrecioMaquina(cotizacion.getNombreListaPrecioMaquina());
                        ordenTrabajo.setDescripcion(cotizacion.getDescripcion());
                        ordenTrabajo.setIdMoneda(cotizacion.getIdMoneda());
                        ordenTrabajo.setSimboloMoneda(cotizacion.getSimboloMoneda());
                        ordenTrabajo.setFechaCreacion(fechaServidor);
                        ordenTrabajo.setIdVendedor(cotizacion.getIdVendedor());
                        ordenTrabajo.setNombreVendedor(cotizacion.getNombreVendedor());
                        ordenTrabajo.setIdCotizador(cotizacion.getIdCotizador());
                        ordenTrabajo.setNombreCotizador(cotizacion.getNombreCotizador());
                        ordenTrabajo.setLineaProduccion(cotizacion.getLineaProduccion());
                        ordenTrabajo.setCantidad(cotizacion.getCantidad());
                        ordenTrabajo.setSubTotal(cotizacion.getSubTotal());
                        ordenTrabajo.setPorcentajeUtilidad(cotizacion.getPorcentajeUtilidad());
                        ordenTrabajo.setMontoUtilidad(cotizacion.getMontoUtilidad());
                        ordenTrabajo.setTotal(cotizacion.getTotal());

                        for (ItemCotizacion itemCotizacion : cotizacion.getItems()) {
                            ItemOrdenTrabajo itemOrdenTrabajo = new ItemOrdenTrabajo();
                            itemOrdenTrabajo.setNombre(itemCotizacion.getNombre());
                            itemOrdenTrabajo.setCantidad(itemCotizacion.getCantidad());
                            itemOrdenTrabajo.setIdServicioImpresion(itemCotizacion.getIdServicioImpresion());
                            itemOrdenTrabajo.setNombreServicioImpresion(itemCotizacion.getNombreServicioImpresion());
                            itemOrdenTrabajo.setImpresionVinil(itemCotizacion.isImpresionVinil());
                            itemOrdenTrabajo.setImpresionBanner(itemCotizacion.isImpresionBanner());
                            itemOrdenTrabajo.setIdMaquina(itemCotizacion.getIdMaquina());
                            itemOrdenTrabajo.setDescripcionMaquina(itemCotizacion.getDescripcionMaquina());
                            itemOrdenTrabajo.setAltoMaximoPliegoMaquina(itemCotizacion.getAltoMaximoPliegoMaquina());
                            itemOrdenTrabajo.setLargoMaximoPliegoMaquina(itemCotizacion.getLargoMaximoPliegoMaquina());
                            itemOrdenTrabajo.setIdMaterial(itemCotizacion.getIdMaterial());
                            itemOrdenTrabajo.setCodigoMaterial(itemCotizacion.getCodigoMaterial());
                            itemOrdenTrabajo.setNombreMaterial(itemCotizacion.getNombreMaterial());
                            itemOrdenTrabajo.setAltoMaterial(itemCotizacion.getAltoMaterial());
                            itemOrdenTrabajo.setLargoMaterial(itemCotizacion.getLargoMaterial());
                            itemOrdenTrabajo.setIdUnidadMaterial(itemCotizacion.getIdUnidadMaterial());
                            itemOrdenTrabajo.setAbreviacionUnidadMaterial(itemCotizacion.getAbreviacionUnidadMaterial());
                            itemOrdenTrabajo.setFactorUnidadMaterial(itemCotizacion.getFactorUnidadMaterial());
                            itemOrdenTrabajo.setNombreTipoUnidad(itemCotizacion.getNombreTipoUnidad());
                            itemOrdenTrabajo.setUnidadMedidaAbierta(itemCotizacion.getUnidadMedidaAbierta());
                            itemOrdenTrabajo.setMedidaAbierta(itemCotizacion.isMedidaAbierta());
                            itemOrdenTrabajo.setMedidaCerrada(itemCotizacion.isMedidaCerrada());
                            itemOrdenTrabajo.setTiraRetira(itemCotizacion.isTiraRetira());
                            itemOrdenTrabajo.setGrafico(itemCotizacion.isGrafico());
                            itemOrdenTrabajo.setMaterial(itemCotizacion.isMaterial());
                            itemOrdenTrabajo.setServicioImpresion(itemCotizacion.isServicioImpresion());
                            itemOrdenTrabajo.setFondo(itemCotizacion.isFondo());
                            itemOrdenTrabajo.setTipoUnidad(itemCotizacion.isTipoUnidad());
                            itemOrdenTrabajo.setLargoMedidaAbierta(itemCotizacion.getLargoMedidaAbierta());
                            itemOrdenTrabajo.setAltoMedidaAbierta(itemCotizacion.getAltoMedidaAbierta());
                            itemOrdenTrabajo.setLargoMedidaCerrada(itemCotizacion.getLargoMedidaCerrada());
                            itemOrdenTrabajo.setAltoMedidaCerrada(itemCotizacion.getAltoMedidaCerrada());
                            itemOrdenTrabajo.setTiraColor(itemCotizacion.getTiraColor());
                            itemOrdenTrabajo.setRetiraColor(itemCotizacion.getRetiraColor());
                            itemOrdenTrabajo.setdFondo(itemCotizacion.getdFondo());
                            itemOrdenTrabajo.setCantidadTipoUnidad(itemCotizacion.getCantidadTipoUnidad());
                            itemOrdenTrabajo.setAltoFormatoImpresion(itemCotizacion.getAltoFormatoImpresion());
                            itemOrdenTrabajo.setLargoFormatoImpresion(itemCotizacion.getLargoFormatoImpresion());
                            itemOrdenTrabajo.setSeparacionX(itemCotizacion.getSeparacionX());
                            itemOrdenTrabajo.setSeparacionY(itemCotizacion.getSeparacionY());
                            itemOrdenTrabajo.setCantidadPliegos(itemCotizacion.getCantidadPliegos());
                            itemOrdenTrabajo.setUbicacionGraficoPrecorte(itemCotizacion.getUbicacionGraficoPrecorte());
                            itemOrdenTrabajo.setUbicacionGraficoImpresion(itemCotizacion.getUbicacionGraficoImpresion());
                            itemOrdenTrabajo.setGraficoPrecorte(itemCotizacion.getGraficoPrecorte());
                            itemOrdenTrabajo.setGraficoImpresion(itemCotizacion.getGraficoImpresion());
                            itemOrdenTrabajo.setGraficoPrecorteGirado(itemCotizacion.isGraficoPrecorteGirado());
                            itemOrdenTrabajo.setGraficoImpresionGirado(itemCotizacion.isGraficoImpresionGirado());
                            itemOrdenTrabajo.setCantidadPiezasPrecorte(itemCotizacion.getCantidadPiezasPrecorte());
                            itemOrdenTrabajo.setCantidadPiezasImpresion(itemCotizacion.getCantidadPiezasImpresion());
                            itemOrdenTrabajo.setIdMetodoImpresion(itemCotizacion.getIdMetodoImpresion());
                            itemOrdenTrabajo.setDescripcionMetodoImpresion(itemCotizacion.getDescripcionMetodoImpresion());
                            itemOrdenTrabajo.setCantidadPases(itemCotizacion.getCantidadPases());
                            itemOrdenTrabajo.setCantidadCambios(itemCotizacion.getCantidadCambios());
                            itemOrdenTrabajo.setFactorHorizontal(itemCotizacion.getFactorHorizontal());
                            itemOrdenTrabajo.setFactorVertical(itemCotizacion.getFactorVertical());
                            itemOrdenTrabajo.setLetras(itemCotizacion.getLetras());
                            itemOrdenTrabajo.setCantidadMaterial(itemCotizacion.getCantidadMaterial());
                            itemOrdenTrabajo.setCantidadDemasia(itemCotizacion.getCantidadDemasia());
                            itemOrdenTrabajo.setCantidadDemasiaMaterial(itemCotizacion.getCantidadDemasiaMaterial());
                            itemOrdenTrabajo.setCantidadProduccion(itemCotizacion.getCantidadProduccion());
                            itemOrdenTrabajo.setCantidad(itemCotizacion.getCantidad());
                            itemOrdenTrabajo.setCantidadPaginasSobrantes(itemCotizacion.getCantidadPaginasSobrantes());
                            itemOrdenTrabajo.setObservacion(itemCotizacion.getObservacion());
                            itemOrdenTrabajo.setTotalMaquina(itemCotizacion.getTotalMaquina());
                            itemOrdenTrabajo.setTotalMaterial(itemCotizacion.getCantidadMaterial());
                            itemOrdenTrabajo.setTotalAcabados(itemCotizacion.getTotalAcabados());

                            for (ServicioCotizacion acabadoCotizacion : itemCotizacion.getAcabados()) {
                                ServicioOrdenTrabajo acabadoOT = new ServicioOrdenTrabajo();
                                acabadoOT.setIdServicio(acabadoCotizacion.getIdServicio());
                                acabadoOT.setDescripcionServicio(acabadoCotizacion.getDescripcionServicio());
                                acabadoOT.setIdServicioUnidad(acabadoCotizacion.getIdServicioUnidad());
                                acabadoOT.setAbreviacionUnidad(acabadoCotizacion.getAbreviacionUnidad());
                                acabadoOT.setCantidad(acabadoCotizacion.getCantidad());
                                acabadoOT.setPrecioManual(acabadoCotizacion.isPrecioManual());
                                acabadoOT.setPrecio(acabadoCotizacion.getPrecio());
                                acabadoOT.setTotal(acabadoCotizacion.getTotal());
                                itemOrdenTrabajo.getAcabados().add(acabadoOT);
                            }
                            ordenTrabajo.getItems().add(itemOrdenTrabajo);
                        }
                        regOrdenTrabajo regOrdenTrabajo = new regOrdenTrabajo(0);
                        regOrdenTrabajo.setEntidad(ordenTrabajo);
                        regOrdenTrabajo.AsignarControles();
                        getParent().add(regOrdenTrabajo);
                        regOrdenTrabajo.setVisible(true);
                    }
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliVentas.close();
            }
            return null;
        }

        @Override
        protected void done() {
            OcultarCargando(frame);
        }
    }

    public void EditarPresupuesto() {
        int idPresupuesto = ObtenerValorCelda(tbPresupuestos, 1);
        VerFrame(new regPresupuesto(idPresupuesto), refr);
    }

    public void EliminarPresupuesto() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarPresupuesto().execute();
        }
    }

    public void AprobarPresupuesto() {
        String estado = ObtenerValorCelda(tbPresupuestos, 6);
        if (estado.equals("PENDIENTE DE APROBACIÓN") || estado.equals("RECHAZADO")) {
            new swAprobarPresupuesto().execute();
        } else {
            VerAdvertencia("SÓLO SE PUEDE APROBAR CUANDO ESTÁ EN ESTADO : PENDIENTE DE APROBACIÓN Ó RECHAZADO.", frame);
        }
    }

    public void DesaprobarPresupuesto() {
        String estado = ObtenerValorCelda(tbPresupuestos, 6);
        if (estado.equals("APROBADO")) {
            new swDesaprobarPresupuesto().execute();
        } else {
            VerAdvertencia("SÓLO SE PUEDE DESAPROBAR CUANDO ESTÁ EN ESTADO : APROBADO.", frame);
        }
    }

    public void EnviarPresupuesto() {
        String estado = ObtenerValorCelda(tbPresupuestos, 6);
        if (estado.equals("APROBADO")) {
            new swEnviarPresupuesto().execute();
        } else {
            VerAdvertencia("SÓLO SE PUEDE ENVIAR CUANDO ESTÁ EN ESTADO : APROBADO.", frame);
        }
    }

    public void AceptarPresupuesto() {
        String estado = ObtenerValorCelda(tbPresupuestos, 6);
        if (estado.equals("ENVIADO AL CLIENTE")) {
            new swAceptarPresupuesto().execute();
        } else {
            VerAdvertencia("SÓLO SE PUEDE ACEPTAR CUANDO ESTÁ EN ESTADO : ENVIADO AL CLIENTE.", frame);
        }
    }

    public void RechazarPresupuesto() {
        String estado = ObtenerValorCelda(tbPresupuestos, 6);
        if (estado.equals("ENVIADO AL CLIENTE")) {
            new swRechazarPresupuesto().execute();
        } else {
            VerAdvertencia("SÓLO SE PUEDE RECHAZAR CUANDO ESTÁ EN ESTADO : ENVIADO AL CLIENTE.", frame);
        }
    }

    public void GenerarOrdenTrabajo() {
        String estado = ObtenerValorCelda(tbPresupuestos, 6);
        if (estado.equals("ACEPTADO")) {
            new swGenerarOrdenTrabajo().execute();
        } else {
            VerAdvertencia("SÓLO SE PUEDE GENERAR OT CUANDO ESTÁ EN ESTADO : ACEPTADO.", frame);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frame = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPresupuestos = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();
        btnGenerarOT = new javax.swing.JButton();
        btnAprobar = new javax.swing.JButton();
        btnDesaprobar = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnRechazar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbPresupuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "NUMERO", "F.CREACION", "CLIENTE", "TOTAL", "ESTADO", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPresupuestos.setRowHeight(25);
        tbPresupuestos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbPresupuestos);
        if (tbPresupuestos.getColumnModel().getColumnCount() > 0) {
            tbPresupuestos.getColumnModel().getColumn(1).setMinWidth(0);
            tbPresupuestos.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbPresupuestos.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE PRESUPUESTOS");

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addContainerGap())
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(btnNuevo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSeleccionar.setText("SELECCIONAR");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        lblFiltro.setText("FILTRO");

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/refresh-16.png"))); // NOI18N
        btnRefrescar.setToolTipText("REFRESCAR");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        btnGenerarOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/send-file-16.png"))); // NOI18N
        btnGenerarOT.setToolTipText("GENERAR OT");
        btnGenerarOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarOTActionPerformed(evt);
            }
        });

        btnAprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/checkmark-16.png"))); // NOI18N
        btnAprobar.setToolTipText("APROBAR");
        btnAprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprobarActionPerformed(evt);
            }
        });

        btnDesaprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/x-mark-16.png"))); // NOI18N
        btnDesaprobar.setToolTipText("DESAPROBAR");
        btnDesaprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesaprobarActionPerformed(evt);
            }
        });

        btnEnviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/talk-16.png"))); // NOI18N
        btnEnviar.setToolTipText("ENVIAR");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/checked-user-16.png"))); // NOI18N
        btnAceptar.setToolTipText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnRechazar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/remove-user-16.png"))); // NOI18N
        btnRechazar.setToolTipText("RECHAZAR");
        btnRechazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechazarActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/imprimir.png"))); // NOI18N
        btnImprimir.setToolTipText("REFRESCAR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDesaprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRechazar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenerarOT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFiltro)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarOT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesaprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRechazar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        VerFrame(new regPresupuesto(0), refr);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (getModo()) {
            case 1:
                if (FilaActiva(tbPresupuestos)) {
                    Presupuesto presupuesto = new Presupuesto();
                    presupuesto.setIdPresupuesto(ObtenerValorCelda(tbPresupuestos, 1));
                    presupuesto.setNumero(ObtenerValorCelda(tbPresupuestos, 2));
                    setSeleccionado(presupuesto);
                }
                Cerrar();
                break;
            case 2:
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbPresupuestos, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerPresupuestos().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnGenerarOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarOTActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbPresupuestos)) {
            GenerarOrdenTrabajo();
        }
    }//GEN-LAST:event_btnGenerarOTActionPerformed

    private void btnAprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbPresupuestos)) {
            AprobarPresupuesto();
        }
    }//GEN-LAST:event_btnAprobarActionPerformed

    private void btnDesaprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesaprobarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbPresupuestos)) {
            DesaprobarPresupuesto();
        }
    }//GEN-LAST:event_btnDesaprobarActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbPresupuestos)) {
            EnviarPresupuesto();
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbPresupuestos)) {
            AceptarPresupuesto();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnRechazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechazarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbPresupuestos)) {
            RechazarPresupuesto();
        }
    }//GEN-LAST:event_btnRechazarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbPresupuestos)) {
            ImprimirConEntidad(5, ObtenerValorCelda(tbPresupuestos, 1), frame);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAprobar;
    private javax.swing.JButton btnDesaprobar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnGenerarOT;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRechazar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbPresupuestos;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
