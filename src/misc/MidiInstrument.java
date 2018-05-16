/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import java.awt.Color;

/**
 *
 * @author Tomek
 */

public enum MidiInstrument {
   
    ACOUSTIC_BASS_DRUM("Acoustic base drum",0,35,Color.red,Color.white),
    SIDE_STICK("Side stick",1,37,Color.yellow,Color.black),
    ACOUSTIC_SNARE("Acoustic snare",2,38,Color.blue,Color.white),
    COWBELL("Cowbell",3,56,Color.green,Color.white),
    LOW_FLOOR_TOM("Low floor tom",4,41,Color.black,Color.white),
    HIGH_FLOOR_TOM("High floor tom",5,43,Color.white,Color.black),
    LOW_MID_TOM("Low mid tom",6,47,Color.darkGray,Color.white),
    HI_MID_TOM("Hi mid tom",7,48,Color.orange,Color.white),
    HIGH_TOM("High tom",8,50,Color.cyan,Color.darkGray),
    CLOSED_HI_HAT("Closed hi-hat",9,42,Color.pink,Color.white),
    OPEN_HI_HAT("Open hi-hat",10,46,Color.gray,Color.white),
    PEDAL_HI_HAT("Pedal hi-hat",11,44,Color.magenta,Color.white),
    CRASH_CYMBAL_1("Crash cymbal 1",12,49,Color.decode("113355"),Color.white),
    RIDE_CYMBAL_2("Ride cymbal 2",13,59,Color.decode("553355"),Color.white),
    SPLASH_CYMBAL("Splash cymbal",14,55,Color.decode("116655"),Color.white),
    CHINESE_CYMBAL("Chinese cymbal",15,52,Color.decode("003355"),Color.white),
    BASS_GUITAR("Bass guitar",16,0,Color.decode("002211"),Color.white),
    NONE("No instrument",17,0,Color.WHITE,Color.white);
    
   
    private final String name;
    private final int numValue;
    private final int midiValue;
    private final Color background;
    private final Color foreground;
        
    MidiInstrument(String name,int value,int mValue,Color bg,Color fg){
        this.name = name;
        this.numValue = value;
        this.midiValue = mValue;
        this.background = bg;
        this.foreground = fg;
    }
    
       
    public String getName() {
        return this.name;
    }  
   public int getValue() {
        return numValue;
    }
   
 
   public static String[] displayValues(){  
        return new String[]{
            ACOUSTIC_BASS_DRUM.getName(),
            SIDE_STICK.getName(),
            ACOUSTIC_SNARE.getName(),
            COWBELL.getName(),
            LOW_FLOOR_TOM.getName(),
            HIGH_FLOOR_TOM.getName(),
            LOW_MID_TOM.getName(),
            HI_MID_TOM.getName(),
            HIGH_TOM.getName(),
            CLOSED_HI_HAT.getName(),
            OPEN_HI_HAT.getName(),
            PEDAL_HI_HAT.getName(),
            CRASH_CYMBAL_1.getName(),
            RIDE_CYMBAL_2.getName(),
            SPLASH_CYMBAL.getName(),
            CHINESE_CYMBAL.getName(),
            BASS_GUITAR.getName()
        };
    }
  
     public static MidiInstrument getInstrumentByIndex(int index) {       
        for(MidiInstrument d:MidiInstrument.values()){
            if(d.getValue() == index)
                return d;
        }        
        
        return BASS_GUITAR;   
    }

    public static MidiInstrument getInstrumentByMidiValue(int mv,int part) {  
        if(part == 4)
            return BASS_GUITAR; 
        for(MidiInstrument d:MidiInstrument.values()){
            if(d.getMidiValue() == mv)
                return d;
        }              
        return MidiInstrument.NONE;
        
    }
    
    public int getMidiValue() {
        return this.midiValue;
    }

    public Color getBackground() {
        return this.background;
    }
    
    public Color getForeground() {
        return this.foreground;
    }    
}




