package com.sge.modulos.ventas.formularios;

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
import com.sge.modulos.inventarios.clases.Producto;
import com.sge.modulos.inventarios.formularios.lisProducto;
import com.sge.modulos.ventas.clases.Cliente;
import com.sge.modulos.ventas.clases.ContactoCliente;
import com.sge.modulos.ventas.clases.FormaPago;
import com.sge.modulos.ventas.clases.GrupoSolicitudCotizacion;
import com.sge.modulos.ventas.clases.ItemPlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.clases.ItemSolicitudCotizacion;
import com.sge.modulos.ventas.clases.Maquina;
import com.sge.modulos.ventas.clases.PlantillaSolicitudCotizacion;
import com.sge.modulos.ventas.clases.Servicio;
import com.sge.modulos.ventas.clases.SolicitudCotizacion;
import com.sge.modulos.ventas.cliente.cliVentas;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author elderson
 */
public class regSolicitudCotizacion extends frameBase<SolicitudCotizacion> {

    /**
     * Creates new form regSolicitudCotizacion
     */
    public regSolicitudCotizacion() {
        initComponents();
    }

    /**
     * Creates new form regSolicitudCotizacion
     *
     * @param id
     */
    public regSolicitudCotizacion(int id) {
        initComponents();
        setId(id);
    }

    public regSolicitudCotizacion(ValorDefinido valorDefinido) {
        initComponents();
        OcultarControl(tpnlGruposItems);
        super.Init(valorDefinido);
    }

    @Override
    public void Init() {
        if (getId() == 0) {
            lblTitulo.setText("NUEVA " + lblTitulo.getText());
            new swObtenerValoresDefinidos().execute();
        } else {
            lblTitulo.setText("MODIFICAR " + lblTitulo.getText());
            new swObtenerSolicitud().execute();
        }
        OcultarControl(tpnlGruposItems);
    }

    private GrupoSolicitudCotizacion grupo;
    private ItemSolicitudCotizacion item;

    Action sele_clie = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Cliente seleccionado = ((lisCliente) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schCliente.asingValues(seleccionado.getIdCliente(), seleccionado.getRazonSocial());
                schListaPrecioProducto.asingValues(seleccionado.getIdListaPrecioProducto(), seleccionado.getNombreListaPrecioProducto());
                schListaPrecioServicio.asingValues(seleccionado.getIdListaPrecioServicio(), seleccionado.getNombreListaPrecioServicio());
                schListaPrecioMaquina.asingValues(seleccionado.getIdListaPrecioMaquina(), seleccionado.getNombreListaPrecioMaquina());
                getEntidad().setIdListaPrecioProducto(seleccionado.getIdListaPrecioProducto());
                getEntidad().setNombreListaPrecioProducto(seleccionado.getNombreListaPrecioProducto());
                getEntidad().setIdListaPrecioServicio(seleccionado.getIdListaPrecioServicio());
                getEntidad().setNombreListaPrecioServicio(seleccionado.getNombreListaPrecioServicio());
                getEntidad().setIdListaPrecioMaquina(seleccionado.getIdListaPrecioMaquina());
                getEntidad().setNombreListaPrecioMaquina(seleccionado.getNombreListaPrecioMaquina());
            }
        }
    };

    Action sele_nume = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Numeracion seleccionado = ((lisNumeracion) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schNumeracion.asingValues(seleccionado.getIdNumeracion(), seleccionado.getDescripcion());
                getEntidad().setNumeracionManual(seleccionado.isManual());
                txtNumero.setEnabled(seleccionado.isManual());
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

    Action sele_mone = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Moneda seleccionado = ((lisMoneda) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMoneda.asingValues(seleccionado.getIdMoneda(), seleccionado.getSimbolo());
            }
        }
    };

    Action sele_form = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            FormaPago seleccionado = ((lisFormaPago) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schFormaPago.asingValues(seleccionado.getIdFormaPago(), seleccionado.getDescripcion());
            }
        }
    };

    Action sele_maqu = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Maquina seleccionado = ((lisMaquina) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMaquina.asingValues(seleccionado.getIdMaquina(), seleccionado.getDescripcion());
                item.setIdMaquina(seleccionado.getIdMaquina());
                item.setDescripcionMaquina(seleccionado.getDescripcion());
                item.setAltoMaximoPliegoMaquina(seleccionado.getAltoMaximoPliego());
                item.setLargoMaximoPliegoMaquina(seleccionado.getLargoMaximoPliego());
            }
        }
    };

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

    Action sele_cont = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ContactoCliente seleccionado = ((lisContactoCliente) e.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schContacto.asingValues(seleccionado.getIdContactoCliente(), seleccionado.getNombre());
            }
        }
    };

    Action sele_plan = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            List<PlantillaSolicitudCotizacion> seleccionados = ((lisPlantillaSC) evt.getSource()).getSeleccionados();
            if (!seleccionados.isEmpty()) {
                cliVentas cliente = new cliVentas();
                try {
                    String json = cliente.ObtenerPlantillasSolicitudCotizacionConItems(new Gson().toJson(seleccionados));
                    String[] resultado = new Gson().fromJson(json, String[].class);
                    if (resultado[0].equals("true")) {
                        PlantillaSolicitudCotizacion[] plantillas = new Gson().fromJson(resultado[1], PlantillaSolicitudCotizacion[].class);
                        for (PlantillaSolicitudCotizacion plantilla : plantillas) {
                            GrupoSolicitudCotizacion grupo = new GrupoSolicitudCotizacion();
                            grupo.setNombre(plantilla.getNombre());
                            grupo.setLineaProduccion(plantilla.getLineaProduccion());
                            DefaultMutableTreeNode nodoGrupo = AgregarNodo(treeGruposItems, grupo);
                            for (ItemPlantillaSolicitudCotizacion itemPlantilla : plantilla.getItems()) {
                                ItemSolicitudCotizacion item = new ItemSolicitudCotizacion();
                                item.setNombre(itemPlantilla.getNombre());
                                item.setServicioImpresion(itemPlantilla.isServicioImpresion());
                                item.setIdServicioImpresion(itemPlantilla.getIdServicioImpresion());
                                item.setNombreServicioImpresion(itemPlantilla.getNombreServicioImpresion());
                                item.setMaterial(itemPlantilla.isMaterial());
                                item.setIdMaterial(itemPlantilla.getIdMaterial());
                                item.setCodigoMaterial(itemPlantilla.getCodigoMaterial());
                                item.setNombreMaterial(itemPlantilla.getNombreMaterial());
                                item.setAltoMaterial(itemPlantilla.getAltoMaterial());
                                item.setLargoMaterial(itemPlantilla.getLargoMaterial());
                                item.setIdUnidadMaterial(itemPlantilla.getIdUnidadMaterial());
                                item.setAbreviacionUnidadMaterial(itemPlantilla.getAbreviacionUnidadMaterial());
                                item.setFactorUnidadMaterial(itemPlantilla.getFactorUnidadMaterial());
                                item.setTipoUnidad(itemPlantilla.isTipoUnidad());
                                item.setNombreTipoUnidad(itemPlantilla.getNombreTipoUnidad());
                                item.setMedidaAbierta(itemPlantilla.isMedidaAbierta());
                                item.setUnidadMedidaAbierta(itemPlantilla.getUnidadMedidaAbierta());
                                item.setMedidaCerrada(itemPlantilla.isMedidaCerrada());
                                item.setTiraRetira(itemPlantilla.isTiraRetira());
                                item.setGrafico(itemPlantilla.isGrafico());
                                item.setFondo(itemPlantilla.isFondo());
                                AgregarNodo(treeGruposItems, nodoGrupo, item);
                                grupo.getItems().add(item);
                            }
                            getEntidad().getGrupos().add(grupo);
                        }
                        ExpandirTodosNodos(treeGruposItems);
                    }
                } catch (Exception e) {
                    System.out.print(e);
                } finally {
                    cliente.close();
                }
            }
        }
    };

    public List<GrupoSolicitudCotizacion> getGrupos() {
        for (GrupoSolicitudCotizacion grupo : getEntidad().getGrupos()) {
            if(grupo.getIdGrupoSolicitudCotizacion() == 0){
                grupo.setAgregar(true);
            } else if (!grupo.isEliminar()){
                grupo.setActualizar(true);
            }
            for (ItemSolicitudCotizacion item : grupo.getItems()) {
                if(item.getIdItemSolicitudCotizacion() == 0){
                    item.setAgregar(true);
                } else if (!item.isEliminar()){
                    item.setActualizar(true);
                }
            }
        }
        return getEntidad().getGrupos();
    }
    
    private void AsignarValores() {
        getEntidad().setIdCliente(schCliente.getId());
        getEntidad().setRazonSocialCliente(schCliente.getText());
        getEntidad().setIdNumeracion(schNumeracion.getId());
        getEntidad().setDescripcionNumeracion(schNumeracion.getText());
        getEntidad().setNumero(txtNumero.getText());
        getEntidad().setIdVendedor(schVendedor.getId());
        getEntidad().setNombreVendedor(schVendedor.getText());
        getEntidad().setIdMoneda(schMoneda.getId());
        getEntidad().setSimboloMoneda(schMoneda.getText());
        //getEntidad().setFechaCreacion(txtFechaCreacion.getValue());
        getEntidad().setIdFormaPago(schFormaPago.getId());
        getEntidad().setDescripcionFormaPago(schFormaPago.getText());
        getEntidad().setDescripcion(txtDescripcion.getText());
        getEntidad().setIdContactoCliente(schContacto.getId());
        getEntidad().setNombreContactoCliente(schContacto.getText());
        getEntidad().setGrupos(getGrupos());
    }

    @Override
    public void AsignarControles() {
        schCliente.asingValues(getEntidad().getIdCliente(), getEntidad().getRazonSocialCliente());
        schListaPrecioProducto.asingValues(getEntidad().getIdListaPrecioProducto(), getEntidad().getNombreListaPrecioProducto());
        schListaPrecioServicio.asingValues(getEntidad().getIdListaPrecioServicio(), getEntidad().getNombreListaPrecioServicio());
        schListaPrecioMaquina.asingValues(getEntidad().getIdListaPrecioMaquina(), getEntidad().getNombreListaPrecioMaquina());
        schNumeracion.asingValues(getEntidad().getIdNumeracion(), getEntidad().getDescripcionNumeracion());
        txtNumero.setText(getEntidad().getNumero());
        schVendedor.asingValues(getEntidad().getIdVendedor(), getEntidad().getNombreVendedor());
        schMoneda.asingValues(getEntidad().getIdMoneda(), getEntidad().getSimboloMoneda());
        txtFechaCreacion.setValue(getEntidad().getFechaCreacion());
        schFormaPago.asingValues(getEntidad().getIdFormaPago(), getEntidad().getDescripcionFormaPago());
        txtDescripcion.setText(getEntidad().getDescripcion());
        schContacto.asingValues(getEntidad().getIdContactoCliente(), getEntidad().getNombreContactoCliente());
        for (GrupoSolicitudCotizacion grupo : getEntidad().getGrupos()) {
            DefaultMutableTreeNode nodoGrupo = AgregarNodo(treeGruposItems, grupo);
            for (ItemSolicitudCotizacion item : grupo.getItems()) {
                AgregarNodo(treeGruposItems, nodoGrupo, item);
            }
        }
        ExpandirTodosNodos(treeGruposItems);
    }

    @Override
    public void OcultarControles() {
        OcultarControl(btnNuevoGrupoItem);
        OcultarControl(btnEliminarGrupoItem);
    }

    public class swObtenerValoresDefinidos extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {
                String json = cliente.ObtenerValorDefinidoPorUsuarioYEntidad(new Gson().toJson(new int[]{getIdUsuario(), 3}));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    if (resultado[2].isEmpty()) {
                        setEntidad(new SolicitudCotizacion());
                    } else {
                        setEntidad(new Gson().fromJson(resultado[2], SolicitudCotizacion.class));
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

    public class swObtenerSolicitud extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                String json = cliVentas.ObtenerSolicitudCotizacion(new Gson().toJson(getId()));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    setEntidad(new Gson().fromJson(resultado[1], SolicitudCotizacion.class));
                    AsignarControles();
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
    
    public class swGuardarSolicitud extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliVentas = new cliVentas();
            String json = "";
            try {
                AsignarValores();
                if (getId() == 0) {
                    json = cliVentas.RegistrarSolicitudCotizacion(new Gson().toJson(getEntidad()));
                } else {
                    getEntidad().setIdSolicitudCotizacion(getId());
                    json = cliVentas.ActualizarSolicitudCotizacion(new Gson().toJson(getEntidad()));
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

    private void AsignarControlesGrupo() {
        tpnlGruposItems.setTitleAt(0, this.grupo.getNombre());
        txtNombreGrupo.setText(this.grupo.getNombre());
        cboLineaProduccionGrupo.setSelectedItem(this.grupo.getLineaProduccion());
        spCantidad.setValue(this.grupo.getCantidad());
    }

    private void AsignarControlesItem() {
        tpnlGruposItems.setTitleAt(0, this.item.getNombre());
        txtNombreItem.setText(this.item.getNombre());
        chkMedidaAbierta.setSelected(this.item.isMedidaAbierta());
        spAltoMedidaAbierta.setEnabled(this.item.isMedidaAbierta());
        spLargoMedidaAbierta.setEnabled(this.item.isMedidaAbierta());
        cboUnidadMedidaAbierta.setEnabled(this.item.isMedidaAbierta());
        spAltoMedidaAbierta.setValue(this.item.getAltoMedidaAbierta());
        spLargoMedidaAbierta.setValue(this.item.getLargoMedidaAbierta());
        cboUnidadMedidaAbierta.setSelectedItem(this.item.getUnidadMedidaAbierta());
        chkMedidaCerrada.setSelected(this.item.isMedidaCerrada());
        spAltoMedidaCerrada.setEnabled(this.item.isMedidaCerrada());
        spLargoMedidaCerrada.setEnabled(this.item.isMedidaCerrada());
        spAltoMedidaCerrada.setValue(this.item.getAltoMedidaCerrada());
        spLargoMedidaCerrada.setValue(this.item.getLargoMedidaCerrada());
        chkTiraRetira.setSelected(this.item.isTiraRetira());
        spTiraColor.setEnabled(this.item.isTiraRetira());
        spRetiraColor.setEnabled(this.item.isTiraRetira());
        spTiraColor.setValue(this.item.getTiraColor());
        spRetiraColor.setValue(this.item.getRetiraColor());
        chkTipoUnidad.setSelected(this.item.isTipoUnidad());
        cboTipoUnidad.setEnabled(this.item.isTipoUnidad());
        spCantidadTipoUnidad.setEnabled(this.item.isTipoUnidad());
        cboTipoUnidad.setSelectedItem(this.item.getNombreTipoUnidad());
        spCantidadTipoUnidad.setValue(this.item.getCantidadTipoUnidad());
        chkGrafico.setSelected(this.item.isGrafico());
        chkFondo.setSelected(this.item.isFondo());
        spFondo.setEnabled(this.item.isFondo());
        spFondo.setValue(this.item.getdFondo());
        chkServicioImpresion.setSelected(this.item.isServicioImpresion());
        schServicioImpresion.setEnabled(this.item.isServicioImpresion());
        schMaquina.setEnabled(this.item.isServicioImpresion());
        schServicioImpresion.asingValues(this.item.getIdServicioImpresion(), this.item.getNombreServicioImpresion());
        schMaquina.asingValues(this.item.getIdMaquina(), this.item.getDescripcionMaquina());
        chkMaterial.setSelected(this.item.isMaterial());
        schMaterial.setEnabled(this.item.isMaterial());
        schMaterial.asingValues(this.item.getIdMaterial(), this.item.getNombreMaterial());
        txtAcabados.setText(this.item.getAcabados());
    }
    
    public void Aceptar() {
        if (super.isFromJson()) {
            AsignarValores();
            setJson(new Gson().toJson(super.getEntidad()));
            Cerrar();
        } else {
            if (getEntidad().getIdSolicitudCotizacion() == 0 || "PENDIENTE DE APROBACIÓN".equals(getEntidad().getEstado())) {
                new swGuardarSolicitud().execute();
            } else {
                VerAdvertencia("SÓLO SE PUEDE MODIFICAR CUANDO EL ESTADO ES : PENDIENTE DE APROBACIÓN.", frame);
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
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        tpnlGruposItems = new javax.swing.JTabbedPane();
        tabGrupoItem = new javax.swing.JPanel();
        pnlGrupo = new javax.swing.JPanel();
        lblNombreGrupo = new javax.swing.JLabel();
        txtNombreGrupo = new javax.swing.JTextField();
        lblLineaProduccionGrupo = new javax.swing.JLabel();
        cboLineaProduccionGrupo = new javax.swing.JComboBox();
        lblCantidad = new javax.swing.JLabel();
        spCantidad = new javax.swing.JSpinner();
        pnlItem = new javax.swing.JPanel();
        lblNombreItem = new javax.swing.JLabel();
        txtNombreItem = new javax.swing.JTextField();
        lblAcabados = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAcabados = new javax.swing.JTextArea();
        chkMedidaAbierta = new javax.swing.JCheckBox();
        chkMedidaCerrada = new javax.swing.JCheckBox();
        lblAltoMedidaAbierta = new javax.swing.JLabel();
        lblLargoMedidaAbierta = new javax.swing.JLabel();
        cboUnidadMedidaAbierta = new javax.swing.JComboBox();
        lblAltoMedidaCerrada = new javax.swing.JLabel();
        lblLargoMedidaCerrada = new javax.swing.JLabel();
        chkTiraRetira = new javax.swing.JCheckBox();
        lblTiraColor = new javax.swing.JLabel();
        lblRetiraColor = new javax.swing.JLabel();
        chkFondo = new javax.swing.JCheckBox();
        chkTipoUnidad = new javax.swing.JCheckBox();
        cboTipoUnidad = new javax.swing.JComboBox();
        chkGrafico = new javax.swing.JCheckBox();
        chkServicioImpresion = new javax.swing.JCheckBox();
        schServicioImpresion = new com.sge.base.controles.JSearch();
        schMaquina = new com.sge.base.controles.JSearch();
        chkMaterial = new javax.swing.JCheckBox();
        lblMaquina = new javax.swing.JLabel();
        schMaterial = new com.sge.base.controles.JSearch();
        spAltoMedidaAbierta = new javax.swing.JSpinner();
        spLargoMedidaAbierta = new javax.swing.JSpinner();
        spAltoMedidaCerrada = new javax.swing.JSpinner();
        spLargoMedidaCerrada = new javax.swing.JSpinner();
        spFondo = new javax.swing.JSpinner();
        spRetiraColor = new javax.swing.JSpinner();
        spTiraColor = new javax.swing.JSpinner();
        spCantidadTipoUnidad = new javax.swing.JSpinner();
        tabInformacionAdicional = new javax.swing.JPanel();
        lblListaPrecioProducto = new javax.swing.JLabel();
        schListaPrecioProducto = new com.sge.base.controles.JSearch();
        schListaPrecioServicio = new com.sge.base.controles.JSearch();
        lblListaPrecioServicio = new javax.swing.JLabel();
        lblListaPrecioMaquina = new javax.swing.JLabel();
        schListaPrecioMaquina = new com.sge.base.controles.JSearch();
        jSeparator1 = new javax.swing.JSeparator();
        lblContacto = new javax.swing.JLabel();
        schContacto = new com.sge.base.controles.JSearch();
        btnNuevoGrupoItem = new javax.swing.JButton();
        btnEliminarGrupoItem = new javax.swing.JButton();
        lblCliente = new javax.swing.JLabel();
        schCliente = new com.sge.base.controles.JSearch();
        lblVendedor = new javax.swing.JLabel();
        schVendedor = new com.sge.base.controles.JSearch();
        schNumeracion = new com.sge.base.controles.JSearch();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblFechaCreacion = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JFormattedTextField();
        schMoneda = new com.sge.base.controles.JSearch();
        lblMoneda = new javax.swing.JLabel();
        lblFormaPago = new javax.swing.JLabel();
        schFormaPago = new com.sge.base.controles.JSearch();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeGruposItems = new javax.swing.JTree();

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
        lblTitulo.setText("SOLICITUD DE COTIZACIÓN");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        lblDescripcion.setText("DESCRIPCION");

        tabGrupoItem.setBackground(java.awt.Color.white);

        pnlGrupo.setBackground(java.awt.Color.white);

        lblNombreGrupo.setText("NOMBRE");

        txtNombreGrupo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtNombreGrupoChange();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                txtNombreGrupoChange();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                txtNombreGrupoChange();
            }

        });

        lblLineaProduccionGrupo.setText("L. PRODUCCIÓN");

        cboLineaProduccionGrupo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PUBLICIDAD", "OFFSET", "DIGITAL", "OTROS" }));
        cboLineaProduccionGrupo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLineaProduccionGrupoItemStateChanged(evt);
            }
        });

        lblCantidad.setText("CANTIDAD");

        spCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spCantidadStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlGrupoLayout = new javax.swing.GroupLayout(pnlGrupo);
        pnlGrupo.setLayout(pnlGrupoLayout);
        pnlGrupoLayout.setHorizontalGroup(
            pnlGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGrupoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblNombreGrupo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombreGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLineaProduccionGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(cboLineaProduccionGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGrupoLayout.setVerticalGroup(
            pnlGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGrupoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboLineaProduccionGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLineaProduccionGrupo)
                        .addComponent(lblCantidad)
                        .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombreGrupo)
                        .addComponent(txtNombreGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pnlItem.setBackground(java.awt.Color.white);

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

        lblAcabados.setText("ACABADOS");

        txtAcabados.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txtAcabadosChange();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                txtAcabadosChange();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                txtAcabadosChange();
            }

        });
        txtAcabados.setColumns(20);
        txtAcabados.setRows(5);
        jScrollPane3.setViewportView(txtAcabados);

        chkMedidaAbierta.setText("MEDIDA ABIERTA");
        chkMedidaAbierta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMedidaAbiertaStateChanged(evt);
            }
        });

        chkMedidaCerrada.setText("MEDIDA CERRADA");
        chkMedidaCerrada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMedidaCerradaStateChanged(evt);
            }
        });

        lblAltoMedidaAbierta.setText("ALTO");

        lblLargoMedidaAbierta.setText("LARGO");

        cboUnidadMedidaAbierta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CM", "MT" }));

        lblAltoMedidaCerrada.setText("ALTO");

        lblLargoMedidaCerrada.setText("LARGO");

        chkTiraRetira.setText("TIRA Y RETIRA");
        chkTiraRetira.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkTiraRetiraStateChanged(evt);
            }
        });

        lblTiraColor.setText("TIRA COLOR");

        lblRetiraColor.setText("RETIRA COLOR");

        chkFondo.setText("FONDO");
        chkFondo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkFondoStateChanged(evt);
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

        chkGrafico.setText("GRÁFICO");

        chkServicioImpresion.setText("IMPRESIÓN");
        chkServicioImpresion.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
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

        schMaquina.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMaquinaSearch();
            }
            @Override
            public void Clear(){
                schMaquinaClear();
            }
        });

        chkMaterial.setText("MATERIAL");
        chkMaterial.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkMaterial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMaterialStateChanged(evt);
            }
        });

        lblMaquina.setText("MAQUINA");

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

        spAltoMedidaAbierta.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spAltoMedidaAbierta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spAltoMedidaAbiertaStateChanged(evt);
            }
        });

        spLargoMedidaAbierta.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spLargoMedidaAbierta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spLargoMedidaAbiertaStateChanged(evt);
            }
        });

        spAltoMedidaCerrada.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spAltoMedidaCerrada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spAltoMedidaCerradaStateChanged(evt);
            }
        });

        spLargoMedidaCerrada.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spLargoMedidaCerrada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spLargoMedidaCerradaStateChanged(evt);
            }
        });

        spFondo.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spFondo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spFondoStateChanged(evt);
            }
        });

        spRetiraColor.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spRetiraColor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spRetiraColorStateChanged(evt);
            }
        });

        spTiraColor.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spTiraColor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spTiraColorStateChanged(evt);
            }
        });

        spCantidadTipoUnidad.setModel(new javax.swing.SpinnerNumberModel());
        spCantidadTipoUnidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spCantidadTipoUnidadStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pnlItemLayout = new javax.swing.GroupLayout(pnlItem);
        pnlItem.setLayout(pnlItemLayout);
        pnlItemLayout.setHorizontalGroup(
            pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkServicioImpresion)
                                    .addComponent(lblMaquina))
                                .addGap(18, 18, 18)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(schServicioImpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(schMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(schMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlItemLayout.createSequentialGroup()
                                        .addComponent(lblNombreItem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlItemLayout.createSequentialGroup()
                                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkMedidaAbierta)
                                            .addGroup(pnlItemLayout.createSequentialGroup()
                                                .addComponent(lblAltoMedidaAbierta)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spAltoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblLargoMedidaAbierta)
                                                .addGap(4, 4, 4)
                                                .addComponent(spLargoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboUnidadMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkMedidaCerrada)
                                            .addGroup(pnlItemLayout.createSequentialGroup()
                                                .addComponent(lblAltoMedidaCerrada)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(chkFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(pnlItemLayout.createSequentialGroup()
                                                        .addComponent(spAltoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lblLargoMedidaCerrada))))))
                                    .addComponent(chkTiraRetira)
                                    .addGroup(pnlItemLayout.createSequentialGroup()
                                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlItemLayout.createSequentialGroup()
                                                .addComponent(chkTipoUnidad)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cboTipoUnidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(pnlItemLayout.createSequentialGroup()
                                                .addComponent(lblTiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spTiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lblRetiraColor)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlItemLayout.createSequentialGroup()
                                                .addComponent(spRetiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(spFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlItemLayout.createSequentialGroup()
                                                .addComponent(spCantidadTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(chkGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 59, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlItemLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(spLargoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addComponent(chkMaterial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAcabados)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlItemLayout.setVerticalGroup(
            pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreItem)
                    .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAcabados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlItemLayout.createSequentialGroup()
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(chkMedidaCerrada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblAltoMedidaCerrada)
                                    .addComponent(lblLargoMedidaCerrada)
                                    .addComponent(spAltoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spLargoMedidaCerrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(chkMedidaAbierta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboUnidadMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAltoMedidaAbierta)
                                    .addComponent(lblLargoMedidaAbierta)
                                    .addComponent(spAltoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spLargoMedidaAbierta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkTiraRetira)
                            .addComponent(chkFondo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTiraColor)
                            .addComponent(lblRetiraColor)
                            .addComponent(spFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spRetiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTiraColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkGrafico)
                            .addComponent(spCantidadTipoUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(chkServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlItemLayout.createSequentialGroup()
                                .addComponent(schServicioImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(schMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabGrupoItemLayout = new javax.swing.GroupLayout(tabGrupoItem);
        tabGrupoItem.setLayout(tabGrupoItemLayout);
        tabGrupoItemLayout.setHorizontalGroup(
            tabGrupoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabGrupoItemLayout.setVerticalGroup(
            tabGrupoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGrupoItemLayout.createSequentialGroup()
                .addComponent(pnlGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnlGruposItems.addTab("GRUPO/ITEM", tabGrupoItem);

        tabInformacionAdicional.setBackground(java.awt.Color.white);
        tabInformacionAdicional.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblListaPrecioProducto.setText("L.P.PRODUCTO");

        schListaPrecioProducto.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
            }
            @Override
            public void Clear(){
            }
        });
        schListaPrecioProducto.setEnabled(false);

        schListaPrecioServicio.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
            }
            @Override
            public void Clear(){
            }
        });
        schListaPrecioServicio.setEnabled(false);

        lblListaPrecioServicio.setText("L.P.SERVICIO");

        lblListaPrecioMaquina.setText("L.P.MAQUINA");

        schListaPrecioMaquina.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
            }
            @Override
            public void Clear(){
            }
        });
        schListaPrecioMaquina.setEnabled(false);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblContacto.setText("CONTACTO");

        schContacto.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schContactoSearch();
            }
            @Override
            public void Clear(){
            }
        });

        javax.swing.GroupLayout tabInformacionAdicionalLayout = new javax.swing.GroupLayout(tabInformacionAdicional);
        tabInformacionAdicional.setLayout(tabInformacionAdicionalLayout);
        tabInformacionAdicionalLayout.setHorizontalGroup(
            tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaPrecioProducto)
                    .addComponent(lblListaPrecioServicio)
                    .addComponent(lblListaPrecioMaquina))
                .addGap(58, 58, 58)
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContacto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(schContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        tabInformacionAdicionalLayout.setVerticalGroup(
            tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                            .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(schListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(schListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(schListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(304, Short.MAX_VALUE))
        );

        tpnlGruposItems.addTab("INFORMACION ADICIONAL", tabInformacionAdicional);

        btnNuevoGrupoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-16.png"))); // NOI18N
        btnNuevoGrupoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoGrupoItemActionPerformed(evt);
            }
        });

        btnEliminarGrupoItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/delete-16.png"))); // NOI18N
        btnEliminarGrupoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarGrupoItemActionPerformed(evt);
            }
        });

        lblCliente.setText("CLIENTE");

        schCliente.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schClienteSearch();
            }
            @Override
            public void Clear(){
                schClienteClear();
            }
        });

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

        schNumeracion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schNumeracionSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblNumero.setText("N°");

        lblFechaCreacion.setText("F. CREACION");

        schMoneda.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMonedaSearch();
            }
            @Override
            public void Clear(){
            }
        });

        lblMoneda.setText("MONEDA");

        lblFormaPago.setText("F. PAGO");

        schFormaPago.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schFormaPagoSearch();
            }
            @Override
            public void Clear(){
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("SOLICITUD");
        treeGruposItems.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeGruposItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeGruposItemsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(treeGruposItems);

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                        .addComponent(btnNuevoGrupoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminarGrupoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tpnlGruposItems, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(frameLayout.createSequentialGroup()
                                            .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(21, 21, 21)
                                            .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                            .addComponent(lblVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(21, 21, 21)
                                            .addComponent(schVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)
                                        .addComponent(txtDescripcion)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(frameLayout.createSequentialGroup()
                                                .addComponent(lblMoneda)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(frameLayout.createSequentialGroup()
                                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(frameLayout.createSequentialGroup()
                                                .addComponent(lblFechaCreacion)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtNumero)))
                                    .addGroup(frameLayout.createSequentialGroup()
                                        .addComponent(lblFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(schFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(23, 23, 23))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(schCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(schVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaCreacion)
                            .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(schFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tpnlGruposItems, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarGrupoItem)
                            .addComponent(btnNuevoGrupoItem))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

    private void schClienteSearch() {
        VerModal(new lisCliente(1), sele_clie);
    }

    private void schClienteClear() {
        schListaPrecioProducto.asingValues(0, null);
        schListaPrecioServicio.asingValues(0, null);
        schListaPrecioMaquina.asingValues(0, null);
        getEntidad().setIdListaPrecioProducto(0);
        getEntidad().setNombreListaPrecioProducto(null);
        getEntidad().setIdListaPrecioServicio(0);
        getEntidad().setNombreListaPrecioServicio(null);
        getEntidad().setIdListaPrecioMaquina(0);
        getEntidad().setNombreListaPrecioMaquina(null);
    }

    private void schVendedorSearch() {
        VerModal(new lisEmpleado(1), sele_vend);
    }

    private void schNumeracionSearch() {
        String filtro = "WHERE Numeracion.idEntidad = 3";
        VerModal(new lisNumeracion(1, filtro), sele_nume);
    }

    private void schMonedaSearch() {
        VerModal(new lisMoneda(1), sele_mone);
    }

    private void schFormaPagoSearch() {
        VerModal(new lisFormaPago(1), sele_form);
    }

    private void txtNombreGrupoChange() {
        this.grupo.setNombre(txtNombreGrupo.getText());
        treeGruposItems.updateUI();
        tpnlGruposItems.setTitleAt(0, this.grupo.getNombre());
    }

    private void txtNombreItemChange(){
        this.item.setNombre(txtNombreItem.getText());
        treeGruposItems.updateUI();
        tpnlGruposItems.setTitleAt(0, this.item.getNombre());
    }
    
    private void txtAcabadosChange(){
        this.item.setAcabados(txtAcabados.getText());
    }
    
    private void schMaquinaSearch() {
        VerModal(new lisMaquina(1), sele_maqu);
    }

    private void schMaquinaClear() {
        this.item.setIdMaquina(0);
        this.item.setDescripcionMaquina("");
        this.item.setAltoMaximoPliegoMaquina(0);
        this.item.setLargoMaximoPliegoMaquina(0);
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

    private void schContactoSearch() {
        String filtro = "WHERE ContactoCliente.idCliente = " + schCliente.getId();
        VerModal(new lisContactoCliente(filtro), sele_cont);
    }

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkServicioImpresionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkServicioImpresionStateChanged
        // TODO add your handling code here:
        this.item.setServicioImpresion(chkServicioImpresion.isSelected());
        schServicioImpresion.setEnabled(chkServicioImpresion.isSelected());
        schMaquina.setEnabled(chkServicioImpresion.isSelected());
    }//GEN-LAST:event_chkServicioImpresionStateChanged

    private void chkMaterialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMaterialStateChanged
        // TODO add your handling code here:
        this.item.setMaterial(chkMaterial.isSelected());
        schMaterial.setEnabled(chkMaterial.isSelected());
    }//GEN-LAST:event_chkMaterialStateChanged

    private void chkTipoUnidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkTipoUnidadStateChanged
        // TODO add your handling code here:
        this.item.setTipoUnidad(chkTipoUnidad.isSelected());
        cboTipoUnidad.setEnabled(chkTipoUnidad.isSelected());
    }//GEN-LAST:event_chkTipoUnidadStateChanged

    private void chkMedidaAbiertaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMedidaAbiertaStateChanged
        // TODO add your handling code here:
        this.item.setMedidaAbierta(chkMedidaAbierta.isSelected());
        spAltoMedidaAbierta.setEnabled(chkMedidaAbierta.isSelected());
        spLargoMedidaAbierta.setEnabled(chkMedidaAbierta.isSelected());
        cboUnidadMedidaAbierta.setEnabled(chkMedidaAbierta.isSelected());
    }//GEN-LAST:event_chkMedidaAbiertaStateChanged

    private void chkMedidaCerradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMedidaCerradaStateChanged
        // TODO add your handling code here:
        this.item.setMedidaAbierta(chkMedidaCerrada.isSelected());
        spAltoMedidaCerrada.setEnabled(chkMedidaCerrada.isSelected());
        spLargoMedidaCerrada.setEnabled(chkMedidaCerrada.isSelected());
    }//GEN-LAST:event_chkMedidaCerradaStateChanged

    private void chkTiraRetiraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkTiraRetiraStateChanged
        // TODO add your handling code here:
        this.item.setTiraRetira(chkTiraRetira.isSelected());
        spTiraColor.setEnabled(chkTiraRetira.isSelected());
        spRetiraColor.setEnabled(chkTiraRetira.isSelected());
    }//GEN-LAST:event_chkTiraRetiraStateChanged

    private void chkFondoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkFondoStateChanged
        // TODO add your handling code here:
        this.item.setFondo(chkFondo.isSelected());
        spFondo.setEnabled(chkFondo.isSelected());
    }//GEN-LAST:event_chkFondoStateChanged

    private void btnNuevoGrupoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoGrupoItemActionPerformed
        // TODO add your handling code here:
        if (treeGruposItems.getSelectionPath() != null) {
            Object userObject = ObtenerValorNodo(treeGruposItems.getSelectionPath());
            if (userObject instanceof String) {
                VerModal(new lisPlantillaSC(2), sele_plan);
            } else {
                if (userObject instanceof GrupoSolicitudCotizacion) {
                    ItemSolicitudCotizacion item = new ItemSolicitudCotizacion();
                    item.setNombre("ITEM " + (this.grupo.getItems().size() + 1));
                    item.setUnidadMedidaAbierta(cboUnidadMedidaAbierta.getSelectedItem().toString());
                    item.setNombreTipoUnidad(cboTipoUnidad.getSelectedItem().toString());
                    this.grupo.getItems().add(item);
                    AgregarNodo(treeGruposItems, treeGruposItems.getSelectionPath(), item);
                    ExpandirTodosNodos(treeGruposItems);
                }
            }
        }
    }//GEN-LAST:event_btnNuevoGrupoItemActionPerformed

    private void btnEliminarGrupoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarGrupoItemActionPerformed
        // TODO add your handling code here:
        if (treeGruposItems.getSelectionPath() != null) {
            Object userObject = ObtenerValorNodo(treeGruposItems.getSelectionPath());
            if (userObject instanceof GrupoSolicitudCotizacion) {
                GrupoSolicitudCotizacion grupo = (GrupoSolicitudCotizacion) userObject;
                if (getId() == 0) {
                    getEntidad().getGrupos().remove(grupo);
                } else {
                    grupo.setEliminar(true);
                }
                EliminarNodoActivo(treeGruposItems);
            } else if (userObject instanceof ItemSolicitudCotizacion) {
                ItemSolicitudCotizacion item = (ItemSolicitudCotizacion) userObject;
                GrupoSolicitudCotizacion grupo = ObtenerValorNodoPadre(treeGruposItems.getSelectionPath());
                if (grupo.getIdGrupoSolicitudCotizacion() == 0) {
                    grupo.getItems().remove(item);
                } else {
                    item.setEliminar(true);
                }
                EliminarNodoActivo(treeGruposItems);
            }
            OcultarControl(tpnlGruposItems);
        }
    }//GEN-LAST:event_btnEliminarGrupoItemActionPerformed

    private void treeGruposItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeGruposItemsMouseClicked
        // TODO add your handling code here:
        if (treeGruposItems.getSelectionPath() != null) {
            Object userObject = ObtenerValorNodo(treeGruposItems.getSelectionPath());
            if (userObject instanceof GrupoSolicitudCotizacion) {
                VerControl(tpnlGruposItems);
                VerControl(pnlGrupo);
                OcultarControl(pnlItem);
                this.grupo = (GrupoSolicitudCotizacion) userObject;
                AsignarControlesGrupo();
            } else if (userObject instanceof ItemSolicitudCotizacion) {
                VerControl(tpnlGruposItems);
                VerControl(pnlItem);
                OcultarControl(pnlGrupo);
                this.item = (ItemSolicitudCotizacion) userObject;
                AsignarControlesItem();
            }
        }
    }//GEN-LAST:event_treeGruposItemsMouseClicked

    private void cboLineaProduccionGrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLineaProduccionGrupoItemStateChanged
        // TODO add your handling code here:
        this.grupo.setLineaProduccion(cboLineaProduccionGrupo.getSelectedItem().toString());
    }//GEN-LAST:event_cboLineaProduccionGrupoItemStateChanged

    private void spCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spCantidadStateChanged
        // TODO add your handling code here:
        this.grupo.setCantidad((int) spCantidad.getValue());
    }//GEN-LAST:event_spCantidadStateChanged

    private void spAltoMedidaAbiertaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spAltoMedidaAbiertaStateChanged
        // TODO add your handling code here:
        this.item.setAltoMedidaAbierta((double)spAltoMedidaAbierta.getValue());
    }//GEN-LAST:event_spAltoMedidaAbiertaStateChanged

    private void spLargoMedidaAbiertaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spLargoMedidaAbiertaStateChanged
        // TODO add your handling code here:
        this.item.setLargoMedidaAbierta((double)spLargoMedidaAbierta.getValue());
    }//GEN-LAST:event_spLargoMedidaAbiertaStateChanged

    private void spAltoMedidaCerradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spAltoMedidaCerradaStateChanged
        // TODO add your handling code here:
        this.item.setAltoMedidaCerrada((double)spAltoMedidaCerrada.getValue());
    }//GEN-LAST:event_spAltoMedidaCerradaStateChanged

    private void spLargoMedidaCerradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spLargoMedidaCerradaStateChanged
        // TODO add your handling code here:
        this.item.setLargoMedidaCerrada((double)spLargoMedidaCerrada.getValue());
    }//GEN-LAST:event_spLargoMedidaCerradaStateChanged

    private void spTiraColorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spTiraColorStateChanged
        // TODO add your handling code here:
        this.item.setTiraColor((double)spTiraColor.getValue());
    }//GEN-LAST:event_spTiraColorStateChanged

    private void spRetiraColorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spRetiraColorStateChanged
        // TODO add your handling code here:
        this.item.setRetiraColor((double)spRetiraColor.getValue());
    }//GEN-LAST:event_spRetiraColorStateChanged

    private void spFondoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spFondoStateChanged
        // TODO add your handling code here:
        this.item.setdFondo((double)spFondo.getValue());
    }//GEN-LAST:event_spFondoStateChanged

    private void spCantidadTipoUnidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spCantidadTipoUnidadStateChanged
        // TODO add your handling code here:
        this.item.setCantidadTipoUnidad((int)spCantidadTipoUnidad.getValue());
    }//GEN-LAST:event_spCantidadTipoUnidadStateChanged

    private void cboTipoUnidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTipoUnidadItemStateChanged
        // TODO add your handling code here:
        this.item.setNombreTipoUnidad(cboTipoUnidad.getSelectedItem().toString());
    }//GEN-LAST:event_cboTipoUnidadItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarGrupoItem;
    private javax.swing.JButton btnNuevoGrupoItem;
    private javax.swing.JComboBox cboLineaProduccionGrupo;
    private javax.swing.JComboBox cboTipoUnidad;
    private javax.swing.JComboBox cboUnidadMedidaAbierta;
    private javax.swing.JCheckBox chkFondo;
    private javax.swing.JCheckBox chkGrafico;
    private javax.swing.JCheckBox chkMaterial;
    private javax.swing.JCheckBox chkMedidaAbierta;
    private javax.swing.JCheckBox chkMedidaCerrada;
    private javax.swing.JCheckBox chkServicioImpresion;
    private javax.swing.JCheckBox chkTipoUnidad;
    private javax.swing.JCheckBox chkTiraRetira;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAcabados;
    private javax.swing.JLabel lblAltoMedidaAbierta;
    private javax.swing.JLabel lblAltoMedidaCerrada;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblContacto;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFechaCreacion;
    private javax.swing.JLabel lblFormaPago;
    private javax.swing.JLabel lblLargoMedidaAbierta;
    private javax.swing.JLabel lblLargoMedidaCerrada;
    private javax.swing.JLabel lblLineaProduccionGrupo;
    private javax.swing.JLabel lblListaPrecioMaquina;
    private javax.swing.JLabel lblListaPrecioProducto;
    private javax.swing.JLabel lblListaPrecioServicio;
    private javax.swing.JLabel lblMaquina;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNombreGrupo;
    private javax.swing.JLabel lblNombreItem;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblRetiraColor;
    private javax.swing.JLabel lblTiraColor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JPanel pnlGrupo;
    private javax.swing.JPanel pnlItem;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schCliente;
    private com.sge.base.controles.JSearch schContacto;
    private com.sge.base.controles.JSearch schFormaPago;
    private com.sge.base.controles.JSearch schListaPrecioMaquina;
    private com.sge.base.controles.JSearch schListaPrecioProducto;
    private com.sge.base.controles.JSearch schListaPrecioServicio;
    private com.sge.base.controles.JSearch schMaquina;
    private com.sge.base.controles.JSearch schMaterial;
    private com.sge.base.controles.JSearch schMoneda;
    private com.sge.base.controles.JSearch schNumeracion;
    private com.sge.base.controles.JSearch schServicioImpresion;
    private com.sge.base.controles.JSearch schVendedor;
    private javax.swing.JSpinner spAltoMedidaAbierta;
    private javax.swing.JSpinner spAltoMedidaCerrada;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JSpinner spCantidadTipoUnidad;
    private javax.swing.JSpinner spFondo;
    private javax.swing.JSpinner spLargoMedidaAbierta;
    private javax.swing.JSpinner spLargoMedidaCerrada;
    private javax.swing.JSpinner spRetiraColor;
    private javax.swing.JSpinner spTiraColor;
    private javax.swing.JPanel tabGrupoItem;
    private javax.swing.JPanel tabInformacionAdicional;
    private javax.swing.JTabbedPane tpnlGruposItems;
    private javax.swing.JTree treeGruposItems;
    private javax.swing.JTextArea txtAcabados;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JTextField txtNombreGrupo;
    private javax.swing.JTextField txtNombreItem;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
