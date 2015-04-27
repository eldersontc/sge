package com.sge.base.excepciones;

import com.google.gson.Gson;
import com.sge.base.controles.FabricaControles;
import java.awt.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author elderson
 */
public class Excepciones {

    private static void EscribirLog(Throwable throwable) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("/home/elderson/SGE_LOG.txt", true);
            if (throwable.getStackTrace().length == 0) {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                writer.append(String.format("%s : %s \n", df.format(new Date()), throwable.getMessage()));
            } else {
                for (StackTraceElement stackTrace : throwable.getStackTrace()) {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    writer.append(String.format("%s : %s : %s : %s \n", df.format(new Date()), stackTrace.getClassName(), stackTrace.getMethodName(), stackTrace.getLineNumber()));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
            }
        }
    }

    private static void EscribirLog(Exception exception) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("/home/elderson/SGE_LOG.txt", true);
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            writer.append(String.format("%s : %s : %s \n", df.format(new Date()), exception.getClass().getName(), exception.getMessage()));
        } catch (Exception e) {
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
            }
        }
    }

    public static void Controlar(String[] resultado, Component component) {
        try {
            if (resultado.length > 1) {
                Exception exception = new Gson().fromJson(resultado[1], Exception.class);
                Throwable throwable = exception.getCause();
                if (throwable != null) {
                    EscribirLog(throwable);
                    FabricaControles.VerAdvertencia(throwable.getMessage(), component);
                } else {
                    EscribirLog(exception);
                    String mensaje = "ERROR";
                    switch (exception.getClass().getName()) {
                        case "java.lang.ClassNotFoundException":
                            mensaje = "CLASE NO ENCONTRADA";
                            break;
                    }
                    FabricaControles.VerAdvertencia(String.format("%s : %s", mensaje, exception.getMessage()), component);
                }
            }
        } catch (Exception e) {
            EscribirLog(e);
            FabricaControles.VerAdvertencia(e.getMessage(), component);
        }
    }

    public static void Controlar(Exception exception, Component component) {
        Throwable throwable = exception.getCause();
        if (throwable != null) {
            EscribirLog(throwable);
            FabricaControles.VerAdvertencia(throwable.getMessage(), component);
        } else {
            EscribirLog(exception);
            String mensaje = "ERROR";
            switch (exception.getClass().getName()) {
                case "java.lang.ClassNotFoundException":
                    mensaje = "CLASE NO ENCONTRADA";
                    break;
            }
            FabricaControles.VerAdvertencia(String.format("%s : %s", mensaje, exception.getMessage()), component);
        }
    }
}
