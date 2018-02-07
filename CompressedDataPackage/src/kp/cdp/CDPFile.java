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
public final class CDPFile extends CDPElement
{
    final CDPFileSystem base;
    final long dataLen;
    final long dataOffset;
    
    CDPFile(String name, CDPFolder parent, CDPFileSystem base, long dataLen, long dataOffset)
    {
        super(FILE_TYPE, name, parent);
        if(base == null)
            throw new NullPointerException();
        this.base = base;
        this.dataLen = dataLen;
        this.dataOffset = dataOffset;
    }
    
    @Override
    public final CDPFile toFile() { return this; }
    
    @Override
    public final String toString() { return getAbsolutePath(); }
    
    public final InputStream openStream() throws IOException { return base.openFileStream(this); }
}
