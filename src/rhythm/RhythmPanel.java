/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createCompoundBorder;
import javax.swing.JLabel;

/**
 *
 * @author Tomek
 */
public class RhythmPanel extends javax.swing.JPanel {
    final int rows = 5;
    int beats;
    int resolution;
    int cells;
    int cols;
    /**
     * Creates new form PatternForm
     */
    public RhythmPanel() {       
        
    }
    
    public void makeLabels(int bts,int res){
        beats = bts;
        resolution = res;
        cols = beats * resolution;
        cells = cols * rows; 
        
        
        for (int row = 0; row < rows; row++) {
            for (int j = row, col = 0; j < cells && col < cols; j = j + 5, col++) {
                JLabel label = new JLabel("");
                label.setSize(30, 60);
                label.addMouseListener(new RhythmPanelMouseListener());
                label.setName(Integer.toString(j));
                makeBorder(label, row, col);
                this.add(label);
            }
        }
        
    }
    
    private void makeBorder(JLabel label, int row, int col) {
        final int borderWidth = 1;
       
        if (row == 0) {
            
            if (col == 0) {
                // Top left corner, draw all sides
                if((col + 1) % resolution == 0)
                    label.setBorder(createCompoundBorder(
                        javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, Color.RED), 
                        javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK)
                    ));
                else
                    label.setBorder(createCompoundBorder(
                        javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, Color.RED), 
                        javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK)
                    ));
            } else {
                // Top edge, draw all sides except left edge
                if(( col + 1 ) % resolution == 0)
                    label.setBorder(createCompoundBorder(
                    javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK), 
                    javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, Color.RED)
                ));
                else
                label.setBorder(BorderFactory.createMatteBorder(borderWidth,
                        0,
                        borderWidth,
                        borderWidth,
                        Color.BLACK));
            }
        } else {
            if (col == 0) {
                // Left-hand edge, draw all sides except top
                if((col + 1) % resolution == 0)
                    label.setBorder(createCompoundBorder(
                        javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, Color.RED), 
                        javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)
                    ));
                else
                label.setBorder(createCompoundBorder(
                    javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, Color.RED), 
                    javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK)
                ));

            } else {
                // Neither top edge nor left edge, skip both top and left lines
                if(( col + 1 ) % resolution == 0)
                    label.setBorder(createCompoundBorder(
                    javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK), 
                    javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, Color.RED)
                ));
                    
                else
                label.setBorder(BorderFactory.createMatteBorder(0,
                        0,
                        borderWidth,
                        borderWidth,
                        Color.BLACK));
            }
        }        
        
    }

    class RhythmPanelMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            System.out.println(me.getComponent().getName());
        }

        @Override
        public void mousePressed(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent me) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
