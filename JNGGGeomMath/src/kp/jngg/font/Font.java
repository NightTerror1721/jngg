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
}
