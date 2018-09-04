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
@FunctionalInterface
public interface CustomDefaultEventAction
{
    boolean execute(InputEvent event, MenuController controller);
    
    default CustomDefaultEventAction and(CustomDefaultEventAction second)
    {
        CustomDefaultEventAction self = this;
        return (event, controller) -> self.execute(event, controller) && second.execute(event, controller);
    }
    
    default CustomDefaultEventAction xor(CustomDefaultEventAction second)
    {
        CustomDefaultEventAction self = this;
        return (event, controller) -> self.execute(event, controller) ^ second.execute(event, controller);
    }
    
    default CustomDefaultEventAction or(CustomDefaultEventAction second)
    {
        CustomDefaultEventAction self = this;
        return (event, controller) -> self.execute(event, controller) || second.execute(event, controller);
    }
    
    default CustomDefaultEventAction not()
    {
        CustomDefaultEventAction self = this;
        return (event, controller) -> !self.execute(event, controller);
    }
}
