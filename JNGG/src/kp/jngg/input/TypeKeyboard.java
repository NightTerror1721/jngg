/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.awt.event.KeyEvent;

/**
 *
 * @author Asus
 */
public class TypeKeyboard extends Keyboard
{
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        char c = e.getKeyChar();
        if(c == KeyEvent.CHAR_UNDEFINED)
            return;
        putEntry(new TypedKeyId(c), true);
    }
    
}
