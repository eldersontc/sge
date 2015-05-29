package com.sge;

import com.google.gson.Gson;
import com.sge.base.controles.FabricaControles;
import com.sge.base.excepciones.Excepciones;
import com.sge.base.formularios.frameBase;
import com.sge.base.formularios.frameLogin;
import com.sge.modulos.administracion.clases.Menu;
import com.sge.modulos.administracion.clases.Usuario;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

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

    private Date fechaServidor;

    public Usuario getUsuario() {
        return usuario;
    }

    public void AsignarFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
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
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.fechaServidor));
    }

    Action close = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frameLogin frame = ((frameLogin) e.getSource());
            if (frame.getUsuario() != null) {
                usuario = frame.getUsuario();
                AsignarFechaServidor(frame.getFechaServidor());
                LlenarMenus(getUsuario().getIdUsuario());
                VerBanner();
            }
        }
    };

    public void LlenarMenus(int idUsuario) {
        cliAdministracion cliente = new cliAdministracion();
        try {
            String json = cliente.ObtenerMenusPorUsuario(new Gson().toJson(idUsuario));
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                Menu[] menus = new Gson().fromJson(resultado[1], Menu[].class);
                JMenuBar menuBar = new JMenuBar();
                menuBar.setPreferredSize(new java.awt.Dimension(128, 40));
                menuBar.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent evt) {
                        if (SwingUtilities.isRightMouseButton(evt)) {
                            if (!pnlBanner.isVisible()) {
                                JPopupMenu popup = new JPopupMenu();
                                popup.setLayout(new java.awt.BorderLayout());
                                JMenuItem menuItem = new JMenuItem("VER BANNER", new ImageIcon(getClass().getResource("/com/sge/base/imagenes/visible-16.png")));
                                menuItem.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        pnlBanner.setVisible(true);
                                    }
                                });
                                popup.add(menuItem);
                                popup.show(menuBar, evt.getPoint().x, evt.getPoint().y);
                            }
                        }
                    }
                });
                for (Menu itemN1 : menus) {
                    JMenu menuN1 = new JMenu(itemN1.getNombre());
                    URL urlN1 = getClass().getResource("/com/sge/base/imagenes/" + itemN1.getIcono());
                    if (!(urlN1 == null)) {
                        menuN1.setIcon(new ImageIcon(urlN1));
                    }
                    menuBar.add(menuN1);
                    for (Menu itemN2 : itemN1.getSubMenus()) {
                        if (itemN2.getSubMenus().isEmpty()) {
                            JMenuItem menuItemN2 = new JMenuItem(itemN2.getNombre());
                            menuItemN2.setName((itemN2.getFormulario() == null) ? "" : itemN2.getFormulario());
                            URL urlItemN2 = getClass().getResource("/com/sge/base/imagenes/" + itemN2.getIcono());
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
                            JMenu menuN2 = new JMenu(itemN2.getNombre());
                            URL urlN2 = getClass().getResource("/com/sge/base/imagenes/" + itemN2.getIcono());
                            if (!(urlN2 == null)) {
                                menuN2.setIcon(new ImageIcon(urlN2));
                            }
                            menuN1.add(menuN2);
                            for (Menu itemN3 : itemN2.getSubMenus()) {
                                JMenuItem menuItemN3 = new JMenuItem(itemN3.getNombre());
                                menuItemN3.setName((itemN3.getFormulario() == null) ? "" : itemN3.getFormulario());
                                URL urlItemN3 = getClass().getResource("/com/sge/base/imagenes/" + itemN3.getIcono());
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
                
                int mensajesSinLeer = new Gson().fromJson(resultado[2], int.class);
                if(mensajesSinLeer > 0){
                    btnVerMensajes.setText(String.format("(%d)", mensajesSinLeer));
                }
            }
        } catch (Exception e) {
            Excepciones.Controlar(e, this);
        } finally {
            cliente.close();
            IniciarServidor();
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

    public void DesconectarUsuario() {
        cliAdministracion cliente = new cliAdministracion();
        try {
            cliente.DesconectarUsuario(new Gson().toJson(getUsuario().getIdUsuario()));
        } catch (Exception e) {
            cliente.close();
            Excepciones.EscribirLog(e);
        } finally {
            FinalizarServidor();
        }
    }

    /////////////////////////// SERVIDOR SOCKET ////////////////////////////////
    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public class swServidor extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            try {
                //create the socket server object
                server = new ServerSocket(port);
                //keep listens indefinitely until receives 'exit' call or program terminates
                while (true) {
                    System.out.println("Waiting for client request");
                    //creating socket and waiting for client connection
                    Socket socket = server.accept();
                    //read from socket to ObjectInputStream object
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    //convert ObjectInputStream object to String
                    String message = (String) ois.readObject();
                    System.out.println("Message Received: " + message);
                    //create ObjectOutputStream object
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    //write object to Socket
                    oos.writeObject("Hi Client " + message);
                    //close resources
                    ois.close();
                    oos.close();
                    socket.close();
                    //terminate the server if client sends exit request
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    }
                }
            } catch (Exception e) {
                Excepciones.EscribirLog(e);
            }
            return null;
        }

    }

    public void IniciarServidor() {
        new swServidor().execute();
    }

    public void FinalizarServidor() {
        try {
            System.out.println("Shutting down Socket server!!");
            server.close();
        } catch (Exception e) {
            Excepciones.EscribirLog(e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
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
        btnVerMensajes = new javax.swing.JButton();
        pnlMenu = new javax.swing.JPanel();
        jdpPrincipal = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlBanner.setBackground(new java.awt.Color(67, 100, 130));
        pnlBanner.setBorder(null);
        pnlBanner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlBannerMouseReleased(evt);
            }
        });

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

        btnVerMensajes.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnVerMensajes.setForeground(java.awt.Color.red);
        btnVerMensajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/message-2-16.png"))); // NOI18N
        btnVerMensajes.setToolTipText("");
        btnVerMensajes.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnVerMensajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMensajesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBannerLayout = new javax.swing.GroupLayout(pnlBanner);
        pnlBanner.setLayout(pnlBannerLayout);
        pnlBannerLayout.setHorizontalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBannerLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblTituloBanner, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSeparador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(btnVerMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        pnlBannerLayout.setVerticalGroup(
            pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTituloBanner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblSeparador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBannerLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBannerLayout.createSequentialGroup()
                        .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir)
                            .addComponent(btnVerMensajes))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBannerLayout.createSequentialGroup()
                        .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUsuario)
                            .addComponent(lblUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha)
                            .addComponent(lblFecha))
                        .addGap(16, 16, 16))))
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
        DesconectarUsuario();
        FabricaControles.VerModal(jdpPrincipal, new frameLogin(), close);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void pnlBannerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBannerMouseReleased
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            JPopupMenu popup = new JPopupMenu();
            popup.setLayout(new java.awt.BorderLayout());
            JMenuItem menuItem = new JMenuItem("OCULTAR BANNER", new ImageIcon(getClass().getResource("/com/sge/base/imagenes/invisible-16.png")));
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pnlBanner.setVisible(false);
                }
            });
            popup.add(menuItem);
            popup.show(pnlBanner, evt.getPoint().x, evt.getPoint().y);
        }
    }//GEN-LAST:event_pnlBannerMouseReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        DesconectarUsuario();
    }//GEN-LAST:event_formWindowClosing

    private void btnVerMensajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMensajesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerMensajesActionPerformed

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
    private javax.swing.JButton btnVerMensajes;
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
