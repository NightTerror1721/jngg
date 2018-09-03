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
public class VirtualBiBinaryButton extends VirtualBiButton
{
    protected final BindingObject leftObj, rightObj;
    protected boolean left, right;
    
    public VirtualBiBinaryButton(InputId leftId, InputId rightId)
    {
        this.leftObj = BindingObject.create(leftId);
        this.rightObj = BindingObject.create(rightId);
    }
    public VirtualBiBinaryButton(InputMask leftMask, InputMask rightMask)
    {
        this.leftObj = BindingObject.create(leftMask);
        this.rightObj = BindingObject.create(rightMask);
    }
    
    @Override
    public void setData(float data)
    {
        if(data < 0)
            pressLeft();
        else if(data > 0)
            pressRight();
    }
    @Override
    public float getData() { return getDirection(); }
    
    @Override
    public void pressLeft() { left = true; right = false; }
    @Override
    public void pressRight() { right = true; left = false; }
    
    @Override
    public void releaseLeft() { left = false; }
    @Override
    public void releaseRight() { right = false; }
    
    @Override
    public boolean isLeftPressed() { return left; }
    @Override
    public boolean isRightPressed() { return right; }
    @Override
    public boolean isAnyPressed() { return left || right; }
    
    @Override
    public int getDirection() { return (left ? -1 : 0) + (right ? 1 : 0); }
    
    @Override
    public void dispatchEvent(InputEvent event)
    {
        if(leftObj.check(event))
        {
            if(event.isPressed())
            {
                left = true;
                right = false;
            }
            else left = false;
        }
        else if(rightObj.check(event))
        {
            if(event.isPressed())
            {
                right = true;
                left = false;
            }
            else right = false;
        }
    }
    
}
