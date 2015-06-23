package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.inventarios.clases.Almacen;
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
public class lisAlmacen extends frameBase<Almacen> {

    /**
     * Creates new form lisAlmacen
     *
     * @param modo
     */
    public lisAlmacen(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form lisAlmacen
     *
     * @param modo
     * @param filtro
     */
    public lisAlmacen(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        switch (getModo()) {
            case 0:
                OcultarColumnas(tbAlmacenes, new int[]{0, 1});
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbAlmacenes, new int[]{0, 1, 5, 6});
                OcultarControl(btnNuevo);
                break;
            case 2:
                OcultarColumnas(tbAlmacenes, new int[]{1, 5, 6});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerAlmacenes().execute();
    }

    ImageIcon Icon_Save = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action save = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swGuardarAlmacen().execute();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarAlmacen();
        }
    };

    public class swObtenerAlmacenes extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerAlmacenes(new Gson().toJson(getFiltro()));
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
                    Almacen[] almacenes = new Gson().fromJson(resultado[1], Almacen[].class);
                    if (getModo() == 1 && almacenes.length == 1) {
                        setSeleccionado(almacenes[0]);
                        Cerrar();
                    } else if (getModo() == 2 && almacenes.length == 1) {
                        setSeleccionados(new ArrayList<>());
                        getSeleccionados().add(almacenes[0]);
                        Cerrar();
                    } else {
                        EliminarTodasFilas(tbAlmacenes);
                        for (Almacen almacen : almacenes) {
                            AgregarFila(tbAlmacenes, new Object[]{false, almacen.getIdAlmacen(), almacen.getCodigo(), almacen.getDescripcion(), almacen.isActivo(), Icon_Save, Icon_Dele});
                        }
                        AgregarBoton(tbAlmacenes, save, 5);
                        AgregarBoton(tbAlmacenes, dele, 6);
                        AgregarOrdenamiento(tbAlmacenes);
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGuardarAlmacen extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                Almacen almacen = new Almacen();
                almacen.setIdAlmacen(ObtenerValorCelda(tbAlmacenes, 1));
                almacen.setCodigo(ObtenerValorCelda(tbAlmacenes, 2));
                almacen.setDescripcion(ObtenerValorCelda(tbAlmacenes, 3));
                almacen.setActivo(ObtenerValorCelda(tbAlmacenes, 4));
                if (almacen.getIdAlmacen() == 0) {
                    json = cliente.RegistrarAlmacen(new Gson().toJson(almacen));
                } else {
                    json = cliente.ActualizarAlmacen(new Gson().toJson(almacen));
                }
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
                    new swObtenerAlmacenes().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarAlmacen extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                int idAlmacen = ObtenerValorCelda(tbAlmacenes, 1);
                json = cliente.EliminarAlmacen(new Gson().toJson(idAlmacen));
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
                    new swObtenerAlmacenes().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void EliminarAlmacen() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            int idAlmacen = ObtenerValorCelda(tbAlmacenes, 1);
            if (idAlmacen == 0) {
                EliminarFila(tbAlmacenes);
            } else {
                new swEliminarAlmacen().execute();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAlmacenes = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbAlmacenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "CODIGO", "DESCRIPCION", "ACTIVO", "GUARDAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true
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
        tbAlmacenes.setRowHeight(25);
        tbAlmacenes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbAlmacenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAlmacenesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAlmacenes);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE ALMACENES");

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
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFiltro)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(btnSeleccionar)
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
            .addComponent(frame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        AgregarFila(tbAlmacenes, new Object[]{false, 0, "", "", false, Icon_Save, Icon_Dele});
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (getModo()) {
            case 1:
                if (FilaActiva(tbAlmacenes)) {
                    Almacen almacen = new Almacen();
                    almacen.setIdAlmacen(ObtenerValorCelda(tbAlmacenes, 1));
                    almacen.setDescripcion(ObtenerValorCelda(tbAlmacenes, 3));
                    setSeleccionado(almacen);
                }
                Cerrar();
                break;
            case 2:
                setSeleccionados(new ArrayList<>());
                for (int i = 0; i < tbAlmacenes.getRowCount(); i++) {
                    boolean check = ObtenerValorCelda(tbAlmacenes, i, 0);
                    if (check) {
                        Almacen almacen = new Almacen();
                        almacen.setIdAlmacen(ObtenerValorCelda(tbAlmacenes, i, 1));
                        almacen.setCodigo(ObtenerValorCelda(tbAlmacenes, i, 2));
                        almacen.setDescripcion(ObtenerValorCelda(tbAlmacenes, i, 3));
                        almacen.setActivo(ObtenerValorCelda(tbAlmacenes, i, 4));
                        getSeleccionados().add(almacen);
                    }
                }
                Cerrar();
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbAlmacenes, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerAlmacenes().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void tbAlmacenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAlmacenesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            switch (getModo()) {
                case 1:
                    if (FilaActiva(tbAlmacenes)) {
                        Almacen almacen = new Almacen();
                        almacen.setIdAlmacen(ObtenerValorCelda(tbAlmacenes, 1));
                        almacen.setDescripcion(ObtenerValorCelda(tbAlmacenes, 3));
                        setSeleccionado(almacen);
                    }
                    Cerrar();
                    break;
                case 2:
                    break;
            }
        }
    }//GEN-LAST:event_tbAlmacenesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbAlmacenes;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
