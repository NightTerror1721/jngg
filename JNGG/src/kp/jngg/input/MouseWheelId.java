/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

/**
 *
 * @author Asus
 */
public final class MouseWheelId extends InputId
{
    public static final MouseWheelId ID = new MouseWheelId();
    
    private MouseWheelId()
    {
        super(InputId.MOUSE_WHEEL_TYPE, 1);
    }

    @Override
    public String getName() { return "MOUSE WHEEL"; }

    @Override
    protected String innerToString() { return getName(); }
    
}
