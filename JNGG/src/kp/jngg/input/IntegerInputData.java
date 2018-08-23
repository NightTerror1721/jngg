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
public class IntegerInputData extends InputData
{
    private final int value;
    
    public IntegerInputData(int value) { this.value = value; }
    
    public final int getValue() { return value; }
    
    @Override
    public boolean isPressed() { return value != 0; }

    @Override
    public boolean isReleased() { return value == 0; }
    
    @Override
    public String toString() { return super.toString() + " value: " + value; }
    
    @Override
    public final float getData() { return value; }
}
