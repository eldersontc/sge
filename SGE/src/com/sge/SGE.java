package com.sge;

import com.google.gson.Gson;
import com.sge.base.controles.FabricaControles;
import com.sge.base.excepciones.Excepciones;
import com.sge.base.formularios.frameLogin;
import com.sge.base.formularios.frameBase;
import com.sge.base.formularios.framePreload;
import com.sge.modulos.administracion.clases.Mensaje;
import com.sge.modulos.administracion.clases.Menu;
import com.sge.modulos.administracion.clases.Usuario;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public void AsignarIp() throws UnknownHostException{
        InetAddress host = InetAddress.getLocalHost();
        this.usuario.setIp(host.getHostAddress());
    }
    
    public void Init() {
        OcultarBanner();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    }

    public void OcultarBanner() {
        pnlBanner.setVisible(false);
        pnlMenu.setVisible(false);
        pnlMensajes.setVisible(false);
        pnlTabs.setVisible(false);
        tpnlTabs.removeAll();
        frameProcesando.setVisible(false);
        pnlFondoLogin.setVisible(true);
    }

    public void VerBanner() {
        pnlBanner.setVisible(true);
        pnlMenu.setVisible(true);
        pnlTabs.setVisible(true);
        pnlFondoLogin.setVisible(false);
        txtUsuario.setText(getUsuario().getUsuario());
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.fechaServidor));
    }
    
    private int mensajesSinLeer;

    public void setMensajesSinLeer(int mensajesSinLeer) {
        this.mensajesSinLeer = mensajesSinLeer;
    }

    public void VerProcesandoLogin(){
        frameLogin.setVisible(false);
        frameProcesando.setVisible(true);
    }
    
    public void OcultarProcesandoLogin(){
        frameLogin.setVisible(true);
        frameProcesando.setVisible(false);
    }
    
    public class swAutenticar extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesandoLogin();
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                String filtro = String.format("WHERE Usuario.usuario = '%s'", txtUsuarioLogin.getText());
                json = cliente.ObtenerUsuarios(new Gson().toJson(filtro));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    Usuario[] usuarios = new Gson().fromJson(resultado[1], Usuario[].class);
                    if (usuarios.length == 0) {
                        throw new Exception("USUARIO NO EXISTE.");
                    } else {
                        if (usuarios[0].getClave().equals(txtClaveLogin.getText())) {
                            if (usuarios[0].isConectado()) {
                                throw new Exception("EL USUARIO YA SE ENCUENTRA CONECTADO.");
                            } else {
                                setUsuario(usuarios[0]);
                                setFechaServidor(new Gson().fromJson(resultado[2], Date.class));
                                AsignarIp();
                                cliente.ConectarUsuario(new Gson().toJson(getUsuario()));
                                LlenarMenus(getUsuario().getIdUsuario());
                                VerBanner();
                            }
                        } else {
                            throw new Exception("CLAVE INCORRECTA.");
                        }
                    }
                }
            } catch (Exception e) {
                OcultarProcesandoLogin();
                cancel(false);
                //Excepciones.Controlar(e);
            } finally {
                cliente.close();
            }
            return json;
        }

        @Override
        protected void done() {
            OcultarProcesandoLogin();
        }
    }
    
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
                setMensajesSinLeer(mensajesSinLeer);
                VerMensajesSinLeer();
            }
        } catch (Exception e) {
            Excepciones.Controlar(e, this);
        } finally {
            cliente.close();
            IniciarServidor();
        }
    }

    public void VerMensajesSinLeer() {
        if (this.mensajesSinLeer > 0) {
            btnVerMensajes.setText(String.format("(%d)", this.mensajesSinLeer));
            btnVerMensajes.setToolTipText(String.format("TIENES (%d) MENSAJE(S) NUEVO(S)", this.mensajesSinLeer));
        } else {
            btnVerMensajes.setText("");
            btnVerMensajes.setToolTipText("NO HAY NUEVOS MENSAJES");
        }
    }

    private void ClickMenuItem(ActionEvent evt) {
        // TODO add your handling code here:
        try {
            JMenuItem menuItem = (JMenuItem) evt.getSource();
            AgregarTab(menuItem.getName(), menuItem.getText());
        } catch (Exception e) {
            
        }
    }
    
    
    public frameBase CrearInstancia(String frameName) throws Exception {
        Class<?> clazz = Class.forName(frameName);
        Constructor<?> constructor = clazz.getConstructor(int.class);
        frameBase frame = (frameBase) constructor.newInstance(new Object[]{0});
        return frame;
    }
    
    public void AgregarTab(String frameName, String title) throws Exception {
        int index = tpnlTabs.indexOfTab(title);
        if (index == -1) {
            frameBase frame = CrearInstancia(frameName);
            frame.setUsuario(getUsuario());
            framePreload frameCargando = new framePreload();
            frameCargando.AgregarFrame(frame);
            frame.Init();
            tpnlTabs.addTab(title, frameCargando);
            tpnlTabs.setSelectedIndex(tpnlTabs.getTabCount() - 1);
        } else {
            tpnlTabs.setSelectedIndex(index);
        }
    }

    public void DesconectarUsuario() {
        cliAdministracion cliente = new cliAdministracion();
        try {
            cliente.DesconectarUsuario(new Gson().toJson(getUsuario().getIdUsuario()));
        } catch (Exception e) {
            Excepciones.EscribirLog(e);
        } finally {
            cliente.close();
            FinalizarServidor();
        }
    }

    ////////////////////////////// MENSAJES ////////////////////////////////////
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoHoras = new SimpleDateFormat("kk:mm:ss");

    private String fecha = "";

    private Usuario usuarioOrigen;

    public void setUsuarioOrigen(Usuario usuario) {
        this.usuarioOrigen = usuario;
    }

    public Usuario getUsuarioOrigen() {
        return usuarioOrigen;
    }

    public void VerUsuarios() {
        cliAdministracion cliente = new cliAdministracion();
        try {
            String json = cliente.ObtenerUsuariosConMensajesSinLeer(new Gson().toJson(getUsuario().getIdUsuario()));
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                Usuario[] usuarios = new Gson().fromJson(resultado[1], Usuario[].class);
                lisUsuarios.removeAll();
                txtMensajes.setText("");
                DefaultListModel modelo = new DefaultListModel();
                for (Usuario usuario : usuarios) {
                    modelo.addElement(usuario);
                }
                lisUsuarios.setModel(modelo);
                pnlMensajes.setVisible(true);
            }
        } catch (Exception e) {
            Excepciones.EscribirLog(e);
        } finally {
            cliente.close();
        }
    }

    public void AgregarMensaje(Mensaje mensaje) throws BadLocationException, IOException {

        HTMLEditorKit kit = (HTMLEditorKit) txtMensajes.getEditorKit();
        HTMLDocument html = (HTMLDocument) txtMensajes.getDocument();

        String fecha_ = formatoFecha.format(mensaje.getFechaEnvio());

        if (!fecha.equals(fecha_)) {
            kit.insertHTML(html, html.getLength(), "<b>" + fecha_ + "</b><br>", 0, 0, null);
            fecha = fecha_;
        }

        String remitente = (mensaje.getIdUsuarioOrigen() == getUsuario().getIdUsuario()) ? "YO" : getUsuarioOrigen().getUsuario();
        String hora = formatoHoras.format(mensaje.getFechaEnvio());

        kit.insertHTML(html, html.getLength(), "<b>" + remitente + " " + hora + " : </b><br>", 0, 0, null);
        kit.insertHTML(html, html.getLength(), mensaje.getMensaje() + "<br>", 0, 0, null);
    }

    public void VerMensajes() {
        cliAdministracion cliente = new cliAdministracion();
        try {
            Mensaje mensaje_ = new Mensaje();
            mensaje_.setIdUsuarioOrigen(getUsuarioOrigen().getIdUsuario());
            mensaje_.setIdUsuarioDestino(getUsuario().getIdUsuario());
            String json = cliente.ObtenerMensajesPorUsuarioOrigenYDestino(new Gson().toJson(mensaje_));
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                Mensaje[] mensajes = new Gson().fromJson(resultado[1], Mensaje[].class);
                txtMensajes.setText("");
                for (Mensaje mensaje : mensajes) {
                    AgregarMensaje(mensaje);
                }
                if (usuarioOrigen.getMensajesSinLeer() > 0) {
                    cliente.CambiarALeido(new Gson().toJson(mensaje_));
                    this.mensajesSinLeer -= usuarioOrigen.getMensajesSinLeer();
                    VerMensajesSinLeer();
                    usuarioOrigen.setMensajesSinLeer(0);
                }
            }
        } catch (Exception e) {
            Excepciones.EscribirLog(e);
        } finally {
            cliente.close();
        }
    }

    public void EnviarMensaje() {
        cliAdministracion cliente = new cliAdministracion();
        try {
            Mensaje mensaje = new Mensaje();
            mensaje.setIdUsuarioOrigen(getUsuario().getIdUsuario());
            mensaje.setIdUsuarioDestino(getUsuarioOrigen().getIdUsuario());
            mensaje.setMensaje(txtMensaje.getText());
            String json = cliente.RegistrarMensaje(new Gson().toJson(mensaje));
            String[] resultado = new Gson().fromJson(json, String[].class);
            if (resultado[0].equals("true")) {
                Date fechaEnvio = new Gson().fromJson(resultado[1], Date.class);
                mensaje.setFechaEnvio(fechaEnvio);
                AgregarMensaje(mensaje);
                EnviarMensajeSocket(new Gson().toJson(mensaje));
                txtMensaje.setText("");
            }
        } catch (Exception e) {
            Excepciones.EscribirLog(e);
        } finally {
            cliente.close();
        }
    }

    public void EnviarMensajeSocket(String json) {
        try {
            //establish socket connection to server
            Socket socket = new Socket(getUsuarioOrigen().getIp(), 9876);
            //write to socket using ObjectOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            oos.writeObject(json);
            //close resources
            oos.close();
        } catch (Exception e) {
            Excepciones.EscribirLog(e);
        }
    }

    public void CambiarALeido(Mensaje mensaje) {
        cliAdministracion cliente = new cliAdministracion();
        try {
            cliente.CambiarALeido(mensaje);
        } catch (Exception e) {
            Excepciones.EscribirLog(e);
        } finally {
            cliente.close();
        }
    }

    public void VerNuevoMensaje(String json) throws BadLocationException, IOException {
        Mensaje mensaje = new Gson().fromJson(json, Mensaje.class);
        if (pnlMensajes.isVisible()) {
            if (getUsuarioOrigen() != null) {
                if (getUsuarioOrigen().getIdUsuario() == mensaje.getIdUsuarioOrigen()) {
                    CambiarALeido(mensaje);
                    AgregarMensaje(mensaje);
                    txtMensajes.setCaretPosition(txtMensajes.getDocument().getLength());
                } else {
                    for (int i = 0; i < lisUsuarios.getModel().getSize(); i++) {
                        Usuario usuario = (Usuario) lisUsuarios.getModel().getElementAt(i);
                        if (usuario.getIdUsuario() == mensaje.getIdUsuarioOrigen()) {
                            usuario.setMensajesSinLeer(usuario.getMensajesSinLeer() + 1);
                            lisUsuarios.updateUI();
                        }
                    }
                }
            } else {
                for (int i = 0; i < lisUsuarios.getModel().getSize(); i++) {
                    Usuario usuario = (Usuario) lisUsuarios.getModel().getElementAt(i);
                    if (usuario.getIdUsuario() == mensaje.getIdUsuarioOrigen()) {
                        usuario.setMensajesSinLeer(usuario.getMensajesSinLeer() + 1);
                        lisUsuarios.updateUI();
                    }
                }
            }
        } else {
            this.mensajesSinLeer++;
            VerMensajesSinLeer();
        }
    }

    ////////////////////////////////////////////////////////////////////////////
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
                    String json = (String) ois.readObject();
                    VerNuevoMensaje(json);
                    //close resources
                    ois.close();
                    socket.close();
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
        pnlMensajes = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnOcultarMensajes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lisUsuarios = new javax.swing.JList();
        txtMensaje = new javax.swing.JTextField();
        btnEnviarMensaje = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMensajes = new javax.swing.JEditorPane();
        pnlTabs = new javax.swing.JPanel();
        tpnlTabs = new javax.swing.JTabbedPane();
        pnlFondoLogin = new javax.swing.JPanel();
        pnlLogin = new javax.swing.JPanel();
        frameProcesando = new javax.swing.JPanel();
        lblProcesandoLogin = new javax.swing.JLabel();
        frameLogin = new javax.swing.JPanel();
        pnlTituloLogin = new javax.swing.JPanel();
        lblTituloLogin = new javax.swing.JLabel();
        lblUsuarioLogin = new javax.swing.JLabel();
        txtUsuarioLogin = new javax.swing.JTextField();
        lblClaveLogin = new javax.swing.JLabel();
        txtClaveLogin = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();

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

        pnlMensajes.setBackground(java.awt.Color.white);
        pnlMensajes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/message-3-16.png"))); // NOI18N
        lblTitulo.setText("MENSAJES");

        btnOcultarMensajes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/close-window-16.png"))); // NOI18N
        btnOcultarMensajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarMensajesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(btnOcultarMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOcultarMensajes)
                    .addGroup(pnlTituloLayout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );

        lisUsuarios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lisUsuariosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lisUsuarios);

        btnEnviarMensaje.setText(">>");
        btnEnviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarMensajeActionPerformed(evt);
            }
        });

        txtMensajes.setContentType("text/html"); // NOI18N
        jScrollPane4.setViewportView(txtMensajes);

        javax.swing.GroupLayout pnlMensajesLayout = new javax.swing.GroupLayout(pnlMensajes);
        pnlMensajes.setLayout(pnlMensajesLayout);
        pnlMensajesLayout.setHorizontalGroup(
            pnlMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMensajesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlMensajesLayout.createSequentialGroup()
                        .addComponent(txtMensaje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        pnlMensajesLayout.setVerticalGroup(
            pnlMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMensajesLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMensajesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEnviarMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(txtMensaje))
                .addContainerGap())
        );

        pnlTabs.setBackground(java.awt.Color.white);

        tpnlTabs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tpnlTabs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tpnlTabsMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlTabsLayout = new javax.swing.GroupLayout(pnlTabs);
        pnlTabs.setLayout(pnlTabsLayout);
        pnlTabsLayout.setHorizontalGroup(
            pnlTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnlTabs)
        );
        pnlTabsLayout.setVerticalGroup(
            pnlTabsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnlTabs)
        );

        pnlFondoLogin.setBackground(java.awt.Color.white);

        frameProcesando.setBackground(java.awt.Color.white);

        lblProcesandoLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProcesandoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/process-32.gif"))); // NOI18N
        lblProcesandoLogin.setText("Procesando...");

        javax.swing.GroupLayout frameProcesandoLayout = new javax.swing.GroupLayout(frameProcesando);
        frameProcesando.setLayout(frameProcesandoLayout);
        frameProcesandoLayout.setHorizontalGroup(
            frameProcesandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProcesandoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );
        frameProcesandoLayout.setVerticalGroup(
            frameProcesandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProcesandoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        frameLogin.setBackground(java.awt.Color.white);
        frameLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlTituloLogin.setBackground(new java.awt.Color(67, 100, 130));
        pnlTituloLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTituloLogin.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTituloLogin.setForeground(java.awt.Color.white);
        lblTituloLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/key-16.png"))); // NOI18N
        lblTituloLogin.setText("INGRESAR AL SISTEMA");

        javax.swing.GroupLayout pnlTituloLoginLayout = new javax.swing.GroupLayout(pnlTituloLogin);
        pnlTituloLogin.setLayout(pnlTituloLoginLayout);
        pnlTituloLoginLayout.setHorizontalGroup(
            pnlTituloLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTituloLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTituloLoginLayout.setVerticalGroup(
            pnlTituloLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblUsuarioLogin.setText("USUARIO");

        txtUsuarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioLoginActionPerformed(evt);
            }
        });

        lblClaveLogin.setText("CLAVE");

        txtClaveLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveLoginActionPerformed(evt);
            }
        });

        btnIngresar.setText("INGRESAR");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameLoginLayout = new javax.swing.GroupLayout(frameLogin);
        frameLogin.setLayout(frameLoginLayout);
        frameLoginLayout.setHorizontalGroup(
            frameLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTituloLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLoginLayout.createSequentialGroup()
                        .addGroup(frameLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuarioLogin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblClaveLogin, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(frameLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuarioLogin)
                            .addComponent(txtClaveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnIngresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        frameLoginLayout.setVerticalGroup(
            frameLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameLoginLayout.createSequentialGroup()
                .addComponent(pnlTituloLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(frameLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioLogin)
                    .addComponent(txtUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(frameLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClaveLogin)
                    .addComponent(txtClaveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIngresar)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addComponent(frameProcesando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(frameLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frameProcesando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(frameLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout pnlFondoLoginLayout = new javax.swing.GroupLayout(pnlFondoLogin);
        pnlFondoLogin.setLayout(pnlFondoLoginLayout);
        pnlFondoLoginLayout.setHorizontalGroup(
            pnlFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLoginLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlFondoLoginLayout.setVerticalGroup(
            pnlFondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLoginLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(pnlFondoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(pnlTabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMensajes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(pnlFondoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(pnlTabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        OcultarBanner();
        DesconectarUsuario();
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
        VerUsuarios();
    }//GEN-LAST:event_btnVerMensajesActionPerformed

    private void btnOcultarMensajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarMensajesActionPerformed
        // TODO add your handling code here:
        pnlMensajes.setVisible(false);
        usuarioOrigen = null;
    }//GEN-LAST:event_btnOcultarMensajesActionPerformed

    private void lisUsuariosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lisUsuariosValueChanged
        // TODO add your handling code here:
        if (lisUsuarios.getSelectedValue() != null) {
            setUsuarioOrigen((Usuario) lisUsuarios.getSelectedValue());
            VerMensajes();
        }
    }//GEN-LAST:event_lisUsuariosValueChanged

    private void btnEnviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarMensajeActionPerformed
        // TODO add your handling code here:
        if (lisUsuarios.getSelectedValue() != null) {
            EnviarMensaje();
        }
    }//GEN-LAST:event_btnEnviarMensajeActionPerformed

    private void tpnlTabsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpnlTabsMouseReleased
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            JPopupMenu popup = new JPopupMenu();
            popup.setLayout(new java.awt.BorderLayout());
            JMenuItem menuClose = new JMenuItem("CERRAR");
            menuClose.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tpnlTabs.remove(tpnlTabs.getSelectedIndex());
                }
            });
            popup.add(menuClose);
            popup.show(tpnlTabs, evt.getPoint().x, evt.getPoint().y);
        }
    }//GEN-LAST:event_tpnlTabsMouseReleased

    private void txtUsuarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioLoginActionPerformed
        // TODO add your handling code here:
        new swAutenticar().execute();
    }//GEN-LAST:event_txtUsuarioLoginActionPerformed

    private void txtClaveLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveLoginActionPerformed
        // TODO add your handling code here:
        new swAutenticar().execute();
    }//GEN-LAST:event_txtClaveLoginActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
        new swAutenticar().execute();
    }//GEN-LAST:event_btnIngresarActionPerformed

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
    private javax.swing.JButton btnEnviarMensaje;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnOcultarMensajes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerMensajes;
    private javax.swing.JPanel frameLogin;
    private javax.swing.JPanel frameProcesando;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblClaveLogin;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblProcesandoLogin;
    private javax.swing.JLabel lblSeparador;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloBanner;
    private javax.swing.JLabel lblTituloLogin;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuarioLogin;
    private javax.swing.JList lisUsuarios;
    private javax.swing.JPanel pnlBanner;
    private javax.swing.JPanel pnlFondoLogin;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlMensajes;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlTabs;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JPanel pnlTituloLogin;
    private javax.swing.JTabbedPane tpnlTabs;
    private javax.swing.JPasswordField txtClaveLogin;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JEditorPane txtMensajes;
    private javax.swing.JLabel txtUsuario;
    private javax.swing.JTextField txtUsuarioLogin;
    // End of variables declaration//GEN-END:variables
}
