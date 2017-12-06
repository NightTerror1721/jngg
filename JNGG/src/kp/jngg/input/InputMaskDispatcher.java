/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.util.HashMap;

/**
 *
 * @author mpasc
 */
public final class InputMaskDispatcher
{
    private final InputDispatcher dispatcher;
    private final HashMap<InputMask, SimpleArrayList<InputId>> maskBinds;
    private final HashMap<InputId, SimpleArrayList<InputMask>> idBinds;
    private final SimpleArrayList<InputMaskListener> listeners;
    
    public InputMaskDispatcher(InputDispatcher dispatcher)
    {
        if(dispatcher == null)
            throw new NullPointerException();
        this.dispatcher = dispatcher;
        this.maskBinds = new HashMap<>();
        this.idBinds = new HashMap<>();
        this.listeners = new SimpleArrayList<>(new InputMaskListener[4]);
        initInnerListeners();
    }
    
    public final void addListener(InputMaskListener listener)
    {
        if(listener == null)
            throw new NullPointerException();
        listeners.add(listener);
    }
    
    public final void removeListener(InputMaskListener listener)
    {
        if(listener == null)
            throw new NullPointerException();
        listeners.remove(listener);
    }
    
    
    public final void registerMask(InputMask mask)
    {
        if(mask == null)
            throw new NullPointerException();
        if(maskBinds.containsKey(mask))
            throw new IllegalArgumentException("This mask has already exists");
        maskBinds.put(mask,new SimpleArrayList<>(new InputId[2]));
    }
    public final void registerMasks(InputMask... masks)
    {
        for(InputMask mask : masks)
            registerMask(mask);
    }
    
    public final void unregisterMask(InputMask mask)
    {
        SimpleArrayList<InputId> ids = maskBinds.get(mask);
        if(ids == null)
            return;
        for(InputId id : ids)
        {
            SimpleArrayList<InputMask> masks = idBinds.get(id);
            if(masks == null)
                continue;
            masks.remove(mask);
        }
        ids.clear();
    }
    
    
    public final void assignInputIdToMask(InputId code, InputMask mask)
    {
        SimpleArrayList<InputId> ids = maskBinds.get(mask);
        if(ids == null)
            throw new IllegalArgumentException("This mask does not exists");
        SimpleArrayList<InputMask> masks = idBinds.get(code);
        if(masks == null)
            idBinds.put(code, masks = new SimpleArrayList<>(new InputMask[2]));
        ids.add(code);
        masks.add(mask);
    }
    
    public final void unassignInputIdToMask(InputId code, InputMask mask)
    {
        SimpleArrayList<InputId> ids = maskBinds.get(mask);
        if(ids == null)
            throw new IllegalArgumentException("This mask does not exists");
        SimpleArrayList<InputMask> masks = idBinds.get(code);
        if(masks == null)
            throw new IllegalArgumentException("This code does not exists");
        ids.remove(code);
        masks.remove(mask);
        if(masks.isEmpty())
            idBinds.remove(code);
    }
    
    
    
    private void initInnerListeners()
    {
        dispatcher.addListener((InputEvent event) -> {
            SimpleArrayList<InputMask> masks = idBinds.get(event.id);
            if(masks != null && !masks.isEmpty())
            {
                for(InputMask mask : masks)
                {
                    event.mask = mask;
                    for(InputMaskListener list : listeners)
                        list.dispatchMaskEvent(event);
                }
                event.mask = null;
            }
            else for(InputMaskListener list : listeners)
                list.dispatchMaskEvent(event);
        });
    }
}
