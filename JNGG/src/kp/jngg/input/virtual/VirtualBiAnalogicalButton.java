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
public class VirtualBiAnalogicalButton extends VirtualBiButton
{
    protected final BindingObject leftObj, rightObj;
    protected float data;
    
    public VirtualBiAnalogicalButton(InputId leftId, InputId rightId)
    {
        this.leftObj = BindingObject.create(leftId);
        this.rightObj = BindingObject.create(rightId);
    }
    public VirtualBiAnalogicalButton(InputMask leftMask, InputMask rightMask)
    {
        this.leftObj = BindingObject.create(leftMask);
        this.rightObj = BindingObject.create(rightMask);
    }
    
    @Override
    public void setData(float data) { this.data = data; }
    @Override
    public float getData() { return data; }

    @Override
    public void pressLeft() { data = -1f; }
    @Override
    public void pressRight() { data = 1f; }

    @Override
    public void releaseLeft()
    {
        if(data < 0)
            data = 0;
    }
    @Override
    public void releaseRight()
    {
        if(data > 0)
            data = 0;
    }

    @Override
    public boolean isAnyPressed() { return data != 0; }
    @Override
    public boolean isLeftPressed() { return data < 0; }
    @Override
    public boolean isRightPressed() { return data > 0; }

    @Override
    public int getDirection() { return data < 0 ? -1 : data > 0 ? 1 : 0; }

    @Override
    public void dispatchEvent(InputEvent event)
    {
        if(leftObj.check(event))
        {
            if(event.isPressed())
                data = event.getData().getData();
            else if(data < 0)
                data = 0;
        }
        else if(rightObj.check(event))
        {
            if(event.isPressed())
                data = event.getData().getData();
            else if(data > 0)
                data = 0;
        }
    }
    
}
