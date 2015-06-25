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
        tpnlGruposItems.remove(tabGrupo);
        tpnlGruposItems.remove(tabItem);
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
            if (grupo.getIdGrupoSolicitudCotizacion() == 0) {
                grupo.setAgregar(true);
            } else if (!grupo.isEliminar()) {
                grupo.setActualizar(true);
            }
            for (ItemSolicitudCotizacion item : grupo.getItems()) {
                if (item.getIdItemSolicitudCotizacion() == 0) {
                    item.setAgregar(true);
                } else if (!item.isEliminar()) {
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
        getEntidad().setIdFormaPago(schFormaPago.getId());
        getEntidad().setDescripcionFormaPago(schFormaPago.getText());
        getEntidad().setDescripcion(txtDescripcion.getText());
        getEntidad().setIdContactoCliente(schContacto.getId());
        getEntidad().setNombreContactoCliente(schContacto.getText());
        getEntidad().setObservacion(txtObservacion.getText());
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
        txtObservacion.setText(getEntidad().getObservacion());
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
        AsignarTexto(txtNombreGrupo, this.grupo.getNombre());
        AsignarItemActivo(cboLineaProduccionGrupo, this.grupo.getLineaProduccion());
        AsignarValor(spCantidad, this.grupo.getCantidad());
    }

    private void AsignarControlesItem() {
        AsignarTexto(txtNombreItem, this.item.getNombre());
        AsignarSeleccion(chkGrafico, this.item.isGrafico());
        
        AsignarSeleccion(chkMedidaAbierta, this.item.isMedidaAbierta());
        HabilitarControles(new Object[]{spAltoMedidaAbierta, spLargoMedidaAbierta, cboUnidadMedidaAbierta}, this.item.isMedidaAbierta());
        AsignarValor(spAltoMedidaAbierta, this.item.getAltoMedidaAbierta());
        AsignarValor(spLargoMedidaAbierta, this.item.getLargoMedidaAbierta());
        AsignarItemActivo(cboUnidadMedidaAbierta, this.item.getUnidadMedidaAbierta());
        
        AsignarSeleccion(chkMedidaCerrada, this.item.isMedidaCerrada());
        HabilitarControles(new Object[]{spAltoMedidaCerrada, spLargoMedidaCerrada}, this.item.isMedidaCerrada());
        AsignarValor(spAltoMedidaCerrada, this.item.getAltoMedidaCerrada());
        AsignarValor(spLargoMedidaCerrada, this.item.getLargoMedidaCerrada());
        
        AsignarSeleccion(chkTiraRetira, this.item.isTiraRetira());
        HabilitarControles(new Object[]{spTiraColor, spRetiraColor}, this.item.isTiraRetira());
        AsignarValor(spTiraColor, this.item.getTiraColor());
        AsignarValor(spRetiraColor, this.item.getRetiraColor());
        
        AsignarSeleccion(chkTipoUnidad, this.item.isTipoUnidad());
        HabilitarControles(new Object[]{cboTipoUnidad, spCantidadTipoUnidad}, this.item.isTipoUnidad());
        AsignarItemActivo(cboTipoUnidad, this.item.getNombreTipoUnidad());
        AsignarValor(spCantidadTipoUnidad, this.item.getCantidadTipoUnidad());
        
        AsignarSeleccion(chkFondo, this.item.isFondo());
        HabilitarControles(new Object[]{spFondo}, this.item.isFondo());
        AsignarValor(spFondo, this.item.getdFondo());
        
        AsignarSeleccion(chkServicioImpresion, this.item.isServicioImpresion());
        HabilitarControles(new Object[]{schServicioImpresion,schMaquina}, this.item.isServicioImpresion());
        schServicioImpresion.asingValues(this.item.getIdServicioImpresion(), this.item.getNombreServicioImpresion());
        schMaquina.asingValues(this.item.getIdMaquina(), this.item.getDescripcionMaquina());
        
        AsignarSeleccion(chkMaterial, this.item.isMaterial());
        HabilitarControles(new Object[]{schMaterial}, this.item.isMaterial());
        schMaterial.asingValues(this.item.getIdMaterial(), this.item.getNombreMaterial());
        
        AsignarTexto(txtAcabados, this.item.getAcabados());
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
        tabGrupo = new javax.swing.JPanel();
        lblNombreGrupo = new javax.swing.JLabel();
        txtNombreGrupo = new javax.swing.JTextField();
        lblLineaProduccionGrupo = new javax.swing.JLabel();
        cboLineaProduccionGrupo = new javax.swing.JComboBox();
        lblCantidad = new javax.swing.JLabel();
        spCantidad = new javax.swing.JSpinner();
        tabItem = new javax.swing.JPanel();
        lblNombreItem = new javax.swing.JLabel();
        txtNombreItem = new javax.swing.JTextField();
        chkGrafico = new javax.swing.JCheckBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        tabMedidas = new javax.swing.JPanel();
        chkMedidaAbierta = new javax.swing.JCheckBox();
        lblAltoMedidaAbierta = new javax.swing.JLabel();
        spAltoMedidaAbierta = new javax.swing.JSpinner();
        lblLargoMedidaAbierta = new javax.swing.JLabel();
        spLargoMedidaAbierta = new javax.swing.JSpinner();
        cboUnidadMedidaAbierta = new javax.swing.JComboBox();
        chkMedidaCerrada = new javax.swing.JCheckBox();
        lblAltoMedidaCerrada = new javax.swing.JLabel();
        spAltoMedidaCerrada = new javax.swing.JSpinner();
        lblLargoMedidaCerrada = new javax.swing.JLabel();
        spLargoMedidaCerrada = new javax.swing.JSpinner();
        chkTiraRetira = new javax.swing.JCheckBox();
        lblTiraColor = new javax.swing.JLabel();
        spTiraColor = new javax.swing.JSpinner();
        lblRetiraColor = new javax.swing.JLabel();
        spRetiraColor = new javax.swing.JSpinner();
        chkFondo = new javax.swing.JCheckBox();
        spFondo = new javax.swing.JSpinner();
        chkTipoUnidad = new javax.swing.JCheckBox();
        cboTipoUnidad = new javax.swing.JComboBox();
        spCantidadTipoUnidad = new javax.swing.JSpinner();
        chkServicioImpresion = new javax.swing.JCheckBox();
        schServicioImpresion = new com.sge.base.controles.JSearch();
        schMaquina = new com.sge.base.controles.JSearch();
        chkMaterial = new javax.swing.JCheckBox();
        schMaterial = new com.sge.base.controles.JSearch();
        tabAcabados = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAcabados = new javax.swing.JTextArea();
        tabObservacion = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        btnNuevoGrupoItem = new javax.swing.JButton();
        btnEliminarGrupoItem = new javax.swing.JButton();
        lblCliente = new javax.swing.JLabel();
        schCliente = new com.sge.base.controles.JSearch();
        lblVendedor = new javax.swing.JLabel();
        schVendedor = new com.sge.base.controles.JSearch();
        schNumeracion = new com.sge.base.controles.JSearch();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
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

        lblDescripcion.setText("DESCRIPCIÓN");

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

        lblListaPrecioMaquina.setText("L.P.MÁQUINA");

        schListaPrecioMaquina.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
            }
            @Override
            public void Clear(){
            }
        });
        schListaPrecioMaquina.setEnabled(false);

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
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                        .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblListaPrecioProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblListaPrecioServicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblListaPrecioMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(schListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(schListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(schListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(344, Short.MAX_VALUE))
        );
        tabInformacionAdicionalLayout.setVerticalGroup(
            tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                        .addComponent(lblListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabInformacionAdicionalLayout.createSequentialGroup()
                        .addComponent(schListaPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(schListaPrecioServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabInformacionAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schListaPrecioMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tpnlGruposItems.addTab("INFORMACION ADICIONAL", tabInformacionAdicional);

        tabGrupo.setBackground(java.awt.Color.white);
        tabGrupo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout tabGrupoLayout = new javax.swing.GroupLayout(tabGrupo);
        tabGrupo.setLayout(tabGrupoLayout);
        tabGrupoLayout.setHorizontalGroup(
            tabGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGrupoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(tabGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLineaProduccionGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreGrupo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabGrupoLayout.createSequentialGroup()
                        .addComponent(cboLineaProduccionGrupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidad)
                        .addGap(18, 18, 18)
                        .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombreGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabGrupoLayout.setVerticalGroup(
            tabGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGrupoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreGrupo)
                    .addComponent(txtNombreGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(tabGrupoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLineaProduccionGrupo)
                    .addComponent(cboLineaProduccionGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad)
                    .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnlGruposItems.addTab("", tabGrupo);

        tabItem.setBackground(java.awt.Color.white);
        tabItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        chkGrafico.setText("GRÁFICO");

        tabMedidas.setBackground(java.awt.Color.white);
        tabMedidas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabMedidas.setLayout(null);

        chkMedidaAbierta.setText("MEDIDA ABIERTA");
        chkMedidaAbierta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkMedidaAbiertaItemStateChanged(evt);
            }
        });
        tabMedidas.add(chkMedidaAbierta);
        chkMedidaAbierta.setBounds(20, 12, 201, 24);

        lblAltoMedidaAbierta.setText("ALTO");
        tabMedidas.add(lblAltoMedidaAbierta);
        lblAltoMedidaAbierta.setBounds(20, 47, 38, 17);

        spAltoMedidaAbierta.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spAltoMedidaAbierta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spAltoMedidaAbiertaStateChanged(evt);
            }
        });
        tabMedidas.add(spAltoMedidaAbierta);
        spAltoMedidaAbierta.setBounds(70, 42, 84, 28);

        lblLargoMedidaAbierta.setText("LARGO");
        tabMedidas.add(lblLargoMedidaAbierta);
        lblLargoMedidaAbierta.setBounds(174, 47, 47, 17);

        spLargoMedidaAbierta.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spLargoMedidaAbierta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spLargoMedidaAbiertaStateChanged(evt);
            }
        });
        tabMedidas.add(spLargoMedidaAbierta);
        spLargoMedidaAbierta.setBounds(233, 42, 87, 28);

        cboUnidadMedidaAbierta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CM", "MT" }));
        tabMedidas.add(cboUnidadMedidaAbierta);
        cboUnidadMedidaAbierta.setBounds(326, 42, 60, 27);

        chkMedidaCerrada.setText("MEDIDA CERRADA");
        chkMedidaCerrada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkMedidaCerradaItemStateChanged(evt);
            }
        });
        tabMedidas.add(chkMedidaCerrada);
        chkMedidaCerrada.setBounds(398, 12, 149, 24);

        lblAltoMedidaCerrada.setText("ALTO");
        tabMedidas.add(lblAltoMedidaCerrada);
        lblAltoMedidaCerrada.setBounds(398, 47, 38, 17);

        spAltoMedidaCerrada.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spAltoMedidaCerrada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spAltoMedidaCerradaStateChanged(evt);
            }
        });
        tabMedidas.add(spAltoMedidaCerrada);
        spAltoMedidaCerrada.setBounds(448, 42, 84, 28);

        lblLargoMedidaCerrada.setText("LARGO");
        tabMedidas.add(lblLargoMedidaCerrada);
        lblLargoMedidaCerrada.setBounds(544, 47, 47, 17);

        spLargoMedidaCerrada.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spLargoMedidaCerrada.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spLargoMedidaCerradaStateChanged(evt);
            }
        });
        tabMedidas.add(spLargoMedidaCerrada);
        spLargoMedidaCerrada.setBounds(603, 42, 86, 28);

        chkTiraRetira.setText("TIRA Y RETIRA (COLOR)");
        chkTiraRetira.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTiraRetiraItemStateChanged(evt);
            }
        });
        tabMedidas.add(chkTiraRetira);
        chkTiraRetira.setBounds(20, 78, 201, 24);

        lblTiraColor.setText("TIRA");
        tabMedidas.add(lblTiraColor);
        lblTiraColor.setBounds(20, 116, 38, 17);

        spTiraColor.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spTiraColor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spTiraColorStateChanged(evt);
            }
        });
        tabMedidas.add(spTiraColor);
        spTiraColor.setBounds(70, 111, 84, 28);

        lblRetiraColor.setText("RETIRA");
        tabMedidas.add(lblRetiraColor);
        lblRetiraColor.setBounds(174, 116, 47, 17);

        spRetiraColor.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spRetiraColor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spRetiraColorStateChanged(evt);
            }
        });
        tabMedidas.add(spRetiraColor);
        spRetiraColor.setBounds(233, 111, 87, 28);

        chkFondo.setText("FONDO");
        chkFondo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkFondoItemStateChanged(evt);
            }
        });
        tabMedidas.add(chkFondo);
        chkFondo.setBounds(398, 147, 98, 24);

        spFondo.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(0.0d), null, null, Double.valueOf(1.0d)));
        spFondo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spFondoStateChanged(evt);
            }
        });
        tabMedidas.add(spFondo);
        spFondo.setBounds(603, 146, 86, 28);

        chkTipoUnidad.setText("TIPO UNIDAD");
        chkTipoUnidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTipoUnidadItemStateChanged(evt);
            }
        });
        tabMedidas.add(chkTipoUnidad);
        chkTipoUnidad.setBounds(398, 76, 117, 29);

        cboTipoUnidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UNIDADES", "PAGINAS", "JUEGOS", "ORIGINALES", "OTROS" }));
        cboTipoUnidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTipoUnidadItemStateChanged(evt);
            }
        });
        tabMedidas.add(cboTipoUnidad);
        cboTipoUnidad.setBounds(398, 111, 134, 27);

        spCantidadTipoUnidad.setModel(new javax.swing.SpinnerNumberModel());
        spCantidadTipoUnidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spCantidadTipoUnidadStateChanged(evt);
            }
        });
        tabMedidas.add(spCantidadTipoUnidad);
        spCantidadTipoUnidad.setBounds(603, 111, 86, 28);

        chkServicioImpresion.setText("IMPRESIÓN");
        chkServicioImpresion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkServicioImpresion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkServicioImpresionItemStateChanged(evt);
            }
        });
        tabMedidas.add(chkServicioImpresion);
        chkServicioImpresion.setBounds(20, 145, 102, 29);

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
        tabMedidas.add(schServicioImpresion);
        schServicioImpresion.setBounds(20, 180, 366, 29);

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
        tabMedidas.add(schMaquina);
        schMaquina.setBounds(20, 215, 366, 29);

        chkMaterial.setText("MATERIAL");
        chkMaterial.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMaterial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkMaterialItemStateChanged(evt);
            }
        });
        tabMedidas.add(chkMaterial);
        chkMaterial.setBounds(398, 180, 98, 29);

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
        tabMedidas.add(schMaterial);
        schMaterial.setBounds(398, 215, 289, 29);

        jTabbedPane1.addTab("MEDIDAS", tabMedidas);

        tabAcabados.setBackground(java.awt.Color.white);
        tabAcabados.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout tabAcabadosLayout = new javax.swing.GroupLayout(tabAcabados);
        tabAcabados.setLayout(tabAcabadosLayout);
        tabAcabadosLayout.setHorizontalGroup(
            tabAcabadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
        );
        tabAcabadosLayout.setVerticalGroup(
            tabAcabadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ACABADOS", tabAcabados);

        javax.swing.GroupLayout tabItemLayout = new javax.swing.GroupLayout(tabItem);
        tabItem.setLayout(tabItemLayout);
        tabItemLayout.setHorizontalGroup(
            tabItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabItemLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(tabItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tabItemLayout.createSequentialGroup()
                        .addComponent(lblNombreItem)
                        .addGap(70, 70, 70)
                        .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabItemLayout.setVerticalGroup(
            tabItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreItem)
                    .addComponent(txtNombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkGrafico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnlGruposItems.addTab("", tabItem);

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane2.setViewportView(txtObservacion);

        javax.swing.GroupLayout tabObservacionLayout = new javax.swing.GroupLayout(tabObservacion);
        tabObservacion.setLayout(tabObservacionLayout);
        tabObservacionLayout.setHorizontalGroup(
            tabObservacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );
        tabObservacionLayout.setVerticalGroup(
            tabObservacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );

        tpnlGruposItems.addTab("OBSERVACIÓN", tabObservacion);

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
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(lblFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(schFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(68, 68, 68))
                        .addGroup(frameLayout.createSequentialGroup()
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                    .addComponent(btnNuevoGrupoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnEliminarGrupoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(12, 12, 12)
                            .addComponent(tpnlGruposItems, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(frameLayout.createSequentialGroup()
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(schVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                .addComponent(lblMoneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(40, 40, 40)
                                .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                                .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(schNumeracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarGrupoItem, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNuevoGrupoItem, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(tpnlGruposItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void txtNombreItemChange() {
        this.item.setNombre(txtNombreItem.getText());
        treeGruposItems.updateUI();
        tpnlGruposItems.setTitleAt(0, this.item.getNombre());
    }

    private void txtAcabadosChange() {
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

    private void schServicioImpresionClear() {
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
            tpnlGruposItems.remove(tabGrupo);
            tpnlGruposItems.remove(tabItem);
        }
    }//GEN-LAST:event_btnEliminarGrupoItemActionPerformed

    private void treeGruposItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeGruposItemsMouseClicked
        // TODO add your handling code here:
        if (treeGruposItems.getSelectionPath() != null) {
            Object userObject = ObtenerValorNodo(treeGruposItems.getSelectionPath());
            if (userObject instanceof GrupoSolicitudCotizacion) {
                tpnlGruposItems.remove(tabItem);
                this.grupo = (GrupoSolicitudCotizacion) userObject;
                tpnlGruposItems.insertTab(this.grupo.getNombre(), null, tabGrupo, null, 0);
                tpnlGruposItems.setSelectedIndex(0);
                AsignarControlesGrupo();
            } else if (userObject instanceof ItemSolicitudCotizacion) {
                tpnlGruposItems.remove(tabGrupo);
                this.item = (ItemSolicitudCotizacion) userObject;
                tpnlGruposItems.insertTab(this.item.getNombre(), null, tabItem, null, 0);
                tpnlGruposItems.setSelectedIndex(0);
                AsignarControlesItem();
            } else {
                tpnlGruposItems.remove(tabGrupo);
                tpnlGruposItems.remove(tabItem);
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
        this.item.setAltoMedidaAbierta((double) spAltoMedidaAbierta.getValue());
    }//GEN-LAST:event_spAltoMedidaAbiertaStateChanged

    private void spLargoMedidaAbiertaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spLargoMedidaAbiertaStateChanged
        // TODO add your handling code here:
        this.item.setLargoMedidaAbierta((double) spLargoMedidaAbierta.getValue());
    }//GEN-LAST:event_spLargoMedidaAbiertaStateChanged

    private void spAltoMedidaCerradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spAltoMedidaCerradaStateChanged
        // TODO add your handling code here:
        this.item.setAltoMedidaCerrada((double) spAltoMedidaCerrada.getValue());
    }//GEN-LAST:event_spAltoMedidaCerradaStateChanged

    private void spLargoMedidaCerradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spLargoMedidaCerradaStateChanged
        // TODO add your handling code here:
        this.item.setLargoMedidaCerrada((double) spLargoMedidaCerrada.getValue());
    }//GEN-LAST:event_spLargoMedidaCerradaStateChanged

    private void spTiraColorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spTiraColorStateChanged
        // TODO add your handling code here:
        this.item.setTiraColor((double) spTiraColor.getValue());
    }//GEN-LAST:event_spTiraColorStateChanged

    private void spRetiraColorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spRetiraColorStateChanged
        // TODO add your handling code here:
        this.item.setRetiraColor((double) spRetiraColor.getValue());
    }//GEN-LAST:event_spRetiraColorStateChanged

    private void spFondoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spFondoStateChanged
        // TODO add your handling code here:
        this.item.setdFondo((double) spFondo.getValue());
    }//GEN-LAST:event_spFondoStateChanged

    private void spCantidadTipoUnidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spCantidadTipoUnidadStateChanged
        // TODO add your handling code here:
        this.item.setCantidadTipoUnidad((int) spCantidadTipoUnidad.getValue());
    }//GEN-LAST:event_spCantidadTipoUnidadStateChanged

    private void cboTipoUnidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTipoUnidadItemStateChanged
        // TODO add your handling code here:
        this.item.setNombreTipoUnidad(cboTipoUnidad.getSelectedItem().toString());
    }//GEN-LAST:event_cboTipoUnidadItemStateChanged

    private void chkMedidaAbiertaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkMedidaAbiertaItemStateChanged
        // TODO add your handling code here:
        this.item.setMedidaAbierta(chkMedidaAbierta.isSelected());
        HabilitarControles(new Object[]{spAltoMedidaAbierta, spLargoMedidaAbierta, cboUnidadMedidaAbierta}, this.item.isMedidaAbierta());
    }//GEN-LAST:event_chkMedidaAbiertaItemStateChanged

    private void chkMedidaCerradaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkMedidaCerradaItemStateChanged
        // TODO add your handling code here:
        this.item.setMedidaCerrada(chkMedidaCerrada.isSelected());
        HabilitarControles(new Object[]{spAltoMedidaCerrada, spLargoMedidaCerrada}, this.item.isMedidaCerrada());
    }//GEN-LAST:event_chkMedidaCerradaItemStateChanged

    private void chkTiraRetiraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTiraRetiraItemStateChanged
        // TODO add your handling code here:
        this.item.setTiraRetira(chkTiraRetira.isSelected());
        HabilitarControles(new Object[]{spTiraColor, spRetiraColor}, this.item.isTiraRetira());
    }//GEN-LAST:event_chkTiraRetiraItemStateChanged

    private void chkTipoUnidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTipoUnidadItemStateChanged
        // TODO add your handling code here:
        this.item.setTipoUnidad(chkTipoUnidad.isSelected());
        HabilitarControles(new Object[]{cboTipoUnidad, spCantidadTipoUnidad}, this.item.isTipoUnidad());
    }//GEN-LAST:event_chkTipoUnidadItemStateChanged

    private void chkServicioImpresionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkServicioImpresionItemStateChanged
        // TODO add your handling code here:
        this.item.setServicioImpresion(chkServicioImpresion.isSelected());
        HabilitarControles(new Object[]{schServicioImpresion, schMaquina}, this.item.isServicioImpresion());
    }//GEN-LAST:event_chkServicioImpresionItemStateChanged

    private void chkFondoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkFondoItemStateChanged
        // TODO add your handling code here:
        this.item.setFondo(chkFondo.isSelected());
        HabilitarControles(new Object[]{spFondo}, this.item.isFondo());
    }//GEN-LAST:event_chkFondoItemStateChanged

    private void chkMaterialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkMaterialItemStateChanged
        // TODO add your handling code here:
        this.item.setMaterial(chkMaterial.isSelected());
        HabilitarControles(new Object[]{schMaterial}, this.item.isMaterial());
    }//GEN-LAST:event_chkMaterialItemStateChanged

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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAltoMedidaAbierta;
    private javax.swing.JLabel lblAltoMedidaCerrada;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblContacto;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFormaPago;
    private javax.swing.JLabel lblLargoMedidaAbierta;
    private javax.swing.JLabel lblLargoMedidaCerrada;
    private javax.swing.JLabel lblLineaProduccionGrupo;
    private javax.swing.JLabel lblListaPrecioMaquina;
    private javax.swing.JLabel lblListaPrecioProducto;
    private javax.swing.JLabel lblListaPrecioServicio;
    private javax.swing.JLabel lblMoneda;
    private javax.swing.JLabel lblNombreGrupo;
    private javax.swing.JLabel lblNombreItem;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblRetiraColor;
    private javax.swing.JLabel lblTiraColor;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVendedor;
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
    private javax.swing.JPanel tabAcabados;
    private javax.swing.JPanel tabGrupo;
    private javax.swing.JPanel tabInformacionAdicional;
    private javax.swing.JPanel tabItem;
    private javax.swing.JPanel tabMedidas;
    private javax.swing.JPanel tabObservacion;
    private javax.swing.JTabbedPane tpnlGruposItems;
    private javax.swing.JTree treeGruposItems;
    private javax.swing.JTextArea txtAcabados;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JFormattedTextField txtFechaCreacion;
    private javax.swing.JTextField txtNombreGrupo;
    private javax.swing.JTextField txtNombreItem;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextArea txtObservacion;
    // End of variables declaration//GEN-END:variables
}
