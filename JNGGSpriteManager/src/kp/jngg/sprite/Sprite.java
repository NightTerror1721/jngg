/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.sprite;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Asus
 */
public abstract class Sprite
{
    Sprite() {}
    
    public abstract SpriteModel getModel();
    
    public abstract int width();
    public abstract int height();
    
    public abstract void update(double delta);
    public abstract void draw(Graphics2D g, AffineTransform transform);
    
    
    
    
    /* DEDICATED DRAW METHODS */
    
    public final void draw(Graphics2D g, double x, double y)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, float x, float y)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, int x, int y)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        draw(g, af);
    }
    
    
    
    public final void draw(Graphics2D g, double x, double y, double width, double height)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, float x, float y, float width, float height)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, int x, int y, int width, int height)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    
    
    public final void draw(Graphics2D g, double x, double y, double width, double height, double rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.rotate(rotationRadians, width / 2, height / 2);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, float x, float y, float width, float height, double rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.rotate(rotationRadians, width / 2, height / 2);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, int x, int y, int width, int height, double rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.rotate(rotationRadians, width / 2, height / 2);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    
    
    public final void draw(Graphics2D g, double x, double y, double width, double height, float rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.rotate(rotationRadians, width / 2, height / 2);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, float x, float y, float width, float height, float rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.rotate(rotationRadians, width / 2, height / 2);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, int x, int y, int width, int height, float rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        af.setToTranslation(x, y);
        af.rotate(rotationRadians, width / 2, height / 2);
        af.scale(width / width(), height / height());
        draw(g, af);
    }
    
    
    
    public final void draw(Graphics2D g, FlipMode flip, double x, double y, double width, double height)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, FlipMode flip, float x, float y, float width, float height)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, FlipMode flip, int x, int y, int width, int height)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    
    
    public final void draw(Graphics2D g, FlipMode flip, double x, double y, double width, double height, double rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, FlipMode flip, float x, float y, float width, float height, double rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, FlipMode flip, int x, int y, int width, int height, double rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    
    
    public final void draw(Graphics2D g, FlipMode flip, double x, double y, double width, double height, float rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, FlipMode flip, float x, float y, float width, float height, float rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    public final void draw(Graphics2D g, FlipMode flip, int x, int y, int width, int height, float rotationRadians)
    {
        AffineTransform af = new AffineTransform();
        switch(flip)
        {
            case HORIZONTAL:
                af.setToTranslation(x + width, y);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), height / height());
                break;
            case VERTICAL:
                af.setToTranslation(x, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(width / width(), -height / height());
                break;
            case BOTH:
                af.setToTranslation(x + width, y + height);
                af.rotate(rotationRadians, width / 2, height / 2);
                af.scale(-width / width(), -height / height());
                break;
        }
        draw(g, af);
    }
    
    
    
    public enum FlipMode { HORIZONTAL, VERTICAL, BOTH }
}
