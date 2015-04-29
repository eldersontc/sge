package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Empleado;
import com.sge.modulos.administracion.clases.Moneda;
import com.sge.modulos.administracion.clases.Numeracion;
import com.sge.modulos.administracion.clases.ValorDefinido;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import com.sge.modulos.administracion.formularios.lisEmpleado;
import com.sge.modulos.administracion.formularios.lisMoneda;
import com.sge.modulos.administracion.formularios.lisNumeracion;
import com.sge.modulos.inventarios.clases.Producto;
import com.sge.modulos.inventarios.formularios.lisProducto;
import com.sge.modulos.ventas.clases.Cliente;
import com.sge.modulos.ventas.clases.FormaPago;
import com.sge.modulos.ventas.clases.ItemSolicitudCotizacion;
import com.sge.modulos.ventas.clases.Maquina;
import com.sge.modulos.ventas.clases.Servicio;
import com.sge.modulos.ventas.clases.SolicitudCotizacion;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regSolicitudCotizacion extends frameBase<SolicitudCotizacion> {

    /**
     * Creates new form regSolicitudCotizacion
     */
    public regSolicitudCotizacion() {
        initComponents();
    }

    public regSolicitudCotizacion(int id) {
        initComponents();
        Init(id);
    }
    
    public regSolicitudCotizacion(ValorDefinido valorDefinido) {
        initComponents();
        OcultarControl(tpnlItems);
        super.Init(valorDefinido);
    }

    int id = 0;

    private ItemSolicitudCotizacion item;

    Action sele_clie = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente seleccionado = ((lisCliente) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schCliente.asingValues(seleccionado.getIdCliente(), seleccionado.getRazonSocial());
            }
        }
    };

    Action sele_nume = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Numeracion seleccionado = ((lisNumeracion) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schNumeracion.asingValues(seleccionado.getIdNumeracion(), seleccionado.getDescripcion());
                getEntidad().setNumeracionManual(seleccionado.isManual());
                txtNumero.setEnabled(seleccionado.isManual());
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

    Action sele_maqu = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Maquina seleccionado = ((lisMaquina) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMaquina.asingValues(seleccionado.getIdMaquina(), seleccionado.getDescripcion());
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

    Action sele_mate = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Producto seleccionado = ((lisProducto) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMaterial.asingValues(seleccionado.getIdProducto(), seleccionado.getDescripcion());
            }
        }
    };

    public void Init(int id) {
        this.id = id;
        if (this.id == 0) {
            lblTitulo.setText("NUEVA " + lblTitulo.getText());
            new swObtenerValoresDefinidos().execute();
        } else {
            lblTitulo.setText("MODIFICAR " + lblTitulo.getText());
            new swObtenerSolicitud().execute();
        }
        OcultarControl(tpnlItems);
    }

    private void AsignarValores() {
        getEntidad().setIdCliente(schCliente.getId());
        getEntidad().setRazonSocialCliente(schCliente.getText());
        getEntidad().setIdNumeracion(schNumeracion.getId());
        getEntidad().setDescripcionNumeracion(schNumeracion.getText());
        getEntidad().setNumero(txtNumero.getText());
        getEntidad().setGrupo(txtGrupo.getText());
        getEntidad().setIdVendedor(schVendedor.getId());
        getEntidad().setNombreVendedor(schVendedor.getText());
        getEntidad().setIdMoneda(schMoneda.getId());
        getEntidad().setSimboloMoneda(schMoneda.getText());
        //getEntidad().setFechaCreacion(txtFechaCreacion.getValue());
        getEntidad().setIdFormaPago(schFormaPago.getId());
        getEntidad().setDescripcionFormaPago(schFormaPago.getText());
        getEntidad().setDescripcion(txtDescripcion.getText());
        getEntidad().setCantidad(Integer.valueOf(txtCantidad.getText()));
        getEntidad().setLineaProduccion(cboLineaProduccion.getSelectedItem().toString());
        getEntidad().setItems(getItems());
    }
    
    @Override
    public void AsignarControles() {
        schCliente.asingValues(getEntidad().getIdCliente(), getEntidad().getRazonSocialCliente());
        schNumeracion.asingValues(getEntidad().getIdNumeracion(), getEntidad().getDescripcionNumeracion());
        txtNumero.setText(getEntidad().getNumero());
        txtGrupo.setText(getEntidad().getGrupo());
        schVendedor.asingValues(getEntidad().getIdVendedor(), getEntidad().getNombreVendedor());
        schMoneda.asingValues(getEntidad().getIdMoneda(), getEntidad().getSimboloMoneda());
        txtFechaCreacion.setValue(getEntidad().getFechaCreacion());
        schFormaPago.asingValues(getEntidad().getIdFormaPago(), getEntidad().getDescripcionFormaPago());
        txtDescripcion.setText(getEntidad().getDescripcion());
        txtCantidad.setText(String.valueOf(getEntidad().getCantidad()));
        cboLineaProduccion.setSelectedItem(getEntidad().getLineaProduccion());
        for (ItemSolicitudCotizacion itemPlantilla : getEntidad().getItems()) {
            AgregarElemento(lisItems, itemPlantilla);
        }
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
                String json = cliente.ObtenerValorDefinidoPorUsuarioYEntidad(new Gson().toJson(new int[]{getUsuario().getIdUsuario(), 3}));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    if (resultado[2].isEmpty()) {
                        setEntidad(new SolicitudCotizacion());
                    } else {
                        setEntidad(new Gson().fromJson(resultado[2], SolicitudCotizacion.class));
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

    public class swObtenerSolicitud extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                String json = cliVentas.ObtenerSolicitudCotizacion(new Gson().toJson(id));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    setEntidad(new Gson().fromJson(resultado[1], SolicitudCotizacion.class));
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

    public List<ItemSolicitudCotizacion> getItems() {
        for (ItemSolicitudCotizacion item : getEntidad().getItems()) {
            if (item.getIdItemSolicitudCotizacion() == 0) {
                item.setAgregar(true);
            } else if (!item.isEliminar()) {
                item.setActualizar(true);
            }
        }
        return getEntidad().getItems();
    }

    public class swGuardarSolicitud extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliVentas = new cliVentas();
            String json = "";
            try {
                AsignarValores();
                if (id == 0) {
                    json = cliVentas.RegistrarSolicitudCotizacion(new Gson().toJson(getEntidad()));
                } else {
                    getEntidad().setIdSolicitudCotizacion(id);
                    json = cliVentas.ActualizarSolicitudCotizacion(new Gson().toJson(getEntidad()));
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

    private void AsignarControlesItem() {
        txtNombreItem.setText(this.item.getNombre());
        chkMedidaAbierta.setSelected(this.item.isMedidaAbierta());
        txtAltoMedidaAbierta.setEnabled(this.item.isMedidaAbierta());
        txtLargoMedidaAbierta.setEnabled(this.item.isMedidaAbierta());
        cboUnidadMedidaAbierta.setEnabled(this.item.isMedidaAbierta());
        txtAltoMedidaAbierta.setText(String.valueOf(this.item.getAltoMedidaAbierta()));
        txtLargoMedidaAbierta.setText(String.valueOf(this.item.getLargoMedidaAbierta()));
        cboUnidadMedidaAbierta.setSelectedItem(this.item.getUnidadMedidaAbierta());
        chkMedidaCerrada.setSelected(this.item.isMedidaCerrada());
        txtAltoMedidaCerrada.setEnabled(this.item.isMedidaCerrada());
        txtLargoMedidaCerrada.setEnabled(this.item.isMedidaCerrada());
        txtAltoMedidaCerrada.setText(String.valueOf(this.item.getAltoMedidaCerrada()));
        txtLargoMedidaCerrada.setText(String.valueOf(this.item.getLargoMedidaCerrada()));
        chkTiraRetira.setSelected(this.item.isTiraRetira());
        txtTiraColor.setEnabled(this.item.isTiraRetira());
        txtRetiraColor.setEnabled(this.item.isTiraRetira());
        txtTiraColor.setText(String.valueOf(this.item.getTiraColor()));
        txtRetiraColor.setText(String.valueOf(this.item.getRetiraColor()));
        chkTipoUnidad.setSelected(this.item.isTipoUnidad());
        cboTipoUnidad.setEnabled(this.item.isTipoUnidad());
        cboTipoUnidad.setSelectedItem(this.item.getNombreTipoUnidad());
        chkGrafico.setSelected(this.item.isGrafico());
        chkFondo.setSelected(this.item.isFondo());
        txtFondo.setEnabled(this.item.isFondo());
        txtFondo.setText(String.valueOf(this.item.getdFondo()));
        chkServicioImpresion.setSelected(this.item.isServicioImpresion());
        schServicioImpresion.setEnabled(this.item.isServicioImpresion());
        schMaquina.setEnabled(this.item.isServicioImpresion());
        schServicioImpresion.asingValues(this.item.getIdServicioImpresion(), this.item.getNombreServicioImpresion());
        schMaquina.asingValues(this.item.getIdMaquina(), this.item.getDescripcionMaquina());
        chkMaterial.setSelected(this.item.isMaterial());
        schMaterial.setEnabled(this.item.isMaterial());
        schMaterial.asingValues(this.item.getIdMaterial(), this.item.getNombreMaterial());
        txtAcabados.setText(this.item.getAcabados());
    }

    private void AsignarValoresItem() {
        this.item.setNombre(txtNombreItem.getText());
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
        this.item.setFondo(chkFondo.isSelected());
        this.item.setdFondo(Double.parseDouble(txtFondo.getText()));
        this.item.setGrafico(chkGrafico.isSelected());
        this.item.setServicioImpresion(chkServicioImpresion.isSelected());
        this.item.setIdServicioImpresion(schServicioImpresion.getId());
        this.item.setNombreServicioImpresion(schServicioImpresion.getText());
        this.item.setIdMaquina(schMaquina.getId());
        this.item.setDescripcionMaquina(schMaquina.getText());
        this.item.setMaterial(chkMaterial.isSelected());
        this.item.setIdMaterial(schMaterial.getId());
        this.item.setNombreMaterial(schMaterial.getText());
        this.item.setAcabados(txtAcabados.getText());
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
        schMaquina = new com.sge.base.controles.JSearch();
        chkServicioImpresion = new javax.swing.JCheckBox();
        schMaterial = new com.sge.base.controles.JSearch();
        chkMaterial = new javax.swing.JCheckBox();
        chkTipoUnidad = new javax.swing.JCheckBox();
        cboTipoUnidad = new javax.swing.JComboBox();
        chkMedidaAbierta = new javax.swing.JCheckBox();
        chkMedidaCerrada = new javax.swing.JCheckBox();
        chkTiraRetira = new javax.swing.JCheckBox();
        chkFondo = new javax.swing.JCheckBox();
        chkGrafico = new javax.swing.JCheckBox();
        cboUnidadMedidaAbierta = new javax.swing.JComboBox();
        btnGuardarItem = new javax.swing.JButton();
        lblNombreItem = new javax.swing.JLabel();
        txtNombreItem = new javax.swing.JTextField();
        lblAltoMedidaAbierta = new javax.swing.JLabel();
        lblLargoMedidaAbierta = new javax.swing.JLabel();
        txtAltoMedidaAbierta = new javax.swing.JTextField();
        txtLargoMedidaAbierta = new javax.swing.JTextField();
        lblAltoMedidaCerrada = new javax.swing.JLabel();
        txtAltoMedidaCerrada = new javax.swing.JTextField();
        lblLargoMedidaCerrada = new javax.swing.JLabel();
        txtLargoMedidaCerrada = new javax.swing.JTextField();
        lblTiraColor = new javax.swing.JLabel();
        txtFondo = new javax.swing.JTextField();
        lblRetiraColor = new javax.swing.JLabel();
        txtRetiraColor = new javax.swing.JTextField();
        txtTiraColor = new javax.swing.JTextField();
        lblMaquina = new javax.swing.JLabel();
        schServicioImpresion = new com.sge.base.controles.JSearch();
        lblAcabados = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAcabados = new javax.swing.JTextArea();
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
        lblGrupo = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        lblFechaCreacion = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JFormattedTextField();
        schMoneda = new com.sge.base.controles.JSearch();
        lblMoneda = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblFormaPago = new javax.swing.JLabel();
        schFormaPago = new com.sge.base.controles.JSearch();

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
        lblTitulo.setText("SOLICITUD DE COTIZACIÓN");

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
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblLineaProduccion.setText("L. PRODUCCIÓN");

        lblDescripcion.setText("DESCRIPCION");

        cboLineaProduccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LINEA 1", "LINEA 2", "LINEA 3", "LINEA 4", "LINEA 5" }));

        pnlItem.setBackground(java.awt.Color.white);
        pnlItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        schMaquina.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMaquinaSearch();
            }
            @Override
            public void Clear(){
            }
        });

        chkServicioImpresion.setText("SERV. IMPRESIÓN");
        chkServicioImpresion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkServicioImpresionStateChanged(evt);
            }
        });

        schMaterial.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMaterialSearch();
            }
            @Override
            public void Clear(){
            }
        });

        chkMaterial.setText("MATERIAL");
        chkMaterial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMaterialStateChanged(evt);
            }
        });

        chkTipoUnidad.setText("TIPO UNIDAD");
        chkTipoUnidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkTipoUnidadStateChanged(evt);
            }
        });

        cboTipoUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO UNIDAD 1", "TIPO UNIDAD 2", "TIPO UNIDAD 3" }));

        chkMedidaAbierta.setText("MEDIDA ABIERTA");
        chkMedidaAbierta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMedidaAbiertaStateChanged(evt);
            }
        });

        chkMedidaCerrada.setText("MEDIDA CERRADA");
        chkMedidaCerrada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMedidaCerradaStateChanged(evt);
            }
        });

        chkTiraRetira.setText("TIRA Y RETIRA");
        chkTiraRetira.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkTiraRetiraStateChanged(evt);
            }
        });

        chkFondo.setText("FONDO");
        chkFondo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkFondoStateChanged(evt);
            }
        });

        chkGrafico.setText("GRÁFICO");

        cboUnidadMedidaAbierta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UNIDAD 1", "UNIDAD 2", "UNIDAD 3" }));

        btnGuardarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"))); // NOI18N
        btnGuardarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarItemActionPerformed(evt);
            }
        });

        lblNombreItem.setText("NOMBRE");

        lblAltoMedidaAbierta.setText("ALTO");

        lblLargoMedidaAbierta.setText("LARGO");

        txtAltoMedidaAbierta.setText("0");

        txtLargoMedidaAbierta.setText("0");

        lblAltoMedidaCerrada.setText("ALTO");

        txtAltoMedidaCerrada.setText("0");

        lblLargoMedidaCerrada.setText("LARGO");

        txtLargoMedidaCerrada.setText("0");

        lblTiraColor.setText("TIRA COLOR");

        txtFondo.setText("0");

        lblRetiraColor.setText("RETIRA COLOR");

        txtRetiraColor.setText("0");

        txtTiraColor.setText("0");

        lblMaquina.setText("MAQUINA");

        schServicioImpresion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schServicioImpresionSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblAcabados.setText("ACABADOS");

        txtAcabados.setColumns(20);
        txtAcabados.setRows(5);
        jScrollPane3.setViewportView(txtAcabados);

        javax.swing.GroupLayout pnlItemLayout = new javax.swing.GroupLayout(pnlItem);
        pnlItem.setLayout(pnlItemLayout);
        pnlItemLayout.setHorizontalGroup(
            pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkMedidaAbierta)
                    .addComponent(chkMedidaCerrada)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(lblAltoMedidaCerrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAltoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLargoMedidaCerrada)
                        .addGap(7, 7, 7)
                        .addComponent(txtLargoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(lblNombreItem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreItem))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(lblAltoMedidaAbierta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAltoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLargoMedidaAbierta)
                        .addGap(7, 7, 7)
                        .addComponent(txtLargoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboUnidadMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(chkServicioImpresion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(schServicioImpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(chkTiraRetira)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkTipoUnidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(lblTiraColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)
                                .addComponent(txtTiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(chkFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(lblRetiraColor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRetiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkMaterial)
                            .addComponent(lblMaquina))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(schMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(lblAcabados)
                        .addGap(0, 114, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardarItem)))
                .addContainerGap())
        );
        pnlItemLayout.setVerticalGroup(
            pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreItem)
                            .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAcabados))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(chkMedidaAbierta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboUnidadMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAltoMedidaAbierta)
                                    .addComponent(txtAltoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLargoMedidaAbierta)
                                    .addComponent(txtLargoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chkMedidaCerrada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblAltoMedidaCerrada)
                                    .addComponent(txtAltoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLargoMedidaCerrada)
                                    .addComponent(txtLargoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkTiraRetira)
                                    .addComponent(chkTipoUnidad)
                                    .addComponent(cboTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTiraColor)
                                    .addComponent(lblRetiraColor)
                                    .addComponent(txtRetiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkFondo)
                                    .addComponent(txtFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkGrafico))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(schServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(schMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(schMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnlItems.addTab("ITEM", pnlItem);

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

        lblGrupo.setText("GRUPO");

        lblFechaCreacion.setText("FECHA CREACION");

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

        lblFormaPago.setText("FORMA PAGO");

        schFormaPago.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schFormaPagoSearch();
            }
            @Override
            public void Clear(){
            }
        });

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblMoneda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGrupo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblFechaCreacion)
                                .addGap(29, 29, 29)))
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaCreacion)
                            .addComponent(txtGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFormaPago)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(schFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(cboLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcion)))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(schVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(btnNuevoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tpnlItems)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGap(1, 1, 1)))
                .addGap(23, 23, 23))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(schCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(schNumeracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGrupo)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(schVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaCreacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(schMoneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLineaProduccion)
                        .addComponent(lblCantidad)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(schFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpnlItems, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminarItem)
                        .addComponent(btnNuevoItem))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar)
                        .addComponent(btnAceptar)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void schClienteSearch() {
        VerModal(new lisCliente(1), sele_clie);
    }

    private void schVendedorSearch() {
        VerModal(new lisEmpleado(1), sele_vend);
    }

    private void schNumeracionSearch() {
        String filtro = "WHERE Numeracion.idEntidad = 3";
        VerModal(new lisNumeracion(1, filtro), sele_nume);
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

    public void Aceptar() {
        if (super.isFromJson()) {
            AsignarValores();
            setJson(new Gson().toJson(super.getEntidad()));
            Cerrar();
        } else {
            new swGuardarSolicitud().execute();
        }
    }

    public void Cancelar() {
        Cerrar();
    }
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkServicioImpresionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkServicioImpresionStateChanged
        // TODO add your handling code here:
        schServicioImpresion.setEnabled(chkServicioImpresion.isSelected());
        schMaquina.setEnabled(chkServicioImpresion.isSelected());
    }//GEN-LAST:event_chkServicioImpresionStateChanged

    private void chkMaterialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMaterialStateChanged
        // TODO add your handling code here:
        schMaterial.setEnabled(chkMaterial.isSelected());
    }//GEN-LAST:event_chkMaterialStateChanged

    private void chkTipoUnidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkTipoUnidadStateChanged
        // TODO add your handling code here:
        cboTipoUnidad.setEnabled(chkTipoUnidad.isSelected());
    }//GEN-LAST:event_chkTipoUnidadStateChanged

    private void chkMedidaAbiertaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMedidaAbiertaStateChanged
        // TODO add your handling code here:
        txtAltoMedidaAbierta.setEnabled(chkMedidaAbierta.isSelected());
        txtLargoMedidaAbierta.setEnabled(chkMedidaAbierta.isSelected());
        cboUnidadMedidaAbierta.setEnabled(chkMedidaAbierta.isSelected());
    }//GEN-LAST:event_chkMedidaAbiertaStateChanged

    private void btnGuardarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarItemActionPerformed
        // TODO add your handling code here:
        AsignarValoresItem();
        lisItems.updateUI();
    }//GEN-LAST:event_btnGuardarItemActionPerformed

    private void btnNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemActionPerformed
        // TODO add your handling code here:
        ItemSolicitudCotizacion item = new ItemSolicitudCotizacion();
        item.setNombre("ITEM " + (getEntidad().getItems().size() + 1));
        getEntidad().getItems().add(item);
        AgregarElemento(lisItems, item);
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        ItemSolicitudCotizacion itemPlantilla = (ItemSolicitudCotizacion) lisItems.getSelectedValue();
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
            this.item = (ItemSolicitudCotizacion) lisItems.getSelectedValue();
            AsignarControlesItem();
            AsignarTitulo(tpnlItems, 0, this.item.getNombre());
            MostrarControl(tpnlItems);
        } else {
            OcultarControl(tpnlItems);
        }
    }//GEN-LAST:event_lisItemsValueChanged

    private void chkMedidaCerradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMedidaCerradaStateChanged
        // TODO add your handling code here:
        txtAltoMedidaCerrada.setEnabled(chkMedidaCerrada.isSelected());
        txtLargoMedidaCerrada.setEnabled(chkMedidaCerrada.isSelected());
    }//GEN-LAST:event_chkMedidaCerradaStateChanged

    private void chkTiraRetiraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkTiraRetiraStateChanged
        // TODO add your handling code here:
        txtTiraColor.setEnabled(chkTiraRetira.isSelected());
        txtRetiraColor.setEnabled(chkTiraRetira.isSelected());
    }//GEN-LAST:event_chkTiraRetiraStateChanged

    private void chkFondoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkFondoStateChanged
        // TODO add your handling code here:
        txtFondo.setEnabled(chkFondo.isSelected());
    }//GEN-LAST:event_chkFondoStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnGuardarItem;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JComboBox cboLineaProduccion;
    private javax.swing.JComboBox cboTipoUnidad;
    private javax.swing.JComboBox cboUnidadMedidaAbierta;
    private javax.swing.JCheckBox chkFondo;
    private javax.swing.JCheckBox chkGrafico;
    private javax.swing.JCheckBox chkMaterial;
    private javax.swing.JCheckBox chkMedidaAbierta;
    private javax.swing.JCheckBox chkMedidaCerrada;
    private javax.swing.JCheckBox chkServicioImpresion;
    private javax.swing.JCheckBox chkTipoUnidad;
    private javax.swing.JCheckBox chkTiraRetira;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAcabados;
    private javax.swing.JLabel lblAltoMedidaAbierta;
    private javax.swing.JLabel lblAltoMedidaCerrada;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblFormaPago;
    private javax.swing.JLabel lblGrupo;
    private javax.swing.JLabel lblLargoMedidaAbierta;
    private javax.swing.JLabel lblLargoMedidaCerrada;
    private javax.swing.JLabel lblLineaProduccion;
    private javax.swing.JLabel lblMaquina;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNombreItem;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblRetiraColor;
    private javax.swing.JLabel lblTiraColor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JList lisItems;
    private javax.swing.JPanel pnlItem;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schCliente;
    private com.sge.base.controles.JSearch schFormaPago;
    private com.sge.base.controles.JSearch schMaquina;
    private com.sge.base.controles.JSearch schMaterial;
    private com.sge.base.controles.JSearch schMoneda;
    private com.sge.base.controles.JSearch schNumeracion;
    private com.sge.base.controles.JSearch schServicioImpresion;
    private com.sge.base.controles.JSearch schVendedor;
    private javax.swing.JTabbedPane tpnlItems;
    private javax.swing.JTextArea txtAcabados;
    private javax.swing.JTextField txtAltoMedidaAbierta;
    private javax.swing.JTextField txtAltoMedidaCerrada;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JTextField txtFondo;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtLargoMedidaAbierta;
    private javax.swing.JTextField txtLargoMedidaCerrada;
    private javax.swing.JTextField txtNombreItem;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRetiraColor;
    private javax.swing.JTextField txtTiraColor;
    // End of variables declaration//GEN-END:variables
}
