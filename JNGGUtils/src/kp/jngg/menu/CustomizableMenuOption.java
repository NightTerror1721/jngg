/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.menu;

import kp.jngg.input.InputEvent;

/**
 *
 * @author Asus
 */
public class CustomizableMenuOption extends MenuOption
{
    private CustomEventAction onAction;
    private CustomEventAction onBack;
    private CustomEventAction onUp;
    private CustomEventAction onDown;
    private CustomEventAction onLeft;
    private CustomEventAction onRight;
    private CustomDefaultEventAction onDefaultEvent;
    
    
    public final void setCustomOnAction(CustomEventAction eventAction) { this.onAction = eventAction; }
    public final void setCustomOnBack(CustomEventAction eventAction) { this.onBack = eventAction; }
    public final void setCustomOnUp(CustomEventAction eventAction) { this.onUp = eventAction; }
    public final void setCustomOnDown(CustomEventAction eventAction) { this.onDown = eventAction; }
    public final void setCustomOnLeft(CustomEventAction eventAction) { this.onLeft = eventAction; }
    public final void setCustomOnRight(CustomEventAction eventAction) { this.onRight = eventAction; }
    public final void setCustomOnDefaultEvent(CustomDefaultEventAction eventAction) { this.onDefaultEvent = eventAction; }
    
    public void onAction(MenuController controller)
    {
        if(onAction != null)
            onAction.execute(controller);
    }
    
    public void onBack(MenuController controller)
    {
        if(onBack != null)
            onBack.execute(controller);
    }
    
    public void onUp(MenuController controller)
    {
        if(onUp != null)
            onUp.execute(controller);
    }
    
    public void onDown(MenuController controller)
    {
        if(onDown != null)
            onDown.execute(controller);
    }
    
    public void onLeft(MenuController controller)
    {
        if(onLeft != null)
            onLeft.execute(controller);
    }
    
    public void onRight(MenuController controller)
    {
        if(onRight != null)
            onRight.execute(controller);
    }
    
    public void dispatchUnbindedEvent(InputEvent event, MenuController controller)
    {
        if(onDefaultEvent != null)
            onDefaultEvent.execute(event, controller);
    }
}
