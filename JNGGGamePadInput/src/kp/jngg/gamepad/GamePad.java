/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kp.jngg.gamepad;

import java.util.Collection;
import java.util.LinkedList;
import kp.jngg.gamepad.GamePadComponent.AxisReference;
import kp.jngg.gamepad.GamePadComponent.ButtonReference;
import kp.jngg.gamepad.GamePadComponent.PovReference;
import kp.jngg.input.Input;
import kp.jngg.input.InputEvent;
import kp.jngg.input.InputListener;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Rumbler;

/**
 *
 * @author Marc
 */
public final class GamePad implements Input
{
    private final GamePadComponent[] components;
    private final Rumbler0[] rumblers;
    private final int port;
    private final Controller device;
    
    GamePad(Controller controller, int port)
    {
        this.port = port;
        device = controller;
        if(!isGamePad(controller))
        {
            components = null;
            rumblers = null;
            return;
        }
        System.out.println("Game controller: " + controller.getName() + ", " +  controller.getType());
        rumblers = findRumblers();
        LinkedList<GamePadComponent> cmps = new LinkedList<>();
        findAxis(cmps);
        findPov(cmps);
        findButtons(cmps);
        components = cmps.toArray(new GamePadComponent[cmps.size()]);
    }
    
    static final boolean isGamePad(Controller controller)
    {
        return controller.getType() == Controller.Type.GAMEPAD ||
                controller.getType() == Controller.Type.STICK;
    }
    
    private void findAxis(LinkedList<GamePadComponent> cmps)
    {
        GamePadComponent gpc;
        int count = 0;
        System.out.println("Find Axes...");
        for(Component comp : device.getComponents())
        {
            if(!isAxis(comp)) continue;
            gpc = new AxisReference(comp,
                GamePadInputId.createAxisID(port,comp.getIdentifier(),true),true);
            cmps.add(gpc);
            gpc = new AxisReference(comp,
                GamePadInputId.createAxisID(port,comp.getIdentifier(),false),false);
            cmps.add(gpc);
            count++;
            //printFound(id);
        }
        System.out.println("Found " + count + " Axes;");
    }
    
    private boolean isAxis(Component c)
    {
        return c.getIdentifier().getClass().equals(Component.Identifier.Axis.class) &&
            !c.getIdentifier().equals(Component.Identifier.Axis.POV);
    }
    
    private void findPov(LinkedList<GamePadComponent> cmps)
    {
        System.out.println("Find POV...");
        GamePadComponent gpc;
        for(Component comp : device.getComponents())
            if(comp.getIdentifier().equals(Component.Identifier.Axis.POV))
            {
                gpc = new PovReference(comp,GamePadInputId.createPovID(port,PovDirection.UP),
                        PovDirection.UP);
                cmps.add(gpc);
                gpc = new PovReference(comp,GamePadInputId.createPovID(port,PovDirection.UP_RIGHT),
                        PovDirection.UP_RIGHT);
                cmps.add(gpc);
                gpc = new PovReference(comp,GamePadInputId.createPovID(port,PovDirection.RIGHT),
                        PovDirection.RIGHT);
                cmps.add(gpc);
                gpc = new PovReference(comp,GamePadInputId.createPovID(port,PovDirection.DOWN_RIGHT),
                        PovDirection.DOWN_RIGHT);
                cmps.add(gpc);
                gpc = new PovReference(comp,GamePadInputId.createPovID(port,PovDirection.DOWN),
                        PovDirection.DOWN);
                cmps.add(gpc);
                gpc = new PovReference(comp,GamePadInputId.createPovID(port,PovDirection.DOWN_LEFT),
                        PovDirection.DOWN_LEFT);
                cmps.add(gpc);
                gpc = new PovReference(comp,GamePadInputId.createPovID(port,PovDirection.LEFT),
                        PovDirection.LEFT);
                cmps.add(gpc);
                gpc = new PovReference(comp,GamePadInputId.createPovID(port,PovDirection.UP_LEFT),
                        PovDirection.UP_LEFT);
                cmps.add(gpc);
                return;
            }
        System.out.println("No POV found;");
    }
    
    private void findButtons(LinkedList<GamePadComponent> cmps)
    {
        GamePadComponent gpc;
        System.out.println("Find Buttons...");
        int count = 0;
        for(Component comp : device.getComponents())
        {
            if(!isButton(comp)) continue;
            gpc = new ButtonReference(comp,GamePadInputId.createButtonID(port,comp.getIdentifier()));
            cmps.add(gpc);
            count++;
        }
        System.out.println("Found " + count + " Buttons;");
    }
    
    private boolean isButton(Component c)
    {
        if(!c.isAnalog() && !c.isRelative())
            return c.getIdentifier().getClass().equals(Component.Identifier.Button.class);
        return false;
    }
    
    
    private Rumbler0[] findRumblers()
    {
        System.out.println("Find Rumblers...");
        Rumbler[] r = device.getRumblers();
        if(r.length > 0)
        {
            System.out.println("Found " + r.length + " Rumblers;");
            Rumbler0[] rumbs = new Rumbler0[r.length];
            for(int i=0;i<rumbs.length;i++)
                rumbs[i] = new Rumbler0(r[i]);
            return rumbs;
        }
        System.out.println("No Rumblers found;");
        return null;
    }
    
    public final void rumble(float intensity)
    {
        for(Rumbler0 r : rumblers)
            r.rumble(intensity);
    }
    public final void stopRumble()
    {
        for(Rumbler0 r : rumblers)
            r.stop();
    }
    public final boolean isRumbling()
    {
        for(Rumbler0 r : rumblers)
            if(r.isActive())
                return true;
        return false;
    }
    
    public final boolean poll()
    {
        return device.poll();
    }

    public final int getType()
    {
        return GamePadInputId.INPUT_TYPE;
    }

    public final String getName()
    {
        return device.getName();
    }

    @Override
    public void dispatchEvents(Collection<InputListener> listeners)
    {
        device.poll();
        InputEvent event = new InputEvent();
        for(GamePadComponent comp : components)
        {
            GamePadData data = comp.getData();
            if(data.value != comp.lastPoll)
            {
                event.setEventProperties(comp.id, data);
                for(InputListener listener : listeners)
                    listener.dispatchEvent(event);
                comp.lastPoll = data.value;
            }
        }
    }
    
    private final class Rumbler0
    {
        private final Rumbler rumbler;
        private boolean active;
        
        public Rumbler0(Rumbler rumbler)
        {
            this.rumbler = rumbler;
            active = false;
        }
        
        public void rumble(float intensity)
        {
            rumbler.rumble(Math.abs(intensity));
            active = intensity != 0f;
        }
        public void stop()
        {
            rumble(0f);
        }
        public boolean isActive() { return active; }
    }
}
