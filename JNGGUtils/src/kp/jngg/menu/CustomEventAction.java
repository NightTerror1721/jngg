/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.menu;

/**
 *
 * @author Asus
 */
@FunctionalInterface
public interface CustomEventAction
{
    boolean execute(MenuController controller);
    
    default CustomEventAction and(CustomEventAction second)
    {
        CustomEventAction self = this;
        return controller -> self.execute(controller) && second.execute(controller);
    }
    
    default CustomEventAction xor(CustomEventAction second)
    {
        CustomEventAction self = this;
        return controller -> self.execute(controller) ^ second.execute(controller);
    }
    
    default CustomEventAction or(CustomEventAction second)
    {
        CustomEventAction self = this;
        return controller -> self.execute(controller) || second.execute(controller);
    }
    
    default CustomEventAction not()
    {
        CustomEventAction self = this;
        return controller -> !self.execute(controller);
    }
}
