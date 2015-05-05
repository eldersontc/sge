package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.Maquina;
import com.sge.modulos.ventas.cliente.cliVentas;
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
public class lisMaquina extends frameBase<Maquina> {

    /**
     * Creates new form lisMaquina
     */
    public lisMaquina(int modo) {
        initComponents();
        Init(modo);
    }

    private int modo = 0;

    private Maquina seleccionado;

    private List<Maquina> seleccionados = new ArrayList<>();

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarMaquina();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarMaquina();
        }
    };

    public class swObtenerMaquinas extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                json = cliente.ObtenerMaquinas(new Gson().toJson(""));
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
                    EliminarTodasFilas(tbMaquinas);
                    List<Object[]> filas = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                    }.getType());
                    for (Object[] fila : filas) {
                        AgregarFila(tbMaquinas, new Object[]{false, ((Double) fila[0]).intValue(), fila[1], fila[2], ((Double) fila[3]).intValue(), ((Double) fila[4]).intValue(), fila[5], Icon_Edit, Icon_Dele});
                    }
                    AgregarBoton(tbMaquinas, edit, 7);
                    AgregarBoton(tbMaquinas, dele, 8);
                    AgregarOrdenamiento(tbMaquinas);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarMaquina extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idMaquina = ObtenerValorCelda(tbMaquinas, 1);
                json = cliente.EliminarMaquina(new Gson().toJson(idMaquina));
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
                    new swObtenerMaquinas().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void Init(int modo) {
        this.modo = modo;
        switch (this.modo) {
            case 0:
                OcultarColumna(tbMaquinas, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbMaquinas, new int[]{0, 7, 8});
                OcultarControl(btnNuevo);
                break;
            case 2:
                OcultarColumnas(tbMaquinas, new int[]{7, 8});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerMaquinas().execute();
    }

    public void EditarMaquina() {
        int idMaquina = ObtenerValorCelda(tbMaquinas, 1);
        regMaquina regMaquina = new regMaquina("EDITAR ", idMaquina);
        this.getParent().add(regMaquina);
        regMaquina.setVisible(true);
    }

    public void EliminarMaquina() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarMaquina().execute();
        }
    }

    public Maquina getSeleccionado() {
        return seleccionado;
    }

    public List<Maquina> getSeleccionados() {
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

        frame = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMaquinas = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        tbMaquinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "IDMAQUINA", "DESCRIPCION", "TIPO", "ALTO MAX.", "LARGO MAX.", "ACTIVO", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbMaquinas.setRowHeight(25);
        tbMaquinas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbMaquinas);
        if (tbMaquinas.getColumnModel().getColumnCount() > 0) {
            tbMaquinas.getColumnModel().getColumn(1).setMinWidth(0);
            tbMaquinas.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbMaquinas.getColumnModel().getColumn(1).setMaxWidth(0);
            tbMaquinas.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE MÁQUINAS");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 481, Short.MAX_VALUE)
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

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/refresh-16.png"))); // NOI18N
        btnRefrescar.setToolTipText("REFRESCAR");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFiltro)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(9, 9, 9))
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

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        regMaquina regMaquina = new regMaquina("NUEVO ", 0);
        this.getParent().add(regMaquina);
        regMaquina.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (this.modo) {
            case 1:
                if (FilaActiva(tbMaquinas)) {
                    Maquina maquina = new Maquina();
                    maquina.setIdMaquina(ObtenerValorCelda(tbMaquinas, 1));
                    maquina.setDescripcion(ObtenerValorCelda(tbMaquinas, 2));
                    maquina.setAltoMaximoPliego(ObtenerValorCelda(tbMaquinas, 4));
                    maquina.setLargoMaximoPliego(ObtenerValorCelda(tbMaquinas, 5));
                    seleccionado = maquina;
                }
                Cerrar();
                break;
            case 2:
                for (int i = 0; i < tbMaquinas.getRowCount(); i++) {
                    boolean check = ObtenerValorCelda(tbMaquinas, i, 0);
                    if (check) {
                        Maquina maquina = new Maquina();
                        maquina.setIdMaquina(ObtenerValorCelda(tbMaquinas, i, 1));
                        maquina.setDescripcion(ObtenerValorCelda(tbMaquinas, i, 2));
                        maquina.setAltoMaximoPliego(ObtenerValorCelda(tbMaquinas, i, 4));
                        maquina.setLargoMaximoPliego(ObtenerValorCelda(tbMaquinas, i, 5));
                        seleccionados.add(maquina);
                    }
                }
                Cerrar();
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbMaquinas, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerMaquinas().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbMaquinas;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
