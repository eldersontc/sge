package com.sge.base.controles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author elderson
 */
public class JSearch extends JPanel {
    
    private int id = 0;

    public JSearch() {
        textfield = new javax.swing.JTextField();
        textfield.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fireSearchEvent();
            }
        });
        textfield.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    JPopupMenu popup = new JPopupMenu();
                    popup.setLayout(new java.awt.BorderLayout());
                    JMenuItem menuItem = new JMenuItem("LIMPIAR", new ImageIcon(getClass().getResource("/com/sge/base/imagenes/clear-16.png")));
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clickMenuItem(e);
                        }
                    });
                    popup.add(menuItem);
                    popup.show(textfield, evt.getPoint().x, evt.getPoint().y);
                }
            }
        });

        button = new javax.swing.JButton();
        button.setIcon(new ImageIcon(getClass().getResource("/com/sge/base/imagenes/search-16.png"))); // NOI18N
        button.setBorder(null);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fireSearchEvent();
            }
        });
        
        this.setBackground(java.awt.Color.white);
        javax.swing.GroupLayout JSearchLayout = new javax.swing.GroupLayout(this);
        this.setLayout(JSearchLayout);
        JSearchLayout.setHorizontalGroup(
            JSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JSearchLayout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(JSearchLayout.createSequentialGroup()
                .addComponent(textfield)
                .addGap(26, 26, 26))
        );
        JSearchLayout.setVerticalGroup(
            JSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JSearchLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }

    @Override
    public void setEnabled(boolean enabled) {
        textfield.setEnabled(enabled);
        button.setEnabled(enabled);
    }
    
    public String getText() {
        return textfield.getText();
    }

    public void setText(String text) {
        textfield.setText(text);
    }

    public void asingValues(int id, String text) {
        this.id = id;
        textfield.setText(text);
    }

    public void clearValues() {
        this.id = 0;
        textfield.setText("");
    }

    public int getId() {
        return id;
    }

    private void clickMenuItem(ActionEvent e) {
        JMenuItem menuItem = (JMenuItem) e.getSource();
        switch (menuItem.getText()) {
            case "LIMPIAR":
                clearValues();
                fireClearEvent();
                break;
        }
    }

    public void addSearchListener(SearchListener listener) {
        listenerList.add(SearchListener.class, listener);
    }

    public void removeEventListener(SearchListener listener) {
        listenerList.remove(SearchListener.class, listener);
    }

    void fireSearchEvent() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == SearchListener.class) {
                ((SearchListener) listeners[i + 1]).Search();
            }
        }
    }

    void fireClearEvent() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == SearchListener.class) {
                ((SearchListener) listeners[i + 1]).Clear();
            }
        }
    }
    
    // Variables declaration
    private JTextField textfield;
    private JButton button;
}
