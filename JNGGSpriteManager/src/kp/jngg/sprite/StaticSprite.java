/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.sprite;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Asus
 */
public class StaticSprite extends Sprite implements SpriteModel
{
    final BufferedImage image;
    
    StaticSprite(BufferedImage image) { this.image = image; }
    
    @Override
    public final SpriteModel getModel() { return this; }
    
    @Override
    public int width() { return image.getWidth(); }

    @Override
    public int height() { return image.getHeight(); }

    @Override
    public final void update(double delta) {}

    @Override
    public void draw(Graphics2D g, AffineTransform transform)
    {
        g.drawImage(image, transform, null);
    }

    @Override
    public StaticSprite buildSprite() { return this; }
    
}
