package com.sge.base.negocios;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author elderson
 */
public class BaseDTO {
    
    public String getCarpetaReportes() throws IOException {
//        Properties prop = new Properties();
//        InputStream input = null;
//        String carpetaReportes = null;
//        try {
//            input = new FileInputStream(System.getProperty("user.dir") + "/SGE_CONF/config.properties");
//            prop.load(input);
//            carpetaReportes = prop.getProperty("carpetaReportes");
//            input.close();
//        } catch (IOException ex) {
//            throw  ex;
//        }
//        return carpetaReportes;
        return "/home/elderson/REPORTES/";
    }
    
    public String getCarpetaGraficos() throws IOException {
//        Properties prop = new Properties();
//        InputStream input = null;
//        String carpetaGraficos = null;
//        try {
//            input = new FileInputStream(System.getProperty("user.dir") + "/SGE_CONF/config.properties");
//            prop.load(input);
//            carpetaGraficos = prop.getProperty("carpetaGraficos");
//            input.close();
//        } catch (IOException ex) {
//            throw  ex;
//        }
//        return carpetaGraficos;
        return "/home/elderson/GRAFICOS/";
    }
}
