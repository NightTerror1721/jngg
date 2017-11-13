/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Collection;
import kp.jngg.Display;

/**
 *
 * @author Asus
 */
public class MouseTracker extends AbstractInput
{
    protected final Display base;
    private int x, y;
    
    public MouseTracker(Display display)
    {
        if(display == null)
            throw new NullPointerException();
        base = display;
    }
    
    public final int getX() { return x; }
    public final int getY() { return y; }
    public final Point getLocation() { return new Point(x, y); }
    
    public void update()
    {
        Point p = MouseInfo.getPointerInfo().getLocation();
        
        int dx = p.x + base.getX();
        int dy = p.y - base.getY();
        if(dx >= 0 && dx < base.getWidth() && dy >= 0 && dy < base.getHeight())
        {
            x = dx;
            y = dy;
        }
    }
    
    @Override
    public void dispatchEvents(Collection<InputListener> listeners) { update(); }
}
