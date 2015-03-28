package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.FabricaControles;
import com.sge.base.utils.Utils;
import com.sge.modulos.administracion.clases.Moneda;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elderson
 */
public class lisMoneda extends javax.swing.JInternalFrame {

    /**
     * Creates new form lisMoneda
     */
    public lisMoneda() {
        initComponents();
        Init();
    }

    ImageIcon Icon_Save = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action save = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swGuardarMoneda().execute();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarMoneda();
        }
    };

    public class swObtenerMonedas extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            FabricaControles.VerCargando(pnlContenido);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerMonedas("");
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
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
                    DefaultTableModel modelo = (DefaultTableModel) tbMonedas.getModel();
                    modelo.setRowCount(0);

                    List<Object[]> filas = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                    }.getType());

                    for (Object[] fila : filas) {
                        modelo.addRow(new Object[]{((Double) fila[0]).intValue(), fila[1], fila[2], fila[3], Icon_Save, Icon_Dele});
                    }

                    FabricaControles.AgregarBoton(tbMonedas, save, 4);
                    FabricaControles.AgregarBoton(tbMonedas, dele, 5);
                }
                FabricaControles.OcultarCargando(pnlContenido);
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
            }
        }
    };

    public class swGuardarMoneda extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            FabricaControles.VerCargando(pnlContenido);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                Moneda moneda = new Moneda();
                moneda.setIdMoneda(Utils.ObtenerValorCelda(tbMonedas, 0));
                moneda.setSimbolo(Utils.ObtenerValorCelda(tbMonedas, 1));
                moneda.setNombre(Utils.ObtenerValorCelda(tbMonedas, 2));
                moneda.setActivo(Utils.ObtenerValorCelda(tbMonedas, 3));
                if (moneda.getIdMoneda() == 0) {
                    json = cliente.RegistrarMoneda(new Gson().toJson(moneda));
                } else {
                    json = cliente.ActualizarMoneda(new Gson().toJson(moneda));
                }
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
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
                    new swObtenerMonedas().execute();
                } else {
                    FabricaControles.OcultarCargando(pnlContenido);
                }
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
            }
        }
    }

    public class swEliminarUnidad extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            FabricaControles.VerCargando(pnlContenido);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                int idMoneda = Utils.ObtenerValorCelda(tbMonedas, 0);
                json = cliente.EliminarMoneda(new Gson().toJson(idMoneda));
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
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
                    new swObtenerMonedas().execute();
                } else {
                    FabricaControles.OcultarCargando(pnlContenido);
                }
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
            }
        }
    }

    public void Init() {
        new swObtenerMonedas().execute();
    }

    public void EliminarMoneda() {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿SEGURO DE CONTINUAR?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            int idMoneda = Utils.ObtenerValorCelda(tbMonedas, 0);
            if (idMoneda == 0) {
                Utils.EliminarFila(tbMonedas);
            } else {
                new swEliminarUnidad().execute();
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

        pnlContenido = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMonedas = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();

        setClosable(true);

        pnlContenido.setBackground(java.awt.Color.white);
        pnlContenido.setBorder(null);

        tbMonedas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDMONEDA", "SIMBOLO", "NOMBRE", "ACTIVO", "GUARDAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbMonedas.setRowHeight(25);
        jScrollPane1.setViewportView(tbMonedas);
        if (tbMonedas.getColumnModel().getColumnCount() > 0) {
            tbMonedas.getColumnModel().getColumn(0).setMinWidth(0);
            tbMonedas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbMonedas.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE MONEDAS");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        Utils.AgregarFila(tbMonedas, new Object[]{0, "", "", false, Icon_Save, Icon_Dele});
    }//GEN-LAST:event_btnNuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbMonedas;
    // End of variables declaration//GEN-END:variables
}
