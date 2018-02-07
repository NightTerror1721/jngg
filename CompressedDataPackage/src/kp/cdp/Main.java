/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Deflater;

/**
 *
 * @author mpasc
 */
public final class Main
{
    public static void main(String[] args) throws IOException
    {
        executeCommand(args);
        
        /*CDPBuilder builder = new CDPBuilder();
        builder.createFile(new File("build.xml"));
        builder.createFile(new File("horari.pdf"));
        builder.createFile("test/Saavedra_2016.pdf", new File("Saavedra_2016.pdf"));
        builder.write(new File("compressed.cdp"), 9);
        
        CDPFileSystem fs = CDPFileSystem.open(new File("compressed.cdp"));
        try(InputStream is = fs.getFile("test/Saavedra_2016.pdf").openStream(); FileOutputStream fos = new FileOutputStream(new File("testExtern.pdf")))
        {
            byte[] b = new byte[8192];
            int len;
            while((len = is.read(b)) > 0)
                fos.write(b, 0, len);
        }
        
        
        
        System.out.println(fs);*/
        
        /*CDPBuilder b2 = new CDPBuilder();
        b2.createFiles(new File(System.getProperty("user.dir")).listFiles());
        b2.write(new File("compressed_2.cdp"));
        fs = CDPFileSystem.open(new File("compressed_2.cdp"));
        System.out.println(fs);*/
        
        //CDPFileSystem fs = CDPFileSystem.open(new File("complib.cdp"));
    }
    
    
    
    private static void executeCommand(String[] args)
    {
        if(args.length < 3)
        {
            usage();
            return;
        }
        String mode = args[0];
        File source = new File(args[1]);
        File dest = new File(args[2]);
        if(!source.exists())
        {
            System.out.println("Source file not found: " + source.getAbsolutePath());   
            usage();
            return;
        }
        switch(mode)
        {
            case "-c": {
                int clevel;
                if(args.length > 3)
                {
                    try { clevel = Integer.decode(args[3]); }
                    catch(NumberFormatException ex) { clevel = Deflater.DEFAULT_COMPRESSION; }
                }
                else clevel = Deflater.DEFAULT_COMPRESSION;
                compress(source, dest, clevel);
            } break;
            case "-u": uncompress(source, dest); break;
            default:
                System.out.println("Invalid mode: " + mode);   
                usage();
                break;
        }
    }
    
    private static void compress(File source, File dest, int clevel)
    {
        try
        {
            CDPBuilder builder = new CDPBuilder();
            WriteListener list = new WriteListener();
            if(source.isFile())
                builder.createFile(source);
            else builder.createFiles(source.listFiles());
            builder.write(dest, clevel, list);
            System.out.println("done.");
        }
        catch(IOException ex)
        {
            System.err.println("Exception has been ocurred during compression:\n\t" + ex);
        }
    }
    
    private static void uncompress(File source, File dest)
    {
        try
        {
            if(!source.isFile())
                throw new IOException("Expected valid source file");
            CDPFileSystem fs = CDPFileSystem.open(source, 8192);
            uncompressFolder(fs.getRootFolder(), dest, new byte[MIN_BUFFER_SIZE]);
            System.out.println("done.");
        }
        catch(IOException ex)
        {
            System.err.println("Exception has been ocurred during uncompression:\n\t" + ex);
        }
    }
    
    private static void uncompressFolder(CDPFolder folder, File dest, byte[] buffer)
    {
        System.out.println("Reading \"" + folder.getAbsolutePath() + "\"");
        dest.mkdir();
        for(CDPElement e : folder)
        {
            File edest = new File(dest.getAbsolutePath() + "/" + e.name);
            if(e.isFile())
                uncompressFile(e.toFile(), edest, buffer);
            else uncompressFolder(e.toFolder(), edest, buffer);
        }
    }
    
    private static void uncompressFile(CDPFile file, File dest, byte[] buffer)
    {
        buffer = ensureBuffer(buffer, file);
        try
        {
            System.out.println("Reading \"" + file.getAbsolutePath() + "\"");
            InputStream is = file.openStream();
            FileOutputStream fos = new FileOutputStream(dest);
            int len;
            while((len = is.read(buffer)) > 0)
                fos.write(buffer, 0, len);
        }
        catch(IOException ex)
        {
            System.err.println("Exception has been ocurred during uncompression:\n\t" + ex);
        }
    }
    
    private static final int MAX_BUFFER_SIZE = 128 << 10;
    private static final int MIN_BUFFER_SIZE = 8 << 10;
    private static byte[] ensureBuffer(byte[] buffer, CDPFile file)
    {
        int len = file.dataLen > MAX_BUFFER_SIZE
                ? MAX_BUFFER_SIZE
                : file.dataLen < MIN_BUFFER_SIZE
                        ? MIN_BUFFER_SIZE
                        : (int) file.dataLen;
        if(buffer.length < len)
            buffer = new byte[len];
        return buffer;
    }
    
    private static void usage()
    {
        System.out.println("Usage:\n\tcompress: -c <file_source | folder_source> <file_dest>\n\tuncompress: -u <file_source> <folder_dest>");
    }
    
    
    private static final class WriteListener implements CDPBuilderListener
    {
        private String last;
        
        @Override
        public void notifyState(BuilderState state)
        {
            if(last == null || !last.equals(state.getCurrentPath()))
            {
                System.out.println("writing \"" + state.getCurrentPath() + "\"");
                last = state.getCurrentPath();
            }
        }
        
    }
}
