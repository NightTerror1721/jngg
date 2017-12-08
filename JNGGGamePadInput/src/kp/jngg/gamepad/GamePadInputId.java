/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kp.jngg.gamepad;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import kp.jngg.input.InputId;
import net.java.games.input.Component.Identifier;

/**
 *
 * @author Marc
 */
public final class GamePadInputId extends InputId
{
    public static final int INPUT_TYPE = 2001;
    
    static final int BUTTON = 0, AXIS = 1;
    
    final Identifier componentID;
    final String name;
    
    private GamePadInputId(int type, int port, int flag, Identifier id)
    {
        super(INPUT_TYPE, (port & 0x1F) | ((type & 0x1) << 5) |
                 ((flag & 0x7) << 6) | ((DIC.get(id) & 0xFFFF) << 9));
        this.componentID = id;
        this.name = "GAME_" + port + "_" + flag + "_" + componentID.getName();
    }
    
    @Override
    public final String getName()
    {
        return name;
    }
    
    public static InputId getId(int code) { return decode0(code); }
    
    static final GamePadInputId decode0(int code)
    {
        return new GamePadInputId(((code >>> 5) & 0x1),
            (code & 0x1F),
            ((code >>> 6) & 0x7),
            DIC_INV.get(((code >>> 9) & 0xFFFF)));
    }
    
    static final GamePadInputId createAxisID(int port, Identifier id, boolean positive)
    {
        return new GamePadInputId(AXIS,port,positive?0:1,id);
    }
    
    static final GamePadInputId createPovID(int port, PovDirection dir)
    {
        if(dir == PovDirection.NONE)
            throw new IllegalArgumentException("Expected valid pov direction");
        return new GamePadInputId(AXIS,port,dir.dirCode(),Identifier.Axis.POV);
    }
    
    static final GamePadInputId createButtonID(int port, Identifier id)
    {
        return new GamePadInputId(BUTTON,port,0,id);
    }
    
    
    
    
    
    
    private static final HashMap<Identifier,Integer> DIC = new HashMap<>();
    private static final HashMap<Integer,Identifier> DIC_INV = new HashMap<>();
    
    static final void checkAllPorts()
    {
        TreeSet<Integer> tree = new TreeSet<>();
        GamePadInputId id;
        for(int port=0;port<32;port++)
        {
            for(Identifier iden : DIC.keySet())
            {
                if(iden instanceof Identifier.Axis)
                {
                    if(iden.equals(Identifier.Axis.POV))
                    {
                        for(PovDirection dir : PovDirection.validDirs())
                        {
                            id = createPovID(port,dir);
                            if(tree.contains(id.getCode()))
                                throw new IllegalStateException("Repeat code: " + id.getCode());
                            tree.add(id.getCode());
                        }
                        continue;
                    }
                    id = createAxisID(port,iden,true);
                    if(tree.contains(id.getCode()))
                            throw new IllegalStateException("Repeat code: " + id.getCode());
                        tree.add(id.getCode());
                    id = createAxisID(port,iden,false);
                    if(tree.contains(id.getCode()))
                            throw new IllegalStateException("Repeat code: " + id.getCode());
                        tree.add(id.getCode());
                    continue;
                }
                id = createButtonID(port,iden);
                if(tree.contains(id.getCode()))
                            throw new IllegalStateException("Repeat code: " + id.getCode());
                        tree.add(id.getCode());
            }
        }
        System.out.println("All possible codes:");
        for(Integer i : tree)
            System.out.println(i);
    }
    
    static final String allCodes(int port)
    {
        StringBuilder sb = new StringBuilder();
        TreeSet<Integer> tree = new TreeSet<>();
        GamePadInputId id;
        for(Identifier iden : DIC.keySet())
        {
            if(iden instanceof Identifier.Axis)
            {
                if(iden.equals(Identifier.Axis.POV))
                {
                    for(PovDirection dir : PovDirection.validDirs())
                    {
                        id = createPovID(port,dir);
                        sb.append(id).append("\n");
                        if(tree.contains(id.getCode()))
                            throw new IllegalStateException("Repeat code: " + id.getCode());
                        tree.add(id.getCode());
                    }
                    continue;
                }
                id = createAxisID(port,iden,true);
                sb.append(id).append("\n");
                if(tree.contains(id.getCode()))
                        throw new IllegalStateException("Repeat code: " + id.getCode());
                    tree.add(id.getCode());
                id = createAxisID(port,iden,false);
                sb.append(id).append("\n");
                if(tree.contains(id.getCode()))
                        throw new IllegalStateException("Repeat code: " + id.getCode());
                    tree.add(id.getCode());
                continue;
            }
            id = createButtonID(port,iden);
            sb.append(id).append("\n");
            if(tree.contains(id.getCode()))
                        throw new IllegalStateException("Repeat code: " + id.getCode());
                    tree.add(id.getCode());
        }
        System.out.println(tree);
        return sb.substring(0,sb.length()-1);
    }
    
    static
    {
        TreeMap<String,Identifier> tree = new TreeMap<>();
        Identifier id;
        Object field;
        Class cl;
        try
        {
            cl = Identifier.Axis.class;
            for(Field f : cl.getDeclaredFields())
            {
                if((field = f.get(null)) instanceof Identifier.Axis)
                {
                    id = (Identifier.Axis) field;
                    if(id.equals(Identifier.Axis.POV))
                        tree.put("POV " + id.getName(),id);
                    else tree.put("AXIS " + id.getName(),id);
                }
            }
            cl = Identifier.Button.class;
            for(Field f : cl.getDeclaredFields())
            {
                if((field = f.get(null)) instanceof Identifier.Button)
                {
                    id = (Identifier.Button) field;
                    tree.put("BUTTON " + id.getName(),id);
                }
            }
            
            int count = 0;
            for(Identifier idef : tree.values())
            {
                //System.out.println(idef.getName());
                DIC_INV.put(count,idef);
                DIC.put(idef,count++);
            }
        }
        catch(IllegalAccessException | IllegalArgumentException | SecurityException ex)
        {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }

    @Override
    protected final String innerToString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(componentID.getName()).append(": ").append(getCode());
        return sb.toString();
    }
}
