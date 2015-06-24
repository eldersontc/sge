package com.sge.modulos.administracion.formularios;

import com.google.gson.Gson;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.administracion.clases.Menu;
import com.sge.modulos.administracion.clases.Perfil;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author elderson
 */
public class perMenu extends frameBase<Menu> {

    /**
     * Creates new form perMenux
     *
     * @param modo
     */
    public perMenu(int modo) {
        initComponents();
        setModo(modo);
        setFiltro("");
    }

    /**
     * Creates new form perMenux
     *
     * @param modo
     * @param filtro
     */
    public perMenu(int modo, String filtro) {
        initComponents();
        setModo(modo);
        setFiltro(filtro);
    }

    @Override
    public void Init() {
        OcultarControl(treeMenus);
        new swObtenerPerfiles().execute();
    }

    public class swObtenerPerfiles extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                json = cliente.ObtenerPerfiles(new Gson().toJson(getFiltro()));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Perfil[] perfiles = new Gson().fromJson(resultado[1], Perfil[].class);
                    cboPerfiles.removeAllItems();
                    for (Perfil perfil : perfiles) {
                        cboPerfiles.addItem(perfil);
                    }
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public class swObtenerMenusPorPerfil extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() throws Exception {
            VerCargando(frame);
            VerControl(treeMenus);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                Perfil perfil = (Perfil) cboPerfiles.getSelectedItem();
                json = cliente.ObtenerMenusPorPerfil(new Gson().toJson(perfil.getIdPerfil()));
            } catch (Exception e) {
                OcultarCargando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliente.close();
            }
            return json;
        }

        @Override
        protected void done() {
            try {
                String json = get().toString();
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    Menu[] menus = new Gson().fromJson(resultado[1], Menu[].class);
                    DefaultMutableTreeNode root = new DefaultMutableTreeNode();
                    for (Menu menu : menus) {
                        DefaultMutableTreeNode nivel1 = new DefaultMutableTreeNode(menu);
                        for (Menu subMenu : menu.getSubMenus()) {
                            DefaultMutableTreeNode nivel2 = new DefaultMutableTreeNode(subMenu);
                            for (Menu subSubMenu : subMenu.getSubMenus()) {
                                DefaultMutableTreeNode nivel3 = new DefaultMutableTreeNode(subSubMenu);
                                nivel2.add(nivel3);
                            }
                            nivel1.add(nivel2);
                        }
                        root.add(nivel1);
                    }
                    treeMenus.setModel(new DefaultTreeModel(root, false));
                    ExpandirTodosNodos(treeMenus);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    private List<Menu> menus;

    private void getSubMenus(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Menu) {
            Menu menu = (Menu) node.getUserObject();
            if (menu.isCheck() != menu.isNuevoCheck()) {
                if (menu.isNuevoCheck()) {
                    menu.setAgregar(true);
                } else {
                    menu.setEliminar(true);
                }
                menus.add(menu);
            }
        }
        TreePath tp = new TreePath(node.getPath());
        for (int i = 0; i < node.getChildCount(); i++) {
            getSubMenus((DefaultMutableTreeNode) tp.pathByAddingChild(node.getChildAt(i)).getLastPathComponent());
        }
    }

    public List<Menu> getMenus() {
        this.menus = new ArrayList<>();
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) treeMenus.getModel().getRoot();
        getSubMenus(nodo);
        return menus;
    }

    public class swActualizarPermisos extends SwingWorker<Object, Object> {

        @Override
        protected Object doInBackground() {
            VerProcesando(frame);
            cliAdministracion cliente = new cliAdministracion();
            String json = "";
            try {
                Perfil perfil = (Perfil) cboPerfiles.getSelectedItem();
                String[] arrayJson = new String[]{new Gson().toJson(getMenus()), new Gson().toJson(perfil.getIdPerfil())};
                json = cliente.ActualizarPermisos(new Gson().toJson(arrayJson));
            } catch (Exception e) {
                OcultarProcesando(frame);
                cancel(false);
                ControlarExcepcion(e);
            } finally {
                cliente.close();
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
        pnlTitulo1 = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        btnVer = new javax.swing.JButton();
        cboPerfiles = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        treeMenus = new com.sge.base.controles.JCheckBoxTree();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        frame.setBackground(java.awt.Color.white);

        pnlTitulo1.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo1.setForeground(java.awt.Color.white);
        lblTitulo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/show-property-16.png"))); // NOI18N
        lblTitulo1.setText("PERMISOS DE MENUS");

        javax.swing.GroupLayout pnlTitulo1Layout = new javax.swing.GroupLayout(pnlTitulo1);
        pnlTitulo1.setLayout(pnlTitulo1Layout);
        pnlTitulo1Layout.setHorizontalGroup(
            pnlTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitulo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(315, Short.MAX_VALUE))
        );
        pnlTitulo1Layout.setVerticalGroup(
            pnlTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTitulo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lblPerfil.setText("PERFIL");

        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/view-16.png"))); // NOI18N
        btnVer.setToolTipText("REFRESCAR");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        treeMenus.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeMenus.setRootVisible(false);
        jScrollPane2.setViewportView(treeMenus);

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

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblPerfil)
                        .addGap(36, 36, 36)
                        .addComponent(cboPerfiles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(frameLayout.createSequentialGroup()
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPerfil)
                        .addComponent(cboPerfiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
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
            .addComponent(frame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
        new swObtenerMenusPorPerfil().execute();
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        new swActualizarPermisos().execute();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        new swObtenerMenusPorPerfil().execute();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox cboPerfiles;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JPanel pnlTitulo1;
    private com.sge.base.controles.JCheckBoxTree treeMenus;
    // End of variables declaration//GEN-END:variables
}
