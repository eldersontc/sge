package com.sge;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import com.sge.modulos.administracion.formularios.lisEmpleado;
import com.sge.modulos.administracion.formularios.lisMoneda;
import com.sge.modulos.administracion.formularios.lisReporte;
import com.sge.modulos.administracion.formularios.lisUsuario;
import com.sge.modulos.administracion.formularios.lisValorDefinido;
import com.sge.modulos.compras.formularios.lisProveedor;
import com.sge.modulos.inventarios.formularios.lisAlmacen;
import com.sge.modulos.inventarios.formularios.lisEntradaInventario;
import com.sge.modulos.inventarios.formularios.lisProducto;
import com.sge.modulos.inventarios.formularios.lisUnidad;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author elderson
 */
public class SGE extends javax.swing.JFrame {

    /**
     * Creates new form SGE
     */
    public SGE() {
        initComponents();
        LlenarMenus();
        //VerReporte();
    }

    public void VerReporte() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sge", "postgres", "123456");
            
            String ruta = "/home/elderson/report1.jasper";
            Map parametros = new HashMap();
            parametros.put("idAlmacen", 36);
            JasperPrint informa = JasperFillManager.fillReport(ruta, parametros, cn);
            JasperViewer view = new JasperViewer(informa);
            view.setTitle("xx");
            view.setVisible(true);
        } catch (Exception e) {
            System.out.print("xxxx");
        }
    }

    public void LlenarMenus() {
        cliAdministracion cliente = new cliAdministracion();
        try {
            String json = cliente.ObtenerMenus(new Gson().toJson(1));
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                List<Object[]> menus = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                }.getType());
                JMenuBar menuBar = new JMenuBar();
                menuBar.setPreferredSize(new java.awt.Dimension(128, 40));
                for (Object[] itemN1 : menus) {
                    JMenu menuN1 = new JMenu(itemN1[2].toString());
                    URL urlN1 = getClass().getResource("/com/sge/base/imagenes/" + itemN1[4]);
                    if (!(urlN1 == null)) {
                        menuN1.setIcon(new ImageIcon(urlN1));
                    }
                    menuBar.add(menuN1);
                    for (List<Object> itemN2 : (List<List<Object>>) itemN1[5]) {
                        if (((List<Object>) itemN2.get(5)).isEmpty()) {
                            JMenuItem menuItemN2 = new JMenuItem(itemN2.get(2).toString());
                            menuItemN2.setName((itemN2.get(3) == null) ? "" : itemN2.get(3).toString());
                            URL urlItemN2 = getClass().getResource("/com/sge/base/imagenes/" + itemN2.get(4));
                            if (!(urlItemN2 == null)) {
                                menuItemN2.setIcon(new ImageIcon(urlItemN2));
                            }
                            menuItemN2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ClickMenuItem(e);
                                }
                            });
                            menuN1.add(menuItemN2);
                        } else {
                            JMenu menuN2 = new JMenu(itemN2.get(2).toString());
                            URL urlN2 = getClass().getResource("/com/sge/base/imagenes/" + itemN2.get(4));
                            if (!(urlN2 == null)) {
                                menuN2.setIcon(new ImageIcon(urlN2));
                            }
                            menuN1.add(menuN2);
                            for (List<Object> itemN3 : (List<List<Object>>) itemN2.get(5)) {
                                JMenuItem menuItemN3 = new JMenuItem(itemN3.get(2).toString());
                                menuItemN3.setName((itemN3.get(3) == null) ? "" : itemN3.get(3).toString());
                                URL urlItemN3 = getClass().getResource("/com/sge/base/imagenes/" + itemN3.get(4));
                                if (!(urlItemN3 == null)) {
                                    menuItemN3.setIcon(new ImageIcon(urlItemN3));
                                }
                                menuItemN3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        ClickMenuItem(e);
                                    }
                                });
                                menuN2.add(menuItemN3);
                            }
                        }
                    }
                }
                pnlMenu.setLayout(new BorderLayout());
                pnlMenu.add(menuBar, BorderLayout.NORTH);
            }
        } catch (Exception e) {
        } finally {
            cliente.close();
        }
    }

    private void ClickMenuItem(ActionEvent e) {
        // TODO add your handling code here:
        JMenuItem menuItem = (JMenuItem) e.getSource();
        LanzarFormulario(menuItem.getName());
    }

    public void LanzarFormulario(String formulario) {
        switch (formulario) {
            case "lisUsuario":
                lisUsuario lisUsuario = new lisUsuario(0);
                jdpPrincipal.add(lisUsuario);
                lisUsuario.setVisible(true);
                break;
        case "lisReporte":
                lisReporte lisReporte = new lisReporte(0);
                jdpPrincipal.add(lisReporte);
                lisReporte.setVisible(true);
                break;
            case "lisValorDefinido":
                lisValorDefinido lisValorDefinido = new lisValorDefinido(0);
                jdpPrincipal.add(lisValorDefinido);
                lisValorDefinido.setVisible(true);
                break;
            case "lisMoneda":
                lisMoneda lisMoneda = new lisMoneda(0);
                jdpPrincipal.add(lisMoneda);
                lisMoneda.setVisible(true);
                break;
            case "lisEmpleado":
                lisEmpleado lisEmpleado = new lisEmpleado(0);
                jdpPrincipal.add(lisEmpleado);
                lisEmpleado.setVisible(true);
                break;
            case "lisProveedor":
                lisProveedor lisProveedor = new lisProveedor(0);
                jdpPrincipal.add(lisProveedor);
                lisProveedor.setVisible(true);
                break;
            case "lisAlmacen":
                lisAlmacen lisAlmacen = new lisAlmacen(0);
                jdpPrincipal.add(lisAlmacen);
                lisAlmacen.setVisible(true);
                break;
            case "lisUnidad":
                lisUnidad lisUnidad = new lisUnidad(0);
                jdpPrincipal.add(lisUnidad);
                lisUnidad.setVisible(true);
                break;
            case "lisProducto":
                lisProducto lisProducto = new lisProducto(0);
                jdpPrincipal.add(lisProducto);
                lisProducto.setVisible(true);
                break;
            case "lisEntradaInventario":
                lisEntradaInventario lisEntradaInventario = new lisEntradaInventario(0);
                jdpPrincipal.add(lisEntradaInventario);
                lisEntradaInventario.setVisible(true);
                break;
            default:
                break;
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

        pnlBanner = new javax.swing.JPanel();
        lblTituloBanner = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnlMenu = new javax.swing.JPanel();
        jdpPrincipal = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBanner.setBackground(new java.awt.Color(67, 100, 130));
        pnlBanner.setBorder(null);

        lblTituloBanner.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTituloBanner.setForeground(java.awt.Color.white);
        lblTituloBanner.setText("SISTEMA DE GESTIÓN EMPRESARIAL");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBannerLayout = new javax.swing.GroupLayout(pnlBanner);
        pnlBanner.setLayout(pnlBannerLayout);
        pnlBannerLayout.setHorizontalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblTituloBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBannerLayout.setVerticalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBannerLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(20, 20, 20))
        );

        pnlMenu.setBackground(new java.awt.Color(67, 100, 130));
        pnlMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        jdpPrincipal.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jdpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VerReporte();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SGE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JLabel lblTituloBanner;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration//GEN-END:variables
}
