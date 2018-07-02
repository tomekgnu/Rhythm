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
import model.MidiEvent;
import model.MidiInstrument;
import static rhythm.MainFrame.currentPattern;

/**
 *
 * @author Tomek
 */
public class RhythmPanel extends javax.swing.JPanel {
    private final int rows = 5;
    private int beats;
    private int resolution;
    private int cells;
    private int cols;
    private MidiEvent currentEvent;
    private int instrumentIndex;  // index in combobox
    private int currentNoteIndex;        // index in combobox
    private int currentOctaveIndex;      // index in combobox
   
    public void setBeats(int beats) {
        this.beats = beats;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public void setInstrumentIndex(int instrumentIndex) {
        this.instrumentIndex = instrumentIndex;
    }

    public void setCurrentNoteIndex(int currentNoteIndex) {
        this.currentNoteIndex = currentNoteIndex;
    }

    public void setCurrentOctaveIndex(int currentOctaveIndex) {
        this.currentOctaveIndex = currentOctaveIndex;
    }
    
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
            for (int eventIndex = row, col = 0; eventIndex < cells && col < cols; eventIndex = eventIndex + 5, col++) {
                JLabel label = new JLabel("");
                label.setSize(30, 60);
                label.addMouseListener(new RhythmPanelMouseListener());
                label.setName(Integer.toString(eventIndex)); 
                label.setOpaque(true);
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
            JLabel label = (JLabel)me.getComponent();
            Integer eventIndex = Integer.parseInt(label.getName());
            currentEvent = new MidiEvent(instrumentIndex,currentNoteIndex,currentOctaveIndex);   
                        
            switch (eventIndex % rows) {
                // hands
                case 0:
                case 1:                
                    switch(currentEvent.getMidiInstrument()){
                        case ACOUSTIC_BASS_DRUM:
                        case PEDAL_HI_HAT:  
                        case BASS_GUITAR: System.out.println("Wrong part of body");
                        return;
                    }   break;
                 // feet
                case 2:
                case 3:               
                    switch(currentEvent.getMidiInstrument()){
                        case SIDE_STICK:
                        case ACOUSTIC_SNARE:
                        case COWBELL:
                        case LOW_FLOOR_TOM:
                        case HIGH_FLOOR_TOM:
                        case LOW_MID_TOM:
                        case HI_MID_TOM:
                        case HIGH_TOM:
                        case CLOSED_HI_HAT:
                        case OPEN_HI_HAT:
                        case CRASH_CYMBAL_1:
                        case RIDE_CYMBAL_2:
                        case SPLASH_CYMBAL:
                        case CHINESE_CYMBAL:
                        case BASS_GUITAR:   System.out.println("Wrong part of body");
                                            return;
                    }   
                    break;
                case 4: if(currentEvent.getMidiInstrument() != MidiInstrument.BASS_GUITAR){
                            System.out.println("Wrong part of body");
                            return;
                        }
            }
            
            assert(currentPattern.hasEvents());
            MidiInstrument instrument = currentPattern.getEventAt(eventIndex).getMidiInstrument();
            if(instrument == MidiInstrument.NONE){  // empty event ? set one with current instrument
                currentPattern.addEvent(eventIndex, currentEvent);
                label.setBackground(currentEvent.getMidiInstrument().getBackground());
            }
            else{
                currentPattern.addEvent(eventIndex, new MidiEvent());
                label.setBackground(getParent().getBackground());
            }
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
