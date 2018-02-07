/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author Asus
 */
public final class CDPFileSystem
{
    static final int SIGNATURE = ('C' & 0xff) | (('D' & 0xff) << 8) | (('P' & 0xff) << 16);
    
    private final File nativeFile;
    private final CDPFolder root;
    
    private boolean reading = false;
    
    private CDPFileSystem(File nativeFile)
    {
        if(nativeFile == null)
            throw new NullPointerException();
        if(!nativeFile.exists() || !nativeFile.isFile())
            throw new IllegalArgumentException("Required a valid CDF file");
        this.nativeFile = nativeFile;
        root = new CDPFolder("", null);
    }
    
    private void open(int bufferLen) throws IOException
    {
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(nativeFile), bufferLen))
        {
            if(IOUtils.readInt24(bis) != SIGNATURE)
                throw new IOException("Invalid CDP file");
            root.readFolder(this, bis);
        }
    }
    
    public static final CDPFileSystem open(File file, int bufferLength) throws IOException
    {
        CDPFileSystem cdp = new CDPFileSystem(file);
        cdp.open(bufferLength);
        return cdp;
    }
    public static final CDPFileSystem open(File file) throws IOException { return open(file, 8192); }
    
    public final CDPFolder getRootFolder() { return root; }
    
    public final int getElementCount() { return root.getElementCount(); }
    
    public final CDPElement getElement(String path) { return root.getElement(path); }
    
    public final CDPFolder getFolder(String path) { return root.getFolder(path); }
    
    public final CDPFile getFile(String path) { return root.getFile(path); }
    
    public final boolean hasElement(String path) { return root.hasElement(path); }
    public final boolean hasFolder(String path) { return root.hasFolder(path); }
    public final boolean hasFile(String path) { return root.hasFile(path); }
    
    public final CDPElement[] listElements() { return root.listElements(); }
    
    public final CDPFolder[] listFolders() { return root.listFolders(); }
    
    public final CDPFile[] listFiles() { return root.listFiles(); }
    
    @Override
    public final String toString() { return root.toString(); }
    
    
    
    final InputStream openFileStream(CDPFile file) throws IOException
    {
        if(reading)
            throw new IOException("Already reading");
        if(file.base != this)
            throw new IllegalStateException("Invalid file in this file system");
        return new CDPFileAccessor(file.dataLen, file.dataOffset);
    }
    

    
    private final class CDPFileAccessor extends InflaterInputStream
    {
        public CDPFileAccessor(long bytesToRead, long offset) throws IOException
        {
            super(new NativeFileAccessor(bytesToRead, offset));
        }
    }
    
    private final class NativeFileAccessor extends InputStream
    {
        private final RandomAccessFile accessor;
        private long remaining;
        
        private NativeFileAccessor(long bytesToRead, long offset) throws IOException
        {
            accessor = new RandomAccessFile(nativeFile, "r");
            accessor.seek(offset);
            remaining = bytesToRead;
        }
        
        @Override
        public final int read() throws IOException
        {
            if(remaining <= 0)
                return -1;
            remaining--;
            return accessor.read();
        }
        
        @Override
        public final int read(byte b[], int off, int len) throws IOException
        {
            if(remaining <= 0)
                return -1;
            if(len > remaining)
                len = (int) remaining;
            remaining -= len;
            return accessor.read(b, off, len);
        }
        
        @Override
        public final long skip(long n) throws IOException
        {
            if(remaining <= 0)
                return -1;
            if(n > remaining)
                n = remaining;
            remaining -= n;
            long skipped = 0;
            while(n > 0)
            {
                int s = accessor.skipBytes((int) n);
                skipped += s;
                n -= s;
            }
            return skipped;
        }
        
        @Override
        public final void close() throws IOException
        {
            accessor.close();
            reading = false;
        }
    }
}
