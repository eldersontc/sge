package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.MetodoImpresion;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisMetodoImpresion extends frameBase<MetodoImpresion> {

    /**
     * Creates new form lisMetodoImpresion
     *
     * @param modo
     */
    public lisMetodoImpresion(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form lisMetodoImpresion
     *
     * @param modo
     * @param filtro
     */
    public lisMetodoImpresion(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        switch (getModo()) {
            case 0:
                OcultarColumnas(tbMetodosImpresion, new int[]{0, 1});
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbMetodosImpresion, new int[]{0, 1, 9, 10});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerMetodosImpresion().execute();
    }

    ImageIcon Icon_Save = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action save = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swGuardarMetodoImpresion().execute();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarMetodoImpresion();
        }
    };

    public class swObtenerMetodosImpresion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                json = cliente.ObtenerMetodosImpresion(new Gson().toJson(getFiltro()));
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
                    MetodoImpresion[] metodosImpresion = new Gson().fromJson(resultado[1], MetodoImpresion[].class);
                    if (getModo() == 1 && metodosImpresion.length == 1) {
                        setSeleccionado(metodosImpresion[0]);
                        Cerrar();
                    } else {
                        EliminarTodasFilas(tbMetodosImpresion);
                        for (MetodoImpresion metodoImpresion : metodosImpresion) {
                            AgregarFila(tbMetodosImpresion, new Object[]{false, metodoImpresion.getIdMetodoImpresion(), metodoImpresion.getNombre(), metodoImpresion.getFactorPases(), metodoImpresion.getFactorCambios(), metodoImpresion.getFactorHorizontal(), metodoImpresion.getFactorVertical(), metodoImpresion.getLetras(), metodoImpresion.isActivo(), Icon_Save, Icon_Dele});
                        }
                        AgregarBoton(tbMetodosImpresion, save, 9);
                        AgregarBoton(tbMetodosImpresion, dele, 10);
                        AgregarOrdenamiento(tbMetodosImpresion);
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    };

    public class swGuardarMetodoImpresion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                MetodoImpresion metodoImpresion = new MetodoImpresion();
                metodoImpresion.setIdMetodoImpresion(ObtenerValorCelda(tbMetodosImpresion, 1));
                metodoImpresion.setNombre(ObtenerValorCelda(tbMetodosImpresion, 2));
                metodoImpresion.setFactorPases(ObtenerValorCelda(tbMetodosImpresion, 3));
                metodoImpresion.setFactorCambios(ObtenerValorCelda(tbMetodosImpresion, 4));
                metodoImpresion.setFactorHorizontal(ObtenerValorCelda(tbMetodosImpresion, 5));
                metodoImpresion.setFactorVertical(ObtenerValorCelda(tbMetodosImpresion, 6));
                metodoImpresion.setLetras(ObtenerValorCelda(tbMetodosImpresion, 7));
                metodoImpresion.setActivo(ObtenerValorCelda(tbMetodosImpresion, 8));
                if (metodoImpresion.getIdMetodoImpresion() == 0) {
                    json = cliente.RegistrarMetodoImpresion(new Gson().toJson(metodoImpresion));
                } else {
                    json = cliente.ActualizarMetodoImpresion(new Gson().toJson(metodoImpresion));
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
                    new swObtenerMetodosImpresion().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
            }
        }
    }

    public class swEliminarMetodoImpresion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idMetodoImpresion = ObtenerValorCelda(tbMetodosImpresion, 1);
                json = cliente.EliminarMetodoImpresion(new Gson().toJson(idMetodoImpresion));
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
                    new swObtenerMetodosImpresion().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void EliminarMetodoImpresion() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            int idMetodoImpresion = ObtenerValorCelda(tbMetodosImpresion, 1);
            if (idMetodoImpresion == 0) {
                EliminarFila(tbMetodosImpresion);
            } else {
                new swEliminarMetodoImpresion().execute();
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
        tbMetodosImpresion = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbMetodosImpresion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "NOMBRE", "F.PASES", "F.CAMBIOS", "F.HORIZONTAL", "F.VERTICAL", "LETRAS", "ACTIVO", "GUARDAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(getModo() == 0) {
                    return canEdit [columnIndex];
                } else {
                    return false;
                }
            }
        });
        tbMetodosImpresion.setRowHeight(25);
        tbMetodosImpresion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbMetodosImpresion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMetodosImpresionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMetodosImpresion);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE METODOS DE IMPRESION");

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
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        AgregarFila(tbMetodosImpresion, new Object[]{false, 0, "", 0, 0, 1, 1, "", false, Icon_Save, Icon_Dele});
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (getModo()) {
            case 1:
                if (FilaActiva(tbMetodosImpresion)) {
                    MetodoImpresion metodoImpresion = new MetodoImpresion();
                    metodoImpresion.setIdMetodoImpresion(ObtenerValorCelda(tbMetodosImpresion, 1));
                    metodoImpresion.setNombre(ObtenerValorCelda(tbMetodosImpresion, 2));
                    metodoImpresion.setFactorPases(ObtenerValorCelda(tbMetodosImpresion, 3));
                    metodoImpresion.setFactorCambios(ObtenerValorCelda(tbMetodosImpresion, 4));
                    metodoImpresion.setFactorHorizontal(ObtenerValorCelda(tbMetodosImpresion, 5));
                    metodoImpresion.setFactorVertical(ObtenerValorCelda(tbMetodosImpresion, 6));
                    metodoImpresion.setLetras(ObtenerValorCelda(tbMetodosImpresion, 7));
                    metodoImpresion.setActivo(ObtenerValorCelda(tbMetodosImpresion, 8));
                    setSeleccionado(metodoImpresion);
                }
                Cerrar();
                break;
            case 2:
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbMetodosImpresion, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerMetodosImpresion().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void tbMetodosImpresionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMetodosImpresionMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            switch (getModo()) {
                case 1:
                    if (FilaActiva(tbMetodosImpresion)) {
                        MetodoImpresion metodoImpresion = new MetodoImpresion();
                        metodoImpresion.setIdMetodoImpresion(ObtenerValorCelda(tbMetodosImpresion, 1));
                        metodoImpresion.setNombre(ObtenerValorCelda(tbMetodosImpresion, 2));
                        metodoImpresion.setFactorPases(ObtenerValorCelda(tbMetodosImpresion, 3));
                        metodoImpresion.setFactorCambios(ObtenerValorCelda(tbMetodosImpresion, 4));
                        metodoImpresion.setFactorHorizontal(ObtenerValorCelda(tbMetodosImpresion, 5));
                        metodoImpresion.setFactorVertical(ObtenerValorCelda(tbMetodosImpresion, 6));
                        metodoImpresion.setLetras(ObtenerValorCelda(tbMetodosImpresion, 7));
                        metodoImpresion.setActivo(ObtenerValorCelda(tbMetodosImpresion, 8));
                        setSeleccionado(metodoImpresion);
                    }
                    Cerrar();
                    break;
                case 2:
                    break;
            }
        }
    }//GEN-LAST:event_tbMetodosImpresionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbMetodosImpresion;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
