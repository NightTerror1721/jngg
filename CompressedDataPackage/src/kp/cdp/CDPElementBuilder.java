/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.IOException;
import java.io.OutputStream;
import kp.cdp.CDPBuilder.NativeFileWriter;

/**
 *
 * @author mpasc
 */
public abstract class CDPElementBuilder
{
    final byte type;
    final CDPFolderBuilder parent;
    final String name;
    private String path;
    
    CDPElementBuilder(byte type, CDPFolderBuilder parent, String name)
    {
        if(name == null)
            throw new NullPointerException();
        if(parent == null)
            throw new NullPointerException();
        if(name.isEmpty())
            throw new IllegalArgumentException("Invalid empty element name");
        this.type = type;
        this.parent = parent;
        this.name = name;
    }
    CDPElementBuilder()
    {
        this.type = CDPElement.FOLDER_TYPE;
        this.parent = null;
        this.name = "";
    }
    
    public final String getName() { return name; }
    
    public final CDPFolderBuilder getParent() { return parent; }
    
    public final boolean isFile() { return type == CDPElement.FILE_TYPE; }
    public final boolean isFolder() { return type == CDPElement.FOLDER_TYPE; }
    
    abstract long headerLen();
    abstract void writeHeader(NativeFileWriter accessor) throws IOException;
    abstract void writeFiles(NativeFileWriter accessor) throws IOException;
    
    final int nameLen()
    {
        return name.getBytes().length;
    }
    
    static final void writeName(OutputStream os, String name) throws IOException
    {
        byte[] b = name.getBytes();
        IOUtils.writeInt(os, b.length);
        IOUtils.write(os, b);
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
