/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import dao.Event;
import dao.Pattern;
import javax.persistence.EntityManager;

/**
 *
 * @author Tomek
 */
public class Controller {
    
    private EntityManager em;
    
    public Controller(EntityManager m){
        this.em = m;
    }
    
    private void saveEvent(MidiEvent ev){
        Event evt = new Event();
        
    }
    
    public void savePattern(Pattern pat){
        em.getTransaction().begin();
        em.persist(pat);        
        em.getTransaction().commit();
    }
}
