/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.sprite;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Asus
 */
public final class AnimatedSprite extends Sprite
{
    private static final Random RAND = new Random();
    private static final int NORMAL_MODE = 0;
    private static final int LOOP_MODE = 1;
    private static final int RANDOM_MODE = 2;
    
    private final AnimatedSpriteModel model;
    private final BufferedImage base;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int frames;
    
    private int mode;
    
    private double min;
    private double max;
    private double current;
    private Random rand = RAND;
    
    private float iterator;
    private float speed;
    private boolean end;
    
    AnimatedSprite(AnimatedSpriteModel model, BufferedImage base, int x, int y, int width, int height, int frames)
    {
        this.model = model;
        this.base = base;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frames = frames;
    }
    
    public final void setRandomMode(double min, double max)
    {
        if(min < 0 || min > max)
            throw new IllegalArgumentException();
        this.mode = RANDOM_MODE;
        this.min = min;
        this.max = max;
        generateCurrent();
    }
    
    public final void setRandomGenerator(Random random)
    {
        if(random == null)
            throw new NullPointerException();
        this.rand = random;
    }
    
    public final void setLoopMode() { mode = LOOP_MODE; }
    public final void setNormalMode() { mode = NORMAL_MODE; }
    
    public final void setSpeed(float speed) { this.speed = speed; }
    public final float getSpeed() { return speed; }
    
    public final boolean isNormalMode() { return mode == NORMAL_MODE; }
    public final boolean isLoopMode() { return mode == LOOP_MODE; }
    public final boolean isRandomMode() { return mode == RANDOM_MODE; }
    
    public final boolean hasEnded() { return end; }
    
    @Override
    public final AnimatedSpriteModel getModel() { return model; }

    @Override
    public final int width() { return width; }

    @Override
    public final int height() { return height; }
    
    
    
    private void generateCurrent()
    {
        current = rand.nextDouble();
        current = (max - min) * current + min;
    }
    
    private int state()
    {
        return iterator >= frames ? 1 : iterator < 0 ? -1 : 0;
    }
    
    private void updateIterator(int state)
    {
        if(state > 0)
            iterator -= frames;
        else iterator += frames;
    }

    @Override
    public final void update(double delta)
    {
        if(end)
            return;
        iterator += delta * speed;
        switch(mode)
        {
            default:
            case NORMAL_MODE: {
                if(state() != 0)
                {
                    end = true;
                    iterator = 0;
                }
            } break;
            case LOOP_MODE: {
                int state;
                while((state = state()) != 0)
                    updateIterator(state);
            } break;
            case RANDOM_MODE: {
                if(current > 0)
                    current -= delta;
                else
                {
                    int state = state();
                    if(state != 0)
                    {
                        do {
                            updateIterator(state);
                        } while((state = state()) != 0);
                        generateCurrent();
                    }
                }
            } break;
        }
    }

    @Override
    public final void draw(Graphics2D g, AffineTransform transform)
    {
        AffineTransform aold = g.getTransform();
        int dx = x + ((int) iterator * width);
        g.transform(transform);
        g.drawImage(base, 0, 0, width, height, dx, y, dx + width, y + height, null);
        g.setTransform(aold);
    }
}
