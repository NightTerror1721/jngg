/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author Asus
 */
public class MouseWheel extends AbstractInput implements MouseWheelListener
{
    public static final InputId INPUT_ID = MouseWheelId.ID;
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        int units = e.getScrollAmount();
        if(units != 0)
            putEntry(INPUT_ID, new IntegerInputData(units));
    }
}
