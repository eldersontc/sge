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
        int fila = tabla.getSelectedRow();
        return (T) modelo.getValueAt(fila, columna);
    }

    public static void AgregarFila(JTable tabla, Object[] fila) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.addRow(fila);
    }
}
