package com.sge.base.reportes;

import com.google.gson.Gson;
import com.sge.base.controles.FabricaControles;
import com.sge.base.excepciones.Excepciones;
import com.sge.modulos.administracion.clases.Reporte;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
        protected Object doInBackground() {
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
        protected Object doInBackground() {
            FabricaControles.VerProcesando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {
                String json = cliente.ObtenerReportes(new Gson().toJson("WHERE Reporte.idEntidad = " + idEntidad));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    Reporte[] reportes = new Gson().fromJson(resultado[1], Reporte[].class);
                    if (reportes.length == 0) {
                        throw new Exception("NO HAY NINGUN REPORTE!");
                    }
                    Reporte reporteSeleccionado = null;
                    if (reportes.length == 1) {
                        reporteSeleccionado = reportes[0];
                    } else {
                        JComboBox combo = new JComboBox();
                        for (Reporte reporte : reportes) {
                            combo.addItem(reporte);
                        }
                        int confirmacion = FabricaControles.VerModal(frame, combo, "SELECCIONE UN REPORTE");
                        if (confirmacion == 0) {
                            reporteSeleccionado = (Reporte) combo.getSelectedItem();
                        }
                    }
                    if (reporteSeleccionado != null) {
                        json = cliente.GenerarReporteConEntidad(new Gson().toJson(new int[]{reporteSeleccionado.getIdReporte(), id}));
                        resultado = new Gson().fromJson(json, String[].class);
                        if (resultado[0].equals("true")) {
                            byte[] bytes = new Gson().fromJson(resultado[1], byte[].class);
                            InputStream is = new ByteArrayInputStream(bytes);
                            JasperViewer view = new JasperViewer(is, false, false);
                            view.setTitle(reporteSeleccionado.getNombre());
                            view.setVisible(true);
                        } else {
                            Excepciones.Controlar(resultado, frame);
                        }
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
    public static void ImprimirSinEntidad(int idReporte, String titulo, JPanel frame) {
        new swImprimirSinEntidad(idReporte, titulo, frame).execute();
    }

    public static void ImprimirConEntidad(int idEntidad, int id, JPanel frame) {
        new swImprimirConEntidad(idEntidad, id, frame).execute();
    }
}
