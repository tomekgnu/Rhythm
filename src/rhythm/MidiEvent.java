/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rhythm;

import java.util.Objects;
import misc.MidiInstrument;

/**
 *
 * @author Tomek
 */
public class MidiEvent {
   
    private byte midiValue;
    private MidiInstrument instrument;
    
    MidiEvent(){
        
    }
    MidiEvent(int index,int note,int oct){
        this.instrument = MidiInstrument.getInstrument(index);
        this.midiValue = instrument.getMidiValue();
        if(instrument == MidiInstrument.BASS_GUITAR)
            this.midiValue = (byte)(note + 24 + (oct * 12));
    }
    public byte getMidiValue(){
        return this.midiValue;
    }
    public void setMidiValue(byte val){
        this.midiValue = val;
    }
    public MidiInstrument getInstrument(){
       return this.instrument;
    }
    
    
    @Override
    public boolean equals(Object e){
        if(e instanceof MidiInstrument == false)
            return false;
        return this.instrument.name().equals(((MidiInstrument)e).name());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.midiValue;
        hash = 89 * hash + Objects.hashCode(this.instrument);
        return hash;
    }
}
