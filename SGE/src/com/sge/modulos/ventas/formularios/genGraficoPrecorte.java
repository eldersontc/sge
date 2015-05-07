package com.sge.modulos.ventas.formularios;

import com.sge.base.formularios.frameBase;
import com.sge.modulos.ventas.clases.Cotizacion;
import com.sge.modulos.ventas.clases.ItemCotizacion;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
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
     */
    public genGraficoPrecorte(ItemCotizacion item) {
        initComponents();
        this.item = item;
    }

    private ItemCotizacion item;

    public BufferedImage scaleImage(BufferedImage img, int width, int height) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth * height < imgHeight * width) {
            width = imgWidth * height / imgHeight;
        } else {
            height = imgHeight * width / imgWidth;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            //g.setBackground(background);
            g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }

    public BufferedImage scale(BufferedImage sbi, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if (sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

    private static BufferedImage resizeImage(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 500, 500, null);
        g.dispose();

        return resizedImage;
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage) {

        BufferedImage resizedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 500, 500, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    public void GenerarGraficoPrecorte() throws Exception {

        int altGraf = (int) (this.item.getAltoMaterial() * 10);
        int larGraf = (int) (this.item.getLargoMaterial() * 10);
        int altPiez = (int) (this.item.getAltoFormatoImpresion() * 10);
        int larPiez = (int) (this.item.getLargoFormatoImpresion() * 10);

        if (altGraf <= 0) {
            throw new Exception("ALTO DE MATERIAL NO PUEDE SER 0.");
        }
        if (larGraf <= 0) {
            throw new Exception("LARGO DE MATERIAL NO PUEDE SER 0.");
        }
        if (altPiez <= 0) {
            throw new Exception("ALTO DE PIEZA NO PUEDE SER 0.");
        }
        if (larPiez <= 0) {
            throw new Exception("LARGO DE PIEZA NO PUEDE SER 0.");
        }

        BufferedImage imagen = new BufferedImage(larGraf, altGraf, BufferedImage.TYPE_INT_RGB);
        Graphics2D grafico = imagen.createGraphics();

        grafico.setColor(Color.white);
        grafico.fillRect(0, 0, larGraf, altGraf);

        grafico.setColor(Color.black);
        grafico.drawRect(0, 0, larGraf - 1, altGraf - 1);

        for (int y = 0; y <= altGraf - altPiez; y += altPiez) {
            for (int x = 0; x <= larGraf - larPiez; x += larPiez) {
                grafico.setColor(Color.black);
                grafico.drawRect(x, y, larPiez, altPiez);
            }
        }

        //lblGraficoPrecorte.setIcon(new ImageIcon(imagen));
        lblGraficoPrecorte.setIcon(new ImageIcon(scaleImage(imagen, larGraf / 2, altGraf / 2)));

//        try {
//            ImageIO.write(imagen, "jpg", new File("/home/elderson/grafico.jpg"));
//        } catch (IOException ex) {
//            //Logger.getLogger(JFrame02.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAltoFormatoImpresion = new javax.swing.JLabel();
        txtAltoFormatoImpresion = new javax.swing.JTextField();
        lblLargoFormatoImpresion = new javax.swing.JLabel();
        txtLargoFormatoImpresion = new javax.swing.JTextField();
        btnGirarGraficoPrecorte = new javax.swing.JButton();
        btnGenerarGraficoPrecorte = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblGraficoPrecorte = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);

        lblAltoFormatoImpresion.setText("ALTO");

        txtAltoFormatoImpresion.setText("0");

        lblLargoFormatoImpresion.setText("LARGO");

        txtLargoFormatoImpresion.setText("0");

        btnGirarGraficoPrecorte.setText("GIRAR GRAFICO");

        btnGenerarGraficoPrecorte.setText("GENERAR GRAFICO");
        btnGenerarGraficoPrecorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarGraficoPrecorteActionPerformed(evt);
            }
        });

        lblGraficoPrecorte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane1.setViewportView(lblGraficoPrecorte);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGenerarGraficoPrecorte)
                            .addComponent(btnGirarGraficoPrecorte, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAltoFormatoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtAltoFormatoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLargoFormatoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtLargoFormatoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 341, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGenerarGraficoPrecorte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGirarGraficoPrecorte)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAltoFormatoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAltoFormatoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLargoFormatoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLargoFormatoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarGraficoPrecorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarGraficoPrecorteActionPerformed
        // TODO add your handling code here:
        try {
            this.item.setAltoFormatoImpresion(Double.parseDouble(txtAltoFormatoImpresion.getText()));
            this.item.setLargoFormatoImpresion(Double.parseDouble(txtLargoFormatoImpresion.getText()));
            GenerarGraficoPrecorte();
        } catch (Exception e) {
            ControlarExcepcion(e);
        }
    }//GEN-LAST:event_btnGenerarGraficoPrecorteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarGraficoPrecorte;
    private javax.swing.JButton btnGirarGraficoPrecorte;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAltoFormatoImpresion;
    private javax.swing.JLabel lblGraficoPrecorte;
    private javax.swing.JLabel lblLargoFormatoImpresion;
    private javax.swing.JTextField txtAltoFormatoImpresion;
    private javax.swing.JTextField txtLargoFormatoImpresion;
    // End of variables declaration//GEN-END:variables
}
