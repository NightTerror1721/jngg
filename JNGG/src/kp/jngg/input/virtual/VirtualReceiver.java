/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input.virtual;

import kp.jngg.input.InputEvent;

/**
 *
 * @author Asus
 */
public abstract class VirtualReceiver
{
    public abstract void dispatchEvent(InputEvent event);
}
