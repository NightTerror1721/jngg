/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input.virtual;

import kp.jngg.input.InputEvent;
import kp.jngg.input.InputId;
import kp.jngg.input.InputMask;

/**
 *
 * @author Asus
 */
public class VirtualAnalogicalButton extends VirtualButton
{
    protected final BindingObject bindingObj;
    protected float data;
    
    public VirtualAnalogicalButton(InputId bindingId) { this.bindingObj = BindingObject.create(bindingId); }
    public VirtualAnalogicalButton(InputMask bindingMask) { this.bindingObj = BindingObject.create(bindingMask); }
    
    @Override
    public final void setData(float data) { this.data = data; }
    @Override
    public final float getData() { return data; }
    
    @Override
    public final void press() { data = 1f; }
    
    @Override
    public final void release() { data = 0f; }
    
    @Override
    public final boolean isPressed() { return data != 0f; }
    
    @Override
    public void dispatchEvent(InputEvent event)
    {
        if(bindingObj.check(event))
            data = event.getData().getData();
    }
    
}
