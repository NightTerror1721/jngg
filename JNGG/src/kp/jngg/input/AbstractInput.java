/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author mpasc
 */
abstract class AbstractInput implements Input
{
    private final LinkedList<InputEventEntry> entries = new LinkedList<>();
    
    final InputEventEntry putEntry(InputId id, InputData data)
    {
        InputEventEntry e;
        entries.add(e = new InputEventEntry(id, data));
        return e;
    }
    
    final InputEventEntry putEntry(InputId id, boolean pressed)
    {
        InputEventEntry e;
        entries.add(e = new InputEventEntry(id, pressed ? InputData.PRESSED : InputData.RELEASED));
        return e;
    }
    
    final boolean hasEntries() { return !entries.isEmpty(); }
    final InputEventEntry popEntry() { return entries.removeFirst(); }
    
    @Override
    public void dispatchEvents(Collection<InputListener> listeners)
    {
        InputEvent event = new InputEvent();
        while(hasEntries())
        {
            InputEventEntry e = popEntry();
            event.setEventProperties(e.id, e.data);
            for(InputListener list : listeners)
                list.dispatchEvent(event);
        }
    }
    
    
    
    static final class InputEventEntry
    {
        final InputId id;
        final InputData data;
        
        private InputEventEntry(InputId id, InputData data)
        {
            this.id = id;
            this.data = data;
        }
    }
}
