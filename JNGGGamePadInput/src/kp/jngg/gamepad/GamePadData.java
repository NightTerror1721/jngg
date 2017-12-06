/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.gamepad;

import kp.jngg.input.InputData;

/**
 *
 * @author mpasc
 */
public class GamePadData extends InputData
{
    final float value;
    
    GamePadData(float value) { this.value = value; }
    GamePadData(boolean value) { this.value = value ? 1f : 0f; }
    
    public final float getValue() { return value; }
    
    @Override
    public boolean isPressed() { return value != 0; }

    @Override
    public boolean isReleased() { return value == 0; }
    
    @Override
    public String toString() { return super.toString() + " value: " + value; }
}
