package com.sge.base.utils;

import com.sge.base.excepciones.Excepciones;
import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author elderson
 */
public class Utils {

    public static boolean FilaActiva(JTable tabla) {
        return (!(tabla.getSelectedRow() == -1));
    }

    public static <T> T ObtenerValorCelda(JTable tabla, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        return (T) modelo.getValueAt(tabla.getSelectedRow(), columna);
    }

    public static <T> T ObtenerValorCelda(JTable tabla, int fila, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        return (T) modelo.getValueAt(fila, columna);
    }

    public static void AsignarValorCelda(JTable tabla, Object valor, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setValueAt(valor, tabla.getSelectedRow(), columna);
    }

    public static void AsignarValorCelda(JTable tabla, Object valor, int fila, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setValueAt(valor, fila, columna);
    }

    public static void AgregarFila(JTable tabla, Object[] fila) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.addRow(fila);
    }

    public static void EliminarTodasFilas(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
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

    public static void OcultarColumnas(JTable tabla, int[] columnas) {
        for (int columna : columnas) {
            tabla.getColumnModel().getColumn(columna).setMinWidth(0);
            tabla.getColumnModel().getColumn(columna).setMaxWidth(0);
            tabla.getColumnModel().getColumn(columna).setWidth(0);
        }
    }

    public static void OcultarControl(Component componet) {
        componet.setVisible(false);
    }

    public static void Cerrar(JInternalFrame frame) {
        try {
            frame.setClosed(true);
        } catch (Exception e) {
            Excepciones.Controlar(e, frame);
        }
    }

    public static void AgregarOrdenamiento(JTable tabla) {
        tabla.setRowSorter(new TableRowSorter<TableModel>(tabla.getModel()));
    }

    public static void Filtrar(JTable tabla, String filtro) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) tabla.getRowSorter();
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }
}
