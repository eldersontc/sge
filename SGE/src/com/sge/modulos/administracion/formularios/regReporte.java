package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Entidad;
import com.sge.modulos.administracion.clases.ItemReporte;
import com.sge.modulos.administracion.clases.Reporte;
import com.sge.modulos.administracion.cliente.cliAdministracion;
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
public class regReporte extends frameBase<Reporte> {

    /**
     * Creates new form regReporte
     */
    public regReporte(String operacion, int idReporte) {
        initComponents();
        Init(operacion, idReporte);
    }

    private int idReporte = 0;

    private List<ItemReporte> items = new ArrayList<>();

    public void Init(String operacion, int idReporte) {
        lblTitulo.setText(operacion + lblTitulo.getText());
        this.idReporte = idReporte;
        if (this.idReporte > 0) {
            new swObtenerReporte().execute();
        }
    }

    Action sele_enti = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Entidad seleccionado = ((lisEntidadx) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schEntidad.asingValues(seleccionado.getIdEntidad(), seleccionado.getNombre());
            }
        }
    };
    
    public List<ItemReporte> getItems() {
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            int idItemReporte = ObtenerValorCelda(tbItems, i, 0);
            if (idItemReporte == 0) {
                ItemReporte itemReporte = new ItemReporte();
                itemReporte.setNombre(ObtenerValorCelda(tbItems, i, 1));
                itemReporte.setAsignarId(ObtenerValorCelda(tbItems, i, 2));
                itemReporte.setValor(ObtenerValorCelda(tbItems, i, 3));
                itemReporte.setAgregar(true);
                this.items.add(itemReporte);
            } else {
                ItemReporte itemReporte = new ItemReporte();
                itemReporte.setIdItemReporte(idItemReporte);
                itemReporte.setNombre(ObtenerValorCelda(tbItems, i, 1));
                itemReporte.setAsignarId(ObtenerValorCelda(tbItems, i, 2));
                itemReporte.setValor(ObtenerValorCelda(tbItems, i, 3));
                itemReporte.setActualizar(true);
                this.items.add(itemReporte);
            }
        }
        return this.items;
    }

    public class swObtenerReporte extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerReporte(new Gson().toJson(idReporte));
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
                    Reporte reporte = new Gson().fromJson(resultado[1], Reporte.class);
                    txtNombre.setText(reporte.getNombre());
                    schEntidad.asingValues(reporte.getIdEntidad(), reporte.getNombreEntidad());
                    txtUbicacion.setText(reporte.getUbicacion());
                    chkActivo.setSelected(reporte.isActivo());
                    for (ItemReporte item : reporte.getItems()) {
                        AgregarFila(tbItems,
                                new Object[]{
                                    item.getIdItemReporte(),
                                    item.getNombre(),
                                    item.isAsignarId(),
                                    item.getValor()
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

    public class swGuardarReporte extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                Reporte reporte = new Reporte();
                reporte.setNombre(txtNombre.getText());
                reporte.setIdEntidad(schEntidad.getId());
                reporte.setNombreEntidad(schEntidad.getText());
                reporte.setUbicacion(txtUbicacion.getText());
                reporte.setActivo(chkActivo.isSelected());
                reporte.setItems(getItems());
                if (idReporte == 0) {
                    json = cliente.RegistrarReporte(new Gson().toJson(reporte));
                } else {
                    reporte.setIdReporte(idReporte);
                    json = cliente.ActualizarReporte(new Gson().toJson(reporte));
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
                    setVisible(false);
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
        lblNombre = new javax.swing.JLabel();
        lblEntidad = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        chkActivo = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlParametros = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItems = new javax.swing.JTable();
        btnEliminarItem = new javax.swing.JButton();
        btnNuevoItem = new javax.swing.JButton();
        schEntidad = new com.sge.base.controles.JSearch();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblNombre.setText("NOMBRE");

        lblEntidad.setText("ENTIDAD");

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

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("REPORTE");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblUbicacion.setText("UBICACION");

        pnlParametros.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDITEM", "NOMBRE", "ASIGNAR ID", "VALOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbItems);
        if (tbItems.getColumnModel().getColumnCount() > 0) {
            tbItems.getColumnModel().getColumn(0).setMinWidth(0);
            tbItems.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnEliminarItem.setText("ELIMINAR");
        btnEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemActionPerformed(evt);
            }
        });

        btnNuevoItem.setText("NUEVO");
        btnNuevoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlParametrosLayout = new javax.swing.GroupLayout(pnlParametros);
        pnlParametros.setLayout(pnlParametrosLayout);
        pnlParametrosLayout.setHorizontalGroup(
            pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlParametrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlParametrosLayout.setVerticalGroup(
            pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlParametrosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarItem)
                    .addComponent(btnNuevoItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("PARAMETROS", pnlParametros);

        schEntidad.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schEntidadSearch();
            }
            @Override
            public void Clear(){
            }
        });

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTabbedPane1)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNombre)
                                .addComponent(lblUbicacion, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblEntidad))
                            .addGap(39, 39, 39)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                    .addComponent(schEntidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                                .addComponent(txtUbicacion)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEntidad)
                        .addComponent(chkActivo))
                    .addComponent(schEntidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUbicacion)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void schEntidadSearch() {
        VerModal(new lisEntidadx(1), sele_enti);
    }
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarReporte().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemActionPerformed
        // TODO add your handling code here:
        AgregarFila(tbItems, new Object[]{0, "", false, ""});
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbItems)) {
            int idItemReporte = ObtenerValorCelda(tbItems, 0);
            if (idItemReporte > 0) {
                items.add(new ItemReporte(idItemReporte, true));
            }
            EliminarFila(tbItems);
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblEntidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JPanel pnlParametros;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schEntidad;
    private javax.swing.JTable tbItems;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
