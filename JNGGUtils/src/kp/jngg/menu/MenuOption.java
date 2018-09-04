/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.menu;

import java.awt.Graphics2D;
import java.util.Objects;
import kp.jngg.input.InputEvent;

/**
 *
 * @author Asus
 */
public abstract class MenuOption
{
    private String title;
    private String desc;
    private boolean locked;
    
    protected MenuOption()
    {
        this.title = "";
        this.desc = "";
        this.locked = false;
    }
    
    public final void setOptionTitle(String title) { this.title = Objects.requireNonNull(title); }
    public final String getOptionTitle() { return title; }
    
    public final void setOptionDescription(String description) { this.desc = Objects.requireNonNull(description); }
    public final String getDescription() { return desc; }
    
    public final void setLocked(boolean flag) { this.locked = flag; }
    public final boolean isLocked() { return locked; }
    
    public void update(double delta, MenuController controller)
    {
        
    }
    
    public void draw(Graphics2D g, MenuController controller)
    {
        
    }
    
    public void dispatchUnbindedEvent(InputEvent event, MenuController controller)
    {
        
    }
    
    /*public void drawName(Graphics2D g, Font font, Color color, double x, double y, MenuTextMode mode)
    {
        Color old = g.getColor();
        g.setColor(color);
        switch(mode)
        {
            case LEFT: font.print(g, title, (int) x, (int) y); break;
            case CENTER: font.printCentre(g, title, (int) x, (int) y); break;
            case RIGHT: font.printFinal(g, title, (int) x, (int) y); break;
        }
        g.setColor(old);
    }*/
    
    //public void onSelect(MenuController controller) {}
    public void onAction(MenuController controller) {}
    public void onBack(MenuController controller) {}
    public void onUp(MenuController controller) {}
    public void onDown(MenuController controller) {}
    public void onLeft(MenuController controller) {}
    public void onRight(MenuController controller) {}
}
