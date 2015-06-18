package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.finanzas.formularios.regSalidaCaja;
import com.sge.modulos.inventarios.clases.SalidaInventario;
import com.sge.modulos.inventarios.cliente.cliInventarios;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisSalidaInventario extends frameBase<SalidaInventario> {

    /**
     * Creates new form lisSalidaInventariox
     */
    public lisSalidaInventario(int modo) {
        initComponents();
        Init(modo, "");
    }

    public lisSalidaInventario(int modo, String filtro) {
        initComponents();
        Init(modo, filtro);
    }

    private int modo;

    private String filtro;

    private SalidaInventario seleccionado;

    private List<SalidaInventario> seleccionados = new ArrayList<>();

    ImageIcon Icon_View = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/view-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action view = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            VerSalidaInventario();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarSalidaInventario();
        }
    };

    Action refr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swObtenerSalidaInventarios().execute();
        }
    };

    public class swObtenerSalidaInventarios extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerSalidaInventarios(new Gson().toJson(filtro));
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
                    EliminarTodasFilas(tbSalidaInventarios);
                    SalidaInventario[] salidas = new Gson().fromJson(resultado[1], SalidaInventario[].class);
                    for (SalidaInventario salida : salidas) {
                        AgregarFila(tbSalidaInventarios, new Object[]{false, salida.getIdSalidaInventario(), salida.getNumero(), salida.getFechaCreacionString(), salida.getRazonSocialCliente(), salida.getNombreResponsable(), salida.getSimboloMoneda(), salida.getTotal(), Icon_View, Icon_Dele});
                    }
                    AgregarBoton(tbSalidaInventarios, view, 8);
                    AgregarBoton(tbSalidaInventarios, dele, 9);
                    AgregarOrdenamiento(tbSalidaInventarios);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarSalidaInventario extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                int idSalidaInventario = ObtenerValorCelda(tbSalidaInventarios, 1);
                json = cliente.EliminarSalidaInventario(new Gson().toJson(idSalidaInventario));
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
                    new swObtenerSalidaInventarios().execute();
                } else {
                    OcultarCargando(frame);
                    ControlarExcepcion(resultado);
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
                OcultarColumna(tbSalidaInventarios, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbSalidaInventarios, new int[]{0, 8, 9});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerSalidaInventarios().execute();
    }

    public void VerSalidaInventario() {
        int idSalidaInventario = ObtenerValorCelda(tbSalidaInventarios, 1);
        VerFrame(new regSalidaInventario(idSalidaInventario));
    }

    public void EliminarSalidaInventario() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarSalidaInventario().execute();
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
        tbSalidaInventarios = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        txtFiltro = new javax.swing.JTextField();
        lblFiltro = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbSalidaInventarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "NUMERO", "F. CREACION", "CLIENTE", "RESPONSABLE", "MONEDA", "TOTAL", "VER", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSalidaInventarios.setRowHeight(25);
        tbSalidaInventarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbSalidaInventarios);
        if (tbSalidaInventarios.getColumnModel().getColumnCount() > 0) {
            tbSalidaInventarios.getColumnModel().getColumn(1).setMinWidth(0);
            tbSalidaInventarios.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbSalidaInventarios.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE SALIDAS DE INVENTARIO");

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
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
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

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });

        lblFiltro.setText("FILTRO");

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/refresh-16.png"))); // NOI18N
        btnRefrescar.setToolTipText("REFRESCAR");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/imprimir.png"))); // NOI18N
        btnImprimir.setToolTipText("REFRESCAR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFiltro)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(6, 6, 6))
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
        regSalidaInventario regSalidaInventario = new regSalidaInventario(0);
        regSalidaInventario.setUsuario(getUsuario());
        VerFrame(regSalidaInventario, refr);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbSalidaInventarios, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerSalidaInventarios().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbSalidaInventarios)) {
            ImprimirConEntidad(2, ObtenerValorCelda(tbSalidaInventarios, 1), frame);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbSalidaInventarios;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
