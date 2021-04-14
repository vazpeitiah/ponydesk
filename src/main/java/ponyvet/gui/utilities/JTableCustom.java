package ponyvet.gui.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class JTableCustom extends JTable {

    public JTableCustom() {

        super();
        
        this.setAutoCreateRowSorter(true);
//        this.setOpaque(true);
//        JTableHeader defaultHeader = this.getTableHeader();
//        defaultHeader.setBackground(new Color(0, 102, 153));
//        defaultHeader.setForeground(Color.white);
//        defaultHeader.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        
        this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component componenete = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.       
                //this.setHorizontalAlignment();
                this.setBorder(null);
                this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(58, 159, 171)));
                componenete.setFont(new Font("Tahoma", Font.PLAIN, 16));
                if (row % 2 == 0) {
                    componenete.setForeground(new Color(0, 0, 0));
                    componenete.setBackground(new Color(255, 255, 255));
                } else {
                    componenete.setForeground(new Color(0, 0, 0));
                    componenete.setBackground(new Color(255, 255, 255));
                }
                if (isSelected) {
                    componenete.setForeground(Color.white);
                    componenete.setBackground(new Color(51, 51, 51));
                }

                return componenete;

            }
        }
        );
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setRowHeight(25);
        setDefaultEditor(Object.class, null);
       

    }

}
