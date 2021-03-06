/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.math;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Asus
 */
public class BoundingBox
{
    public double x0, y0, x1, y1;
    
    public BoundingBox() {}
    public BoundingBox(double x0, double y0, double x1, double y1)
    {
        this.x0 = x0 > x1 ? x1 : x0;
        this.y0 = y0 > y1 ? y1 : y0;
        this.x1 = x1 < x0 ? x0 : x1;
        this.y1 = y1 < y0 ? y0 : y1;
    }
    public BoundingBox(Vector2 p0, Vector2 p1)
    {
        this(p0.x, p0.y, p1.x, p1.y);
    }
    
    public Vector2 getPoint0() { return new Vector2(x0,y0); }
    public Vector2 getPoint1() { return new Vector2(x1,y1); }
    
    public BoundingBox translate(Vector2 position)
    {
        return translate(position.x, position.y);
    }
    public BoundingBox translate(double x, double y)
    {
        x0 += x;
        y0 += y;
        x1 += x;
        y1 += y;
        return this;
    }
    
    public BoundingBox resituate(Vector2 position, double size)
    {
        return resituate(position.x, position.y, size);
    }
    public BoundingBox resituate(Vector2 position, Vector2 size)
    {
        return resituate(position.x, position.y, size.x, size.y);
    }
    public BoundingBox resituateLeftTopPoint(Vector2 point, double size)
    {
        return resituateLeftTopPoint(point.x, point.y, size);
    }
    public BoundingBox resituateLeftTopPoint(Vector2 point, Vector2 size)
    {
        return resituateLeftTopPoint(point.x, point.y, size.x, size.y);
    }
    public BoundingBox resituate(double x, double y, double sizeX, double sizeY)
    {
        double sizeX2 = sizeX / 2d;
        double sizeY2 = sizeY / 2d;
        x0 = x - sizeX2;
        y0 = y - sizeY2;
        x1 = x + sizeX2;
        y1 = y + sizeY2;
        return this;
    }
    public BoundingBox resituateLeftTopPoint(double x, double y, double sizeX, double sizeY)
    {
        x0 = x;
        y0 = y;
        x1 = x + sizeX;
        y1 = y + sizeY;
        return this;
    }
    public BoundingBox resituate(double x, double y, double size)
    {
        double size2 = size / 2d;
        x0 = x - size2;
        y0 = y - size2;
        x1 = x + size2;
        y1 = y + size2;
        return this;
    }
    
    public BoundingBox resituateLeftTopPoint(double x, double y, double size)
    {
        x0 = x;
        y0 = y;
        x1 = x + size;
        y1 = y + size;
        return this;
    }
    
    public static final BoundingBox situate(Vector2 position, double size)
    {
        return situate(position.x, position.y, size);
    }
    public static final BoundingBox situate(Vector2 position, Vector2 size)
    {
        return situate(position.x, position.y, size.x, size.y);
    }
    public static final BoundingBox situate(double x, double y, double sizeX, double sizeY)
    {
        double sizeX2 = sizeX / 2d;
        double sizeY2 = sizeY / 2d;
        return new BoundingBox(x - sizeX2, y - sizeY2, x + sizeX2, y + sizeY2);
    }
    public static final BoundingBox situate(double x, double y, double size)
    {
        double size2 = size / 2d;
        return new BoundingBox(x - size2, y - size2, x + size2, y + size2);
    }
    
    public boolean hasCollision(BoundingBox other)
    {
        return x0 <= other.x1 && y0 <= other.y1 &&
               x1 >= other.x0 && y1 >= other.y0;
    }
    
    public boolean contains(Vector2 point)
    {
        return contains(point.x, point.y);
    }
    public boolean contains(double x, double y)
    {
        return x0 <= x && y0 <= y &&
               x1 >= x && y1 >= y;
    }
    
    public void draw(Graphics2D g, Color color)
    {
        Color old = g.getColor();
        g.setColor(color);
        g.drawRect((int) x0, (int) y0, (int) (x1 - x0), (int) (y1 - y0));
        g.setColor(old);
    }
}
