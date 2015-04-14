package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.FabricaControles;
import com.sge.base.utils.Utils;
import com.sge.modulos.administracion.clases.Usuario;
import com.sge.modulos.administracion.clases.ValorDefinido;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regValorDefinido extends javax.swing.JInternalFrame {

    /**
     * Creates new form regValoresDefinidos
     */
    public regValorDefinido(String operacion, int idValoresDefinidos) {
        initComponents();
        Init(operacion, idValoresDefinidos);
    }

    private int idValorDefinido = 0;

    private ValorDefinido valorDefinido;

    private List<Object[]> entidades;

    public void Init(String operacion, int idValorDefinido) {
        lblTitulo.setText(operacion + lblTitulo.getText());
        this.idValorDefinido = idValorDefinido;
        new swObtenerEntidades().execute();
    }

    Action select_usuario = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Usuario seleccionado = ((lisUsuario) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                valorDefinido.setIdUsuario(seleccionado.getIdUsuario());
                valorDefinido.setUsuario(seleccionado.getUsuario());
                txtUsuario.setText(seleccionado.getUsuario());
            }
        }
    };

    public class swObtenerEntidades extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            FabricaControles.VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerEntidades(new Gson().toJson(""));
            } catch (Exception e) {
                json = "[\"false\"]";
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
                    if (idValorDefinido > 0) {
                        new swObtenerValorDefinido().execute();
                    } else {
                        valorDefinido = new ValorDefinido();
                    }
                } else {
                    FabricaControles.OcultarCargando(frame);
                }
            } catch (Exception e) {
                FabricaControles.OcultarCargando(frame);
            }
        }
    }

    public class swObtenerValorDefinido extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            FabricaControles.VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "[\"false\"]";
            try {
                json = cliente.ObtenerValorDefinido(new Gson().toJson(idValorDefinido));
            } catch (Exception e) {
                json = "[\"false\"]";
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
                    valorDefinido = new Gson().fromJson(resultado[1], ValorDefinido.class);
                    txtUsuario.setText(valorDefinido.getUsuario());
                    cboEntidad.setSelectedItem(getNombreEntidad(valorDefinido.getIdEntidad()));
                    chkActivo.setSelected(valorDefinido.isActivo());
                }
                FabricaControles.OcultarCargando(frame);
            } catch (Exception e) {
                FabricaControles.OcultarCargando(frame);
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

    public class swGuardarValorDefinido extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            FabricaControles.VerProcesando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                valorDefinido.setIdEntidad(getIdEntidad());
                valorDefinido.setActivo(chkActivo.isSelected());
                if (idValorDefinido == 0) {
                    json = cliente.RegistrarValorDefinido(new Gson().toJson(valorDefinido));
                } else {
                    valorDefinido.setIdValorDefinido(idValorDefinido);
                    json = cliente.ActualizarValorDefinido(new Gson().toJson(valorDefinido));
                }
            } catch (Exception e) {
                FabricaControles.OcultarProcesando(frame);
                json = "[\"false\"]";
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
                    FabricaControles.OcultarProcesando(frame);
                }
            } catch (Exception e) {
                FabricaControles.OcultarProcesando(frame);
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
        lblUsuario = new javax.swing.JLabel();
        lblEntidad = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        cboEntidad = new javax.swing.JComboBox();
        chkActivo = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnEstablecerValores = new javax.swing.JButton();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblUsuario.setText("USUARIO");

        lblEntidad.setText("ENTIDAD");

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

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
        lblTitulo.setText("VALOR DEFINIDO");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnEstablecerValores.setText("ESTABLECER VALORES");
        btnEstablecerValores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstablecerValoresActionPerformed(evt);
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
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario)
                            .addComponent(lblEntidad))
                        .addGap(54, 54, 54)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEstablecerValores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUsuario)
                            .addComponent(cboEntidad, 0, 263, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActivo))
                .addGap(2, 2, 2)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEntidad)
                    .addComponent(cboEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEstablecerValores)
                .addGap(25, 25, 25)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
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
        new swGuardarValorDefinido().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Utils.Cerrar(this);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEstablecerValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstablecerValoresActionPerformed
        // TODO add your handling code here:
        try {
            for (Object[] entidad : entidades) {
                if (entidad[1].equals(cboEntidad.getSelectedItem())) {
                    Class<?> clazz = Class.forName(entidad[2].toString());
                    Constructor<?> constructor = clazz.getConstructor(ValorDefinido.class);
                    JInternalFrame frame = (JInternalFrame) constructor.newInstance(new Object[]{this.valorDefinido});
                    FabricaControles.VerModal(this.getDesktopPane(), frame);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnEstablecerValoresActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
        FabricaControles.VerModal(this.getDesktopPane(), new lisUsuario(1), select_usuario);
    }//GEN-LAST:event_txtUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEstablecerValores;
    private javax.swing.JComboBox cboEntidad;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JPanel frame;
    private javax.swing.JLabel lblEntidad;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
