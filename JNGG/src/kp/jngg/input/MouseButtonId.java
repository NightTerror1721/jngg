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
public final class MouseButtonId extends InputId
{
    private final MouseButton.MouseButtonType button;
    
    private MouseButtonId(MouseButton.MouseButtonType button)
    {
        super(InputId.MOUSE_TYPE, button.value);
        this.button = button;
    }

    @Override
    public final String getName() { return button.name(); }

    @Override
    protected String innerToString() { return "MOUSE " + getName(); }
    
    public static final MouseButtonId
            BUTTON1 = new MouseButtonId(MouseButton.MouseButtonType.BUTTON1),
            BUTTON2 = new MouseButtonId(MouseButton.MouseButtonType.BUTTON2),
            BUTTON3 = new MouseButtonId(MouseButton.MouseButtonType.BUTTON3);
    
    public static final InputId getId(int buttonCode)
    {
        switch(buttonCode)
        {
            case MouseEvent.BUTTON1:
                return BUTTON1;
            case MouseEvent.BUTTON2:
                return BUTTON2;
            case MouseEvent.BUTTON3:
                return BUTTON3;
            default: return InputId.invalid(-1);
        }
    }
}
