package com.sge.base.utils;

import com.sge.base.excepciones.Excepciones;
import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author elderson
 */
public class Utils {

    ////////////////////////////////// JTABLE //////////////////////////////////
    public static boolean FilaActiva(JTable tabla) {
        return (!(tabla.getSelectedRow() == -1));
    }

    public static <T> T ObtenerValorCelda(JTable tabla, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        return (T) modelo.getValueAt(tabla.convertRowIndexToModel(tabla.getSelectedRow()), columna);
    }

    public static <T> T ObtenerValorCelda(JTable tabla, int fila, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        return (T) modelo.getValueAt(tabla.convertRowIndexToModel(fila), columna);
    }

    public static void AsignarValorCelda(JTable tabla, Object valor, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setValueAt(valor, tabla.convertRowIndexToModel(tabla.getSelectedRow()), columna);
    }

    public static void AsignarValorCelda(JTable tabla, Object valor, int fila, int columna) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setValueAt(valor, tabla.convertRowIndexToModel(fila), columna);
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
        modelo.removeRow(tabla.convertRowIndexToModel(tabla.getSelectedRow()));
    }

    public static void VerColumna(JTable tabla, int columna, int ancho){
        tabla.getColumnModel().getColumn(columna).setMinWidth(ancho);
        tabla.getColumnModel().getColumn(columna).setMaxWidth(ancho);
        tabla.getColumnModel().getColumn(columna).setWidth(ancho);
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

    public static void AgregarOrdenamiento(JTable tabla) {
        tabla.setRowSorter(new TableRowSorter<TableModel>(tabla.getModel()));
    }

    public static void Filtrar(JTable tabla, String filtro) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) tabla.getRowSorter();
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
    }

    //////////////////////////////// COMPONENT /////////////////////////////////
    public static void OcultarControl(Component componet) {
        componet.setVisible(false);
    }

    public static void VerControl(Component componet) {
        componet.setVisible(true);
    }
    
    public static void HabilitarControles(Object[] controles, boolean habilitar){
        for (Object control : controles) {
            ((JComponent)control).setEnabled(habilitar);
        }
    }
    ////////////////////////// JSPINNER ////////////////////////////////////////
    public static void AsignarValor(JSpinner spinner, Object valor){
        spinner.setValue(valor);
    }
    
    ///////////////////////// JCOMBOBOX ////////////////////////////////////////
    public static void AsignarItemActivo(JComboBox comboBox, Object item){
        comboBox.setSelectedItem(item);
    }
    
    ////////////////////////////////// JTREE ///////////////////////////////////
    public static DefaultMutableTreeNode AgregarNodo(JTree tree, Object userObject) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(userObject);
        model.insertNodeInto(nodo, root, root.getChildCount());
        return nodo;
    }

    public static void AgregarNodo(JTree tree, TreePath path, Object userObject){
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
        model.insertNodeInto(new DefaultMutableTreeNode(userObject), nodo, nodo.getChildCount());
    }
    
    public static void AgregarNodo(JTree tree, DefaultMutableTreeNode nodoPadre, Object userObject){
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.insertNodeInto(new DefaultMutableTreeNode(userObject), nodoPadre, nodoPadre.getChildCount());
    }
    
    public static void EliminarNodoActivo(JTree tree) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        TreePath path = tree.getSelectionPath();
        if (path != null) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (nodo.getParent() != null) {
                model.removeNodeFromParent(nodo);
            }
        }
    }

    public static <T> T ObtenerValorNodo(TreePath path) {
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
        return (T) nodo.getUserObject();
    }
    
    public static <T> T ObtenerValorNodoPadre(TreePath path) {
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
        return (T) ((DefaultMutableTreeNode)nodo.getParent()).getUserObject();
    }
    
    public static void ExpandirTodosNodos(JTree tree){
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }
    
    ////////////////////////////////// JLIST ///////////////////////////////////
    public static void AgregarElemento(JList list, Object element) {
        DefaultListModel model = (DefaultListModel) list.getModel();
        model.addElement(element);
    }

    public static void EliminarElementoActivo(JList list) {
        DefaultListModel model = (DefaultListModel) list.getModel();
        model.removeElement(list.getSelectedValue());
    }

    ///////////////////////////// JTABBEDPANE //////////////////////////////////
    public static void AsignarTitulo(JTabbedPane tabbedPane, int tab, String titulo) {
        tabbedPane.setTitleAt(tab, titulo);
    }
    
    ////////////////////////// JTEXTFIELD //////////////////////////////////////
    public static void AsignarTexto(JTextComponent textComponent, String texto){
        textComponent.setText(texto);
    }
    
    public static void AsignarVacio(JTextField textField){
        textField.setText("");
    }
    
    ///////////////////////// JCHECKBOX ////////////////////////////////////////
    public static void AsignarSeleccion(JCheckBox checkBox, boolean seleccion){
        checkBox.setSelected(seleccion);
    }
}
