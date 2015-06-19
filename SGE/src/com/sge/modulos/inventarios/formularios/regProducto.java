package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
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
     *
     * @param id
     */
    public regProducto(int id) {
        initComponents();
        setId(id);
    }

    @Override
    public void Init() {
        if (getId() == 0) {
            lblTitulo.setText("NUEVO " + lblTitulo.getText());
            setEntidad(new Producto());
        } else {
            lblTitulo.setText("EDITAR " + lblTitulo.getText());
            new swObtenerProducto().execute();
        }
    }

    private List<ProductoUnidad> unidades = new ArrayList<>();
    private List<ProductoAlmacen> almacenes = new ArrayList<>();

    Action sele_unidad = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Unidad> seleccionados = ((lisUnidad) e.getSource()).getSeleccionados();
            for (Unidad seleccionado : seleccionados) {
                AgregarFila(tbUnidades, new Object[]{0, seleccionado.getIdUnidad(), seleccionado.getAbreviacion(), 0});
            }
        }
    };

    Action sele_almacen = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Almacen> seleccionados = ((lisAlmacen) e.getSource()).getSeleccionados();
            for (Almacen seleccionado : seleccionados) {
                ProductoAlmacen productoAlmacen = new ProductoAlmacen();
                productoAlmacen.setIdAlmacen(seleccionado.getIdAlmacen());
                productoAlmacen.setDescripcionAlmacen(seleccionado.getDescripcion());
                almacenes.add(productoAlmacen);
                AgregarFila(tbAlmacenes, new Object[]{0, seleccionado.getIdAlmacen(), seleccionado.getDescripcion(), 0, 0, 0, 0});
            }
        }
    };

    Action change_unidad = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] celda = (int[]) e.getSource();
            switch (celda[1]) {
                case 4:
                    boolean base = ObtenerValorCelda(tbUnidades, celda[0], celda[1]);
                    if (base) {
                        getEntidad().setIdUnidadBase(ObtenerValorCelda(tbUnidades, celda[0], 1));
                        getEntidad().setAbreviacionUnidadBase(ObtenerValorCelda(tbUnidades, celda[0], 2));
                        getEntidad().setFactorUnidadBase(ObtenerValorCelda(tbUnidades, celda[0], 3));
                    }
                    break;
            }
        }
    };

    public List<ProductoUnidad> getProductoUnidades() {
        for (int i = 0; i < tbUnidades.getRowCount(); i++) {
            int idProductoUnidad = ObtenerValorCelda(tbUnidades, i, 0);
            ProductoUnidad productoUnidad = new ProductoUnidad();
            productoUnidad.setIdUnidad(ObtenerValorCelda(tbUnidades, i, 1));
            productoUnidad.setAbreviacionUnidad(ObtenerValorCelda(tbUnidades, i, 2));
            productoUnidad.setFactor(ObtenerValorCelda(tbUnidades, i, 3));
            productoUnidad.setBase(ObtenerValorCelda(tbUnidades, i, 4));
            if (idProductoUnidad == 0) {
                productoUnidad.setAgregar(true);
            } else {
                productoUnidad.setIdProductoUnidad(idProductoUnidad);
                productoUnidad.setActualizar(true);
            }
            unidades.add(productoUnidad);
        }
        return unidades;
    }

    public class swObtenerProducto extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerProducto(new Gson().toJson(getId()));
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
                    setEntidad(new Gson().fromJson(resultado[1], Producto.class));
                    txtCodigo.setText(getEntidad().getCodigo());
                    txtDescripcion.setText(getEntidad().getDescripcion());
                    txtAlto.setText(String.valueOf(getEntidad().getAlto()));
                    txtLargo.setText(String.valueOf(getEntidad().getLargo()));
                    chkInventarios.setSelected(getEntidad().isInventarios());
                    chkCompras.setSelected(getEntidad().isCompras());
                    chkVentas.setSelected(getEntidad().isVentas());
                    chkActivo.setSelected(getEntidad().isActivo());
                    for (ProductoUnidad productoUnidad : getEntidad().getUnidades()) {
                        AgregarFila(tbUnidades,
                                new Object[]{
                                    productoUnidad.getIdProductoUnidad(),
                                    productoUnidad.getIdUnidad(),
                                    productoUnidad.getAbreviacionUnidad(),
                                    productoUnidad.getFactor(),
                                    productoUnidad.isBase()
                                });
                    }
                    AgregarEventoChange(tbUnidades, change_unidad);
                    for (ProductoAlmacen productoAlmacen : getEntidad().getAlmacenes()) {
                        AgregarFila(tbAlmacenes,
                                new Object[]{
                                    productoAlmacen.getIdProductoAlmacen(),
                                    productoAlmacen.getIdProducto(),
                                    productoAlmacen.getDescripcionAlmacen(),
                                    productoAlmacen.getStockFisico(),
                                    productoAlmacen.getStockComprometido(),
                                    productoAlmacen.getStockSolicitado(),
                                    productoAlmacen.getStockDisponible()
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
                getEntidad().setCodigo(txtCodigo.getText());
                getEntidad().setDescripcion(txtDescripcion.getText());
                getEntidad().setAlto(Double.parseDouble(txtAlto.getText()));
                getEntidad().setLargo(Double.parseDouble(txtLargo.getText()));
                getEntidad().setInventarios(chkInventarios.isSelected());
                getEntidad().setCompras(chkCompras.isSelected());
                getEntidad().setVentas(chkVentas.isSelected());
                getEntidad().setActivo(chkActivo.isSelected());
                getEntidad().setUnidades(getProductoUnidades());
                getEntidad().setAlmacenes(almacenes);
                if (getId() == 0) {
                    json = cliente.RegistrarProducto(new Gson().toJson(getEntidad()));
                } else {
                    getEntidad().setIdProducto(getId());
                    json = cliente.ActualizarProducto(new Gson().toJson(getEntidad()));
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
                    Cerrar();
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
        txtAlto = new javax.swing.JTextField();
        txtLargo = new javax.swing.JTextField();
        lblLargo = new javax.swing.JLabel();
        lblAlto = new javax.swing.JLabel();

        frame.setBackground(java.awt.Color.white);

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
                "ID", "IDUNIDAD", "UNIDAD", "FACTOR", "BASE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUnidades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbUnidades);
        if (tbUnidades.getColumnModel().getColumnCount() > 0) {
            tbUnidades.getColumnModel().getColumn(0).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(0).setMaxWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        btnNuevaUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevaUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaUnidadActionPerformed(evt);
            }
        });

        btnEliminarUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevaUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "ID", "IDALMACEN", "ALMACEN", "S. FISICO", "S. COMPROMETIDO", "S. SOLICITADO", "S. DISPONIBLE"
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
        tbAlmacenes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbAlmacenes);
        if (tbAlmacenes.getColumnModel().getColumnCount() > 0) {
            tbAlmacenes.getColumnModel().getColumn(0).setMinWidth(0);
            tbAlmacenes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbAlmacenes.getColumnModel().getColumn(0).setMaxWidth(0);
            tbAlmacenes.getColumnModel().getColumn(1).setMinWidth(0);
            tbAlmacenes.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbAlmacenes.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        btnNuevoAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevoAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAlmacenActionPerformed(evt);
            }
        });

        btnEliminarAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabAlmacenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        txtAlto.setText("0");

        txtLargo.setText("0");

        lblLargo.setText("LARGO");

        lblAlto.setText("ALTO");

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chkInventarios)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCodigo)
                                .addComponent(lblNombre)
                                .addComponent(lblAlto))
                            .addGap(24, 24, 24)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(chkCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(frameLayout.createSequentialGroup()
                                            .addComponent(txtAlto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(21, 21, 21)
                                            .addComponent(lblLargo)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtLargo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chkVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAlto)
                    .addComponent(lblLargo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkInventarios)
                    .addComponent(chkCompras)
                    .addComponent(chkVentas))
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarProducto().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevaUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaUnidadActionPerformed
        // TODO add your handling code here:
        VerModal(new lisUnidad(2), sele_unidad);
    }//GEN-LAST:event_btnNuevaUnidadActionPerformed

    private void btnEliminarUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUnidadActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbUnidades)) {
            int idProductoUnidad = ObtenerValorCelda(tbUnidades, 0);
            if (idProductoUnidad > 0) {
                ProductoUnidad productoUnidad = new ProductoUnidad();
                productoUnidad.setIdProductoUnidad(idProductoUnidad);
                productoUnidad.setEliminar(true);
                unidades.add(productoUnidad);
            }
            EliminarFila(tbUnidades);
        }
    }//GEN-LAST:event_btnEliminarUnidadActionPerformed

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
                almacenes.removeIf(p -> p.getIdAlmacen() == idAlmacen);
            } else {
                ProductoAlmacen productoAlmacen = new ProductoAlmacen();
                productoAlmacen.setIdProductoAlmacen(idProductoAlmacen);
                almacenes.add(productoAlmacen);
            }
            EliminarFila(tbAlmacenes);
        }
    }//GEN-LAST:event_btnEliminarAlmacenActionPerformed

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
    private javax.swing.JLabel lblAlto;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblLargo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel tabAlmacenes;
    private javax.swing.JPanel tabUnidades;
    private javax.swing.JTable tbAlmacenes;
    private javax.swing.JTable tbUnidades;
    private javax.swing.JTextField txtAlto;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtLargo;
    // End of variables declaration//GEN-END:variables
}
