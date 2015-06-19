package com.sge.modulos.produccion.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.inventarios.clases.ItemSalidaInventario;
import com.sge.modulos.inventarios.clases.SalidaInventario;
import com.sge.modulos.inventarios.formularios.regSalidaInventario;
import com.sge.modulos.produccion.clases.ItemOrdenTrabajo;
import com.sge.modulos.produccion.clases.OrdenTrabajo;
import com.sge.modulos.produccion.cliente.cliProduccion;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisOrdenTrabajo extends frameBase<OrdenTrabajo> {

    /**
     * Creates new form lisOrdenTrabajo
     *
     * @param modo
     */
    public lisOrdenTrabajo(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form lisOrdenTrabajo
     *
     * @param modo
     * @param filtro
     */
    public lisOrdenTrabajo(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        switch (getModo()) {
            case 0:
                OcultarColumna(tbOrdenesTrabajo, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbOrdenesTrabajo, new int[]{0, 10, 11});
                break;
            case 2:
                OcultarColumnas(tbOrdenesTrabajo, new int[]{10, 11});
                break;
        }
        new swObtenerOrdenesTrabajo().execute();
    }

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarOrdenTrabajo();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarOrdenTrabajo();
        }
    };

    Action refr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swObtenerOrdenesTrabajo().execute();
        }
    };

    public class swObtenerOrdenesTrabajo extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliProduccion cliente = new cliProduccion();
            String json = "";
            try {
                json = cliente.ObtenerOrdenesTrabajo(new Gson().toJson(getFiltro()));
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
                    EliminarTodasFilas(tbOrdenesTrabajo);
                    OrdenTrabajo[] ordenesTrabajo = new Gson().fromJson(resultado[1], OrdenTrabajo[].class);
                    for (OrdenTrabajo ordenTrabajo : ordenesTrabajo) {
                        AgregarFila(tbOrdenesTrabajo, new Object[]{false, ordenTrabajo.getIdOrdenTrabajo(), ordenTrabajo.getNumero(), ordenTrabajo.getDescripcion(), ordenTrabajo.getCantidad(), ordenTrabajo.getFechaCreacionString(), ordenTrabajo.getRazonSocialCliente(), ordenTrabajo.getNombreCotizador(), ordenTrabajo.getNombreVendedor(), ordenTrabajo.getTotal(), Icon_Edit, Icon_Dele});
                    }
                    AgregarBoton(tbOrdenesTrabajo, edit, 10);
                    AgregarBoton(tbOrdenesTrabajo, dele, 11);
                    AgregarOrdenamiento(tbOrdenesTrabajo);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarOrdenTrabajo extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliProduccion cliente = new cliProduccion();
            String json = "";
            try {
                int idOrdenTrabajo = ObtenerValorCelda(tbOrdenesTrabajo, 1);
                json = cliente.EliminarOrdenTrabajo(new Gson().toJson(idOrdenTrabajo));
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
                    new swObtenerOrdenesTrabajo().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGenerarSalidaInventario extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliProduccion cliProduccion = new cliProduccion();
            try {
                int idOrdenTrabajo = ObtenerValorCelda(tbOrdenesTrabajo, 1);
                String json = cliProduccion.GenerarSalidaInventario(new Gson().toJson(new int[]{idOrdenTrabajo, getUsuario().getIdUsuario()}));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Date fechaServidor = new Gson().fromJson(resultado[1], Date.class);
                    OrdenTrabajo ordenTrabajo = new Gson().fromJson(resultado[2], OrdenTrabajo.class);
                    SalidaInventario salidaInventario = null;
                    if (resultado[3].isEmpty()) {
                        salidaInventario = new SalidaInventario();
                    } else {
                        salidaInventario = new Gson().fromJson(resultado[3], SalidaInventario.class);
                    }

                    if (salidaInventario.getIdAlmacen() == 0) {
                        throw new Exception("NO SE HA ASIGNADO NINGÚN ALMACEN.");
                    }

                    salidaInventario.setIdCliente(ordenTrabajo.getIdCliente());
                    salidaInventario.setRazonSocialCliente(ordenTrabajo.getRazonSocialCliente());
                    salidaInventario.setFechaCreacion(fechaServidor);
                    salidaInventario.setIdOrdenTrabajo(ordenTrabajo.getIdOrdenTrabajo());
                    salidaInventario.setNumeroOrdenTrabajo(ordenTrabajo.getNumero());

                    for (ItemOrdenTrabajo itemOrdenTrabajo : ordenTrabajo.getItems()) {
                        ItemSalidaInventario itemSalidaInventario = new ItemSalidaInventario();
                        itemSalidaInventario.setIdProducto(itemOrdenTrabajo.getIdMaterial());
                        itemSalidaInventario.setCodigoProducto(itemOrdenTrabajo.getCodigoMaterial());
                        itemSalidaInventario.setDescripcionProducto(itemOrdenTrabajo.getNombreMaterial());
                        itemSalidaInventario.setIdUnidad(itemOrdenTrabajo.getIdUnidadMaterial());
                        itemSalidaInventario.setAbreviacionUnidad(itemOrdenTrabajo.getAbreviacionUnidadMaterial());
                        itemSalidaInventario.setFactor(itemOrdenTrabajo.getFactorUnidadMaterial());
                        itemSalidaInventario.setCantidad(itemOrdenTrabajo.getCantidadMaterial() + itemOrdenTrabajo.getCantidadDemasiaMaterial());
                        itemSalidaInventario.setPrecio(itemOrdenTrabajo.getPrecioMaterial());
                        itemSalidaInventario.setTotal(itemSalidaInventario.getCantidad() * itemSalidaInventario.getPrecio());
                        itemSalidaInventario.setIdAlmacen(salidaInventario.getIdAlmacen());
                        salidaInventario.getItems().add(itemSalidaInventario);
                    }

                    regSalidaInventario regSalidaInventario = new regSalidaInventario();
                    regSalidaInventario.setUsuario(getUsuario());
                    regSalidaInventario.setEntidad(salidaInventario);
                    regSalidaInventario.AsignarControlesConStocks();
                    regSalidaInventario.CalcularTotales();
                    getParent().add(regSalidaInventario);
                    regSalidaInventario.setVisible(true);
                    regSalidaInventario.VerItemsSinStock();
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliProduccion.close();
            }
            return null;
        }

        @Override
        protected void done() {
            OcultarCargando(frame);
        }
    }

    public class swGenerarSalidaCaja extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliProduccion cliProduccion = new cliProduccion();
            try {
                int idOrdenTrabajo = ObtenerValorCelda(tbOrdenesTrabajo, 1);
                String json = cliProduccion.GenerarSalidaInventario(new Gson().toJson(new int[]{idOrdenTrabajo, getUsuario().getIdUsuario()}));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Date fechaServidor = new Gson().fromJson(resultado[1], Date.class);
                    OrdenTrabajo ordenTrabajo = new Gson().fromJson(resultado[2], OrdenTrabajo.class);
                    SalidaInventario salidaInventario = null;
                    if (resultado[3].isEmpty()) {
                        salidaInventario = new SalidaInventario();
                    } else {
                        salidaInventario = new Gson().fromJson(resultado[3], SalidaInventario.class);
                    }

                    if (salidaInventario.getIdAlmacen() == 0) {
                        throw new Exception("NO SE HA ASIGNADO NINGÚN ALMACEN.");
                    }

                    salidaInventario.setIdCliente(ordenTrabajo.getIdCliente());
                    salidaInventario.setRazonSocialCliente(ordenTrabajo.getRazonSocialCliente());
                    salidaInventario.setFechaCreacion(fechaServidor);
                    salidaInventario.setIdOrdenTrabajo(ordenTrabajo.getIdOrdenTrabajo());
                    salidaInventario.setNumeroOrdenTrabajo(ordenTrabajo.getNumero());

                    for (ItemOrdenTrabajo itemOrdenTrabajo : ordenTrabajo.getItems()) {
                        ItemSalidaInventario itemSalidaInventario = new ItemSalidaInventario();
                        itemSalidaInventario.setIdProducto(itemOrdenTrabajo.getIdMaterial());
                        itemSalidaInventario.setCodigoProducto(itemOrdenTrabajo.getCodigoMaterial());
                        itemSalidaInventario.setDescripcionProducto(itemOrdenTrabajo.getNombreMaterial());
                        itemSalidaInventario.setIdUnidad(itemOrdenTrabajo.getIdUnidadMaterial());
                        itemSalidaInventario.setAbreviacionUnidad(itemOrdenTrabajo.getAbreviacionUnidadMaterial());
                        itemSalidaInventario.setFactor(itemOrdenTrabajo.getFactorUnidadMaterial());
                        itemSalidaInventario.setCantidad(itemOrdenTrabajo.getCantidadMaterial() + itemOrdenTrabajo.getCantidadDemasiaMaterial());
                        itemSalidaInventario.setPrecio(itemOrdenTrabajo.getPrecioMaterial());
                        itemSalidaInventario.setTotal(itemSalidaInventario.getCantidad() * itemSalidaInventario.getPrecio());
                        itemSalidaInventario.setIdAlmacen(salidaInventario.getIdAlmacen());
                        salidaInventario.getItems().add(itemSalidaInventario);
                    }

                    regSalidaInventario regSalidaInventario = new regSalidaInventario();
                    regSalidaInventario.setUsuario(getUsuario());
                    regSalidaInventario.setEntidad(salidaInventario);
                    regSalidaInventario.AsignarControlesConStocks();
                    regSalidaInventario.CalcularTotales();
                    getParent().add(regSalidaInventario);
                    regSalidaInventario.setVisible(true);
                    regSalidaInventario.VerItemsSinStock();
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliProduccion.close();
            }
            return null;
        }

        @Override
        protected void done() {
            OcultarCargando(frame);
        }
    }

    public void EditarOrdenTrabajo() {
        int idOrdenTrabajo = ObtenerValorCelda(tbOrdenesTrabajo, 1);
        VerFrame(new regOrdenTrabajo(idOrdenTrabajo), refr);
    }

    public void EliminarOrdenTrabajo() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarOrdenTrabajo().execute();
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
        tbOrdenesTrabajo = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();
        btnGenerarSalidaInventario = new javax.swing.JButton();
        btnGenerarSalidaCaja = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        tbOrdenesTrabajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "NUMERO", "DESCRIPCION", "CANTIDAD", "F. CREACION", "CLIENTE", "COTIZADOR", "VENDEDOR", "TOTAL", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
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
        tbOrdenesTrabajo.setRowHeight(25);
        tbOrdenesTrabajo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbOrdenesTrabajo);
        if (tbOrdenesTrabajo.getColumnModel().getColumnCount() > 0) {
            tbOrdenesTrabajo.getColumnModel().getColumn(1).setMinWidth(0);
            tbOrdenesTrabajo.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbOrdenesTrabajo.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE ORDENES DE TRABAJO");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addContainerGap(402, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addContainerGap(17, Short.MAX_VALUE))
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

        btnGenerarSalidaInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/inventarios.png"))); // NOI18N
        btnGenerarSalidaInventario.setToolTipText("GENERAR SALIDA DE INVENTARIO");
        btnGenerarSalidaInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarSalidaInventarioActionPerformed(evt);
            }
        });

        btnGenerarSalidaCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/salida-caja.png"))); // NOI18N
        btnGenerarSalidaCaja.setToolTipText("GENERAR SALIDA DE CAJA");
        btnGenerarSalidaCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarSalidaCajaActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
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
                        .addComponent(btnGenerarSalidaInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenerarSalidaCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnGenerarSalidaInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarSalidaCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
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

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (getModo()) {
            case 1:
                if (FilaActiva(tbOrdenesTrabajo)) {
                    OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
                    ordenTrabajo.setIdOrdenTrabajo(ObtenerValorCelda(tbOrdenesTrabajo, 1));
                    ordenTrabajo.setNumero(ObtenerValorCelda(tbOrdenesTrabajo, 2));
                    ordenTrabajo.setDescripcion(ObtenerValorCelda(tbOrdenesTrabajo, 3));
                    ordenTrabajo.setCantidad(ObtenerValorCelda(tbOrdenesTrabajo, 4));
                    ordenTrabajo.setTotal(ObtenerValorCelda(tbOrdenesTrabajo, 9));
                    setSeleccionado(ordenTrabajo);
                }
                Cerrar();
                break;
            case 2:
                setSeleccionados(new ArrayList<>());
                for (int i = 0; i < tbOrdenesTrabajo.getRowCount(); i++) {
                    boolean check = ObtenerValorCelda(tbOrdenesTrabajo, i, 0);
                    if (check) {
                        OrdenTrabajo ordenTrabajo = new OrdenTrabajo();
                        ordenTrabajo.setIdOrdenTrabajo(ObtenerValorCelda(tbOrdenesTrabajo, i, 1));
                        ordenTrabajo.setNumero(ObtenerValorCelda(tbOrdenesTrabajo, i, 2));
                        ordenTrabajo.setDescripcion(ObtenerValorCelda(tbOrdenesTrabajo, i, 3));
                        ordenTrabajo.setCantidad(ObtenerValorCelda(tbOrdenesTrabajo, i, 4));
                        ordenTrabajo.setTotal(ObtenerValorCelda(tbOrdenesTrabajo, i, 9));
                        getSeleccionados().add(ordenTrabajo);
                    }
                }
                Cerrar();
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbOrdenesTrabajo, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerOrdenesTrabajo().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnGenerarSalidaInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarSalidaInventarioActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbOrdenesTrabajo)) {
            new swGenerarSalidaInventario().execute();
        }
    }//GEN-LAST:event_btnGenerarSalidaInventarioActionPerformed

    private void btnGenerarSalidaCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarSalidaCajaActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbOrdenesTrabajo)) {
            new swGenerarSalidaCaja().execute();
        }
    }//GEN-LAST:event_btnGenerarSalidaCajaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarSalidaCaja;
    private javax.swing.JButton btnGenerarSalidaInventario;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbOrdenesTrabajo;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
