package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.Cliente;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisCliente extends frameBase<Cliente> {

    /**
     * Creates new form lisCliente
     *
     * @param modo
     */
    public lisCliente(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form lisCliente
     *
     * @param modo
     * @param filtro
     */
    public lisCliente(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        switch (getModo()) {
            case 0:
                OcultarColumnas(tbClientes, new int[]{0, 1, 4, 6, 8});
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbClientes, new int[]{0, 1, 4, 6, 8, 11, 12});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerClientes().execute();
    }

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarCliente();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarCliente();
        }
    };

    Action refr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swObtenerClientes().execute();
        }
    };

    public class swObtenerClientes extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                json = cliente.ObtenerClientes(new Gson().toJson(getFiltro()));
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
                    Cliente[] clientes = new Gson().fromJson(resultado[1], Cliente[].class);
                    if (getModo() == 1 && clientes.length == 1) {
                        setSeleccionado(clientes[0]);
                        Cerrar();
                    } else {
                        EliminarTodasFilas(tbClientes);
                        for (Cliente cliente : clientes) {
                            AgregarFila(tbClientes, new Object[]{false, cliente.getIdCliente(), cliente.getRazonSocial(), cliente.getFechaUltimaVenta(), cliente.getIdListaPrecioProducto(), cliente.getNombreListaPrecioProducto(), cliente.getIdListaPrecioServicio(), cliente.getNombreListaPrecioServicio(), cliente.getIdListaPrecioMaquina(), cliente.getNombreListaPrecioMaquina(), cliente.isActivo(), Icon_Edit, Icon_Dele});
                        }
                        AgregarBoton(tbClientes, edit, 11);
                        AgregarBoton(tbClientes, dele, 12);
                        AgregarOrdenamiento(tbClientes);
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarCliente extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idCliente = ObtenerValorCelda(tbClientes, 1);
                json = cliente.EliminarCliente(new Gson().toJson(idCliente));
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
                    new swObtenerClientes().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void EditarCliente() {
        int idCliente = ObtenerValorCelda(tbClientes, 1);
        VerFrame(new regCliente(idCliente), refr);
    }

    public void EliminarCliente() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarCliente().execute();
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
        tbClientes = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "RAZON SOCIAL", "F. U. VENTA", "IDLPPRODUCTO", "L.P.PRODUCTO", "IDLPSERVICIO", "L.P.SERVICIO", "IDLPMAQUINA", "L.P.MAQUINA", "ACTIVO", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false, true, false, true, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(getModo() == 0) {
                    return canEdit [columnIndex];
                } else {
                    return false;
                }
            }
        });
        tbClientes.setRowHeight(25);
        tbClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbClientes);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE CLIENTES");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addGap(9, 9, 9))
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
        VerFrame(new regCliente(0), refr);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (getModo()) {
            case 1:
                if (FilaActiva(tbClientes)) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(ObtenerValorCelda(tbClientes, 1));
                    cliente.setRazonSocial(ObtenerValorCelda(tbClientes, 2));
                    cliente.setIdListaPrecioProducto(ObtenerValorCelda(tbClientes, 4));
                    cliente.setNombreListaPrecioProducto(ObtenerValorCelda(tbClientes, 5));
                    cliente.setIdListaPrecioServicio(ObtenerValorCelda(tbClientes, 6));
                    cliente.setNombreListaPrecioServicio(ObtenerValorCelda(tbClientes, 7));
                    cliente.setIdListaPrecioMaquina(ObtenerValorCelda(tbClientes, 8));
                    cliente.setNombreListaPrecioMaquina(ObtenerValorCelda(tbClientes, 9));
                    setSeleccionado(cliente);
                }
                Cerrar();
                break;
            case 2:
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbClientes, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerClientes().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void tbClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            switch (getModo()) {
                case 1:
                    if (FilaActiva(tbClientes)) {
                        Cliente cliente = new Cliente();
                        cliente.setIdCliente(ObtenerValorCelda(tbClientes, 1));
                        cliente.setRazonSocial(ObtenerValorCelda(tbClientes, 2));
                        cliente.setIdListaPrecioProducto(ObtenerValorCelda(tbClientes, 4));
                        cliente.setNombreListaPrecioProducto(ObtenerValorCelda(tbClientes, 5));
                        cliente.setIdListaPrecioServicio(ObtenerValorCelda(tbClientes, 6));
                        cliente.setNombreListaPrecioServicio(ObtenerValorCelda(tbClientes, 7));
                        cliente.setIdListaPrecioMaquina(ObtenerValorCelda(tbClientes, 8));
                        cliente.setNombreListaPrecioMaquina(ObtenerValorCelda(tbClientes, 9));
                        setSeleccionado(cliente);
                    }
                    Cerrar();
                    break;
                case 2:
                    break;
            }
        }
    }//GEN-LAST:event_tbClientesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbClientes;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
