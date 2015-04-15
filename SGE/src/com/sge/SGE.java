package com.sge;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.FabricaControles;
import com.sge.base.excepciones.Excepciones;
import com.sge.base.formularios.frameBase;
import com.sge.base.formularios.frameLogin;
import com.sge.modulos.administracion.clases.Usuario;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
        Init();
    }

    private Usuario usuario;

    public void AsignarUsuario(Object[] array) {
        usuario = new Usuario();
        usuario.setIdUsuario(((Double) array[0]).intValue());
        usuario.setUsuario(array[1].toString());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void Init() {
        OcultarBanner();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        FabricaControles.VerModal(jdpPrincipal, new frameLogin(), close);
    }

    public void OcultarBanner() {
        pnlBanner.setVisible(false);
        pnlMenu.setVisible(false);
        for (JInternalFrame frame : jdpPrincipal.getAllFrames()) {
            try {
                frame.setClosed(true);
            } catch (Exception ex) {
            }
        }
    }

    public void VerBanner() {
        pnlBanner.setVisible(true);
        pnlMenu.setVisible(true);
        txtUsuario.setText(getUsuario().getUsuario());
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }

    Action close = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] usuario = ((frameLogin) e.getSource()).getUsuario();
            if (usuario != null) {
                AsignarUsuario(usuario);
                LlenarMenus(getUsuario().getIdUsuario());
                VerBanner();
            }
        }
    };

    public void LlenarMenus(int idUsuario) {
        cliAdministracion cliente = new cliAdministracion();
        try {
            String json = cliente.ObtenerMenus(new Gson().toJson(idUsuario));
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
            Excepciones.Controlar(e, this);
        } finally {
            cliente.close();
        }
    }

    private void ClickMenuItem(ActionEvent e) {
        // TODO add your handling code here:
        JMenuItem menuItem = (JMenuItem) e.getSource();
        LanzarFormulario(menuItem.getName());
    }

    public void LanzarFormulario(String frameName) {
        try {
            Class<?> clazz = Class.forName(frameName);
            Constructor<?> constructor = clazz.getConstructor(int.class);
            frameBase frame = (frameBase) constructor.newInstance(new Object[]{0});
            frame.setUsuario(getUsuario());
            jdpPrincipal.add(frame);
            frame.setVisible(true);
        } catch (Exception e) {
            Excepciones.Controlar(e, this);
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
        btnSalir = new javax.swing.JButton();
        lblSeparador = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        jdpPrincipal = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBanner.setBackground(new java.awt.Color(67, 100, 130));
        pnlBanner.setBorder(null);

        lblTituloBanner.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        lblTituloBanner.setForeground(java.awt.Color.white);
        lblTituloBanner.setText("SISTEMA DE GESTIÓN EMPRESARIAL");

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/exit-16.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblSeparador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/reviewer-48.png"))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblUsuario.setForeground(java.awt.Color.white);
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsuario.setText("USUARIO:");

        txtUsuario.setForeground(java.awt.Color.white);
        txtUsuario.setText("DEMO");

        lblFecha.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblFecha.setForeground(java.awt.Color.white);
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha.setText("FECHA:");

        txtFecha.setForeground(java.awt.Color.white);
        txtFecha.setText("DD/MM/YYYY");

        javax.swing.GroupLayout pnlBannerLayout = new javax.swing.GroupLayout(pnlBanner);
        pnlBanner.setLayout(pnlBannerLayout);
        pnlBannerLayout.setHorizontalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblTituloBanner, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addComponent(lblSeparador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        pnlBannerLayout.setVerticalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBannerLayout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addGap(22, 22, 22))
                    .addGroup(pnlBannerLayout.createSequentialGroup()
                        .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsuario)
                            .addComponent(lblUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha)
                            .addComponent(lblFecha))
                        .addGap(16, 16, 16))))
            .addComponent(lblTituloBanner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSeparador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlMenu.setBackground(new java.awt.Color(67, 100, 130));
        pnlMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
                .addComponent(jdpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        OcultarBanner();
        FabricaControles.VerModal(jdpPrincipal, new frameLogin(), close);
    }//GEN-LAST:event_btnSalirActionPerformed

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
    private javax.swing.JButton btnSalir;
    private javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblSeparador;
    private javax.swing.JLabel lblTituloBanner;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JLabel txtUsuario;
    // End of variables declaration//GEN-END:variables
}
