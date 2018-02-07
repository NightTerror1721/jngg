/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Asus
 */
public abstract class CDPElement
{
    public static final byte FOLDER_TYPE = 1;
    public static final byte FILE_TYPE = 2;
    
    private final byte type;
    final String name;
    final CDPFolder parent;
    private String path;
    
    CDPElement(byte type, String name, CDPFolder parent)
    {
        if(name == null)
            throw new NullPointerException();
        this.name = name;
        this.type = type;
        this.parent = parent;
    }
    
    public final boolean isFile() { return type == FILE_TYPE; }
    public final boolean isFolder() { return type == FOLDER_TYPE; }
    
    public CDPFolder toFolder() { return (CDPFolder) this; }
    public CDPFile toFile() { return (CDPFile) this; }
    
    public final String getName() { return name; }
    
    public final CDPFolder getParentFolder() { return parent; }
    
    static String readName(InputStream is) throws IOException
    {
        int len = IOUtils.readInt(is);
        byte[] bname = new byte[len];
        IOUtils.read(is, bname);
        return new String(bname);
    }
    
    public final String getAbsolutePath()
    {
        if(path != null)
            return path;
        if(parent == null)
            return path = name;
        path = parent.getAbsolutePath();
        return path = path.isEmpty() ? name : (path + "/" + name);
    }
}
