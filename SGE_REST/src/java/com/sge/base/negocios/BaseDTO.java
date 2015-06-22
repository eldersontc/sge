package com.sge.base.negocios;

import com.sge.base.accesoDatos.BaseDAO;
import com.sge.modulos.administracion.accesoDatos.FiltroDAO;
import com.sge.modulos.administracion.entidades.Filtro;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author elderson
 */
public class BaseDTO {

    private int idUsuario;

    public BaseDTO(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getCarpetaReportes() throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        String carpetaReportes = null;
        try {
            input = new FileInputStream(System.getProperty("user.home") + "/SGE_REST_CONF/config.properties");
            prop.load(input);
            carpetaReportes = prop.getProperty("carpetaReportes");
            input.close();
        } catch (IOException ex) {
            throw ex;
        }
        return carpetaReportes;
    }

    public String getCarpetaGraficos() throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        String carpetaGraficos = null;
        try {
            input = new FileInputStream(System.getProperty("user.home") + "/SGE_REST_CONF/config.properties");
            prop.load(input);
            carpetaGraficos = prop.getProperty("carpetaGraficos");
            input.close();
        } catch (IOException ex) {
            throw ex;
        }
        return carpetaGraficos;
    }

    public String getFiltro(BaseDAO baseDAO, int idEntidad, String filtro) {

        FiltroDAO filtroDAO = new FiltroDAO();
        filtroDAO.AsignarSesion(baseDAO);

        List<Object[]> fs = new ArrayList<>();
        fs.add(new Object[]{"idEntidad", idEntidad});
        fs.add(new Object[]{"idUsuario", getIdUsuario()});

        List<Filtro> filtros = filtroDAO.ObtenerLista(Filtro.class, fs);

        if (filtros.size() > 0) {
            if (filtro.isEmpty()) {
                filtro += " WHERE " + filtros.get(0).getFiltro();
            } else {
                filtro += "  AND  " + filtros.get(0).getFiltro();
            }
        }

        return filtro;
    }
}
