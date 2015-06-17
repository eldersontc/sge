package com.sge.modulos.facturacion.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBasex;
import com.sge.modulos.administracion.clases.Departamento;
import com.sge.modulos.administracion.clases.Distrito;
import com.sge.modulos.administracion.clases.Empleado;
import com.sge.modulos.administracion.clases.Numeracion;
import com.sge.modulos.administracion.clases.Provincia;
import com.sge.modulos.administracion.clases.ValorDefinido;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import com.sge.modulos.administracion.formularios.lisDepartamento;
import com.sge.modulos.administracion.formularios.lisDistrito;
import com.sge.modulos.administracion.formularios.lisEmpleado;
import com.sge.modulos.administracion.formularios.lisNumeracionx;
import com.sge.modulos.administracion.formularios.lisProvincia;
import com.sge.modulos.facturacion.clases.GuiaRemision;
import com.sge.modulos.facturacion.clases.ItemGuiaRemision;
import com.sge.modulos.facturacion.cliente.cliFacturacion;
import com.sge.modulos.produccion.clases.OrdenTrabajo;
import com.sge.modulos.produccion.formularios.lisOrdenTrabajo;
import com.sge.modulos.ventas.clases.Cliente;
import com.sge.modulos.ventas.formularios.lisCliente;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regGuiaRemision extends frameBasex<GuiaRemision> {

    /**
     * Creates new form regGuiaRemision
     */
    public regGuiaRemision(int id) {
        initComponents();
        Init(id);
    }

    public regGuiaRemision(ValorDefinido valorDefinido) {
        initComponents();
        super.Init(valorDefinido);
    }

    public int id = 0;

    public void Init(int id) {
        this.id = id;
        if (this.id == 0) {
            lblTitulo.setText("NUEVA " + lblTitulo.getText());
            new swObtenerValoresDefinidos().execute();
        } else {
            lblTitulo.setText("EDITAR " + lblTitulo.getText());
            new swObtenerGuiaRemision().execute();
        }
    }

    @Override
    public void AsignarControles() {
        schNumeracion.asingValues(getEntidad().getIdNumeracion(), getEntidad().getDescripcionNumeracion());
        txtNumero.setEnabled(getEntidad().isNumeracionManual());
        txtNumero.setText(getEntidad().getNumero());
        schResponsable.asingValues(getEntidad().getIdResponsable(), getEntidad().getNombreResponsable());
        txtFechaCreacion.setValue(getEntidad().getFechaCreacion());
        schCliente.asingValues(getEntidad().getIdCliente(), getEntidad().getRazonSocialCliente());
        schChofer.asingValues(getEntidad().getIdChofer(), getEntidad().getNombreChofer());
        txtLicenciaConducir.setText(getEntidad().getLicenciaConducir());
        txtNumeroPlaca.setText(getEntidad().getNumeroPlaca());
        schContacto.asingValues(getEntidad().getIdContactoCliente(), getEntidad().getNombreContactoCliente());
        schDepartamento.asingValues(getEntidad().getIdDepartamento(), getEntidad().getNombreDepartamento());
        schProvincia.asingValues(getEntidad().getIdProvincia(), getEntidad().getNombreProvincia());
        schDistrito.asingValues(getEntidad().getIdDistrito(), getEntidad().getNombreDistrito());
        txtDireccion.setText(getEntidad().getDireccion());
        txtObservacion.setText(getEntidad().getObservacion());
        for (ItemGuiaRemision item : getEntidad().getItems()) {
            AgregarFila(tbItems,
                    new Object[]{
                        item.getIdItemGuiaRemision(),
                        item.getIdOrdenTrabajo(),
                        item.getNumeroOrdenTrabajo(),
                        item.getDescripcionOrdenTrabajo(),
                        item.getCantidad()
                    });
        }
        getEntidad().getItems().clear();
    }

    @Override
    public void OcultarControles() {
        OcultarControl(btnNuevoItem);
        OcultarControl(btnEliminarItem);
    }

    public List<ItemGuiaRemision> getItems() {
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            int idItemGuiaRemision = ObtenerValorCelda(tbItems, i, 0);
            if (idItemGuiaRemision == 0) {
                ItemGuiaRemision item = new ItemGuiaRemision();
                item.setIdOrdenTrabajo(ObtenerValorCelda(tbItems, i, 1));
                item.setNumeroOrdenTrabajo(ObtenerValorCelda(tbItems, i, 2));
                item.setDescripcionOrdenTrabajo(ObtenerValorCelda(tbItems, i, 3));
                item.setCantidad(ObtenerValorCelda(tbItems, i, 4));
                item.setAgregar(true);
                getEntidad().getItems().add(item);
            }
        }
        return getEntidad().getItems();
    }

    Action select_nume = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Numeracion seleccionado = ((lisNumeracionx) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schNumeracion.asingValues(seleccionado.getIdNumeracion(), seleccionado.getDescripcion());
                getEntidad().setNumeracionManual(seleccionado.isManual());
                txtNumero.setEnabled(seleccionado.isManual());
            }
        }
    };

    Action select_resp = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Empleado seleccionado = ((lisEmpleado) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schResponsable.asingValues(seleccionado.getIdEmpleado(), seleccionado.getNombre());
            }
        }
    };

    Action select_clie = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Cliente seleccionado = ((lisCliente) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schCliente.asingValues(seleccionado.getIdCliente(), seleccionado.getRazonSocial());
            }
        }
    };

    Action select_chof = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Empleado seleccionado = ((lisEmpleado) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schChofer.asingValues(seleccionado.getIdEmpleado(), seleccionado.getNombre());
            }
        }
    };

    Action select_ortr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<OrdenTrabajo> seleccionados = ((lisOrdenTrabajo) evt.getSource()).getSeleccionados();
            if (!seleccionados.isEmpty()) {
                for (OrdenTrabajo seleccionado : seleccionados) {
                    AgregarFila(tbItems, new Object[]{0, seleccionado.getIdOrdenTrabajo(), seleccionado.getNumero(), seleccionado.getDescripcion(), seleccionado.getCantidad()});
                }
            }
        }
    };

    Action select_depa = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Departamento seleccionado = ((lisDepartamento) e.getSource()).getSeleccionado();
            if (seleccionado != null) {
                schDepartamento.asingValues(seleccionado.getIdDepartamento(), seleccionado.getNombre());
                schProvincia.clearValues();
                schDistrito.clearValues();
            }
        }
    };

    Action select_prov = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Provincia seleccionado = ((lisProvincia) e.getSource()).getSeleccionado();
            if (seleccionado != null) {
                schProvincia.asingValues(seleccionado.getIdProvincia(), seleccionado.getNombre());
                schDistrito.clearValues();
            }
        }
    };

    Action select_dist = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Distrito seleccionado = ((lisDistrito) e.getSource()).getSeleccionado();
            if (seleccionado != null) {
                schDistrito.asingValues(seleccionado.getIdDistrito(), seleccionado.getNombre());
            }
        }
    };
    
    public class swObtenerValoresDefinidos extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {
                String json = cliente.ObtenerValorDefinidoPorUsuarioYEntidad(new Gson().toJson(new int[]{getUsuario().getIdUsuario(), 11}));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    if (resultado[2].isEmpty()) {
                        setEntidad(new GuiaRemision());
                    } else {
                        setEntidad(new Gson().fromJson(resultado[2], GuiaRemision.class));
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

    public class swObtenerGuiaRemision extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliFacturacion cliente = new cliFacturacion();
            String json = "";
            try {
                json = cliente.ObtenerGuiaRemision(new Gson().toJson(id));
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
                    setEntidad(new Gson().fromJson(resultado[1], GuiaRemision.class));
                    AsignarControles();
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    private void AsignarValores() {
        getEntidad().setNumero(txtNumero.getText());
        getEntidad().setIdNumeracion(schNumeracion.getId());
        getEntidad().setDescripcionNumeracion(schNumeracion.getText());
        getEntidad().setIdResponsable(schResponsable.getId());
        getEntidad().setNombreResponsable(schResponsable.getText());
        getEntidad().setIdCliente(schCliente.getId());
        getEntidad().setRazonSocialCliente(schCliente.getText());
        getEntidad().setIdChofer(schChofer.getId());
        getEntidad().setNombreChofer(schChofer.getText());
        getEntidad().setLicenciaConducir(txtLicenciaConducir.getText());
        getEntidad().setNumeroPlaca(txtNumeroPlaca.getText());
        getEntidad().setIdContactoCliente(schContacto.getId());
        getEntidad().setNombreContactoCliente(schContacto.getText());
        getEntidad().setIdDepartamento(schDepartamento.getId());
        getEntidad().setNombreDepartamento(schDepartamento.getText());
        getEntidad().setIdProvincia(schProvincia.getId());
        getEntidad().setNombreProvincia(schProvincia.getText());
        getEntidad().setIdDistrito(schDistrito.getId());
        getEntidad().setNombreDistrito(schDistrito.getText());
        getEntidad().setDireccion(txtDireccion.getText());
        getEntidad().setObservacion(txtObservacion.getText());
        getEntidad().setItems(getItems());
    }

    public class swGuardarGuiaRemision extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliFacturacion cliente = new cliFacturacion();
            String json = "";
            try {
                AsignarValores();
                if (id == 0) {
                    json = cliente.RegistrarGuiaRemision(new Gson().toJson(getEntidad()));
                } else {
                    getEntidad().setIdGuiaRemision(id);
                    json = cliente.ActualizarGuiaRemision(new Gson().toJson(getEntidad()));
                }
            } catch (Exception e) {
                OcultarProcesando(frame);
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

    public void Aceptar() {
        if (super.isFromJson()) {
            AsignarValores();
            setJson(new Gson().toJson(super.getEntidad()));
            Cerrar();
        } else {
            new swGuardarGuiaRemision().execute();
        }
    }

    public void Cancelar() {
        Cerrar();
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
        lblResponsable = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlUnidades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbItems = new javax.swing.JTable();
        btnNuevoItem = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        tabDistribucion = new javax.swing.JPanel();
        lblChofer = new javax.swing.JLabel();
        schChofer = new com.sge.base.controles.JSearch();
        lblLicenciaConducir = new javax.swing.JLabel();
        lblNumeroPlaca = new javax.swing.JLabel();
        lblContacto = new javax.swing.JLabel();
        schContacto = new com.sge.base.controles.JSearch();
        jSeparator1 = new javax.swing.JSeparator();
        lblDepartamento = new javax.swing.JLabel();
        schDepartamento = new com.sge.base.controles.JSearch();
        lblProvincia = new javax.swing.JLabel();
        schProvincia = new com.sge.base.controles.JSearch();
        lblDistrito = new javax.swing.JLabel();
        schDistrito = new com.sge.base.controles.JSearch();
        lblDireccion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        lblObservacion = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        txtLicenciaConducir = new javax.swing.JTextField();
        txtNumeroPlaca = new javax.swing.JTextField();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JFormattedTextField();
        lblFechaCreacion = new javax.swing.JLabel();
        schCliente = new com.sge.base.controles.JSearch();
        schResponsable = new com.sge.base.controles.JSearch();
        schNumeracion = new com.sge.base.controles.JSearch();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblResponsable.setText("RESPONSABLE");

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

        jTabbedPane1.setBorder(null);

        pnlUnidades.setBackground(java.awt.Color.white);
        pnlUnidades.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDITEM", "IDOT", "N°", "DESCRIPCION", "CANTIDAD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbItems);
        if (tbItems.getColumnModel().getColumnCount() > 0) {
            tbItems.getColumnModel().getColumn(0).setMinWidth(0);
            tbItems.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(0).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(1).setMinWidth(0);
            tbItems.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(1).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(3).setPreferredWidth(300);
        }

        btnNuevoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoItemActionPerformed(evt);
            }
        });

        btnEliminarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUnidadesLayout = new javax.swing.GroupLayout(pnlUnidades);
        pnlUnidades.setLayout(pnlUnidadesLayout);
        pnlUnidadesLayout.setHorizontalGroup(
            pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUnidadesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevoItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUnidadesLayout.setVerticalGroup(
            pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnidadesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnNuevoItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarItem)
                .addContainerGap(286, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ORDENES DE TRABAJO", pnlUnidades);

        tabDistribucion.setBackground(java.awt.Color.white);
        tabDistribucion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblChofer.setText("CHOFER");

        schChofer.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schChoferSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblLicenciaConducir.setText("L.CONDUCIR");

        lblNumeroPlaca.setText("N° PLACA");

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

        lblDepartamento.setText("DEPARTAMENTO");

        schDepartamento.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schDepartamentoSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblProvincia.setText("PROVINCIA");

        schProvincia.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schProvinciaSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblDistrito.setText("DISTRITO");

        schDistrito.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schDistritoSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblDireccion.setText("DIRECCION");

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane1.setViewportView(txtObservacion);

        lblObservacion.setText("OBSERVACION");

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane3.setViewportView(txtDireccion);

        javax.swing.GroupLayout tabDistribucionLayout = new javax.swing.GroupLayout(tabDistribucion);
        tabDistribucion.setLayout(tabDistribucionLayout);
        tabDistribucionLayout.setHorizontalGroup(
            tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDistribucionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabDistribucionLayout.createSequentialGroup()
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblChofer, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLicenciaConducir, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schChofer, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLicenciaConducir, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1)
                    .addGroup(tabDistribucionLayout.createSequentialGroup()
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDistrito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(schDistrito, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(tabDistribucionLayout.createSequentialGroup()
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblProvincia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schProvincia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tabDistribucionLayout.createSequentialGroup()
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabDistribucionLayout.createSequentialGroup()
                        .addComponent(lblObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabDistribucionLayout.setVerticalGroup(
            tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDistribucionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChofer, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schChofer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabDistribucionLayout.createSequentialGroup()
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLicenciaConducir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLicenciaConducir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumeroPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabDistribucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DISTRIBUCION", tabDistribucion);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("GUIA DE REMISION");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblNumero.setText("N°");

        lblCliente.setText("CLIENTE");

        txtFechaCreacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        lblFechaCreacion.setText("F. CREACION");

        schCliente.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schClienteSearch();
            }
            @Override
            public void Clear(){
            }
        });

        schResponsable.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schResponsableSearch();
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

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(schResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, frameLayout.createSequentialGroup()
                        .addGap(468, 468, 468)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(15, Short.MAX_VALUE))
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

    private void schNumeracionSearch() {
        String filtro = "WHERE Numeracion.idEntidad = 11";
        VerModal(new lisNumeracionx(1, filtro), select_nume);
    }

    private void schResponsableSearch() {
        VerModal(new lisEmpleado(1), select_resp);
    }

    private void schClienteSearch() {
        VerModal(new lisCliente(1), select_clie);
    }

    private void schChoferSearch() {
        VerModal(new lisEmpleado(1), select_chof);
    }
    
    private void schContactoSearch() {
        //VerModal(new lisEmpleado(1), select_chof);
    }
    
    private void schDepartamentoSearch() {
        VerModal(new lisDepartamento(1), select_depa);
    }
    
    private void schProvinciaSearch() {
        int idDepartamento = schDepartamento.getId();
        if (idDepartamento == 0) {
            VerAdvertencia("FALTA SELECCIONAR UN DEPARTAMENTO.", frame);
        } else {
            String filtro = "WHERE Provincia.idDepartamento = " + idDepartamento;
            VerModal(new lisProvincia(1, filtro), select_prov);
        }
    }
    
    private void schDistritoSearch() {
        int idProvincia = schProvincia.getId();
        if (idProvincia == 0) {
            VerAdvertencia("FALTA SELECCIONAR UNA PROVINCIA.", frame);
        } else {
            String filtro = "WHERE Distrito.idProvincia = " + idProvincia;
            VerModal(new lisDistrito(1, filtro), select_dist);
        }
    }
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemActionPerformed
        // TODO add your handling code here:
        VerModal(new lisOrdenTrabajo(2), select_ortr);
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbItems)) {
            int idItemGuiaRemision = ObtenerValorCelda(tbItems, 0);
            if (idItemGuiaRemision > 0) {
                ItemGuiaRemision itemGuiaRemision = new ItemGuiaRemision();
                itemGuiaRemision.setIdItemGuiaRemision(idItemGuiaRemision);
                itemGuiaRemision.setEliminar(true);
                getEntidad().getItems().add(itemGuiaRemision);
            }
            EliminarFila(tbItems);
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChofer;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblContacto;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDistrito;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblLicenciaConducir;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblNumeroPlaca;
    private javax.swing.JLabel lblObservacion;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblResponsable;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel pnlUnidades;
    private com.sge.base.controles.JSearch schChofer;
    private com.sge.base.controles.JSearch schCliente;
    private com.sge.base.controles.JSearch schContacto;
    private com.sge.base.controles.JSearch schDepartamento;
    private com.sge.base.controles.JSearch schDistrito;
    private com.sge.base.controles.JSearch schNumeracion;
    private com.sge.base.controles.JSearch schProvincia;
    private com.sge.base.controles.JSearch schResponsable;
    private javax.swing.JPanel tabDistribucion;
    private javax.swing.JTable tbItems;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JTextField txtLicenciaConducir;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNumeroPlaca;
    private javax.swing.JTextArea txtObservacion;
    // End of variables declaration//GEN-END:variables
}
