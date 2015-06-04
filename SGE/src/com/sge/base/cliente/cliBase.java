package com.sge.base.cliente;

import com.sge.base.excepciones.Excepciones;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author elderson
 */
public class cliBase {

    public String getIpServidor() {
        Properties prop = new Properties();
        InputStream input = null;
        String ipServidor = null;
        try {
            input = new FileInputStream(System.getProperty("user.home") + "/SGE_CONF/config.properties");
            prop.load(input);
            ipServidor = prop.getProperty("ipServidor");
            input.close();
        } catch (IOException ex) {
            Excepciones.EscribirLog(ex);
        }
        return ipServidor;
    }
}
