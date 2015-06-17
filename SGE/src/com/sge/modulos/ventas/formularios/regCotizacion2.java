package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBasex;
import com.sge.modulos.administracion.clases.Empleado;
import com.sge.modulos.administracion.clases.Moneda;
import com.sge.modulos.administracion.clases.Numeracion;
import com.sge.modulos.administracion.clases.ValorDefinido;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import com.sge.modulos.administracion.formularios.lisEmpleado;
import com.sge.modulos.administracion.formularios.lisMoneda;
import com.sge.modulos.administracion.formularios.lisNumeracionx;
import com.sge.modulos.inventarios.clases.Producto;
import com.sge.modulos.inventarios.formularios.lisProducto;
import com.sge.modulos.ventas.clases.Cliente;
import com.sge.modulos.ventas.clases.ContactoCliente;
import com.sge.modulos.ventas.clases.Cotizacion;
import com.sge.modulos.ventas.clases.Cotizacion2;
import com.sge.modulos.ventas.clases.EscalaListaPrecioMaquina;
import com.sge.modulos.ventas.clases.EscalaListaPrecioProducto;
import com.sge.modulos.ventas.clases.EscalaListaPrecioServicio;
import com.sge.modulos.ventas.clases.FormaPago;
import com.sge.modulos.ventas.clases.ItemCotizacion;
import com.sge.modulos.ventas.clases.ItemCotizacion2;
import com.sge.modulos.ventas.clases.ItemListaPrecioMaquina;
import com.sge.modulos.ventas.clases.ItemMaterial;
import com.sge.modulos.ventas.clases.Maquina;
import com.sge.modulos.ventas.clases.Servicio;
import com.sge.modulos.ventas.clases.ServicioCotizacion;
import com.sge.modulos.ventas.clases.ServicioUnidad;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regCotizacion2 extends frameBasex<Cotizacion2> {

    /**
     * Creates new form regCotizacion2
     */
    public regCotizacion2() {
        initComponents();
    }

    public regCotizacion2(int id) {
        initComponents();
        Init(id);
    }

    public regCotizacion2(ValorDefinido valorDefinido) {
        initComponents();
        OcultarControl(tpnlItems);
        super.Init(valorDefinido);
    }

    int id = 0;

    private ItemCotizacion2 item;
    
    private ItemMaterial itemMaterial;
    
    ImageIcon Icon_Refresh = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/refresh-16.png"));
    
    Action sele_clie = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente seleccionado = ((lisCliente) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schCliente.asingValues(seleccionado.getIdCliente(), seleccionado.getRazonSocial());
                schListaPrecioProducto.asingValues(seleccionado.getIdListaPrecioProducto(), seleccionado.getNombreListaPrecioProducto());
                schListaPrecioServicio.asingValues(seleccionado.getIdListaPrecioServicio(), seleccionado.getNombreListaPrecioServicio());
                schListaPrecioMaquina.asingValues(seleccionado.getIdListaPrecioMaquina(), seleccionado.getNombreListaPrecioMaquina());
                getEntidad().setIdListaPrecioProducto(seleccionado.getIdListaPrecioProducto());
                getEntidad().setNombreListaPrecioProducto(seleccionado.getNombreListaPrecioProducto());
                getEntidad().setIdListaPrecioServicio(seleccionado.getIdListaPrecioServicio());
                getEntidad().setNombreListaPrecioServicio(seleccionado.getNombreListaPrecioServicio());
                getEntidad().setIdListaPrecioMaquina(seleccionado.getIdListaPrecioMaquina());
                getEntidad().setNombreListaPrecioMaquina(seleccionado.getNombreListaPrecioMaquina());
            }
        }
    };

    Action sele_nume = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Numeracion seleccionado = ((lisNumeracionx) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schNumeracion.asingValues(seleccionado.getIdNumeracion(), seleccionado.getDescripcion());
                getEntidad().setNumeracionManual(seleccionado.isManual());
                txtNumero.setEnabled(seleccionado.isManual());
            }
        }
    };

    Action sele_coti = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Empleado seleccionado = ((lisEmpleado) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schCotizador.asingValues(seleccionado.getIdEmpleado(), seleccionado.getNombre());
            }
        }
    };

    Action sele_vend = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Empleado seleccionado = ((lisEmpleado) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schVendedor.asingValues(seleccionado.getIdEmpleado(), seleccionado.getNombre());
            }
        }
    };

    Action sele_mone = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Moneda seleccionado = ((lisMoneda) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMoneda.asingValues(seleccionado.getIdMoneda(), seleccionado.getSimbolo());
            }
        }
    };

    Action sele_form = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            FormaPago seleccionado = ((lisFormaPago) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schFormaPago.asingValues(seleccionado.getIdFormaPago(), seleccionado.getDescripcion());
            }
        }
    };

    Action sele_cont = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ContactoCliente seleccionado = ((lisContactoCliente) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schContacto.asingValues(seleccionado.getIdContactoCliente(), seleccionado.getNombre());
            }
        }
    };
    
    Action sele_serv = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Servicio seleccionado = ((lisServicio) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schServicioImpresion.asingValues(seleccionado.getIdServicio(), seleccionado.getDescripcion());
            }
        }
    };
    
    Action sele_maqu = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Maquina seleccionado = ((lisMaquina) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMaquina.asingValues(seleccionado.getIdMaquina(), seleccionado.getDescripcion());
                itemMaterial.setAltoMaquina(seleccionado.getAltoMaximoPliego());
                itemMaterial.setLargoMaquina(seleccionado.getLargoMaximoPliego());
            }
        }
    };
    
    Action sele_mate = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Producto seleccionado = ((lisProducto) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                AgregarFila(tbMateriales, new Object[]{0, seleccionado.getIdProducto(), "", seleccionado.getDescripcion() });
                itemMaterial = new ItemMaterial();
                itemMaterial.setAltoMaterial(seleccionado.getAlto());
                itemMaterial.setLargoMaterial(seleccionado.getLargo());
            }
        }
    };
    
    Action sele_acab = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<Servicio> seleccionados = ((lisServicio) evt.getSource()).getSeleccionados();
            if (!seleccionados.isEmpty()) {
                cliVentas cliente = new cliVentas();
                try {
                    String[] arrayJson = new String[]{new Gson().toJson(seleccionados), new Gson().toJson(getEntidad().getIdListaPrecioServicio())};
                    String json = cliente.ObtenerUnidadesPorServicios(new Gson().toJson(arrayJson));
                    String[] resultado = new Gson().fromJson(json, String[].class);
                    Servicio[] acabados = new Gson().fromJson(resultado[1], Servicio[].class);
                    for (Servicio acabado : acabados) {
                        double precio = ObtenerPrecioAcabado(acabado.getUnidades().get(0).getEscalas(), 1);
                        AgregarFila(tbAcabados,
                                new Object[]{
                                    0,
                                    acabado.getIdServicio(),
                                    acabado.getDescripcion(),
                                    acabado.getUnidades(),
                                    acabado.getUnidades().get(0),
                                    1.0,
                                    false,
                                    Icon_Refresh,
                                    precio,
                                    precio
                                });
                    }
                    AgregarBoton(tbAcabados, refresh, 7);
                    AgregarEventoChange(tbAcabados, change_acab);
                    CalcularTotalAcabados();
                } catch (Exception e) {
                    ControlarExcepcion(e);
                } finally {
                    cliente.close();
                }
            }
        }
    };

    Action refresh = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            cliVentas cliente = new cliVentas();
            try {
                int idServicio = ObtenerValorCelda(tbAcabados, 1);
                ServicioUnidad servicioUnidad = ObtenerValorCelda(tbAcabados, 4);
                String json = cliente.ObtenerEscalasListaPrecioServicioPorUnidad(new Gson().toJson(new int[]{getEntidad().getIdListaPrecioServicio(), idServicio, servicioUnidad.getIdServicioUnidad()}));
                String[] resultado = new Gson().fromJson(json, String[].class);
                EscalaListaPrecioServicio[] escalas = new Gson().fromJson(resultado[1], EscalaListaPrecioServicio[].class);
                servicioUnidad.setEscalas(Arrays.asList(escalas));
                double cantidad = ObtenerValorCelda(tbAcabados, 5);
                double precio = ObtenerPrecioAcabado(servicioUnidad.getEscalas(), cantidad);
                AsignarValorCelda(tbAcabados, precio, 8);
            } catch (Exception e) {
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
        }
    };

    Action change_acab = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] celda = (int[]) e.getSource();
            switch (celda[1]) {
                case 4:
                    ServicioUnidad servicioUnidad4 = ObtenerValorCelda(tbAcabados, celda[0], celda[1]);
                    double cantidad4 = ObtenerValorCelda(tbAcabados, celda[0], 5);
                    double precio4 = ObtenerPrecioAcabado(servicioUnidad4.getEscalas(), cantidad4);
                    AsignarValorCelda(tbAcabados, precio4, celda[0], 8);
                    break;
                case 5:
                    boolean precioManual5 = ObtenerValorCelda(tbAcabados, celda[0], 6);
                    if (precioManual5) {
                        double precio5 = ObtenerValorCelda(tbAcabados, celda[0], 8);
                        AsignarValorCelda(tbAcabados, precio5, celda[0], 8);
                    } else {
                        ServicioUnidad servicioUnidad5 = ObtenerValorCelda(tbAcabados, celda[0], 4);
                        double cantidad5 = ObtenerValorCelda(tbAcabados, celda[0], celda[1]);
                        double precio5 = ObtenerPrecioAcabado(servicioUnidad5.getEscalas(), cantidad5);
                        AsignarValorCelda(tbAcabados, precio5, celda[0], 8);
                    }
                    break;
                case 6:
                    boolean precioManual6 = ObtenerValorCelda(tbAcabados, celda[0], celda[1]);
                    if (!precioManual6) {
                        ServicioUnidad servicioUnidad6 = ObtenerValorCelda(tbAcabados, celda[0], 4);
                        double cantidad6 = ObtenerValorCelda(tbAcabados, celda[0], 5);
                        double precio6 = ObtenerPrecioAcabado(servicioUnidad6.getEscalas(), cantidad6);
                        AsignarValorCelda(tbAcabados, precio6, celda[0], 8);
                    }
                    break;
                case 8:
                    double cantidad8 = ObtenerValorCelda(tbAcabados, celda[0], 5);
                    double precio8 = ObtenerValorCelda(tbAcabados, celda[0], celda[1]);
                    AsignarValorCelda(tbAcabados, cantidad8 * precio8, celda[0], 9);
                    CalcularTotalAcabados();
                    break;
            }
        }
    };
    
    public void Init(int id) {
        this.id = id;
        if (this.id == 0) {
            lblTitulo.setText("NUEVA " + lblTitulo.getText());
            AgregarCombo(tbAcabados, 4);
            setEntidad(new Cotizacion2());
            pnlItemMaterial.setVisible(false);
        } else {
            lblTitulo.setText("MODIFICAR " + lblTitulo.getText());
            AgregarCombo(tbAcabados, 4);
            //new swObtenerCotizacion().execute();
        }
        OcultarControl(tpnlItems);
    }

    private void AsignarValores() {
//        getEntidad().setIdCliente(schCliente.getId());
//        getEntidad().setRazonSocialCliente(schCliente.getText());
//        getEntidad().setIdNumeracion(schNumeracion.getId());
//        getEntidad().setDescripcionNumeracion(schNumeracion.getText());
//        getEntidad().setNumero(txtNumero.getText());
//        getEntidad().setIdCotizador(schCotizador.getId());
//        getEntidad().setNombreCotizador(schCotizador.getText());
//        getEntidad().setIdVendedor(schVendedor.getId());
//        getEntidad().setNombreVendedor(schVendedor.getText());
//        getEntidad().setIdMoneda(schMoneda.getId());
//        getEntidad().setSimboloMoneda(schMoneda.getText());
//        //getEntidad().setFechaCreacion(txtFechaCreacion.getValue());
//        getEntidad().setIdFormaPago(schFormaPago.getId());
//        getEntidad().setDescripcionFormaPago(schFormaPago.getText());
//        getEntidad().setDescripcion(txtDescripcion.getText());
//        getEntidad().setCantidad(Integer.valueOf(txtCantidad.getText()));
//        getEntidad().setLineaProduccion(cboLineaProduccion.getSelectedItem().toString());
//        getEntidad().setIdContactoCliente(schContacto.getId());
//        getEntidad().setNombreContactoCliente(schContacto.getText());
//        getEntidad().setItems(getItems());
    }

    @Override
    public void AsignarControles() {
        schCliente.asingValues(getEntidad().getIdCliente(), getEntidad().getRazonSocialCliente());
        schListaPrecioProducto.asingValues(getEntidad().getIdListaPrecioProducto(), getEntidad().getNombreListaPrecioProducto());
        schListaPrecioServicio.asingValues(getEntidad().getIdListaPrecioServicio(), getEntidad().getNombreListaPrecioServicio());
        schListaPrecioMaquina.asingValues(getEntidad().getIdListaPrecioMaquina(), getEntidad().getNombreListaPrecioMaquina());
        schNumeracion.asingValues(getEntidad().getIdNumeracion(), getEntidad().getDescripcionNumeracion());
        txtNumero.setText(getEntidad().getNumero());
        schCotizador.asingValues(getEntidad().getIdCotizador(), getEntidad().getNombreCotizador());
        schVendedor.asingValues(getEntidad().getIdVendedor(), getEntidad().getNombreVendedor());
        schMoneda.asingValues(getEntidad().getIdMoneda(), getEntidad().getSimboloMoneda());
        txtFechaCreacion.setValue(getEntidad().getFechaCreacion());
        schFormaPago.asingValues(getEntidad().getIdFormaPago(), getEntidad().getDescripcionFormaPago());
        txtDescripcion.setText(getEntidad().getDescripcion());
        txtCantidad.setText(String.valueOf(getEntidad().getCantidad()));
        cboLineaProduccion.setSelectedItem(getEntidad().getLineaProduccion());
        schContacto.asingValues(getEntidad().getIdContactoCliente(), getEntidad().getNombreContactoCliente());
        for (ItemCotizacion2 itemCotizacion : getEntidad().getItems()) {
            AgregarElemento(lisItems, itemCotizacion);
        }
        AsignarTotales();
    }

    @Override
    public void OcultarControles() {
        OcultarControl(btnNuevoItem);
        OcultarControl(btnEliminarItem);
    }

    public class swObtenerValoresDefinidos extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {
                String json = cliente.ObtenerValorDefinidoPorUsuarioYEntidad(new Gson().toJson(new int[]{getUsuario().getIdUsuario(), 4}));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    if (resultado[2].isEmpty()) {
                        setEntidad(new Cotizacion2());
                    } else {
                        setEntidad(new Gson().fromJson(resultado[2], Cotizacion2.class));
                    }
                    getEntidad().setFechaCreacion(new Gson().fromJson(resultado[1], Date.class));
                    AsignarControles();
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
            return null;
        }

        @Override
        protected void done() {
            OcultarCargando(frame);
        }
    }
    /*
    public class swObtenerCotizacion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                String json = cliVentas.ObtenerCotizacion(new Gson().toJson(id));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    setEntidad(new Gson().fromJson(resultado[1], Cotizacion.class));
                    AsignarControles();
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
    */
    /*
    public List<ItemCotizacion> getItems() {
        for (ItemCotizacion item : getEntidad().getItems()) {
            if (item.getIdItemCotizacion() == 0) {
                item.setAgregar(true);
            } else if (!item.isEliminar()) {
                item.setActualizar(true);
            }
        }
        return getEntidad().getItems();
    }
    */
    /*
    public class swGuardarCotizacion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliVentas = new cliVentas();
            String json = "";
            try {
                AsignarValores();
                if (id == 0) {
                    json = cliVentas.RegistrarCotizacion(new Gson().toJson(getEntidad()));
                } else {
                    getEntidad().setIdCotizacion(id);
                    json = cliVentas.ActualizarCotizacion(new Gson().toJson(getEntidad()));
                }
            } catch (Exception e) {
                OcultarProcesando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliVentas.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    setClosed(true);
                } else {
                    OcultarProcesando(frame);
                    ControlarExcepcion(resultado);
                }
            } catch (Exception e) {
                OcultarProcesando(frame);
                ControlarExcepcion(e);
            }
        }
    }
    */
    private void AsignarControlesItem() {
        txtNombreItem.setText(this.item.getNombre());
        chkIncluirEnPresupuesto.setSelected(this.item.isIncluirEnPresupuesto());
        chkMostrarPrecioEnPresupuesto.setSelected(this.item.isMostrarPrecioEnPresupuesto());
        /*
        txtNombreItem.setText(this.item.getNombre());
        txtCantidadItem.setText(String.valueOf(this.item.getCantidad()));
        btnGenerarGraficoPrecorte.setVisible(this.item.isGrafico());
        btnGenerarGraficoImpresion.setVisible(this.item.isGrafico());
        chkMedidaAbierta.setVisible(this.item.isMedidaAbierta());
        chkMedidaAbierta.setSelected(this.item.isMedidaAbierta());
        lblAltoMedidaAbierta.setVisible(this.item.isMedidaAbierta());
        txtAltoMedidaAbierta.setVisible(this.item.isMedidaAbierta());
        lblLargoMedidaAbierta.setVisible(this.item.isMedidaAbierta());
        txtLargoMedidaAbierta.setVisible(this.item.isMedidaAbierta());
        cboUnidadMedidaAbierta.setVisible(this.item.isMedidaAbierta());
        txtAltoMedidaAbierta.setText(String.valueOf(this.item.getAltoMedidaAbierta()));
        txtLargoMedidaAbierta.setText(String.valueOf(this.item.getLargoMedidaAbierta()));
        cboUnidadMedidaAbierta.setSelectedItem(this.item.getUnidadMedidaAbierta());
        chkMedidaCerrada.setVisible(this.item.isMedidaCerrada());
        chkMedidaCerrada.setSelected(this.item.isMedidaCerrada());
        lblAltoMedidaCerrada.setVisible(this.item.isMedidaCerrada());
        txtAltoMedidaCerrada.setVisible(this.item.isMedidaCerrada());
        lblLargoMedidaCerrada.setVisible(this.item.isMedidaCerrada());
        txtLargoMedidaCerrada.setVisible(this.item.isMedidaCerrada());
        txtAltoMedidaCerrada.setText(String.valueOf(this.item.getAltoMedidaCerrada()));
        txtLargoMedidaCerrada.setText(String.valueOf(this.item.getLargoMedidaCerrada()));
        chkTiraRetira.setVisible(this.item.isTiraRetira());
        chkTiraRetira.setSelected(this.item.isTiraRetira());
        lblTiraColor.setVisible(this.item.isTiraRetira());
        txtTiraColor.setVisible(this.item.isTiraRetira());
        lblRetiraColor.setVisible(this.item.isTiraRetira());
        txtRetiraColor.setVisible(this.item.isTiraRetira());
        txtTiraColor.setText(String.valueOf(this.item.getTiraColor()));
        txtRetiraColor.setText(String.valueOf(this.item.getRetiraColor()));
        chkTipoUnidad.setVisible(this.item.isTipoUnidad());
        chkTipoUnidad.setSelected(this.item.isTipoUnidad());
        cboTipoUnidad.setVisible(this.item.isTipoUnidad());
        cboTipoUnidad.setSelectedItem(this.item.getNombreTipoUnidad());
        txtCantidadTipoUnidad.setVisible(this.item.isTipoUnidad());
        txtCantidadTipoUnidad.setText(String.valueOf(this.item.getCantidadTipoUnidad()));
        chkFondo.setVisible(this.item.isFondo());
        chkFondo.setSelected(this.item.isFondo());
        txtFondo.setVisible(this.item.isFondo());
        txtFondo.setText(String.valueOf(this.item.getdFondo()));
        chkServicioImpresion.setVisible(this.item.isServicioImpresion());
        chkServicioImpresion.setSelected(this.item.isServicioImpresion());
        schServicioImpresion.setVisible(this.item.isServicioImpresion());
        lblMaquina.setVisible(this.item.isServicioImpresion());
        schMaquina.setVisible(this.item.isServicioImpresion());
        schServicioImpresion.asingValues(this.item.getIdServicioImpresion(), this.item.getNombreServicioImpresion());
        schMaquina.asingValues(this.item.getIdMaquina(), this.item.getDescripcionMaquina());
        chkMaterial.setVisible(this.item.isMaterial());
        chkMaterial.setSelected(this.item.isMaterial());
        schMaterial.setVisible(this.item.isMaterial());
        schMaterial.asingValues(this.item.getIdMaterial(), this.item.getNombreMaterial());
        chkIncluirEnPresupuesto.setSelected(this.item.isIncluirEnPresupuesto());
        chkMostrarPrecioEnPresupuesto.setSelected(this.item.isVerPrecioEnPresupuesto());
        EliminarTodasFilas(tbAcabados);
        for (ServicioCotizacion acabado : this.item.getAcabados()) {
            if (!acabado.isEliminar()) {
                AgregarFila(tbAcabados,
                        new Object[]{
                            acabado.getIdServicioCotizacion(),
                            acabado.getIdServicio(),
                            acabado.getDescripcionServicio(),
                            acabado.getUnidades(),
                            acabado.getUnidad(),
                            acabado.getCantidad(),
                            acabado.isPrecioManual(),
                            Icon_Refresh,
                            acabado.getPrecio(),
                            acabado.getTotal()
                        });
            }
        }
        AgregarBoton(tbAcabados, refresh, 7);
        AgregarEventoChange(tbAcabados, change_acab);
        AsignarTotalAcabados();
        AsignarTotalesItem();
        */
    }
    /*
    private List<ServicioCotizacion> getAcabados() {
        this.item.getAcabados().removeIf(a -> a.isEliminar() == false);
        for (int i = 0; i < tbAcabados.getRowCount(); i++) {
            int idServicioCotizacion = ObtenerValorCelda(tbAcabados, i, 0);
            ServicioCotizacion acabado = new ServicioCotizacion();
            acabado.setIdServicio(ObtenerValorCelda(tbAcabados, i, 1));
            acabado.setDescripcionServicio(ObtenerValorCelda(tbAcabados, i, 2));
            ServicioUnidad servicioUnidad = ObtenerValorCelda(tbAcabados, i, 4);
            acabado.setIdServicioUnidad(servicioUnidad.getIdServicioUnidad());
            acabado.setAbreviacionUnidad(servicioUnidad.getAbreviacionUnidad());
            acabado.setCantidad(ObtenerValorCelda(tbAcabados, i, 5));
            acabado.setPrecio(ObtenerValorCelda(tbAcabados, i, 8));
            acabado.setTotal(ObtenerValorCelda(tbAcabados, i, 9));
            if (idServicioCotizacion == 0) {
                acabado.setAgregar(true);
            } else {
                acabado.setIdServicioCotizacion(idServicioCotizacion);
                acabado.setActualizar(true);
            }
            this.item.getAcabados().add(acabado);
        }
        return this.item.getAcabados();
    }
    */
    private void AsignarValoresItem() {
        /*
        this.item.setNombre(txtNombreItem.getText());
        this.item.setCantidad(Integer.valueOf(txtCantidadItem.getText()));
        this.item.setMedidaAbierta(chkMedidaAbierta.isSelected());
        this.item.setAltoMedidaAbierta(Double.parseDouble(txtAltoMedidaAbierta.getText()));
        this.item.setLargoMedidaAbierta(Double.parseDouble(txtLargoMedidaAbierta.getText()));
        if (cboUnidadMedidaAbierta.getSelectedItem() != null) {
            this.item.setUnidadMedidaAbierta(cboUnidadMedidaAbierta.getSelectedItem().toString());
        }
        this.item.setMedidaCerrada(chkMedidaCerrada.isSelected());
        this.item.setAltoMedidaCerrada(Double.parseDouble(txtAltoMedidaCerrada.getText()));
        this.item.setLargoMedidaCerrada(Double.parseDouble(txtLargoMedidaCerrada.getText()));
        this.item.setTiraRetira(chkTiraRetira.isSelected());
        this.item.setTiraColor(Double.parseDouble(txtTiraColor.getText()));
        this.item.setRetiraColor(Double.parseDouble(txtTiraColor.getText()));
        this.item.setTipoUnidad(chkTipoUnidad.isSelected());
        if (cboTipoUnidad.getSelectedItem() != null) {
            this.item.setNombreTipoUnidad(cboTipoUnidad.getSelectedItem().toString());
        }
        this.item.setCantidadTipoUnidad(Integer.parseInt(txtCantidadTipoUnidad.getText()));
        this.item.setFondo(chkFondo.isSelected());
        this.item.setdFondo(Double.parseDouble(txtFondo.getText()));
        this.item.setServicioImpresion(chkServicioImpresion.isSelected());
        this.item.setIdServicioImpresion(schServicioImpresion.getId());
        this.item.setNombreServicioImpresion(schServicioImpresion.getText());
        this.item.setIdMaquina(schMaquina.getId());
        this.item.setDescripcionMaquina(schMaquina.getText());
        this.item.setMaterial(chkMaterial.isSelected());
        this.item.setIdMaterial(schMaterial.getId());
        this.item.setNombreMaterial(schMaterial.getText());
        this.item.setIncluirEnPresupuesto(chkIncluirEnPresupuesto.isSelected());
        this.item.setVerPrecioEnPresupuesto(chkMostrarPrecioEnPresupuesto.isSelected());
        this.item.setAcabados(getAcabados());
        */
    }

    private void schClienteSearch() {
        VerModal(new lisCliente(1), sele_clie);
    }
    
    private void schClienteClear() {
        schListaPrecioProducto.asingValues(0, null);
        schListaPrecioServicio.asingValues(0, null);
        schListaPrecioMaquina.asingValues(0, null);
        getEntidad().setIdListaPrecioProducto(0);
        getEntidad().setNombreListaPrecioProducto(null);
        getEntidad().setIdListaPrecioServicio(0);
        getEntidad().setNombreListaPrecioServicio(null);
        getEntidad().setIdListaPrecioMaquina(0);
        getEntidad().setNombreListaPrecioMaquina(null);
    }

    private void schCotizadorSearch() {
        VerModal(new lisEmpleado(1), sele_coti);
    }

    private void schVendedorSearch() {
        VerModal(new lisEmpleado(1), sele_vend);
    }

    private void schNumeracionSearch() {
        String filtro = "WHERE Numeracion.idEntidad = 4";
        VerModal(new lisNumeracionx(1, filtro), sele_nume);
    }

    private void schMonedaSearch() {
        VerModal(new lisMoneda(1), sele_mone);
    }

    private void schFormaPagoSearch() {
        VerModal(new lisFormaPago(1), sele_form);
    }

    private void schMaquinaSearch() {
        VerModal(new lisMaquina(1), sele_maqu);
    }

    private void schServicioImpresionSearch() {
        String filtro = "WHERE Servicio.servicioImpresion = TRUE";
        VerModal(new lisServicio(1, filtro), sele_serv);
    }

    private void schMaterialSearch() {
        VerModal(new lisProducto(1), sele_mate);
    }
    
    private void schMaterialClear() {
        this.itemMaterial.setAltoMaterial(0);
        this.itemMaterial.setLargoMaterial(0);
    }
    
    private void schContactoSearch() {
        String filtro = "WHERE ContactoCliente.idCliente = " + schCliente.getId();
        VerModal(new lisContactoCliente(filtro), sele_cont);
    }
    
    public void Aceptar() {
//        if (super.isFromJson()) {
//            AsignarValores();
//            setJson(new Gson().toJson(super.getEntidad()));
//            Cerrar();
//        } else {
//            if (getEntidad().getIdCotizacion() == 0 || "PENDIENTE DE APROBACIÓN".equals(getEntidad().getEstado())) {
//                new swGuardarCotizacion().execute();
//            } else {
//                VerAdvertencia("SÓLO SE PUEDE MODIFICAR CUANDO EL ESTADO ES : PENDIENTE DE APROBACIÓN.", frame);
//            }
//        }
    }

    public void Cancelar() {
        Cerrar();
    }

    public void AsignarTotales() {
        NumberFormat formato = new DecimalFormat("#0.00");
        txtSubTotal.setText(getEntidad().getSimboloMoneda() + formato.format(getEntidad().getSubTotal()));
        txtUtilidad.setText(getEntidad().getSimboloMoneda() + formato.format(getEntidad().getMontoUtilidad()));
        txtTotal.setText(getEntidad().getSimboloMoneda() + formato.format(getEntidad().getTotal()));
    }

    public void AsignarTotalesItem() {
        NumberFormat formato = new DecimalFormat("#0.00");
        //txtTotalMaquina.setText(getEntidad().getSimboloMoneda() + formato.format(this.item.getTotalMaquina()));
        //txtTotalMaterial.setText(getEntidad().getSimboloMoneda() + formato.format(this.item.getTotalMaterial()));
    }

    public void AsignarTotalAcabados() {
        NumberFormat formato = new DecimalFormat("#0.00");
        //txtTotalAcabados.setText(getEntidad().getSimboloMoneda() + formato.format(this.item.getTotalAcabados()));
    }

    public double ObtenerPrecioAcabado(List<EscalaListaPrecioServicio> escalas, double cantidad) {
        double precio = 0.0;
        for (EscalaListaPrecioServicio escala : escalas) {
            if (escala.getDesde() == 0 && escala.getHasta() == 0) {
                precio = escala.getPrecio();
                break;
            }
            if (escala.getDesde() <= cantidad && escala.getHasta() >= cantidad) {
                precio = escala.getPrecio();
                break;
            }
            if (escala.getDesde() <= cantidad && escala.getHasta() == 0) {
                precio = escala.getPrecio();
                break;
            }
        }
        return precio;
    }

    public void CalcularTotalAcabados() {
        double total = 0.0;
        for (int i = 0; i < tbAcabados.getRowCount(); i++) {
            double totalItem = ObtenerValorCelda(tbAcabados, i, 9);
            total += totalItem;
        }
        //this.item.setTotalAcabados(total);
        AsignarTotalAcabados();
    }

    public void Calcular() {
//        cliVentas cliVentas = new cliVentas();
//        try {
//            double subTotal = 0;
//            for (ItemCotizacion item : getEntidad().getItems()) {
//                String json = cliVentas.ObtenerEscalasMaquinaYProducto(new Gson().toJson(new int[]{getEntidad().getIdListaPrecioMaquina(), item.getIdMaquina(), getEntidad().getIdListaPrecioProducto(), item.getIdMaterial(), item.getIdUnidadMaterial()}));
//                String[] resultado = new Gson().fromJson(json, String[].class);
//
//                if (resultado[0].equals("true")) {
//                    ItemListaPrecioMaquina[] itemsListaPrecioMaquina = new Gson().fromJson(resultado[1], ItemListaPrecioMaquina[].class);
//
//                    if (itemsListaPrecioMaquina.length == 0) {
//                        VerAdvertencia("NO SE ENCONTRÓ UNA ESCALA PARA LA MÁQUINA: " + item.getDescripcionMaquina(), frame);
//                        break;
//                    }
//
//                    int cantidadIntMaquina = item.getCantidadProduccion() / itemsListaPrecioMaquina[0].getFactor();
//                    double cantidadDoubleMaquina = item.getCantidadProduccion() / itemsListaPrecioMaquina[0].getFactor();
//                    double precioMaquina = 0;
//
//                    for (EscalaListaPrecioMaquina escala : itemsListaPrecioMaquina[0].getEscalas()) {
//                        if (escala.getDesde() == 0 && escala.getHasta() == 0) {
//                            precioMaquina = escala.getPrecio();
//                            break;
//                        }
//                        if (escala.getDesde() <= cantidadIntMaquina && escala.getHasta() >= cantidadIntMaquina) {
//                            precioMaquina = escala.getPrecio();
//                            break;
//                        }
//                        if (escala.getDesde() <= cantidadIntMaquina && escala.getHasta() == 0) {
//                            precioMaquina = escala.getPrecio();
//                            break;
//                        }
//                    }
//
//                    if (precioMaquina == 0) {
//                        VerAdvertencia("NO SE ENCONTRÓ UNA ESCALA PARA LA MÁQUINA: " + item.getDescripcionMaquina(), frame);
//                        break;
//                    } else {
//                        double totalMaquina = cantidadDoubleMaquina * precioMaquina;
//                        if (item.isTiraRetira()) {
//                            double nuevoTotalMaquina = 0;
//                            if (item.getCantidadCambios() == 2) {
//                                double factorTira = item.getTiraColor() / 4;
//                                double factorRetira = item.getRetiraColor() / 4;
//                                nuevoTotalMaquina = totalMaquina * factorTira;
//                                nuevoTotalMaquina += totalMaquina * factorRetira;
//                            } else {
//                                double factorTira = item.getTiraColor() / 4;
//                                if (item.getCantidadPliegos() == 1) {
//                                    nuevoTotalMaquina = totalMaquina * factorTira;
//                                }
//                            }
//                            item.setTotalMaquina(nuevoTotalMaquina * item.getCantidadPliegos());
//                        } else {
//                            item.setTotalMaquina(totalMaquina);
//                        }
//                    }
//
//                    EscalaListaPrecioProducto[] escalasProducto = new Gson().fromJson(resultado[2], EscalaListaPrecioProducto[].class);
//
//                    int cantidadMaterial = item.getCantidadMaterial() + item.getCantidadDemasiaMaterial();
//                    double precioMaterial = 0;
//                    for (EscalaListaPrecioProducto escala : escalasProducto) {
//                        if (escala.getDesde() == 0 && escala.getHasta() == 0) {
//                            precioMaterial = escala.getPrecio();
//                            break;
//                        }
//                        if (escala.getDesde() <= cantidadMaterial && escala.getHasta() >= cantidadMaterial) {
//                            precioMaterial = escala.getPrecio();
//                            break;
//                        }
//                        if (escala.getDesde() <= cantidadMaterial && escala.getHasta() == 0) {
//                            precioMaterial = escala.getPrecio();
//                            break;
//                        }
//                    }
//
//                    if (precioMaterial == 0) {
//                        VerAdvertencia("NO SE ENCONTRÓ UNA ESCALA PARA EL MATERIAL: " + item.getNombreMaterial(), frame);
//                        break;
//                    } else {
//                        item.setCantidadPliegos((item.getCantidadPliegos() == 0) ? 1 : item.getCantidadPliegos());
//                        item.setTotalMaterial(precioMaterial * cantidadMaterial * item.getCantidadPliegos());
//                    }
//                }
//                subTotal += item.getTotalMaquina() + item.getTotalMaterial();
//            }
//            double porcentajeUtilidad = Double.parseDouble(txtPorcentajeUtilidad.getText());
//            double utilidad = subTotal * (porcentajeUtilidad / 100);
//            getEntidad().setSubTotal(subTotal);
//            getEntidad().setMontoUtilidad(utilidad);
//            getEntidad().setTotal(subTotal + utilidad);
//            AsignarTotalesItem();
//            AsignarTotales();
//        } catch (Exception e) {
//            ControlarExcepcion(e);
//        } finally {
//            cliVentas.close();
//        }
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
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblLineaProduccion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        cboLineaProduccion = new javax.swing.JComboBox();
        tpnlItems = new javax.swing.JTabbedPane();
        pnlItem = new javax.swing.JPanel();
        btnGuardarItem = new javax.swing.JButton();
        lblNombreItem = new javax.swing.JLabel();
        txtNombreItem = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        chkIncluirEnPresupuesto = new javax.swing.JCheckBox();
        chkMostrarPrecioEnPresupuesto = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbMateriales = new javax.swing.JTable();
        btnNuevoItemMaterial = new javax.swing.JButton();
        btnEliminarItemMaterial = new javax.swing.JButton();
        pnlItemMaterial = new javax.swing.JPanel();
        lblCantidadItem = new javax.swing.JLabel();
        txtCantidadItem = new javax.swing.JTextField();
        btnGenerarGraficoPrecorte = new javax.swing.JButton();
        btnGenerarGraficoImpresion = new javax.swing.JButton();
        lblMedidaAbierta = new javax.swing.JLabel();
        lblAltoMedidaAbierta = new javax.swing.JLabel();
        txtAltoMedidaAbierta = new javax.swing.JTextField();
        lblLargoMedidaAbierta = new javax.swing.JLabel();
        txtLargoMedidaAbierta = new javax.swing.JTextField();
        lblMedidaCerrada = new javax.swing.JLabel();
        lblAltoMedidaCerrada = new javax.swing.JLabel();
        txtAltoMedidaCerrada = new javax.swing.JTextField();
        lblLargoMedidaCerrada = new javax.swing.JLabel();
        txtLargoMedidaCerrada = new javax.swing.JTextField();
        lblTiraYRetira = new javax.swing.JLabel();
        lblTiraColor = new javax.swing.JLabel();
        txtTiraColor = new javax.swing.JTextField();
        lblRetiraColor = new javax.swing.JLabel();
        txtRetiraColor = new javax.swing.JTextField();
        schServicioImpresion = new com.sge.base.controles.JSearch();
        lblImpresion = new javax.swing.JLabel();
        schMaquina = new com.sge.base.controles.JSearch();
        lblMaquina = new javax.swing.JLabel();
        tabAcabados = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbAcabados = new javax.swing.JTable();
        btnNuevoAcabado = new javax.swing.JButton();
        btnEliminarAcabado = new javax.swing.JButton();
        txtTotalAcabados = new javax.swing.JTextField();
        tabInformacionAdicional = new javax.swing.JPanel();
        lblListaPrecioProducto = new javax.swing.JLabel();
        schListaPrecioProducto = new com.sge.base.controles.JSearch();
        schListaPrecioServicio = new com.sge.base.controles.JSearch();
        lblListaPrecioServicio = new javax.swing.JLabel();
        lblListaPrecioMaquina = new javax.swing.JLabel();
        schListaPrecioMaquina = new com.sge.base.controles.JSearch();
        jSeparator2 = new javax.swing.JSeparator();
        lblContacto = new javax.swing.JLabel();
        schContacto = new com.sge.base.controles.JSearch();
        jPanel1 = new javax.swing.JPanel();
        btnNuevoItem = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lisItems = new javax.swing.JList();
        lblCliente = new javax.swing.JLabel();
        schCliente = new com.sge.base.controles.JSearch();
        lblVendedor = new javax.swing.JLabel();
        schVendedor = new com.sge.base.controles.JSearch();
        schNumeracion = new com.sge.base.controles.JSearch();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtFechaCreacion = new javax.swing.JFormattedTextField();
        schMoneda = new com.sge.base.controles.JSearch();
        lblMoneda = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        schFormaPago = new com.sge.base.controles.JSearch();
        lblCotizador = new javax.swing.JLabel();
        schCotizador = new com.sge.base.controles.JSearch();
        lblFormaPago1 = new javax.swing.JLabel();
        lblObservacion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblPorcentajeUtilidad = new javax.swing.JLabel();
        txtPorcentajeUtilidad = new javax.swing.JTextField();
        btnCalcular = new javax.swing.JButton();
        lblSubtotal = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        lblUtilidad = new javax.swing.JLabel();
        txtUtilidad = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("COTIZACIÓN");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        lblLineaProduccion.setText("L. PRODUCCIÓN");

        lblDescripcion.setText("DESCRIPCIÓN");

        cboLineaProduccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PUBLICIDAD", "OFFSET", "DIGITAL", "OTROS" }));

        tpnlItems.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        pnlItem.setBackground(java.awt.Color.white);
        pnlItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGuardarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"))); // NOI18N
        btnGuardarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarItemActionPerformed(evt);
            }
        });

        lblNombreItem.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblNombreItem.setText("NOMBRE");

        txtNombreItem.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N

        chkIncluirEnPresupuesto.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        chkIncluirEnPresupuesto.setText("INCLUIR EN PRESUPUESTO");

        chkMostrarPrecioEnPresupuesto.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        chkMostrarPrecioEnPresupuesto.setText("MOSTRAR PRECIO EN PRESUPUESTO");

        tbMateriales.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        tbMateriales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDMATERIAL", "NOMBRE", "MATERIAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbMateriales.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbMateriales.setRowHeight(25);
        tbMateriales.setShowHorizontalLines(false);
        tbMateriales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMaterialesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbMateriales);
        if (tbMateriales.getColumnModel().getColumnCount() > 0) {
            tbMateriales.getColumnModel().getColumn(0).setMinWidth(0);
            tbMateriales.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbMateriales.getColumnModel().getColumn(0).setMaxWidth(0);
            tbMateriales.getColumnModel().getColumn(1).setMinWidth(0);
            tbMateriales.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbMateriales.getColumnModel().getColumn(1).setMaxWidth(0);
            tbMateriales.getColumnModel().getColumn(3).setPreferredWidth(250);
        }

        btnNuevoItemMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevoItemMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoItemMaterialActionPerformed(evt);
            }
        });

        btnEliminarItemMaterial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarItemMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemMaterialActionPerformed(evt);
            }
        });

        pnlItemMaterial.setBackground(java.awt.Color.white);
        pnlItemMaterial.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCantidadItem.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblCantidadItem.setText("CANTIDAD");

        txtCantidadItem.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtCantidadItem.setText("0");

        btnGenerarGraficoPrecorte.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnGenerarGraficoPrecorte.setText("G. PRECORTE");
        btnGenerarGraficoPrecorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarGraficoPrecorteActionPerformed(evt);
            }
        });

        btnGenerarGraficoImpresion.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        btnGenerarGraficoImpresion.setText("G.IMPRESIÓN");
        btnGenerarGraficoImpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarGraficoImpresionActionPerformed(evt);
            }
        });

        lblMedidaAbierta.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblMedidaAbierta.setText("MEDIDA ABIERTA");

        lblAltoMedidaAbierta.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblAltoMedidaAbierta.setText("ALTO");

        txtAltoMedidaAbierta.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtAltoMedidaAbierta.setText("0");

        lblLargoMedidaAbierta.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblLargoMedidaAbierta.setText("LARGO");

        txtLargoMedidaAbierta.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtLargoMedidaAbierta.setText("0");

        lblMedidaCerrada.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblMedidaCerrada.setText("MEDIDA CERRADA");

        lblAltoMedidaCerrada.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblAltoMedidaCerrada.setText("ALTO");

        txtAltoMedidaCerrada.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtAltoMedidaCerrada.setText("0");

        lblLargoMedidaCerrada.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblLargoMedidaCerrada.setText("LARGO");

        txtLargoMedidaCerrada.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtLargoMedidaCerrada.setText("0");

        lblTiraYRetira.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblTiraYRetira.setText("TIRA Y RETIRA");

        lblTiraColor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblTiraColor.setText("TIRA");

        txtTiraColor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtTiraColor.setText("0");

        lblRetiraColor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        lblRetiraColor.setText("RETIRA ");

        txtRetiraColor.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        txtRetiraColor.setText("0");

        schServicioImpresion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schServicioImpresionSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblImpresion.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblImpresion.setText("IMPRESIÓN");

        schMaquina.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMaquinaSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblMaquina.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        lblMaquina.setText("MÁQUINA");

        javax.swing.GroupLayout pnlItemMaterialLayout = new javax.swing.GroupLayout(pnlItemMaterial);
        pnlItemMaterial.setLayout(pnlItemMaterialLayout);
        pnlItemMaterialLayout.setHorizontalGroup(
            pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schServicioImpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                        .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                                .addComponent(lblAltoMedidaAbierta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAltoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLargoMedidaAbierta)
                                .addGap(5, 5, 5)
                                .addComponent(txtLargoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMedidaAbierta)
                            .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                                .addComponent(lblCantidadItem)
                                .addGap(4, 4, 4)
                                .addComponent(txtCantidadItem, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                                .addComponent(btnGenerarGraficoPrecorte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGenerarGraficoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                                .addComponent(lblAltoMedidaCerrada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAltoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLargoMedidaCerrada)
                                .addGap(7, 7, 7)
                                .addComponent(txtLargoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMedidaCerrada)))
                    .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                        .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(txtTiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblRetiraColor)
                                .addGap(5, 5, 5)
                                .addComponent(txtRetiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTiraYRetira)
                            .addComponent(lblImpresion)
                            .addComponent(lblMaquina))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(schMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlItemMaterialLayout.setVerticalGroup(
            pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidadItem)
                    .addComponent(txtCantidadItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarGraficoImpresion)
                    .addComponent(btnGenerarGraficoPrecorte))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                        .addComponent(lblMedidaAbierta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAltoMedidaAbierta)
                            .addComponent(txtAltoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLargoMedidaAbierta)
                            .addComponent(txtLargoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAltoMedidaCerrada)
                        .addComponent(txtAltoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLargoMedidaCerrada)
                        .addComponent(txtLargoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlItemMaterialLayout.createSequentialGroup()
                        .addComponent(lblMedidaCerrada)
                        .addGap(31, 31, 31)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTiraYRetira)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemMaterialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTiraColor)
                    .addComponent(lblRetiraColor)
                    .addComponent(txtRetiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImpresion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblMaquina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlItemLayout = new javax.swing.GroupLayout(pnlItem);
        pnlItem.setLayout(pnlItemLayout);
        pnlItemLayout.setHorizontalGroup(
            pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(chkIncluirEnPresupuesto)
                                .addGap(31, 31, 31)
                                .addComponent(chkMostrarPrecioEnPresupuesto)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(276, 276, 276)
                        .addComponent(btnGuardarItem))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(lblNombreItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarItemMaterial)
                            .addComponent(btnNuevoItemMaterial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlItemMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        pnlItemLayout.setVerticalGroup(
            pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreItem)
                            .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(btnNuevoItemMaterial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarItemMaterial))))
                    .addComponent(pnlItemMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(chkMostrarPrecioEnPresupuesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(chkIncluirEnPresupuesto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnGuardarItem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnlItems.addTab("ITEM", pnlItem);

        tabAcabados.setBackground(java.awt.Color.white);
        tabAcabados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbAcabados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDSERVICIO", "DESCRIPCION", "ARRAYUNIDAD", "UNIDAD", "CANTIDAD", "MANUAL", "", "PRECIO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAcabados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbAcabados.setRowHeight(25);
        tbAcabados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbAcabados.setShowHorizontalLines(false);
        jScrollPane4.setViewportView(tbAcabados);

        btnNuevoAcabado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevoAcabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAcabadoActionPerformed(evt);
            }
        });

        btnEliminarAcabado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarAcabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAcabadoActionPerformed(evt);
            }
        });

        txtTotalAcabados.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalAcabados.setText("0");

        javax.swing.GroupLayout tabAcabadosLayout = new javax.swing.GroupLayout(tabAcabados);
        tabAcabados.setLayout(tabAcabadosLayout);
        tabAcabadosLayout.setHorizontalGroup(
            tabAcabadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabAcabadosLayout.createSequentialGroup()
                .addGroup(tabAcabadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabAcabadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtTotalAcabados, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabAcabadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoAcabado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarAcabado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabAcabadosLayout.setVerticalGroup(
            tabAcabadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAcabadosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNuevoAcabado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarAcabado)
                .addContainerGap(238, Short.MAX_VALUE))
            .addGroup(tabAcabadosLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(txtTotalAcabados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpnlItems.addTab("ACABADOS", tabAcabados);

        tabInformacionAdicional.setBackground(java.awt.Color.white);
        tabInformacionAdicional.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblListaPrecioProducto.setText("L.P.PRODUCTO");

        schListaPrecioProducto.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
            }
            @Override
            public void Clear(){
            }
        });
        schListaPrecioProducto.setEnabled(false);

        schListaPrecioServicio.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
            }
            @Override
            public void Clear(){
            }
        });
        schListaPrecioServicio.setEnabled(false);

        lblListaPrecioServicio.setText("L.P.SERVICIO");

        lblListaPrecioMaquina.setText("L.P.MAQUINA");

        schListaPrecioMaquina.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
            }
            @Override
            public void Clear(){
            }
        });
        schListaPrecioMaquina.setEnabled(false);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblContacto.setText("CONTACTO");

        schContacto.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schContactoSearch();
            }
            @Override
            public void Clear(){
            }
        });

        javax.swing.GroupLayout tabInformacionAdicionalLayout = new javax.swing.GroupLayout(tabInformacionAdicional);
        tabInformacionAdicional.setLayout(tabInformacionAdicionalLayout);
        tabInformacionAdicionalLayout.setHorizontalGroup(
            tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaPrecioProducto)
                    .addComponent(lblListaPrecioServicio)
                    .addComponent(lblListaPrecioMaquina))
                .addGap(58, 58, 58)
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContacto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        tabInformacionAdicionalLayout.setVerticalGroup(
            tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addComponent(lblContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                        .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        tpnlItems.addTab("INFORMACION ADICIONAL", tabInformacionAdicional);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );

        tpnlItems.addTab("tab4", jPanel1);

        btnNuevoItem.setText("NUEVO");
        btnNuevoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoItemActionPerformed(evt);
            }
        });

        btnEliminarItem.setText("ELIMINAR");
        btnEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemActionPerformed(evt);
            }
        });

        lisItems.setModel(new DefaultListModel());
        lisItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lisItems.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lisItemsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lisItems);

        lblCliente.setText("CLIENTE");

        schCliente.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schClienteSearch();
            }
            @Override
            public void Clear(){
                schClienteClear();
            }
        });

        lblVendedor.setText("VENDEDOR");

        schVendedor.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schVendedorSearch();
            }
            @Override
            public void Clear(){
            }
        });

        schNumeracion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schNumeracionSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblNumero.setText("N°");

        schMoneda.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMonedaSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblMoneda.setText("MONEDA");

        lblCantidad.setText("CANTIDAD");

        txtCantidad.setText("0");

        lblFecha.setText("FECHA");

        schFormaPago.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schFormaPagoSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblCotizador.setText("COTIZADOR");

        schCotizador.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schCotizadorSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblFormaPago1.setText("FORMA PAGO");

        lblObservacion.setText("OBSERVACIÓN");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        lblPorcentajeUtilidad.setText("% UTILIDAD");

        txtPorcentajeUtilidad.setText("0");

        btnCalcular.setText("CALCULAR");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        lblSubtotal.setText("SUBTOTAL");

        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setText("0");

        lblUtilidad.setText("UTILIDAD");

        txtUtilidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUtilidad.setText("0");

        lblTotal.setText("TOTAL");

        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(btnNuevoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblObservacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tpnlItems, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(lblPorcentajeUtilidad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPorcentajeUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblSubtotal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1))
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblUtilidad)
                                            .addComponent(lblTotal))
                                        .addGap(23, 23, 23)
                                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTotal)
                                            .addComponent(txtUtilidad))))
                                .addGap(9, 9, 9))))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCotizador, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                        .addGap(544, 544, 544)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblFecha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)
                                        .addComponent(lblFormaPago1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(schFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(schCotizador, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblVendedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(schVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(26, 26, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(schNumeracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(schCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFormaPago1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(schCotizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCotizador, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(schVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarItem)
                            .addComponent(btnNuevoItem))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                    .addComponent(tpnlItems))
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotal)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnAceptar)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPorcentajeUtilidad)
                            .addComponent(txtPorcentajeUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCalcular)
                            .addComponent(lblSubtotal)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUtilidad)
                            .addComponent(txtUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblObservacion)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarItemActionPerformed
        // TODO add your handling code here:
        AsignarValoresItem();
        lisItems.updateUI();
        VerAdvertencia("ITEM GUARDADO!", this);
    }//GEN-LAST:event_btnGuardarItemActionPerformed

    private void btnGenerarGraficoPrecorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarGraficoPrecorteActionPerformed
        // TODO add your handling code here:
        //VerModal(new genGraficoPrecorte(this.item));
    }//GEN-LAST:event_btnGenerarGraficoPrecorteActionPerformed

    private void btnGenerarGraficoImpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarGraficoImpresionActionPerformed
        // TODO add your handling code here:
        //VerModal(new genGraficoImpresion(this.item));
    }//GEN-LAST:event_btnGenerarGraficoImpresionActionPerformed

    private void btnNuevoAcabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAcabadoActionPerformed
        // TODO add your handling code here:
        VerModal(new lisServicio(2), sele_acab);
    }//GEN-LAST:event_btnNuevoAcabadoActionPerformed

    private void btnEliminarAcabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAcabadoActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbAcabados)) {
            int idServicioCotizacion = ObtenerValorCelda(tbAcabados, 0);
            if (idServicioCotizacion > 0) {
                ServicioCotizacion servicioCotizacion = new ServicioCotizacion();
                servicioCotizacion.setIdServicioCotizacion(idServicioCotizacion);
                servicioCotizacion.setEliminar(true);
                //this.item.getAcabados().add(servicioCotizacion);
            }
            EliminarFila(tbAcabados);
            CalcularTotalAcabados();
        }
    }//GEN-LAST:event_btnEliminarAcabadoActionPerformed

    private void btnNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemActionPerformed
        // TODO add your handling code here:
        ItemCotizacion2 item = new ItemCotizacion2();
        item.setNombre("ITEM " + (getEntidad().getItems().size() + 1));
        getEntidad().getItems().add(item);
        AgregarElemento(lisItems, item);
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        ItemCotizacion itemPlantilla = (ItemCotizacion) lisItems.getSelectedValue();
        if (id == 0) {
            getEntidad().getItems().remove(itemPlantilla);
        } else {
            itemPlantilla.setEliminar(true);
        }
        EliminarElementoActivo(lisItems);
    }//GEN-LAST:event_btnEliminarItemActionPerformed

    private void lisItemsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lisItemsValueChanged
        // TODO add your handling code here:
        if (lisItems.getSelectedValue() != null) {
            this.item = (ItemCotizacion2) lisItems.getSelectedValue();
            AsignarControlesItem();
            AsignarTitulo(tpnlItems, 0, this.item.getNombre());
            MostrarControl(tpnlItems);
        } else {
            OcultarControl(tpnlItems);
        }
    }//GEN-LAST:event_lisItemsValueChanged

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        // TODO add your handling code here:
        Calcular();
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnNuevoItemMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemMaterialActionPerformed
        // TODO add your handling code here:
        VerModal(new lisProducto(1), sele_mate);
    }//GEN-LAST:event_btnNuevoItemMaterialActionPerformed

    private void btnEliminarItemMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarItemMaterialActionPerformed

    private void tbMaterialesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMaterialesMouseClicked
        // TODO add your handling code here:
        if(FilaActiva(tbMateriales)){
            pnlItemMaterial.setVisible(true);
        }
    }//GEN-LAST:event_tbMaterialesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarAcabado;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnEliminarItemMaterial;
    private javax.swing.JButton btnGenerarGraficoImpresion;
    private javax.swing.JButton btnGenerarGraficoPrecorte;
    private javax.swing.JButton btnGuardarItem;
    private javax.swing.JButton btnNuevoAcabado;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JButton btnNuevoItemMaterial;
    private javax.swing.JComboBox cboLineaProduccion;
    private javax.swing.JCheckBox chkIncluirEnPresupuesto;
    private javax.swing.JCheckBox chkMostrarPrecioEnPresupuesto;
    private javax.swing.JPanel frame;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAltoMedidaAbierta;
    private javax.swing.JLabel lblAltoMedidaCerrada;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCantidadItem;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblContacto;
    private javax.swing.JLabel lblCotizador;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFormaPago1;
    private javax.swing.JLabel lblImpresion;
    private javax.swing.JLabel lblLargoMedidaAbierta;
    private javax.swing.JLabel lblLargoMedidaCerrada;
    private javax.swing.JLabel lblLineaProduccion;
    private javax.swing.JLabel lblListaPrecioMaquina;
    private javax.swing.JLabel lblListaPrecioProducto;
    private javax.swing.JLabel lblListaPrecioServicio;
    private javax.swing.JLabel lblMaquina;
    private javax.swing.JLabel lblMedidaAbierta;
    private javax.swing.JLabel lblMedidaCerrada;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNombreItem;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblObservacion;
    private javax.swing.JLabel lblPorcentajeUtilidad;
    private javax.swing.JLabel lblRetiraColor;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTiraColor;
    private javax.swing.JLabel lblTiraYRetira;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblUtilidad;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JList lisItems;
    private javax.swing.JPanel pnlItem;
    private javax.swing.JPanel pnlItemMaterial;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schCliente;
    private com.sge.base.controles.JSearch schContacto;
    private com.sge.base.controles.JSearch schCotizador;
    private com.sge.base.controles.JSearch schFormaPago;
    private com.sge.base.controles.JSearch schListaPrecioMaquina;
    private com.sge.base.controles.JSearch schListaPrecioProducto;
    private com.sge.base.controles.JSearch schListaPrecioServicio;
    private com.sge.base.controles.JSearch schMaquina;
    private com.sge.base.controles.JSearch schMoneda;
    private com.sge.base.controles.JSearch schNumeracion;
    private com.sge.base.controles.JSearch schServicioImpresion;
    private com.sge.base.controles.JSearch schVendedor;
    private javax.swing.JPanel tabAcabados;
    private javax.swing.JPanel tabInformacionAdicional;
    private javax.swing.JTable tbAcabados;
    private javax.swing.JTable tbMateriales;
    private javax.swing.JTabbedPane tpnlItems;
    private javax.swing.JTextField txtAltoMedidaAbierta;
    private javax.swing.JTextField txtAltoMedidaCerrada;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidadItem;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JTextField txtLargoMedidaAbierta;
    private javax.swing.JTextField txtLargoMedidaCerrada;
    private javax.swing.JTextField txtNombreItem;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPorcentajeUtilidad;
    private javax.swing.JTextField txtRetiraColor;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTiraColor;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalAcabados;
    private javax.swing.JTextField txtUtilidad;
    // End of variables declaration//GEN-END:variables
}
