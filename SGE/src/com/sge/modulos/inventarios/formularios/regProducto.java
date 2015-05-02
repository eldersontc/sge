package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.inventarios.clases.Almacen;
import com.sge.modulos.inventarios.clases.Producto;
import com.sge.modulos.inventarios.clases.ProductoAlmacen;
import com.sge.modulos.inventarios.clases.ProductoUnidad;
import com.sge.modulos.inventarios.clases.Unidad;
import com.sge.modulos.inventarios.cliente.cliInventarios;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regProducto extends frameBase<Producto> {

    /**
     * Creates new form regProducto
     */
    public regProducto(String operacion, int idProducto) {
        initComponents();
        Init(operacion, idProducto);
    }

    int idProducto = 0;

    private List<ProductoUnidad> productoUnidades = new ArrayList<>();
    private List<ProductoAlmacen> productoAlmacenes = new ArrayList<>();

    Action sele_unidad = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Unidad> unidades = ((lisUnidad) e.getSource()).getSeleccionados();
            for (Unidad unidad : unidades) {
                AgregarFila(tbUnidades, new Object[]{0, unidad.getIdUnidad(), unidad.getAbreviacion(), 0});
            }
        }
    };

    Action sele_almacen = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Almacen> almacenes = ((lisAlmacen) e.getSource()).getSeleccionados();
            for (Almacen almacen : almacenes) {
                productoAlmacenes.add(new ProductoAlmacen(almacen));
                AgregarFila(tbAlmacenes, new Object[]{0, almacen.getIdAlmacen(), almacen.getDescripcion(), 0, 0, 0, 0});
            }
        }
    };

    public void Init(String operacion, int idProducto) {
        lblTitulo.setText(operacion + lblTitulo.getText());
        this.idProducto = idProducto;
        if (this.idProducto > 0) {
            new swObtenerProducto().execute();
        }
    }

    public List<ProductoUnidad> getProductoUnidades() {
        for (int i = 0; i < tbUnidades.getRowCount(); i++) {
            int idProductoUnidad = ObtenerValorCelda(tbUnidades, i, 0);
            if (idProductoUnidad == 0) {
                ProductoUnidad productoUnidad = new ProductoUnidad();
                productoUnidad.setUnidad(new Unidad(ObtenerValorCelda(tbUnidades, i, 1)));
                productoUnidad.setFactor(ObtenerValorCelda(tbUnidades, i, 3));
                productoUnidad.setAgregar(true);
                productoUnidades.add(productoUnidad);
            } else {
                ProductoUnidad productoUnidad = new ProductoUnidad();
                productoUnidad.setIdProductoUnidad(idProductoUnidad);
                productoUnidad.setFactor(ObtenerValorCelda(tbUnidades, i, 3));
                productoUnidad.setActualizar(true);
                productoUnidades.add(productoUnidad);
            }
        }
        return productoUnidades;
    }

    public class swObtenerProducto extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerProducto(new Gson().toJson(idProducto));
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
                    Producto producto = new Gson().fromJson(resultado[1], Producto.class);
                    List<Object[]> productoUnidades = (List<Object[]>) new Gson().fromJson(resultado[2], new TypeToken<List<Object[]>>() {
                    }.getType());
                    List<Object[]> productoAlmacenes = (List<Object[]>) new Gson().fromJson(resultado[3], new TypeToken<List<Object[]>>() {
                    }.getType());
                    txtCodigo.setText(producto.getCodigo());
                    txtDescripcion.setText(producto.getDescripcion());
                    chkInventarios.setSelected(producto.isInventarios());
                    chkCompras.setSelected(producto.isCompras());
                    chkVentas.setSelected(producto.isVentas());
                    chkActivo.setSelected(producto.isActivo());
                    for (Object[] productoUnidad : productoUnidades) {
                        AgregarFila(tbUnidades,
                                new Object[]{
                                    ((Double) productoUnidad[0]).intValue(),
                                    ((Double) productoUnidad[1]).intValue(),
                                    productoUnidad[2],
                                    ((Double) productoUnidad[3]).intValue()
                                });
                    }
                    for (Object[] productoAlmacen : productoAlmacenes) {
                        AgregarFila(tbAlmacenes,
                                new Object[]{
                                    ((Double) productoAlmacen[0]).intValue(),
                                    ((Double) productoAlmacen[1]).intValue(),
                                    productoAlmacen[2],
                                    ((Double) productoAlmacen[3]).intValue(),
                                    ((Double) productoAlmacen[4]).intValue(),
                                    ((Double) productoAlmacen[5]).intValue(),
                                    ((Double) productoAlmacen[6]).intValue()
                                });
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGuardarProducto extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                List<String> arrayJson = new ArrayList<>();
                Producto producto = new Producto();
                producto.setCodigo(txtCodigo.getText());
                producto.setDescripcion(txtDescripcion.getText());
                producto.setInventarios(chkInventarios.isSelected());
                producto.setCompras(chkCompras.isSelected());
                producto.setVentas(chkVentas.isSelected());
                producto.setActivo(chkActivo.isSelected());
                if (idProducto == 0) {
                    arrayJson.add(new Gson().toJson(producto));
                    arrayJson.add(new Gson().toJson(getProductoUnidades()));
                    arrayJson.add(new Gson().toJson(productoAlmacenes));
                    json = cliente.RegistrarProducto(new Gson().toJson(arrayJson));
                } else {
                    producto.setIdProducto(idProducto);
                    arrayJson.add(new Gson().toJson(producto));
                    arrayJson.add(new Gson().toJson(getProductoUnidades()));
                    arrayJson.add(new Gson().toJson(productoAlmacenes));
                    json = cliente.ActualizarProducto(new Gson().toJson(arrayJson));
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
                }
            } catch (Exception e) {
                OcultarProcesando(frame);
                ControlarExcepcion(e);
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
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        chkActivo = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabUnidades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUnidades = new javax.swing.JTable();
        btnNuevaUnidad = new javax.swing.JButton();
        btnEliminarUnidad = new javax.swing.JButton();
        tabAlmacenes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAlmacenes = new javax.swing.JTable();
        btnNuevoAlmacen = new javax.swing.JButton();
        btnEliminarAlmacen = new javax.swing.JButton();
        chkCompras = new javax.swing.JCheckBox();
        chkVentas = new javax.swing.JCheckBox();
        chkInventarios = new javax.swing.JCheckBox();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblCodigo.setText("CODIGO");

        lblNombre.setText("DESCRIPCION");

        chkActivo.setText("ACTIVO");

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

        tabUnidades.setBackground(java.awt.Color.white);
        tabUnidades.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbUnidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDPRODUCTOUNIDAD", "IDUNIDAD", "UNIDAD", "FACTOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUnidades.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tbUnidades);
        if (tbUnidades.getColumnModel().getColumnCount() > 0) {
            tbUnidades.getColumnModel().getColumn(0).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(0).setMaxWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setMaxWidth(0);
            tbUnidades.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        btnNuevaUnidad.setText("NUEVO");
        btnNuevaUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaUnidadActionPerformed(evt);
            }
        });

        btnEliminarUnidad.setText("ELIMINAR");
        btnEliminarUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUnidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabUnidadesLayout = new javax.swing.GroupLayout(tabUnidades);
        tabUnidades.setLayout(tabUnidadesLayout);
        tabUnidadesLayout.setHorizontalGroup(
            tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUnidadesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(btnNuevaUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabUnidadesLayout.setVerticalGroup(
            tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUnidadesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(tabUnidadesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNuevaUnidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarUnidad)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("UNIDADES", tabUnidades);

        tabAlmacenes.setBackground(java.awt.Color.white);
        tabAlmacenes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbAlmacenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDPRODUCTOALMACEN", "IDALMACEN", "ALMACEN", "S. FISICO", "S. COMPROMETIDO", "S. SOLICITADO", "S. DISPONIBLE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAlmacenes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tbAlmacenes);
        if (tbAlmacenes.getColumnModel().getColumnCount() > 0) {
            tbAlmacenes.getColumnModel().getColumn(0).setMinWidth(0);
            tbAlmacenes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbAlmacenes.getColumnModel().getColumn(0).setMaxWidth(0);
            tbAlmacenes.getColumnModel().getColumn(1).setMinWidth(0);
            tbAlmacenes.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbAlmacenes.getColumnModel().getColumn(1).setMaxWidth(0);
            tbAlmacenes.getColumnModel().getColumn(2).setPreferredWidth(200);
            tbAlmacenes.getColumnModel().getColumn(4).setPreferredWidth(150);
            tbAlmacenes.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbAlmacenes.getColumnModel().getColumn(6).setPreferredWidth(120);
        }

        btnNuevoAlmacen.setText("NUEVO");
        btnNuevoAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAlmacenActionPerformed(evt);
            }
        });

        btnEliminarAlmacen.setText("ELIMINAR");
        btnEliminarAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAlmacenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabAlmacenesLayout = new javax.swing.GroupLayout(tabAlmacenes);
        tabAlmacenes.setLayout(tabAlmacenesLayout);
        tabAlmacenesLayout.setHorizontalGroup(
            tabAlmacenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAlmacenesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabAlmacenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(btnNuevoAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabAlmacenesLayout.setVerticalGroup(
            tabAlmacenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAlmacenesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(tabAlmacenesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNuevoAlmacen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarAlmacen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ALMACENES", tabAlmacenes);

        chkCompras.setText("COMPRAS");

        chkVentas.setText("VENTAS");

        chkInventarios.setText("INVENTARIOS");

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("PRODUCTO");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(chkInventarios, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(chkCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCodigo)
                                .addComponent(lblNombre))
                            .addGap(24, 24, 24)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(frameLayout.createSequentialGroup()
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActivo)
                    .addComponent(lblCodigo))
                .addGap(3, 3, 3)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCompras)
                    .addComponent(chkVentas)
                    .addComponent(chkInventarios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
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
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarProducto().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAlmacenActionPerformed
        // TODO add your handling code here:
        VerModal(new lisAlmacen(2), sele_almacen);
    }//GEN-LAST:event_btnNuevoAlmacenActionPerformed

    private void btnEliminarAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAlmacenActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbAlmacenes)) {
            int idProductoAlmacen = ObtenerValorCelda(tbAlmacenes, 0);
            if (idProductoAlmacen == 0) {
                int idAlmacen = ObtenerValorCelda(tbAlmacenes, 1);
                productoAlmacenes.removeIf(p -> p.getAlmacen().getIdAlmacen() == idAlmacen);
            } else {
                productoAlmacenes.add(new ProductoAlmacen(idProductoAlmacen));
            }
            EliminarFila(tbAlmacenes);
        }
    }//GEN-LAST:event_btnEliminarAlmacenActionPerformed

    private void btnNuevaUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaUnidadActionPerformed
        // TODO add your handling code here:
        VerModal(new lisUnidad(2), sele_unidad);
    }//GEN-LAST:event_btnNuevaUnidadActionPerformed

    private void btnEliminarUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUnidadActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbUnidades)) {
            int idProductoUnidad = ObtenerValorCelda(tbUnidades, 0);
            if (idProductoUnidad > 0) {
                productoUnidades.add(new ProductoUnidad(idProductoUnidad, true));
            }
            EliminarFila(tbUnidades);
        }
    }//GEN-LAST:event_btnEliminarUnidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarAlmacen;
    private javax.swing.JButton btnEliminarUnidad;
    private javax.swing.JButton btnNuevaUnidad;
    private javax.swing.JButton btnNuevoAlmacen;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JCheckBox chkCompras;
    private javax.swing.JCheckBox chkInventarios;
    private javax.swing.JCheckBox chkVentas;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel tabAlmacenes;
    private javax.swing.JPanel tabUnidades;
    private javax.swing.JTable tbAlmacenes;
    private javax.swing.JTable tbUnidades;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
