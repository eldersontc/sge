package com.sge.modulos.compras.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.compras.clases.Proveedor;
import com.sge.modulos.compras.cliente.cliCompras;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regProveedor extends frameBase<Proveedor> {

    /**
     * Creates new form regProveedor
     */
    public regProveedor(String operacion, int idProveedor) {
        initComponents();
        Init(operacion, idProveedor);
    }

    int idProveedor = 0;

    public void Init(String operacion, int idProveedor) {
        lblTitulo.setText(operacion + lblTitulo.getText());
        this.idProveedor = idProveedor;
        if (this.idProveedor > 0) {
            new swObtenerProveedor().execute();
        }
    }

    public class swObtenerProveedor extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliCompras cliente = new cliCompras();
            String json = "";
            try {
                json = cliente.ObtenerProveedor(new Gson().toJson(idProveedor));
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
                    Proveedor proveedor = new Gson().fromJson(resultado[1], Proveedor.class);
                    txtRazonSocial.setText(proveedor.getRazonSocial());
                    txtNombreComercial.setText(proveedor.getNombreComercial());
                    cboTipoDocumento.setSelectedItem(proveedor.getTipoDocumentoIdentidad());
                    txtDocumento.setText(proveedor.getDocumentoIdentidad());
                    txtFechaUltimaCompra.setText(proveedor.getFechaUltimaCompra());
                    chkActivo.setSelected(proveedor.isActivo());
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGuardarProveedor extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliCompras cliente = new cliCompras();
            String json = "";
            try {
                Proveedor proveedor = new Proveedor();
                proveedor.setRazonSocial(txtRazonSocial.getText());
                proveedor.setNombreComercial(txtNombreComercial.getText());
                proveedor.setTipoDocumentoIdentidad(cboTipoDocumento.getSelectedItem().toString());
                proveedor.setDocumentoIdentidad(txtDocumento.getText());
                proveedor.setActivo(chkActivo.isSelected());
                if (idProveedor == 0) {
                    json = cliente.RegistrarProveedor(new Gson().toJson(proveedor));
                } else {
                    proveedor.setIdProveedor(idProveedor);
                    json = cliente.ActualizarProveedor(new Gson().toJson(proveedor));
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
        lblRazonSocial = new javax.swing.JLabel();
        lblFechaUltimaCompra = new javax.swing.JLabel();
        lblNombreComercial = new javax.swing.JLabel();
        lblTipoDocumento = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        txtFechaUltimaCompra = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        txtNombreComercial = new javax.swing.JTextField();
        cboTipoDocumento = new javax.swing.JComboBox();
        chkActivo = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblRazonSocial.setText("RAZON SOCIAL");

        lblFechaUltimaCompra.setText("F. ULTIMA COMPRA");

        lblNombreComercial.setText("NOMBRE COMERCIAL");

        lblTipoDocumento.setText("TIPO DOCUMENTO");

        lblDocumento.setText("Nº DOCUMENTO");

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI", "RUC", "PASAPORTE", "OTRO" }));

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
        lblTitulo.setText("PROVEEDOR");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(308, Short.MAX_VALUE))
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
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombreComercial)
                    .addComponent(lblTipoDocumento)
                    .addComponent(lblDocumento)
                    .addComponent(lblFechaUltimaCompra))
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(txtFechaUltimaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRazonSocial))
                .addGap(1, 1, 1)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreComercial)
                            .addComponent(txtNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTipoDocumento))
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDocumento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaUltimaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaUltimaCompra)
                    .addComponent(chkActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(17, Short.MAX_VALUE))
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarProveedor().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cboTipoDocumento;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JPanel frame;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFechaUltimaCompra;
    private javax.swing.JLabel lblNombreComercial;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblTipoDocumento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtFechaUltimaCompra;
    private javax.swing.JTextField txtNombreComercial;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables
}
