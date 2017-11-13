/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

/**
 *
 * @author mpasc
 */
public abstract class InputId
{
    public static final int INVALID_TYPE = 0;
    public static final int MOUSE_TYPE = 1;
    public static final int KEYBOARD_TYPE = 2;
    public static final int TYPED_KEYBOARD_TYPE = 3;
    public static final int MOUSE_CATCHER_TYPE = 4;
    public static final int MOUSE_WHEEL_TYPE = 5;
    
    protected final int code;
    private final int type;
    
    protected InputId(int type, int code)
    {
        this.code = code;
        this.type = type;
    }
    
    public final boolean isInvalid() { return type == INVALID_TYPE; }
    
    public final int getCode() { return code; }
    
    public final int getInputType() { return type; }
    
    public abstract String getName();
    
    @Override
    public final String toString() { return "[code: " + getCode() + "] " + innerToString(); }
    protected abstract String innerToString();
    
    public static final InputId invalid(int code)
    {
        final int negCode = code > 0 ? -code : code == 0 ? -1 : code;
        return new InputId(negCode, INVALID_TYPE)
        {
            @Override
            public String getName() { return "INVALID"; }
            
            @Override
            protected String innerToString() { return getName(); }
        };
    }
    
    public final boolean equals(InputId other)
    {
        return type == other.type && code == other.code;
    }
    
    @Override
    public final boolean equals(Object o)
    {
        if(o instanceof InputId)
        {
            InputId id = (InputId) o;
            return type == id.type && code == id.code;
        }
        return false;
    }

    @Override
    public final int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + this.type;
        return (this.code & 0xff) | (hash << 8);
    }
}
