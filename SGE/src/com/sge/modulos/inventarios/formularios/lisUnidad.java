package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.inventarios.clases.Unidad;
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
public class lisUnidad extends frameBase<Unidad> {

    /**
     * Creates new form lisUnidad
     */
    public lisUnidad(int modo) {
        initComponents();
        Init(modo, filtro);
    }
    
    public lisUnidad(int modo, String filtro) {
        initComponents();
        Init(modo, filtro);
    }

    private int modo;

    private String filtro;
    
    private Unidad seleccionado;

    private List<Unidad> seleccionados = new ArrayList<>();

    ImageIcon Icon_Save = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action save = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swGuardarUnidad().execute();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarUnidad();
        }
    };

    public class swObtenerUnidades extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerUnidades(new Gson().toJson(filtro));
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
                    EliminarTodasFilas(tbUnidades);
                    Unidad[] unidades = new Gson().fromJson(resultado[1].toString(), Unidad[].class);
                    for (Unidad unidad : unidades) {
                        AgregarFila(tbUnidades, new Object[]{false, unidad.getIdUnidad(), unidad.getAbreviacion(), unidad.getDescripcion(), unidad.isActivo(), Icon_Save, Icon_Dele});
                    }
                    AgregarBoton(tbUnidades, save, 5);
                    AgregarBoton(tbUnidades, dele, 6);
                    AgregarOrdenamiento(tbUnidades);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGuardarUnidad extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                Unidad unidad = new Unidad();
                unidad.setIdUnidad(ObtenerValorCelda(tbUnidades, 1));
                unidad.setAbreviacion(ObtenerValorCelda(tbUnidades, 2));
                unidad.setDescripcion(ObtenerValorCelda(tbUnidades, 3));
                unidad.setActivo(ObtenerValorCelda(tbUnidades, 4));
                if (unidad.getIdUnidad() == 0) {
                    json = cliente.RegistrarUnidad(new Gson().toJson(unidad));
                } else {
                    json = cliente.ActualizarUnidad(new Gson().toJson(unidad));
                }
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
                    new swObtenerUnidades().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarUnidad extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                int idUnidad = ObtenerValorCelda(tbUnidades, 1);
                json = cliente.EliminarUnidad(new Gson().toJson(idUnidad));
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
                    new swObtenerUnidades().execute();
                } else {
                    OcultarCargando(frame);
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
                OcultarColumna(tbUnidades, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbUnidades, new int[]{0, 5, 6});
                OcultarControl(btnNuevo);
                break;
            case 2:
                OcultarColumnas(tbUnidades, new int[]{5, 6});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerUnidades().execute();
    }

    public void EliminarUnidad() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            int idUnidad = ObtenerValorCelda(tbUnidades, 1);
            if (idUnidad == 0) {
                EliminarFila(tbUnidades);
            } else {
                new swEliminarUnidad().execute();
            }
        }
    }

    public Unidad getSeleccionado() {
        return seleccionado;
    }

    public List<Unidad> getSeleccionados() {
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
        tbUnidades = new javax.swing.JTable();
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

        tbUnidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "IDUNIDAD", "ABREVIACION", "DESCRIPCION", "ACTIVO", "GUARDAR", "ELIMINAR"
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
        tbUnidades.setRowHeight(25);
        tbUnidades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbUnidades);
        if (tbUnidades.getColumnModel().getColumnCount() > 0) {
            tbUnidades.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbUnidades.getColumnModel().getColumn(1).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setMaxWidth(0);
            tbUnidades.getColumnModel().getColumn(5).setPreferredWidth(30);
            tbUnidades.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE UNIDADES");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 816, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(15, 15, 15))
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
        AgregarFila(tbUnidades, new Object[]{false, 0, "", "", false, Icon_Save, Icon_Dele});
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (this.modo) {
            case 1:

                break;
            case 2:
                for (int i = 0; i < tbUnidades.getRowCount(); i++) {
                    boolean check = ObtenerValorCelda(tbUnidades, i, 0);
                    if (check) {
                        Unidad unidad = new Unidad();
                        unidad.setIdUnidad(ObtenerValorCelda(tbUnidades, i, 1));
                        unidad.setAbreviacion(ObtenerValorCelda(tbUnidades, i, 2));
                        unidad.setDescripcion(ObtenerValorCelda(tbUnidades, i, 3));
                        unidad.setActivo(ObtenerValorCelda(tbUnidades, i, 4));
                        seleccionados.add(unidad);
                    }
                }
                Cerrar();
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbUnidades, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerUnidades().execute();
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
    private javax.swing.JTable tbUnidades;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
