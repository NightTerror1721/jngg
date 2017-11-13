/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.util.LinkedList;

/**
 *
 * @author mpasc
 */
public final class InputDispatcher
{
    private final LinkedList<InputListener> listeners;
    private final LinkedList<Input> inputs;
    
    public InputDispatcher()
    {
        listeners = new LinkedList<>();
        inputs = new LinkedList<>();
    }
    
    public final void addListener(InputListener listener)
    {
        if(listener == null)
            throw new NullPointerException();
        listeners.add(listener);
    }
    
    public final void removeListener(InputListener listener)
    {
        if(listener == null)
            throw new NullPointerException();
        listeners.remove(listener);
    }
    
    
    public final void addInput(Input input)
    {
        if(input == null)
            throw new NullPointerException();
        inputs.add(input);
    }
    
    public final void removeInput(Input input)
    {
        if(input == null)
            throw new NullPointerException();
        inputs.remove(input);
    }
    
    
    public final void dispatch()
    {
        for(Input input : inputs)
            input.dispatchEvents(listeners);
    }
}
