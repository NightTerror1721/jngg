/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.awt.AWTException;
import java.awt.Robot;
import kp.jngg.Display;

/**
 *
 * @author Asus
 */
public class MouseCatcher extends MouseTracker
{
    public static final InputId INPUT_ID = MouseCatcherId.ID;
    
    private static Robot ROBOT;
    
    public MouseCatcher(Display display)
    {
        super(display);
        if(ROBOT == null)
        {
            try { ROBOT = new Robot(); }
            catch(AWTException ex)
            {
                ex.printStackTrace(System.err);
                throw new IllegalStateException(ex);
            }
        }
    }
    
    @Override
    public void update()
    {
        super.update();
        
        int cx = base.getWidth() / 2;
        int cy = base.getHeight() / 2;
        ROBOT.mouseMove(cx, cy);
        
        int dx = getX() - cx;
        int dy = getY() - cy;
        
        if(dx != 0 || dy != 0)
            putEntry(INPUT_ID, new MouseCatcherData(dx, dy));
    }
    
    public static final class MouseCatcherData extends InputData
    {
        private final int deltaX, deltaY;
        
        private MouseCatcherData(int deltaX, int deltaY)
        {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
        
        public final int getDeltaX() { return deltaX; }
        public final int getDeltaY() { return deltaY; }
        
        @Override
        public boolean isPressed() { return true; }

        @Override
        public boolean isReleased() { return false; }
        
        @Override
        public float getData() { return deltaX != 0 && deltaY != 0 ? 1f : 0f; }
        
        @Override
        public String toString() { return super.toString() + " deltas: [" + deltaX + ", " + deltaY + "]"; }
    }
}
