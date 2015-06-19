package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.EscalaListaPrecioServicio;
import com.sge.modulos.ventas.clases.ItemListaPrecioServicio;
import com.sge.modulos.ventas.clases.ListaPrecioServicio;
import com.sge.modulos.ventas.clases.Servicio;
import com.sge.modulos.ventas.clases.ServicioUnidad;
import com.sge.modulos.ventas.clases.UnidadListaPrecioServicio;
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
public class regListaPrecioServicio extends frameBase<ListaPrecioServicio> {

    /**
     * Creates new form regListaPrecioServicio
     *
     * @param id
     */
    public regListaPrecioServicio(int id) {
        initComponents();
        setId(id);
    }

    @Override
    public void Init() {
        if (getId() == 0) {
            lblTitulo.setText("NUEVA " + lblTitulo.getText());
            OcultarControl(btnNuevoItem);
            OcultarControl(btnNuevaUnidad);
            OcultarControl(btnNuevaEscala);
        } else {
            lblTitulo.setText("EDITAR " + lblTitulo.getText());
            new swObtenerListaPrecio().execute();
        }
    }

    int idItem = 0;
    int idServicio = 0;
    int idUnidad = 0;
    int idEscala = 0;

    public void ObtenerUnidades() {
        cliVentas cliente = new cliVentas();
        try {
            String json = cliente.ObtenerUnidadesListaPrecioServicio(new Gson().toJson(idItem));
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                UnidadListaPrecioServicio[] unidades = new Gson().fromJson(resultado[1], UnidadListaPrecioServicio[].class);
                EliminarTodasFilas(tbUnidades);
                EliminarTodasFilas(tbEscalas);
                for (UnidadListaPrecioServicio unidad : unidades) {
                    AgregarFila(tbUnidades, new Object[]{unidad.getIdUnidadListaPrecioServicio(), unidad.getIdServicioUnidad(), unidad.getAbreviacionUnidad(), unidad.getFactor()});
                }
            }
        } catch (Exception e) {
            ControlarExcepcion(e);
        } finally {
            cliente.close();
        }
    }

    public void ObtenerEscalas() {
        cliVentas cliente = new cliVentas();
        try {
            String json = cliente.ObtenerEscalasListaPrecioServicio(new Gson().toJson(idUnidad));
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                EscalaListaPrecioServicio[] escalas = new Gson().fromJson(resultado[1], EscalaListaPrecioServicio[].class);
                EliminarTodasFilas(tbEscalas);
                for (EscalaListaPrecioServicio escala : escalas) {
                    AgregarFila(tbEscalas, new Object[]{escala.getIdEscalaListaPrecioServicio(), escala.getDesde(), escala.getHasta(), escala.getPrecio()});
                }
            }
        } catch (Exception e) {
            ControlarExcepcion(e);
        } finally {
            cliente.close();
        }
    }

    public void GuardarEscala() {
        cliVentas cliente = new cliVentas();
        try {
            String json = "";
            int idEscala = ObtenerValorCelda(tbEscalas, 0);
            EscalaListaPrecioServicio escala = new EscalaListaPrecioServicio();
            escala.setIdUnidadListaPrecioServicio(idUnidad);
            escala.setDesde(ObtenerValorCelda(tbEscalas, 1));
            escala.setHasta(ObtenerValorCelda(tbEscalas, 2));
            escala.setPrecio(ObtenerValorCelda(tbEscalas, 3));
            if (idEscala == 0) {
                json = cliente.RegistrarEscalaListaPrecioServicio(new Gson().toJson(escala));
            } else {
                escala.setIdEscalaListaPrecioServicio(idEscala);
                json = cliente.ActualizarEscalaListaPrecioServicio(new Gson().toJson(escala));
            }
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                AsignarValorCelda(tbEscalas, new Gson().fromJson(resultado[1], int.class), 0);
                VerAdvertencia("GUARDADO CORRECTAMENTE!", frame);
            }
        } catch (Exception e) {
            ControlarExcepcion(e);
        } finally {
            cliente.close();
        }
    }

    public void EliminarItem() {
        int confirmacion = VerConfirmacion(frame);
        if (confirmacion == 0) {
            cliVentas cliente = new cliVentas();
            try {
                int idItem = ObtenerValorCelda(tbItems, 0);
                String json = cliente.EliminarItemListaPrecioServicio(new Gson().toJson(idItem));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    EliminarFila(tbItems);
                }
            } catch (Exception e) {
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
        }
    }

    public void EliminarUnidad() {
        int confirmacion = VerConfirmacion(frame);
        if (confirmacion == 0) {
            cliVentas cliente = new cliVentas();
            try {
                int idUnidad = ObtenerValorCelda(tbUnidades, 0);
                String json = cliente.EliminarUnidadListaPrecioServicio(new Gson().toJson(idUnidad));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    EliminarFila(tbUnidades);
                }
            } catch (Exception e) {
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
        }
    }

    public void EliminarEscala() {
        int confirmacion = VerConfirmacion(frame);
        if (confirmacion == 0) {
            cliVentas cliente = new cliVentas();
            try {
                int idEscala = ObtenerValorCelda(tbEscalas, 0);
                String json = cliente.EliminarEscalaListaPrecioServicio(new Gson().toJson(idEscala));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    EliminarFila(tbEscalas);
                }
            } catch (Exception e) {
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
        }
    }

    Action select_item = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<Servicio> seleccionados = ((lisServicio) evt.getSource()).getSeleccionados();
            if (!seleccionados.isEmpty()) {
                List<ItemListaPrecioServicio> itemsListaPrecio = new ArrayList<>();
                for (Servicio seleccionado : seleccionados) {
                    ItemListaPrecioServicio item = new ItemListaPrecioServicio();
                    item.setIdListaPrecioServicio(getId());
                    item.setIdServicio(seleccionado.getIdServicio());
                    item.setNombreServicio(seleccionado.getDescripcion());
                    itemsListaPrecio.add(item);
                }
                cliVentas cliente = new cliVentas();
                try {
                    String json = cliente.RegistrarItemsListaPrecioServicio(new Gson().toJson(itemsListaPrecio));
                    String[] resultado = new Gson().fromJson(json, String[].class);
                    ItemListaPrecioServicio[] items = new Gson().fromJson(resultado[1], ItemListaPrecioServicio[].class);
                    for (ItemListaPrecioServicio item : items) {
                        AgregarFila(tbItems,
                                new Object[]{
                                    item.getIdItemListaPrecioServicio(),
                                    item.getIdServicio(),
                                    item.getNombreServicio()
                                });
                    }
                } catch (Exception e) {
                    ControlarExcepcion(e);
                } finally {
                    cliente.close();
                }
            }
        }
    };

    Action select_unid = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<ServicioUnidad> seleccionados = ((lisServicioUnidad) evt.getSource()).getSeleccionados();
            if (!seleccionados.isEmpty()) {
                List<UnidadListaPrecioServicio> unidadesListaPrecio = new ArrayList<>();
                for (ServicioUnidad seleccionado : seleccionados) {
                    UnidadListaPrecioServicio unidad = new UnidadListaPrecioServicio();
                    unidad.setIdItemListaPrecioServicio(idItem);
                    unidad.setIdServicioUnidad(seleccionado.getIdServicioUnidad());
                    unidad.setAbreviacionUnidad(seleccionado.getAbreviacionUnidad());
                    unidad.setFactor(seleccionado.getFactor());
                    unidadesListaPrecio.add(unidad);
                }
                cliVentas cliente = new cliVentas();
                try {
                    String json = cliente.RegistrarUnidadesListaPrecioServicio(new Gson().toJson(unidadesListaPrecio));
                    String[] resultado = new Gson().fromJson(json, String[].class);
                    UnidadListaPrecioServicio[] unidades = new Gson().fromJson(resultado[1], UnidadListaPrecioServicio[].class);
                    for (UnidadListaPrecioServicio unidad : unidades) {
                        AgregarFila(tbUnidades,
                                new Object[]{
                                    unidad.getIdUnidadListaPrecioServicio(),
                                    unidad.getIdServicioUnidad(),
                                    unidad.getAbreviacionUnidad(),
                                    unidad.getFactor()
                                });
                    }
                } catch (Exception e) {
                    ControlarExcepcion(e);
                } finally {
                    cliente.close();
                }
            }
        }
    };

    public class swObtenerListaPrecio extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                String json = cliVentas.ObtenerListaPrecioServicio(new Gson().toJson(getId()));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    ListaPrecioServicio listaPrecio = new Gson().fromJson(resultado[1], ListaPrecioServicio.class);
                    txtNombre.setText(listaPrecio.getNombre());
                    chkActivo.setSelected(listaPrecio.isActivo());
                    for (ItemListaPrecioServicio item : listaPrecio.getItems()) {
                        AgregarFila(tbItems,
                                new Object[]{
                                    item.getIdItemListaPrecioServicio(),
                                    item.getIdServicio(),
                                    item.getNombreServicio()
                                });
                    }
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

    public class swGuardarListaPrecioServicio extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliVentas = new cliVentas();
            String json = "";
            try {
                ListaPrecioServicio listaPrecio = new ListaPrecioServicio();
                listaPrecio.setNombre(txtNombre.getText());

                listaPrecio.setActivo(chkActivo.isSelected());
                if (getId() == 0) {
                    json = cliVentas.RegistrarListaPrecioServicio(new Gson().toJson(listaPrecio));
                } else {
                    listaPrecio.setIdListaPrecioServicio(getId());
                    json = cliVentas.ActualizarListaPrecioServicio(new Gson().toJson(listaPrecio));
                }
            } catch (Exception e) {
                OcultarProcesando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliVentas.close();
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
                    ControlarExcepcion(resultado);
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
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        chkActivo = new javax.swing.JCheckBox();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        tpnlItems = new javax.swing.JTabbedPane();
        tabItems = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbItems = new javax.swing.JTable();
        btnNuevoItem = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        tpnlUnidades = new javax.swing.JTabbedPane();
        tabUnidades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUnidades = new javax.swing.JTable();
        btnNuevaUnidad = new javax.swing.JButton();
        btnEliminarUnidad = new javax.swing.JButton();
        tpnlEscalas = new javax.swing.JTabbedPane();
        tabEscalas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbEscalas = new javax.swing.JTable();
        btnNuevaEscala = new javax.swing.JButton();
        btnGuardarEscala = new javax.swing.JButton();
        btnEliminarEscala = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

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

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("LISTA DE PRECIO DE SERVICIO");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        chkActivo.setText("ACTIVO");

        lblNombre.setText("NOMBRE");

        tpnlItems.setBorder(null);

        tabItems.setBackground(java.awt.Color.white);
        tabItems.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDPRODUCTO", "NOMBRE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbItems.setAutoscrolls(false);
        tbItems.setRowHeight(25);
        tbItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbItems);
        if (tbItems.getColumnModel().getColumnCount() > 0) {
            tbItems.getColumnModel().getColumn(0).setMinWidth(0);
            tbItems.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(0).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(1).setMinWidth(0);
            tbItems.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(1).setMaxWidth(0);
        }

        btnNuevoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevoItem.setToolTipText("NUEVO");
        btnNuevoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoItemActionPerformed(evt);
            }
        });

        btnEliminarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarItem.setToolTipText("NUEVO");
        btnEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabItemsLayout = new javax.swing.GroupLayout(tabItems);
        tabItems.setLayout(tabItemsLayout);
        tabItemsLayout.setHorizontalGroup(
            tabItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabItemsLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoItem, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarItem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        tabItemsLayout.setVerticalGroup(
            tabItemsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(tabItemsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnNuevoItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarItem)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        tpnlItems.addTab("SERVICIOS", tabItems);

        tpnlUnidades.setBorder(null);

        tabUnidades.setBackground(java.awt.Color.white);
        tabUnidades.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbUnidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDPRODUCTOUNIDAD", "ABREVIACION", "FACTOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUnidades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbUnidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUnidadesMouseClicked(evt);
            }
        });
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
        btnNuevaUnidad.setToolTipText("NUEVO");
        btnNuevaUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaUnidadActionPerformed(evt);
            }
        });

        btnEliminarUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarUnidad.setToolTipText("NUEVO");
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevaUnidad, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarUnidad, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        tabUnidadesLayout.setVerticalGroup(
            tabUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .addGroup(tabUnidadesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnNuevaUnidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarUnidad)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnlUnidades.addTab("UNIDADES", tabUnidades);

        tpnlEscalas.setBorder(null);

        tabEscalas.setBackground(java.awt.Color.white);
        tabEscalas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbEscalas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DESDE", "HASTA", "PRECIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbEscalas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tbEscalas);
        if (tbEscalas.getColumnModel().getColumnCount() > 0) {
            tbEscalas.getColumnModel().getColumn(0).setMinWidth(0);
            tbEscalas.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbEscalas.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnNuevaEscala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevaEscala.setToolTipText("NUEVO");
        btnNuevaEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaEscalaActionPerformed(evt);
            }
        });

        btnGuardarEscala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/save-16.png"))); // NOI18N
        btnGuardarEscala.setToolTipText("NUEVO");
        btnGuardarEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEscalaActionPerformed(evt);
            }
        });

        btnEliminarEscala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarEscala.setToolTipText("NUEVO");
        btnEliminarEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEscalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tabEscalasLayout = new javax.swing.GroupLayout(tabEscalas);
        tabEscalas.setLayout(tabEscalasLayout);
        tabEscalasLayout.setHorizontalGroup(
            tabEscalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEscalasLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabEscalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarEscala)
                    .addComponent(btnEliminarEscala)
                    .addComponent(btnNuevaEscala))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabEscalasLayout.setVerticalGroup(
            tabEscalasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .addGroup(tabEscalasLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnNuevaEscala)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarEscala)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarEscala)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnlEscalas.addTab("ESCALAS", tabEscalas);

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblNombre)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkActivo)
                        .addGap(14, 14, 14))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tpnlItems)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(tpnlUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tpnlEscalas)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpnlItems, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tpnlUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpnlEscalas, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarListaPrecioServicio().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tbItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemsMouseClicked
        // TODO add your handling code here:
        if (FilaActiva(tbItems)) {
            idItem = ObtenerValorCelda(tbItems, 0);
            idServicio = ObtenerValorCelda(tbItems, 1);
            ObtenerUnidades();
        }
    }//GEN-LAST:event_tbItemsMouseClicked

    private void btnNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemActionPerformed
        // TODO add your handling code here:
        VerModal(new lisServicio(2), select_item);
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbItems)) {
            EliminarItem();
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed

    private void tbUnidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUnidadesMouseClicked
        // TODO add your handling code here:
        if (FilaActiva(tbUnidades)) {
            idUnidad = ObtenerValorCelda(tbUnidades, 0);
            ObtenerEscalas();
        }
    }//GEN-LAST:event_tbUnidadesMouseClicked

    private void btnNuevaUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaUnidadActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbItems)) {
            String filtro = "WHERE ServicioUnidad.idServicio = " + idServicio;
            VerModal(new lisServicioUnidad(filtro), select_unid);
        }
    }//GEN-LAST:event_btnNuevaUnidadActionPerformed

    private void btnEliminarUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUnidadActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbUnidades)) {
            EliminarUnidad();
        }
    }//GEN-LAST:event_btnEliminarUnidadActionPerformed

    private void btnNuevaEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaEscalaActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbUnidades)) {
            AgregarFila(tbEscalas, new Object[]{0, 0, 0, 0.0});
        }
    }//GEN-LAST:event_btnNuevaEscalaActionPerformed

    private void btnGuardarEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEscalaActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbEscalas)) {
            GuardarEscala();
        }
    }//GEN-LAST:event_btnGuardarEscalaActionPerformed

    private void btnEliminarEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEscalaActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbEscalas)) {
            EliminarEscala();
        }
    }//GEN-LAST:event_btnEliminarEscalaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarEscala;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnEliminarUnidad;
    private javax.swing.JButton btnGuardarEscala;
    private javax.swing.JButton btnNuevaEscala;
    private javax.swing.JButton btnNuevaUnidad;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel tabEscalas;
    private javax.swing.JPanel tabItems;
    private javax.swing.JPanel tabUnidades;
    private javax.swing.JTable tbEscalas;
    private javax.swing.JTable tbItems;
    private javax.swing.JTable tbUnidades;
    private javax.swing.JTabbedPane tpnlEscalas;
    private javax.swing.JTabbedPane tpnlItems;
    private javax.swing.JTabbedPane tpnlUnidades;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
