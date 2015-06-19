package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.inventarios.clases.Unidad;
import com.sge.modulos.inventarios.formularios.lisUnidad;
import com.sge.modulos.ventas.clases.Maquina;
import com.sge.modulos.ventas.clases.Servicio;
import com.sge.modulos.ventas.clases.ServicioMaquina;
import com.sge.modulos.ventas.clases.ServicioUnidad;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regServicio extends frameBase<Servicio> {

    /**
     * Creates new form regServicio
     *
     * @param id
     */
    public regServicio(int id) {
        initComponents();
        setId(id);
    }

    @Override
    public void Init() {
        if (getId() == 0) {
            lblTitulo.setText("NUEVO " + lblTitulo.getText());
        } else {
            lblTitulo.setText("EDITAR " + lblTitulo.getText());
            new swObtenerServicio().execute();
        }
    }

    private List<ServicioUnidad> servicioUnidades = new ArrayList<>();
    private List<ServicioMaquina> servicioMaquinas = new ArrayList<>();

    Action sele_unidad = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Unidad> unidades = ((lisUnidad) e.getSource()).getSeleccionados();
            for (Unidad unidad : unidades) {
                AgregarFila(tbUnidades, new Object[]{0, unidad.getIdUnidad(), unidad.getAbreviacion(), 0});
            }
        }
    };

    Action sele_maquina = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Maquina> maquinas = ((lisMaquina) e.getSource()).getSeleccionados();
            for (Maquina maquina : maquinas) {
                ServicioMaquina servicioMaquina = new ServicioMaquina();
                servicioMaquina.setIdMaquina(maquina.getIdMaquina());
                servicioMaquina.setDescripcionMaquina(maquina.getDescripcion());
                servicioMaquinas.add(servicioMaquina);
                AgregarFila(tbMaquinas, new Object[]{0, maquina.getIdMaquina(), maquina.getDescripcion()});
            }
        }
    };

    public List<ServicioUnidad> getServicioUnidades() {
        for (int i = 0; i < tbUnidades.getRowCount(); i++) {
            int idServicioUnidad = ObtenerValorCelda(tbUnidades, i, 0);
            ServicioUnidad servicioUnidad = new ServicioUnidad();
            servicioUnidad.setIdUnidad(ObtenerValorCelda(tbUnidades, i, 1));
            servicioUnidad.setAbreviacionUnidad(ObtenerValorCelda(tbUnidades, i, 2));
            servicioUnidad.setFactor(ObtenerValorCelda(tbUnidades, i, 3));
            if (idServicioUnidad == 0) {
                servicioUnidad.setAgregar(true);
            } else {
                servicioUnidad.setIdServicioUnidad(idServicioUnidad);
                servicioUnidad.setActualizar(true);

            }
            servicioUnidades.add(servicioUnidad);
        }
        return servicioUnidades;
    }

    public class swObtenerServicio extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                json = cliente.ObtenerServicio(new Gson().toJson(getId()));
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
                    Servicio servicio = new Gson().fromJson(resultado[1], Servicio.class);
                    txtCodigo.setText(servicio.getCodigo());
                    txtDescripcion.setText(servicio.getDescripcion());
                    chkServicioImpresion.setSelected(servicio.isServicioImpresion());
                    chkActivo.setSelected(servicio.isActivo());
                    for (ServicioUnidad servicioUnidad : servicio.getUnidades()) {
                        AgregarFila(tbUnidades,
                                new Object[]{
                                    servicioUnidad.getIdServicioUnidad(),
                                    servicioUnidad.getIdUnidad(),
                                    servicioUnidad.getAbreviacionUnidad(),
                                    servicioUnidad.getFactor()
                                });
                    }
                    for (ServicioMaquina servicioMaquina : servicio.getMaquinas()) {
                        AgregarFila(tbMaquinas,
                                new Object[]{
                                    servicioMaquina.getIdServicioMaquina(),
                                    servicioMaquina.getIdMaquina(),
                                    servicioMaquina.getDescripcionMaquina()
                                });
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGuardarServicio extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                Servicio servicio = new Servicio();
                servicio.setCodigo(txtCodigo.getText());
                servicio.setDescripcion(txtDescripcion.getText());
                servicio.setServicioImpresion(chkServicioImpresion.isSelected());
                servicio.setUnidades(getServicioUnidades());
                servicio.setMaquinas(servicioMaquinas);
                servicio.setActivo(chkActivo.isSelected());
                if (getId() == 0) {
                    json = cliente.RegistrarServicio(new Gson().toJson(servicio));
                } else {
                    servicio.setIdServicio(getId());
                    json = cliente.ActualizarServicio(new Gson().toJson(servicio));
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
                    Cerrar();
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
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        chkActivo = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabUnidades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUnidades = new javax.swing.JTable();
        btnNuevaUnidad = new javax.swing.JButton();
        btnEliminarUnidad = new javax.swing.JButton();
        tabMaquinas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMaquinas = new javax.swing.JTable();
        btnNuevaMaquina = new javax.swing.JButton();
        btnEliminarMaquina = new javax.swing.JButton();
        chkServicioImpresion = new javax.swing.JCheckBox();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();

        frame.setBackground(java.awt.Color.white);

        lblCodigo.setText("CODIGO");

        lblNombre.setText("DESCRIPCION");

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

        jTabbedPane1.setBorder(null);

        tabUnidades.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbUnidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDUNIDAD", "UNIDAD", "FACTOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUnidades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbUnidades);
        if (tbUnidades.getColumnModel().getColumnCount() > 0) {
            tbUnidades.getColumnModel().getColumn(0).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(0).setMaxWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        btnNuevaUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevaUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaUnidadActionPerformed(evt);
            }
        });

        btnEliminarUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUnidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabUnidadesLayout = new javax.swing.GroupLayout(tabUnidades);
        tabUnidades.setLayout(tabUnidadesLayout);
        tabUnidadesLayout.setHorizontalGroup(
            tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUnidadesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevaUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabUnidadesLayout.setVerticalGroup(
            tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabUnidadesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(tabUnidadesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNuevaUnidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarUnidad)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("UNIDADES", tabUnidades);

        tabMaquinas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbMaquinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDMAQUINA", "MAQUINA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbMaquinas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbMaquinas);
        if (tbMaquinas.getColumnModel().getColumnCount() > 0) {
            tbMaquinas.getColumnModel().getColumn(0).setMinWidth(0);
            tbMaquinas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbMaquinas.getColumnModel().getColumn(0).setMaxWidth(0);
            tbMaquinas.getColumnModel().getColumn(1).setMinWidth(0);
            tbMaquinas.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbMaquinas.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        btnNuevaMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevaMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaMaquinaActionPerformed(evt);
            }
        });

        btnEliminarMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMaquinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabMaquinasLayout = new javax.swing.GroupLayout(tabMaquinas);
        tabMaquinas.setLayout(tabMaquinasLayout);
        tabMaquinasLayout.setHorizontalGroup(
            tabMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMaquinasLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevaMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tabMaquinasLayout.setVerticalGroup(
            tabMaquinasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMaquinasLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(tabMaquinasLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNuevaMaquina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarMaquina)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MAQUINAS", tabMaquinas);

        chkServicioImpresion.setText("SERVICIO IMPRESION");

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("SERVICIO");

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

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chkServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCodigo)
                                .addComponent(lblNombre))
                            .addGap(24, 24, 24)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chkActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActivo)
                    .addComponent(lblCodigo))
                .addGap(3, 3, 3)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkServicioImpresion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarServicio().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevaUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaUnidadActionPerformed
        // TODO add your handling code here:
        VerModal(new lisUnidad(2), sele_unidad);
    }//GEN-LAST:event_btnNuevaUnidadActionPerformed

    private void btnEliminarUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUnidadActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbUnidades)) {
            int idServicioUnidad = ObtenerValorCelda(tbUnidades, 0);
            if (idServicioUnidad > 0) {
                ServicioUnidad servicioUnidad = new ServicioUnidad();
                servicioUnidad.setIdServicioUnidad(idServicioUnidad);
                servicioUnidad.setEliminar(true);
                servicioUnidades.add(servicioUnidad);
            }
            EliminarFila(tbUnidades);
        }
    }//GEN-LAST:event_btnEliminarUnidadActionPerformed

    private void btnNuevaMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaMaquinaActionPerformed
        // TODO add your handling code here:
        VerModal(new lisMaquina(2), sele_maquina);
    }//GEN-LAST:event_btnNuevaMaquinaActionPerformed

    private void btnEliminarMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMaquinaActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbMaquinas)) {
            int idServicioMaquina = ObtenerValorCelda(tbMaquinas, 0);
            if (idServicioMaquina == 0) {
                int idMaquina = ObtenerValorCelda(tbMaquinas, 1);
                servicioMaquinas.removeIf(p -> p.getIdMaquina() == idMaquina);
            } else {
                ServicioMaquina servicioMaquina = new ServicioMaquina();
                servicioMaquina.setIdServicioMaquina(idServicioMaquina);
                servicioMaquinas.add(servicioMaquina);
            }
            EliminarFila(tbMaquinas);
        }
    }//GEN-LAST:event_btnEliminarMaquinaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarMaquina;
    private javax.swing.JButton btnEliminarUnidad;
    private javax.swing.JButton btnNuevaMaquina;
    private javax.swing.JButton btnNuevaUnidad;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JCheckBox chkServicioImpresion;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel tabMaquinas;
    private javax.swing.JPanel tabUnidades;
    private javax.swing.JTable tbMaquinas;
    private javax.swing.JTable tbUnidades;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
