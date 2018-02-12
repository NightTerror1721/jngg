/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
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
        
        Display display = Display.create("Test", DisplayMode.getDisplayMode(640, 480));
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
                Stroke s = g.getStroke();
                g.setStroke(new BasicStroke(4));
                g.setColor(Color.RED);
                g.drawRect(0, 0, display.getWidth(), display.getHeight());
                g.setStroke(s);
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
