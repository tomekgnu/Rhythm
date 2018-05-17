/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import com.sun.corba.se.impl.ior.ByteBuffer;
import model.Pattern;
import model.PatternSequence;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MidiEvent;
import model.MidiInstrument;

/**
 *
 * @author Tomek
 */
public class RhythmFileWriter {
    
    private File rhythmFile;
    private FileInputStream fis;
    private FileOutputStream fos;
    
    public RhythmFileWriter(File fil){
        rhythmFile = fil;
    }
    
    public void setFile(File fil){
        rhythmFile = fil;
    }
    
    private void writePattern(Pattern p){
        try {
		ByteBuffer buffer = new ByteBuffer();
                int beats = p.getBeats();
                int division = p.getDivision();
                int beatTime = p.getBeatTime();
                buffer.append(new Integer(beats).byteValue());
                buffer.append(new Integer(beats >> 8).byteValue());
                buffer.append(new Integer(beats >> 16).byteValue());
                buffer.append(new Integer(beats >> 24).byteValue());
                buffer.append(new Integer(division).byteValue());
                buffer.append(new Integer(division >> 8).byteValue());
                buffer.append(new Integer(division >> 16).byteValue());
                buffer.append(new Integer(division >> 24).byteValue());
                buffer.append(new Integer(beatTime).byteValue());
                buffer.append(new Integer(beatTime >> 8).byteValue());
                buffer.append(new Integer(beatTime >> 16).byteValue());
                buffer.append(new Integer(beatTime >> 24).byteValue());                
                
                for(Object e:p.getEventList()){
                    int midiValue = ((MidiEvent)e).getMidiValue();
                    buffer.append(new Integer(midiValue).byteValue());
                }
                
                buffer.trimToSize();
                fos.write(buffer.toArray());					
                fos.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void writeSequence(PatternSequence seq){
        try {
            fos = new FileOutputStream(rhythmFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RhythmFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Pattern> patternList = seq.getPatternList();
        for(Pattern pat:patternList){
            writePattern(pat);
        }
        
        try {
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(RhythmFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PatternSequence readSequence(String filename){
        
        return new PatternSequence();
    }

    private int getUnsignedInt(int one,int two,int three,int four){
       one &= 0x000000ff; 
       two &= 0x000000ff;
       three &= 0x000000ff;
       four &= 0x000000ff;
       
       return one | (two << 8) | (three << 16) | (four << 24);
    }
    
    void readSequence(PatternSequence seq) {
        
        
        if(rhythmFile != null){
            try {
                byte[] header = new byte[12];
                fis = new FileInputStream(rhythmFile);
                int patternIndex = 0;
                int offset = 0;
                int count = 0;
                int len = 0;
                int beats = 0;
                int division = 0;
                int beatTime = 0;
                while(fis.available() > 0){
                    Pattern p = new Pattern();
                    fis.read(header,0,12);
                    beats = getUnsignedInt(header[0],header[1],header[2],header[3]);
                    division = getUnsignedInt(header[4],header[5],header[6],header[7]);
                    beatTime = getUnsignedInt(header[8],header[9],header[10],header[11]);
                    p.setBeats(beats);
                    p.setDivision(division);
                    p.setBeatTime(beatTime);                   
                    offset += 12;
                    count = 5 * beats * division;
                    header = new byte[count];
                    fis.read(header,0,count);
                    for(int i = 0; i < count; i++){
                        MidiEvent e = new MidiEvent();
                        MidiInstrument ins = MidiInstrument.getInstrumentByMidiValue(header[i], i % 5);
                        System.out.println(ins);
                        e.setInstrument(ins);
                        e.setMidiValue((int)header[i]);                        
                        p.addEvent(i, e);
                    }
                    seq.addPattern(patternIndex++,p);
                    offset += count;
                }
                fis.close();
            } 
            catch (IOException ex) {
                Logger.getLogger(RhythmFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    }
}