package com.sge.modulos.ventas.formularios;

import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.Cotizacion;
import com.sge.modulos.ventas.clases.ItemCotizacion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author elderson
 */
public class genGraficoPrecorte extends frameBase<Cotizacion> {

    /**
     * Creates new form genGraficoPrecorte
     *
     * @param item
     */
    public genGraficoPrecorte(ItemCotizacion item) {
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
        txtMedidasMaterial.setText(String.format("%s X %s CM.", this.item.getAltoMaterial(), this.item.getLargoMaterial()));
        txtAltoPieza.setText(String.valueOf(this.item.getAltoFormatoImpresion()));
        txtLargoPieza.setText(String.valueOf(this.item.getLargoFormatoImpresion()));
        txtDemasia.setText(String.valueOf(this.item.getCantidadDemasia()));
        txtCantidadPiezas.setText(String.valueOf(this.item.getCantidadPiezasPrecorte()));
        txtCantidadHojasMaquina.setText(String.valueOf(this.item.getCantidadMaterial() * this.item.getCantidadPiezasPrecorte()));
        txtOtros.setText(String.format("%s PLIEGOS DE : %s + %s = %s HJS/RESMA \n", this.item.getCantidadPliegos(), this.item.getCantidadMaterial(), this.item.getCantidadDemasiaMaterial(), this.item.getCantidadMaterial() + this.item.getCantidadDemasiaMaterial()));
        if (this.item.getGraficoPrecorte() != null) {
            lblGrafico.setIcon(new ImageIcon(this.item.getGraficoPrecorte()));
        }
    }

    public byte[] CambiarDimensiones(BufferedImage imagen, int largo, int alto) throws IOException {
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

        ByteArrayOutputStream arrayBytesOut = new ByteArrayOutputStream();
        ImageIO.write(nuevaImagen, "jpg", arrayBytesOut);
        byte[] arrayBytes = arrayBytesOut.toByteArray();

        return arrayBytes;
    }

    public void GenerarGrafico() throws Exception {

        aGrafi = (int) (this.item.getAltoMaterial() * 10);
        lGrafi = (int) (this.item.getLargoMaterial() * 10);

        if (this.item.isGraficoPrecorteGirado()) {
            aPieza = (int) (this.item.getLargoFormatoImpresion() * 10);
            lPieza = (int) (this.item.getAltoFormatoImpresion() * 10);
        } else {
            aPieza = (int) (this.item.getAltoFormatoImpresion() * 10);
            lPieza = (int) (this.item.getLargoFormatoImpresion() * 10);
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

        BufferedImage imagen = new BufferedImage(lGrafi, aGrafi, BufferedImage.TYPE_INT_RGB);
        Graphics2D grafico = imagen.createGraphics();

        grafico.setColor(Color.WHITE);
        grafico.fillRect(0, 0, lGrafi, aGrafi);

        grafico.setColor(Color.BLACK);
        grafico.drawRect(0, 0, lGrafi - 1, aGrafi - 1);

        int cantidadPiezas = 0;
        for (int y = 0; y <= aGrafi - aPieza; y += aPieza) {
            for (int x = 0; x <= lGrafi - lPieza; x += lPieza) {
                grafico.setColor(Color.BLACK);
                grafico.drawRect(x, y, lPieza, aPieza);
                cantidadPiezas++;
            }
        }

        this.item.setCantidadPiezasPrecorte(cantidadPiezas);
        this.item.setGraficoPrecorte(CambiarDimensiones(imagen, lGrafi / 2, aGrafi / 2));
    }

    public void GenerarGraficoImpresion() throws Exception {

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

        ByteArrayOutputStream arrayBytesOut = new ByteArrayOutputStream();
        ImageIO.write(imagen, "jpg", arrayBytesOut);
        byte[] arrayBytes = arrayBytesOut.toByteArray();

        this.item.setGraficoImpresion(arrayBytes);
    }

    public void GirarGrafico() throws Exception {
        this.item.setGraficoPrecorteGirado(!this.item.isGraficoPrecorteGirado());
        GenerarGrafico();
    }

    public void CalcularProduccion() throws Exception {
        if (this.item.isImpresionVinil() || this.item.isImpresionBanner()) {
            double alto = this.item.getAltoMedidaAbierta();
            double largo = this.item.getLargoMedidaAbierta();
            if (this.item.getUnidadMedidaAbierta().equals("MT")) {
                alto = alto * 100;
                largo = largo * 100;
            }
            this.item.setCantidadMaterial(new Double(this.item.getCantidad() * (alto * largo)).intValue());
            this.item.setCantidadProduccion(this.item.getCantidadMaterial() + this.item.getCantidadDemasia());
        } else {
            double cantidadDecimal = 0;
            if (this.item.isTipoUnidad() && this.item.getCantidadTipoUnidad() > 0) {
                cantidadDecimal = this.item.getCantidad() / this.item.getCantidadPiezasPrecorte();
            } else {
                cantidadDecimal = this.item.getCantidad() / (this.item.getCantidadPiezasPrecorte() * this.item.getCantidadPiezasImpresion());
            }
            int cantidadEntera = new Double(cantidadDecimal).intValue();
            this.item.setCantidadMaterial(((cantidadDecimal - cantidadEntera) > 0) ? cantidadEntera + 1 : cantidadEntera);
            this.item.setCantidadDemasiaMaterial(new Double(this.item.getCantidadDemasia() / this.item.getCantidadPiezasPrecorte()).intValue());
            if (this.item.getCantidadTipoUnidad() == 0) {
                this.item.setCantidadPliegos(1);
            } else {
                double pliegosDecimal = this.item.getCantidadTipoUnidad() / (this.item.getCantidadPiezasImpresion() * 2);
                int pliegosEntero = new Double(Math.floor(pliegosDecimal)).intValue();
                double residuo = pliegosEntero - pliegosDecimal;
                this.item.setCantidadPaginasSobrantes(0);
                if (residuo > 0) {
                    this.item.setCantidadPaginasSobrantes(this.item.getCantidadTipoUnidad() - (pliegosEntero * this.item.getCantidadPiezasImpresion() * 2));
                }
                this.item.setCantidadPliegos((pliegosEntero == 0) ? 1 : pliegosEntero);
            }
            this.item.setCantidadProduccion((this.item.getCantidadMaterial() + this.item.getCantidadDemasiaMaterial()) * this.item.getCantidadPiezasPrecorte() * this.item.getCantidadPases());
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
        lblDemasia = new javax.swing.JLabel();
        txtDemasia = new javax.swing.JTextField();
        txtCantidadHojasMaquina = new javax.swing.JTextField();
        lblCantidadPiezas1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOtros = new javax.swing.JTextArea();

        frame.setBackground(java.awt.Color.white);

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/details-large-view-16.png"))); // NOI18N
        lblTitulo.setText("GRAFICO DE PRECORTE");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(557, Short.MAX_VALUE))
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

        lblDemasia.setText("DEMASIA");

        txtDemasia.setText("0");

        txtCantidadHojasMaquina.setEditable(false);
        txtCantidadHojasMaquina.setText("0");

        lblCantidadPiezas1.setText("HJS MAQ.");

        txtOtros.setColumns(20);
        txtOtros.setRows(5);
        jScrollPane2.setViewportView(txtOtros);

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(btnGenerarGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGirarGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
                    .addComponent(txtMedidasMaterial)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCantidadPiezas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCantidadPiezas1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAltoPieza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLargoPieza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDemasia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDemasia, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                            .addComponent(txtCantidadPiezas)
                            .addComponent(txtLargoPieza, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtAltoPieza, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCantidadHojasMaquina)))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMedidasMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAltoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAltoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLargoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLargoPieza, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDemasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDemasia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidadPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadHojasMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidadPiezas1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(btnAceptar))
                    .addComponent(jScrollPane1))
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

    private void btnGenerarGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarGraficoActionPerformed
        // TODO add your handling code here:
        try {
            this.item.setAltoFormatoImpresion(Double.parseDouble(txtAltoPieza.getText()));
            this.item.setLargoFormatoImpresion(Double.parseDouble(txtLargoPieza.getText()));
            this.item.setCantidadDemasia(Integer.parseInt(txtDemasia.getText()));
            GenerarGrafico();
            GenerarGraficoImpresion();
            CalcularProduccion();
            AsignarControles();
        } catch (Exception e) {
            ControlarExcepcion(e);
        }
    }//GEN-LAST:event_btnGenerarGraficoActionPerformed

    private void btnGirarGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGirarGraficoActionPerformed
        // TODO add your handling code here:
        try {
            this.item.setAltoFormatoImpresion(Double.parseDouble(txtAltoPieza.getText()));
            this.item.setLargoFormatoImpresion(Double.parseDouble(txtLargoPieza.getText()));
            this.item.setCantidadDemasia(Integer.parseInt(txtDemasia.getText()));
            GirarGrafico();
            CalcularProduccion();
            AsignarControles();
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
    private javax.swing.JLabel lblCantidadPiezas;
    private javax.swing.JLabel lblCantidadPiezas1;
    private javax.swing.JLabel lblDemasia;
    private javax.swing.JLabel lblGrafico;
    private javax.swing.JLabel lblLargoPieza;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtAltoPieza;
    private javax.swing.JTextField txtCantidadHojasMaquina;
    private javax.swing.JTextField txtCantidadPiezas;
    private javax.swing.JTextField txtDemasia;
    private javax.swing.JTextField txtLargoPieza;
    private javax.swing.JTextField txtMedidasMaterial;
    private javax.swing.JTextArea txtOtros;
    // End of variables declaration//GEN-END:variables
}
