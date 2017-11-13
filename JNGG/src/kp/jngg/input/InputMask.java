/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.util.HashMap;

/**
 *
 * @author mpasc
 */
public final class InputMask implements Comparable<InputMask>
{
    private final int id;
    
    private InputMask(final int id) { this.id = id; }
    
    public final int getId() { return id; }
    
    public final boolean equals(InputMask mask) { return id == mask.id; }
    
    @Override
    public final boolean equals(Object o)
    {
        return o instanceof InputMask && id == ((InputMask)o).id;
    }

    @Override
    public final int hashCode()
    {
        int hash = 5;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public final int compareTo(InputMask o)
    {
        return id < o.id ? -1 : id > o.id ? 1 : 0;
    }
    
    
    
    
    private static final HashMap<Integer, InputMask> HASH = new HashMap<>();
    
    public static final InputMask registerMask(int id)
    {
        if(HASH.containsKey(id))
            throw new IllegalArgumentException("InputMask: " + id + " already exists");
        InputMask m = new InputMask(id);
        HASH.put(id, m);
        return m;
    }
    
    public static final void destroyMask(InputMask mask)
    {
        HASH.remove(mask.id);
    }
}
