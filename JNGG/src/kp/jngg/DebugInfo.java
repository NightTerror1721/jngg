/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Asus
 */
public final class DebugInfo
{
    private final Display display;
    private Font font;
    private Color color;
    private boolean enabled, exact, showMem, showUpFps;
    
    DebugInfo(Display display)
    {
        if(display == null)
            throw new NullPointerException();
        this.display = display;
        this.font = new Font("arial", Font.BOLD, 12);
        this.color = Color.BLACK;
        this.enabled = false;
        this.exact = false;
        this.showMem = false;
        this.showUpFps = false;
    }
    
    public final void setFont(Font font)
    {
        if(font == null)
            throw new NullPointerException();
        this.font = font;
    }
    
    public final void setColor(Color color)
    {
        if(color == null)
            throw new NullPointerException();
        this.color = color;
    }
    
    public final void setEnabled(boolean enabled) { this.enabled = enabled; }
    public final void setExactFps(boolean exact) { this.exact = exact; }
    public final void setShowMemory(boolean show) { this.showMem = show; }
    public final void setShowUpdateFps(boolean show) { this.showUpFps = show; }
    
    final void draw(Graphics2D g)
    {
        if(!enabled)
            return;
        
        String text, text2;
        if(exact)
            text = display.getExactCurrentFpsPerSecond() + " fps";
        else
            text = display.getCurrentFpsPerSecond() + " fps";
        Dimension dim = getStringDimensions(text,g,font);
        int x = 5;
        int y =  dim.height;
        g.setColor(color);
        g.setFont(font);
        g.drawString(text,x,y);

        if(showMem)
        {
            text = Float.toString(Display.getMemoryUsedInMb());
            if(text.length() > 5)
                text = text.substring(0,5);
            text = "Memory => Used: " + text + "MB";
            text2 = Float.toString(Display.getTotalMemoryInMb());
            if(text2.length() > 5)
                text2 = text2.substring(0,5);
            text2 = "Total: " + text2 + "MB";
            text += " | " + text2;
            dim = getStringDimensions(text,g,font);
            y += 3 + dim.height;
            g.drawString(text,x,y);
        }

        if(showUpFps)
        {
            if(exact)
                text = Double.toString(display.getExactCurrentUpdateFramerate());
            else
                text = Integer.toString(display.getCurrentUpdateFramerate());
            text = "Physics Framerate = " + text;
            dim = getStringDimensions(text,g,font);
            y += 3 + dim.height;
            g.drawString(text,x,y);
        }
    }
    
    static final Dimension getStringDimensions(String text, Graphics g, Font font)
    {
        // get metrics from the graphics
        FontMetrics metrics = g.getFontMetrics(font);
        // get the height of a line of text in this
        // font and render context
        int hgt = metrics.getAscent();
        // get the advance of my text in this font
        // and render context
        int adv = metrics.stringWidth(text);
        // calculate the size of a box to hold the
        // text with some padding.
        return new Dimension(adv,hgt);
    }
}
