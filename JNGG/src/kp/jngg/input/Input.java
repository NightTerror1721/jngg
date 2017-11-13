/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.util.Collection;

/**
 *
 * @author mpasc
 */
public interface Input
{
    abstract void dispatchEvents(Collection<InputListener> listeners);
}
