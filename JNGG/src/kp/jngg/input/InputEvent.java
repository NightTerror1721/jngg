/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.awt.event.KeyEvent;

/**
 *
 * @author mpasc
 */
public final class InputEvent
{
    public static final char INVALID_CHAR = KeyEvent.CHAR_UNDEFINED;
    
    InputId id;
    InputData data;
    InputMask mask;
    
    public final void setEventProperties(InputId id, InputData data)
    {
        this.id = id;
        this.data = data;
    }
    
    public final int getCode() { return id.code; }
    public final <D extends InputData> D getData() { return (D) data; }
    
    public final InputId getId() { return id; }
    public final int getIdType() { return id.getInputType(); }
    
    public final char getCodeChar() { return (char) id.code; }
    
    public final boolean isPressed() { return data.isPressed(); }
    public final boolean isReleased() { return data.isReleased(); }
    
    public final boolean hasMask() { return mask != null; }
    public final InputMask getMask() { return mask; }
    
    public final boolean equalsMask(InputMask mask) { return this.mask.equals(mask); }
    public final boolean anyMaskEquals(InputMask... masks)
    {
        for(InputMask m : masks)
            if(mask.equals(m))
                return true;
        return false;
    }
    public final InputMask getFirstMaskMatches(InputMask... masks)
    {
        for(InputMask m : masks)
            if(mask.equals(m))
                return m;
        return null;
    }
    
    @Override
    public final String toString() { return "[" + data + "]" + id; }
}
