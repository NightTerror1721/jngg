/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

/**
 *
 * @author Asus
 */
@FunctionalInterface
public interface CDPBuilderListener
{
    void notifyState(BuilderState state);
    
    public static final class BuilderState
    {
        CDPElementBuilder current;
        long bytes;
        
        BuilderState() {}
        
        public final CDPElementBuilder getCurrentElement() { return current; }
        public final long getBytesWritten() { return bytes; }
        
        public final String getCurrentPath() { return current.getAbsolutePath(); }
        public final String getCurrentName() { return current.name; }
    }
}
