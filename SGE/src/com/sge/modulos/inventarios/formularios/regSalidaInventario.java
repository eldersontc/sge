package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.constantes.Constantes;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Empleado;
import com.sge.modulos.administracion.clases.Moneda;
import com.sge.modulos.administracion.formularios.lisEmpleado;
import com.sge.modulos.administracion.formularios.lisMoneda;
import com.sge.modulos.compras.clases.Proveedor;
import com.sge.modulos.compras.formularios.lisProveedor;
import com.sge.modulos.inventarios.clases.Almacen;
import com.sge.modulos.inventarios.clases.SalidaInventario;
import com.sge.modulos.inventarios.clases.ItemSalidaInventario;
import com.sge.modulos.inventarios.clases.Producto;
import com.sge.modulos.inventarios.cliente.cliInventarios;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regSalidaInventario extends frameBase<SalidaInventario> {

    /**
     * Creates new form regSalidaInventario
     */
    public regSalidaInventario(int id) {
        initComponents();
        Init(id);
    }

    public int id = 0;

    //public SalidaInventario getEntidad();
    @Override
    public void Init(int id) {
        this.id = id;
        if (this.id == 0) {
            lblTitulo.setText("NUEVA " + lblTitulo.getText());
            AgregarCombo(tbItems, 7, 2);
            ObtenerValoresDefinidos(frame, 1, Constantes.SAL_INV);
        } else {
            lblTitulo.setText("VER " + lblTitulo.getText());
            OcultarControles();
            new swObtenerSalidaInventario().execute();
        }
    }

    @Override
    public void AsignarControles() {
        txtCliente.setText(getEntidad().getRazonSocialCliente());
        txtNumero.setText(getEntidad().getNumero());
        txtResponsable.setText(getEntidad().getNombreResponsable());
        txtFechaCreacion.setValue(getEntidad().getFechaCreacion());
        txtAlmacen.setText(getEntidad().getDescripcionAlmacen());
        txtMoneda.setText(getEntidad().getSimboloMoneda());
        txtSubTotal.setValue(getEntidad().getSubTotal());
        txtImpuesto.setValue(getEntidad().getMontoImpuesto());
        txtTotal.setValue(getEntidad().getTotal());
        for (ItemSalidaInventario item : getEntidad().getItems()) {
            AgregarFila(tbItems,
                    new Object[]{
                        item.getIdItemSalidaInventario(),
                        item.getIdProducto(),
                        item.getCodigoProducto(),
                        item.getDescripcionProducto(),
                        item.getIdUnidad(),
                        item.getFactor(),
                        null,
                        item.getAbreviacionUnidad(),
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
            double totalItem = ObtenerValorCelda(tbItems, i, 10);
            subTotal += totalItem;
        }
        getEntidad().setSubTotal(subTotal);
        getEntidad().setTotal(subTotal);
        txtSubTotal.setValue(subTotal);
        txtTotal.setValue(subTotal);
    }

    public List<ItemSalidaInventario> getItems() {
        List<ItemSalidaInventario> items = new ArrayList<>();
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            ItemSalidaInventario item = new ItemSalidaInventario();
            item.setIdProducto(ObtenerValorCelda(tbItems, i, 1));
            item.setCodigoProducto(ObtenerValorCelda(tbItems, i, 2));
            item.setDescripcionProducto(ObtenerValorCelda(tbItems, i, 3));
            item.setIdUnidad(ObtenerValorCelda(tbItems, i, 4));
            item.setFactor(ObtenerValorCelda(tbItems, i, 5));
            item.setAbreviacionUnidad(ObtenerValorCelda(tbItems, i, 7));
            item.setCantidad(ObtenerValorCelda(tbItems, i, 8));
            item.setPrecio(ObtenerValorCelda(tbItems, i, 9));
            item.setTotal(ObtenerValorCelda(tbItems, i, 10));
            items.add(item);
        }
        return items;
    }

    Action change_item = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] celda = (int[]) e.getSource();
            switch (celda[1]) {
                case 7:
                    String unidad = ObtenerValorCelda(tbItems, celda[0], celda[1]);
                    List<Object[]> unidades = ObtenerValorCelda(tbItems, celda[0], 6);
                    for (Object[] item : unidades) {
                        if (item[2].equals(unidad)) {
                            AsignarValorCelda(tbItems, item[1], celda[0], 4);
                            AsignarValorCelda(tbItems, item[3], celda[0], 5);
                            break;
                        }
                    }
                    break;
                case 8:
                    double cantidad8 = ObtenerValorCelda(tbItems, celda[0], celda[1]);
                    double precio8 = ObtenerValorCelda(tbItems, celda[0], 9);
                    AsignarValorCelda(tbItems, cantidad8 * precio8, celda[0], 10);
                    CalcularTotales();
                    break;
                case 9:
                    double cantidad9 = ObtenerValorCelda(tbItems, celda[0], 8);
                    double precio9 = ObtenerValorCelda(tbItems, celda[0], celda[1]);
                    AsignarValorCelda(tbItems, cantidad9 * precio9, celda[0], 10);
                    CalcularTotales();
                    break;
            }
        }
    };

    Action select_item = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<Producto> seleccionados = ((lisProducto) evt.getSource()).getSeleccionados();
            if (!seleccionados.isEmpty()) {
                cliInventarios cliente = new cliInventarios();
                try {
                    String json = cliente.ObtenerProductoUnidades(new Gson().toJson(seleccionados));
                    String[] resultado = new Gson().fromJson(json, String[].class);
                    List<Producto> productos = (List<Producto>) new Gson().fromJson(resultado[1], new TypeToken<List<Producto>>() {
                    }.getType());
                    for (Producto producto : productos) {
                        AgregarFila(tbItems,
                                new Object[]{
                                    0,
                                    producto.getIdProducto(),
                                    producto.getCodigo(),
                                    producto.getDescripcion(),
                                    ((Double) producto.getProductoUnidades().get(0)[1]).intValue(),
                                    ((Double) producto.getProductoUnidades().get(0)[3]).intValue(),
                                    producto.getProductoUnidades(),
                                    producto.getProductoUnidades().get(0)[2],
                                    0.0,
                                    0.0,
                                    0.0
                                });
                    }
                    AgregarEventoChange(tbItems, change_item);
                } catch (Exception e) {
                } finally {
                    cliente.close();
                }
            }
        }
    };

    Action select_clie = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Proveedor seleccionado = ((lisProveedor) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                //getEntidad().setIdProveedor(seleccionado.getIdProveedor());
                //getEntidad().setRazonSocialProveedor(seleccionado.getRazonSocial());
                txtCliente.setText(seleccionado.getRazonSocial());
            }
        }
    };

    Action select_resp = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Empleado seleccionado = ((lisEmpleado) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                getEntidad().setIdResponsable(seleccionado.getIdEmpleado());
                getEntidad().setNombreResponsable(seleccionado.getNombre());
                txtResponsable.setText(seleccionado.getNombre());
            }
        }
    };

    Action select_alma = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Almacen seleccionado = ((lisAlmacen) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                getEntidad().setIdAlmacen(seleccionado.getIdAlmacen());
                getEntidad().setDescripcionAlmacen(seleccionado.getDescripcion());
                txtAlmacen.setText(seleccionado.getDescripcion());
            }
        }
    };

    Action select_mone = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Moneda seleccionado = ((lisMoneda) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                getEntidad().setIdMoneda(seleccionado.getIdMoneda());
                getEntidad().setSimboloMoneda(seleccionado.getSimbolo());
                txtMoneda.setText(seleccionado.getSimbolo());
            }
        }
    };

    public class swObtenerSalidaInventario extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerSalidaInventario(new Gson().toJson(id));
            } catch (Exception e) {
                OcultarCargando(frame);
                json = "[\"false\"]";
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
                    setEntidad(new Gson().fromJson(resultado[1], SalidaInventario.class));
                    AsignarControles();
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
            }
        }
    }

    public class swGuardarSalidaInventario extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                List<String> arrayJson = new ArrayList<>();
                getEntidad().setItems(getItems());
                arrayJson.add(new Gson().toJson(getEntidad()));
                json = cliente.RegistrarSalidaInventario(new Gson().toJson(arrayJson));
            } catch (Exception e) {
                OcultarProcesando(frame);
                json = "[\"false\"]";
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
            }
        }
    }

    @Override
    public void Aceptar() {
        if (this.id == 0) {
            new swGuardarSalidaInventario().execute();
        } else {
            Cerrar();
        }
    }

    @Override
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
        lblCliente = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtResponsable = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlUnidades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbItems = new javax.swing.JTable(){
            public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);
                DefaultCellEditor cell = (DefaultCellEditor)tbItems.getCellEditor(row, column);
                if(cell.getComponent() instanceof JTextField){
                    tbItems.editCellAt(row, column);
                    tbItems.transferFocus();
                    ((JTextField)cell.getComponent()).selectAll();
                }
            }
        }
        ;
        btnNuevoItem = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblMoneda = new javax.swing.JLabel();
        txtMoneda = new javax.swing.JTextField();
        lblAlmacen = new javax.swing.JLabel();
        txtAlmacen = new javax.swing.JTextField();
        txtFechaCreacion = new javax.swing.JFormattedTextField();
        lblFechaCreacion = new javax.swing.JLabel();
        lblSubTotal = new javax.swing.JLabel();
        lblImpuesto = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JFormattedTextField();
        txtTotal = new javax.swing.JFormattedTextField();
        txtImpuesto = new javax.swing.JFormattedTextField();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblCliente.setText("CLIENTE");

        lblNombre.setText("RESPONSABLE");

        txtResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResponsableActionPerformed(evt);
            }
        });

        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });

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
                "IDITEM", "IDPRODUCTO", "CODIGO", "PRODUCTO", "IDUNIDAD", "FACTOR", "ARRAYUNIDAD", "UNIDAD", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbItems.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tbItems);
        if (tbItems.getColumnModel().getColumnCount() > 0) {
            tbItems.getColumnModel().getColumn(0).setMinWidth(0);
            tbItems.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(0).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(1).setMinWidth(0);
            tbItems.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(1).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(3).setPreferredWidth(230);
            tbItems.getColumnModel().getColumn(4).setMinWidth(0);
            tbItems.getColumnModel().getColumn(4).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(4).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(5).setMinWidth(0);
            tbItems.getColumnModel().getColumn(5).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(5).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(6).setMinWidth(0);
            tbItems.getColumnModel().getColumn(6).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(6).setMaxWidth(0);
        }

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

        javax.swing.GroupLayout pnlUnidadesLayout = new javax.swing.GroupLayout(pnlUnidades);
        pnlUnidades.setLayout(pnlUnidadesLayout);
        pnlUnidadesLayout.setHorizontalGroup(
            pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUnidadesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlUnidadesLayout.setVerticalGroup(
            pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnidadesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarItem)
                    .addComponent(btnNuevoItem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ITEMS", pnlUnidades);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("SALIDA DE  INVENTARIO");

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

        txtMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMonedaActionPerformed(evt);
            }
        });

        lblAlmacen.setText("ALMACEN");

        txtAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlmacenActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAlmacen)
                            .addComponent(lblNombre)
                            .addComponent(lblCliente))
                        .addGap(24, 24, 24)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCliente)
                            .addComponent(txtResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtAlmacen))
                        .addGap(29, 29, 29)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMoneda, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(txtFechaCreacion)
                            .addComponent(txtNumero)))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addGap(420, 420, 420)
                            .addComponent(lblImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2))))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente)
                    .addComponent(lblNumero)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre)
                    .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaCreacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlmacen)
                    .addComponent(lblMoneda)
                    .addComponent(txtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubTotal)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImpuesto)
                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResponsableActionPerformed
        // TODO add your handling code here:
        VerModal(new lisEmpleado(1), select_resp);
    }//GEN-LAST:event_txtResponsableActionPerformed

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
        //FabricaControles.VerModal(this.getDesktopPane(), new lisProveedor(1), select_prov);
    }//GEN-LAST:event_txtClienteActionPerformed

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
        VerModal(new lisProducto(2), select_item);
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbItems)) {
            EliminarFila(tbItems);
            CalcularTotales();
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed

    private void txtMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMonedaActionPerformed
        // TODO add your handling code here:
        VerModal(new lisMoneda(1), select_mone);
    }//GEN-LAST:event_txtMonedaActionPerformed

    private void txtAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlmacenActionPerformed
        // TODO add your handling code here:
        VerModal(new lisAlmacen(1), select_alma);
    }//GEN-LAST:event_txtAlmacenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAlmacen;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblImpuesto;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel pnlUnidades;
    private javax.swing.JTable tbItems;
    private javax.swing.JTextField txtAlmacen;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JFormattedTextField txtImpuesto;
    private javax.swing.JTextField txtMoneda;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtResponsable;
    private javax.swing.JFormattedTextField txtSubTotal;
    private javax.swing.JFormattedTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
