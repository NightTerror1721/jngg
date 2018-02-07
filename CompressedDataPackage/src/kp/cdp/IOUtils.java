/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Asus
 */
final class IOUtils
{
    public static final void write(OutputStream os, int abyte) throws IOException { os.write(abyte); }
    
    public static final void write(OutputStream os, byte[] buffer, int offset, int length) throws IOException
    {
        os.write(buffer, offset, length);
    }
    
    public static final void write(OutputStream os, byte[] buffer) throws IOException { os.write(buffer); }
    
    public static final void copy(byte[] sourceBuffer, int sourceOffset, byte[] destBuffer, int destOffset, int length)
    {
        System.arraycopy(sourceBuffer, sourceOffset, destBuffer, destOffset, length);
    }
    
    public static final void writeByte(byte[] buffer, int offset, int value)
    {
        buffer[offset] = (byte) (value & 0xff);
    }
    public static final void writeByte(byte[] buffer, int value) { writeByte(buffer, 0, value); }
    public static final void writeByte(OutputStream os, int value) throws IOException { os.write(value); }
    
    public static final void writeShort(byte[] buffer, int offset, int value)
    {
        buffer[offset] = (byte) (value & 0xff);
        buffer[offset + 1] = (byte) ((value >> 8) & 0xff);
    }
    public static final void writeShort(byte[] buffer, int value) { writeShort(buffer, 0, value); }
    public static final void writeShort(OutputStream os, int value) throws IOException
    {
        byte[] buffer = new byte[2];
        writeShort(buffer, 0, value);
        os.write(buffer, 0, buffer.length);
    }
    
    
    public static final void writeInt24(byte[] buffer, int offset, int value)
    {
        buffer[offset] = (byte) (value & 0xff);
        buffer[offset + 1] = (byte) ((value >> 8) & 0xff);
        buffer[offset + 2] = (byte) ((value >> 16) & 0xff);
    }
    public static final void writeInt24(byte[] buffer, int value) { writeInt24(buffer, 0, value); }
    public static final void writeInt24(OutputStream os, int value) throws IOException
    {
        byte[] buffer = new byte[3];
        writeInt24(buffer, 0, value);
        os.write(buffer, 0, buffer.length);
    }
    
    
    public static final void writeInt(byte[] buffer, int offset, int value)
    {
        buffer[offset] = (byte) (value & 0xff);
        buffer[offset + 1] = (byte) ((value >> 8) & 0xff);
        buffer[offset + 2] = (byte) ((value >> 16) & 0xff);
        buffer[offset + 3] = (byte) ((value >> 24) & 0xff);
    }
    public static final void writeInt(byte[] buffer, int value) { writeInt(buffer, 0, value); }
    public static final void writeInt(OutputStream os, int value) throws IOException
    {
        byte[] buffer = new byte[4];
        writeInt(buffer, 0, value);
        os.write(buffer, 0, buffer.length);
    }
    
    
    public static final void writeLong(byte[] buffer, int offset, long value)
    {
        buffer[offset] = (byte) (value & 0xff);
        buffer[offset + 1] = (byte) ((value >> 8) & 0xff);
        buffer[offset + 2] = (byte) ((value >> 16) & 0xff);
        buffer[offset + 3] = (byte) ((value >> 24) & 0xff);
        buffer[offset + 4] = (byte) ((value >> 32) & 0xff);
        buffer[offset + 5] = (byte) ((value >> 40) & 0xff);
        buffer[offset + 6] = (byte) ((value >> 48) & 0xff);
        buffer[offset + 7] = (byte) ((value >> 56) & 0xff);
    }
    public static final void writeLong(byte[] buffer, long value) { writeLong(buffer, 0, value); }
    public static final void writeLong(OutputStream os, long value) throws IOException
    {
        byte[] buffer = new byte[8];
        writeLong(buffer, 0, value);
        os.write(buffer, 0, buffer.length);
    }
    
    
    public static final void writeFloat(byte[] buffer, int offset, float value)
    {
        int ivalue = Float.floatToIntBits(value);
        buffer[offset] = (byte) (ivalue & 0xff);
        buffer[offset + 1] = (byte) ((ivalue >> 8) & 0xff);
        buffer[offset + 2] = (byte) ((ivalue >> 16) & 0xff);
        buffer[offset + 3] = (byte) ((ivalue >> 24) & 0xff);
    }
    public static final void writeFloat(byte[] buffer, float value) { writeFloat(buffer, 0, value); }
    public static final void writeFloat(OutputStream os, float value) throws IOException
    {
        byte[] buffer = new byte[4];
        writeFloat(buffer, 0, value);
        os.write(buffer, 0, buffer.length);
    }
    
    
    public static final void writeDouble(byte[] buffer, int offset, double value)
    {
        long ivalue = Double.doubleToLongBits(value);
        buffer[offset] = (byte) (ivalue & 0xff);
        buffer[offset + 1] = (byte) ((ivalue >> 8) & 0xff);
        buffer[offset + 2] = (byte) ((ivalue >> 16) & 0xff);
        buffer[offset + 3] = (byte) ((ivalue >> 24) & 0xff);
        buffer[offset + 4] = (byte) ((ivalue >> 32) & 0xff);
        buffer[offset + 5] = (byte) ((ivalue >> 40) & 0xff);
        buffer[offset + 6] = (byte) ((ivalue >> 48) & 0xff);
        buffer[offset + 7] = (byte) ((ivalue >> 56) & 0xff);
    }
    public static final void writeDouble(byte[] buffer, double value) { writeDouble(buffer, 0, value); }
    public static final void writeDouble(OutputStream os, double value) throws IOException
    {
        byte[] buffer = new byte[8];
        writeDouble(buffer, 0, value);
        os.write(buffer, 0, buffer.length);
    }
    
    
    
    
    
    public static final int read(InputStream is) throws IOException { return is.read(); }
    
    public static final int read(InputStream is, byte[] buffer, int offset, int length) throws IOException
    {
        return is.read(buffer, offset, length);
    }
    public static final int read(InputStream is, byte[] buffer) throws IOException { return is.read(buffer); }
    
    
    public static final byte readByte(byte[] buffer, int offset)
    {
        return buffer[offset];
    }
    public static final byte readByte(InputStream is) throws IOException
    {
        return (byte) is.read();
    }
    
    
    public static final short readShort(byte[] buffer, int offset)
    {
        return (short) (
                (buffer[offset] & 0xff) |
                ((buffer[offset + 1] & 0xff) << 8));
    }
    public static final short readShort(InputStream is) throws IOException
    {
        byte[] buffer = new byte[2];
        readFully(is, buffer, 0, buffer.length);
        return readShort(buffer, 0);
    }
    
    
    public static final int readUnsignedShort(byte[] buffer, int offset)
    {
        return (int) (
                (buffer[offset] & 0xff) |
                ((buffer[offset + 1] & 0xff) << 8));
    }
    public static final int readUnsignedShort(InputStream is) throws IOException
    {
        byte[] buffer = new byte[2];
        readFully(is, buffer, 0, buffer.length);
        return readUnsignedShort(buffer, 0);
    }
    
    
    public static final int readInt24(byte[] buffer, int offset)
    {
        return (int) (
                (buffer[offset] & 0xff) |
                ((buffer[offset + 1] & 0xff) << 8) |
                ((buffer[offset + 2] & 0xff) << 16));
    }
    public static final int readInt24(InputStream is) throws IOException
    {
        byte[] buffer = new byte[3];
        readFully(is, buffer, 0, buffer.length);
        return readInt24(buffer, 0);
    }
    
    
    public static final int readInt(byte[] buffer, int offset)
    {
        return (int) (
                (buffer[offset] & 0xff) |
                ((buffer[offset + 1] & 0xff) << 8) |
                ((buffer[offset + 2] & 0xff) << 16) |
                ((buffer[offset + 3] & 0xff) << 24));
    }
    public static final int readInt(InputStream is) throws IOException
    {
        byte[] buffer = new byte[4];
        readFully(is, buffer, 0, buffer.length);
        return readInt(buffer, 0);
    }
    
    
    public static final long readLong(byte[] buffer, int offset)
    {
        return (long) (
                (buffer[offset] & 0xffL) |
                ((buffer[offset + 1] & 0xffL) << 8) |
                ((buffer[offset + 2] & 0xffL) << 16) |
                ((buffer[offset + 3] & 0xffL) << 24) |
                ((buffer[offset + 4] & 0xffL) << 32) |
                ((buffer[offset + 5] & 0xffL) << 40) |
                ((buffer[offset + 6] & 0xffL) << 48) |
                ((buffer[offset + 7] & 0xffL) << 56));
    }
    public static final long readLong(InputStream is) throws IOException
    {
        byte[] buffer = new byte[8];
        readFully(is, buffer, 0, buffer.length);
        return readLong(buffer, 0);
    }
    
    
    public static final float readFloat(byte[] buffer, int offset)
    {
        return Float.intBitsToFloat(
                (buffer[offset] & 0xff) |
                ((buffer[offset + 1] & 0xff) << 8) |
                ((buffer[offset + 2] & 0xff) << 16) |
                ((buffer[offset + 3] & 0xff) << 24));
    }
    public static final float readFloat(InputStream is) throws IOException
    {
        byte[] buffer = new byte[4];
        readFully(is, buffer, 0, buffer.length);
        return readFloat(buffer, 0);
    }
    
    
    public static final double readDouble(byte[] buffer, int offset)
    {
        return Double.longBitsToDouble(
                (buffer[offset] & 0xffL) |
                ((buffer[offset + 1] & 0xffL) << 8) |
                ((buffer[offset + 2] & 0xffL) << 16) |
                ((buffer[offset + 3] & 0xffL) << 24) |
                ((buffer[offset + 4] & 0xffL) << 32) |
                ((buffer[offset + 5] & 0xffL) << 40) |
                ((buffer[offset + 6] & 0xffL) << 48) |
                ((buffer[offset + 7] & 0xffL) << 56));
    }
    public static final double readDouble(InputStream is) throws IOException
    {
        byte[] buffer = new byte[8];
        readFully(is, buffer, 0, buffer.length);
        return readDouble(buffer, 0);
    }
    
    
    
    public static final void readFully(InputStream is, byte[] buffer, int offset, int length) throws IOException
    {
        if(length < 0)
            throw new IndexOutOfBoundsException();
        int idx = 0;
        while(idx < length)
        {
            int count = is.read(buffer, offset + idx, length - idx);
            if(count < 0)
                throw new IOException();
            idx += count;
        }
    }
    public static final void readFully(InputStream is, byte[] buffer) throws IOException
    {
        readFully(is, buffer, 0, buffer.length);
    }
    
    
    public static final void skip(InputStream is, long length) throws IOException { is.skip(length); }
    
    
    
    public static final void close(InputStream is) throws IOException { is.close(); }
    public static final void close(OutputStream os) throws IOException { os.close(); }
    
    public static final void flush(OutputStream os) throws IOException { os.flush(); }
}
