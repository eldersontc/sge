package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Departamento;
import com.sge.modulos.administracion.clases.Distrito;
import com.sge.modulos.administracion.clases.Empleado;
import com.sge.modulos.administracion.clases.Provincia;
import com.sge.modulos.administracion.formularios.lisDepartamento;
import com.sge.modulos.administracion.formularios.lisDistrito;
import com.sge.modulos.administracion.formularios.lisEmpleado;
import com.sge.modulos.administracion.formularios.lisProvincia;
import com.sge.modulos.ventas.clases.Cliente;
import com.sge.modulos.ventas.clases.ContactoCliente;
import com.sge.modulos.ventas.clases.DireccionCliente;
import com.sge.modulos.ventas.clases.ListaPrecioMaquina;
import com.sge.modulos.ventas.clases.ListaPrecioProducto;
import com.sge.modulos.ventas.clases.ListaPrecioServicio;
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
public class regCliente extends frameBase<Cliente> {

    /**
     * Creates new form regClientex
     */
    public regCliente(String operacion, int idCliente) {
        initComponents();
        Init(operacion, idCliente);
    }

    int idCliente = 0;

    private List<DireccionCliente> direcciones = new ArrayList<>();
    private List<ContactoCliente> contactos = new ArrayList<>();

    Action click_depa = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            VerModal(new lisDepartamento(1), sele_depa);
        }
    };

    Action click_prov = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idDepartamento = ObtenerValorCelda(tbDirecciones, 1);
            if (idDepartamento == 0) {
                VerAdvertencia("FALTA SELECCIONAR UN DEPARTAMENTO.", frame);
            } else {
                String filtro = "WHERE Provincia.idDepartamento = " + idDepartamento;
                VerModal(new lisProvincia(1, filtro), sele_prov);
            }
        }
    };

    Action click_dist = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idProvincia = ObtenerValorCelda(tbDirecciones, 3);
            if (idProvincia == 0) {
                VerAdvertencia("FALTA SELECCIONAR UNA PROVINCIA.", frame);
            } else {
                String filtro = "WHERE Distrito.idProvincia = " + idProvincia;
                VerModal(new lisDistrito(1, filtro), sele_dist);
            }
        }
    };

    Action sele_depa = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Departamento seleccionado = ((lisDepartamento) e.getSource()).getSeleccionado();
            if (seleccionado != null) {
                AsignarValorCelda(tbDirecciones, seleccionado.getIdDepartamento(), 1);
                AsignarValorCelda(tbDirecciones, seleccionado.getNombre(), 2);
                AsignarValorCelda(tbDirecciones, 0, 3);
                AsignarValorCelda(tbDirecciones, "", 4);
                AsignarValorCelda(tbDirecciones, 0, 5);
                AsignarValorCelda(tbDirecciones, "", 6);
            }
        }
    };

    Action sele_prov = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Provincia seleccionado = ((lisProvincia) e.getSource()).getSeleccionado();
            if (seleccionado != null) {
                AsignarValorCelda(tbDirecciones, seleccionado.getIdProvincia(), 3);
                AsignarValorCelda(tbDirecciones, seleccionado.getNombre(), 4);
                AsignarValorCelda(tbDirecciones, 0, 5);
                AsignarValorCelda(tbDirecciones, "", 6);
            }
        }
    };

    Action sele_dist = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Distrito seleccionado = ((lisDistrito) e.getSource()).getSeleccionado();
            if (seleccionado != null) {
                AsignarValorCelda(tbDirecciones, seleccionado.getIdDistrito(), 5);
                AsignarValorCelda(tbDirecciones, seleccionado.getNombre(), 6);
            }
        }
    };

    Action sele_vend = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Empleado seleccionado = ((lisEmpleado) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schVendedor.asingValues(seleccionado.getIdEmpleado(), seleccionado.getNombre());
            }
        }
    };

    Action sele_lppr = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ListaPrecioProducto seleccionado = ((lisListaPrecioProducto) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schListaPrecioProducto.asingValues(seleccionado.getIdListaPrecioProducto(), seleccionado.getNombre());
            }
        }
    };

    Action sele_lpse = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ListaPrecioServicio seleccionado = ((lisListaPrecioServicio) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schListaPrecioServicio.asingValues(seleccionado.getIdListaPrecioServicio(), seleccionado.getNombre());
            }
        }
    };

    Action sele_lpma = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ListaPrecioMaquina seleccionado = ((lisListaPrecioMaquina) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schListaPrecioMaquina.asingValues(seleccionado.getIdListaPrecioMaquina(), seleccionado.getNombre());
            }
        }
    };

    public void Init(String operacion, int idCliente) {
        lblTitulo.setText(operacion + lblTitulo.getText());
        this.idCliente = idCliente;
        if (this.idCliente > 0) {
            new swObtenerCliente().execute();
        }
        AgregarBoton(tbDirecciones, click_depa, 2);
        AgregarBoton(tbDirecciones, click_prov, 4);
        AgregarBoton(tbDirecciones, click_dist, 6);
    }

    public List<DireccionCliente> getDirecciones() {
        for (int i = 0; i < tbDirecciones.getRowCount(); i++) {
            int idDireccionCliente = ObtenerValorCelda(tbDirecciones, i, 0);
            DireccionCliente direccionCliente = new DireccionCliente();
            direccionCliente.setIdDepartamento(ObtenerValorCelda(tbDirecciones, i, 1));
            direccionCliente.setNombreDepartamento(ObtenerValorCelda(tbDirecciones, i, 2));
            direccionCliente.setIdProvincia(ObtenerValorCelda(tbDirecciones, i, 3));
            direccionCliente.setNombreProvincia(ObtenerValorCelda(tbDirecciones, i, 4));
            direccionCliente.setIdDistrito(ObtenerValorCelda(tbDirecciones, i, 5));
            direccionCliente.setNombreDistrito(ObtenerValorCelda(tbDirecciones, i, 6));
            direccionCliente.setDireccion(ObtenerValorCelda(tbDirecciones, i, 7));
            if (idDireccionCliente == 0) {
                direccionCliente.setAgregar(true);
            } else {
                direccionCliente.setIdDireccionCliente(idDireccionCliente);
                direccionCliente.setActualizar(true);
            }
            direcciones.add(direccionCliente);
        }
        return direcciones;
    }

    public List<ContactoCliente> getContactos() {
        for (int i = 0; i < tbContactos.getRowCount(); i++) {
            int idContactoCliente = ObtenerValorCelda(tbContactos, i, 0);
            ContactoCliente contactoCliente = new ContactoCliente();
            contactoCliente.setNombre(ObtenerValorCelda(tbContactos, i, 1));
            contactoCliente.setCargo(ObtenerValorCelda(tbContactos, i, 2));
            contactoCliente.setTelefono(ObtenerValorCelda(tbContactos, i, 3));
            contactoCliente.setCorreo(ObtenerValorCelda(tbContactos, i, 4));
            if (idContactoCliente == 0) {
                contactoCliente.setAgregar(true);
            } else {
                contactoCliente.setIdContactoCliente(idContactoCliente);
                contactoCliente.setActualizar(true);
            }
            contactos.add(contactoCliente);
        }
        return contactos;
    }

    public class swObtenerCliente extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                String json = cliVentas.ObtenerCliente(new Gson().toJson(idCliente));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Cliente cliente = new Gson().fromJson(resultado[1], Cliente.class);
                    txtRazonSocial.setText(cliente.getRazonSocial());
                    txtNombreComercial.setText(cliente.getNombreComercial());
                    cboTipoDocumento.setSelectedItem(cliente.getTipoDocumentoIdentidad());
                    txtDocumento.setText(cliente.getDocumentoIdentidad());
                    txtFechaUltimaVenta.setText(cliente.getFechaUltimaVenta());
                    schVendedor.asingValues(cliente.getIdVendedor(), cliente.getNombreVendedor());
                    schListaPrecioProducto.asingValues(cliente.getIdListaPrecioProducto(), cliente.getNombreListaPrecioProducto());
                    schListaPrecioServicio.asingValues(cliente.getIdListaPrecioServicio(), cliente.getNombreListaPrecioServicio());
                    schListaPrecioMaquina.asingValues(cliente.getIdListaPrecioMaquina(), cliente.getNombreListaPrecioMaquina());
                    chkActivo.setSelected(cliente.isActivo());
                    for (DireccionCliente direccion : cliente.getDirecciones()) {
                        AgregarFila(tbDirecciones,
                                new Object[]{
                                    direccion.getIdDireccionCliente(),
                                    direccion.getIdDepartamento(),
                                    direccion.getNombreDepartamento(),
                                    direccion.getIdProvincia(),
                                    direccion.getNombreProvincia(),
                                    direccion.getIdDistrito(),
                                    direccion.getNombreDistrito(),
                                    direccion.getDireccion()
                                });
                    }
                    for (ContactoCliente contacto : cliente.getContactos()) {
                        AgregarFila(tbContactos,
                                new Object[]{
                                    contacto.getIdContactoCliente(),
                                    contacto.getNombre(),
                                    contacto.getCargo(),
                                    contacto.getTelefono(),
                                    contacto.getCorreo()
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

    public class swGuardarCliente extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliVentas = new cliVentas();
            String json = "";
            try {
                Cliente cliente = new Cliente();
                cliente.setRazonSocial(txtRazonSocial.getText());
                cliente.setNombreComercial(txtNombreComercial.getText());
                cliente.setTipoDocumentoIdentidad(cboTipoDocumento.getSelectedItem().toString());
                cliente.setDocumentoIdentidad(txtDocumento.getText());
                cliente.setIdVendedor(schVendedor.getId());
                cliente.setNombreVendedor(schVendedor.getText());
                cliente.setIdListaPrecioProducto(schListaPrecioProducto.getId());
                cliente.setNombreListaPrecioProducto(schListaPrecioProducto.getText());
                cliente.setIdListaPrecioServicio(schListaPrecioServicio.getId());
                cliente.setNombreListaPrecioServicio(schListaPrecioServicio.getText());
                cliente.setIdListaPrecioMaquina(schListaPrecioMaquina.getId());
                cliente.setNombreListaPrecioMaquina(schListaPrecioMaquina.getText());
                cliente.setActivo(chkActivo.isSelected());
                cliente.setDirecciones(getDirecciones());
                cliente.setContactos(getContactos());
                if (idCliente == 0) {
                    json = cliVentas.RegistrarCliente(new Gson().toJson(cliente));
                } else {
                    cliente.setIdCliente(idCliente);
                    json = cliVentas.ActualizarCliente(new Gson().toJson(cliente));
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlDirecciones = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDirecciones = new javax.swing.JTable();
        btnNuevaDireccion = new javax.swing.JButton();
        btnEliminarDireccion = new javax.swing.JButton();
        pnlContactos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbContactos = new javax.swing.JTable();
        btnNuevoContacto = new javax.swing.JButton();
        btnEliminarContacto = new javax.swing.JButton();
        tabListasPrecio = new javax.swing.JPanel();
        schListaPrecioProducto = new com.sge.base.controles.JSearch();
        lblListaPrecioProducto = new javax.swing.JLabel();
        lblListaPrecioServicio = new javax.swing.JLabel();
        schListaPrecioServicio = new com.sge.base.controles.JSearch();
        lblListaPrecioMaquina = new javax.swing.JLabel();
        schListaPrecioMaquina = new com.sge.base.controles.JSearch();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        chkActivo = new javax.swing.JCheckBox();
        txtFechaUltimaVenta = new javax.swing.JTextField();
        lblFechaUltimaVenta = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        cboTipoDocumento = new javax.swing.JComboBox();
        lblTipoDocumento = new javax.swing.JLabel();
        lblNombreComercial = new javax.swing.JLabel();
        txtNombreComercial = new javax.swing.JTextField();
        txtRazonSocial = new javax.swing.JTextField();
        lblRazonSocial = new javax.swing.JLabel();
        lblVendedor = new javax.swing.JLabel();
        schVendedor = new com.sge.base.controles.JSearch();

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        pnlDirecciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbDirecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "IDDEPARTAMENTO", "DEPARTAMENTO", "IDPROVINCIA", "PROVINCIA", "IDDISTRITO", "DISTRITO", "DIRECCION"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDirecciones.setRowHeight(25);
        tbDirecciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbDirecciones);
        if (tbDirecciones.getColumnModel().getColumnCount() > 0) {
            tbDirecciones.getColumnModel().getColumn(0).setMinWidth(0);
            tbDirecciones.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbDirecciones.getColumnModel().getColumn(0).setMaxWidth(0);
            tbDirecciones.getColumnModel().getColumn(1).setMinWidth(0);
            tbDirecciones.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbDirecciones.getColumnModel().getColumn(1).setMaxWidth(0);
            tbDirecciones.getColumnModel().getColumn(3).setMinWidth(0);
            tbDirecciones.getColumnModel().getColumn(3).setPreferredWidth(0);
            tbDirecciones.getColumnModel().getColumn(3).setMaxWidth(0);
            tbDirecciones.getColumnModel().getColumn(5).setMinWidth(0);
            tbDirecciones.getColumnModel().getColumn(5).setPreferredWidth(0);
            tbDirecciones.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        btnNuevaDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevaDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaDireccionActionPerformed(evt);
            }
        });

        btnEliminarDireccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDireccionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDireccionesLayout = new javax.swing.GroupLayout(pnlDirecciones);
        pnlDirecciones.setLayout(pnlDireccionesLayout);
        pnlDireccionesLayout.setHorizontalGroup(
            pnlDireccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDireccionesLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDireccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevaDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDireccionesLayout.setVerticalGroup(
            pnlDireccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDireccionesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNuevaDireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarDireccion)
                .addContainerGap(109, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("DIRECCIONES", pnlDirecciones);

        pnlContactos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbContactos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CARGO", "TELEFONO", "CORREO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbContactos.setRowHeight(25);
        tbContactos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbContactos);
        if (tbContactos.getColumnModel().getColumnCount() > 0) {
            tbContactos.getColumnModel().getColumn(0).setMinWidth(0);
            tbContactos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tbContactos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnNuevoContacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevoContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoContactoActionPerformed(evt);
            }
        });

        btnEliminarContacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarContactoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContactosLayout = new javax.swing.GroupLayout(pnlContactos);
        pnlContactos.setLayout(pnlContactosLayout);
        pnlContactosLayout.setHorizontalGroup(
            pnlContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContactosLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminarContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlContactosLayout.setVerticalGroup(
            pnlContactosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContactosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNuevoContacto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminarContacto)
                .addContainerGap(109, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("CONTACTOS", pnlContactos);

        tabListasPrecio.setBackground(java.awt.Color.white);
        tabListasPrecio.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        schListaPrecioProducto.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schListaPrecioProductoSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblListaPrecioProducto.setText("PRODUCTO");

        lblListaPrecioServicio.setText("SERVICIO");

        schListaPrecioServicio.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schListaPrecioServicioSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblListaPrecioMaquina.setText("MAQUINA");

        schListaPrecioMaquina.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schListaPrecioMaquinaSearch();
            }
            @Override
            public void Clear(){
            }
        });

        javax.swing.GroupLayout tabListasPrecioLayout = new javax.swing.GroupLayout(tabListasPrecio);
        tabListasPrecio.setLayout(tabListasPrecioLayout);
        tabListasPrecioLayout.setHorizontalGroup(
            tabListasPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabListasPrecioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(tabListasPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaPrecioProducto)
                    .addComponent(lblListaPrecioServicio)
                    .addComponent(lblListaPrecioMaquina))
                .addGap(58, 58, 58)
                .addGroup(tabListasPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        tabListasPrecioLayout.setVerticalGroup(
            tabListasPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabListasPrecioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabListasPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabListasPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabListasPrecioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("LISTAS DE PRECIO", tabListasPrecio);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("CLIENTE");

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

        chkActivo.setText("ACTIVO");

        txtFechaUltimaVenta.setEnabled(false);

        lblFechaUltimaVenta.setText("F. ULTIMA VENTA");

        lblDocumento.setText("Nº DOCUMENTO");

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI", "RUC", "PASAPORTE", "OTRO" }));

        lblTipoDocumento.setText("TIPO DOCUMENTO");

        lblNombreComercial.setText("NOMBRE COMERCIAL");

        lblRazonSocial.setText("RAZON SOCIAL");

        lblVendedor.setText("VENDEDOR");

        schVendedor.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schVendedorSearch();
            }
            @Override
            public void Clear(){
            }
        });

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblRazonSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNombreComercial)
                                    .addComponent(lblTipoDocumento)
                                    .addComponent(lblDocumento)
                                    .addComponent(lblFechaUltimaVenta))
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombreComercial)
                                            .addGroup(frameLayout.createSequentialGroup()
                                                .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(chkActivo))
                                            .addGroup(frameLayout.createSequentialGroup()
                                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtFechaUltimaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(schVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRazonSocial))))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVendedor))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRazonSocial))
                .addGap(1, 1, 1)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreComercial)
                            .addComponent(txtNombreComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTipoDocumento))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkActivo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDocumento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaUltimaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaUltimaVenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
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

    private void schVendedorSearch() {
        VerModal(new lisEmpleado(1), sele_vend);
    }

    private void schListaPrecioProductoSearch() {
        VerModal(new lisListaPrecioProducto(1), sele_lppr);
    }

    private void schListaPrecioServicioSearch() {
        VerModal(new lisListaPrecioServicio(1), sele_lpse);
    }

    private void schListaPrecioMaquinaSearch() {
        VerModal(new lisListaPrecioMaquina(1), sele_lpma);
    }

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarCliente().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevaDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaDireccionActionPerformed
        // TODO add your handling code here:
        AgregarFila(tbDirecciones, new Object[]{0, 0, "", 0, "", 0, "", ""});
    }//GEN-LAST:event_btnNuevaDireccionActionPerformed

    private void btnEliminarDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDireccionActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbDirecciones)) {
            int idDireccionCliente = ObtenerValorCelda(tbDirecciones, 0);
            if (idDireccionCliente > 0) {
                DireccionCliente direccionCliente = new DireccionCliente();
                direccionCliente.setIdDireccionCliente(idDireccionCliente);
                direccionCliente.setEliminar(true);
                direcciones.add(direccionCliente);
            }
            EliminarFila(tbDirecciones);
        }
    }//GEN-LAST:event_btnEliminarDireccionActionPerformed

    private void btnNuevoContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoContactoActionPerformed
        // TODO add your handling code here:
        AgregarFila(tbContactos, new Object[]{0, "", "", "", ""});
    }//GEN-LAST:event_btnNuevoContactoActionPerformed

    private void btnEliminarContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarContactoActionPerformed
        // TODO add your handling code here:
        if (FilaActiva(tbContactos)) {
            int idContactoCliente = ObtenerValorCelda(tbContactos, 0);
            if (idContactoCliente > 0) {
                ContactoCliente contactoCliente = new ContactoCliente();
                contactoCliente.setIdContactoCliente(idContactoCliente);
                contactoCliente.setEliminar(true);
                contactos.add(contactoCliente);
            }
            EliminarFila(tbContactos);
        }
    }//GEN-LAST:event_btnEliminarContactoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarContacto;
    private javax.swing.JButton btnEliminarDireccion;
    private javax.swing.JButton btnNuevaDireccion;
    private javax.swing.JButton btnNuevoContacto;
    private javax.swing.JComboBox cboTipoDocumento;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFechaUltimaVenta;
    private javax.swing.JLabel lblListaPrecioMaquina;
    private javax.swing.JLabel lblListaPrecioProducto;
    private javax.swing.JLabel lblListaPrecioServicio;
    private javax.swing.JLabel lblNombreComercial;
    private javax.swing.JLabel lblRazonSocial;
    private javax.swing.JLabel lblTipoDocumento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JPanel pnlContactos;
    private javax.swing.JPanel pnlDirecciones;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schListaPrecioMaquina;
    private com.sge.base.controles.JSearch schListaPrecioProducto;
    private com.sge.base.controles.JSearch schListaPrecioServicio;
    private com.sge.base.controles.JSearch schVendedor;
    private javax.swing.JPanel tabListasPrecio;
    private javax.swing.JTable tbContactos;
    private javax.swing.JTable tbDirecciones;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtFechaUltimaVenta;
    private javax.swing.JTextField txtNombreComercial;
    private javax.swing.JTextField txtRazonSocial;
    // End of variables declaration//GEN-END:variables
}
