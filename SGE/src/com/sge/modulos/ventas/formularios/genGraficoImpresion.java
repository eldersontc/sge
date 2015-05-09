package com.sge.modulos.ventas.formularios;

import com.sge.base.controles.SearchListener;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.Cotizacion;
import com.sge.modulos.ventas.clases.ItemCotizacion;
import com.sge.modulos.ventas.clases.MetodoImpresion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author elderson
 */
public class genGraficoImpresion extends frameBase<Cotizacion> {

    /**
     * Creates new form genGraficoImpresion
     */
    public genGraficoImpresion(ItemCotizacion item) {
        initComponents();
        Init(item);
    }

    private int aGrafi = 0;
    private int lGrafi = 0;
    private int aPieza = 0;
    private int lPieza = 0;
    private int separx = 0;
    private int separy = 0;

    private ItemCotizacion item;

    public void Init(ItemCotizacion item) {
        this.item = item;
        AsignarControles();
    }

    public void AsignarControles() {
        txtMedidasMaterial.setText(String.format("%s X %s CM.", this.item.getAltoFormatoImpresion(), this.item.getLargoFormatoImpresion()));
        txtAltoPieza.setText(String.valueOf(this.item.getAltoMedidaAbierta()));
        txtLargoPieza.setText(String.valueOf(this.item.getLargoMedidaAbierta()));
        txtSeparacionX.setText(String.valueOf(this.item.getSeparacionX()));
        txtSeparacionY.setText(String.valueOf(this.item.getSeparacionY()));
        txtCantidadPiezas.setText(String.valueOf(this.item.getCantidadPiezasImpresion()));
        schMetodoImpresion.asingValues(this.item.getIdMetodoImpresion(), this.item.getNombre());
        txtCantidadPases.setText(String.valueOf(this.item.getCantidadPases()));
        txtCantidadCambios.setText(String.valueOf(this.item.getCantidadCambios()));
        txtTiraje.setText(String.valueOf(this.item.getCantidadProduccion()));
        txtOtros.setText(String.format("%s PLIEGOS DE : %s HJS/MAQUINA \n", this.item.getCantidadPliegos(), (this.item.getCantidadMaterial() + this.item.getCantidadDemasia()) * this.item.getCantidadPiezasPrecorte()));
        if (this.item.getGraficoImpresion() != null) {
            lblGrafico.setIcon(new ImageIcon(this.item.getGraficoImpresion()));
        }
    }

    public BufferedImage CambiarDimensiones(BufferedImage imagen, int largo, int alto) {
        int largoImagen = imagen.getWidth();
        int altoImagen = imagen.getHeight();
        if (largoImagen * alto < altoImagen * largo) {
            largo = largoImagen * alto / altoImagen;
        } else {
            alto = altoImagen * largo / largoImagen;
        }
        BufferedImage nuevaImagen = new BufferedImage(largo, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D grafico = nuevaImagen.createGraphics();
        try {
            grafico.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            grafico.clearRect(0, 0, largo, alto);
            grafico.drawImage(imagen, 0, 0, largo, alto, null);
        } finally {
            grafico.dispose();
        }
        return nuevaImagen;
    }

    public void GenerarGrafico() throws Exception {

        aGrafi = (int) (Math.min(this.item.getAltoFormatoImpresion(), this.item.getLargoFormatoImpresion()) * 10) / this.item.getFactorVertical();
        lGrafi = (int) (Math.max(this.item.getAltoFormatoImpresion(), this.item.getLargoFormatoImpresion()) * 10) / this.item.getFactorHorizontal();

        separx = (int) (this.item.getSeparacionX() * 10);
        separy = (int) (this.item.getSeparacionY() * 10);

        if (this.item.isGraficoImpresionGirado()) {
            aPieza = (int) (this.item.getLargoMedidaAbierta() * 10);
            lPieza = (int) (this.item.getAltoMedidaAbierta() * 10);
        } else {
            aPieza = (int) (this.item.getAltoMedidaAbierta() * 10);
            lPieza = (int) (this.item.getLargoMedidaAbierta() * 10);
        }

        if (this.item.getIdMetodoImpresion() == 0) {
            throw new Exception("DEBE DE SELECCIONAR UN METODO DE IMPRESION.");
        }
        if (aGrafi <= 0) {
            throw new Exception("ALTO DE MATERIAL NO PUEDE SER 0.");
        }
        if (lGrafi <= 0) {
            throw new Exception("LARGO DE MATERIAL NO PUEDE SER 0.");
        }
        if (aPieza <= 0) {
            throw new Exception("ALTO DE PIEZA NO PUEDE SER 0.");
        }
        if (lPieza <= 0) {
            throw new Exception("LARGO DE PIEZA NO PUEDE SER 0.");
        }

        BufferedImage imagen_ = new BufferedImage(lGrafi, aGrafi, BufferedImage.TYPE_INT_RGB);
        Graphics2D grafico_ = imagen_.createGraphics();

        grafico_.setColor(Color.WHITE);
        grafico_.fillRect(0, 0, lGrafi, aGrafi);

        grafico_.setColor(Color.BLACK);
        grafico_.drawRect(0, 0, lGrafi - 1, aGrafi - 1);

        int cantidadPiezas = 0;
        for (int y = 0; y <= aGrafi - aPieza; y += aPieza) {
            for (int x = 0; x <= lGrafi - lPieza; x += lPieza) {
                grafico_.setColor(Color.BLACK);
                grafico_.drawRect(x, y, lPieza, aPieza);
                cantidadPiezas++;
                x += separy;
            }
            y += separx;
        }

        BufferedImage imagen = null;

        if (this.item.getFactorHorizontal() > 1 || this.item.getFactorVertical() > 1) {

            aGrafi = (int) (Math.min(this.item.getAltoFormatoImpresion(), this.item.getLargoFormatoImpresion()) * 10);
            lGrafi = (int) (Math.max(this.item.getAltoFormatoImpresion(), this.item.getLargoFormatoImpresion()) * 10);

            imagen = new BufferedImage(lGrafi, aGrafi, BufferedImage.TYPE_INT_RGB);
            Graphics2D grafico = imagen.createGraphics();

            if (this.item.getFactorHorizontal() > 1) {
                String[] letras = this.item.getLetras().split(",");
                for (int i = 0; i < this.item.getFactorHorizontal(); i++) {
                    grafico.drawImage(imagen_, i * imagen_.getWidth(), 0, imagen_.getWidth(), imagen_.getHeight(), null);
                    grafico.setFont(new Font("Arial", Font.PLAIN, 50));
                    grafico.setColor(Color.RED);
                    grafico.drawString(letras[i], (i * imagen_.getWidth()) + (imagen_.getWidth() / 2), imagen_.getHeight() / 2);
                }
                cantidadPiezas = cantidadPiezas * this.item.getFactorHorizontal();
            }

            if (this.item.getFactorVertical() > 1) {
                String[] letras = this.item.getLetras().split(",");
                for (int i = 0; i < this.item.getFactorVertical(); i++) {
                    grafico.drawImage(imagen_, 0, i * imagen_.getHeight(), imagen_.getWidth(), imagen_.getHeight(), null);
                    grafico.setFont(new Font("Arial", Font.PLAIN, 50));
                    grafico.setColor(Color.RED);
                    grafico.drawString(letras[i], imagen_.getWidth() / 2, (i * imagen_.getHeight()) + (imagen_.getHeight() / 2));
                }
                cantidadPiezas = cantidadPiezas * this.item.getFactorVertical();
            }
        } else {
            imagen = imagen_;
        }

        this.item.setCantidadPiezasImpresion(cantidadPiezas);
        this.item.setGraficoImpresion(imagen);

        AsignarControles();
    }

    public void GirarGrafico() throws Exception {
        this.item.setGraficoImpresionGirado(!this.item.isGraficoImpresionGirado());
        GenerarGrafico();
    }

    Action sele_meto = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            MetodoImpresion seleccionado = ((lisMetodoImpresion) evt.getSource()).getSeleccionado();
            if (!(seleccionado == null)) {
                schMetodoImpresion.asingValues(seleccionado.getIdMetodoImpresion(), seleccionado.getNombre());
                item.setIdMetodoImpresion(seleccionado.getIdMetodoImpresion());
                item.setDescripcionMetodoImpresion(seleccionado.getNombre());
                item.setCantidadPases(seleccionado.getFactorPases());
                item.setCantidadCambios(seleccionado.getFactorCambios());
                item.setFactorHorizontal(seleccionado.getFactorHorizontal());
                item.setFactorVertical(seleccionado.getFactorVertical());
                item.setLetras(seleccionado.getLetras());
                AsignarControles();
            }
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frame = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblGrafico = new javax.swing.JLabel();
        btnGenerarGrafico = new javax.swing.JButton();
        btnGirarGrafico = new javax.swing.JButton();
        lblAltoPieza = new javax.swing.JLabel();
        txtAltoPieza = new javax.swing.JTextField();
        lblLargoPieza = new javax.swing.JLabel();
        txtLargoPieza = new javax.swing.JTextField();
        txtMedidasMaterial = new javax.swing.JTextField();
        lblCantidadPiezas = new javax.swing.JLabel();
        txtCantidadPiezas = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        lblSeparacionX = new javax.swing.JLabel();
        txtSeparacionX = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOtros = new javax.swing.JTextArea();
        lblSeparacionY = new javax.swing.JLabel();
        txtSeparacionY = new javax.swing.JTextField();
        lblMetodoImpresion = new javax.swing.JLabel();
        schMetodoImpresion = new com.sge.base.controles.JSearch();
        txtCantidadPases = new javax.swing.JTextField();
        lblCantidadPases = new javax.swing.JLabel();
        lblTiraje = new javax.swing.JLabel();
        lblCantidadCambios = new javax.swing.JLabel();
        txtCantidadCambios = new javax.swing.JTextField();
        txtTiraje = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(903, 653));

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/details-large-view-16.png"))); // NOI18N
        lblTitulo.setText("GRAFICO DE IMPRESION");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(567, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        lblGrafico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane1.setViewportView(lblGrafico);

        btnGenerarGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/visible-16.png"))); // NOI18N
        btnGenerarGrafico.setText("VER");
        btnGenerarGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarGraficoActionPerformed(evt);
            }
        });

        btnGirarGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/rotate-16.png"))); // NOI18N
        btnGirarGrafico.setText("GIRAR");
        btnGirarGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGirarGraficoActionPerformed(evt);
            }
        });

        lblAltoPieza.setText("ALTO");

        txtAltoPieza.setText("0");

        lblLargoPieza.setText("LARGO");

        txtLargoPieza.setText("0");

        txtMedidasMaterial.setEditable(false);

        lblCantidadPiezas.setText("N° PIEZAS");

        txtCantidadPiezas.setEditable(false);
        txtCantidadPiezas.setText("0");

        btnAceptar.setText("ACEPTAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblSeparacionX.setText("SEP. X");

        txtSeparacionX.setText("0");

        txtOtros.setColumns(20);
        txtOtros.setRows(5);
        jScrollPane2.setViewportView(txtOtros);

        lblSeparacionY.setText("SEP. Y");

        txtSeparacionY.setText("0");

        lblMetodoImpresion.setText("METODO IMPRESION");

        schMetodoImpresion.addSearchListener(new SearchListener() {
            @Override
            public void Search(){
                schMetodoImpresionSearch();
            }
            @Override
            public void Clear(){
            }
        });

        txtCantidadPases.setEditable(false);
        txtCantidadPases.setText("0");

        lblCantidadPases.setText("N° PASES");

        lblTiraje.setText("TIRAJE");

        lblCantidadCambios.setText("N° CAMBIOS");

        txtCantidadCambios.setEditable(false);
        txtCantidadCambios.setText("0");

        txtTiraje.setEditable(false);
        txtTiraje.setText("0");

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addComponent(btnGenerarGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGirarGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(txtMedidasMaterial)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblAltoPieza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLargoPieza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSeparacionX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSeparacionY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSeparacionY)
                                .addComponent(txtSeparacionX, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addComponent(txtLargoPieza, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtAltoPieza, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addComponent(jSeparator1)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addComponent(lblCantidadPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCantidadPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblMetodoImpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(schMetodoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                            .addComponent(lblCantidadPases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(3, 3, 3)
                            .addComponent(txtCantidadPases, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblTiraje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCantidadCambios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiraje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGenerarGrafico)
                            .addComponent(btnGirarGrafico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedidasMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAltoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAltoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLargoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLargoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSeparacionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeparacionX, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSeparacionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeparacionY, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidadPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMetodoImpresion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(schMetodoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadPases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidadPases, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidadCambios, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadCambios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTiraje, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar))
                    .addComponent(jScrollPane1))
                .addContainerGap())
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

    public void schMetodoImpresionSearch() {
        VerModal(new lisMetodoImpresion(1), sele_meto);
    }

    private void btnGenerarGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarGraficoActionPerformed
        // TODO add your handling code here:
        try {
            this.item.setAltoMedidaAbierta(Double.parseDouble(txtAltoPieza.getText()));
            this.item.setLargoMedidaAbierta(Double.parseDouble(txtLargoPieza.getText()));
            this.item.setSeparacionX(Double.parseDouble(txtSeparacionX.getText()));
            this.item.setSeparacionY(Double.parseDouble(txtSeparacionY.getText()));
            GenerarGrafico();
        } catch (Exception e) {
            ControlarExcepcion(e);
        }
    }//GEN-LAST:event_btnGenerarGraficoActionPerformed

    private void btnGirarGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGirarGraficoActionPerformed
        // TODO add your handling code here:
        try {
            this.item.setAltoMedidaAbierta(Double.parseDouble(txtAltoPieza.getText()));
            this.item.setLargoMedidaAbierta(Double.parseDouble(txtLargoPieza.getText()));
            this.item.setSeparacionX(Double.parseDouble(txtSeparacionX.getText()));
            this.item.setSeparacionY(Double.parseDouble(txtSeparacionY.getText()));
            GirarGrafico();
        } catch (Exception e) {
            ControlarExcepcion(e);
        }
    }//GEN-LAST:event_btnGirarGraficoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        Cerrar();
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnGenerarGrafico;
    private javax.swing.JButton btnGirarGrafico;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAltoPieza;
    private javax.swing.JLabel lblCantidadCambios;
    private javax.swing.JLabel lblCantidadPases;
    private javax.swing.JLabel lblCantidadPiezas;
    private javax.swing.JLabel lblGrafico;
    private javax.swing.JLabel lblLargoPieza;
    private javax.swing.JLabel lblMetodoImpresion;
    private javax.swing.JLabel lblSeparacionX;
    private javax.swing.JLabel lblSeparacionY;
    private javax.swing.JLabel lblTiraje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private com.sge.base.controles.JSearch schMetodoImpresion;
    private javax.swing.JTextField txtAltoPieza;
    private javax.swing.JTextField txtCantidadCambios;
    private javax.swing.JTextField txtCantidadPases;
    private javax.swing.JTextField txtCantidadPiezas;
    private javax.swing.JTextField txtLargoPieza;
    private javax.swing.JTextField txtMedidasMaterial;
    private javax.swing.JTextArea txtOtros;
    private javax.swing.JTextField txtSeparacionX;
    private javax.swing.JTextField txtSeparacionY;
    private javax.swing.JTextField txtTiraje;
    // End of variables declaration//GEN-END:variables
}
