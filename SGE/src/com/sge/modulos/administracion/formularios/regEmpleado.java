/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.sge.base.utils.Utils;
import com.sge.modulos.administracion.clases.Empleado;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.util.List;

/**
 *
 * @author elderson
 */
public class regEmpleado extends javax.swing.JInternalFrame {

    /**
     * Creates new form regEmpleado
     */
    public regEmpleado(String operacion, int idEmpleado) {
        initComponents();
        Init(operacion, idEmpleado);
    }

    public void Init(String operacion, int idEmpleado) {
        lblTitulo.setText(operacion + lblTitulo.getText());
        if(idEmpleado > 0)
            ObtenerEmpleado(idEmpleado);
    }

    public void ObtenerEmpleado(int idEmpleado){
        cliAdministracion cliente = new cliAdministracion();
        try {
            String json = cliente.ObtenerEmpleado(new Gson().toJson(idEmpleado));
            cliente.close();
            String[] resultado = new Gson().fromJson(json, String[].class);
            Empleado empleado = new Gson().fromJson(resultado[1], Empleado.class);
            txtCodigo.setText(empleado.getCodigo());
            txtNombre.setText(empleado.getNombre());
            txtApellidoPaterno.setText(empleado.getApellidoPaterno());
            txtApellidoMaterno.setText(empleado.getApellidoMaterno());
            cboTipoDocumento.setSelectedItem(empleado.getTipoDocumentoIdentidad());
            txtDocumento.setText(empleado.getDocumentoIdentidad());
            chkActivo.setSelected(empleado.isActivo());
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

        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlContenido = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblApellidoPaterno = new javax.swing.JLabel();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblTipoDocumento = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        cboTipoDocumento = new javax.swing.JComboBox();
        chkActivo = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();

        setClosable(true);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("EMPLEADO");

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

        pnlContenido.setBackground(java.awt.Color.white);
        pnlContenido.setBorder(null);

        lblCodigo.setText("CODIGO");

        lblNombre.setText("NOMBRE");

        lblApellidoPaterno.setText("APELLIDO PATERNO");

        lblApellidoMaterno.setText("APELLIDO MATERNO");

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

        btnCancelar1.setText("CANCELAR");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodigo)
                            .addComponent(lblNombre)
                            .addComponent(lblApellidoPaterno)
                            .addComponent(lblApellidoMaterno)
                            .addComponent(lblTipoDocumento)
                            .addComponent(lblDocumento))
                        .addGap(28, 28, 28)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlContenidoLayout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtApellidoPaterno)
                            .addComponent(txtNombre)
                            .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCodigo))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActivo))
                .addGap(3, 3, 3)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblNombre))
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblApellidoPaterno))
                    .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblApellidoMaterno))
                    .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTipoDocumento))
                    .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDocumento))
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar1)
                    .addComponent(btnAceptar))
                .addContainerGap(24, Short.MAX_VALUE))
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

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JComboBox cboTipoDocumento;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTipoDocumento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
