/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Tomek
 */
public class ComboBoxRenderer implements ListCellRenderer {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    
    public ComboBoxRenderer() {       
        
    }

    /*
     * This method finds the image and text corresponding
     * to the selected value and returns the label, set up
     * to display the text and image.
     */
    @Override
    public Component getListCellRendererComponent(
                                       JList list,
                                       Object value,
                                       int index,
                                       boolean isSelected,
                                       boolean cellHasFocus) {
        //Get the selected index. (The index param isn't
        //always valid, so just use the value.)
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
        
        renderer.setText(value.toString()); 
        renderer.setBackground(MidiInstrument.getInstrumentByIndex(index).getBackground());
        renderer.setForeground(MidiInstrument.getInstrumentByIndex(index).getForeground());
       
        //Set the icon and text.  If icon was null, say so.
//        ImageIcon icon = images[selectedIndex];
//        String pet = petStrings[selectedIndex];
//        setIcon(icon);
//        if (icon != null) {
//            setText(pet);
//            setFont(list.getFont());
//        } else {
//            setUhOhText(pet + " (no image available)",
//                        list.getFont());
//        }

        return renderer;
    }
    
        
}