package com.sge.base.reportes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.FabricaControles;
import com.sge.modulos.administracion.clases.ItemReporte;
import com.sge.modulos.administracion.clases.Reporte;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author elderson
 */
public class FabricaReportes {

    /////////////////////////////// CONEXION ///////////////////////////////////
    
    private static Connection conexion;

    public static Connection getConexion(JPanel panel) {
        if (conexion == null) {
            try {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sge", "postgres", "123456");
            } catch (ClassNotFoundException | SQLException e) {
                FabricaControles.VerAdvertencia(e.getMessage(), panel);
            }
        }
        return conexion;
    }

    ///////////////////////////////// CLASES ///////////////////////////////////
    
    public static class swImprimirSinEntidad extends SwingWorker {

        private final int idReporte;
        private final JPanel panel;

        public swImprimirSinEntidad(int idReporte, JPanel panel) {
            this.idReporte = idReporte;
            this.panel = panel;
        }

        @Override
        protected Object doInBackground() throws Exception {
            FabricaControles.VerProcesando(panel);
            cliAdministracion cliente = new cliAdministracion();
            try {
                
                String json = cliente.ObtenerReporte(new Gson().toJson(idReporte));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {
                    Reporte reporte = new Gson().fromJson(resultado[1], Reporte.class);

                    Map parametros = new HashMap();
                    for (ItemReporte item : reporte.getItems()) {
                        parametros.put(item.getNombre(), item.getValor());
                    }

                    JasperPrint informa = JasperFillManager.fillReport(reporte.getUbicacion(), parametros, getConexion(panel));
                    JasperViewer view = new JasperViewer(informa, false);
                    view.setTitle(reporte.getNombre());
                    view.setVisible(true);
                }
            } catch (Exception e) {
                FabricaControles.OcultarProcesando(panel);
                FabricaControles.VerAdvertencia(e.getMessage(), panel);
            } finally {
                cliente.close();
            }
            return null;
        }

        @Override
        protected void done() {
            FabricaControles.OcultarProcesando(panel);
        }
    }

    public static class swImprimirConEntidad extends SwingWorker {

        private final String entidad;
        private final int idEntidad;
        private final JPanel panel;

        public swImprimirConEntidad(String entidad, int idEntidad, JPanel panel) {
            this.entidad = entidad;
            this.idEntidad = idEntidad;
            this.panel = panel;
        }

        @Override
        protected Object doInBackground() throws Exception {
            FabricaControles.VerProcesando(panel);
            cliAdministracion cliente = new cliAdministracion();
            try {
                
                String json = cliente.ObtenerReportes(new Gson().toJson(String.format("WHERE entidad = '%s'", entidad)));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {

                    List<Object[]> reportes = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                    }.getType());

                    if (reportes.isEmpty()) {
                        throw new Exception("NO HAY NINGUN REPORTE!");
                    }

                    Reporte seleccionado = null;

                    if (reportes.size() == 1) {
                        json = cliente.ObtenerReporte(new Gson().toJson(reportes.get(0)[0]));
                        resultado = new Gson().fromJson(json, String[].class);
                        seleccionado = new Gson().fromJson(resultado[1], Reporte.class);
                    } else {
                        JComboBox combo = new JComboBox();
                        for (Object[] reporte : reportes) {
                            combo.addItem(reporte[1]);
                        }
                        FabricaControles.VerModal(panel, combo, "SELECCIONE UN REPORTE");
                        for (Object[] reporte : reportes) {
                            if (reporte[1].equals(combo.getSelectedItem())) {
                                json = cliente.ObtenerReporte(new Gson().toJson(reporte[0]));
                                resultado = new Gson().fromJson(json, String[].class);
                                seleccionado = new Gson().fromJson(resultado[1], Reporte.class);
                                break;
                            }
                        }
                    }

                    Map parametros = new HashMap();
                    for (ItemReporte item : seleccionado.getItems()) {
                        if (item.isAsignarId()) {
                            parametros.put(item.getNombre(), idEntidad);
                        } else {
                            parametros.put(item.getNombre(), item.getValor());
                        }
                    }

                    JasperPrint informa = JasperFillManager.fillReport(seleccionado.getUbicacion(), parametros, getConexion(panel));
                    JasperViewer view = new JasperViewer(informa, false);
                    view.setTitle(seleccionado.getNombre());
                    view.setVisible(true);
                }
            } catch (Exception e) {
                FabricaControles.OcultarProcesando(panel);
                FabricaControles.VerAdvertencia(e.getMessage(), panel);
            } finally {
                cliente.close();
            }
            return null;
        }

        @Override
        protected void done() {
            FabricaControles.OcultarProcesando(panel);
        }
    }

    //////////////////////////////// METODOS ///////////////////////////////////
    
    public static void Imprimir(int idReporte, JPanel panel) {
        new swImprimirSinEntidad(idReporte, panel).execute();
    }

    public static void Imprimir(String entidad, int idEntidad, JPanel panel) {
        new swImprimirConEntidad(entidad, idEntidad, panel).execute();
    }
}
