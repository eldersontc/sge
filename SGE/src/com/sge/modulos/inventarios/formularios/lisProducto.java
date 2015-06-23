package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.inventarios.clases.Producto;
import com.sge.modulos.inventarios.cliente.cliInventarios;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisProducto extends frameBase<Producto> {

    /**
     * Creates new form lisProducto
     *
     * @param modo
     */
    public lisProducto(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form lisProducto
     *
     * @param modo
     * @param filtro
     */
    public lisProducto(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        switch (getModo()) {
            case 0:
                OcultarColumnas(tbProductos, new int[]{0, 1});
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbProductos, new int[]{0, 1, 10, 11});
                OcultarControl(btnNuevo);
                break;
            case 2:
                OcultarColumnas(tbProductos, new int[]{1, 10, 11});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerProductos().execute();
    }

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarProducto();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarProducto();
        }
    };

    Action refr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swObtenerProductos().execute();
        }
    };

    public class swObtenerProductos extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerProductos(new Gson().toJson(getFiltro()));
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
                    Producto[] productos = new Gson().fromJson(resultado[1], Producto[].class);
                    if (getModo() == 1 && productos.length == 1) {
                        setSeleccionado(productos[0]);
                        Cerrar();
                    } else if (getModo() == 2 && productos.length == 1) {
                        setSeleccionados(new ArrayList<>());
                        getSeleccionados().add(productos[0]);
                        Cerrar();
                    } else {
                        EliminarTodasFilas(tbProductos);
                        for (Producto producto : productos) {
                            AgregarFila(tbProductos, new Object[]{false, producto.getIdProducto(), producto.getCodigo(), producto.getDescripcion(), producto.getAlto(), producto.getLargo(), producto.getIdUnidadBase(), producto.getAbreviacionUnidadBase(), producto.getFactorUnidadBase(), producto.isActivo(), Icon_Edit, Icon_Dele});
                        }
                        AgregarBoton(tbProductos, edit, 10);
                        AgregarBoton(tbProductos, dele, 11);
                        AgregarOrdenamiento(tbProductos);
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarProducto extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                int idProducto = ObtenerValorCelda(tbProductos, 1);
                json = cliente.EliminarProducto(new Gson().toJson(idProducto));
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
                    new swObtenerProductos().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void EditarProducto() {
        int idProducto = ObtenerValorCelda(tbProductos, 1);
        VerFrame(new regProducto(idProducto), refr);
    }

    public void EliminarProducto() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarProducto().execute();
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
        tbProductos = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "CODIGO", "DESCRIPCION", "ALTO", "LARGO", "IDUNIDADBASE", "UNID.BASE", "FACTOR", "ACTIVO", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean isEditable = false;
                switch (getModo()) {
                    case 0:
                    isEditable = canEdit [columnIndex];
                    break;
                    case 1:
                    isEditable = false;
                    break;
                    case 2:
                    isEditable = columnIndex == 0;
                    break;
                }
                return isEditable;
            }
        });
        tbProductos.setRowHeight(25);
        tbProductos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbProductos);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE PRODUCTOS");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
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

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFiltro))
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(27, 27, 27))
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
        VerFrame(new regProducto(0), refr);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (getModo()) {
            case 1:
                if (FilaActiva(tbProductos)) {
                    setSeleccionado(new Producto());
                    getSeleccionado().setIdProducto(ObtenerValorCelda(tbProductos, 1));
                    getSeleccionado().setCodigo(ObtenerValorCelda(tbProductos, 2));
                    getSeleccionado().setDescripcion(ObtenerValorCelda(tbProductos, 3));
                    getSeleccionado().setAlto(ObtenerValorCelda(tbProductos, 4));
                    getSeleccionado().setLargo(ObtenerValorCelda(tbProductos, 5));
                    getSeleccionado().setIdUnidadBase(ObtenerValorCelda(tbProductos, 6));
                    getSeleccionado().setAbreviacionUnidadBase(ObtenerValorCelda(tbProductos, 7));
                    getSeleccionado().setFactorUnidadBase(ObtenerValorCelda(tbProductos, 8));
                    getSeleccionado().setActivo(ObtenerValorCelda(tbProductos, 9));
                    Cerrar();
                }
                break;
            case 2:
                setSeleccionados(new ArrayList<>());
                for (int i = 0; i < tbProductos.getRowCount(); i++) {
                    boolean check = ObtenerValorCelda(tbProductos, i, 0);
                    if (check) {
                        Producto producto = new Producto();
                        producto.setIdProducto(ObtenerValorCelda(tbProductos, i, 1));
                        producto.setCodigo(ObtenerValorCelda(tbProductos, i, 2));
                        producto.setDescripcion(ObtenerValorCelda(tbProductos, i, 3));
                        producto.setAlto(ObtenerValorCelda(tbProductos, i, 4));
                        producto.setLargo(ObtenerValorCelda(tbProductos, i, 5));
                        producto.setIdUnidadBase(ObtenerValorCelda(tbProductos, i, 6));
                        producto.setAbreviacionUnidadBase(ObtenerValorCelda(tbProductos, i, 7));
                        producto.setFactorUnidadBase(ObtenerValorCelda(tbProductos, i, 8));
                        producto.setActivo(ObtenerValorCelda(tbProductos, i, 9));
                        getSeleccionados().add(producto);
                    }
                }
                Cerrar();
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbProductos, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerProductos().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            switch (getModo()) {
                case 1:
                    if (FilaActiva(tbProductos)) {
                        setSeleccionado(new Producto());
                        getSeleccionado().setIdProducto(ObtenerValorCelda(tbProductos, 1));
                        getSeleccionado().setCodigo(ObtenerValorCelda(tbProductos, 2));
                        getSeleccionado().setDescripcion(ObtenerValorCelda(tbProductos, 3));
                        getSeleccionado().setAlto(ObtenerValorCelda(tbProductos, 4));
                        getSeleccionado().setLargo(ObtenerValorCelda(tbProductos, 5));
                        getSeleccionado().setIdUnidadBase(ObtenerValorCelda(tbProductos, 6));
                        getSeleccionado().setAbreviacionUnidadBase(ObtenerValorCelda(tbProductos, 7));
                        getSeleccionado().setFactorUnidadBase(ObtenerValorCelda(tbProductos, 8));
                        getSeleccionado().setActivo(ObtenerValorCelda(tbProductos, 9));
                        Cerrar();
                    }
                    break;
                case 2:
                    break;
            }
        }
    }//GEN-LAST:event_tbProductosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
