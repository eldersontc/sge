package com.sge.modulos.produccion.formularios;

import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.produccion.clases.ItemOrdenTrabajo;
import com.sge.modulos.produccion.clases.OrdenTrabajo;
import javax.swing.ImageIcon;

/**
 *
 * @author elderson
 */
public class verGraficoImpresion extends frameBase<OrdenTrabajo> {

    /**
     * Creates new form verGraficoImpresionx
     */
    public verGraficoImpresion(ItemOrdenTrabajo item) {
        initComponents();
        Init(item);
    }

    private ItemOrdenTrabajo item;

    public void Init(ItemOrdenTrabajo item) {
        this.item = item;
        AsignarControles();
    }

    public void AsignarControles() {
        txtMedidasMaterial.setText(String.format("%s X %s CM.", this.item.getAltoFormatoImpresion(), this.item.getLargoFormatoImpresion()));
        txtAltoPieza.setText(String.valueOf(this.item.getAltoMedidaAbierta()));
        txtLargoPieza.setText(String.valueOf(this.item.getLargoMedidaAbierta()));
        txtSeparacionX.setText(String.valueOf(this.item.getSeparacionX()));
        txtSeparacionY.setText(String.valueOf(this.item.getSeparacionY()));
        txtCantidadPiezas.setText(String.valueOf(this.item.getCantidadPiezasImpresion()));
        schMetodoImpresion.asingValues(this.item.getIdMetodoImpresion(), this.item.getDescripcionMetodoImpresion());
        txtCantidadPases.setText(String.valueOf(this.item.getCantidadPases()));
        txtCantidadCambios.setText(String.valueOf(this.item.getCantidadCambios()));
        txtTiraje.setText(String.valueOf(this.item.getCantidadProduccion()));
        txtOtros.setText(String.format("%s PLIEGOS DE : %s HJS/MAQUINA \n", this.item.getCantidadPliegos(), (this.item.getCantidadMaterial() + this.item.getCantidadDemasiaMaterial()) * this.item.getCantidadPiezasPrecorte()));
        if (this.item.getGraficoImpresion() != null) {
            lblGrafico.setIcon(new ImageIcon(this.item.getGraficoImpresion()));
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
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblGrafico = new javax.swing.JLabel();
        lblAltoPieza = new javax.swing.JLabel();
        txtAltoPieza = new javax.swing.JTextField();
        lblLargoPieza = new javax.swing.JLabel();
        txtLargoPieza = new javax.swing.JTextField();
        txtMedidasMaterial = new javax.swing.JTextField();
        lblCantidadPiezas = new javax.swing.JLabel();
        txtCantidadPiezas = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        lblSeparacionX = new javax.swing.JLabel();
        txtSeparacionX = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOtros = new javax.swing.JTextArea();
        lblSeparacionY = new javax.swing.JLabel();
        txtSeparacionY = new javax.swing.JTextField();
        lblMetodoImpresion = new javax.swing.JLabel();
        schMetodoImpresion = new com.sge.base.controles.JSearch();
        txtCantidadPases = new javax.swing.JTextField();
        lblCantidadPases = new javax.swing.JLabel();
        lblTiraje = new javax.swing.JLabel();
        lblCantidadCambios = new javax.swing.JLabel();
        txtCantidadCambios = new javax.swing.JTextField();
        txtTiraje = new javax.swing.JTextField();

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/details-large-view-16.png"))); // NOI18N
        lblTitulo.setText("GRÁFICO DE IMPRESIÓN");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(665, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        lblGrafico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane1.setViewportView(lblGrafico);

        lblAltoPieza.setText("ALTO");

        txtAltoPieza.setText("0");

        lblLargoPieza.setText("LARGO");

        txtLargoPieza.setText("0");

        txtMedidasMaterial.setEditable(false);

        lblCantidadPiezas.setText("N° PIEZAS");

        txtCantidadPiezas.setEditable(false);
        txtCantidadPiezas.setText("0");

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblSeparacionX.setText("SEP. X");

        txtSeparacionX.setText("0");

        txtOtros.setColumns(20);
        txtOtros.setRows(5);
        jScrollPane2.setViewportView(txtOtros);

        lblSeparacionY.setText("SEP. Y");

        txtSeparacionY.setText("0");

        lblMetodoImpresion.setText("MÉTODO DE IMPRESIÓN");

        schMetodoImpresion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
            }
            @Override
            public void Clear(){
            }
        });

        txtCantidadPases.setEditable(false);
        txtCantidadPases.setText("0");

        lblCantidadPases.setText("N° PASES");

        lblTiraje.setText("TIRAJE");

        lblCantidadCambios.setText("N° CAMBIOS");

        txtCantidadCambios.setEditable(false);
        txtCantidadCambios.setText("0");

        txtTiraje.setEditable(false);
        txtTiraje.setText("0");

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMedidasMaterial)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCantidadPases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCantidadCambios, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(lblAltoPieza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLargoPieza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                            .addComponent(txtCantidadPases)
                            .addComponent(txtCantidadCambios)
                            .addComponent(txtAltoPieza, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(schMetodoImpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMetodoImpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLargoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblSeparacionY, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSeparacionY))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblSeparacionX, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSeparacionX, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblCantidadPiezas, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(lblTiraje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadPiezas)
                            .addComponent(txtTiraje))))
                .addGap(20, 20, 20))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(txtMedidasMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMetodoImpresion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(schMetodoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidadPases, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadPases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidadCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadCambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAltoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAltoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLargoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLargoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSeparacionX, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSeparacionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSeparacionY, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSeparacionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidadPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTiraje, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addGap(41, 41, 41)
                        .addComponent(btnAceptar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAltoPieza;
    private javax.swing.JLabel lblCantidadCambios;
    private javax.swing.JLabel lblCantidadPases;
    private javax.swing.JLabel lblCantidadPiezas;
    private javax.swing.JLabel lblGrafico;
    private javax.swing.JLabel lblLargoPieza;
    private javax.swing.JLabel lblMetodoImpresion;
    private javax.swing.JLabel lblSeparacionX;
    private javax.swing.JLabel lblSeparacionY;
    private javax.swing.JLabel lblTiraje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schMetodoImpresion;
    private javax.swing.JTextField txtAltoPieza;
    private javax.swing.JTextField txtCantidadCambios;
    private javax.swing.JTextField txtCantidadPases;
    private javax.swing.JTextField txtCantidadPiezas;
    private javax.swing.JTextField txtLargoPieza;
    private javax.swing.JTextField txtMedidasMaterial;
    private javax.swing.JTextArea txtOtros;
    private javax.swing.JTextField txtSeparacionX;
    private javax.swing.JTextField txtSeparacionY;
    private javax.swing.JTextField txtTiraje;
    // End of variables declaration//GEN-END:variables
}
