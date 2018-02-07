/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.zip.Deflater;

/**
 *
 * @author mpasc
 */
public final class CDPBuilder extends CDPFolderBuilder
{
    public final void write(File dest, int compressionLevel, CDPBuilderListener listener) throws IOException
    {
        long initLen = headerLen() + 3;
        try(RandomAccessFile nativeAccessor = new RandomAccessFile(dest, "rw"))
        {
            NativeFileWriter accessor = new NativeFileWriter(nativeAccessor, compressionLevel, listener);
            nativeAccessor.seek(initLen);
            writeFiles(accessor);
            nativeAccessor.seek(0);
            IOUtils.writeInt24(accessor, CDPFileSystem.SIGNATURE);
            writeHeader(accessor);
        }
    }
    public final void write(File dest, int compressionLevel) throws IOException { write(dest, compressionLevel, null); }
    public final void write(File dest, CDPBuilderListener listener) throws IOException { write(dest, Deflater.DEFAULT_COMPRESSION, listener); }
    public final void write(File dest) throws IOException { write(dest, Deflater.DEFAULT_COMPRESSION, null); }
    
    static final class NativeFileWriter extends OutputStream
    {
        private final RandomAccessFile accessor;
        private final int compressionLevel;
        private final CDPBuilderListener listener;
        private final CDPBuilderListener.BuilderState state;
        
        private NativeFileWriter(RandomAccessFile accessor, int compressionLevel, CDPBuilderListener listener) throws IOException
        {
            this.accessor = accessor;
            this.compressionLevel = compressionLevel;
            this.listener = listener;
            this.state = listener == null ? null : new CDPBuilderListener.BuilderState();
        }
        
        public final long getPointerLocation() throws IOException { return accessor.getFilePointer(); }
        public final int getCompressionLevel() { return compressionLevel; }
        public final CDPBuilderListener.BuilderState geetState() { return state; }
        public final void notifyState(CDPElementBuilder element)
        {
            if(listener != null)
            {
                state.current = element;
                listener.notifyState(state);
            }
        }
        private void notifyState(int bytesLen)
        {
            if(listener != null)
            {
                state.bytes += bytesLen;
                listener.notifyState(state);
            }
        }
        
        @Override
        public final void write(int b) throws IOException
        {
            accessor.write(b);
            notifyState(1);
        }
        
        @Override
        public final void write(byte b[]) throws IOException{ accessor.write(b, 0, b.length); }

        @Override
        public final void write(byte b[], int off, int len) throws IOException
        {
            accessor.write(b, off, len);
            notifyState(len);
        }
        
        @Override
        public final void flush() throws IOException {}
        
        @Override
        public final void close() throws IOException {}
    }
}
