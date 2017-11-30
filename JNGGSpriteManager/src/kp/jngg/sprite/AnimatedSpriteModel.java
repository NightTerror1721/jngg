/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.sprite;

import java.awt.image.BufferedImage;

/**
 *
 * @author Asus
 */
public class AnimatedSpriteModel implements SpriteModel
{
    private final BufferedImage base;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int frames;
    
    AnimatedSpriteModel(BufferedImage base, int x, int y, int width, int height, int frames)
    {
        this.base = base;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frames = frames;
    }
    
    @Override
    public Sprite buildSprite() { return new AnimatedSprite(this, base, x, y, width, height, frames); }
    
}
