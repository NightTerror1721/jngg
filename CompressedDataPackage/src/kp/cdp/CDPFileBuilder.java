/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import kp.cdp.CDPBuilder.NativeFileWriter;

/**
 *
 * @author mpasc
 */
public abstract class CDPFileBuilder extends CDPElementBuilder
{
    private long dataLen = -1;
    private long dataOffset = -1;
    
    private CDPFileBuilder(CDPFolderBuilder parent, String name)
    {
        super(CDPElement.FILE_TYPE, parent, name);
    }

    @Override
    final void writeFiles(NativeFileWriter accessor) throws IOException
    {
        accessor.notifyState(this);
        dataOffset = accessor.getPointerLocation();
        BuilderOutputStream bos = new BuilderOutputStream(accessor);
        InputStream is = getStream();
        byte[] buffer = new byte[8192];
        int len;
        while((len = is.read(buffer, 0, buffer.length)) > 0)
            bos.write(buffer, 0, len);
        bos.flush();
        bos.close();
        is.close();
        dataLen = bos.getBytesWritten();
    }
    abstract InputStream getStream() throws IOException;
    
    @Override
    final long headerLen() { return 21 + nameLen(); }
    
    @Override
    final void writeHeader(NativeFileWriter accessor) throws IOException
    {
        IOUtils.writeByte(accessor, type);
        writeName(accessor, name);
        IOUtils.writeLong(accessor, dataLen);
        IOUtils.writeLong(accessor, dataOffset);
    }
    
    static final CDPFileBuilder fromFile(CDPFolderBuilder parent, String name, File file)
    {
        return new NativeFileBuilder(parent, name, file);
    }
    
    static final CDPFileBuilder fromCDPFile(CDPFolderBuilder parent, String name, CDPFile file)
    {
        return new CDPReferenceBuilder(parent, name, file);
    }
    
    static final CDPFileBuilder fromStream(CDPFolderBuilder parent, String name, InputStream is)
    {
        return new StreamBuilder(parent, name, is);
    }
    
    
    private static final class NativeFileBuilder extends CDPFileBuilder
    {
        private final File file;
        
        private NativeFileBuilder(CDPFolderBuilder parent, String name, File file)
        {
            super(parent, name);
            if(file == null)
                throw new NullPointerException();
            if(!file.exists() || !file.isFile())
                throw new IllegalArgumentException("Expected valid file");
            this.file = file;
        }
        
        @Override
        final InputStream getStream() throws FileNotFoundException { return new FileInputStream(file); }
    }
    
    private static final class CDPReferenceBuilder extends CDPFileBuilder
    {
        private final CDPFile file;

        public CDPReferenceBuilder(CDPFolderBuilder parent, String name, CDPFile file)
        {
            super(parent, name);
            if(file == null)
                throw new NullPointerException();
            this.file = file;
        }
        
        @Override
        final InputStream getStream() throws IOException { return file.openStream(); }
    }
    
    private static final class StreamBuilder extends CDPFileBuilder
    {
        private final InputStream is;
        
        public StreamBuilder(CDPFolderBuilder parent, String name, InputStream is)
        {
            super(parent, name);
            if(is == null)
                throw new NullPointerException();
            this.is = is;
        }

        @Override
        final InputStream getStream() throws IOException { return is; }
    }
    
    private static final class BuilderOutputStream extends OutputStream
    {
        private final DeflaterOutputStream out;
        private final Deflater encoder;
        
        private BuilderOutputStream(NativeFileWriter nativeWriter)
        {
            encoder = new Deflater(nativeWriter.getCompressionLevel());
            out = new DeflaterOutputStream(nativeWriter, encoder);
        }
        
        public final long getBytesWritten() { return encoder.getBytesWritten(); }
        
        @Override
        public final void write(int b) throws IOException
        {
            out.write(b);
        }
        
        @Override
        public final void write(byte b[]) throws IOException { out.write(b, 0, b.length); }

        @Override
        public final void write(byte b[], int off, int len) throws IOException
        {
            out.write(b, off, len);
        }
        
        @Override
        public final void flush() throws IOException { out.flush(); }
        
        @Override
        public final void close() throws IOException { out.close(); }
    }
}
