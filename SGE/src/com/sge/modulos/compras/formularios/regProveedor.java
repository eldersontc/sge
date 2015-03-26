package com.sge.modulos.compras.formularios;

import com.google.gson.Gson;
import com.sge.modulos.compras.clases.Proveedor;
import com.sge.modulos.compras.cliente.cliCompras;

/**
 *
 * @author elderson
 */
public class regProveedor extends javax.swing.JInternalFrame {

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
            ObtenerProveedor(idProveedor);
        }
    }

    public void ObtenerProveedor(int idProveedor) {
        cliCompras cliente = new cliCompras();
        try {
            String json = cliente.ObtenerProveedor(new Gson().toJson(idProveedor));
            cliente.close();
            String[] resultado = new Gson().fromJson(json, String[].class);
            Proveedor proveedor = new Gson().fromJson(resultado[1], Proveedor.class);
            txtRazonSocial.setText(proveedor.getRazonSocial());
            txtNombreComercial.setText(proveedor.getNombreComercial());
            cboTipoDocumento.setSelectedItem(proveedor.getTipoDocumentoIdentidad());
            txtDocumento.setText(proveedor.getDocumentoIdentidad());
            txtFechaUltimaCompra.setText(proveedor.getFechaUltimaCompra());
            chkActivo.setSelected(proveedor.isActivo());
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            cliente.close();
        }
    }

    public void GuardarProveedor() {
        cliCompras cliente = new cliCompras();
        try {
            if (this.idProveedor == 0) {
                Proveedor proveedor = new Proveedor();
                proveedor.setRazonSocial(txtRazonSocial.getText());
                proveedor.setNombreComercial(txtNombreComercial.getText());
                proveedor.setTipoDocumentoIdentidad(cboTipoDocumento.getSelectedItem().toString());
                proveedor.setDocumentoIdentidad(txtDocumento.getText());
                proveedor.setActivo(chkActivo.isSelected());
                cliente.RegistrarProveedor(proveedor);
            } else {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(this.idProveedor);
                proveedor.setRazonSocial(txtRazonSocial.getText());
                proveedor.setNombreComercial(txtNombreComercial.getText());
                proveedor.setTipoDocumentoIdentidad(cboTipoDocumento.getSelectedItem().toString());
                proveedor.setDocumentoIdentidad(txtDocumento.getText());
                proveedor.setActivo(chkActivo.isSelected());
                cliente.ActualizarProveedor(proveedor);
            }
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            cliente.close();
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

        pnlContenido = new javax.swing.JPanel();
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

        pnlContenido.setBackground(java.awt.Color.white);
        pnlContenido.setBorder(null);

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

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombreComercial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTipoDocumento)
                    .addComponent(lblDocumento)
                    .addComponent(lblFechaUltimaCompra))
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlContenidoLayout.createSequentialGroup()
                                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                                        .addComponent(txtFechaUltimaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(37, Short.MAX_VALUE))))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRazonSocial))
                .addGap(1, 1, 1)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreComercial)
                            .addComponent(txtNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTipoDocumento))
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDocumento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaUltimaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaUltimaCompra)
                    .addComponent(chkActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addGap(0, 18, Short.MAX_VALUE))
        );

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        GuardarProveedor();
        this.setVisible(false);
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cboTipoDocumento;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFechaUltimaCompra;
    private javax.swing.JLabel lblNombreComercial;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblTipoDocumento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtFechaUltimaCompra;
    private javax.swing.JTextField txtNombreComercial;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables
}
