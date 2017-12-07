/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;
import javax.swing.JDialog;
import javax.swing.JFrame;
import kp.jngg.input.Input;
import kp.jngg.input.InputDispatcher;
import kp.jngg.input.InputListener;
import kp.jngg.input.InputMaskDispatcher;

/**
 *
 * @author mpasc
 */
public abstract class Display
{
    protected final InputDispatcher inputs = new InputDispatcher();
    protected final DebugInfo debug = new DebugInfo(this);
    protected GameLoop mainLoop = DEFAULT_GAME_LOOP;
    
    private final LinkedList<Callback> STOP_CALLBACKS = new LinkedList<>();
    private volatile boolean work = false;
    
    
    
    public static final Display create(JFrame window, DisplayMode displayMode)
    {
        return new WindowDisplay(window, displayMode);
    }
    
    public static final Display create(JDialog window, DisplayMode displayMode)
    {
        return new WindowDisplay(window, displayMode);
    }
    
    public static final Display create(String title, DisplayMode displayMode) { return create(new JFrame(), displayMode); }
    
    
    
    
    public final void addInputListener(InputListener listener)
    {
        inputs.addListener(listener);
    }
    public final void removeInputListener(InputListener listener)
    {
        inputs.removeListener(listener);
    }
    
    public final InputMaskDispatcher createInputMaskDispatcher()
    {
        return new InputMaskDispatcher(inputs);
    }
    
    public final DebugInfo getDebugInfo() { return debug; }
    
    public final void setGameLoop(GameLoop loop)
    {
        if(loop == null)
            throw new NullPointerException();
        this.mainLoop = loop;
    }
    
    
    public abstract void addInput(Input input);
    public abstract void removeInput(Input input);
    
    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getRefreshRate();
    
    public abstract void setFullscreen(boolean flag);
    public abstract boolean isFullscreenEnabled();
    
    public abstract void setDisplayMode(DisplayMode mode);
    public abstract DisplayMode getDisplayMode();
    
    public abstract double getLastDeltaTime();
    
    public abstract int getCurrentFpsPerSecond();
    public abstract double getExactCurrentFpsPerSecond();
    
    public abstract void setUpdateFramerate(int fps);
    public abstract int getCurrentUpdateFramerate();
    public abstract double getExactCurrentUpdateFramerate();
    
    protected abstract void stopLoop();
    protected abstract void loop();
    
    
    public final void start()
    {
        if(work || mainLoop == null)
            return;
        work = true;
        loop();
        abort();
    }
    public final void stop()
    {
        if(work)
            work = false;
        else
        {
            work = true;
            abort();
        }
    }
    public final void abort()
    {
        stop();
        stopLoop();
        
        ListIterator<Callback> it = STOP_CALLBACKS.listIterator();
        while(it.hasNext())
        {
            Callback callback = it.next();
            callback.function.run();
            if(callback.destroy)
                it.remove();
        }
    }
    public final boolean isRunning() { return work; }
    
    public final Dimension getDimension() { return new Dimension(getWidth(), getHeight()); }
    public final Rectangle getArea() { return new Rectangle(getX(), getY(), getWidth(), getHeight()); }
    
    public final void abort(int code)
    {
        abort();
        System.exit(code);
    }
    
    public final void addStopCallbackFunction(Runnable function) { addStopCallbackFunction(false, function); }
    public final void addStopCallbackFunction(boolean oneUse, Runnable function)
    {
        Callback callback = new Callback(function, oneUse);
        STOP_CALLBACKS.add(callback);
    }
    public final void removeStopCallbackFunction(Runnable function)
    {
        Callback callback = new Callback(function, true);
        STOP_CALLBACKS.remove(callback);
    }
    
    public final void insertRuntimeStopCallback()
    {
        RUNTIME.addShutdownHook(new Thread(this::abort));
    }
    
    
    
    static {
        System.setProperty("sun.java2d.opengl","True");
    }
    
    private static final Runtime RUNTIME = Runtime.getRuntime();
    
    public static final long getMemoryUsed() { return RUNTIME.totalMemory() - RUNTIME.freeMemory(); }
    public static final float getMemoryUsedInKb() { return getMemoryUsed() / 1024f; }
    public static final float getMemoryUsedInMb() { return getMemoryUsed() / 1024f / 1024f; }
    
    public static final long getTotalMemory() { return RUNTIME.totalMemory(); }
    public static final float getTotalMemoryInKb() { return getTotalMemory() / 1024f; }
    public static final float getTotalMemoryInMb() { return getTotalMemory() / 1024f / 1024f; }
    
    private static final GameLoop DEFAULT_GAME_LOOP = new GameLoop()
    {
        @Override
        public void init() {}

        @Override
        public void draw(Graphics2D g) {}

        @Override
        public void update(double delta) {}
    };
    
    private static final class Callback
    {
        private final boolean destroy;
        private final Runnable function;
        
        private Callback(Runnable function, boolean destroy)
        {
            this.destroy = destroy;
            this.function = function;
        }
        
        @Override
        public final boolean equals(Object o)
        {
            return o instanceof Callback && function == ((Callback)o).function;
        }

        @Override
        public final int hashCode()
        {
            int hash = 7;
            hash = 89 * hash + Objects.hashCode(this.function);
            return hash;
        }
    }
}
