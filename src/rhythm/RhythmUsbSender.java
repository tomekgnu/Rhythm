/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;
import com.sun.corba.se.impl.ior.ByteBuffer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import misc.Splitter;
import misc.UsbWriter;
import model.MidiEvent;
import model.Pattern;
import model.PatternSequence;
/**
 *
 * @author Tomek
 */
public class RhythmUsbSender extends Thread{
    
    private ByteBuffer buffer;
    private PatternSequence sequence;
    private JLabel progLabel;
    private JButton transBut;
    private boolean executing;
    
    public RhythmUsbSender(PatternSequence seq,JLabel lab,JButton but){
        this.buffer = new ByteBuffer();
        this.sequence = seq;
        this.progLabel = lab;
        this.transBut = but;
    }
    
    public void setExecuting(boolean flag){
        this.executing = flag;
    }
    
    private void makeMainHeader(int numBytes,int numPats, int maxResol){
      
        buffer.append(new Integer(numBytes).byteValue());
        buffer.append(new Integer(numBytes >> 8).byteValue());
        buffer.append(new Integer(numBytes >> 16).byteValue());
        buffer.append(new Integer(numBytes >> 24).byteValue());
        
        buffer.append(new Integer(numPats).byteValue());
        buffer.append(new Integer(numPats >> 8).byteValue());
        buffer.append(new Integer(numPats >> 16).byteValue());
        buffer.append(new Integer(numPats >> 24).byteValue()); 
        
        buffer.append(new Integer(maxResol).byteValue());
        buffer.append(new Integer(maxResol >> 8).byteValue());
        buffer.append(new Integer(maxResol >> 16).byteValue());
        buffer.append(new Integer(maxResol >> 24).byteValue());
        buffer.trimToSize();
    
    }
        
    
    private void makePattern(Pattern p){
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
    }
    
    @Override
    public void run(){
        List<Pattern> patternList = sequence.getPatternList();
        int byts = sequence.getNumOfBytes() + 12;
        int pats = sequence.getNumOfPats();
        int maxr = sequence.getMaxResolution();
        executing = true;       
        makeMainHeader(byts, pats, maxr);
        for(Pattern pat:patternList){            
            int repeat = pat.getRepeat();
            while(repeat-- > 0)
                makePattern(pat);                
        }
        
        System.out.println(byts + " " + buffer.size());
   
        Splitter sp = new Splitter(buffer.toArray(),64);
        System.out.println("Chunks: " + sp.getBlockCount());
        int chunks = sp.getBlockCount();      
        progLabel.setText(Integer.toString(0) + "/" + chunks);        
       
        if(chunks > 1){
            for(int i = 0; i < chunks - 1; i++){
                byte[] range = sp.getChunk(i);                 
                if(executing == true)
                    UsbWriter.sendBytes(range);   // usb                 
                else{
                    UsbWriter.sendBytes("STOP".getBytes());
                    return;
                }
                try {
                    progLabel.setText(Integer.toString(i + 1) + "/" + chunks);
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RhythmUsbSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
        byte[] last = sp.getLastChunk();
        UsbWriter.sendBytes(last);   // usb  
        progLabel.setText(Integer.toString(chunks) + "/" + chunks);
        executing = false;
        transBut.setText("Send via USB");
    }
    
}
