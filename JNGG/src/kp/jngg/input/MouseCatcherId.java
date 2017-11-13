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
public final class MouseCatcherId extends InputId
{
    public static final MouseCatcherId ID = new MouseCatcherId();
    
    private MouseCatcherId()
    {
        super(InputId.MOUSE_CATCHER_TYPE, 1);
    }
    
    @Override
    public String getName() { return "Virtual Mouse Delta"; }

    @Override
    protected String innerToString() { return getName(); }
    
}
