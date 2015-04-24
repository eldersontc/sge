package com.sge.modulos.ventas.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.Maquina;
import com.sge.modulos.ventas.cliente.cliVentas;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class regMaquina extends frameBase<Maquina> {

    /**
     * Creates new form regMaquina
     */
    public regMaquina(String operacion, int idMaquina) {
        initComponents();
        Init(operacion, idMaquina);
    }

    int idMaquina = 0;
    
    public void Init(String operacion, int idMaquina) {
        lblTitulo.setText(operacion + lblTitulo.getText());
        this.idMaquina = idMaquina;
        if (this.idMaquina > 0) {
            new swObtenerMaquina().execute();
        }
    }
    
    public class swObtenerMaquina extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliVentas cliVentas = new cliVentas();
            try {
                String json = cliVentas.ObtenerMaquina(new Gson().toJson(idMaquina));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Maquina maquina = new Gson().fromJson(resultado[1], Maquina.class);
                    txtDescripcion.setText(maquina.getDescripcion());
                    cboTipoMaquina.setSelectedItem(maquina.getTipoMaquina());
                    txtCantidadCuerpos.setText(String.valueOf(maquina.getCantidadCuerpos()));
                    txtGramajeMinimo.setText(String.valueOf(maquina.getMinimoGramaje()));
                    txtGramajeMaximo.setText(String.valueOf(maquina.getMaximoGramaje()));
                    txtAltoMinimoPliego.setText(String.valueOf(maquina.getAltoMinimoPliego()));
                    txtAltoMaximoPliego.setText(String.valueOf(maquina.getAltoMaximoPliego()));
                    txtAnchoMinimoPliego.setText(String.valueOf(maquina.getAnchoMinimoPliego()));
                    txtAnchoMaximoPliego.setText(String.valueOf(maquina.getAnchoMaximoPliego()));
                    txtMargenPinza.setText(String.valueOf(maquina.getMargenPinza()));
                    txtMargenSalida.setText(String.valueOf(maquina.getMargenSalida()));
                    txtMargenEscuadra.setText(String.valueOf(maquina.getMargenEscuadra()));
                    txtMargenContraEscuadra.setText(String.valueOf(maquina.getMargenContraEscuadra()));
                    txtMargenCalle.setText(String.valueOf(maquina.getMargenCalle()));
                    txtResolucionMinimo.setText(String.valueOf(maquina.getMinimaResolucion()));
                    txtResolucionMaximo.setText(String.valueOf(maquina.getMaximaResolucion()));
                    chkActivo.setSelected(maquina.isActivo());
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

    public class swGuardarMaquina extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliVentas cliVentas = new cliVentas();
            String json = "";
            try {
                Maquina maquina = new Maquina();
                maquina.setDescripcion(txtDescripcion.getText());
                maquina.setTipoMaquina(cboTipoMaquina.getSelectedItem().toString());
                maquina.setCantidadCuerpos(Integer.parseInt(txtCantidadCuerpos.getText()));
                maquina.setMinimoGramaje(Integer.parseInt(txtGramajeMinimo.getText()));
                maquina.setMaximoGramaje(Integer.parseInt(txtGramajeMaximo.getText()));
                maquina.setAltoMinimoPliego(Integer.parseInt(txtAltoMinimoPliego.getText()));
                maquina.setAltoMaximoPliego(Integer.parseInt(txtAltoMaximoPliego.getText()));
                maquina.setAnchoMinimoPliego(Integer.parseInt(txtAnchoMinimoPliego.getText()));
                maquina.setAnchoMaximoPliego(Integer.parseInt(txtAnchoMaximoPliego.getText()));
                maquina.setMargenPinza(Integer.parseInt(txtMargenPinza.getText()));
                maquina.setMargenSalida(Integer.parseInt(txtMargenSalida.getText()));
                maquina.setMargenEscuadra(Integer.parseInt(txtMargenEscuadra.getText()));
                maquina.setMargenContraEscuadra(Integer.parseInt(txtMargenContraEscuadra.getText()));
                maquina.setMargenCalle(Integer.parseInt(txtMargenCalle.getText()));
                maquina.setMinimaResolucion(Integer.parseInt(txtResolucionMinimo.getText()));
                maquina.setMaximaResolucion(Integer.parseInt(txtResolucionMaximo.getText()));
                maquina.setActivo(chkActivo.isSelected());
                if (idMaquina == 0) {
                    json = cliVentas.RegistrarMaquina(new Gson().toJson(maquina));
                } else {
                    maquina.setIdMaquina(idMaquina);
                    json = cliVentas.ActualizarMaquina(new Gson().toJson(maquina));
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
        tabGramaje = new javax.swing.JPanel();
        lblGramajeMinimo = new javax.swing.JLabel();
        txtGramajeMinimo = new javax.swing.JTextField();
        lblGramajeMaximo = new javax.swing.JLabel();
        txtGramajeMaximo = new javax.swing.JTextField();
        tabPliego = new javax.swing.JPanel();
        lblAltoMinimoPliego = new javax.swing.JLabel();
        txtAltoMinimoPliego = new javax.swing.JTextField();
        lblAltoMaximoPliego = new javax.swing.JLabel();
        txtAltoMaximoPliego = new javax.swing.JTextField();
        lblAnchoMinimoPliego = new javax.swing.JLabel();
        txtAnchoMinimoPliego = new javax.swing.JTextField();
        lblAnchoMaximoPliego = new javax.swing.JLabel();
        txtAnchoMaximoPliego = new javax.swing.JTextField();
        tabMargenes = new javax.swing.JPanel();
        lblMargenPinza = new javax.swing.JLabel();
        txtMargenPinza = new javax.swing.JTextField();
        lblMargenSalida = new javax.swing.JLabel();
        txtMargenSalida = new javax.swing.JTextField();
        txtMargenContraEscuadra = new javax.swing.JTextField();
        lblMargenContraEscuadra = new javax.swing.JLabel();
        txtMargenEscuadra = new javax.swing.JTextField();
        lblMargenEscuadra = new javax.swing.JLabel();
        lblMargenCalle = new javax.swing.JLabel();
        txtMargenCalle = new javax.swing.JTextField();
        tabResolucion = new javax.swing.JPanel();
        lblResolucionMinimo = new javax.swing.JLabel();
        txtResolucionMinimo = new javax.swing.JTextField();
        lblResolucionMaximo = new javax.swing.JLabel();
        txtResolucionMaximo = new javax.swing.JTextField();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        chkActivo = new javax.swing.JCheckBox();
        lblCanidadCuerpos = new javax.swing.JLabel();
        txtCantidadCuerpos = new javax.swing.JTextField();
        cboTipoMaquina = new javax.swing.JComboBox();
        lblTipoMaquina = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

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

        tabGramaje.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblGramajeMinimo.setText("MÍNIMO");

        txtGramajeMinimo.setText("0");

        lblGramajeMaximo.setText("MÁXIMO");

        txtGramajeMaximo.setText("0");

        javax.swing.GroupLayout tabGramajeLayout = new javax.swing.GroupLayout(tabGramaje);
        tabGramaje.setLayout(tabGramajeLayout);
        tabGramajeLayout.setHorizontalGroup(
            tabGramajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGramajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGramajeMinimo)
                .addGap(18, 18, 18)
                .addComponent(txtGramajeMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblGramajeMaximo)
                .addGap(18, 18, 18)
                .addComponent(txtGramajeMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        tabGramajeLayout.setVerticalGroup(
            tabGramajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabGramajeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabGramajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabGramajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGramajeMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGramajeMaximo))
                    .addGroup(tabGramajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGramajeMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGramajeMinimo)))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("GRAMAJE", tabGramaje);

        tabPliego.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAltoMinimoPliego.setText("ALTO MÍNIMO");

        txtAltoMinimoPliego.setText("0");

        lblAltoMaximoPliego.setText("ALTO MÁXIMO");

        txtAltoMaximoPliego.setText("0");

        lblAnchoMinimoPliego.setText("ANCHO MÍNIMO");

        txtAnchoMinimoPliego.setText("0");

        lblAnchoMaximoPliego.setText("ANCHO  MÁXIMO");

        txtAnchoMaximoPliego.setText("0");

        javax.swing.GroupLayout tabPliegoLayout = new javax.swing.GroupLayout(tabPliego);
        tabPliego.setLayout(tabPliegoLayout);
        tabPliegoLayout.setHorizontalGroup(
            tabPliegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPliegoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPliegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabPliegoLayout.createSequentialGroup()
                        .addComponent(lblAltoMinimoPliego)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAltoMinimoPliego, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabPliegoLayout.createSequentialGroup()
                        .addComponent(lblAnchoMinimoPliego)
                        .addGap(18, 18, 18)
                        .addComponent(txtAnchoMinimoPliego, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(tabPliegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabPliegoLayout.createSequentialGroup()
                        .addComponent(lblAnchoMaximoPliego)
                        .addGap(18, 18, 18)
                        .addComponent(txtAnchoMaximoPliego, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabPliegoLayout.createSequentialGroup()
                        .addComponent(lblAltoMaximoPliego)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAltoMaximoPliego, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        tabPliegoLayout.setVerticalGroup(
            tabPliegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPliegoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPliegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAltoMinimoPliego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAltoMinimoPliego)
                    .addComponent(txtAltoMaximoPliego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAltoMaximoPliego))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPliegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnchoMinimoPliego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnchoMinimoPliego)
                    .addComponent(txtAnchoMaximoPliego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnchoMaximoPliego))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PLIEGO", tabPliego);

        tabMargenes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblMargenPinza.setText("PINZA");

        txtMargenPinza.setText("0");

        lblMargenSalida.setText("SALIDA");

        txtMargenSalida.setText("0");

        txtMargenContraEscuadra.setText("0");

        lblMargenContraEscuadra.setText("CONTRA ESCUADRA");

        txtMargenEscuadra.setText("0");

        lblMargenEscuadra.setText("ESCUADRA");

        lblMargenCalle.setText("CALLE");

        txtMargenCalle.setText("0");

        javax.swing.GroupLayout tabMargenesLayout = new javax.swing.GroupLayout(tabMargenes);
        tabMargenes.setLayout(tabMargenesLayout);
        tabMargenesLayout.setHorizontalGroup(
            tabMargenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMargenesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMargenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabMargenesLayout.createSequentialGroup()
                        .addComponent(lblMargenCalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMargenCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabMargenesLayout.createSequentialGroup()
                        .addComponent(lblMargenPinza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMargenPinza, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabMargenesLayout.createSequentialGroup()
                        .addComponent(lblMargenEscuadra)
                        .addGap(18, 18, 18)
                        .addComponent(txtMargenEscuadra, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(tabMargenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tabMargenesLayout.createSequentialGroup()
                        .addComponent(lblMargenContraEscuadra)
                        .addGap(18, 18, 18)
                        .addComponent(txtMargenContraEscuadra, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabMargenesLayout.createSequentialGroup()
                        .addComponent(lblMargenSalida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMargenSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        tabMargenesLayout.setVerticalGroup(
            tabMargenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMargenesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMargenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMargenPinza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMargenPinza)
                    .addComponent(txtMargenSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMargenSalida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMargenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMargenEscuadra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMargenEscuadra)
                    .addComponent(txtMargenContraEscuadra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMargenContraEscuadra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMargenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMargenCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMargenCalle))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MÁRGENES", tabMargenes);

        tabResolucion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblResolucionMinimo.setText("MíNIMO");

        txtResolucionMinimo.setText("0");

        lblResolucionMaximo.setText("MÁXIMO");

        txtResolucionMaximo.setText("0");

        javax.swing.GroupLayout tabResolucionLayout = new javax.swing.GroupLayout(tabResolucion);
        tabResolucion.setLayout(tabResolucionLayout);
        tabResolucionLayout.setHorizontalGroup(
            tabResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabResolucionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblResolucionMinimo)
                .addGap(18, 18, 18)
                .addComponent(txtResolucionMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblResolucionMaximo)
                .addGap(18, 18, 18)
                .addComponent(txtResolucionMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        tabResolucionLayout.setVerticalGroup(
            tabResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabResolucionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtResolucionMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblResolucionMaximo))
                    .addGroup(tabResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtResolucionMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblResolucionMinimo)))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("RESOLUCIÓN", tabResolucion);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/add-list-16.png"))); // NOI18N
        lblTitulo.setText("MÁQUINA");

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

        lblCanidadCuerpos.setText("N° CUERPOS");

        txtCantidadCuerpos.setText("0");

        cboTipoMaquina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "OFFSET" }));

        lblTipoMaquina.setText("TIPO");

        lblDescripcion.setText("DESCRIPCIÓN");

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTipoMaquina)
                                .addComponent(lblCanidadCuerpos))
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCantidadCuerpos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(frameLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(frameLayout.createSequentialGroup()
                                            .addComponent(cboTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(chkActivo))
                                        .addComponent(txtDescripcion)))))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoMaquina)
                    .addComponent(chkActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidadCuerpos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCanidadCuerpos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swGuardarMaquina().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cboTipoMaquina;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JPanel frame;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAltoMaximoPliego;
    private javax.swing.JLabel lblAltoMinimoPliego;
    private javax.swing.JLabel lblAnchoMaximoPliego;
    private javax.swing.JLabel lblAnchoMinimoPliego;
    private javax.swing.JLabel lblCanidadCuerpos;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblGramajeMaximo;
    private javax.swing.JLabel lblGramajeMinimo;
    private javax.swing.JLabel lblMargenCalle;
    private javax.swing.JLabel lblMargenContraEscuadra;
    private javax.swing.JLabel lblMargenEscuadra;
    private javax.swing.JLabel lblMargenPinza;
    private javax.swing.JLabel lblMargenSalida;
    private javax.swing.JLabel lblResolucionMaximo;
    private javax.swing.JLabel lblResolucionMinimo;
    private javax.swing.JLabel lblTipoMaquina;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel tabGramaje;
    private javax.swing.JPanel tabMargenes;
    private javax.swing.JPanel tabPliego;
    private javax.swing.JPanel tabResolucion;
    private javax.swing.JTextField txtAltoMaximoPliego;
    private javax.swing.JTextField txtAltoMinimoPliego;
    private javax.swing.JTextField txtAnchoMaximoPliego;
    private javax.swing.JTextField txtAnchoMinimoPliego;
    private javax.swing.JTextField txtCantidadCuerpos;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtGramajeMaximo;
    private javax.swing.JTextField txtGramajeMinimo;
    private javax.swing.JTextField txtMargenCalle;
    private javax.swing.JTextField txtMargenContraEscuadra;
    private javax.swing.JTextField txtMargenEscuadra;
    private javax.swing.JTextField txtMargenPinza;
    private javax.swing.JTextField txtMargenSalida;
    private javax.swing.JTextField txtResolucionMaximo;
    private javax.swing.JTextField txtResolucionMinimo;
    // End of variables declaration//GEN-END:variables
}
