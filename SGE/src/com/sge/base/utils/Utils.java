package com.sge.base.utils;

import com.sge.base.excepciones.Excepciones;
import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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

    public static void MostrarControl(Component componet) {
        componet.setVisible(true);
    }

    ////////////////////////////// JINTERNALFRAME //////////////////////////////

    public static void Cerrar(JInternalFrame frame) {
        try {
            frame.setClosed(true);
        } catch (Exception e) {
            Excepciones.Controlar(e, frame);
        }
    }

    ////////////////////////////////// JTREE ///////////////////////////////////
    public static void AgregarNodo(JTree tree, Object userObject) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        model.insertNodeInto(new DefaultMutableTreeNode(userObject), root, root.getChildCount());
    }

    public static void EliminarNodoActivo(JTree tree, List<?> list) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        TreePath path = tree.getSelectionPath();
        if (path != null) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
            if (nodo.getParent() != null) {
                model.removeNodeFromParent(nodo);
                list.remove(nodo.getUserObject());
            }
        }
    }

    public static <T> T ObtenerValorNodo(TreePath path) {
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
        return (T) nodo.getUserObject();
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
}
