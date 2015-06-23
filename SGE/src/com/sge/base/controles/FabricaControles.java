package com.sge.base.controles;

import com.sge.base.formularios.frameBase;
import com.sge.base.formularios.framePreload;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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

    public static class ComboCell extends DefaultCellEditor {

        public ComboCell() {
            super(new JComboBox());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JComboBox combo = (JComboBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
            combo.removeAllItems();
            List<?> items = (List<?>) table.getModel().getValueAt(row, column - 1); // getItemsForCell(row, column); // you'll need to implement this method yourself
            for (Object item : items) {
                combo.addItem(item);
            }
            combo.setSelectedItem(table.getModel().getValueAt(row, column));
            return combo;
        }
    }

    //////////////////////////////// METODOS ///////////////////////////////////
    public static void AgregarBoton(JTable table, Action action, int column) {
        ButtonColumn btn = new ButtonColumn(table, action, column);
        btn.setMnemonic(KeyEvent.VK_ENTER);
    }

    public static void AgregarCombo(JTable table, int column) {
        ComboCell comboCell = new ComboCell();
        table.getColumnModel().getColumn(column).setCellEditor(comboCell);
    }

    public static void VerCargando(JPanel panel) {
        framePreload framePreload = (framePreload)panel.getParent().getParent().getParent();
        framePreload.VerCargando();
    }

    public static void OcultarCargando(JPanel panel) {
        framePreload framePreload = (framePreload)panel.getParent().getParent().getParent();
        framePreload.Ocultar();
    }

    public static void VerProcesando(JPanel panel) {
        framePreload framePreload = (framePreload)panel.getParent().getParent().getParent();
        framePreload.VerProcesando();
    }

    public static void OcultarProcesando(JPanel panel) {
        framePreload framePreload = (framePreload)panel.getParent().getParent().getParent();
        framePreload.Ocultar();
    }

    public static void VerModal(Component parent, frameBase framebBase){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(800, 400);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(parent);
        framePreload framePreload = new framePreload();
        framePreload.AgregarFrame(framebBase);
        framebBase.Init();
        dialog.add(framePreload);
        dialog.show();
    }
    
    public static void VerModal(Component parent, frameBase frameBase, Action action){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setSize(800, 400);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(parent);
        framePreload framePreload = new framePreload();
        framePreload.AgregarFrame(frameBase);
        frameBase.Init();
        dialog.add(framePreload);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                action.actionPerformed(new ActionEvent(frameBase, 0, null));
            }
        });
        dialog.show();
    }
    
    public static void VerFrame(Component parent, frameBase frameBase){
        JFrame frame = new JFrame();
        frame.setSize(frameBase.getPreferredSize().width, frameBase.getPreferredSize().height + 30);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        framePreload framePreload = new framePreload();
        framePreload.AgregarFrame(frameBase);
        frameBase.Init();
        frame.add(framePreload);
        frame.show();
    }
    
    public static void VerFrame(Component parent, frameBase frameBase, Action action){
        JFrame frame = new JFrame();
        frame.setSize(frameBase.getPreferredSize().width, frameBase.getPreferredSize().height + 30);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(parent);
        framePreload framePreload = new framePreload();
        framePreload.AgregarFrame(frameBase);
        frameBase.Init();
        frame.add(framePreload);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                action.actionPerformed(new ActionEvent(evt.getSource(), evt.getID(), null));
            }
        });
        frame.show();
    }
    
    public static int VerModal(Component parent, Component component, String title) {
        return JOptionPane.showConfirmDialog(parent, component, title, JOptionPane.OK_CANCEL_OPTION);
    }

    public static int VerConfirmacion(Component parent) {
        return JOptionPane.showConfirmDialog(parent, "¿SEGURO DE CONTINUAR?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
    }

    public static void VerAdvertencia(String message, Component parent) {
        JOptionPane.showMessageDialog(parent, message, "MENSAJE", JOptionPane.WARNING_MESSAGE);
    }

    public static void AgregarEventoChange(JTable tabla, Action action) {
        tabla.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                action.actionPerformed(new ActionEvent(new int[]{e.getFirstRow(), e.getColumn()}, 0, null));
            }
        });
    }
}
