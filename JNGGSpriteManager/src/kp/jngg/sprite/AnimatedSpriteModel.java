/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.sprite;

import kp.jngg.sprite.SpriteLoader.RawBitmap;

/**
 *
 * @author Asus
 */
public class AnimatedSpriteModel implements SpriteModel
{
    private final RawBitmap base;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int frames;
    
    AnimatedSpriteModel(RawBitmap base, int x, int y, int width, int height, int frames)
    {
        this.base = base;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frames = frames;
    }
    
    public final int getX() { return x; }
    public final int getY() { return y; }
    public final int getWidth() { return width; }
    public final int getHeight() { return height; }
    public final int getFrames() { return frames; }
    
    @Override
    public AnimatedSprite buildSprite() { return new AnimatedSprite(this, base.raw, x, y, width, height, frames); }

    @Override
    public RawBitmap getRaw() { return base; }
    
    @Override
    public int getModelType() { return SpriteModel.TYPE_ANIMATED; }
    
}
