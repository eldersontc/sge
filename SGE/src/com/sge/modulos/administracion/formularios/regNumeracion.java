package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Numeracion;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regNumeracion extends frameBase {

    /**
     * Creates new form regNumeracion
     */
    public regNumeracion(String operacion, int idNumeracion) {
        initComponents();
        Init(operacion, idNumeracion);
    }

    private int idNumeracion = 0;

    private List<Object[]> entidades;

    public void Init(String operacion, int idNumeracion) {
        lblTitulo.setText(operacion + lblTitulo.getText());
        this.idNumeracion = idNumeracion;
        new swObtenerEntidades().execute();
    }

    public class swObtenerEntidades extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerEntidades(new Gson().toJson(""));
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
                    entidades = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                    }.getType());
                    for (Object[] entidad : entidades) {
                        cboEntidad.addItem(entidad[1]);
                    }
                    if (idNumeracion > 0) {
                        new swObtenerNumeracion().execute();
                    } else {
                        OcultarCargando(frame);
                    }
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swObtenerNumeracion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerNumeracion(new Gson().toJson(idNumeracion));
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
                    Numeracion numeracion = new Gson().fromJson(resultado[1], Numeracion.class);
                    txtDescripcion.setText(numeracion.getDescripcion());
                    cboEntidad.setSelectedItem(getNombreEntidad(numeracion.getIdEntidad()));
                    chkManual.setSelected(numeracion.isManual());
                    txtSerie.setEnabled(!numeracion.isManual());
                    txtNumeroActual.setEnabled(!numeracion.isManual());
                    txtLongitudNumero.setEnabled(!numeracion.isManual());
                    txtSerie.setText(numeracion.getSerie());
                    txtNumeroActual.setText(String.valueOf(numeracion.getNumeroActual()));
                    txtLongitudNumero.setText(String.valueOf(numeracion.getLongitudNumero()));
                    chkTieneImpuesto.setSelected(numeracion.isTieneImpuesto());
                    txtPorcentajeImpuesto.setEnabled(numeracion.isTieneImpuesto());
                    txtPorcentajeImpuesto.setText(String.valueOf(numeracion.getPorcentajeImpuesto()));
                    chkActivo.setSelected(numeracion.isActivo());
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public int getIdEntidad() {
        int idEntidad = 0;
        for (Object[] entidad : entidades) {
            if (entidad[1].equals(cboEntidad.getSelectedItem())) {
                idEntidad = ((Double) entidad[0]).intValue();
                break;
            }
        }
        return idEntidad;
    }

    public String getNombreEntidad(int idEntidad) {
        String nombre = "";
        for (Object[] entidad : entidades) {
            if (((Double) entidad[0]).intValue() == idEntidad) {
                nombre = entidad[1].toString();
                break;
            }
        }
        return nombre;
    }

    public class swGuardarNumeracion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                Numeracion numeracion = new Numeracion();
                numeracion.setDescripcion(txtDescripcion.getText());
                numeracion.setIdEntidad(getIdEntidad());
                numeracion.setManual(chkManual.isSelected());
                numeracion.setSerie(txtSerie.getText());
                numeracion.setNumeroActual(Integer.parseInt(txtNumeroActual.getText()));
                numeracion.setLongitudNumero(Integer.parseInt(txtLongitudNumero.getText()));
                numeracion.setTieneImpuesto(chkTieneImpuesto.isSelected());
                numeracion.setPorcentajeImpuesto(Double.parseDouble(txtPorcentajeImpuesto.getText()));
                numeracion.setActivo(chkActivo.isSelected());
                if (idNumeracion == 0) {
                    json = cliente.RegistrarNumeracion(new Gson().toJson(numeracion));
                } else {
                    numeracion.setIdNumeracion(idNumeracion);
                    json = cliente.ActualizarNumeracion(new Gson().toJson(numeracion));
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
        lblDescripcion = new javax.swing.JLabel();
        lblEntidad = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        cboEntidad = new javax.swing.JComboBox();
        chkManual = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSerie = new javax.swing.JLabel();
        chkActivo = new javax.swing.JCheckBox();
        txtSerie = new javax.swing.JTextField();
        lblNumeroActual = new javax.swing.JLabel();
        lblLongitudNumero = new javax.swing.JLabel();
        chkTieneImpuesto = new javax.swing.JCheckBox();
        lblPorcentajeImpuesto = new javax.swing.JLabel();
        txtLongitudNumero = new javax.swing.JTextField();
        txtPorcentajeImpuesto = new javax.swing.JTextField();
        txtNumeroActual = new javax.swing.JTextField();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblDescripcion.setText("DESCRIPCIÓN");

        lblEntidad.setText("ENTIDAD");

        chkManual.setText("MANUAL");
        chkManual.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkManualStateChanged(evt);
            }
        });

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
        lblTitulo.setText("NUMERACIÓN");

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

        lblSerie.setText("SERIE");

        chkActivo.setText("ACTIVO");

        lblNumeroActual.setText("N° ACTUAL");

        lblLongitudNumero.setText("LONGITUD");

        chkTieneImpuesto.setText("TIENE IMPUESTO");
        chkTieneImpuesto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkTieneImpuestoStateChanged(evt);
            }
        });

        lblPorcentajeImpuesto.setText("% IMPUESTO");

        txtLongitudNumero.setText("0");

        txtPorcentajeImpuesto.setText("0");

        txtNumeroActual.setText("0");

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcion)
                    .addComponent(lblEntidad)
                    .addComponent(lblSerie)
                    .addComponent(lblPorcentajeImpuesto))
                .addGap(18, 18, 18)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroActual)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumeroActual, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lblLongitudNumero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLongitudNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chkManual, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboEntidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(chkTieneImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPorcentajeImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActivo))
                .addGap(2, 2, 2)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEntidad)
                    .addComponent(cboEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkManual)
                .addGap(6, 6, 6)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSerie)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroActual)
                    .addComponent(lblLongitudNumero)
                    .addComponent(txtLongitudNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkTieneImpuesto)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPorcentajeImpuesto)
                            .addComponent(txtPorcentajeImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnAceptar))))
                .addContainerGap(23, Short.MAX_VALUE))
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

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarNumeracion().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkManualStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkManualStateChanged
        // TODO add your handling code here:
        if (chkManual.isSelected()) {
            txtSerie.setEnabled(false);
            txtNumeroActual.setEnabled(false);
            txtLongitudNumero.setEnabled(false);
        } else {
            txtSerie.setEnabled(true);
            txtNumeroActual.setEnabled(true);
            txtLongitudNumero.setEnabled(true);
        }
    }//GEN-LAST:event_chkManualStateChanged

    private void chkTieneImpuestoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkTieneImpuestoStateChanged
        // TODO add your handling code here:
        if (chkTieneImpuesto.isSelected()) {
            txtPorcentajeImpuesto.setEnabled(true);
        } else {
            txtPorcentajeImpuesto.setEnabled(false);
        }
    }//GEN-LAST:event_chkTieneImpuestoStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cboEntidad;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JCheckBox chkManual;
    private javax.swing.JCheckBox chkTieneImpuesto;
    private javax.swing.JPanel frame;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEntidad;
    private javax.swing.JLabel lblLongitudNumero;
    private javax.swing.JLabel lblNumeroActual;
    private javax.swing.JLabel lblPorcentajeImpuesto;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtLongitudNumero;
    private javax.swing.JTextField txtNumeroActual;
    private javax.swing.JTextField txtPorcentajeImpuesto;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
