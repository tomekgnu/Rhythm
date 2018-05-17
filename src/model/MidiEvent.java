/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Tomek
 */
public class MidiEvent {
   
    private MidiInstrument instrument;
    private int midiValue;
    private Integer part;
    private Integer position;
    
    public MidiEvent(){
       this.instrument = MidiInstrument.NONE;       
       this.midiValue = 0;
    }
    
    public MidiEvent(int index,int part,int pos,int note,int oct){
        this.instrument = MidiInstrument.getInstrumentByIndex(index);
        this.midiValue = instrument.getMidiValue();
        this.part = part;
        this.position = pos;
        if(instrument == MidiInstrument.BASS_GUITAR)
            midiValue = note + 24 + (oct * 12);
    }
    
   
    public MidiInstrument getMidiInstrument(){
       return this.instrument;
    }    
    
    public int getMidiValue(){
        return this.midiValue;
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
        hash = 89 * hash + midiValue;
        hash = 89 * hash + Objects.hashCode(this.instrument);
        return hash;
    }

    public void setMidiValue(int mv){
        this.midiValue = mv;
    }
    
    public void setInstrument(MidiInstrument ins) {
        this.instrument = ins;
    }
}
