package com.sge.base.utils;

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

    public static void AgregarFila(JTable tabla, Object[] fila) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.addRow(fila);
    }

    public static void EliminarFila(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.removeRow(tabla.getSelectedRow());
    }
}
