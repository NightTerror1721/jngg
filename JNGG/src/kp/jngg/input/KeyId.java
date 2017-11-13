/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

/**
 *
 * @author mpasc
 */
public class KeyId extends InputId
{
    KeyId(int keycode)
    {
        super(InputId.KEYBOARD_TYPE, keycode);
    }

    @Override
    public String getName() { return Keycode.getKeyName(this); }

    @Override
    protected String innerToString() { return "KEY " + getName(); }
    
    
    public static final InputId getId(int keycode)
    {
        return new KeyId(keycode);
    }
}
