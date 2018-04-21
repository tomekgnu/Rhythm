/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

/**
 *
 * @author Tomek
 */

public enum DrumSet {
   
    ACOUSTIC_BASS_DRUM("Acoustic base drum",0),
    SIDE_STICK("Side stick",1),
    ACOUSTIC_SNARE("Acoustic snare",2),
    COWBELL("Cowbell",3),
    LOW_FLOOR_TOM("Low floor tom",4),
    HIGH_FLOOR_TOM("High floor tom",5),
    LOW_MID_TOM("Low mid tom",6),
    HI_MID_TOM("Hi mid tom",7),
    HIGH_TOM("High tom",8),
    CLOSED_HI_HAT("Closed hi-hat",9),
    OPEN_HI_HAT("Open hi-hat",10),
    PEDAL_HI_HAT("Pedal hi-hat",11),
    CRASH_CYMBAL_1("Crash cymbal 1",12),
    RIDE_CYMBAL_2("Ride cymbal 2",13),
    SPLASH_CYMBAL("Splash cymbal",14),
    CHINESE_CYMBAL("Chinese cymbal",15);

    static int getObject(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    private final String name;
    private final int value;
        
    DrumSet(String name,int value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }  
   public DrumSet getValue() {
        return this;
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
            CHINESE_CYMBAL.getName()
        };
    }
        
}
