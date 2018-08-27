/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.math;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import kp.jngg.DrawingArea;

/**
 *
 * @author Asus
 */
public class Camera
{
    private static double PIXELS_PER_METER = 1f;
    private double zoom, rotation;
    private AffineTransform at_transform;
    private AffineTransform identity;
    private BoundingBox bounds;
    private final Vector2 pos, clip;
    private Vector2 limit0, limit1;
    private AffineTransform drawPos;
    private Shape canvas;
    private Shape clipIdentity;
    private boolean modif;
    
    public Camera(int viewportX, int viewportY)
    {
        zoom = 1f;
        rotation = 0f;
        at_transform = null;
        bounds = null;
        pos = new Vector2();
        clip = new Vector2(viewportX, viewportY);
        limit0 = limit1 = null;
        canvas = null;
        drawPos = null;
    }
    public Camera(DrawingArea canvas) { this(canvas.getWidth(), canvas.getHeight()); }
    
    public static final void setPixelsPerMeter(double pixels) throws IllegalArgumentException
    {
        if(pixels <= 0d)
            throw new IllegalArgumentException("pixels cannot be less or equals than 0");
        PIXELS_PER_METER = pixels;
    }
    public static final double getPixelsPerMeter() { return PIXELS_PER_METER; }
    
    private void recalcTransform()
    {
        if(!modif) return;
        update();
    }
    
    public final void setLimitedScope(Vector2 p0, Vector2 p1)
    {
        limit0 = new Vector2(p0);
        limit1 = new Vector2(p1);
    }
    public final void unsetLimitedScope() { limit0 = limit1 = null; }
    
    public final void setViewport(double x, double y)
    {
        clip.set(x,y);
        modif = true;
    }
    
    public final Vector2 getViewport()
    {
        return clip.copy();
    }
    
    public final void copyViewport(Camera cam)
    {
        clip.set(cam.clip);
    }
    
    public final void setCustomCanvasArea(double x, double y, double width, double height)
    {
        if(identity != null)
            throw new IllegalStateException("Camera is activated");
        double maxx = clip.x;
        double maxy = clip.y;
        
        width = Math.min(width, maxx);
        height = Math.min(height, maxy);
        x = Math.min(Math.max(x, 0), maxx - width);
        y = Math.min(Math.max(y, 0), maxy - height);
        canvas = new Rectangle.Double(x, y, width, height);
    }
    public final void setNativeCanvasArea()
    {
        if(identity != null)
            throw new IllegalStateException("Camera is activated");
        canvas = null;
    }
    
    public final void setCustomDrawPosition(double x, double y) { drawPos = AffineTransform.getTranslateInstance(x, y); }
    public final void setNativeDrawPosition() { drawPos = null; }
    
    public final void update()
    {
        double w = clip.x;
        double h = clip.y;
        fixLimits(w, h);
                
        Matrix33 transform = Matrix33.newTraslation((float) - pos.x, (float) - pos.y)
                .multiply(Matrix33.newRotation(rotation))
                .multiply(Matrix33.newScale(1f / zoom * PIXELS_PER_METER, 1f / zoom * PIXELS_PER_METER))
                .multiply(Matrix33.newTraslation(w / 2,h / 2));
        at_transform = transform.toAffineTransform();
        
                
        clip.x = w;
        clip.y = h;
        try
        {
            AffineTransform inv = new AffineTransform(at_transform);
            inv.invert();
            Point2D.Double vpos = new Point2D.Double(),
                    vsize = new Point2D.Double();
            inv.transform(new Point2D.Double(), vpos);
            inv.transform(new Point2D.Double(clip.x, clip.y), vsize);
            bounds = new BoundingBox(
                    new Vector2(vpos.x, vpos.y),
                    new Vector2(vsize.x, vsize.y));
            
        }
        catch(NoninvertibleTransformException ex)
        {
            
        }
        
        modif = false;
    }
    
    public final AffineTransform getAffineTransform()
    {
        recalcTransform();
        return at_transform;
    }
    
    public final void activate(Graphics2D g)
    {
        if(identity != null)
            throw new IllegalStateException("Camera is activated");
        recalcTransform();
        identity = new AffineTransform(g.getTransform());
        
        if(canvas != null)
            clipIdentity = g.getClip();
        if(drawPos != null)
            g.transform(drawPos);
        if(clipIdentity != null)
            g.setClip(canvas);
        g.transform(at_transform);
    }
    
    public final void deactivate(Graphics2D g)
    {
        if(identity == null)
            throw new IllegalStateException("Camera is deactivated");
        g.setTransform(identity);
        identity = null;
        if(clipIdentity != null)
        {
            g.setClip(clipIdentity);
            clipIdentity = null;
        }
    }
    
    private void fixLimits(double w, double h)
    {
        if(limit0 == null)
            return;
        
        w *= zoom * PIXELS_PER_METER;
        h *= zoom * PIXELS_PER_METER;
        
        double w2 = w / 2;
        double h2 = h / 2;
        
        if(w >= limit1.x - limit0.x)
            pos.x = limit0.x + (limit1.x - limit0.x) / 2;
        else
        {
            if(pos.x - w2 < limit0.x)
                pos.x = limit0.x + w2;
            else if(pos.x + w2 > limit1.x)
                pos.x = limit1.x - w2;
        }
        
        if(h >= limit1.y - limit0.y)
            pos.y = limit0.y + (limit1.y - limit0.y) / 2;
        else
        {
            if(pos.y - h2 < limit0.y)
                pos.y = limit0.y + h2;
            else if(pos.y + h2 > limit1.y)
                pos.y = limit1.y - h2;
        }
    }
    
    public final BoundingBox getBounds() { return bounds; }
    //public final Rectangle getBoundsAsRectangle() { return bounds.toRectangle(); }
    public final boolean contains(BoundingBox bb)
    {
        return bounds.hasCollision(bb);
    }
    
    public final boolean contains(float x, float y, float width, float height)
    {
        return contains(new BoundingBox(x, y, x + width, y + height));
    }
    
    public final boolean contains(double x, double y, double width, double height)
    {
        return contains(new BoundingBox(x, y, x + width, y + height));
    }
    
    public final boolean contains(Vector2 point)
    {
        return bounds.contains(point);
    }
    
    public final boolean contains(float x, float y)
    {
        return bounds.contains(x,y);
    }
    
    public final boolean contains(double x, double y)
    {
        return bounds.contains(new Vector2(x, y));
    }
    
    public final void setPosition(float x, float y)
    {
        modif = true;
        pos.x = x;
        pos.y = y;
    }
    public final void setPosition(Vector2 pos)
    {
        modif = true;
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }
    public final void traslate(float meters_x, float meters_y)
    {
        modif = true;
        pos.x += meters_x;
        pos.y += meters_y;
    }
    public final void traslate(double meters_x, double meters_y)
    {
        modif = true;
        pos.x += meters_x;
        pos.y += meters_y;
    }
    public final void traslate(Vector2 dv)
    {
        modif = true;
        pos.x += dv.x;
        pos.y += dv.y;
    }
    
    public final void setRotation(float rad_angle)
    {
        modif = true;
        rotation = rad_angle;
    }
    
    public final void rotate(float rad_angle)
    {
        modif = true;
        rotation += rad_angle;
    }
    
    public final double getRotation() { return rotation; }
    
    public final void setZoom(double value)
    {
        this.zoom = value;
    }
    
    public final void translateZ(float meters)
    {
        zoom += meters;
        modif = true;
    }
    
    public final void translateZ(double meters)
    {
        zoom += meters;
        modif = true;
    }
    
    public final double getZoom() { return zoom; }
    public final double getAngle() { return rotation; }
    public final double getX() { return pos.x; }
    public final double getY() { return pos.y; }
    public final Vector2 getPosition() { return pos.copy(); }
    
    public final Vector2 vectorToWorld(Vector2 v)
    {
        Point2D dest = new Point2D.Double();
        at_transform.transform(new Point2D.Double(v.x,v.y),dest);
        return new Vector2(dest);
    }
    
    public final Point2D pointToWorld(Point2D p)
    {
        Point2D dest = new Point2D.Double();
        at_transform.transform(p,dest);
        return dest;
    }
    
    public final Point integerPointToWorld(Point2D p)
    {
        Point dest = new Point();
        at_transform.transform(p,dest);
        return dest;
    }
    
    public final Vector2 vectorToLocal(Vector2 v)
    {
        try
        {
            AffineTransform inv = at_transform.createInverse();
            Point dest = new Point();
            inv.transform(new Point2D.Double(v.x,v.y),dest);
            return new Vector2(dest);
        }
        catch(NoninvertibleTransformException ex) { return v.copy(); }
    }
    
    public final Point2D pointToLocal(Point2D p)
    {
        try
        {
            AffineTransform inv = at_transform.createInverse();
            Point dest = new Point();
            inv.transform(p,dest);
            return dest;
        }
        catch(NoninvertibleTransformException ex) { return p; }
    }
    
    public final Point integerPointToLocal(Point2D p)
    {
        try
        {
            AffineTransform inv = at_transform.createInverse();
            Point dest = new Point();
            inv.transform(p,dest);
            return dest;
        }
        catch(NoninvertibleTransformException ex) { return (Point) p; }
    }
}
