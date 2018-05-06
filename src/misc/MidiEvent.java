/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import dao.Event;
import java.util.Objects;

/**
 *
 * @author Tomek
 */
public class MidiEvent extends Event{
   
    private final MidiInstrument instrument;
    
    public MidiEvent(){
       this.instrument = MidiInstrument.NONE;
       super.setInstrument(MidiInstrument.NONE.getValue());
    }
    
    
    public MidiEvent(int index,int part,int pos,int note,int oct){
        this.instrument = MidiInstrument.getInstrument(index);
        super.setMidiValue(instrument.getMidiValue());
        super.setInstrument(index);
        super.setPart(part);
        super.setPosition(pos);
        if(instrument == MidiInstrument.BASS_GUITAR)
            super.setMidiValue(note + 24 + (oct * 12));
    }
    
   
    public MidiInstrument getMidiInstrument(){
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
        hash = 89 * hash + super.getMidiValue();
        hash = 89 * hash + Objects.hashCode(this.instrument);
        return hash;
    }
}
