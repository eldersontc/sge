package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.FabricaControles;
import com.sge.base.utils.Utils;
import com.sge.modulos.inventarios.clases.Almacen;
import com.sge.modulos.inventarios.cliente.cliInventarios;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
public class lisAlmacen extends javax.swing.JInternalFrame {

    /**
     * Creates new form lisAlmacen
     */
    public lisAlmacen(int modo) {
        initComponents();
        Init(modo);
    }

    private int modo = 0;

    private Almacen seleccionado;

    private List<Almacen> seleccionados = new ArrayList<>();

    ImageIcon Icon_Save = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action save = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swGuardarAlmacen().execute();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarAlmacen();
        }
    };

    public class swObtenerAlmacenes extends SwingWorker {

        @Override
        protected Object doInBackground() {
            FabricaControles.VerCargando(pnlContenido);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerAlmacenes("");
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
                    DefaultTableModel modelo = (DefaultTableModel) tbAlmacenes.getModel();
                    modelo.setRowCount(0);

                    List<Object[]> filas = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                    }.getType());

                    for (Object[] fila : filas) {
                        modelo.addRow(new Object[]{false, ((Double) fila[0]).intValue(), fila[1], fila[2], fila[3], Icon_Save, Icon_Dele});
                    }

                    FabricaControles.AgregarBoton(tbAlmacenes, save, 5);
                    FabricaControles.AgregarBoton(tbAlmacenes, dele, 6);
                    Utils.AgregarOrdenamiento(tbAlmacenes);
                }
                FabricaControles.OcultarCargando(pnlContenido);
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
            }
        }
    }

    public class swGuardarAlmacen extends SwingWorker {

        @Override
        protected Object doInBackground() {
            FabricaControles.VerCargando(pnlContenido);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                Almacen almacen = new Almacen();
                almacen.setIdAlmacen(Utils.ObtenerValorCelda(tbAlmacenes, 1));
                almacen.setCodigo(Utils.ObtenerValorCelda(tbAlmacenes, 2));
                almacen.setDescripcion(Utils.ObtenerValorCelda(tbAlmacenes, 3));
                almacen.setActivo(Utils.ObtenerValorCelda(tbAlmacenes, 4));
                if (almacen.getIdAlmacen() == 0) {
                    json = cliente.RegistrarAlmacen(new Gson().toJson(almacen));
                } else {
                    json = cliente.ActualizarAlmacen(new Gson().toJson(almacen));
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
                    new swObtenerAlmacenes().execute();
                } else {
                    FabricaControles.OcultarCargando(pnlContenido);
                }
            } catch (Exception e) {
                FabricaControles.OcultarCargando(pnlContenido);
            }
        }
    }

    public class swEliminarAlmacen extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            FabricaControles.VerCargando(pnlContenido);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                int idAlmacen = Utils.ObtenerValorCelda(tbAlmacenes, 1);
                json = cliente.EliminarAlmacen(new Gson().toJson(idAlmacen));
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
                    new swObtenerAlmacenes().execute();
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
                Utils.OcultarColumna(tbAlmacenes, 0);
                Utils.OcultarControl(btnSeleccionar);
                break;
            case 1:
                Utils.OcultarColumna(tbAlmacenes, 0);
                break;
        }
        new swObtenerAlmacenes().execute();
    }

    public void EliminarAlmacen() {
        int confirmacion = FabricaControles.VerConfirmacion(this);
        if (confirmacion == 0) {
            int idAlmacen = Utils.ObtenerValorCelda(tbAlmacenes, 1);
            if (idAlmacen == 0) {
                Utils.EliminarFila(tbAlmacenes);
            } else {
                new swEliminarAlmacen().execute();
            }
        }
    }

    public Almacen getSeleccionado() {
        return seleccionado;
    }

    public List<Almacen> getSeleccionados() {
        return seleccionados;
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
        tbAlmacenes = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();

        setClosable(true);

        pnlContenido.setBackground(java.awt.Color.white);
        pnlContenido.setBorder(null);

        tbAlmacenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "IDALMACEN", "CODIGO", "DESCRIPCION", "ACTIVO", "GUARDAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAlmacenes.setRowHeight(25);
        tbAlmacenes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbAlmacenes);
        if (tbAlmacenes.getColumnModel().getColumnCount() > 0) {
            tbAlmacenes.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbAlmacenes.getColumnModel().getColumn(1).setMinWidth(0);
            tbAlmacenes.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbAlmacenes.getColumnModel().getColumn(1).setMaxWidth(0);
            tbAlmacenes.getColumnModel().getColumn(5).setPreferredWidth(30);
            tbAlmacenes.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE ALMACENES");

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
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        btnSeleccionar.setText("SELECCIONAR");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        lblFiltro.setText("FILTRO");

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFiltro)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnSeleccionar)
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
        Utils.AgregarFila(tbAlmacenes, new Object[]{false, 0, "", "", false, Icon_Save, Icon_Dele});
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (this.modo) {
            case 1:
                if (Utils.FilaActiva(tbAlmacenes)) {
                    Almacen almacen = new Almacen();
                    almacen.setIdAlmacen(Utils.ObtenerValorCelda(tbAlmacenes, 1));
                    almacen.setDescripcion(Utils.ObtenerValorCelda(tbAlmacenes, 3));
                    seleccionado = almacen;
                }
                Utils.Cerrar(this);
                break;
            case 2:
                for (int i = 0; i < tbAlmacenes.getRowCount(); i++) {
                    boolean check = Utils.ObtenerValorCelda(tbAlmacenes, i, 0);
                    if (check) {
                        Almacen almacen = new Almacen();
                        almacen.setIdAlmacen(Utils.ObtenerValorCelda(tbAlmacenes, i, 1));
                        almacen.setCodigo(Utils.ObtenerValorCelda(tbAlmacenes, i, 2));
                        almacen.setDescripcion(Utils.ObtenerValorCelda(tbAlmacenes, i, 3));
                        almacen.setActivo(Utils.ObtenerValorCelda(tbAlmacenes, i, 4));
                        seleccionados.add(almacen);
                    }
                }
                Utils.Cerrar(this);
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Utils.Filtrar(tbAlmacenes, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbAlmacenes;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
