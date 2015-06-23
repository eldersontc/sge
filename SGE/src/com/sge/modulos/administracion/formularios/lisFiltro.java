package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Entidad;
import com.sge.modulos.administracion.clases.Filtro;
import com.sge.modulos.administracion.clases.Usuario;
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
public class lisFiltro extends frameBase<Filtro> {

    /**
     * Creates new form lisFiltro
     *
     * @param modo
     */
    public lisFiltro(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form lisFiltro
     *
     * @param modo
     * @param filtro
     */
    public lisFiltro(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        switch (getModo()) {
            case 0:
                OcultarColumnas(tbFiltros, new int[]{0, 1});
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbFiltros, new int[]{0, 1, 8, 9});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerFiltros().execute();
    }

    ImageIcon Icon_Save = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action save = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swGuardarFiltro().execute();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarFiltro();
        }
    };

    Action slEn = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Entidad seleccionado = ((lisEntidad) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                AsignarValorCelda(tbFiltros, seleccionado.getIdEntidad(), 2);
                AsignarValorCelda(tbFiltros, seleccionado.getNombre(), 3);
            }
        }
    };

    Action slUs = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Usuario seleccionado = ((lisUsuario) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                AsignarValorCelda(tbFiltros, seleccionado.getIdUsuario(), 4);
                AsignarValorCelda(tbFiltros, seleccionado.getUsuario(), 5);
            }
        }
    };

    Action scEn = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            VerModal(new lisEntidad(1), slEn);
        }
    };

    Action scUs = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            VerModal(new lisUsuario(1), slUs);
        }
    };

    public class swObtenerFiltros extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerFiltros(new Gson().toJson(getFiltro()));
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
                    Filtro[] filtros = new Gson().fromJson(resultado[1], Filtro[].class);
                    if (getModo() == 1 && filtros.length == 1) {
                        setSeleccionado(filtros[0]);
                        Cerrar();
                    } else {
                        EliminarTodasFilas(tbFiltros);
                        for (Filtro filtro : filtros) {
                            AgregarFila(tbFiltros, new Object[]{false, filtro.getIdFiltro(), filtro.getIdEntidad(), filtro.getNombreEntidad(), filtro.getIdUsuario(), filtro.getUsuario(), filtro.getFiltro(), filtro.isActivo(), Icon_Save, Icon_Dele});
                        }
                        AgregarBoton(tbFiltros, scEn, 3);
                        AgregarBoton(tbFiltros, scUs, 5);
                        AgregarBoton(tbFiltros, save, 8);
                        AgregarBoton(tbFiltros, dele, 9);
                        AgregarOrdenamiento(tbFiltros);
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    };

    public class swGuardarFiltro extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                Filtro filtro = new Filtro();
                filtro.setIdFiltro(ObtenerValorCelda(tbFiltros, 1));
                filtro.setIdEntidad(ObtenerValorCelda(tbFiltros, 2));
                filtro.setNombreEntidad(ObtenerValorCelda(tbFiltros, 3));
                filtro.setIdUsuario(ObtenerValorCelda(tbFiltros, 4));
                filtro.setUsuario(ObtenerValorCelda(tbFiltros, 5));
                filtro.setFiltro(ObtenerValorCelda(tbFiltros, 6));
                filtro.setActivo(ObtenerValorCelda(tbFiltros, 7));
                if (filtro.getIdFiltro() == 0) {
                    json = cliente.RegistrarFiltro(new Gson().toJson(filtro));
                } else {
                    json = cliente.ActualizarFiltro(new Gson().toJson(filtro));
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
                    new swObtenerFiltros().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
            }
        }
    }

    public class swEliminarFiltro extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                int idFiltro = ObtenerValorCelda(tbFiltros, 1);
                json = cliente.EliminarFiltro(new Gson().toJson(idFiltro));
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
                    new swObtenerFiltros().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void EliminarFiltro() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            int idFiltro = ObtenerValorCelda(tbFiltros, 1);
            if (idFiltro == 0) {
                EliminarFila(tbFiltros);
            } else {
                new swEliminarFiltro().execute();
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
        tbFiltros = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbFiltros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "IDENTIDAD", "ENTIDAD", "IDUSUARIO", "USUARIO", "FILTRO", "ACTIVO", "GUARDAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbFiltros.setRowHeight(25);
        tbFiltros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbFiltros);
        if (tbFiltros.getColumnModel().getColumnCount() > 0) {
            tbFiltros.getColumnModel().getColumn(1).setMinWidth(0);
            tbFiltros.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbFiltros.getColumnModel().getColumn(1).setMaxWidth(0);
            tbFiltros.getColumnModel().getColumn(2).setMinWidth(0);
            tbFiltros.getColumnModel().getColumn(2).setPreferredWidth(0);
            tbFiltros.getColumnModel().getColumn(2).setMaxWidth(0);
            tbFiltros.getColumnModel().getColumn(4).setMinWidth(0);
            tbFiltros.getColumnModel().getColumn(4).setPreferredWidth(0);
            tbFiltros.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE FILTROS");

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
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
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
        AgregarFila(tbFiltros, new Object[]{false, 0, 0, "", 0, "", "", false, Icon_Save, Icon_Dele});
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbFiltros, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerFiltros().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbFiltros;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
