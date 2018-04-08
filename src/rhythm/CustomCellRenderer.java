/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Tomek
 */


public class CustomCellRenderer
       extends DefaultTableCellRenderer {
  @Override
  public Component getTableCellRendererComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 boolean hasFocus,
                                                 int row,
                                                 int column) {
    
    Component c = super.getTableCellRendererComponent(table, value,
                                          isSelected, hasFocus,
                                          row, column);

    
    if(table.getValueAt(row, column)!= null){
        if(table.getName().equals("patternTable"))
            c.setBackground(Color.red);
        else if(table.getName().equals("sequenceTable"))
            c.setBackground(Color.green);
        
    }
    else
       c.setBackground(table.getBackground());
        
//    else {
//            String val = (String)table.getValueAt(row, column);
//            if(!"@".equals(val)){
//              c.setBackground(table.getBackground());
//              table.setValueAt("", row, column);
//          }
//    }
    
    return c;
  }
}