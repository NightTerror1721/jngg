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
public class StaticSprite extends Sprite implements SpriteModel
{
    final RawBitmap image;
    
    StaticSprite(RawBitmap image) { this.image = image; }
    
    @Override
    public final SpriteModel getModel() { return this; }
    
    @Override
    public int width() { return image.raw.getWidth(); }

    @Override
    public int height() { return image.raw.getHeight(); }

    @Override
    public final void update(double delta) {}

    @Override
    public void draw(Graphics2D g, AffineTransform transform)
    {
        g.drawImage(image.raw, transform, null);
    }

    @Override
    public StaticSprite buildSprite() { return this; }

    @Override
    public final RawBitmap getRaw() { return image; }
    
}
