/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import kp.jngg.input.InputEvent;
import kp.jngg.input.InputId;
import kp.jngg.input.InputMask;

/**
 *
 * @author Asus
 */
public abstract class BindingObject
{
    private BindingObject() {}
    
    public abstract boolean check(InputEvent event);
    
    
    public static final BindingObject create(InputId id) { return new InputIdBinding(id); }
    public static final BindingObject create(InputMask mask) { return new MaskBinding(mask); }
    
    private static final class InputIdBinding extends BindingObject
    {
        private final InputId id;
        
        private InputIdBinding(InputId id)
        {
            if(id == null)
                throw new NullPointerException();
            this.id = id;
        }

        @Override
        public final boolean check(InputEvent event)
        {
            return id.equals(event.getId());
        }
    }
    
    private static final class MaskBinding extends BindingObject
    {
        private final InputMask mask;
        
        private MaskBinding(InputMask mask)
        {
            if(mask == null)
                throw new NullPointerException();
            this.mask = mask;
        }

        @Override
        public final boolean check(InputEvent event)
        {
            return event.equalsMask(mask);
        }
    }
}
