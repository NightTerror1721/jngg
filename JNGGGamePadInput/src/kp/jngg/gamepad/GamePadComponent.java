/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kp.jngg.gamepad;

import net.java.games.input.Component;

/**
 *
 * @author Marc
 */
abstract class GamePadComponent
{
    final Component component;
    final GamePadInputId id;
    float lastPoll;
    
    private GamePadComponent(Component component, GamePadInputId id)
    {
        this.component = component;
        this.id = id;
        this.lastPoll = 0f;
    }
    
    public final Component getComponent() { return component; }
    public GamePadInputId getID() { return id; } 
    public abstract GamePadData getData();
    
    @Override
    public String toString()
    {
        return getID().toString();
    }
    
    static final class ButtonReference extends GamePadComponent
    {
        ButtonReference(Component component, GamePadInputId id)
        {
            super(component,id);
        }

        @Override
        public final GamePadData getData()
        {
            return new GamePadData(component.getPollData());
        }
    }
    
    static final class PovReference extends GamePadComponent
    {
        private final PovDirection dir;
        
        PovReference(Component component, GamePadInputId id, PovDirection dir)
        {
            super(component,id);
            if(dir == PovDirection.NONE)
                throw new IllegalArgumentException("Invalid POV direction");
            this.dir = dir;
        }
        
        @Override
        public final GamePadData getData()
        {
            return new GamePadData(PovDirection.cast(component.getPollData()) == dir);
        }
        
        @Override
        public final String toString()
        {
            return component.toString() + "direction = " + dir.name() + ";";
        }
    }
    
    private static float axisTolerance = 0.5f;
    public static final void setAxisTolerance(float value)
    {
        axisTolerance = value <= 0f ? 0.1f : value > 1f ? 1f : value;
    }
    
    
    private static final GamePadData NO_PRESSED = new GamePadData(0f);
    
    static final class AxisReference extends GamePadComponent
    {
        private final boolean positive;
        
        AxisReference(Component component, GamePadInputId id, boolean positive)
        {
            super(component,id);
            this.positive = positive;
        }

        @Override
        public final GamePadData getData()
        {
            float val = component.getPollData();
            if(positive)
                return val >= axisTolerance ? new GamePadData(val) : NO_PRESSED;
            return val <= -axisTolerance ? new GamePadData(val) : NO_PRESSED;
        }
        
        @Override
        public final String toString()
        {
            return component.toString() + "["+(positive?"+":"-")+"]";
        }
    }
}
