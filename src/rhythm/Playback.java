/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import model.Pattern;
import model.PatternSequence;
import model.MidiEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MidiInstrument;
import misc.UsbWriter;

/**
 *
 * @author Tomek
 */
public class Playback extends Thread {
    
    private boolean execute = false;
    private boolean usbResult;    
    private int beatTime = 60;
    private int division;
    private int beats;
    private int subBeats;
    private int subBeatTime;
    private int playback;
    public static final int PATTERN = 0;
    public static final int SEQUENCE = 1;
    public static final int SONG = 2;
    
    public Playback(int p){
        playback = p;
    }
    
    @Override
    public void run(){ 
        execute = true;        
        
        Pattern pat = MainFrame.currentPattern;
        PatternSequence seq = MainFrame.currentSequence;
        int patIndex = 0;
        if(playback == Playback.PATTERN && pat.hasEvents()){
            while(execute){                
                playEvents(pat);
            }            
        }
        else if(playback == Playback.SEQUENCE){
            while(seq.hasPatterns() && execute){
                pat = seq.getPatternAt(patIndex);
                setTime(pat.getBeatTime(),pat.getDivision(),pat.getBeats());
                playEvents(pat);
                patIndex++; 
                if(patIndex == seq.getPatternList().size())
                    patIndex = 0;
            }
            
        }
       
            //System.out.println("thread is running...");
            
        
    }
    
    private void playEvents(Pattern p){
        try {                
                MidiEvent event;
                byte[] sendEvents = new byte[5];
                int eventList = p.getEventList().size();
                int nextEvent = 0;
                int i = 0;
                while(nextEvent < eventList){
                    event = (MidiEvent)p.getEventAt(nextEvent);
                    if(event.getMidiInstrument() != MidiInstrument.NONE)
                        sendEvents[i] = (byte)event.getMidiValue();
                    else
                        sendEvents[i] = (byte)0;
                    nextEvent++;
                    i++;
                    if(nextEvent % 5 == 0){
                        i = 0;
                        usbResult = UsbWriter.sendBytes(sendEvents);
                        Thread.sleep(subBeatTime);
                        if(execute == false)
                            break;
                    }               
                
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Playback.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    // time,resolution,beats
    public void setTime(int t,int r,int b){
       this.beatTime = 60000 / t; 
       this.division = r;
       this.beats = b;
       this.subBeatTime = this.beatTime / this.division;
    }
    
    public boolean isExecuting(){
        return this.execute;
    }
    
    public void stopExecuting() {
        this.execute = false;
    }
}
