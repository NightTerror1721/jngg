/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input.virtual;

import kp.jngg.input.BindingObject;
import kp.jngg.input.InputEvent;
import kp.jngg.input.InputId;
import kp.jngg.input.InputMask;

/**
 *
 * @author Asus
 */
public class VirtualBinaryButton extends VirtualButton
{
    protected final BindingObject bindingObj;
    protected boolean pressed;
    
    public VirtualBinaryButton(InputId bindingId) { this.bindingObj = BindingObject.create(bindingId); }
    public VirtualBinaryButton(InputMask bindingMask) { this.bindingObj = BindingObject.create(bindingMask); }
    
    @Override
    public void setData(float data) { pressed = data != 0; }
    @Override
    public float getData() { return pressed ? 1f : 0f; }
    
    @Override
    public void press() { pressed = true; }
    
    @Override
    public void release() { pressed = false; }
    
    @Override
    public boolean isPressed() { return pressed; }
    
    @Override
    public void dispatchEvent(InputEvent event)
    {
        if(bindingObj.check(event))
            pressed = event.isPressed();
    }
    
}
