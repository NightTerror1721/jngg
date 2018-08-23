/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input.virtual;

/**
 *
 * @author Asus
 */
public abstract class VirtualBiButton extends VirtualReceiver
{
    public abstract void setData(float data);
    public abstract float getData();
    
    public abstract void pressLeft();
    public abstract void pressRight();
    
    public abstract void releaseLeft();
    public abstract void releaseRight();
    
    public abstract boolean isAnyPressed();
    public abstract boolean isLeftPressed();
    public abstract boolean isRightPressed();
    
    public abstract int getDirection();
}
