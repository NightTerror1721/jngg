/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

/**
 *
 * @author Asus
 */
public abstract class InputData
{
    public static final InputData PRESSED = new DefaultInputData(true);
    public static final InputData RELEASED = new DefaultInputData(false);
    
    public abstract boolean isPressed();
    public abstract boolean isReleased();
    public abstract float getData();
    
    @Override
    public String toString()
    {
        return isPressed() ? "PRESSED" : "RELEASED";
    }
    
    private static final class DefaultInputData extends InputData
    {
        private final boolean pressed;
        
        private DefaultInputData(boolean pressed) { this.pressed = pressed; }
        
        @Override
        public boolean isPressed() { return pressed; }

        @Override
        public boolean isReleased() { return !pressed; }
        
        @Override
        public final float getData() { return pressed ? 1f : 0f; }
    }
}
