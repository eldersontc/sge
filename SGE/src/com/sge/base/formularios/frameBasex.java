package com.sge.base.formularios;

import com.google.gson.Gson;
import com.sge.base.controles.FabricaControles;
import com.sge.base.excepciones.Excepciones;
import com.sge.base.reportes.FabricaReportes;
import com.sge.base.utils.Utils;
import com.sge.modulos.administracion.clases.Usuario;
import com.sge.modulos.administracion.clases.ValorDefinido;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 *
 * @author elderson
 */
public class frameBasex<T> extends JInternalFrame {

    /////////////////////////////// VARIABLES //////////////////////////////////
    private T entidad;

    private Usuario usuario;

    public T getEntidad() {
        return entidad;
    }

    public void setEntidad(T entidad) {
        this.entidad = entidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public void AsignarValorCelda(JTable tabla, Object valor, int columna) {
        Utils.AsignarValorCelda(tabla, valor, columna);
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
    
    public void MostrarColumna(JTable tabla, int columna, int ancho) {
        Utils.MostrarColumna(tabla, columna, ancho);
    }

    public void OcultarColumna(JTable tabla, int columna) {
        Utils.OcultarColumna(tabla, columna);
    }

    public void OcultarColumnas(JTable tabla, int[] columnas) {
        Utils.OcultarColumnas(tabla, columnas);
    }

    public void OcultarControl(Component component) {
        Utils.OcultarControl(component);
    }

    public void MostrarControl(Component component) {
        Utils.MostrarControl(component);
    }

    public void AgregarOrdenamiento(JTable tabla) {
        Utils.AgregarOrdenamiento(tabla);
    }

    public void Filtrar(JTable tabla, String filtro) {
        Utils.Filtrar(tabla, filtro);
    }

    public void AgregarNodo(JTree tree, Object userObject) {
        Utils.AgregarNodo(tree, userObject);
    }

    public void EliminarNodoActivo(JTree tree, List<?> list) {
        Utils.EliminarNodoActivo(tree, list);
    }

    public <T> T ObtenerValorNodo(TreePath path) {
        return Utils.ObtenerValorNodo(path);
    }

    public void AgregarElemento(JList list, Object element) {
        Utils.AgregarElemento(list, element);
    }

    public void EliminarElementoActivo(JList list) {
        Utils.EliminarElementoActivo(list);
    }

    public void AsignarTitulo(JTabbedPane tabbedPane, int tab, String titulo) {
        Utils.AsignarTitulo(tabbedPane, tab, titulo);
    }

    public void ExpandirTodosNodos(JTree tree) {
        Utils.ExpandirTodosNodos(tree);
    }

    ////////////////////////// FABRICA DE CONTROLES ////////////////////////////
    public void VerProcesando(JPanel panel) {
        FabricaControles.VerProcesando(panel);
    }

    public void OcultarProcesando(JPanel panel) {
        FabricaControles.OcultarProcesando(panel);
    }

    public void AgregarCombo(JTable table, int column) {
        FabricaControles.AgregarCombo(table, column);
    }

    public void AgregarBoton(JTable table, Action action, int column) {
        FabricaControles.AgregarBoton(table, action, column);
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

    public void VerModal(JPanel panel) {
        FabricaControles.VerModal(getParent(), panel);
    }
    
    public void VerModal(JInternalFrame panel) {
        //FabricaControles.VerModal(getParent(), panel, action);
    }
    
    public void VerModal(JInternalFrame panel, Action action) {
        //FabricaControles.VerModal(getParent(), panel, action);
    }
    
    public void VerModal(JPanel panel, Action action) {
        FabricaControles.VerModal(getParent(), panel, action);
    }

    public void VerFrame(JPanel panel){
        FabricaControles.VerFrame(getParent(), panel);
    }
    
    public void VerFrame(JPanel panel, Action action){
        FabricaControles.VerFrame(getParent(), panel, action);
    }

    public int VerModal(JPanel panel, Component component, String title) {
        return FabricaControles.VerModal(panel, component, title);
    }

    public int VerConfirmacion(Component parentComponent) {
        return FabricaControles.VerConfirmacion(parentComponent);
    }

    public void VerAdvertencia(String message, Component parentComponent) {
        FabricaControles.VerAdvertencia(message, parentComponent);
    }

    /////////////////////////// FABRICA DE REPORTES ////////////////////////////
    public void ImprimirSinEntidad(int idReporte, String titulo, JPanel panel) {
        FabricaReportes.ImprimirSinEntidad(idReporte, titulo, panel);
    }

    public void ImprimirConEntidad(int idEntidad, int id, JPanel panel) {
        FabricaReportes.ImprimirConEntidad(idEntidad, id, panel);
    }

    ////////////////////////////// EXCEPCIONES /////////////////////////////////
    public void ControlarExcepcion(String[] resultado) {
        Excepciones.Controlar(resultado, this);
    }

    public void ControlarExcepcion(Exception exception) {
        Excepciones.Controlar(exception, this);
    }

    //////////////////////////////// METODOS BASE //////////////////////////////
    public void Cerrar(){
        Window window = (Window)this.getParent().getParent().getParent().getParent();
        window.dispose();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    private ValorDefinido valorDefinido;

    public void setJson(String json) {
        this.valorDefinido.setJson(json);
    }

    public boolean isFromJson() {
        return this.valorDefinido != null;
    }

    public void Init(ValorDefinido valorDefinido) {
        try {
            this.valorDefinido = valorDefinido;
            OcultarControles();
            if (valorDefinido.getJson() == null || valorDefinido.getJson().isEmpty()) {
                setEntidad(nuevaIntancia());
            } else {
                setEntidad((T) new Gson().fromJson(valorDefinido.getJson(), getClazz()));
            }
            AsignarControles();
        } catch (Exception e) {
            ControlarExcepcion(e);
        }
    }

    public void AsignarControles() {
    }

    public void OcultarControles() {
    }
}
