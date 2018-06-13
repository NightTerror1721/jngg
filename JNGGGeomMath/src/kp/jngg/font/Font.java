/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.font;

import java.awt.Color;
import java.awt.Graphics2D;
import kp.jngg.sprite.Sprite;

/**
 *
 * @author Marc
 */
public interface Font
{
    public void print(Graphics2D g, String text, int x, int y);
    public void printCentre(Graphics2D g, String text, int x, int y);
    public void printFinal(Graphics2D g, String text, int x, int y);
    public void setColor(Color color);
    public void setDimensions(int size);
    
    public Sprite generateImage(String text);
    
    public static Font getNativeFont(String name, int style, int size, Color color) { return new DefaultFont(new java.awt.Font(name, style, size), size, color); }
    public static Font getNativeFont(String name, int size, Color color) { return getNativeFont(name, 0, size, color); }
    public static Font getNativeFont(String name, int size) { return getNativeFont(name, 0, size, Color.BLACK); }
    public static Font getNativeFont(String name, Color color) { return getNativeFont(name, 0, 12, color); }
}
