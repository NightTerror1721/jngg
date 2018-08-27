/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.visual;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import kp.jngg.DrawingArea;

/**
 *
 * @author Asus
 */
public class VirtualCanvas implements DrawingArea
{
    private final DrawingArea parent;
    private final AffineTransform mat;
    private AffineTransform identity;
    private int x, y, width, height;
    
    public VirtualCanvas(DrawingArea parent, int x, int y, int width, int height)
    {
        if(parent == null)
            throw new NullPointerException();
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mat = new AffineTransform();
        
        updateMatrix();
    }
    
    private void updateMatrix()
    {
        mat.setToTranslation(x, y);
        mat.scale(parent.getWidth() / width, parent.getHeight() / height);
    }

    @Override
    public final int getX() { return x; }

    @Override
    public final int getY() { return y; }

    @Override
    public final int getWidth() { return width; }

    @Override
    public final int getHeight() { return height; }
    
    public final void setX(int x) { this.x = x; }
    public final void setY(int y) { this.y = y; }
    public final void setWidth(int width) { this.width = width; }
    public final void setHeight(int height) { this.height = height; }
    
    public final void activate(Graphics2D g)
    {
        if(identity != null)
            throw new IllegalStateException("VirtualCanvas is activated");
        identity = new AffineTransform(g.getTransform());
        
        g.transform(mat);
    }
    
    public final void deactivate(Graphics2D g)
    {
        if(identity == null)
            throw new IllegalStateException("VirtualCanvas is deactivated");
        g.setTransform(identity);
        identity = null;
    }
}
