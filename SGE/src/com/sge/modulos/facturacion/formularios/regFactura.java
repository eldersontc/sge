package com.sge.modulos.facturacion.formularios;

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
import com.sge.modulos.administracion.formularios.lisNumeracion;
import com.sge.modulos.facturacion.clases.Factura;
import com.sge.modulos.facturacion.clases.ItemFactura;
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
public class regFactura extends frameBasex<Factura> {

    /**
     * Creates new form regFactura
     */
    public regFactura(int id) {
        initComponents();
        Init(id);
    }

    public regFactura(ValorDefinido valorDefinido) {
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
            lblTitulo.setText("VER " + lblTitulo.getText());
            OcultarControles();
            new swObtenerFactura().execute();
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
        schMoneda.asingValues(getEntidad().getIdMoneda(), getEntidad().getSimboloMoneda());
        txtSubTotal.setValue(getEntidad().getSubTotal());
        txtImpuesto.setValue(getEntidad().getMontoImpuesto());
        lblPorcentajeImpuesto.setText(String.format("(%s%s)", getEntidad().getPorcentajeImpuesto(), "%"));
        txtTotal.setValue(getEntidad().getTotal());
        for (ItemFactura item : getEntidad().getItems()) {
            AgregarFila(tbItems,
                    new Object[]{
                        item.getIdItemFactura(),
                        item.getIdOrdenTrabajo(),
                        item.getNumeroOrdenTrabajo(),
                        item.getDescripcionOrdenTrabajo(),
                        item.getCantidad(),
                        item.getPrecio(),
                        item.getTotal()
                    });
        }
    }

    @Override
    public void OcultarControles() {
        OcultarControl(btnNuevoItem);
        OcultarControl(btnEliminarItem);
    }

    public void CalcularTotales() {
        double subTotal = 0.0;
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            double totalItem = ObtenerValorCelda(tbItems, i, 6);
            subTotal += totalItem;
        }

        getEntidad().setSubTotal(subTotal);
        double montoImpuesto = subTotal * (getEntidad().getPorcentajeImpuesto() / 100);
        getEntidad().setMontoImpuesto(montoImpuesto);
        double total = subTotal + montoImpuesto;
        getEntidad().setTotal(total);

        txtSubTotal.setValue(subTotal);
        txtImpuesto.setValue(montoImpuesto);
        txtTotal.setValue(total);
    }

    public List<ItemFactura> getItems() {
        List<ItemFactura> items = new ArrayList<>();
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            ItemFactura item = new ItemFactura();
            item.setIdOrdenTrabajo(ObtenerValorCelda(tbItems, i, 1));
            item.setNumeroOrdenTrabajo(ObtenerValorCelda(tbItems, i, 2));
            item.setDescripcionOrdenTrabajo(ObtenerValorCelda(tbItems, i, 3));
            item.setCantidad(ObtenerValorCelda(tbItems, i, 4));
            item.setPrecio(ObtenerValorCelda(tbItems, i, 5));
            item.setTotal(ObtenerValorCelda(tbItems, i, 6));
            items.add(item);
        }
        return items;
    }

    Action select_nume = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Numeracion seleccionado = ((lisNumeracion) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                cliAdministracion cliente = new cliAdministracion();
                String json = cliente.ObtenerNumeracion(new Gson().toJson(seleccionado.getIdNumeracion()));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    Numeracion numeracion = new Gson().fromJson(resultado[1], Numeracion.class);
                    schNumeracion.asingValues(numeracion.getIdNumeracion(), numeracion.getDescripcion());
                    getEntidad().setNumeracionManual(numeracion.isManual());
                    txtNumero.setEnabled(numeracion.isManual());
                    if (numeracion.isTieneImpuesto()) {
                        getEntidad().setPorcentajeImpuesto(numeracion.getPorcentajeImpuesto());
                        lblPorcentajeImpuesto.setText(String.format("(%s%s)", numeracion.getPorcentajeImpuesto(), "%"));
                    }
                }
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

    Action select_mone = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Moneda seleccionado = ((lisMoneda) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMoneda.asingValues(seleccionado.getIdMoneda(), seleccionado.getSimbolo());
            }
        }
    };

    Action select_ortr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<OrdenTrabajo> seleccionados = ((lisOrdenTrabajo) evt.getSource()).getSeleccionados();
            if(!seleccionados.isEmpty()){
                for (OrdenTrabajo seleccionado : seleccionados) {
                    AgregarFila(tbItems, new Object[]{0, seleccionado.getIdOrdenTrabajo(), seleccionado.getNumero(), seleccionado.getDescripcion(), seleccionado.getCantidad(), seleccionado.getTotal() / seleccionado.getCantidad(), seleccionado.getTotal()});
                }
                CalcularTotales();
            }
        }
    };
    
    public class swObtenerValoresDefinidos extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {
                String json = cliente.ObtenerValorDefinidoPorUsuarioYEntidad(new Gson().toJson(new int[]{getUsuario().getIdUsuario(), 10}));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    if (resultado[2].isEmpty()) {
                        setEntidad(new Factura());
                    } else {
                        setEntidad(new Gson().fromJson(resultado[2], Factura.class));
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

    public class swObtenerFactura extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliFacturacion cliente = new cliFacturacion();
            String json = "";
            try {
                json = cliente.ObtenerFactura(new Gson().toJson(id));
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
                    setEntidad(new Gson().fromJson(resultado[1], Factura.class));
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
        getEntidad().setIdMoneda(schMoneda.getId());
        getEntidad().setSimboloMoneda(schMoneda.getText());
        getEntidad().setItems(getItems());
    }

    public class swGuardarFactura extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliFacturacion cliente = new cliFacturacion();
            String json = "";
            try {
                AsignarValores();
                json = cliente.RegistrarFactura(new Gson().toJson(getEntidad()));
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
            if (this.id == 0) {
                new swGuardarFactura().execute();
            } else {
                Cerrar();
            }
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
        lblNombre = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlUnidades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbItems = new javax.swing.JTable();
        btnNuevoItem = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblMoneda = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JFormattedTextField();
        lblFechaCreacion = new javax.swing.JLabel();
        lblSubTotal = new javax.swing.JLabel();
        lblImpuesto = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JFormattedTextField();
        txtTotal = new javax.swing.JFormattedTextField();
        txtImpuesto = new javax.swing.JFormattedTextField();
        schCliente = new com.sge.base.controles.JSearch();
        schResponsable = new com.sge.base.controles.JSearch();
        schMoneda = new com.sge.base.controles.JSearch();
        schNumeracion = new com.sge.base.controles.JSearch();
        lblPorcentajeImpuesto = new javax.swing.JLabel();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblNombre.setText("RESPONSABLE");

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

        pnlUnidades.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDITEM", "IDOT", "N°", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, false, false
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
            tbItems.getColumnModel().getColumn(3).setPreferredWidth(250);
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
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
                .addContainerGap(148, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ORDENES DE TRABAJO", pnlUnidades);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("FACTURA");

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

        lblMoneda.setText("MONEDA");

        lblCliente.setText("CLIENTE");

        txtFechaCreacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        lblFechaCreacion.setText("F. CREACION");

        lblSubTotal.setText("SUBTOTAL");

        lblImpuesto.setText("IMPUESTO");

        lblTotal.setText("TOTAL");

        txtSubTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtImpuesto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtImpuesto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

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

        schMoneda.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMonedaSearch();
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

        lblPorcentajeImpuesto.setText("(%0)");

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
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechaCreacion, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                    .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(frameLayout.createSequentialGroup()
                                            .addGap(411, 411, 411)
                                            .addComponent(lblImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblSubTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPorcentajeImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
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
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubTotal)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImpuesto)
                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPorcentajeImpuesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        String filtro = "WHERE Numeracion.idEntidad = 10";
        VerModal(new lisNumeracion(1, filtro), select_nume);
    }

    private void schResponsableSearch() {
        VerModal(new lisEmpleado(1), select_resp);
    }

    private void schClienteSearch() {
        VerModal(new lisCliente(1), select_clie);
    }

    private void schMonedaSearch() {
        VerModal(new lisMoneda(1), select_mone);
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
            EliminarFila(tbItems);
            CalcularTotales();
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblImpuesto;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPorcentajeImpuesto;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel pnlUnidades;
    private com.sge.base.controles.JSearch schCliente;
    private com.sge.base.controles.JSearch schMoneda;
    private com.sge.base.controles.JSearch schNumeracion;
    private com.sge.base.controles.JSearch schResponsable;
    private javax.swing.JTable tbItems;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JFormattedTextField txtImpuesto;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JFormattedTextField txtSubTotal;
    private javax.swing.JFormattedTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
