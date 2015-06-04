package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Empleado;
import com.sge.modulos.administracion.clases.Moneda;
import com.sge.modulos.administracion.clases.Numeracion;
import com.sge.modulos.administracion.clases.ValorDefinido;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import com.sge.modulos.administracion.formularios.lisEmpleado;
import com.sge.modulos.administracion.formularios.lisMoneda;
import com.sge.modulos.administracion.formularios.lisNumeracion;
import com.sge.modulos.inventarios.clases.Almacen;
import com.sge.modulos.inventarios.clases.SalidaInventario;
import com.sge.modulos.inventarios.clases.ItemSalidaInventario;
import com.sge.modulos.inventarios.clases.ProductoUnidad;
import com.sge.modulos.inventarios.clases.SeleccionProducto;
import com.sge.modulos.inventarios.cliente.cliInventarios;
import com.sge.modulos.ventas.clases.Cliente;
import com.sge.modulos.ventas.formularios.lisCliente;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regSalidaInventario extends frameBase<SalidaInventario> {

    /**
     * Creates new form regSalidaInventario
     */
    public regSalidaInventario() {
        initComponents();
    }

    public regSalidaInventario(int id) {
        initComponents();
        Init(id);
    }

    public regSalidaInventario(ValorDefinido valorDefinido) {
        initComponents();
        super.Init(valorDefinido);
    }

    private int id;

    public void Init(int id) {
        this.id = id;
        if (this.id == 0) {
            lblTitulo.setText("NUEVA " + lblTitulo.getText());
            new swObtenerValoresDefinidos().execute();
        } else {
            lblTitulo.setText("VER " + lblTitulo.getText());
            OcultarControles();
            new swObtenerSalidaInventario().execute();
        }
    }

    Action sele_unid = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            ProductoUnidad seleccionado = ((lisProductoUnidad) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                double cantidad = ObtenerValorCelda(tbItems, 7);
                double cantidadMaxima = ObtenerValorCelda(tbItems, 10);
                if ((cantidad * seleccionado.getFactor()) > cantidadMaxima) {
                    VerAdvertencia("STOCK INSUFICIENTE : " + cantidadMaxima, frame);
                } else {
                    double precioBase = ObtenerValorCelda(tbItems, 12);
                    AsignarValorCelda(tbItems, seleccionado.getIdProductoUnidad(), 4);
                    AsignarValorCelda(tbItems, seleccionado.getFactor(), 5);
                    AsignarValorCelda(tbItems, seleccionado.getAbreviacionUnidad(), 6);
                    AsignarValorCelda(tbItems, precioBase * seleccionado.getFactor(), 8);
                }
            }
        }
    };

    Action clic_unid = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idProducto = ObtenerValorCelda(tbItems, 1);
            String filtro = "WHERE ProductoUnidad.idProducto = " + idProducto;
            VerModal(new lisProductoUnidad(1, filtro), sele_unid);
        }
    };

    private String itemsSinStock = "";
    
    public List<ItemSalidaInventario> getItemsConStock(List<ItemSalidaInventario> items) {
        cliInventarios cliente = new cliInventarios();
        ItemSalidaInventario[] itemsConStock = null;
        try {
            String json = cliente.ObtenerStockFisicoItems(new Gson().toJson(items));
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                itemsConStock = new Gson().fromJson(resultado[1], ItemSalidaInventario[].class);
                for (ItemSalidaInventario item : itemsConStock) {
                    if (item.getCantidad() > item.getCantidadMaxima()) {
                        item.setCantidad(0);
                        itemsSinStock += String.format("PRODUCTO %s SIN STOCK FISICO SUFICIENTE : %s \n", item.getDescripcionProducto(), item.getCantidadMaxima());
                    }
                }
            }
        } catch (Exception e) {
            cliente.close();
            throw e;
        }
        return Arrays.asList(itemsConStock);
    }

    public void VerItemsSinStock(){
        if (!itemsSinStock.isEmpty()) {
            VerAdvertencia(itemsSinStock, frame);
        }
    }
    
    public void AsignarControlesConStocks(){
        schNumeracion.asingValues(getEntidad().getIdNumeracion(), getEntidad().getDescripcionNumeracion());
        schCliente.asingValues(getEntidad().getIdCliente(), getEntidad().getRazonSocialCliente());
        txtNumero.setEnabled(getEntidad().isNumeracionManual());
        txtNumero.setText(getEntidad().getNumero());
        schResponsable.asingValues(getEntidad().getIdResponsable(), getEntidad().getNombreResponsable());
        txtFechaCreacion.setValue(getEntidad().getFechaCreacion());
        schAlmacen.asingValues(getEntidad().getIdAlmacen(), getEntidad().getDescripcionAlmacen());
        schMoneda.asingValues(getEntidad().getIdMoneda(), getEntidad().getSimboloMoneda());
        txtOT.setText(getEntidad().getNumeroOrdenTrabajo());
        txtSubTotal.setValue(getEntidad().getSubTotal());
        txtImpuesto.setValue(getEntidad().getMontoImpuesto());
        lblPorcentajeImpuesto.setText(String.format("(%s%s)", getEntidad().getPorcentajeImpuesto(), "%"));
        txtTotal.setValue(getEntidad().getTotal());
        getEntidad().setItems(getItemsConStock(getEntidad().getItems()));
        for (ItemSalidaInventario item : getEntidad().getItems()) {
            AgregarFila(tbItems,
                    new Object[]{
                        item.getIdItemSalidaInventario(),
                        item.getIdProducto(),
                        item.getCodigoProducto(),
                        item.getDescripcionProducto(),
                        item.getIdUnidad(),
                        item.getFactor(),
                        item.getAbreviacionUnidad(),
                        item.getCantidad(),
                        item.getPrecio(),
                        item.getTotal(),
                        item.getCantidadMaxima(),
                        item.getCantidad(),
                        item.getPrecio()
                    });
        }
        AgregarBoton(tbItems, clic_unid, 6);
        AgregarEventoChange(tbItems, change_item);
    }
    
    @Override
    public void AsignarControles() {
        schNumeracion.asingValues(getEntidad().getIdNumeracion(), getEntidad().getDescripcionNumeracion());
        schCliente.asingValues(getEntidad().getIdCliente(), getEntidad().getRazonSocialCliente());
        txtNumero.setEnabled(getEntidad().isNumeracionManual());
        txtNumero.setText(getEntidad().getNumero());
        schResponsable.asingValues(getEntidad().getIdResponsable(), getEntidad().getNombreResponsable());
        txtFechaCreacion.setValue(getEntidad().getFechaCreacion());
        schAlmacen.asingValues(getEntidad().getIdAlmacen(), getEntidad().getDescripcionAlmacen());
        schMoneda.asingValues(getEntidad().getIdMoneda(), getEntidad().getSimboloMoneda());
        txtOT.setText(getEntidad().getNumeroOrdenTrabajo());
        txtSubTotal.setValue(getEntidad().getSubTotal());
        txtImpuesto.setValue(getEntidad().getMontoImpuesto());
        lblPorcentajeImpuesto.setText(String.format("(%s%s)", getEntidad().getPorcentajeImpuesto(), "%"));
        txtTotal.setValue(getEntidad().getTotal());
        for (ItemSalidaInventario item : getEntidad().getItems()) {
            AgregarFila(tbItems,
                    new Object[]{
                        item.getIdItemSalidaInventario(),
                        item.getIdProducto(),
                        item.getCodigoProducto(),
                        item.getDescripcionProducto(),
                        item.getIdUnidad(),
                        item.getFactor(),
                        item.getAbreviacionUnidad(),
                        item.getCantidad(),
                        item.getPrecio(),
                        item.getTotal(),
                        item.getCantidadMaxima(),
                        item.getCantidad(),
                        item.getPrecio()
                    });
        }
    }

    @Override
    public void OcultarControles() {
        OcultarControl(btnNuevoItem);
        OcultarControl(btnEliminarItem);
    }

    public void CalcularTotales() {
        double subTotal = 0.0;
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            double totalItem = ObtenerValorCelda(tbItems, i, 9);
            subTotal += totalItem;
        }

        getEntidad().setSubTotal(subTotal);
        double montoImpuesto = subTotal * (getEntidad().getPorcentajeImpuesto() / 100);
        getEntidad().setMontoImpuesto(montoImpuesto);
        double total = subTotal + montoImpuesto;
        getEntidad().setTotal(total);

        txtSubTotal.setValue(subTotal);
        txtImpuesto.setValue(montoImpuesto);
        txtTotal.setValue(total);
    }

    public List<ItemSalidaInventario> getItems() {
        List<ItemSalidaInventario> items = new ArrayList<>();
        for (int i = 0; i < tbItems.getRowCount(); i++) {
            ItemSalidaInventario item = new ItemSalidaInventario();
            item.setIdProducto(ObtenerValorCelda(tbItems, i, 1));
            item.setCodigoProducto(ObtenerValorCelda(tbItems, i, 2));
            item.setDescripcionProducto(ObtenerValorCelda(tbItems, i, 3));
            item.setIdUnidad(ObtenerValorCelda(tbItems, i, 4));
            item.setFactor(ObtenerValorCelda(tbItems, i, 5));
            item.setAbreviacionUnidad(ObtenerValorCelda(tbItems, i, 6));
            item.setCantidad(ObtenerValorCelda(tbItems, i, 7));
            item.setPrecio(ObtenerValorCelda(tbItems, i, 8));
            item.setTotal(ObtenerValorCelda(tbItems, i, 9));
            items.add(item);
        }
        return items;
    }

    Action change_item = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] celda = (int[]) e.getSource();
            switch (celda[1]) {
                case 7:
                    double cantidadMaxima7 = ObtenerValorCelda(tbItems, celda[0], 10);
                    double cantidad7 = ObtenerValorCelda(tbItems, celda[0], celda[1]);
                    int factor7 = ObtenerValorCelda(tbItems, celda[0], 5);
                    if ((cantidad7 * factor7) > cantidadMaxima7) {
                        VerAdvertencia("STOCK INSUFICIENTE : " + cantidadMaxima7, frame);
                        double cantidadAnterior7 = ObtenerValorCelda(tbItems, celda[0], 11);
                        AsignarValorCelda(tbItems, cantidadAnterior7, celda[0], celda[1]);
                    } else {
                        double precio7 = ObtenerValorCelda(tbItems, celda[0], 8);
                        AsignarValorCelda(tbItems, cantidad7 * precio7, celda[0], 9);
                        AsignarValorCelda(tbItems, cantidad7, celda[0], 11);
                        CalcularTotales();
                    }
                    break;
                case 8:
                    double cantidad8 = ObtenerValorCelda(tbItems, celda[0], 7);
                    double precio8 = ObtenerValorCelda(tbItems, celda[0], celda[1]);
                    AsignarValorCelda(tbItems, cantidad8 * precio8, celda[0], 9);
                    CalcularTotales();
                    break;
            }
        }
    };

    Action select_item = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<SeleccionProducto> seleccionados = ((selProducto) evt.getSource()).getSeleccionados();
            if (!seleccionados.isEmpty()) {
                for (SeleccionProducto seleccionado : seleccionados) {
                    AgregarFila(tbItems,
                            new Object[]{
                                0,
                                seleccionado.getIdProducto(),
                                seleccionado.getCodigoProducto(),
                                seleccionado.getDescripcionProducto(),
                                seleccionado.getIdUnidadBase(),
                                seleccionado.getFactorUnidadBase(),
                                seleccionado.getAbreviacionUnidadBase(),
                                1.0,
                                seleccionado.getPrecio(),
                                seleccionado.getPrecio(),
                                seleccionado.getStock(),
                                1.0,
                                seleccionado.getPrecio()
                            });
                }
                AgregarBoton(tbItems, clic_unid, 6);
                AgregarEventoChange(tbItems, change_item);
                CalcularTotales();
            }
        }
    };

    Action select_nume = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Numeracion seleccionado = ((lisNumeracion) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                cliAdministracion cliente = new cliAdministracion();
                String json = cliente.ObtenerNumeracion(new Gson().toJson(seleccionado.getIdNumeracion()));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    Numeracion numeracion = new Gson().fromJson(resultado[1], Numeracion.class);
                    schNumeracion.asingValues(numeracion.getIdNumeracion(), numeracion.getDescripcion());
                    getEntidad().setNumeracionManual(numeracion.isManual());
                    txtNumero.setEnabled(numeracion.isManual());
                    if (numeracion.isTieneImpuesto()) {
                        getEntidad().setPorcentajeImpuesto(numeracion.getPorcentajeImpuesto());
                        lblPorcentajeImpuesto.setText(String.format("(%s%s)", numeracion.getPorcentajeImpuesto(), "%"));
                    }
                }
            }
        }
    };

    Action select_clie = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Cliente seleccionado = ((lisCliente) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schCliente.asingValues(seleccionado.getIdCliente(), seleccionado.getRazonSocial());
            }
        }
    };

    Action select_resp = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Empleado seleccionado = ((lisEmpleado) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schResponsable.asingValues(seleccionado.getIdEmpleado(), seleccionado.getNombre());
            }
        }
    };

    Action select_alma = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Almacen seleccionado = ((lisAlmacen) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schAlmacen.asingValues(seleccionado.getIdAlmacen(), seleccionado.getDescripcion());
            }
        }
    };

    Action select_mone = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Moneda seleccionado = ((lisMoneda) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMoneda.asingValues(seleccionado.getIdMoneda(), seleccionado.getSimbolo());
            }
        }
    };

    public class swObtenerValoresDefinidos extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {
                String json = cliente.ObtenerValorDefinidoPorUsuarioYEntidad(new Gson().toJson(new int[]{getUsuario().getIdUsuario(), 2}));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    if (resultado[2].isEmpty()) {
                        setEntidad(new SalidaInventario());
                    } else {
                        setEntidad(new Gson().fromJson(resultado[2], SalidaInventario.class));
                    }
                    getEntidad().setFechaCreacion(new Gson().fromJson(resultado[1], Date.class));
                    AsignarControles();
                }
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
            return null;
        }

        @Override
        protected void done() {
            OcultarCargando(frame);
        }
    }

    public class swObtenerSalidaInventario extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerSalidaInventario(new Gson().toJson(id));
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
                    setEntidad(new Gson().fromJson(resultado[1], SalidaInventario.class));
                    AsignarControles();
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    private void AsignarValores() {
        getEntidad().setNumero(txtNumero.getText());
        getEntidad().setIdNumeracion(schNumeracion.getId());
        getEntidad().setDescripcionNumeracion(schNumeracion.getText());
        getEntidad().setIdCliente(schCliente.getId());
        getEntidad().setRazonSocialCliente(schCliente.getText());
        getEntidad().setIdResponsable(schResponsable.getId());
        getEntidad().setNombreResponsable(schResponsable.getText());
        getEntidad().setIdAlmacen(schAlmacen.getId());
        getEntidad().setDescripcionAlmacen(schAlmacen.getText());
        getEntidad().setIdMoneda(schMoneda.getId());
        getEntidad().setSimboloMoneda(schMoneda.getText());
        getEntidad().setItems(getItems());
    }

    public class swGuardarSalidaInventario extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                AsignarValores();
                json = cliente.RegistrarSalidaInventario(new Gson().toJson(getEntidad()));
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
                    setClosed(true);
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

    public void Aceptar() {
        if (super.isFromJson()) {
            AsignarValores();
            setJson(new Gson().toJson(super.getEntidad()));
            Cerrar();
        } else {
            if (this.id == 0) {
                new swGuardarSalidaInventario().execute();
            } else {
                Cerrar();
            }
        }
    }

    public void Cancelar() {
        Cerrar();
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
        lblCliente = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlUnidades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbItems = new javax.swing.JTable();
        btnNuevoItem = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblMoneda = new javax.swing.JLabel();
        lblAlmacen = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JFormattedTextField();
        lblFechaCreacion = new javax.swing.JLabel();
        lblSubTotal = new javax.swing.JLabel();
        lblImpuesto = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JFormattedTextField();
        txtTotal = new javax.swing.JFormattedTextField();
        txtImpuesto = new javax.swing.JFormattedTextField();
        schAlmacen = new com.sge.base.controles.JSearch();
        schCliente = new com.sge.base.controles.JSearch();
        schResponsable = new com.sge.base.controles.JSearch();
        schMoneda = new com.sge.base.controles.JSearch();
        lblPorcentajeImpuesto = new javax.swing.JLabel();
        schNumeracion = new com.sge.base.controles.JSearch();
        lblOT = new javax.swing.JLabel();
        txtOT = new javax.swing.JTextField();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        lblCliente.setText("CLIENTE");

        lblNombre.setText("RESPONSABLE");

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

        pnlUnidades.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDITEM", "IDPRODUCTO", "CODIGO", "PRODUCTO", "IDUNIDAD", "FACTOR", "UNIDAD", "CANTIDAD", "PRECIO", "TOTAL", "CANTIDADMAXIMA", "CANTIDAANTERIOR", "PRECIOBASE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false, false, true, true, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbItems.setRowHeight(25);
        jScrollPane2.setViewportView(tbItems);
        if (tbItems.getColumnModel().getColumnCount() > 0) {
            tbItems.getColumnModel().getColumn(0).setMinWidth(0);
            tbItems.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(0).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(1).setMinWidth(0);
            tbItems.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(1).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(3).setPreferredWidth(230);
            tbItems.getColumnModel().getColumn(4).setMinWidth(0);
            tbItems.getColumnModel().getColumn(4).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(4).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(5).setMinWidth(0);
            tbItems.getColumnModel().getColumn(5).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(5).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(10).setMinWidth(0);
            tbItems.getColumnModel().getColumn(10).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(10).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(11).setMinWidth(0);
            tbItems.getColumnModel().getColumn(11).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(11).setMaxWidth(0);
            tbItems.getColumnModel().getColumn(12).setMinWidth(0);
            tbItems.getColumnModel().getColumn(12).setPreferredWidth(0);
            tbItems.getColumnModel().getColumn(12).setMaxWidth(0);
        }

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

        javax.swing.GroupLayout pnlUnidadesLayout = new javax.swing.GroupLayout(pnlUnidades);
        pnlUnidades.setLayout(pnlUnidadesLayout);
        pnlUnidadesLayout.setHorizontalGroup(
            pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUnidadesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevoItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUnidadesLayout.setVerticalGroup(
            pnlUnidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUnidadesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnNuevoItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarItem)
                .addContainerGap(136, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ITEMS", pnlUnidades);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("SALIDA DE  INVENTARIO");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblNumero.setText("N°");

        lblMoneda.setText("MONEDA");

        lblAlmacen.setText("ALMACEN");

        txtFechaCreacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        lblFechaCreacion.setText("F. CREACION");

        lblSubTotal.setText("SUBTOTAL");

        lblImpuesto.setText("IMPUESTO");

        lblTotal.setText("TOTAL");

        txtSubTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtImpuesto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtImpuesto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        schAlmacen.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schAlmacenSearch();
            }
            @Override
            public void Clear(){
            }
        });

        schCliente.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schClienteSearch();
            }
            @Override
            public void Clear(){
            }
        });

        schResponsable.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schResponsableSearch();
            }
            @Override
            public void Clear(){
            }
        });

        schMoneda.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMonedaSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblPorcentajeImpuesto.setText("(%0)");

        schNumeracion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schNumeracionSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblOT.setText("O.T");

        txtOT.setEditable(false);

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                                .addGap(32, 32, 32)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(schCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(schAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(schResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNumero, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMoneda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOT)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFechaCreacion))
                            .addComponent(schNumeracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaCreacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(65, 65, 65))
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                        .addComponent(lblPorcentajeImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtSubTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(25, 25, 25))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(schCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schNumeracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNombre)
                                .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblFechaCreacion))
                            .addComponent(schResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(schAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblOT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSubTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPorcentajeImpuesto)
                    .addComponent(lblImpuesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoItemActionPerformed
        // TODO add your handling code here:
        if (schAlmacen.getId() > 0) {
            VerModal(new selProducto(2, schAlmacen.getId()), select_item);
        }
    }//GEN-LAST:event_btnNuevoItemActionPerformed

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbItems)) {
            EliminarFila(tbItems);
            CalcularTotales();
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed

    private void schNumeracionSearch() {
        String filtro = "WHERE Numeracion.idEntidad = 2";
        VerModal(new lisNumeracion(1, filtro), select_nume);
    }

    private void schClienteSearch() {
        VerModal(new lisCliente(1), select_clie);
    }

    private void schResponsableSearch() {
        VerModal(new lisEmpleado(1), select_resp);
    }

    private void schAlmacenSearch() {
        VerModal(new lisAlmacen(1), select_alma);
    }

    private void schMonedaSearch() {
        VerModal(new lisMoneda(1), select_mone);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnNuevoItem;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAlmacen;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblImpuesto;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblOT;
    private javax.swing.JLabel lblPorcentajeImpuesto;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel pnlUnidades;
    private com.sge.base.controles.JSearch schAlmacen;
    private com.sge.base.controles.JSearch schCliente;
    private com.sge.base.controles.JSearch schMoneda;
    private com.sge.base.controles.JSearch schNumeracion;
    private com.sge.base.controles.JSearch schResponsable;
    private javax.swing.JTable tbItems;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JFormattedTextField txtImpuesto;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtOT;
    private javax.swing.JFormattedTextField txtSubTotal;
    private javax.swing.JFormattedTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
