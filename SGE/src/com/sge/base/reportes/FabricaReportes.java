package com.sge.base.reportes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.FabricaControles;
import com.sge.base.excepciones.Excepciones;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author elderson
 */
public class FabricaReportes {

    ///////////////////////////////// CLASES ///////////////////////////////////
    public static class swImprimirSinEntidad extends SwingWorker {

        private final int idReporte;
        private final String titulo;
        private final JPanel frame;

        public swImprimirSinEntidad(int idReporte, String titulo, JPanel frame) {
            this.idReporte = idReporte;
            this.titulo = titulo;
            this.frame = frame;
        }

        @Override
        protected Object doInBackground() throws Exception {
            FabricaControles.VerProcesando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {
                String json = cliente.GenerarReporteSinEntidad(new Gson().toJson(idReporte));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    byte[] bytes = new Gson().fromJson(resultado[1], byte[].class);
                    InputStream is = new ByteArrayInputStream(bytes);
                    JasperViewer view = new JasperViewer(is, false, false);
                    view.setTitle(titulo);
                    view.setVisible(true);
                } else {
                    Excepciones.Controlar(resultado, frame);
                }
            } catch (Exception e) {
                FabricaControles.OcultarProcesando(frame);
                cancel(false);
                Excepciones.Controlar(e, frame);
            } finally {
                cliente.close();
            }
            return null;
        }

        @Override
        protected void done() {
            FabricaControles.OcultarProcesando(frame);
        }
    }

    public static class swImprimirConEntidad extends SwingWorker {

        private final int id;
        private final int idEntidad;
        private final JPanel frame;

        public swImprimirConEntidad(int idEntidad, int id, JPanel frame) {
            this.id = id;
            this.idEntidad = idEntidad;
            this.frame = frame;
        }

        @Override
        protected Object doInBackground() throws Exception {
            FabricaControles.VerProcesando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {

                String json = cliente.ObtenerReportes(new Gson().toJson(String.format("WHERE Reporte.idEntidad = %d", idEntidad)));
                String[] resultado = new Gson().fromJson(json, String[].class);

                if (resultado[0].equals("true")) {

                    List<Object[]> reportes = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                    }.getType());

                    if (reportes.isEmpty()) {
                        throw new Exception("NO HAY NINGUN REPORTE!");
                    }

                    String titulo = "";

                    int idReporte = 0;

                    if (reportes.size() == 1) {
                        idReporte = ((Double) reportes.get(0)[0]).intValue();
                        titulo = reportes.get(0)[1].toString();
                    } else {
                        JComboBox combo = new JComboBox();
                        for (Object[] reporte : reportes) {
                            combo.addItem(reporte[1]);
                        }
                        FabricaControles.VerModal(frame, combo, "SELECCIONE UN REPORTE");
                        for (Object[] reporte : reportes) {
                            if (reporte[1].equals(combo.getSelectedItem())) {
                                idReporte = ((Double) reporte[0]).intValue();
                                titulo = reporte[1].toString();
                                break;
                            }
                        }
                    }

                    json = cliente.GenerarReporteConEntidad(new Gson().toJson(new int[]{idReporte, id}));
                    resultado = new Gson().fromJson(json, String[].class);
                    if (resultado[0].equals("true")) {
                        byte[] bytes = new Gson().fromJson(resultado[1], byte[].class);
                        InputStream is = new ByteArrayInputStream(bytes);
                        JasperViewer view = new JasperViewer(is, false, false);
                        view.setTitle(titulo);
                        view.setVisible(true);
                    } else {
                        Excepciones.Controlar(resultado, frame);
                    }
                } else {
                    Excepciones.Controlar(resultado, frame);
                }
            } catch (Exception e) {
                FabricaControles.OcultarProcesando(frame);
                cancel(false);
                Excepciones.Controlar(e, frame);
            } finally {
                cliente.close();
            }
            return null;
        }

        @Override
        protected void done() {
            FabricaControles.OcultarProcesando(frame);
        }
    }

    //////////////////////////////// METODOS ///////////////////////////////////
    public static void Imprimir(int idReporte, String titulo, JPanel frame) {
        new swImprimirSinEntidad(idReporte, titulo, frame).execute();
    }

    public static void Imprimir(int idEntidad, int id, JPanel frame) {
        new swImprimirConEntidad(idEntidad, id, frame).execute();
    }
}
