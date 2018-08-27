/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.utils;

import kp.jngg.math.BoundingBox;
import kp.jngg.math.Vector2;

/**
 *
 * @author Asus
 */
public final class BBContainer
{
    private final Vector2 localPosition = new Vector2();
    private final Vector2 localSize = new Vector2(1, 1);
    private BoundingBox bbox;
    
    public BBContainer(BoundingBox bbox)
    {
        if(bbox == null)
            throw new NullPointerException();
        this.bbox = bbox;
    }
    
    public BBContainer() { this(new BoundingBox()); }
    
    public final void setBoundingBox(BoundingBox bbox)
    {
        if(bbox == null)
            throw new NullPointerException();
        this.bbox = bbox;
    }
    public final BoundingBox getBoundingBox() { return bbox; }
    
    public final void setLocalPosition(double x, double y) { this.localPosition.set(x, y); }
    public final void setLocalPosition(Vector2 position) { this.localPosition.set(position); }
    public final void setLocalPositionX(double x) { this.localPosition.x = x; }
    public final void setLocalPositionY(double y) { this.localPosition.y = y; }
    
    public final void setLocalSize(double width, double height) { this.localSize.set(width, height); }
    public final void setLocalSize(Vector2 position) { this.localSize.set(position); }
    public final void setLocalSizeWidth(double width) { this.localSize.x = width; }
    public final void setLocalSizeHeight(double height) { this.localSize.y = height; }
    
    public final Vector2 getLocalPosition() { return localPosition.copy(); }
    public final double getLocalPositionX() { return localPosition.x; }
    public final double getLocalPositionY() { return localPosition.y; }
    
    public final Vector2 getLocalSize() { return localSize.copy(); }
    public final double getLocalSizeWidth() { return localSize.x; }
    public final double getLocalSizeHeight() { return localSize.y; }
    
    public final void resituate(double x, double y)
    {
        bbox.resituate(x + localPosition.x, y + localPosition.y, localSize.x, localSize.y);
    }
    public final void resituate(Vector2 position)
    {
        bbox.resituate(position.x + localPosition.x, position.y + localPosition.y, localSize.x, localSize.y);
    }
    
    public final void resituateLeftTopPoint(double x, double y)
    {
        bbox.resituateLeftTopPoint(x + localPosition.x, y + localPosition.y, localSize.x, localSize.y);
    }
    public final void resituateLeftTopPoint(Vector2 position)
    {
        bbox.resituateLeftTopPoint(position.x + localPosition.x, position.y + localPosition.y, localSize.x, localSize.y);
    }
}
