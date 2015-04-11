package com.sge.base.formularios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sge.base.controles.FabricaControles;
import com.sge.base.excepciones.Excepciones;
import com.sge.base.utils.Utils;
import com.sge.modulos.administracion.clases.ValorDefinido;
import com.sge.modulos.administracion.cliente.cliAdministracion;
import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 *
 * @author elderson
 */
public class frameBase<T> extends javax.swing.JInternalFrame {

    /////////////////////////////// VARIABLES //////////////////////////////////
    private T entidad;

    public T getEntidad() {
        return entidad;
    }

    public void setEntidad(T entidad) {
        this.entidad = entidad;
    }

    public Class<?> getClazz() throws ClassNotFoundException {
        String clazz = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        return Class.forName(clazz);
    }

    public T nuevaIntancia() throws Exception {
        try {
            Class<?> clazz = getClazz();
            Constructor<?> constructor = clazz.getConstructors()[0];
            return (T) constructor.newInstance(new Object[]{});
        } catch (Exception e) {
            throw e;
        }
    }

    //////////////////////////////// CLASES ////////////////////////////////////
    public void ObtenerValoresDefinidos(JPanel frame, int idUsuario, String entidad) {
        new swObtenerValoresDefinidos(frame, idUsuario, entidad).execute();
    }

    public class swObtenerValoresDefinidos extends SwingWorker<Object, Object> {

        private int usuario;
        private String entidad;
        private JPanel frame;

        public swObtenerValoresDefinidos(JPanel frame, int usuario, String entidad) {
            this.frame = frame;
            this.usuario = usuario;
            this.entidad = entidad;
        }

        @Override
        protected Object doInBackground() {
            VerCargando(frame);
            cliAdministracion cliente = new cliAdministracion();
            try {
                String json = cliente.ObtenerValoresDefinidos(new Gson().toJson(String.format("WHERE idUsuario = %d AND entidad = '%s'", usuario, entidad)));
                String[] resultado = new Gson().fromJson(json, String[].class);
                if (resultado[0].equals("true")) {
                    List<Object[]> valoresDefinidos = (List<Object[]>) new Gson().fromJson(resultado[1], new TypeToken<List<Object[]>>() {
                    }.getType());
                    if (valoresDefinidos.isEmpty()) {
                        setEntidad(nuevaIntancia());
                    } else {
                        json = cliente.ObtenerValorDefinido(new Gson().toJson(valoresDefinidos.get(0)[0]));
                        resultado = new Gson().fromJson(json, String[].class);
                        if (resultado[0].equals("true")) {
                            ValorDefinido valorDefinido = new Gson().fromJson(resultado[1], ValorDefinido.class);
                            setEntidad((T) new Gson().fromJson(valorDefinido.getJson(), getClazz()));
                        }
                    }
                    AsignarControles();
                }
            } catch (Exception e) {
                OcultarCargando(frame);
            } finally {
                cliente.close();
            }
            return null;
        }

        @Override
        protected void done() {
            OcultarCargando(frame);
        }
    }

    //////////////////////////////// UTILS /////////////////////////////////////
    public boolean FilaActiva(JTable tabla) {
        return Utils.FilaActiva(tabla);
    }

    public <T> T ObtenerValorCelda(JTable tabla, int columna) {
        return Utils.ObtenerValorCelda(tabla, columna);
    }

    public <T> T ObtenerValorCelda(JTable tabla, int fila, int columna) {
        return Utils.ObtenerValorCelda(tabla, fila, columna);
    }

    public void AsignarValorCelda(JTable tabla, Object valor, int fila, int columna) {
        Utils.AsignarValorCelda(tabla, valor, fila, columna);

    }

    public void AgregarFila(JTable tabla, Object[] fila) {
        Utils.AgregarFila(tabla, fila);
    }

    public void EliminarTodasFilas(JTable tabla) {
        Utils.EliminarTodasFilas(tabla);
    }

    public void EliminarFila(JTable tabla) {
        Utils.EliminarFila(tabla);
    }

    public void OcultarColumna(JTable tabla, int columna) {
        Utils.OcultarColumna(tabla, columna);
    }

    public void OcultarControl(Component componet) {
        Utils.OcultarControl(componet);
    }

    public void AgregarOrdenamiento(JTable tabla) {
        Utils.AgregarOrdenamiento(tabla);
    }

    public void Filtrar(JTable tabla, String filtro) {
        Utils.Filtrar(tabla, filtro);
    }

    ////////////////////////// FRABRICA DE CONTROLES ///////////////////////////
    public void VerProcesando(JPanel panel) {
        FabricaControles.VerProcesando(panel);
    }

    public void OcultarProcesando(JPanel panel) {
        FabricaControles.OcultarProcesando(panel);
    }

    public void AgregarCombo(JTable table, int column, int indexField) {
        FabricaControles.AgregarCombo(table, column, indexField);
    }

    public void AgregarEventoChange(JTable table, Action action) {
        FabricaControles.AgregarEventoChange(table, action);
    }

    public void VerCargando(JPanel panel) {
        FabricaControles.VerCargando(panel);
    }

    public void OcultarCargando(JPanel panel) {
        FabricaControles.OcultarCargando(panel);
    }

    public void VerModal(JInternalFrame frame, Action action) {
        FabricaControles.VerModal(this.getDesktopPane(), frame, action);
    }

    ////////////////////////////// EXCEPCIONES /////////////////////////////////
    public void ControlarExcepcion(String[] resultado) {
        Excepciones.Controlar(resultado, this);
    }

    //////////////////////////////// METODOS BASE //////////////////////////////
    public void Cerrar() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
        }
    }

    public void Init(int id) {
    }

    public void AsignarControles() {
    }

    public void OcultarControles() {
    }

    public void Aceptar() {
    }

    public void Cancelar() {
    }
}
