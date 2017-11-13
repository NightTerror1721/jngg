/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 *
 * @author mpasc
 */
public class Keyboard extends AbstractInput implements KeyInput
{
    private final HashMap<Integer, InputEventEntry> pressed;
    
    public Keyboard()
    {
        super();
        pressed = new HashMap<>();
    }
    
    private void pushKey(Integer key)
    {
        if(!pressed.containsKey(key))
        {
            InputEventEntry e = putEntry(new KeyId(key), true);
            pressed.put(key, e);
        }
    }
    
    private void releaseKey(Integer key)
    {
        InputEventEntry e;
        if((e = pressed.remove(key)) != null)
            putEntry(e.id, false);
    }
    
    public final boolean isKeyPressed(int keycode) { return pressed.containsKey(keycode); }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        switch(code)
        {
            case KeyEvent.VK_SHIFT:
                if(e.getKeyLocation() == Keycode.KEY_LOCATION_LEFT)
                    pushKey(Keycode.VK_SHIFT_LEFT);
                else pushKey(Keycode.VK_SHIFT_RIGHT);
                break;
            case KeyEvent.VK_CONTROL:
                if(e.getKeyLocation() == Keycode.KEY_LOCATION_LEFT)
                    pushKey(Keycode.VK_CONTROL_LEFT);
                else pushKey(Keycode.VK_CONTROL_RIGHT);
                break;
            default: pushKey(code); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        switch(code)
        {
            case KeyEvent.VK_SHIFT:
                if(e.getKeyLocation() == Keycode.KEY_LOCATION_LEFT)
                    releaseKey(Keycode.VK_SHIFT_LEFT);
                else releaseKey(Keycode.VK_SHIFT_RIGHT);
                break;
            case KeyEvent.VK_CONTROL:
                if(e.getKeyLocation() == Keycode.KEY_LOCATION_LEFT)
                    releaseKey(Keycode.VK_CONTROL_LEFT);
                else releaseKey(Keycode.VK_CONTROL_RIGHT);
                break;
            default: releaseKey(code); break;
        }
    }
    
}
