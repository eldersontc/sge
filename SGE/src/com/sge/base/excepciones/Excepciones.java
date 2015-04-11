package com.sge.base.excepciones;

import com.google.gson.Gson;
import com.sge.base.controles.FabricaControles;
import java.awt.Component;

/**
 *
 * @author elderson
 */
public class Excepciones {

    public static void Controlar(String[] resultado, Component component) {
        try {
            if (resultado.length > 1) {
                Exception exception = new Gson().fromJson(resultado[1], Exception.class);
                Throwable throwable = exception.getCause();
                while (throwable.getCause() != null) {
                    throwable = exception.getCause();
                }
                FabricaControles.VerAdvertencia(throwable.getMessage(), component);
            } else {
                FabricaControles.VerAdvertencia("SE PRODUJO UN ERROR.", component);
            }
        } catch (Exception e) {
            FabricaControles.VerAdvertencia("SE PRODUJO UN ERROR.", component);
        }
    }
    
    public static void Controlar(Exception exception, Component component) {
        FabricaControles.VerAdvertencia(exception.getMessage().toUpperCase(), component);
    }
}
