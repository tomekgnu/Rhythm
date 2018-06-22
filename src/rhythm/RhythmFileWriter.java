/*
 * To change this license buffer, choose License Headers in Project Properties.
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
import misc.Splitter;
import misc.UsbWriter;
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
    
    private void writeHeader(int numPats,int numBytes, int maxResol){
        try{
        ByteBuffer buffer = new ByteBuffer();
        buffer.append(new Integer(numPats).byteValue());
        buffer.append(new Integer(numPats >> 8).byteValue());
        buffer.append(new Integer(numPats >> 16).byteValue());
        buffer.append(new Integer(numPats >> 24).byteValue());
        
        buffer.append(new Integer(numBytes).byteValue());
        buffer.append(new Integer(numBytes >> 8).byteValue());
        buffer.append(new Integer(numBytes >> 16).byteValue());
        buffer.append(new Integer(numBytes >> 24).byteValue());
        
        buffer.append(new Integer(maxResol).byteValue());
        buffer.append(new Integer(maxResol >> 8).byteValue());
        buffer.append(new Integer(maxResol >> 16).byteValue());
        buffer.append(new Integer(maxResol >> 24).byteValue());
        buffer.trimToSize();
        fos.write(buffer.toArray());        
        fos.flush();
        UsbWriter.sendBytes(buffer.toArray());
        System.out.println(buffer.size());
        }catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
                e.printStackTrace();
	}
    }
    
    private void writePattern(Pattern p){
        try {
		ByteBuffer buffer = new ByteBuffer();
                int beats = p.getBeats();
                int division = p.getDivision();
                int beatTime = p.getBeatTime();
                int id = p.getID();
                
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
                
                buffer.append(new Integer(id).byteValue());
                buffer.append(new Integer(id >> 8).byteValue());
                buffer.append(new Integer(id >> 16).byteValue());
                buffer.append(new Integer(id >> 24).byteValue());
                               
                
                for(Object e:p.getEventList()){
                    int midiValue = ((MidiEvent)e).getMidiValue();
                    buffer.append(new Integer(midiValue).byteValue());
                }
                
                fos.write(buffer.toArray());					
                fos.flush();                
                
                buffer.trimToSize();
                
                
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
        int pats = seq.getNumOfPats();
        int byts = seq.getNumOfBytes();
        int maxr = seq.getMaxResolution();
        writeHeader(pats, byts, maxr);
        for(Pattern pat:patternList){            
            int repeat = pat.getRepeat();
            while(repeat-- > 0)
                writePattern(pat);                
        }
        
        try {
            fos.flush();
            fos.close();
            System.out.println(byts);            
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
                byte[] buffer = new byte[16];
                fis = new FileInputStream(rhythmFile);
                int patternIndex = 0;
                int offset = 0;
                int count = 0;
                int len = 0;
                int beats = 0;
                int division = 0;
                int beatTime = 0;
                int id = 0;
                while(fis.available() > 0){
                    Pattern p = new Pattern();
                    fis.read(buffer,0,16);
                    beats = getUnsignedInt(buffer[0],buffer[1],buffer[2],buffer[3]);
                    division = getUnsignedInt(buffer[4],buffer[5],buffer[6],buffer[7]);
                    beatTime = getUnsignedInt(buffer[8],buffer[9],buffer[10],buffer[11]);
                    id = getUnsignedInt(buffer[12],buffer[13],buffer[14],buffer[15]);
                    p.setBeats(beats);
                    p.setDivision(division);
                    p.setBeatTime(beatTime);
                    p.setID(id);
                    offset += 16;
                    count = 5 * beats * division;
                    buffer = new byte[count];
                    fis.read(buffer,0,count);
                    for(int i = 0; i < count; i++){
                        MidiEvent e = new MidiEvent();
                        MidiInstrument ins = MidiInstrument.getInstrumentByMidiValue(buffer[i], i % 5);
                        e.setInstrument(ins);
                        e.setMidiValue((int)buffer[i]);                        
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