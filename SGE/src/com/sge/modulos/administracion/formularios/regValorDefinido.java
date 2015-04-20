package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
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
public class regValorDefinido extends frameBase<ValorDefinido> {

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
                schUsuario.asingValues(seleccionado.getIdUsuario(), seleccionado.getUsuario());
            }
        }
    };

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
                    if (idValorDefinido > 0) {
                        new swObtenerValorDefinido().execute();
                    } else {
                        valorDefinido = new ValorDefinido();
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

    public class swObtenerValorDefinido extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "[\"false\"]";
            try {
                json = cliente.ObtenerValorDefinido(new Gson().toJson(idValorDefinido));
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
                    valorDefinido = new Gson().fromJson(resultado[1], ValorDefinido.class);
                    schUsuario.asingValues(valorDefinido.getIdUsuario(), valorDefinido.getUsuario());
                    cboEntidad.setSelectedItem(getNombreEntidad(valorDefinido.getIdEntidad()));
                    chkActivo.setSelected(valorDefinido.isActivo());
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

    public class swGuardarValorDefinido extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                valorDefinido.setIdUsuario(schUsuario.getId());
                valorDefinido.setUsuario(schUsuario.getText());
                valorDefinido.setIdEntidad(getIdEntidad());
                valorDefinido.setActivo(chkActivo.isSelected());
                if (idValorDefinido == 0) {
                    json = cliente.RegistrarValorDefinido(new Gson().toJson(valorDefinido));
                } else {
                    valorDefinido.setIdValorDefinido(idValorDefinido);
                    json = cliente.ActualizarValorDefinido(new Gson().toJson(valorDefinido));
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
        lblUsuario = new javax.swing.JLabel();
        lblEntidad = new javax.swing.JLabel();
        cboEntidad = new javax.swing.JComboBox();
        chkActivo = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnEstablecerValores = new javax.swing.JButton();
        schUsuario = new com.sge.base.controles.JSearch();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblUsuario.setText("USUARIO");

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

        schUsuario.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schUsuarioSearch();
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
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario)
                            .addComponent(lblEntidad))
                        .addGap(54, 54, 54)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEstablecerValores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboEntidad, 0, 263, Short.MAX_VALUE)
                            .addComponent(schUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUsuario)
                        .addComponent(chkActivo))
                    .addComponent(schUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEntidad)
                    .addComponent(cboEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEstablecerValores)
                .addGap(25, 25, 25)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(22, Short.MAX_VALUE))
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
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEstablecerValoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstablecerValoresActionPerformed
        // TODO add your handling code here:
        try {
            for (Object[] entidad : entidades) {
                if (entidad[1].equals(cboEntidad.getSelectedItem())) {
                    Class<?> clazz = Class.forName(entidad[2].toString());
                    Constructor<?> constructor = clazz.getConstructor(ValorDefinido.class);
                    JInternalFrame frame = (JInternalFrame) constructor.newInstance(new Object[]{this.valorDefinido});
                    VerModal(frame);
                }
            }
        } catch (Exception e) {
            ControlarExcepcion(e);
        }
    }//GEN-LAST:event_btnEstablecerValoresActionPerformed

    private void schUsuarioSearch(){
        VerModal(new lisUsuario(1), select_usuario);
    }
    

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
    private com.sge.base.controles.JSearch schUsuario;
    // End of variables declaration//GEN-END:variables
}
