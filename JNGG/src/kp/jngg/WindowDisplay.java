/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.TimeUnit;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import kp.jngg.input.Input;

/**
 *
 * @author Asus
 */
public class WindowDisplay extends Display
{
    private static final int BUFFER_STRATEGY_COUNT = 2;
    
    private final Window window;
    private final JPanel canvas;
    private final GraphicsDevice gdevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();;
    private DisplayMode mode;
    
    private double sleepTime;
    private double fps;
    
    private double lastTime;
    private double currentTime;
    private double fpsCount;
    private double fpsPerSecond;
    private double delta;
    private double pSleepTime;
    private double pLastTime;
    private double pDelta;
    private double pFpsCount;
    private double pFpsPerSecond;
    
    private int tempFps;
    private int x;
    private int y;
    private int width;
    private int height;
    private int pTempFps;
    
    private boolean fullscreen;
    private boolean flipfs;
    
    WindowDisplay(JFrame window, DisplayMode mode)
    {
        if(window == null)
            throw new NullPointerException();
        if(mode == null)
            throw new NullPointerException();
        this.window = window;
        
        window.setContentPane(canvas = new JPanel());
        window.setResizable(false);
        window.setExtendedState(JFrame.DO_NOTHING_ON_CLOSE);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        init(mode);
    }
    
    WindowDisplay(JDialog window, DisplayMode mode)
    {
        if(window == null)
            throw new NullPointerException();
        if(mode == null)
            throw new NullPointerException();
        this.window = window;
        
        window.setContentPane(canvas = new JPanel());
        window.setResizable(false);
        window.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        init(mode);
    }
    
    private void init(DisplayMode mode)
    {
        window.setIgnoreRepaint(true);
        //window.pack();
        
        fullscreen = flipfs = false;
        setDisplayMode(mode);
        
        window.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) { stop(); }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }
    
    @Override
    public final void setDisplayMode(DisplayMode displayMode)
    {
        if(displayMode == null)
            throw new NullPointerException();
        if(mode != null && mode.equals(displayMode))
            return;
        try
        {
            if(!isRunning())
            {
                setDisplayMode0(displayMode);
                return;
            }
            flipfs = true;
            if(fullscreen)
            {
                dispose();
                gdevice.setFullScreenWindow(null);
                setDisplayMode0(displayMode);
                gdevice.setFullScreenWindow(window);
                window.setVisible(true);
                if(gdevice.isDisplayChangeSupported())
                    gdevice.setDisplayMode(mode.nativeDisplayMode);
                createBufferStrategy();
                window.requestFocus();
            }
            setDisplayMode0(displayMode);
            if(gdevice.isDisplayChangeSupported())
                gdevice.setDisplayMode(mode.nativeDisplayMode);
            createBufferStrategy();
            window.requestFocus();
        }
        catch(Throwable th)
        {
            th.printStackTrace(System.err);
            System.exit(1);
        }
    }
    private void setDisplayMode0(DisplayMode displayMode)
    {
        mode = displayMode;
        updateDrawArea();
        fps = mode.getRefreshRate();
        sleepTime = 1000000000d / fps;
        canvas.setPreferredSize(new Dimension(width, height));
        window.setSize(width, height);
    }
    
    private void updateDrawArea()
    {
        if(fullscreen)
        {
            x = 0;
            y = 0;
            width = mode.getWidth();
            height = mode.getHeight();
        }
        else
        {
            Insets insets = window.getInsets();
            x = insets.left;
            y = insets.top;
            width = mode.getWidth()/* - (insets.right + insets.left)*/;
            height = mode.getHeight()/* - (insets.bottom + insets.top)*/;
        }
    }
    
    @Override
    public final void setFullscreen(boolean flag)
    {
        try
        {
            if(!isRunning())
            {
                fullscreen = flag;
                return;
            }
            flipfs = true;
            if(flag)
            {
                if(fullscreen)
                    return;
                dispose();
                setUndecoratedWindow(true);
                gdevice.setFullScreenWindow(window);
                window.setVisible(true);
                if(gdevice.isDisplayChangeSupported())
                    gdevice.setDisplayMode(mode.nativeDisplayMode);
                createBufferStrategy();
                window.requestFocus();
                fullscreen = true;
                return;
            }
            if(!fullscreen)
                return;
            dispose();
            setUndecoratedWindow(false);
            gdevice.setFullScreenWindow(null);
            window.setVisible(true);
            if(gdevice.isDisplayChangeSupported())
                gdevice.setDisplayMode(mode.nativeDisplayMode);
            createBufferStrategy();
            window.requestFocus();
            fullscreen = false;
        }
        catch(Throwable th)
        {
            th.printStackTrace(System.err);
            System.exit(1);
        }
    }
    private void setUndecoratedWindow(boolean flag)
    {
        if(window instanceof Frame)
            ((Frame) window).setUndecorated(flag);
        else if(window instanceof Dialog)
            ((Dialog) window).setUndecorated(flag);
    }
    
    private void createBufferStrategy()
    {
        while(true)
        {
            try
            {
                window.createBufferStrategy(BUFFER_STRATEGY_COUNT);
                return;
            }
            catch(IllegalStateException ex)
            {
                System.err.println("[WARNING] Strategy Buffers are not created. Trying again");
            }
        }
    }
    
    private void dispose()
    {
        if(window.isVisible() || window.isDisplayable())
            window.dispose();
    }
    
    
    
    @Override
    public final void addInput(Input input)
    {
        inputs.addInput(input);
        if(input instanceof MouseListener)
            window.addMouseListener((MouseListener) input);
        if(input instanceof MouseWheelListener)
            window.addMouseWheelListener((MouseWheelListener) input);
        if(input instanceof MouseMotionListener)
            window.addMouseMotionListener((MouseMotionListener) input);
        if(input instanceof KeyListener)
            window.addKeyListener((KeyListener) input);
    }
    @Override
    public final void removeInput(Input input)
    {
        inputs.removeInput(input);
        if(input instanceof MouseListener)
            window.removeMouseListener((MouseListener) input);
        if(input instanceof MouseWheelListener)
            window.removeMouseWheelListener((MouseWheelListener) input);
        if(input instanceof MouseMotionListener)
            window.removeMouseMotionListener((MouseMotionListener) input);
        if(input instanceof KeyListener)
            window.removeKeyListener((KeyListener) input);
    }
    
    @Override
    public final int getX() { return x; }
    
    @Override
    public final int getY() { return y; }

    @Override
    public final int getWidth() { return width; }

    @Override
    public final int getHeight() { return height; }

    @Override
    public final int getRefreshRate() { return (int) fps; }

    @Override
    public final boolean isFullscreenEnabled() { return fullscreen; }

    @Override
    public final DisplayMode getDisplayMode() { return mode; }

    @Override
    public final double getLastDeltaTime() { return delta; }

    @Override
    public final int getCurrentFpsPerSecond() { return (int) (fpsPerSecond + 0.5f); }

    @Override
    public final double getExactCurrentFpsPerSecond() { return fpsPerSecond; }

    @Override
    public final void setUpdateFramerate(int fps) { pSleepTime = 1000000000d / StrictMath.abs(fps); }

    @Override
    public final int getCurrentUpdateFramerate() { return (int) (pFpsPerSecond + 0.5f); }

    @Override
    public final double getExactCurrentUpdateFramerate() { return pFpsPerSecond; }

    @Override
    public final void stopLoop()
    {
        dispose();
        if(fullscreen)
            gdevice.setFullScreenWindow(null);
    }

    @Override
    protected final void loop()
    {
        window.pack();
        mainLoop.init();
        lastTime = System.nanoTime();
        pLastTime = lastTime;
        setFullscreen(!(fullscreen = !fullscreen));
        BufferStrategy buffer;
        
        while(isRunning())
        {
            if(flipfs)
            {
                lastTime = System.nanoTime();
                pLastTime = lastTime;
                currentTime = lastTime + 0.000000001;
                flipfs = false;
            }
            else currentTime = System.nanoTime();
            
            if(currentTime - lastTime >= sleepTime)
            {
                delta = (currentTime - lastTime) / 1000000000;
                lastTime = currentTime;
                tempFps++;
                fpsCount += delta;
                if(fpsCount >= 1d)
                {
                    fpsPerSecond = 1d / (fpsCount / tempFps);
                    tempFps = 0;
                    fpsCount = 0;
                }
                
                if(currentTime - pLastTime >= pSleepTime)
                {
                    pDelta = (currentTime - pLastTime) / 1000000000;
                    pLastTime = currentTime;
                    pTempFps++;
                    pFpsCount += pDelta;
                    if(pFpsCount >= 1d)
                    {
                        pFpsPerSecond = 1d / (pFpsCount / pTempFps);
                        pTempFps = 0;
                        pFpsCount = 0;
                    }
                    inputs.dispatch();
                    mainLoop.update(delta);
                }
                
                buffer = window.getBufferStrategy();
                do {
                    do {
                        Graphics2D g;
                        updateDrawArea();
                        if(fullscreen)
                            g = (Graphics2D) buffer.getDrawGraphics();
                        else
                        {
                            Graphics g1 = buffer.getDrawGraphics();
                            g = (Graphics2D) g1.create(x, y, width, height);
                        }
                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, window.getWidth(), window.getHeight());
                        mainLoop.draw(g);
                        debug.draw(g);
                        g.dispose();
                    } while(buffer.contentsRestored());
                    buffer.show();
                } while(buffer.contentsLost());
            }
            
            long nanos = System.nanoTime();
            nanos = (long) (sleepTime - (nanos - lastTime)) - 1000000;
            try { TimeUnit.NANOSECONDS.sleep(nanos); }
            catch(InterruptedException ex) { ex.printStackTrace(System.err); }
        }
    }
}
