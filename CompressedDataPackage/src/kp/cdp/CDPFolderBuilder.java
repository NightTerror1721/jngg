/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import kp.cdp.CDPBuilder.NativeFileWriter;

/**
 *
 * @author mpasc
 */
public class CDPFolderBuilder extends CDPElementBuilder
{
    private final HashMap<String, CDPElementBuilder> elements = new HashMap<>();
    
    private CDPFolderBuilder(CDPFolderBuilder parent, String name)
    {
        super(CDPElement.FOLDER_TYPE, parent, name);
    }
    CDPFolderBuilder() { super(); }
    
    public final CDPFolderBuilder createFolder(String name)
    {
        String[] path = name.split("/");
        if(path.length == 1)
            return createFolder(name, true);
        return findFolder(this, path).createFolder(path[path.length - 1], true);
    }
    private CDPFolderBuilder createFolder(String name, boolean check)
    {
        if(check && elements.containsKey(name))
            throw new IllegalArgumentException("Element with name " + name + " already exists");
        CDPFolderBuilder folder = new CDPFolderBuilder(this, name);
        elements.put(name, folder);
        return folder;
    }
    
    public final CDPFileBuilder createFile(String name, File file)
    {
        String[] path = name.split("/");
        CDPFolderBuilder folder = findFolder(this, path);
        if(folder.elements.containsKey(name))
            throw new IllegalArgumentException("Element with name " + name + " already exists");
        CDPFileBuilder bfile = CDPFileBuilder.fromFile(folder, path[path.length - 1], file);
        folder.elements.put(name, bfile);
        return bfile;
    }
    public final CDPFileBuilder createFile(File file) { return createFile(file.getName(), file); }
    
    public final CDPFileBuilder createFile(String name, CDPFile file)
    {
        String[] path = name.split("/");
        CDPFolderBuilder folder = findFolder(this, path);
        if(folder.elements.containsKey(name))
            throw new IllegalArgumentException("Element with name " + name + " already exists");
        CDPFileBuilder bfile = CDPFileBuilder.fromCDPFile(folder, path[path.length - 1], file);
        folder.elements.put(name, bfile);
        return bfile;
    }
    
    public final CDPFileBuilder createFile(String name, InputStream is)
    {
        String[] path = name.split("/");
        CDPFolderBuilder folder = findFolder(this, path);
        if(folder.elements.containsKey(name))
            throw new IllegalArgumentException("Element with name " + name + " already exists");
        CDPFileBuilder bfile = CDPFileBuilder.fromStream(folder, path[path.length - 1], is);
        folder.elements.put(name, bfile);
        return bfile;
    }
    
    public final void createFiles(Collection<File> files) throws FileNotFoundException
    {
        for(File file : files)
        {
            if(!file.exists())
                throw new FileNotFoundException("File " + file.getAbsolutePath() + " not found");
            if(file.isDirectory())
                createFolder(file.getName(), true).createFiles(file.listFiles());
            else createFile(file);
        }
    }
    public final void createFiles(File[] files) throws FileNotFoundException { createFiles(Arrays.asList(files)); }

    @Override
    final long headerLen()
    {
        long len = parent == null ? 4 : (9 + nameLen());
        for(CDPElementBuilder e : elements.values())
            len += e.headerLen();
        return len;
    }
    
    @Override
    final void writeHeader(NativeFileWriter accessor) throws IOException
    {
        if(parent != null)
        {
            IOUtils.writeByte(accessor, type);
            writeName(accessor, name);
        }
        IOUtils.writeInt(accessor, elements.size());
        for(CDPElementBuilder e : elements.values())
            e.writeHeader(accessor);
    }
    
    @Override
    final void writeFiles(NativeFileWriter accessor) throws IOException
    {
        accessor.notifyState(this);
        for(CDPElementBuilder e : elements.values())
            e.writeFiles(accessor);
    }
    
    private static CDPFolderBuilder findFolder(CDPFolderBuilder current, String[] path)
    {
        int len = path.length - 1;
        for(int i=0;i<len;i++)
        {
            if(!current.elements.containsKey(path[i]))
                current = current.createFolder(path[i], false);
            else
            {
                CDPElementBuilder e = current.elements.get(path[i]);
                if(!e.isFolder())
                    throw new IllegalArgumentException("Expected valid folder with name " + path[i]);
                current = (CDPFolderBuilder) e;
            }
        }
        return current;
    }
}
