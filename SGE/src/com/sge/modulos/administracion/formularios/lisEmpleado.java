package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.FabricaControles;
import com.sge.base.utils.Utils;
import com.sge.modulos.administracion.clases.Empleado;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elderson
 */
public class lisEmpleado extends javax.swing.JInternalFrame {

    /**
     * Creates new form lisEmpleado
     */
    public lisEmpleado(int modo) {
        initComponents();
        Init(modo);
    }

    private int modo = 0;

    private Empleado seleccionado;
    
    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarEmpleado();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarEmpleado();
        }
    };

    public class swObtenerEmpleados extends SwingWorker {

        @Override
        protected Object doInBackground() {
            FabricaControles.VerCargando(pnlContenido);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerEmpleados("");
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
                    DefaultTableModel modelo = (DefaultTableModel) tbEmpleados.getModel();
                    modelo.setRowCount(0);

                    List<Object[]> filas = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                    }.getType());

                    for (Object[] fila : filas) {
                        modelo.addRow(new Object[]{false,((Double) fila[0]).intValue(), fila[1], fila[2], fila[6], fila[7], Icon_Edit, Icon_Dele});
                    }
                    FabricaControles.AgregarBoton(tbEmpleados, edit, 6);
                    FabricaControles.AgregarBoton(tbEmpleados, dele, 7);
                    Utils.AgregarOrdenamiento(tbEmpleados);
                }
                FabricaControles.OcultarCargando(pnlContenido);
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
            }
        }
    }

    public class swEliminarUnidad extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            FabricaControles.VerCargando(pnlContenido);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                int idEmpleado = Utils.ObtenerValorCelda(tbEmpleados, 1);
                json = cliente.EliminarEmpleado(new Gson().toJson(idEmpleado));
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
                    new swObtenerEmpleados().execute();
                } else {
                    FabricaControles.OcultarCargando(pnlContenido);
                }
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
            }
        }
    }

    public void Init(int modo) {
        this.modo = modo;
        switch (this.modo) {
            case 0:
                Utils.OcultarColumna(tbEmpleados, 0);
                Utils.OcultarControl(btnSeleccionar);
                break;
            case 1:
                Utils.OcultarColumna(tbEmpleados, 0);
                break;
        }
        new swObtenerEmpleados().execute();
    }

    public void EditarEmpleado() {
        int idEmpleado = Utils.ObtenerValorCelda(tbEmpleados, 1);
        regEmpleado regEmpleado = new regEmpleado("EDITAR ", idEmpleado);
        this.getParent().add(regEmpleado);
        regEmpleado.setVisible(true);
    }

    public void EliminarEmpleado() {
        int confirmacion = FabricaControles.VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarUnidad().execute();
        }
    }

    public Empleado getSeleccionado() {
        return seleccionado;
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
        tbEmpleados = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();

        setClosable(true);

        pnlContenido.setBackground(java.awt.Color.white);
        pnlContenido.setBorder(null);

        tbEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "IDEMPLEADO", "CODIGO", "NOMBRE", "DNI", "ACTIVO", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEmpleados.setRowHeight(25);
        tbEmpleados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbEmpleados);
        if (tbEmpleados.getColumnModel().getColumnCount() > 0) {
            tbEmpleados.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbEmpleados.getColumnModel().getColumn(1).setMinWidth(0);
            tbEmpleados.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbEmpleados.getColumnModel().getColumn(1).setMaxWidth(0);
            tbEmpleados.getColumnModel().getColumn(6).setPreferredWidth(40);
            tbEmpleados.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE EMPLEADOS");

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
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        lblTitulo.getAccessibleContext().setAccessibleDescription("");

        lblFiltro.setText("FILTRO");

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });

        btnSeleccionar.setText("SELECCIONAR");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltro)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        regEmpleado regEmpleado = new regEmpleado("NUEVO ", 0);
        this.getParent().add(regEmpleado);
        regEmpleado.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Utils.Filtrar(tbEmpleados, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (this.modo) {
            case 1:
            if (Utils.FilaActiva(tbEmpleados)) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(Utils.ObtenerValorCelda(tbEmpleados, 1));
                empleado.setNombre(Utils.ObtenerValorCelda(tbEmpleados, 3));
                seleccionado = empleado;
            }
            Utils.Cerrar(this);
            break;
            case 2:
            break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbEmpleados;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
