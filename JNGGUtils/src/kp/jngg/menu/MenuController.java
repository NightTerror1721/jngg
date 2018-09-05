/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;
import kp.jngg.font.Font;
import kp.jngg.input.BindingObject;
import kp.jngg.input.InputEvent;
import kp.jngg.input.InputId;
import kp.jngg.input.InputMask;
import kp.jngg.math.Vector2;

/**
 *
 * @author Asus
 */
public class MenuController
{
    private double x = 0, y = 0, width = 1, height = 1;
    private Font font = Font.getNativeFont("arial", 12);
    private Color ctitle = Color.ORANGE, cnormal = Color.GRAY, cselected = Color.YELLOW;
    private int stitle = 36, snomal = 24, sselected = 28, sdescription = 12;
    private double optionSeparation = 40;
    
    private MenuOption root;
    private MenuOption current;
    private MenuOption last;
    
    private BindingObject actionInput;
    private BindingObject backInput;
    private BindingObject upInput, downInput, leftInput, rightInput;
    
    
    public final void setPosition(Vector2 position)
    {
        this.x = position.x;
        this.y = position.y;
    }
    public final void setPosition(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public final void setPositionX(double x) { this.x = x; }
    public final void setPositionY(double y) { this.y = y; }
    
    public final Vector2 getPosition() { return new Vector2(x, y); }
    public final double getPositionX() { return x; }
    public final double getPositionY() { return y; }
    
    public final void setSize(Vector2 size)
    {
        this.width = size.x;
        this.height = size.y;
    }
    public final void setSize(double width, double height)
    {
        this.width = width;
        this.height = height;
    }
    public final void setWidth(double width) { this.width = width; }
    public final void setHeight(double height) { this.height = height; }
    
    public final Vector2 getSize() { return new Vector2(width, height); }
    public final double getWidth() { return width; }
    public final double getHeight() { return height; }
    
    public final void setFont(Font font) { this.font = Objects.requireNonNull(font); }
    public final Font getFont() { return font; }
    
    public final void setTitleColor(Color color) { ctitle = Objects.requireNonNull(color); }
    public final void setNormalColor(Color color) { cnormal = Objects.requireNonNull(color); }
    public final void setSelectedColor(Color color) { cselected = Objects.requireNonNull(color); }
    
    public final Color getTitleColor() { return ctitle; }
    public final Color getNormalColor() { return cnormal; }
    public final Color getSelectedColor() { return cselected; }
    
    public final void setTitleSize(int size) { this.stitle = size < 1 ? 1 : size; }
    public final void setNormalSize(int size) { this.snomal = size < 1 ? 1 : size; }
    public final void setSelectedSize(int size) { this.sselected = size < 1 ? 1 : size; }
    public final void setDescriptionSize(int size) { this.sdescription = size < 1 ? 1 : size; }
    
    public final int getTitleSize() { return stitle; }
    public final int getNormalSize() { return snomal; }
    public final int getSelectedSize() { return sselected; }
    public final int getDescriptionSize() { return sdescription; }
    
    public final void setOptionSeparation(double distance) { this.optionSeparation = distance < 0 ? 0 : distance; }
    public final double getOptionSeparation() { return optionSeparation; }
    
    public final void setActionInputId(InputId id) { this.actionInput = id == null ? null : BindingObject.create(id); }
    public final void setBackInputId(InputId id) { this.backInput = id == null ? null : BindingObject.create(id); }
    public final void setUpInputId(InputId id) { this.upInput = id == null ? null : BindingObject.create(id); }
    public final void setDownInputId(InputId id) { this.downInput = id == null ? null : BindingObject.create(id); }
    public final void setLeftInputId(InputId id) { this.leftInput = id == null ? null : BindingObject.create(id); }
    public final void setRightInputId(InputId id) { this.rightInput = id == null ? null : BindingObject.create(id); }
    
    public final void setActionInputId(InputMask mask) { this.actionInput = mask == null ? null : BindingObject.create(mask); }
    public final void setBackInputId(InputMask mask) { this.backInput = mask == null ? null : BindingObject.create(mask); }
    public final void setUpInputId(InputMask mask) { this.upInput = mask == null ? null : BindingObject.create(mask); }
    public final void setDownInputId(InputMask mask) { this.downInput = mask == null ? null : BindingObject.create(mask); }
    public final void setLeftInputId(InputMask mask) { this.leftInput = mask == null ? null : BindingObject.create(mask); }
    public final void setRightInputId(InputMask mask) { this.rightInput = mask == null ? null : BindingObject.create(mask); }
    
    
    public final void setRoot(MenuOption root) { this.root = root; }
    public final MenuOption getRoot() { return root; }
    
    public final MenuOption getCurrentOption() { return current; }
    public final MenuOption getLastOption() { return last; }
    
    public final boolean isCurrentOption(MenuOption option) { return option == current; }
    
    public final void goTo(MenuOption newOption)
    {
        last = current;
        current = Objects.requireNonNull(newOption);
    }
    public final void goToRoot() { goTo(root); }
    public final void goToLast()
    {
        if(last != null && last != current)
            goTo(last);
    }
    
    public final void doAction()
    {
        if(current != null)
            current.onAction(this);
    }
    public final void doBack()
    {
        if(current != null)
            current.onBack(this);
    }
    public final void doUp()
    {
        if(current != null)
            current.onUp(this);
    }
    public final void doDown()
    {
        if(current != null)
            current.onDown(this);
    }
    public final void doLeft()
    {
        if(current != null)
            current.onLeft(this);
    }
    public final void doRight()
    {
        if(current != null)
            current.onRight(this);
    }
    
    
    public final void update(double delta)
    {
        if(current != null)
            current.update(delta, this);
    }
    
    public final void draw(Graphics2D g)
    {
        if(current != null)
            current.draw(g, this);
    }
    
    public final void dispatchEvent(InputEvent event)
    {
        if(current != null && event.isPressed())
        {
            if(actionInput != null && actionInput.check(event))
                current.onAction(this);
            else if(backInput != null && backInput.check(event))
                current.onBack(this);
            else if(upInput != null && upInput.check(event))
                current.onUp(this);
            else if(downInput != null && downInput.check(event))
                current.onDown(this);
            else if(leftInput != null && leftInput.check(event))
                current.onLeft(this);
            else if(rightInput != null && rightInput.check(event))
                current.onRight(this);
            else current.dispatchUnbindedEvent(event, this);
        }
    }
}
