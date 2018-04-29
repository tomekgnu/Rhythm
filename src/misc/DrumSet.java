/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author Tomek
 */

public enum DrumSet {
   
    ACOUSTIC_BASS_DRUM("Acoustic base drum",0,(byte)35),
    SIDE_STICK("Side stick",1,(byte)37),
    ACOUSTIC_SNARE("Acoustic snare",2,(byte)38),
    COWBELL("Cowbell",3,(byte)56),
    LOW_FLOOR_TOM("Low floor tom",4,(byte)41),
    HIGH_FLOOR_TOM("High floor tom",5,(byte)43),
    LOW_MID_TOM("Low mid tom",6,(byte)47),
    HI_MID_TOM("Hi mid tom",7,(byte)48),
    HIGH_TOM("High tom",8,(byte)50),
    CLOSED_HI_HAT("Closed hi-hat",9,(byte)42),
    OPEN_HI_HAT("Open hi-hat",10,(byte)46),
    PEDAL_HI_HAT("Pedal hi-hat",11,(byte)44),
    CRASH_CYMBAL_1("Crash cymbal 1",12,(byte)49),
    RIDE_CYMBAL_2("Ride cymbal 2",13,(byte)59),
    SPLASH_CYMBAL("Splash cymbal",14,(byte)55),
    CHINESE_CYMBAL("Chinese cymbal",15,(byte)52),
    BASS_GUITAR("Bass guitar",16,(byte)1);
    
    public static DrumSet getObject(int index) {
        for(DrumSet d:DrumSet.values()){
            if(d.getValue() == index)
                return d;
        }
        
        return ACOUSTIC_BASS_DRUM;
    }
   
    private final String name;
    private final int numValue;
    private final byte midiValue;
        
    DrumSet(String name,int value,byte mValue){
        this.name = name;
        this.numValue = value; 
        this.midiValue = mValue;
    }
    
    public byte getMidiValue(){
        return this.midiValue;
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
        
}
