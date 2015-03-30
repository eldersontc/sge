package com.sge.base.controles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author elderson
 */
public class FabricaControles {

    ///////////////////////////////// CLASES ///////////////////////////////////
    public static class ButtonColumn extends AbstractCellEditor
            implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener {

        private JTable table;
        private Action action;
        private int mnemonic;
        private Border originalBorder;
        private Border focusBorder;

        private JButton renderButton;
        private JButton editButton;
        private Object editorValue;
        private boolean isButtonColumnEditor;

        /**
         * Create the ButtonColumn to be used as a renderer and editor. The
         * renderer and editor will automatically be installed on the
         * TableColumn of the specified column.
         *
         * @param table the table containing the button renderer/editor
         * @param action the Action to be invoked when the button is invoked
         * @param column the column to which the button renderer/editor is added
         */
        public ButtonColumn(JTable table, Action action, int column) {
            this.table = table;
            this.action = action;

            renderButton = new JButton();
            editButton = new JButton();
            editButton.setFocusPainted(false);
            editButton.addActionListener(this);
            originalBorder = editButton.getBorder();
            setFocusBorder(new LineBorder(Color.BLUE));

            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer(this);
            columnModel.getColumn(column).setCellEditor(this);
            table.addMouseListener(this);
        }

        /**
         * Get foreground color of the button when the cell has focus
         *
         * @return the foreground color
         */
        public Border getFocusBorder() {
            return focusBorder;
        }

        /**
         * The foreground color of the button when the cell has focus
         *
         * @param focusBorder the foreground color
         */
        public void setFocusBorder(Border focusBorder) {
            this.focusBorder = focusBorder;
            editButton.setBorder(focusBorder);
        }

        public int getMnemonic() {
            return mnemonic;
        }

        /**
         * The mnemonic to activate the button when the cell has focus
         *
         * @param mnemonic the mnemonic
         */
        public void setMnemonic(int mnemonic) {
            this.mnemonic = mnemonic;
            renderButton.setMnemonic(mnemonic);
            editButton.setMnemonic(mnemonic);
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column) {
            if (value == null) {
                editButton.setText("");
                editButton.setIcon(null);
            } else if (value instanceof Icon) {
                editButton.setText("");
                editButton.setIcon((Icon) value);
            } else {
                editButton.setText(value.toString());
                editButton.setIcon(null);
            }

            this.editorValue = value;
            return editButton;
        }

        @Override
        public Object getCellEditorValue() {
            return editorValue;
        }

//
//  Implement TableCellRenderer interface
//
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                renderButton.setForeground(table.getSelectionForeground());
                renderButton.setBackground(table.getSelectionBackground());
            } else {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }

            if (hasFocus) {
                renderButton.setBorder(focusBorder);
            } else {
                renderButton.setBorder(originalBorder);
            }

//		renderButton.setText( (value == null) ? "" : value.toString() );
            if (value == null) {
                renderButton.setText("");
                renderButton.setIcon(null);
            } else if (value instanceof Icon) {
                renderButton.setText("");
                renderButton.setIcon((Icon) value);
            } else {
                renderButton.setText(value.toString());
                renderButton.setIcon(null);
            }

            return renderButton;
        }

//
//  Implement ActionListener interface
//
	/*
         *	The button has been pressed. Stop editing and invoke the custom Action
         */
        public void actionPerformed(ActionEvent e) {
            int row = table.convertRowIndexToModel(table.getEditingRow());
            fireEditingStopped();

            //  Invoke the Action
            ActionEvent event = new ActionEvent(
                    table,
                    ActionEvent.ACTION_PERFORMED,
                    "" + row);
            action.actionPerformed(event);
        }

//
//  Implement MouseListener interface
//
	/*
         *  When the mouse is pressed the editor is invoked. If you then then drag
         *  the mouse to another cell before releasing it, the editor is still
         *  active. Make sure editing is stopped when the mouse is released.
         */
        public void mousePressed(MouseEvent e) {
            if (table.isEditing()
                    && table.getCellEditor() == this) {
                isButtonColumnEditor = true;
            }
        }

        public void mouseReleased(MouseEvent e) {
            if (isButtonColumnEditor
                    && table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }

            isButtonColumnEditor = false;
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    //////////////////////////////// METODOS ///////////////////////////////////
    public static void AgregarBoton(JTable table, Action action, int column) {
        ButtonColumn btn = new ButtonColumn(table, action, column);
        btn.setMnemonic(KeyEvent.VK_ENTER);
    }

    public static void VerCargando(JPanel panel) {
        for (Component component : panel.getParent().getComponents()) {
            if ("panelCargando".equals(component.getName())) {
                return;
            }
        }
        JInternalFrame frame = (JInternalFrame) panel.getParent().getParent().getParent().getParent();
        JPanel panelCargando = new JPanel();
        panelCargando.setName("panelCargando");
        panelCargando.setBackground(Color.white);
        panelCargando.setLayout(new BorderLayout());
        ImageIcon iconoCargando = new ImageIcon(frame.getClass().getResource("/com/sge/base/imagenes/preload-24.gif"));
        JLabel labelCargando = new JLabel();
        labelCargando.setText("Cargando, espere un momento por favor...");
        labelCargando.setIcon(iconoCargando);
        labelCargando.setHorizontalAlignment(SwingConstants.CENTER);
        panelCargando.add(labelCargando, BorderLayout.CENTER);
        frame.setLayout(new BorderLayout());
        frame.add(panelCargando, 0);
        panel.setVisible(false);
    }

    public static void OcultarCargando(JPanel panel) {
        Component panelCargando = null;
        for (Component component : panel.getParent().getComponents()) {
            if ("panelCargando".equals(component.getName())) {
                panelCargando = component;
                break;
            }
        }
        if(!(panelCargando == null)){
            panel.getParent().remove(panelCargando);
        }
        panel.setVisible(true);
    }
    
    public static void VerProcesando(JPanel panel) {
        for (Component component : panel.getParent().getComponents()) {
            if ("panelProcesando".equals(component.getName())) {
                return;
            }
        }
        JInternalFrame frame = (JInternalFrame) panel.getParent().getParent().getParent().getParent();
        JPanel panelProcesando = new JPanel();
        panelProcesando.setName("panelProcesando");
        panelProcesando.setBackground(Color.white);
        panelProcesando.setLayout(new BorderLayout());
        ImageIcon iconoProcesando = new ImageIcon(frame.getClass().getResource("/com/sge/base/imagenes/process-32.gif"));
        JLabel labelProcesando = new JLabel();
        labelProcesando.setText("Procesando...");
        labelProcesando.setIcon(iconoProcesando);
        labelProcesando.setHorizontalAlignment(SwingConstants.CENTER);
        panelProcesando.add(labelProcesando, BorderLayout.CENTER);
        frame.setLayout(new BorderLayout());
        frame.add(panelProcesando, 0);
        panel.setVisible(false);
    }
    
    public static void OcultarProcesando(JPanel panel) {
        Component panelProcesando = null;
        for (Component component : panel.getParent().getComponents()) {
            if ("panelProcesando".equals(component.getName())) {
                panelProcesando = component;
                break;
            }
        }
        if(!(panelProcesando == null)){
            panel.getParent().remove(panelProcesando);
        }
        panel.setVisible(true);
    }
}
