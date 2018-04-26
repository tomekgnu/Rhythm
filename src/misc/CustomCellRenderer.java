/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import rhythm.MainFrame;

/**
 *
 * @author Tomek
 */


public class CustomCellRenderer
       extends DefaultTableCellRenderer {
  @Override
  public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column) {
    
    Component c = super.getTableCellRendererComponent(table,value,isSelected, hasFocus,row, column);
    
    if(table.getName().equals("patternTable")){
        if(MainFrame.currentPatternData[row][column] != null){
            switch(MainFrame.currentPatternData[row][column]){
                case ACOUSTIC_BASS_DRUM:  c.setBackground(Color.red); break;
                case SIDE_STICK:          c.setBackground(Color.yellow); break;
                case ACOUSTIC_SNARE:      c.setBackground(Color.blue); break;
                case COWBELL:             c.setBackground(Color.green); break;
                case LOW_FLOOR_TOM:       c.setBackground(Color.black); break;
                case HIGH_FLOOR_TOM:      c.setBackground(Color.white); break;
                case LOW_MID_TOM:         c.setBackground(Color.darkGray); break;
                case HI_MID_TOM:          c.setBackground(Color.orange); break;
                case HIGH_TOM:            c.setBackground(Color.cyan); break;
                case CLOSED_HI_HAT:       c.setBackground(Color.pink); break;
                case OPEN_HI_HAT:         c.setBackground(Color.gray); break;
                case PEDAL_HI_HAT:        c.setBackground(Color.magenta); break;
                case CRASH_CYMBAL_1:      c.setBackground(Color.decode("113355")); break;
                case RIDE_CYMBAL_2:       c.setBackground(Color.decode("553355")); break;
                case SPLASH_CYMBAL:       c.setBackground(Color.decode("116655")); break;
                case CHINESE_CYMBAL:      c.setBackground(Color.decode("003355")); break;
                case BASS_GUITAR:         c.setBackground(Color.decode("002211"));
            }                
        }
        else c.setBackground(table.getBackground());
    }
    
        else if(table.getName().equals("sequenceTable")){
            
        }
    
            
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