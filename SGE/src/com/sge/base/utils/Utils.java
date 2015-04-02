package com.sge.base.utils;

import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elderson
 */
public class Utils {
    
    public static <T> T ObtenerValorCelda(JTable tabla, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        return (T) modelo.getValueAt(tabla.getSelectedRow(), columna);
    }
    
    public static <T> T ObtenerValorCelda(JTable tabla, int fila, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        return (T) modelo.getValueAt(fila, columna);
    }
    
    public static void AgregarFila(JTable tabla, Object[] fila) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.addRow(fila);
    }
    
    public static void EliminarFila(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.removeRow(tabla.getSelectedRow());
    }
    
    public static void OcultarColumna(JTable tabla, int columna) {
        tabla.getColumnModel().getColumn(columna).setMinWidth(0);
        tabla.getColumnModel().getColumn(columna).setMaxWidth(0);
        tabla.getColumnModel().getColumn(columna).setWidth(0);
    }
    
    public static void OcultarControl(Component componet) {
        componet.setVisible(false);
    }
    
    public static void Cerrar(JInternalFrame frame){
        try {
            frame.setClosed(true);
        } catch (Exception e) {
        }
    }
}
