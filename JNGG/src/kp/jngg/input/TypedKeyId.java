/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

/**
 *
 * @author Asus
 */
public class TypedKeyId extends InputId
{
    public TypedKeyId(char code)
    {
        super(InputId.TYPED_KEYBOARD_TYPE, code);
    }

    @Override
    public String getName() { return Character.toString((char) code); }

    @Override
    protected String innerToString() { return "TYPED KEY " + getName(); }
    
    
    public static final InputId getId(char character)
    {
        return new TypedKeyId(character);
    }
}
