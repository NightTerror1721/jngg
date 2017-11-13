/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import kp.jngg.input.MouseButton;
import kp.jngg.input.MouseWheel;
import kp.jngg.input.TypeKeyboard;

/**
 *
 * @author mpasc
 */
public final class Main
{
    public static void main(String[] args)
    {
        System.out.println(DisplayMode.toStringAllDisplayModes());
        
        Display display = Display.create(DisplayMode.getDisplayMode(640, 480));
        display.insertRuntimeStopCallback();
        display.setGameLoop(new GameLoop()
        {
            @Override
            public void init()
            {
                
            }

            @Override
            public void draw(Graphics2D g)
            {
                
            }

            @Override
            public void update(double delta)
            {
                
            }
        });
        
        display.addInput(new TypeKeyboard());
        display.addInput(new MouseButton());
        display.addInput(new MouseWheel());
        display.addInputListener(System.out::println);
        
        DebugInfo info = display.getDebugInfo();
        info.setEnabled(true);
        info.setColor(Color.GREEN);
        info.setShowMemory(true);
        info.setExactFps(true);
        info.setFont(new Font("arial", Font.BOLD, 12));
        
        display.start();
        
        display.stop();
    }
}
