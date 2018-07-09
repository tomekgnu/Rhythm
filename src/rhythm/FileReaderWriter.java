/*
 * To change this license patternHeader, choose License Headers in Project Properties.
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.UsbWriter;
import model.MidiEvent;
import model.MidiInstrument;

/**
 *
 * @author Tomek
 */
public class FileReaderWriter {
    
    private File rhythmFile;
    private FileInputStream fis;
    private FileOutputStream fos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public FileReaderWriter(File fil){
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
    
   
    public void writtePatterns(List<Pattern> patList,PatternSequence seq){        
        try {
            String SDFile = rhythmFile.getAbsolutePath().replace(".bin", ".rth");
            fos = new FileOutputStream(SDFile);
            oos = new ObjectOutputStream(new FileOutputStream(rhythmFile));
            oos.writeObject(patList);
            oos.writeObject(seq);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // this part saves to .rth file to be used by stm32 looper
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
            Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private int getUnsignedInt(int one,int two,int three,int four){
       one &= 0x000000ff; 
       two &= 0x000000ff;
       three &= 0x000000ff;
       four &= 0x000000ff;
       
       return one | (two << 8) | (three << 16) | (four << 24);
    }
    
    
       
    Object[] readPatterns() { 
        List<Pattern> lst = new ArrayList<Pattern>();
        PatternSequence seq = new PatternSequence();
        try {
            
            ois = new ObjectInputStream(new FileInputStream(rhythmFile));
            lst = (List<Pattern>)ois.readObject();
            seq = (PatternSequence) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          
        return new Object[]{lst,seq};
       
    }    

    
    
}