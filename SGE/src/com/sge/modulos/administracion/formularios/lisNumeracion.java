package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Numeracion;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisNumeracion extends frameBase<Numeracion> {

    /**
     * Creates new form lisNumeracion
     *
     * @param modo
     */
    public lisNumeracion(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form lisNumeracion
     *
     * @param modo
     * @param filtro
     */
    public lisNumeracion(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        switch (getModo()) {
            case 0:
                OcultarColumnas(tbNumeraciones, new int[]{0, 1});
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbNumeraciones, new int[]{0, 1, 6, 7});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerNumeraciones().execute();
    }

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarNumeracion();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarNumeracion();
        }
    };

    Action refr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swObtenerNumeraciones().execute();
        }
    };

    public class swObtenerNumeraciones extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerNumeraciones(new Gson().toJson(getFiltro()));
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
                    Numeracion[] numeraciones = new Gson().fromJson(resultado[1], Numeracion[].class);
                    if (getModo() == 1 && numeraciones.length == 1) {
                        setSeleccionado(numeraciones[0]);
                        Cerrar();
                    } else {
                        EliminarTodasFilas(tbNumeraciones);
                        for (Numeracion numeracion : numeraciones) {
                            AgregarFila(tbNumeraciones, new Object[]{false, numeracion.getIdNumeracion(), numeracion.getDescripcion(), numeracion.getNombreEntidad(), numeracion.isManual(), numeracion.isActivo(), Icon_Edit, Icon_Dele});
                        }
                        AgregarBoton(tbNumeraciones, edit, 6);
                        AgregarBoton(tbNumeraciones, dele, 7);
                        AgregarOrdenamiento(tbNumeraciones);
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarNumeracion extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                int idNumeracion = ObtenerValorCelda(tbNumeraciones, 1);
                json = cliente.EliminarNumeracion(new Gson().toJson(idNumeracion));
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
                    new swObtenerNumeraciones().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void EditarNumeracion() {
        int idNumeracion = ObtenerValorCelda(tbNumeraciones, 1);
        VerFrame(new regNumeracion(idNumeracion), refr);
    }

    public void EliminarNumeracion() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarNumeracion().execute();
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
        tbNumeraciones = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbNumeraciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "DESCRIPCION", "ENTIDAD", "MANUAL", "ACTIVO", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true
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
        tbNumeraciones.setRowHeight(25);
        tbNumeraciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbNumeraciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNumeracionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNumeraciones);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE NUMERACIONES");

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
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        lblFiltro.setText("FILTRO");

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });

        btnSeleccionar.setText("SELECCIONAR");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(15, 15, 15))
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
        VerFrame(new regNumeracion(0), refr);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbNumeraciones, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (getModo()) {
            case 1:
                if (FilaActiva(tbNumeraciones)) {
                    Numeracion numeracion = new Numeracion();
                    numeracion.setIdNumeracion(ObtenerValorCelda(tbNumeraciones, 1));
                    numeracion.setDescripcion(ObtenerValorCelda(tbNumeraciones, 2));
                    numeracion.setManual(ObtenerValorCelda(tbNumeraciones, 4));
                    numeracion.setActivo(ObtenerValorCelda(tbNumeraciones, 5));
                    setSeleccionado(numeracion);
                }
                Cerrar();
                break;
            case 2:
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerNumeraciones().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void tbNumeracionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNumeracionesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            switch (getModo()) {
                case 1:
                    if (FilaActiva(tbNumeraciones)) {
                        Numeracion numeracion = new Numeracion();
                        numeracion.setIdNumeracion(ObtenerValorCelda(tbNumeraciones, 1));
                        numeracion.setDescripcion(ObtenerValorCelda(tbNumeraciones, 2));
                        numeracion.setManual(ObtenerValorCelda(tbNumeraciones, 4));
                        numeracion.setActivo(ObtenerValorCelda(tbNumeraciones, 5));
                        setSeleccionado(numeracion);
                    }
                    Cerrar();
                    break;
                case 2:
                    break;
            }
        }
    }//GEN-LAST:event_tbNumeracionesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbNumeraciones;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
