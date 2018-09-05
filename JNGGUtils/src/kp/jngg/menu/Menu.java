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
    private MenuOption back;
    private boolean showTitle;
    private boolean centeredOptions;
    
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
    
    public final void setBack(MenuOption back) { this.back = back; }
    public final MenuOption getBack() { return back; }
    
    public final void setSelectedIndex(int index)
    {
        this.selected = index;
        updateFirstOption();
    }
    public final int getSelectedIndex() { return selected; }
    public final <O extends MenuOption> O getSelectedOption()
    {
        return selected < 0 || selected >= options.size() ? null : (O) options.get(selected);
    }
    public final int getOptionCount() { return options.size(); }
    
    public final void setTitlePosition(int position) { this.titlePos = position; }
    public final int getTitlePosition() { return titlePos; }
    
    public final void setFirstOptionPosition(int position) { this.firstPos = position; }
    public final int getFirstOptionPosition(int position) { return firstPos; }
    
    public final void setPrintCenteredOptions(boolean flag) { this.centeredOptions = flag; }
    public final boolean isPrintCenteredOptionsEnabled() { return centeredOptions; }
    
    public final void copyTitleAndOptionPositions(Menu menu)
    {
        this.titlePos = menu.titlePos;
        this.firstPos = menu.firstPos;
    }
    
    public final void setCustomOnAction(CustomEventAction eventAction) { this.onAction = eventAction; }
    public final void setCustomOnBack(CustomEventAction eventAction) { this.onBack = eventAction; }
    public final void setCustomOnUp(CustomEventAction eventAction) { this.onUp = eventAction; }
    public final void setCustomOnDown(CustomEventAction eventAction) { this.onDown = eventAction; }
    public final void setCustomOnDefaultEvent(CustomDefaultEventAction eventAction) { this.onDefaultEvent = eventAction; }
    
    @Override
    public void update(double delta, MenuController controller)
    {
        this.space = (int) controller.getOptionSeparation();
        if(centeredOptions)
        {
            if(showTitle)
                max = (int) ((controller.getHeight() - (titlePos + controller.getTitleSize() / 2 + space)) / (controller.getNormalSize() + space));
            else max = (int) ((controller.getHeight() - controller.getPositionY()) / (controller.getNormalSize() + space));
        }
        else max = (int) ((controller.getHeight() - firstPos) / (controller.getNormalSize() + space));
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
            font.setColor(controller.getTitleColor());
            font.setDimensions(titleSize);
            font.printCentre(g, getOptionTitle(), center, titlePos);
        }
        
        int len = options.size();
        int nsize = controller.getNormalSize();
        Color ncolor = controller.getNormalColor();
        //len = len > max ? max : len;
        if(centeredOptions)
        {
            MenuOption[] array = new MenuOption[len];
            int showLen = 0;
            int sel = 0;
            for(int idx = first; idx < len && showLen < max; idx++)
            {
                MenuOption option = options.get(idx);
                if(!option.isLocked())
                {
                    if(idx == selected)
                        sel = showLen;
                    array[showLen++] = option;
                }
            }
            
            int newFirstPos = firstPos - (((showLen - 1) * nsize) + ((showLen - 1) * space)) / 2;
            for(int i=0;i<showLen;i++)
            {
                if(i == sel)
                {
                    font.setColor(controller.getSelectedColor());
                    font.setDimensions(controller.getSelectedSize());
                }
                else
                {
                    font.setColor(ncolor);
                    font.setDimensions(nsize);
                }
                font.printCentre(g, array[i].getOptionTitle(), center, newFirstPos + ((i * nsize) + (i * space)));
            }
        }
        else
        {
            for(int idx = first, i = 0; idx < len && i < max; idx++)
            {
                MenuOption option = options.get(idx);
                if(option.isLocked())
                    continue;
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
                font.printCentre(g, option.getOptionTitle(), center, firstPos + ((i * nsize) + (i * space)));
                i++;
            }
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
            first = 0;
        else
        {
            if(selected < first)
                first = selected;
            else if(selected >= first + max)
                first = selected - max + 1;
        }
        
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
        {
            if(back != null)
                controller.goTo(back);
            else controller.goToLast();
        }
    }
    
    @Override
    public void onUp(MenuController controller)
    {
        if((onUp == null || onUp.execute(controller)) && controller.isCurrentOption(this))
        {
            selected--;
            updateFirstOption();
        }
    }
    
    @Override
    public void onDown(MenuController controller)
    {
        if((onDown == null || onDown.execute(controller)) && controller.isCurrentOption(this))
        {
            selected++;
            updateFirstOption();
        }
    }
    
}
