package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.inventarios.clases.Producto;
import com.sge.modulos.inventarios.formularios.lisProducto;
import com.sge.modulos.ventas.clases.ItemPlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.clases.PlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.clases.Servicio;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author elderson
 */
public class regPlantillaSC extends frameBase<PlantillaSolicitudCotizacion> {

    /**
     * Creates new form regPlantillaSC
     *
     * @param id
     */
    public regPlantillaSC(int id) {
        initComponents();
        setId(id);
    }

    @Override
    public void Init() {
        if (getId() == 0) {
            lblTitulo.setText("NUEVA " + lblTitulo.getText());
            setEntidad(new PlantillaSolicitudCotizacion());
        } else {
            lblTitulo.setText("EDITAR " + lblTitulo.getText());
            new swObtenerPlantilla().execute();
        }
        OcultarControl(tpnlItems);
    }

    private ItemPlantillaSolicitudCotizacion item;

    Action sele_serv = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Servicio seleccionado = ((lisServicio) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schServicioImpresion.asingValues(seleccionado.getIdServicio(), seleccionado.getDescripcion());
                item.setIdServicioImpresion(seleccionado.getIdServicio());
                item.setNombreServicioImpresion(seleccionado.getDescripcion());
            }
        }
    };

    Action sele_mate = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Producto seleccionado = ((lisProducto) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMaterial.asingValues(seleccionado.getIdProducto(), seleccionado.getDescripcion());
                item.setIdMaterial(seleccionado.getIdProducto());
                item.setCodigoMaterial(seleccionado.getCodigo());
                item.setNombreMaterial(seleccionado.getDescripcion());
                item.setAltoMaterial(seleccionado.getAlto());
                item.setLargoMaterial(seleccionado.getLargo());
                item.setIdUnidadMaterial(seleccionado.getIdUnidadBase());
                item.setAbreviacionUnidadMaterial(seleccionado.getAbreviacionUnidadBase());
                item.setFactorUnidadMaterial(seleccionado.getFactorUnidadBase());
            }
        }
    };

    public class swObtenerPlantilla extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                String json = cliVentas.ObtenerPlantillaSolicitudCotizacion(new Gson().toJson(getId()));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    setEntidad(new Gson().fromJson(resultado[1], PlantillaSolicitudCotizacion.class));
                    txtNombre.setText(getEntidad().getNombre());
                    cboLineaProduccion.setSelectedItem(getEntidad().getLineaProduccion());
                    chkActivo.setSelected(getEntidad().isActivo());
                    for (ItemPlantillaSolicitudCotizacion itemPlantilla : getEntidad().getItems()) {
                        AgregarElemento(lisItems, itemPlantilla);
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

    public List<ItemPlantillaSolicitudCotizacion> getItems() {
        for (ItemPlantillaSolicitudCotizacion item : getEntidad().getItems()) {
            if (item.getIdItemPlantillaSolicitudCotizacion() == 0) {
                item.setAgregar(true);
            } else if (!item.isEliminar()) {
                item.setActualizar(true);
            }
        }
        return getEntidad().getItems();
    }

    public class swGuardarPlantilla extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliVentas = new cliVentas();
            String json = "";
            try {
                PlantillaSolicitudCotizacion plantilla = new PlantillaSolicitudCotizacion();
                plantilla.setNombre(txtNombre.getText());
                plantilla.setLineaProduccion(cboLineaProduccion.getSelectedItem().toString());
                plantilla.setActivo(chkActivo.isSelected());
                plantilla.setItems(getItems());
                if (getId() == 0) {
                    json = cliVentas.RegistrarPlantillaSolicitudCotizacion(new Gson().toJson(plantilla));
                } else {
                    plantilla.setIdPlantillaSolicitudCotizacion(getId());
                    json = cliVentas.ActualizarPlantillaSolicitudCotizacion(new Gson().toJson(plantilla));
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

    private void AsignarControlesItem() {
        txtNombreItem.setText(this.item.getNombre());
        chkServicioImpresion.setSelected(this.item.isServicioImpresion());
        schServicioImpresion.setEnabled(this.item.isServicioImpresion());
        schServicioImpresion.asingValues(this.item.getIdServicioImpresion(), this.item.getNombreServicioImpresion());
        chkMaterial.setSelected(this.item.isMaterial());
        schMaterial.setEnabled(this.item.isMaterial());
        schMaterial.asingValues(this.item.getIdMaterial(), this.item.getNombreMaterial());
        chkTipoUnidad.setSelected(this.item.isTipoUnidad());
        cboTipoUnidad.setEnabled(this.item.isTipoUnidad());
        cboTipoUnidad.setSelectedItem(this.item.getNombreTipoUnidad());
        chkMedidaAbierta.setSelected(this.item.isMedidaAbierta());
        cboUnidadMedidaAbierta.setEnabled(this.item.isMedidaAbierta());
        cboUnidadMedidaAbierta.setSelectedItem(this.item.getUnidadMedidaAbierta());
        chkMedidaCerrada.setSelected(this.item.isMedidaCerrada());
        chkTiraRetira.setSelected(this.item.isTiraRetira());
        chkGrafico.setSelected(this.item.isGrafico());
        chkFondo.setSelected(this.item.isFondo());
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
        lblLineaProduccion = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        cboLineaProduccion = new javax.swing.JComboBox();
        tpnlItems = new javax.swing.JTabbedPane();
        pnlItem = new javax.swing.JPanel();
        lblNombreItem = new javax.swing.JLabel();
        txtNombreItem = new javax.swing.JTextField();
        chkServicioImpresion = new javax.swing.JCheckBox();
        schServicioImpresion = new com.sge.base.controles.JSearch();
        schMaterial = new com.sge.base.controles.JSearch();
        chkMaterial = new javax.swing.JCheckBox();
        chkTipoUnidad = new javax.swing.JCheckBox();
        cboTipoUnidad = new javax.swing.JComboBox();
        chkMedidaAbierta = new javax.swing.JCheckBox();
        cboUnidadMedidaAbierta = new javax.swing.JComboBox();
        chkTiraRetira = new javax.swing.JCheckBox();
        chkMedidaCerrada = new javax.swing.JCheckBox();
        chkGrafico = new javax.swing.JCheckBox();
        chkFondo = new javax.swing.JCheckBox();
        btnNuevoItem = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lisItems = new javax.swing.JList();

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
        lblTitulo.setText("PLANTILLA DE SOLICITUD DE COTIZACIÓN");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(383, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        chkActivo.setText("ACTIVO");

        lblLineaProduccion.setText("L. PRODUCCIÓN");

        lblNombre.setText("NOMBRE");

        cboLineaProduccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PUBLICIDAD", "OFFSET", "DIGITAL", "OTROS" }));

        pnlItem.setBackground(java.awt.Color.white);
        pnlItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombreItem.setText("NOMBRE");

        txtNombreItem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtNombreItemChange();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                txtNombreItemChange();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                txtNombreItemChange();
            }

        });

        chkServicioImpresion.setText("IMPRESIÓN");
        chkServicioImpresion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkServicioImpresionStateChanged(evt);
            }
        });

        schServicioImpresion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schServicioImpresionSearch();
            }
            @Override
            public void Clear(){
                schServicioImpresionClear();
            }
        });

        schMaterial.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMaterialSearch();
            }
            @Override
            public void Clear(){
                schMaterialClear();
            }
        });

        chkMaterial.setText("MATERIAL");
        chkMaterial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMaterialStateChanged(evt);
            }
        });

        chkTipoUnidad.setText("TIPO UNIDAD");
        chkTipoUnidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkTipoUnidadStateChanged(evt);
            }
        });

        cboTipoUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UNIDADES", "PAGINAS", "JUEGOS", "ORIGINALES", "OTROS" }));
        cboTipoUnidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTipoUnidadItemStateChanged(evt);
            }
        });

        chkMedidaAbierta.setText("MEDIDA ABIERTA");
        chkMedidaAbierta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMedidaAbiertaStateChanged(evt);
            }
        });

        cboUnidadMedidaAbierta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CM", "MT" }));
        cboUnidadMedidaAbierta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboUnidadMedidaAbiertaItemStateChanged(evt);
            }
        });

        chkTiraRetira.setText("TIRA Y RETIRA");
        chkTiraRetira.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkTiraRetiraStateChanged(evt);
            }
        });

        chkMedidaCerrada.setText("MEDIDA CERRADA");
        chkMedidaCerrada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMedidaCerradaStateChanged(evt);
            }
        });

        chkGrafico.setText("GRÁFICO");
        chkGrafico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkGraficoStateChanged(evt);
            }
        });

        chkFondo.setText("FONDO");
        chkFondo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkFondoStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlItemLayout = new javax.swing.GroupLayout(pnlItem);
        pnlItem.setLayout(pnlItemLayout);
        pnlItemLayout.setHorizontalGroup(
            pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(lblNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(chkTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(cboTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(chkServicioImpresion, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(chkMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemLayout.createSequentialGroup()
                                .addComponent(schServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(chkTiraRetira, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(chkFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(chkMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(cboUnidadMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(88, 88, 88)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkGrafico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        pnlItemLayout.setVerticalGroup(
            pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(chkTipoUnidad))
                    .addComponent(cboTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboUnidadMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkMedidaAbierta)
                            .addComponent(chkMedidaCerrada))))
                .addGap(6, 6, 6)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkTiraRetira)
                    .addComponent(chkFondo)
                    .addComponent(chkGrafico)))
        );

        tpnlItems.addTab("ITEM", pnlItem);

        btnNuevoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoItemActionPerformed(evt);
            }
        });

        btnEliminarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemActionPerformed(evt);
            }
        });

        lisItems.setModel(new DefaultListModel());
        lisItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lisItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lisItemsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lisItems);

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addGap(27, 27, 27)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lblLineaProduccion)
                        .addGap(41, 41, 41)
                        .addComponent(cboLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkActivo))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tpnlItems))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(btnNuevoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnEliminarItem, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(428, 428, 428)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblNombre))
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblLineaProduccion))
                    .addComponent(cboLineaProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(chkActivo)))
                .addGap(12, 12, 12)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpnlItems, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoItem)
                    .addComponent(btnEliminarItem)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAceptar)
                            .addComponent(btnCancelar)))))
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

    private void txtNombreItemChange(){
        this.item.setNombre(txtNombreItem.getText());
        lisItems.updateUI();
        tpnlItems.setTitleAt(0, this.item.getNombre());
    }
    
    private void schServicioImpresionSearch() {
        String filtro = "WHERE Servicio.servicioImpresion = TRUE";
        VerModal(new lisServicio(1, filtro), sele_serv);
    }

    private void schServicioImpresionClear(){
        this.item.setIdServicioImpresion(0);
        this.item.setNombreServicioImpresion("");
    }
    
    private void schMaterialSearch() {
        VerModal(new lisProducto(1), sele_mate);
    }

    private void schMaterialClear() {
        this.item.setIdMaterial(0);
        this.item.setCodigoMaterial("");
        this.item.setNombreMaterial("");
        this.item.setAltoMaterial(0);
        this.item.setLargoMaterial(0);
        this.item.setIdUnidadMaterial(0);
        this.item.setAbreviacionUnidadMaterial("");
        this.item.setFactorUnidadMaterial(0);
    }

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarPlantilla().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkServicioImpresionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkServicioImpresionStateChanged
        // TODO add your handling code here:
        this.item.setServicioImpresion(chkServicioImpresion.isSelected());
        schServicioImpresion.setEnabled(chkServicioImpresion.isSelected());
    }//GEN-LAST:event_chkServicioImpresionStateChanged

    private void chkMaterialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMaterialStateChanged
        // TODO add your handling code here:
        this.item.setMaterial(chkMaterial.isSelected());
        schMaterial.setEnabled(chkMaterial.isSelected());
    }//GEN-LAST:event_chkMaterialStateChanged

    private void chkTipoUnidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkTipoUnidadStateChanged
        // TODO add your handling code here:
        this.item.setTipoUnidad(chkTipoUnidad.isSelected());
        this.item.setNombreTipoUnidad(cboTipoUnidad.getSelectedItem().toString());
        cboTipoUnidad.setEnabled(chkTipoUnidad.isSelected());
    }//GEN-LAST:event_chkTipoUnidadStateChanged

    private void chkMedidaAbiertaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMedidaAbiertaStateChanged
        // TODO add your handling code here:
        this.item.setMedidaAbierta(chkMedidaAbierta.isSelected());
        this.item.setUnidadMedidaAbierta(cboUnidadMedidaAbierta.getSelectedItem().toString());
        cboUnidadMedidaAbierta.setEnabled(chkMedidaAbierta.isSelected());
    }//GEN-LAST:event_chkMedidaAbiertaStateChanged

    private void btnNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemActionPerformed
        // TODO add your handling code here:
        ItemPlantillaSolicitudCotizacion item = new ItemPlantillaSolicitudCotizacion();
        item.setNombre("ITEM " + (getEntidad().getItems().size() + 1));
        item.setNombreTipoUnidad("");
        item.setUnidadMedidaAbierta("");
        getEntidad().getItems().add(item);
        AgregarElemento(lisItems, item);
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        ItemPlantillaSolicitudCotizacion itemPlantilla = (ItemPlantillaSolicitudCotizacion) lisItems.getSelectedValue();
        if (getId() == 0) {
            getEntidad().getItems().remove(itemPlantilla);
        } else {
            itemPlantilla.setEliminar(true);
        }
        EliminarElementoActivo(lisItems);
        if(lisItems.getModel().getSize() == 0){
            OcultarControl(tpnlItems);
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed

    private void cboTipoUnidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTipoUnidadItemStateChanged
        // TODO add your handling code here:
        this.item.setNombreTipoUnidad(cboTipoUnidad.getSelectedItem().toString());
    }//GEN-LAST:event_cboTipoUnidadItemStateChanged

    private void cboUnidadMedidaAbiertaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboUnidadMedidaAbiertaItemStateChanged
        // TODO add your handling code here:
        this.item.setUnidadMedidaAbierta(cboUnidadMedidaAbierta.getSelectedItem().toString());
    }//GEN-LAST:event_cboUnidadMedidaAbiertaItemStateChanged

    private void chkMedidaCerradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMedidaCerradaStateChanged
        // TODO add your handling code here:
        this.item.setMedidaCerrada(chkMedidaCerrada.isSelected());
    }//GEN-LAST:event_chkMedidaCerradaStateChanged

    private void chkTiraRetiraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkTiraRetiraStateChanged
        // TODO add your handling code here:
        this.item.setTiraRetira(chkTiraRetira.isSelected());
    }//GEN-LAST:event_chkTiraRetiraStateChanged

    private void chkGraficoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkGraficoStateChanged
        // TODO add your handling code here:
        this.item.setGrafico(chkGrafico.isSelected());
    }//GEN-LAST:event_chkGraficoStateChanged

    private void chkFondoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkFondoStateChanged
        // TODO add your handling code here:
        this.item.setFondo(chkFondo.isSelected());
    }//GEN-LAST:event_chkFondoStateChanged

    private void lisItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lisItemsMouseClicked
        // TODO add your handling code here:
        if (lisItems.getSelectedValue() != null) {
            this.item = (ItemPlantillaSolicitudCotizacion) lisItems.getSelectedValue();
            AsignarControlesItem();
            AsignarTitulo(tpnlItems, 0, this.item.getNombre());
            VerControl(tpnlItems);
        }
    }//GEN-LAST:event_lisItemsMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JComboBox cboLineaProduccion;
    private javax.swing.JComboBox cboTipoUnidad;
    private javax.swing.JComboBox cboUnidadMedidaAbierta;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JCheckBox chkFondo;
    private javax.swing.JCheckBox chkGrafico;
    private javax.swing.JCheckBox chkMaterial;
    private javax.swing.JCheckBox chkMedidaAbierta;
    private javax.swing.JCheckBox chkMedidaCerrada;
    private javax.swing.JCheckBox chkServicioImpresion;
    private javax.swing.JCheckBox chkTipoUnidad;
    private javax.swing.JCheckBox chkTiraRetira;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblLineaProduccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreItem;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList lisItems;
    private javax.swing.JPanel pnlItem;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schMaterial;
    private com.sge.base.controles.JSearch schServicioImpresion;
    private javax.swing.JTabbedPane tpnlItems;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreItem;
    // End of variables declaration//GEN-END:variables
}
