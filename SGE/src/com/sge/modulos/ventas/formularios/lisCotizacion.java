package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.Cotizacion;
import com.sge.modulos.ventas.cliente.cliVentas;
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
public class lisCotizacion extends frameBase<Cotizacion> {

    /**
     * Creates new form lisCotizacion
     */
    public lisCotizacion(int modo) {
        initComponents();
        Init(modo, "");
    }

    public lisCotizacion(int modo, String filtro) {
        initComponents();
        Init(modo, filtro);
    }
    
    private int modo;
    
    private String filtro;

    private Cotizacion seleccionado;
    private List<Cotizacion> seleccionados = new ArrayList<>();

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarCotizacion();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String estado = ObtenerValorCelda(tbCotizaciones, 9);
            if(estado.equals("EN PRESUPUESTO")){
                VerAdvertencia("NO SE PUEDE ELIMINAR PORQUE ESTÁ INCLUIDO EN UN PRESUPUESTO.", frame);
            } else {
                EliminarCotizacion();
            }
        }
    };

    public class swObtenerCotizaciones extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                json = cliente.ObtenerCotizaciones(new Gson().toJson(filtro));
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
                    EliminarTodasFilas(tbCotizaciones);
                    Cotizacion[] cotizaciones = new Gson().fromJson(resultado[1], Cotizacion[].class);
                    for (Cotizacion cotizacion : cotizaciones) {
                        AgregarFila(tbCotizaciones, new Object[]{false, cotizacion.getIdCotizacion(), cotizacion.getNumero(), cotizacion.getDescripcion(), cotizacion.getFechaCreacionString(), cotizacion.getRazonSocialCliente(), cotizacion.getNombreCotizador(), cotizacion.getNombreVendedor(), cotizacion.getTotal(), cotizacion.getEstado(), Icon_Edit, Icon_Dele});
                    }
                    AgregarBoton(tbCotizaciones, edit, 10);
                    AgregarBoton(tbCotizaciones, dele, 11);
                    AgregarOrdenamiento(tbCotizaciones);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarCotizacion extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idCotizacion = ObtenerValorCelda(tbCotizaciones, 1);
                json = cliente.EliminarCotizacion(new Gson().toJson(idCotizacion));
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
                    new swObtenerCotizaciones().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swAprobarCotizacion extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idCotizacion = ObtenerValorCelda(tbCotizaciones, 1);
                json = cliente.AprobarCotizacion(new Gson().toJson(idCotizacion));
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
                    new swObtenerCotizaciones().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }
    
    public class swDesaprobarCotizacion extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idCotizacion = ObtenerValorCelda(tbCotizaciones, 1);
                json = cliente.DesaprobarCotizacion(new Gson().toJson(idCotizacion));
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
                    new swObtenerCotizaciones().execute();
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
                OcultarColumna(tbCotizaciones, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbCotizaciones, new int[]{0, 10, 11});
                OcultarControl(btnNuevo);
                break;
            case 2:
                OcultarColumnas(tbCotizaciones, new int[]{10, 11});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerCotizaciones().execute();
    }

    public void EditarCotizacion() {
        int idCotizacion = ObtenerValorCelda(tbCotizaciones, 1);
        regCotizacion regCotizacion = new regCotizacion(idCotizacion);
        this.getParent().add(regCotizacion);
        regCotizacion.setVisible(true);
    }

    public void EliminarCotizacion() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarCotizacion().execute();
        }
    }

    public void AprobarCotizacion() {
        String estado = ObtenerValorCelda(tbCotizaciones, 9);
        if (estado.equals("PENDIENTE DE APROBACIÓN")) {
            new swAprobarCotizacion().execute();
        } else {
            VerAdvertencia("SÓLO SE PUEDE APROBAR CUANDO ESTÁ EN ESTADO : PENDIENTE DE APROBACIÓN.", frame);
        }
    }
    
    public void DesaprobarCotizacion() {
        String estado = ObtenerValorCelda(tbCotizaciones, 9);
        if (estado.equals("APROBADO")) {
            new swDesaprobarCotizacion().execute();
        } else {
            VerAdvertencia("SÓLO SE PUEDE DESAPROBAR CUANDO ESTÁ EN ESTADO : APROBADO.", frame);
        }
    }
    
    public Cotizacion getSeleccionado() {
        return seleccionado;
    }

    public List<Cotizacion> getSeleccionados() {
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
        tbCotizaciones = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();
        btnAprobar = new javax.swing.JButton();
        btnDesaprobar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        tbCotizaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "NUMERO", "DESCRIPCION", "F. CREACION", "CLIENTE", "COTIZADOR", "VENDEDOR", "TOTAL", "ESTADO", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCotizaciones.setRowHeight(25);
        tbCotizaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbCotizaciones);
        if (tbCotizaciones.getColumnModel().getColumnCount() > 0) {
            tbCotizaciones.getColumnModel().getColumn(1).setMinWidth(0);
            tbCotizaciones.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbCotizaciones.getColumnModel().getColumn(1).setMaxWidth(0);
            tbCotizaciones.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbCotizaciones.getColumnModel().getColumn(5).setPreferredWidth(200);
            tbCotizaciones.getColumnModel().getColumn(6).setPreferredWidth(200);
            tbCotizaciones.getColumnModel().getColumn(7).setPreferredWidth(200);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE COTIZACIONES");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 834, Short.MAX_VALUE)
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

        btnAprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/checkmark-16.png"))); // NOI18N
        btnAprobar.setToolTipText("APROBAR");
        btnAprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprobarActionPerformed(evt);
            }
        });

        btnDesaprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/x-mark-16.png"))); // NOI18N
        btnDesaprobar.setToolTipText("DESAPROBAR");
        btnDesaprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesaprobarActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1181, Short.MAX_VALUE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDesaprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesaprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
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
        regCotizacion regCotizacion = new regCotizacion(0);
        regCotizacion.setUsuario(getUsuario());
        this.getParent().add(regCotizacion);
        regCotizacion.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (this.modo) {
            case 1:
                if (FilaActiva(tbCotizaciones)) {
                    Cotizacion cotizacion = new Cotizacion();
                    cotizacion.setIdCotizacion(ObtenerValorCelda(tbCotizaciones, 1));
                    cotizacion.setNumero(ObtenerValorCelda(tbCotizaciones, 2));
                    cotizacion.setDescripcion(ObtenerValorCelda(tbCotizaciones, 3));
                    cotizacion.setTotal(ObtenerValorCelda(tbCotizaciones, 8));
                    seleccionado = cotizacion;
                }
                Cerrar();
                break;
            case 2:
                for (int i = 0; i < tbCotizaciones.getRowCount(); i++) {
                    boolean check = ObtenerValorCelda(tbCotizaciones, i, 0);
                    if (check) {
                        Cotizacion cotizacion = new Cotizacion();
                        cotizacion.setIdCotizacion(ObtenerValorCelda(tbCotizaciones, i, 1));
                        cotizacion.setNumero(ObtenerValorCelda(tbCotizaciones, i, 2));
                        cotizacion.setDescripcion(ObtenerValorCelda(tbCotizaciones, i, 3));
                        cotizacion.setTotal(ObtenerValorCelda(tbCotizaciones, i, 8));
                        seleccionados.add(cotizacion);
                    }
                }
                Cerrar();
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbCotizaciones, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerCotizaciones().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnAprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbCotizaciones)) {
            AprobarCotizacion();
        }
    }//GEN-LAST:event_btnAprobarActionPerformed

    private void btnDesaprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesaprobarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbCotizaciones)) {
            DesaprobarCotizacion();
        }
    }//GEN-LAST:event_btnDesaprobarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAprobar;
    private javax.swing.JButton btnDesaprobar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbCotizaciones;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
