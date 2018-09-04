/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.function.Consumer;
import kp.jngg.font.Font;
import kp.jngg.input.InputEvent;

/**
 *
 * @author Asus
 */
public class Menu extends MenuOption
{
    private final ArrayList<MenuOption> options = new ArrayList<>();
    private boolean showTitle;
    
    private CustomEventAction onAction;
    private CustomEventAction onBack;
    private CustomEventAction onUp;
    private CustomEventAction onDown;
    private CustomDefaultEventAction onDefaultEvent;
    
    private int selected;
    private int first;
    private int max;
    
    private int titlePos;
    private int firstPos;
    private int center;
    private int space;
    
    
    public final void addOption(MenuOption option)
    {
        if(option == null)
            throw new NullPointerException();
        options.add(option);
    }
    public final void addOption(int index, MenuOption option)
    {
        if(option == null)
            throw new NullPointerException();
        options.add(index, option);
    }
    
    public final void addSimpleAction(String title, String description, CustomEventAction action)
    {
        CustomizableMenuOption option = new CustomizableMenuOption();
        option.setOptionTitle(title);
        option.setOptionDescription(description);
        option.setCustomOnAction(action);
        
        addOption(option);
    }
    public final void addSimpleAction(String title, String description, int index, CustomEventAction action)
    {
        CustomizableMenuOption option = new CustomizableMenuOption();
        option.setOptionTitle(title);
        option.setOptionDescription(description);
        option.setCustomOnAction(action);
        
        addOption(index, option);
    }
    
    public final void addSimpleAction(String title, String description, Consumer<MenuController> action)
    {
        addSimpleAction(title, description, controller -> {
            action.accept(controller);
            return true;
        });
    }
    public final void addSimpleAction(String title, String description, int index, Consumer<MenuController> action)
    {
        addSimpleAction(title, description, index, controller -> {
            action.accept(controller);
            return true;
        });
    }
    
    public final void setShowTitle(boolean flag) { this.showTitle = flag; }
    public final boolean isShowTitleEnabled() { return showTitle; }
    
    public final void setCustomOnAction(CustomEventAction eventAction) { this.onAction = eventAction; }
    public final void setCustomOnBack(CustomEventAction eventAction) { this.onBack = eventAction; }
    public final void setCustomOnUp(CustomEventAction eventAction) { this.onUp = eventAction; }
    public final void setCustomOnDown(CustomEventAction eventAction) { this.onDown = eventAction; }
    public final void setCustomOnDefaultEvent(CustomDefaultEventAction eventAction) { this.onDefaultEvent = eventAction; }
    
    @Override
    public void update(double delta, MenuController controller)
    {
        this.space = (int) controller.getOptionSeparation();
        if(showTitle)
        {
            titlePos = (int) (controller.getPositionY() + space);
            firstPos = titlePos + titlePos / 2;
        }
        else
        {
            titlePos = -1;
            firstPos = (int) (controller.getPositionY() + space);
        }
        max = (int) ((controller.getNormalSize() + space) / (controller.getHeight() - controller.getPositionY()));
        center = (int) (controller.getPositionX() + controller.getWidth() / 2d);
        updateFirstOption();
    }
    
    @Override
    public void draw(Graphics2D g, MenuController controller)
    {
        Font font = controller.getFont();
        
        if(showTitle)
        {
            int titleSize = controller.getTitleSize();
            font.setDimensions(titleSize);
            font.printCentre(g, getOptionTitle(), center, firstPos);
        }
        
        int len = options.size();
        int nsize = controller.getNormalSize();
        Color ncolor = controller.getNormalColor();
        len = len > max ? max : len;
        for(int i=0;i<len && first + i < options.size();i++)
        {
            int idx = i + first;
            MenuOption option = options.get(idx);
            if(option.isLocked())
            {
                i--;
                continue;
            }
            if(selected == idx)
            {
                font.setColor(controller.getSelectedColor());
                font.setDimensions(controller.getSelectedSize());
            }
            else
            {
                font.setColor(ncolor);
                font.setDimensions(nsize);
            }
            font.printCentre(g, option.getOptionTitle(), center, (i * nsize) + ((i + 1) * space));
        }
        
        String desc = options.get(selected).getDescription();
        if(desc != null && !desc.isEmpty())
        {
            nsize = controller.getDescriptionSize();
            font.setColor(controller.getSelectedColor());
            font.setDimensions(nsize);
            font.printCentre(g, desc, center, (int) (controller.getHeight() - nsize - 5));
        }
    }
    
    @Override
    public void dispatchUnbindedEvent(InputEvent event, MenuController controller)
    {
        if(onDefaultEvent != null)
            onDefaultEvent.execute(event, controller);
    }
    
    private void updateFirstOption()
    {
        int count = options.size();
        if(count <= max)
        {
            first = 0;
            return;
        }
        
        if(selected < first)
            first = selected;
        else if(selected >= first + max)
            first = selected - max;
        
        if(selected < 0)
        {
            selected = count - 1;
            first = count - max;
        }
        else if(selected >= count)
        {
            selected = first = 0;
        }
    }
    
    
    @Override
    public void onAction(MenuController controller)
    {
        if(onAction == null || onAction.execute(controller))
        {
            if(controller.isCurrentOption(this))
            {
                if(selected >= 0 && selected < options.size())
                    options.get(selected).onAction(controller);
            }
            else controller.goTo(this);
        }
    }
    
    @Override
    public void onBack(MenuController controller)
    {
        if((onBack == null || onBack.execute(controller)) && controller.isCurrentOption(this))
            controller.goToLast();
    }
    
    @Override
    public void onUp(MenuController controller)
    {
        if((onUp == null || onUp.execute(controller)) && controller.isCurrentOption(this))
        {
            first--;
            updateFirstOption();
        }
    }
    
    @Override
    public void onDown(MenuController controller)
    {
        if((onDown == null || onDown.execute(controller)) && controller.isCurrentOption(this))
        {
            first++;
            updateFirstOption();
        }
    }
    
}
