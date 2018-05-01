/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import java.util.logging.Level;
import java.util.logging.Logger;
import misc.MidiInstrument;
import misc.UsbWriter;

/**
 *
 * @author Tomek
 */
public class Playback extends Thread {
    
    private boolean execute = false;
    private boolean usbResult;    
    private int beatTime = 60;
    private int resolution;
    private int patternTime;
    private int beats;
    private int subBeats;
    private int subBeatTime;
    private int subBeatIndex;
    
    @Override
    public void run(){ 
        execute = true;
        subBeatIndex = 0;
        MidiEvent event;
        byte[] sendEvents = new byte[5];
        while(execute){
            //System.out.println("thread is running...");
            try {
                
                
                for(int i = 0; i < 5; i++){
                    if((event = (MidiEvent)MainFrame.currentPatternData[i][subBeatIndex]) != null)
                        sendEvents[i] = event.getMidiValue();
                    else
                        sendEvents[i] = (byte)0;
                }
                usbResult = UsbWriter.sendBytes(sendEvents);
                subBeatIndex++;
                if(subBeatIndex == subBeats)
                    subBeatIndex = 0;
                Thread.sleep(subBeatTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Playback.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // time,resolution,beats
    public void setTime(int t,int r,int b){
       this.beatTime = 60000 / t; 
       this.resolution = r;
       this.beats = b;
       this.subBeats = this.resolution * this.beats;
       this.patternTime = this.beatTime * this.beats;
       this.subBeatTime = this.beatTime / this.resolution;
    }
    
    
    public void stopExecuting() {
        this.execute = false;
    }
}
