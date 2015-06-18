package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Moneda;
import com.sge.modulos.administracion.clases.Numeracion;
import com.sge.modulos.administracion.clases.ValorDefinido;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import com.sge.modulos.administracion.formularios.lisMoneda;
import com.sge.modulos.administracion.formularios.lisNumeracion;
import com.sge.modulos.ventas.clases.Cliente;
import com.sge.modulos.ventas.clases.Cotizacion;
import com.sge.modulos.ventas.clases.ItemPresupuesto;
import com.sge.modulos.ventas.clases.Presupuesto;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regPresupuesto extends frameBase<Presupuesto> {

    /**
     * Creates new form regPresupuestox
     */
    public regPresupuesto(int id) {
        initComponents();
        Init(id);
    }

    public regPresupuesto(ValorDefinido valorDefinido) {
        initComponents();
        super.Init(valorDefinido);
    }

    int id = 0;

    Action change_item = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] celda = (int[]) e.getSource();
            switch (celda[1]) {
                case 5:
                    double subTotal5 = ObtenerValorCelda(tbItems, celda[0], 4);
                    double recargo5 = ObtenerValorCelda(tbItems, celda[0], celda[1]);
                    AsignarValorCelda(tbItems, subTotal5 + recargo5, celda[0], 6);
                    CalcularTotal();
                    break;
            }
        }
    };

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

    Action sele_mone = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Moneda seleccionado = ((lisMoneda) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMoneda.asingValues(seleccionado.getIdMoneda(), seleccionado.getSimbolo());
                getEntidad().setSimboloMoneda(seleccionado.getSimbolo());
            }
        }
    };

    Action sele_coti = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<Cotizacion> seleccionados = ((lisCotizacion) evt.getSource()).getSeleccionados();
            for (Cotizacion seleccionado : seleccionados) {
                AgregarFila(tbItems, new Object[]{0, seleccionado.getIdCotizacion(), seleccionado.getNumero(), seleccionado.getDescripcion(), seleccionado.getTotal(), 0.0, seleccionado.getTotal()});
            }
            AgregarEventoChange(tbItems, change_item);
            CalcularTotal();
        }
    };

    private void CalcularTotal() {
        double total = 0;
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            double totalItem = ObtenerValorCelda(tbItems, i, 6);
            total += totalItem;
        }
        getEntidad().setTotal(total);
        NumberFormat formato = new DecimalFormat("#0.00");
        txtTotal.setText(getEntidad().getSimboloMoneda() + formato.format(getEntidad().getTotal()));
    }

    public void Init(int id) {
        this.id = id;
        if (this.id == 0) {
            lblTitulo.setText("NUEVO " + lblTitulo.getText());
            new swObtenerValoresDefinidos().execute();
        } else {
            lblTitulo.setText("MODIFICAR " + lblTitulo.getText());
            new swObtenerPresupuesto().execute();
        }
    }

    private void AsignarValores() {
        getEntidad().setIdCliente(schCliente.getId());
        getEntidad().setRazonSocialCliente(schCliente.getText());
        getEntidad().setIdNumeracion(schNumeracion.getId());
        getEntidad().setDescripcionNumeracion(schNumeracion.getText());
        getEntidad().setNumero(txtNumero.getText());
        getEntidad().setIdMoneda(schMoneda.getId());
        getEntidad().setSimboloMoneda(schMoneda.getText());
        getEntidad().setItems(getItems());
    }

    @Override
    public void AsignarControles() {
        schCliente.asingValues(getEntidad().getIdCliente(), getEntidad().getRazonSocialCliente());
        schNumeracion.asingValues(getEntidad().getIdNumeracion(), getEntidad().getDescripcionNumeracion());
        txtNumero.setText(getEntidad().getNumero());
        schMoneda.asingValues(getEntidad().getIdMoneda(), getEntidad().getSimboloMoneda());
        txtFechaCreacion.setValue(getEntidad().getFechaCreacion());
        for (ItemPresupuesto item : getEntidad().getItems()) {
            AgregarFila(tbItems, new Object[]{item.getIdItemPresupuesto(), item.getIdCotizacion(), item.getNumeroCotizacion(), item.getDescripcionCotizacion(), item.getTotalCotizacion(), item.getRecargo(), item.getTotal()});
        }
        AgregarEventoChange(tbItems, change_item);
        getEntidad().getItems().clear();
        NumberFormat formato = new DecimalFormat("#0.00");
        txtTotal.setText(getEntidad().getSimboloMoneda() + formato.format(getEntidad().getTotal()));
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
                String json = cliente.ObtenerValorDefinidoPorUsuarioYEntidad(new Gson().toJson(new int[]{getUsuario().getIdUsuario(), 5}));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    if (resultado[2].isEmpty()) {
                        setEntidad(new Presupuesto());
                    } else {
                        setEntidad(new Gson().fromJson(resultado[2], Presupuesto.class));
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

    public class swObtenerPresupuesto extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                String json = cliVentas.ObtenerPresupuesto(new Gson().toJson(id));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    setEntidad(new Gson().fromJson(resultado[1], Presupuesto.class));
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

    public List<ItemPresupuesto> getItems() {
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            int idItemPresupuesto = ObtenerValorCelda(tbItems, i, 0);
            ItemPresupuesto itemPresupuesto = new ItemPresupuesto();
            itemPresupuesto.setIdCotizacion(ObtenerValorCelda(tbItems, i, 1));
            itemPresupuesto.setNumeroCotizacion(ObtenerValorCelda(tbItems, i, 2));
            itemPresupuesto.setDescripcionCotizacion(ObtenerValorCelda(tbItems, i, 3));
            itemPresupuesto.setTotalCotizacion(ObtenerValorCelda(tbItems, i, 4));
            itemPresupuesto.setRecargo(ObtenerValorCelda(tbItems, i, 5));
            itemPresupuesto.setTotal(ObtenerValorCelda(tbItems, i, 6));
            if (idItemPresupuesto == 0) {
                itemPresupuesto.setAgregar(true);
            } else {
                itemPresupuesto.setIdItemPresupuesto(idItemPresupuesto);
                itemPresupuesto.setActualizar(true);
            }
            getEntidad().getItems().add(itemPresupuesto);
        }
        return getEntidad().getItems();
    }

    public class swGuardarPresupuesto extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliVentas = new cliVentas();
            String json = "";
            try {
                AsignarValores();
                if (id == 0) {
                    json = cliVentas.RegistrarPresupuesto(new Gson().toJson(getEntidad()));
                } else {
                    getEntidad().setIdPresupuesto(id);
                    json = cliVentas.ActualizarPresupuesto(new Gson().toJson(getEntidad()));
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
                    Cerrar();
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
            if (getEntidad().getIdPresupuesto() == 0 || "PENDIENTE DE APROBACIÓN".equals(getEntidad().getEstado()) || "RECHAZADO".equals(getEntidad().getEstado())) {
                new swGuardarPresupuesto().execute();
            } else {
                VerAdvertencia("SÓLO SE PUEDE MODIFICAR CUANDO EL ESTADO ES : PENDIENTE DE APROBACIÓN Ó RECHAZADO.", frame);
            }
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
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        schCliente = new com.sge.base.controles.JSearch();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        schNumeracion = new com.sge.base.controles.JSearch();
        lblFecha = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JFormattedTextField();
        lblMoneda = new javax.swing.JLabel();
        schMoneda = new com.sge.base.controles.JSearch();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabItems = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItems = new javax.swing.JTable();
        btnNuevoItem = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        lblTitulo.setText("PRESUPUESTO");

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

        lblNumero.setText("N°");

        schNumeracion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schNumeracionSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblFecha.setText("FECHA CREACIÓN");

        lblMoneda.setText("MONEDA");

        schMoneda.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMonedaSearch();
            }
            @Override
            public void Clear(){
            }
        });

        tabItems.setBackground(java.awt.Color.white);

        tbItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDCOTIZACION", "N°", "DESCRIPCION", "SUBTOTAL", "RECARGO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbItems.setRowHeight(25);
        tbItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbItems);
        if (tbItems.getColumnModel().getColumnCount() > 0) {
            tbItems.getColumnModel().getColumn(0).setMinWidth(0);
            tbItems.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(0).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(1).setMinWidth(0);
            tbItems.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(1).setMaxWidth(0);
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

        javax.swing.GroupLayout tabItemsLayout = new javax.swing.GroupLayout(tabItems);
        tabItems.setLayout(tabItemsLayout);
        tabItemsLayout.setHorizontalGroup(
            tabItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabItemsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarItem))
                .addContainerGap())
        );
        tabItemsLayout.setVerticalGroup(
            tabItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
            .addGroup(tabItemsLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnNuevoItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarItem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("COTIZACIONES", tabItems);

        lblTotal.setText("TOTAL");

        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNumero)
                                        .addComponent(txtFechaCreacion, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void schClienteSearch() {
        VerModal(new lisCliente(1), sele_clie);
    }

    private void schClienteClear() {
    }

    private void schNumeracionSearch() {
        String filtro = "WHERE Numeracion.idEntidad = 5";
        VerModal(new lisNumeracion(1, filtro), sele_nume);
    }

    private void schMonedaSearch() {
        VerModal(new lisMoneda(1), sele_mone);
    }

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemActionPerformed
        // TODO add your handling code here:
        String filtro = String.format("WHERE idCliente = %d AND idMoneda = %d AND estado = 'APROBADO'", schCliente.getId(), schMoneda.getId());
        VerModal(new lisCotizacion(2, filtro), sele_coti);
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbItems)) {
            int idItemPresupuesto = ObtenerValorCelda(tbItems, 0);
            if (idItemPresupuesto > 0) {
                ItemPresupuesto itemPresupuesto = new ItemPresupuesto();
                itemPresupuesto.setIdItemPresupuesto(idItemPresupuesto);
                itemPresupuesto.setIdCotizacion(ObtenerValorCelda(tbItems, 1));
                itemPresupuesto.setEliminar(true);
                getEntidad().getItems().add(itemPresupuesto);
            }
            EliminarFila(tbItems);
            CalcularTotal();
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schCliente;
    private com.sge.base.controles.JSearch schMoneda;
    private com.sge.base.controles.JSearch schNumeracion;
    private javax.swing.JPanel tabItems;
    private javax.swing.JTable tbItems;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
