/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg;

import java.awt.GraphicsEnvironment;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Asus
 */
public final class DisplayMode
{
    final java.awt.DisplayMode nativeDisplayMode;
    
    private DisplayMode(java.awt.DisplayMode nativeDisplayMode)
    {
        this.nativeDisplayMode = nativeDisplayMode;
    }
    
    public final int getWidth() { return nativeDisplayMode.getWidth(); }
    public final int getHeight() { return nativeDisplayMode.getHeight(); }
    public final int getRefreshRate() { return nativeDisplayMode.getRefreshRate(); }
    public final int getBitDepth() { return nativeDisplayMode.getBitDepth(); }
    public final boolean equals(DisplayMode other) { return nativeDisplayMode.equals(other.nativeDisplayMode); }
    @Override
    public final boolean equals(Object o)
    {
        return o instanceof DisplayMode && nativeDisplayMode.equals(((DisplayMode)o).nativeDisplayMode);
    }
    @Override
    public final int hashCode()
    {
        return nativeDisplayMode.hashCode();
    }
    
    @Override
    public final String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[width=").append(nativeDisplayMode.getWidth())
                .append("; height=").append(nativeDisplayMode.getHeight())
                .append("; refreshRate=").append(nativeDisplayMode.getRefreshRate())
                .append("hz; bitDepth=").append(nativeDisplayMode.getBitDepth())
                .append("]");
        return sb.toString();
    }
    
    public static final DisplayMode getNativeDisplayMode() { return NATIVE_MODE; }
    public static final DisplayMode getDisplayMode(int width, int height, int fps, int bitDepth)
    {
        DisplayMode mode = new DisplayMode(new java.awt.DisplayMode(width, height, bitDepth, height));
        if(ALL_MODES.contains(mode))
            return mode;
        throw new IllegalArgumentException("Unsupported display: " + mode);
    }
    public static final DisplayMode getDisplayMode(int width, int height, int fps)
    {
        DisplayMode mode = null;
        for(DisplayMode dm : ALL_MODES)
        {
            if(dm.getWidth() == width && dm.getHeight() == height && fps == dm.getRefreshRate())
            {
                if(mode == null)
                    mode = dm;
                else if(mode.getBitDepth() < dm.getBitDepth())
                    mode = dm;
            }
        }
        if(mode == null)
            throw new IllegalArgumentException("Unsupported display: " + mode);
        return mode;
    }
    public static final DisplayMode getDisplayMode(int width, int height)
    {
        DisplayMode mode = null;
        for(DisplayMode dm : ALL_MODES)
        {
            if(dm.getWidth() == width && dm.getHeight() == height)
            {
                if(mode == null)
                    mode = dm;
                else if(mode.getRefreshRate() < dm.getRefreshRate())
                    mode = dm;
                else if(mode.getRefreshRate() == dm.getRefreshRate())
                {
                    if(mode.getBitDepth() < dm.getBitDepth())
                        mode = dm;
                }
            }
        }
        if(mode == null)
            throw new IllegalArgumentException("Unsupported display: " + mode);
        return mode;
    }
    
    public static final Set<DisplayMode> getAllDisplayModes()
    {
        return Collections.unmodifiableSet(ALL_MODES);
    }
    public static final String toStringAllDisplayModes()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Display modes found: ").append(ALL_MODES.size());
        ALL_MODES.stream().forEach((mode) -> {
            sb.append("\n\t").append(mode);
        });
        return sb.toString();
    }
    
    
    
    private static final Set<DisplayMode> ALL_MODES;
    private static final DisplayMode NATIVE_MODE;
    static {
        Set<DisplayMode> dmset = new TreeSet<>((dm1, dm2) -> {
            if(dm1.getWidth() != dm2.getWidth())
                return -Integer.compare(dm1.getWidth(), dm2.getWidth());
            if(dm1.getHeight() != dm2.getHeight())
                return -Integer.compare(dm1.getHeight(), dm2.getHeight());
            if(dm1.getRefreshRate()!= dm2.getRefreshRate())
                return -Integer.compare(dm1.getRefreshRate(), dm2.getRefreshRate());
            if(dm1.getBitDepth()!= dm2.getBitDepth())
                return -Integer.compare(dm1.getBitDepth(), dm2.getBitDepth());
            return 0;
        });
        
        DisplayMode nmode = new DisplayMode(null);
        DisplayMode disp;
        boolean first = true;
        for(java.awt.DisplayMode mode : GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDisplayModes())
        {
            dmset.add(disp = new DisplayMode(mode));
            if(first)
            {
                first = false;
                nmode = disp;
            }
            else
            {
                if(nmode.getWidth() <= disp.getWidth() &&
                    nmode.getHeight() <= disp.getHeight())
                nmode = disp;
            }
        }
        LinkedList<DisplayMode> most = new LinkedList<>();
        for(DisplayMode dm : dmset)
        {
            if(nmode.getWidth() == dm.getWidth() &&
                    nmode.getHeight() == dm.getHeight())
                most.add(dm);
        }
        Collections.sort(most, (dm1, dm2) -> {
            return -(Integer.compare(dm1.getRefreshRate(), dm2.getRefreshRate()));
        });
        nmode = most.getFirst();
        ALL_MODES = Collections.unmodifiableSet(new HashSet<>(dmset));
        NATIVE_MODE = nmode;
    }
}
