package com.sge.base.formularios;

import com.sge.base.controles.FabricaControles;
import com.sge.base.excepciones.Excepciones;
import com.sge.base.utils.Utils;
import java.awt.Component;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author elderson
 */
public class frameBase extends javax.swing.JInternalFrame {

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

    public void Init(int id){
    }
    
    public void AsignarControles(){
    }
    
    public void OcultarControles() {
    }

    public void Aceptar() {
    }

    public void Cancelar() {
    }
}
