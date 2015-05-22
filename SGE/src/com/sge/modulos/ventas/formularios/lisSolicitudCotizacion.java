package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.Cotizacion;
import com.sge.modulos.ventas.clases.ItemCotizacion;
import com.sge.modulos.ventas.clases.ItemSolicitudCotizacion;
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
public class lisSolicitudCotizacion extends frameBase<SolicitudCotizacion> {

    /**
     * Creates new form lisSolicitudCotizacion
     */
    public lisSolicitudCotizacion(int modo) {
        initComponents();
        Init(modo);
    }

    private int modo = 0;

    private SolicitudCotizacion seleccionado;

    ImageIcon Icon_Edit = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/edit-16.png"));
    ImageIcon Icon_Dele = new ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"));

    Action edit = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EditarSolicitudCotizacion();
        }
    };

    Action dele = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            EliminarSolicitudCotizacion();
        }
    };

    public class swObtenerSolicitudesCotizacion extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                json = cliente.ObtenerSolicitudesCotizacion(new Gson().toJson(""));
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
                    EliminarTodasFilas(tbSolicitudes);
                    SolicitudCotizacion[] solicitudes = new Gson().fromJson(resultado[1], SolicitudCotizacion[].class);
                    for (SolicitudCotizacion solicitud : solicitudes) {
                        AgregarFila(tbSolicitudes, new Object[]{false, solicitud.getIdSolicitudCotizacion(), solicitud.getNumero(), solicitud.getDescripcion(), solicitud.getFechaCreacion(), solicitud.getRazonSocialCliente(), solicitud.getNombreVendedor(), solicitud.getEstado(), Icon_Edit, Icon_Dele});
                    }
                    AgregarBoton(tbSolicitudes, edit, 8);
                    AgregarBoton(tbSolicitudes, dele, 9);
                    AgregarOrdenamiento(tbSolicitudes);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swEliminarSolicitudCotizacion extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idSolicitudCotizacion = ObtenerValorCelda(tbSolicitudes, 1);
                json = cliente.EliminarSolicitudCotizacion(new Gson().toJson(idSolicitudCotizacion));
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
                    new swObtenerSolicitudesCotizacion().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swAprobarSolicitudCotizacion extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idSolicitudCotizacion = ObtenerValorCelda(tbSolicitudes, 1);
                json = cliente.AprobarSolicitudCotizacion(new Gson().toJson(idSolicitudCotizacion));
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
                    new swObtenerSolicitudesCotizacion().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }
    
    public class swDesaprobarSolicitudCotizacion extends SwingWorker {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliVentas cliente = new cliVentas();
            String json = "";
            try {
                int idSolicitudCotizacion = ObtenerValorCelda(tbSolicitudes, 1);
                json = cliente.DesaprobarSolicitudCotizacion(new Gson().toJson(idSolicitudCotizacion));
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
                    new swObtenerSolicitudesCotizacion().execute();
                } else {
                    OcultarCargando(frame);
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swGenerarCotizacion extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                int idSolicitud = ObtenerValorCelda(tbSolicitudes, 1);
                String json = cliVentas.GenerarCotizacion(new Gson().toJson(new int[]{idSolicitud, getUsuario().getIdUsuario()}));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Date fechaServidor = new Gson().fromJson(resultado[1], Date.class);
                    SolicitudCotizacion solicitud = new Gson().fromJson(resultado[2], SolicitudCotizacion.class);
                    Cotizacion cotizacion = null;
                    if (resultado[3].isEmpty()) {
                        cotizacion = new Cotizacion();
                    } else {
                        cotizacion = new Gson().fromJson(resultado[3], Cotizacion.class);
                    }
                    cotizacion.setIdCliente(solicitud.getIdCliente());
                    cotizacion.setRazonSocialCliente(solicitud.getRazonSocialCliente());
                    cotizacion.setIdListaPrecioProducto(solicitud.getIdListaPrecioProducto());
                    cotizacion.setNombreListaPrecioProducto(solicitud.getNombreListaPrecioProducto());
                    cotizacion.setIdListaPrecioServicio(solicitud.getIdListaPrecioServicio());
                    cotizacion.setNombreListaPrecioServicio(solicitud.getNombreListaPrecioServicio());
                    cotizacion.setIdListaPrecioMaquina(solicitud.getIdListaPrecioMaquina());
                    cotizacion.setNombreListaPrecioMaquina(solicitud.getNombreListaPrecioMaquina());
                    cotizacion.setDescripcion(solicitud.getDescripcion());
                    cotizacion.setIdMoneda(solicitud.getIdMoneda());
                    cotizacion.setSimboloMoneda(solicitud.getSimboloMoneda());
                    cotizacion.setFechaCreacion(fechaServidor);
                    cotizacion.setIdFormaPago(solicitud.getIdFormaPago());
                    cotizacion.setDescripcionFormaPago(solicitud.getDescripcionFormaPago());
                    cotizacion.setIdVendedor(solicitud.getIdVendedor());
                    cotizacion.setNombreVendedor(solicitud.getNombreVendedor());
                    cotizacion.setLineaProduccion(solicitud.getLineaProduccion());
                    cotizacion.setIdContactoCliente(solicitud.getIdContactoCliente());
                    cotizacion.setNombreContactoCliente(solicitud.getNombreContactoCliente());
                    cotizacion.setIdSolicitudCotizacion(solicitud.getIdSolicitudCotizacion());
                    cotizacion.setNumeroSolicitudCotizacion(solicitud.getNumero());
                    cotizacion.setCantidad(solicitud.getCantidad());

                    for (ItemSolicitudCotizacion itemSolicitud : solicitud.getItems()) {
                        ItemCotizacion itemCotizacion = new ItemCotizacion();
                        itemCotizacion.setNombre(itemSolicitud.getNombre());
                        itemCotizacion.setIdServicioImpresion(itemSolicitud.getIdServicioImpresion());
                        itemCotizacion.setNombreServicioImpresion(itemSolicitud.getNombreServicioImpresion());
                        itemCotizacion.setIdMaquina(itemSolicitud.getIdMaquina());
                        itemCotizacion.setDescripcionMaquina(itemSolicitud.getDescripcionMaquina());
                        itemCotizacion.setAltoMaximoPliegoMaquina(itemSolicitud.getAltoMaximoPliegoMaquina());
                        itemCotizacion.setLargoMaximoPliegoMaquina(itemSolicitud.getLargoMaximoPliegoMaquina());
                        itemCotizacion.setIdMaterial(itemSolicitud.getIdMaterial());
                        itemCotizacion.setNombreMaterial(itemSolicitud.getNombreMaterial());
                        itemCotizacion.setAltoMaterial(itemSolicitud.getAltoMaterial());
                        itemCotizacion.setLargoMaterial(itemSolicitud.getLargoMaterial());
                        itemCotizacion.setIdUnidadMaterial(itemSolicitud.getIdUnidadMaterial());
                        itemCotizacion.setAbreviacionUnidadMaterial(itemSolicitud.getAbreviacionUnidadMaterial());
                        itemCotizacion.setNombreTipoUnidad(itemSolicitud.getNombreTipoUnidad());
                        itemCotizacion.setUnidadMedidaAbierta(itemSolicitud.getUnidadMedidaAbierta());
                        itemCotizacion.setMedidaAbierta(itemSolicitud.isMedidaAbierta());
                        itemCotizacion.setMedidaCerrada(itemSolicitud.isMedidaCerrada());
                        itemCotizacion.setTiraRetira(itemSolicitud.isTiraRetira());
                        itemCotizacion.setGrafico(itemSolicitud.isGrafico());
                        itemCotizacion.setMaterial(itemSolicitud.isMaterial());
                        itemCotizacion.setServicioImpresion(itemSolicitud.isServicioImpresion());
                        itemCotizacion.setFondo(itemSolicitud.isFondo());
                        itemCotizacion.setTipoUnidad(itemSolicitud.isTipoUnidad());
                        itemCotizacion.setLargoMedidaAbierta(itemSolicitud.getLargoMedidaAbierta());
                        itemCotizacion.setAltoMedidaAbierta(itemSolicitud.getAltoMedidaAbierta());
                        itemCotizacion.setLargoMedidaCerrada(itemSolicitud.getLargoMedidaCerrada());
                        itemCotizacion.setAltoMedidaCerrada(itemSolicitud.getAltoMedidaCerrada());
                        itemCotizacion.setTiraColor(itemSolicitud.getTiraColor());
                        itemCotizacion.setRetiraColor(itemSolicitud.getRetiraColor());
                        itemCotizacion.setdFondo(itemSolicitud.getdFondo());
                        itemCotizacion.setCantidadTipoUnidad(itemSolicitud.getCantidadTipoUnidad());
                        if (itemSolicitud.isGrafico()) {
                            itemCotizacion.setIdMetodoImpresion(4);
                            itemCotizacion.setDescripcionMetodoImpresion("TIRA");
                            itemCotizacion.setCantidadPases(1);
                            itemCotizacion.setCantidadCambios(1);
                            itemCotizacion.setFactorHorizontal(1);
                            itemCotizacion.setFactorVertical(1);
                        }
                        cotizacion.getItems().add(itemCotizacion);
                    }

                    regCotizacion regCotizacion = new regCotizacion(0);
                    regCotizacion.setUsuario(getUsuario());
                    regCotizacion.setEntidad(cotizacion);
                    regCotizacion.AsignarControles();
                    getParent().add(regCotizacion);
                    regCotizacion.setVisible(true);
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

    public void Init(int modo) {
        this.modo = modo;
        switch (this.modo) {
            case 0:
                OcultarColumna(tbSolicitudes, 0);
                OcultarControl(btnSeleccionar);
                break;
            case 1:
                OcultarColumnas(tbSolicitudes, new int[]{0, 8, 9});
                OcultarControl(btnNuevo);
                break;
        }
        new swObtenerSolicitudesCotizacion().execute();
    }

    public void EditarSolicitudCotizacion() {
        int idSolicitudCotizacion = ObtenerValorCelda(tbSolicitudes, 1);
        regSolicitudCotizacion regSolicitudCotizacion = new regSolicitudCotizacion(idSolicitudCotizacion);
        this.getParent().add(regSolicitudCotizacion);
        regSolicitudCotizacion.setVisible(true);
    }

    public void EliminarSolicitudCotizacion() {
        int confirmacion = VerConfirmacion(this);
        if (confirmacion == 0) {
            new swEliminarSolicitudCotizacion().execute();
        }
    }

    public void AprobarSolicitudCotizacion() {
        String estado = ObtenerValorCelda(tbSolicitudes, 7);
        if (estado.equals("PENDIENTE DE APROBACIÓN")) {
            new swAprobarSolicitudCotizacion().execute();
        } else {
            VerAdvertencia("NO SE PUEDE APROBAR LA SOLICITUD DE COTIZACIÓN", frame);
        }
    }
    
    public void DesaprobarSolicitudCotizacion() {
        String estado = ObtenerValorCelda(tbSolicitudes, 7);
        if (estado.equals("APROBADO")) {
            new swDesaprobarSolicitudCotizacion().execute();
        } else {
            VerAdvertencia("NO SE PUEDE DESAPROBAR LA SOLICITUD DE COTIZACIÓN", frame);
        }
    }

    public void GenerarCotizacion() {
        String estado = ObtenerValorCelda(tbSolicitudes, 7);
        if (estado.equals("APROBADO")) {
            new swGenerarCotizacion().execute();
        } else {
            VerAdvertencia("NO SE PUEDE GENERAR LA COTIZACIÓN", frame);
        }
    }
    
    public SolicitudCotizacion getSeleccionado() {
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
        tbSolicitudes = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();
        btnGenerarCotizacion = new javax.swing.JButton();
        btnAprobar = new javax.swing.JButton();
        btnDesaprobar = new javax.swing.JButton();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        tbSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "NUMERO", "DESCRIPCION", "F. CREACION", "CLIENTE", "VENDEDOR", "ESTADO", "EDITAR", "ELIMINAR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSolicitudes.setRowHeight(25);
        tbSolicitudes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbSolicitudes);
        if (tbSolicitudes.getColumnModel().getColumnCount() > 0) {
            tbSolicitudes.getColumnModel().getColumn(1).setMinWidth(0);
            tbSolicitudes.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbSolicitudes.getColumnModel().getColumn(1).setMaxWidth(0);
            tbSolicitudes.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbSolicitudes.getColumnModel().getColumn(5).setPreferredWidth(200);
            tbSolicitudes.getColumnModel().getColumn(6).setPreferredWidth(200);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE SOLICITUDES DE COTIZACIÓN");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 623, Short.MAX_VALUE)
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

        btnGenerarCotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/send-file-16.png"))); // NOI18N
        btnGenerarCotizacion.setToolTipText("GENERAR COTIZACION");
        btnGenerarCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarCotizacionActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenerarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnGenerarCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesaprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
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
        regSolicitudCotizacion regSolicitudCotizacion = new regSolicitudCotizacion(0);
        regSolicitudCotizacion.setUsuario(getUsuario());
        this.getParent().add(regSolicitudCotizacion);
        regSolicitudCotizacion.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        switch (this.modo) {
            case 1:
                if (FilaActiva(tbSolicitudes)) {
                    SolicitudCotizacion solicitudCotizacion = new SolicitudCotizacion();
                    solicitudCotizacion.setIdSolicitudCotizacion(ObtenerValorCelda(tbSolicitudes, 1));
                    solicitudCotizacion.setNumero(ObtenerValorCelda(tbSolicitudes, 2));
                    seleccionado = solicitudCotizacion;
                }
                Cerrar();
                break;
            case 2:
                break;
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbSolicitudes, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerSolicitudesCotizacion().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnGenerarCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarCotizacionActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbSolicitudes)) {
            GenerarCotizacion();
        }
    }//GEN-LAST:event_btnGenerarCotizacionActionPerformed

    private void btnAprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbSolicitudes)) {
            AprobarSolicitudCotizacion();
        }
    }//GEN-LAST:event_btnAprobarActionPerformed

    private void btnDesaprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesaprobarActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbSolicitudes)) {
            DesaprobarSolicitudCotizacion();
        }
    }//GEN-LAST:event_btnDesaprobarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAprobar;
    private javax.swing.JButton btnDesaprobar;
    private javax.swing.JButton btnGenerarCotizacion;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbSolicitudes;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
