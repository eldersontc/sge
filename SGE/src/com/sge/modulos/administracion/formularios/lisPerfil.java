package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Perfil;
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
public class lisPerfil extends frameBase<Perfil> {

    /**
     * Creates new form lisPerfil
     */
    public lisPerfil(int modo) {
        initComponents();
        Init(modo, "");
    }

    public lisPerfil(int modo, String filtro) {
        initComponents();
        Init(modo, filtro);
    }

    private int modo;

    private String filtro;

    private Perfil seleccionado;

    ImageIcon Icon_Save = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action save = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swGuardarPerfil().execute();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarPerfil();
        }
    };

    public class swObtenerPerfiles extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerPerfiles(new Gson().toJson(filtro));
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
                    EliminarTodasFilas(tbPerfiles);
                    Perfil[] perfiles = new Gson().fromJson(resultado[1], Perfil[].class);
                    for (Perfil perfil : perfiles) {
                        AgregarFila(tbPerfiles, new Object[]{false, perfil.getIdPerfil(), perfil.getNombre(), perfil.isActivo(), Icon_Save, Icon_Dele});
                    }
                    AgregarBoton(tbPerfiles, save, 4);
                    AgregarBoton(tbPerfiles, dele, 5);
                    AgregarOrdenamiento(tbPerfiles);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGuardarPerfil extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                Perfil perfil = new Perfil();
                perfil.setIdPerfil(ObtenerValorCelda(tbPerfiles, 1));
                perfil.setNombre(ObtenerValorCelda(tbPerfiles, 2));
                perfil.setActivo(ObtenerValorCelda(tbPerfiles, 3));
                if (perfil.getIdPerfil() == 0) {
                    json = cliente.RegistrarPerfil(new Gson().toJson(perfil));
                } else {
                    json = cliente.ActualizarPerfil(new Gson().toJson(perfil));
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
                    new swObtenerPerfiles().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarPerfil extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                int idPerfil = ObtenerValorCelda(tbPerfiles, 1);
                json = cliente.EliminarPerfil(new Gson().toJson(idPerfil));
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
                    new swObtenerPerfiles().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void Init(int modo, String filtro) {
        this.modo = modo;
        this.filtro = filtro;
        switch (this.modo) {
            case 0:
                OcultarColumna(tbPerfiles, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbPerfiles, new int[]{0, 4, 5});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerPerfiles().execute();
    }

    public void EliminarPerfil() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            int idPerfil = ObtenerValorCelda(tbPerfiles, 1);
            if (idPerfil == 0) {
                EliminarFila(tbPerfiles);
            } else {
                new swEliminarPerfil().execute();
            }
        }
    }

    public Perfil getSeleccionado() {
        return seleccionado;
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPerfiles = new javax.swing.JTable();
        pnlTitulo1 = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbPerfiles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "NOMBRE", "ACTIVO", "GUARDAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPerfiles.setRowHeight(25);
        tbPerfiles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbPerfiles);
        if (tbPerfiles.getColumnModel().getColumnCount() > 0) {
            tbPerfiles.getColumnModel().getColumn(1).setMinWidth(0);
            tbPerfiles.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbPerfiles.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        pnlTitulo1.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo1.setForeground(java.awt.Color.white);
        lblTitulo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo1.setText("LISTADO DE PERFILES");

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTitulo1Layout = new javax.swing.GroupLayout(pnlTitulo1);
        pnlTitulo1.setLayout(pnlTitulo1Layout);
        pnlTitulo1Layout.setHorizontalGroup(
            pnlTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitulo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addContainerGap())
        );
        pnlTitulo1Layout.setVerticalGroup(
            pnlTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitulo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo1)
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
            .addComponent(pnlTitulo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addGroup(frameLayout.createSequentialGroup()
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
                .addComponent(pnlTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFiltro)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
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
        AgregarFila(tbPerfiles, new Object[]{false, 0, "", false, Icon_Save, Icon_Dele});
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (this.modo) {
            case 1:
                if (FilaActiva(tbPerfiles)) {
                    Perfil perfil = new Perfil();
                    perfil.setIdPerfil(ObtenerValorCelda(tbPerfiles, 1));
                    perfil.setNombre(ObtenerValorCelda(tbPerfiles, 2));
                    seleccionado = perfil;
                }
                Cerrar();
                break;
            case 2:
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbPerfiles, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerPerfiles().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JPanel pnlTitulo1;
    private javax.swing.JTable tbPerfiles;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
