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
public abstract class VirtualButton extends VirtualReceiver
{
    public abstract void setData(float data);
    public abstract float getData();
    
    public abstract void press();
    public abstract void release();
    public abstract boolean isPressed();
}
