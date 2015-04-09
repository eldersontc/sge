package com.sge.base.formularios;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author elderson
 */
public class frameBase extends javax.swing.JInternalFrame {
    
    /////////////////////////// METODOS GENERICOS //////////////////////////////
    
    public boolean FilaActiva(JTable tabla) {
        return (!(tabla.getSelectedRow() == -1));
    }

    public <T> T ObtenerValorCelda(JTable tabla, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        return (T) modelo.getValueAt(tabla.getSelectedRow(), columna);
    }

    public <T> T ObtenerValorCelda(JTable tabla, int fila, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        return (T) modelo.getValueAt(fila, columna);
    }

    public void AsignarValorCelda(JTable tabla, Object valor, int fila, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setValueAt(valor, fila, columna);
    }

    public void AgregarFila(JTable tabla, Object[] fila) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.addRow(fila);
    }

    public void EliminarTodasFilas(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
    }

    public void EliminarFila(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.removeRow(tabla.getSelectedRow());
    }

    public void OcultarColumna(JTable tabla, int columna) {
        tabla.getColumnModel().getColumn(columna).setMinWidth(0);
        tabla.getColumnModel().getColumn(columna).setMaxWidth(0);
        tabla.getColumnModel().getColumn(columna).setWidth(0);
    }

    public void OcultarControl(Component componet) {
        componet.setVisible(false);
    }

    public void AgregarOrdenamiento(JTable tabla) {
        tabla.setRowSorter(new TableRowSorter<TableModel>(tabla.getModel()));
    }

    public void Filtrar(JTable tabla, String filtro) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) tabla.getRowSorter();
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }
    
    public void Cerrar(){
        try {
            this.setClosed(true);
        } catch (Exception e) {
        }
    }
    
    public void Aceptar(){
        
    }
    
    public void Cancelar(){
        
    }
}
