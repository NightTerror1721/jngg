/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.sprite;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import kp.jngg.sprite.SpriteLoader.RawBitmap;

/**
 *
 * @author Asus
 */
public class SheetSprite extends StaticSprite
{
    private final int x0;
    private final int y0;
    
    private final int x1;
    private final int y1;
    
    private final int width;
    private final int height;
    
    SheetSprite(RawBitmap image, int x0, int y0, int x1, int y1)
    {
        super(image);
        
        this.x0 = x0;
        this.y0 = y0;
        
        this.x1 = x1;
        this.y1 = y1;
        
        this.width = x1 - x0;
        this.height = y1 - y0;
    }
    
    @Override
    public final int width() { return width; }

    @Override
    public final int height() { return height; }

    @Override
    public final void draw(Graphics2D g, AffineTransform transform)
    {
        AffineTransform aold = g.getTransform();
        g.transform(transform);
        g.drawImage(image.raw, 0, 0, width, height, x0, y0, x1, y1, null);
        g.setTransform(aold);
    }
    
    @Override
    public SheetSprite buildSprite() { return this; }
}
