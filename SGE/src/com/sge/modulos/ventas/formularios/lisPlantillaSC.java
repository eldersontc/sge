package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.ItemPlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.clases.ItemSolicitudCotizacion;
import com.sge.modulos.ventas.clases.PlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.clases.SolicitudCotizacion;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisPlantillaSC extends frameBase<PlantillaSolicitudCotizacion> {

    /**
     * Creates new form lisPlantillaSCx
     */
    public lisPlantillaSC(int modo) {
        initComponents();
        Init(modo, "");
    }

    public lisPlantillaSC(int modo, String filtro) {
        initComponents();
        Init(modo, filtro);
    }

    private int modo;

    private String filtro;

    private PlantillaSolicitudCotizacion seleccionado;

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarPlantillaSC();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarPlantillaSC();
        }
    };

    Action refr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new swObtenerPlantillasSC().execute();
        }
    };

    public class swObtenerPlantillasSC extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                json = cliente.ObtenerPlantillasSolicitudCotizacion(new Gson().toJson(filtro));
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
                    EliminarTodasFilas(tbPlantillas);
                    PlantillaSolicitudCotizacion[] plantillas = new Gson().fromJson(resultado[1], PlantillaSolicitudCotizacion[].class);
                    for (PlantillaSolicitudCotizacion plantilla : plantillas) {
                        AgregarFila(tbPlantillas, new Object[]{false, plantilla.getIdPlantillaSolicitudCotizacion(), plantilla.getNombre(), plantilla.getLineaProduccion(), plantilla.isActivo(), Icon_Edit, Icon_Dele});
                    }
                    AgregarBoton(tbPlantillas, edit, 5);
                    AgregarBoton(tbPlantillas, dele, 6);
                    AgregarOrdenamiento(tbPlantillas);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarPlantillaSC extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas plantillaSC = new cliVentas();
            String json = "";
            try {
                int idPlantillaSC = ObtenerValorCelda(tbPlantillas, 1);
                json = plantillaSC.EliminarPlantillaSolicitudCotizacion(new Gson().toJson(idPlantillaSC));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                plantillaSC.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    new swObtenerPlantillasSC().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGenerarSolicitudCotizacion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                int idPlantilla = ObtenerValorCelda(tbPlantillas, 1);
                String json = cliVentas.GenerarSolicitudCotizacion(new Gson().toJson(new int[]{idPlantilla, getUsuario().getIdUsuario()}));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Date fechaServidor = new Gson().fromJson(resultado[1], Date.class);
                    PlantillaSolicitudCotizacion plantilla = new Gson().fromJson(resultado[2], PlantillaSolicitudCotizacion.class);
                    SolicitudCotizacion solicitud = null;
                    if (resultado[4].isEmpty()) {
                        solicitud = new SolicitudCotizacion();
                    } else {
                        solicitud = new Gson().fromJson(resultado[4], SolicitudCotizacion.class);
                    }
                    solicitud.setDescripcion(plantilla.getNombre());
                    solicitud.setFechaCreacion(fechaServidor);
                    solicitud.setGrupo(String.valueOf(new Gson().fromJson(resultado[3], int.class)));
                    solicitud.setLineaProduccion(plantilla.getLineaProduccion());

                    for (ItemPlantillaSolicitudCotizacion itemPlantilla : plantilla.getItems()) {
                        ItemSolicitudCotizacion itemSolicitud = new ItemSolicitudCotizacion();
                        itemSolicitud.setNombre(itemPlantilla.getNombre());
                        itemSolicitud.setServicioImpresion(itemPlantilla.isServicioImpresion());
                        itemSolicitud.setIdServicioImpresion(itemPlantilla.getIdServicioImpresion());
                        itemSolicitud.setNombreServicioImpresion(itemPlantilla.getNombreServicioImpresion());
                        itemSolicitud.setMaterial(itemPlantilla.isMaterial());
                        itemSolicitud.setIdMaterial(itemPlantilla.getIdMaterial());
                        itemSolicitud.setCodigoMaterial(itemPlantilla.getCodigoMaterial());
                        itemSolicitud.setNombreMaterial(itemPlantilla.getNombreMaterial());
                        itemSolicitud.setIdUnidadMaterial(itemPlantilla.getIdUnidadMaterial());
                        itemSolicitud.setAbreviacionUnidadMaterial(itemPlantilla.getAbreviacionUnidadMaterial());
                        itemSolicitud.setFactorUnidadMaterial(itemPlantilla.getFactorUnidadMaterial());
                        itemSolicitud.setAltoMaterial(itemPlantilla.getAltoMaterial());
                        itemSolicitud.setLargoMaterial(itemPlantilla.getLargoMaterial());
                        itemSolicitud.setTipoUnidad(itemPlantilla.isTipoUnidad());
                        itemSolicitud.setNombreTipoUnidad(itemPlantilla.getNombreTipoUnidad());
                        itemSolicitud.setMedidaAbierta(itemPlantilla.isMedidaAbierta());
                        itemSolicitud.setUnidadMedidaAbierta(itemPlantilla.getUnidadMedidaAbierta());
                        itemSolicitud.setMedidaCerrada(itemPlantilla.isMedidaCerrada());
                        itemSolicitud.setTiraRetira(itemPlantilla.isTiraRetira());
                        itemSolicitud.setGrafico(itemPlantilla.isGrafico());
                        itemSolicitud.setFondo(itemPlantilla.isFondo());
                        solicitud.getItems().add(itemSolicitud);
                    }

                    regSolicitudCotizacion regSolicitudCotizacion = new regSolicitudCotizacion();
                    regSolicitudCotizacion.setUsuario(getUsuario());
                    regSolicitudCotizacion.setEntidad(solicitud);
                    regSolicitudCotizacion.AsignarControles();
                    getParent().add(regSolicitudCotizacion);
                    regSolicitudCotizacion.setVisible(true);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliVentas.close();
            }
            return null;
        }

        @Override
        protected void done() {
            OcultarCargando(frame);
        }
    }

    public void Init(int modo, String filtro) {
        this.modo = modo;
        this.filtro = filtro;
        switch (this.modo) {
            case 0:
                OcultarColumna(tbPlantillas, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbPlantillas, new int[]{0, 5, 6});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerPlantillasSC().execute();
    }

    public void EditarPlantillaSC() {
        int idPlantillaSC = ObtenerValorCelda(tbPlantillas, 1);
        VerFrame(new regPlantillaSC("EDITAR ", idPlantillaSC), refr);
    }

    public void EliminarPlantillaSC() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarPlantillaSC().execute();
        }
    }

    public PlantillaSolicitudCotizacion getSeleccionado() {
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

        frame = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlantillas = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();
        btnGenerarSolicitudCotizacion = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbPlantillas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "NOMBRE", "LINEA DE PRODUCCION", "ACTIVO", "EDITAR", "ELIMINAR"
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
        tbPlantillas.setRowHeight(25);
        tbPlantillas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbPlantillas);
        if (tbPlantillas.getColumnModel().getColumnCount() > 0) {
            tbPlantillas.getColumnModel().getColumn(1).setMinWidth(0);
            tbPlantillas.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbPlantillas.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE PLANTILLAS DE SOLICITUD DE COTIZACIÓN");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
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

        btnGenerarSolicitudCotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/send-file-16.png"))); // NOI18N
        btnGenerarSolicitudCotizacion.setToolTipText("GENERAR SOLICITUD COTIZACION");
        btnGenerarSolicitudCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarSolicitudCotizacionActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
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
                        .addComponent(btnGenerarSolicitudCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnGenerarSolicitudCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
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
        VerFrame(new regPlantillaSC("NUEVO ", 0), refr);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (this.modo) {
            case 1:
                if (FilaActiva(tbPlantillas)) {
                    PlantillaSolicitudCotizacion plantillaSC = new PlantillaSolicitudCotizacion();
                    plantillaSC.setIdPlantillaSolicitudCotizacion(ObtenerValorCelda(tbPlantillas, 1));
                    plantillaSC.setNombre(ObtenerValorCelda(tbPlantillas, 2));
                    seleccionado = plantillaSC;
                }
                Cerrar();
                break;
            case 2:
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbPlantillas, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerPlantillasSC().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnGenerarSolicitudCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarSolicitudCotizacionActionPerformed
        // TODO add your handling code here:
        new swGenerarSolicitudCotizacion().execute();
    }//GEN-LAST:event_btnGenerarSolicitudCotizacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarSolicitudCotizacion;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbPlantillas;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
