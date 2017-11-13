/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.awt.event.MouseEvent;

/**
 *
 * @author mpasc
 */
public class MouseButton extends AbstractInput implements MouseInput
{
    private static final byte BUTTON1_CODE = 0x1;
    private static final byte BUTTON2_CODE = 0x2;
    private static final byte BUTTON3_CODE = 0x4;
    
    private int butts;
    
    public MouseButton()
    {
        super();
        butts = 0;
    }
    
    public final boolean isPressedButton1() { return (butts & BUTTON1_CODE) == BUTTON1_CODE; }
    public final boolean isPressedButton2() { return (butts & BUTTON2_CODE) == BUTTON2_CODE; }
    public final boolean isPressedButton3() { return (butts & BUTTON3_CODE) == BUTTON3_CODE; }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        switch(e.getButton())
        {
            case MouseEvent.BUTTON1:
                if((butts & BUTTON1_CODE) == BUTTON1_CODE)
                    return;
                butts |= BUTTON1_CODE;
                putEntry(MouseButtonId.BUTTON1, true);
                break;
            case MouseEvent.BUTTON2:
                if((butts & BUTTON2_CODE) == BUTTON2_CODE)
                    return;
                butts |= BUTTON2_CODE;
                putEntry(MouseButtonId.BUTTON2, true);
                break;
            case MouseEvent.BUTTON3:
                if((butts & BUTTON3_CODE) == BUTTON3_CODE)
                    return;
                butts |= BUTTON3_CODE;
                putEntry(MouseButtonId.BUTTON3, true);
                break;
        }
    }
    
    @Override
    public final void mouseReleased(MouseEvent e)
    {
        switch(e.getButton())
        {
            case MouseEvent.BUTTON1:
                if((butts & BUTTON1_CODE) == 0)
                    return;
                butts &= ~BUTTON1_CODE;
                putEntry(MouseButtonId.BUTTON1, false);
                break;
            case MouseEvent.BUTTON2:
                if((butts & BUTTON2_CODE) == 0)
                    return;
                butts &= ~BUTTON2_CODE;
                putEntry(MouseButtonId.BUTTON2, false);
                break;
            case MouseEvent.BUTTON3:
                if((butts & BUTTON3_CODE) == 0)
                    return;
                butts &= ~BUTTON3_CODE;
                putEntry(MouseButtonId.BUTTON3, false);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    public enum MouseButtonType
    {
        BUTTON1(MouseEvent.BUTTON1),
        BUTTON2(MouseEvent.BUTTON2),
        BUTTON3(MouseEvent.BUTTON3);
        
        public final int value;
        private MouseButtonType(final int value) { this.value = value; }
    }
}
