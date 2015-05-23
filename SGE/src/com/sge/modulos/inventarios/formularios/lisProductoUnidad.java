package com.sge.modulos.inventarios.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.formularios.frameBase;
import com.sge.modulos.inventarios.clases.ProductoUnidad;
import com.sge.modulos.inventarios.cliente.cliInventarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class lisProductoUnidad extends frameBase<ProductoUnidad> {

    /**
     * Creates new form lisProductoUnidad
     */
    public lisProductoUnidad(String filtro) {
        initComponents();
        Init(filtro);
    }

    private String filtro;

    private List<ProductoUnidad> seleccionados = new ArrayList<>();

    public class swObtenerUnidades extends SwingWorker {

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliInventarios cliente = new cliInventarios();
            String json = "";
            try {
                json = cliente.ObtenerProductoUnidades(new Gson().toJson(filtro));
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
                    EliminarTodasFilas(tbUnidades);
                    ProductoUnidad[] unidades = new Gson().fromJson(resultado[1].toString(), ProductoUnidad[].class);
                    for (ProductoUnidad unidad : unidades) {
                        AgregarFila(tbUnidades, new Object[]{false, unidad.getIdProductoUnidad(), unidad.getIdUnidad(), unidad.getAbreviacionUnidad(), unidad.getFactor()});
                    }
                    AgregarOrdenamiento(tbUnidades);
                }
                OcultarCargando(frame);
            } catch (Exception e) {
                OcultarCargando(frame);
                ControlarExcepcion(e);
            }
        }
    }

    public void Init(String filtro) {
        this.filtro = filtro;
        new swObtenerUnidades().execute();
    }

    public List<ProductoUnidad> getSeleccionados() {
        return seleccionados;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUnidades = new javax.swing.JTable();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JButton();
        lblFiltro = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();

        setClosable(true);

        frame.setBackground(java.awt.Color.white);
        frame.setBorder(null);

        tbUnidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHECK", "ID", "IDUNIDAD", "ABREVIACION", "FACTOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUnidades.setRowHeight(25);
        tbUnidades.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbUnidades);
        if (tbUnidades.getColumnModel().getColumnCount() > 0) {
            tbUnidades.getColumnModel().getColumn(1).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(1).setMaxWidth(0);
            tbUnidades.getColumnModel().getColumn(2).setMinWidth(0);
            tbUnidades.getColumnModel().getColumn(2).setPreferredWidth(0);
            tbUnidades.getColumnModel().getColumn(2).setMaxWidth(0);
            tbUnidades.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        pnlTitulo.setBackground(new java.awt.Color(67, 100, 130));
        pnlTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblTitulo.setForeground(java.awt.Color.white);
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/list-view-16.png"))); // NOI18N
        lblTitulo.setText("LISTADO DE UNIDADES");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addContainerGap(163, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        btnSeleccionar.setText("SELECCIONAR");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        lblFiltro.setText("FILTRO");

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });

        btnRefrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sge/base/imagenes/refresh-16.png"))); // NOI18N
        btnRefrescar.setToolTipText("REFRESCAR");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameLayout = new javax.swing.GroupLayout(frame);
        frame.setLayout(frameLayout);
        frameLayout.setHorizontalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(frameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(frameLayout.createSequentialGroup()
                        .addComponent(lblFiltro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        frameLayout.setVerticalGroup(
            frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameLayout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFiltro)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSeleccionar)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tbUnidades.getRowCount(); i++) {
            boolean check = ObtenerValorCelda(tbUnidades, i, 0);
            if (check) {
                ProductoUnidad productoUnidad = new ProductoUnidad();
                productoUnidad.setIdProductoUnidad(ObtenerValorCelda(tbUnidades, i, 1));
                productoUnidad.setIdUnidad(ObtenerValorCelda(tbUnidades, i, 2));
                productoUnidad.setAbreviacionUnidad(ObtenerValorCelda(tbUnidades, i, 3));
                productoUnidad.setFactor(ObtenerValorCelda(tbUnidades, i, 4));
                seleccionados.add(productoUnidad);
            }
        }
        Cerrar();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
        Filtrar(tbUnidades, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        new swObtenerUnidades().execute();
    }//GEN-LAST:event_btnRefrescarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JPanel frame;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tbUnidades;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
