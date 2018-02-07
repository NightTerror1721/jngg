/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.cdp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Asus
 */
public class CDPFolder extends CDPElement implements Iterable<CDPElement>
{
    private final HashMap<String, CDPElement> elements = new HashMap<>();
    
    CDPFolder(String name, CDPFolder parent)
    {
        super(FOLDER_TYPE, name, parent);
    }
    
    @Override
    public final CDPFolder toFolder() { return this; }
    
    public final int getElementCount() { return elements.size(); }
    
    public final CDPElement getElement(String path)
    {
        String[] parts = path.split("/");
        if(parts.length == 1)
            return elements.get(path);
        return findElement(this, parts);
    }
    
    public final CDPFolder getFolder(String path)
    {
        CDPElement e = getElement(path);
        return e.isFolder() ? (CDPFolder) e : null;
    }
    
    public final CDPFile getFile(String path)
    {
        CDPElement e = getElement(path);
        return e.isFile() ? (CDPFile) e : null;
    }
    
    public final boolean hasElement(String path) { return getElement(path) != null; }
    public final boolean hasFolder(String path) { return getFolder(path) != null; }
    public final boolean hasFile(String path) { return getFile(path) != null; }
    
    public final CDPElement[] listElements()
    {
        Collection<CDPElement> c = elements.values();
        return c.toArray(new CDPElement[c.size()]);
    }
    
    public final CDPFolder[] listFolders()
    {
        Collection<CDPElement> c = elements.values();
        return c.stream().filter(CDPElement::isFolder).map(CDPElement::toFolder).toArray(size -> new CDPFolder[size]);
    }
    
    public final CDPFile[] listFiles()
    {
        Collection<CDPElement> c = elements.values();
        return c.stream().filter(CDPElement::isFile).map(CDPElement::toFile).toArray(size -> new CDPFile[size]);
    }
    
    @Override
    public final String toString()
    {
        StringBuilder sb = new StringBuilder();
        if(parent != null)
            sb.append(getAbsolutePath()).append("\n");
        for(CDPElement e : elements.values())
            sb.append(e).append('\n');
        if(!elements.isEmpty())
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    
    
    final void readFolder(CDPFileSystem base, InputStream is) throws IOException
    {
        int elementCount = IOUtils.readInt(is);
        main: while(elementCount-- > 0)
        {
            byte etype = IOUtils.readByte(is);
            String ename = readName(is);
            CDPElement element;
            switch(etype)
            {
                case FOLDER_TYPE: {
                    CDPFolder folder = new CDPFolder(ename, this);
                    element = folder;
                    folder.readFolder(base, is);
                } break;
                case FILE_TYPE: {
                    long dlen = IOUtils.readLong(is);
                    long doff = IOUtils.readLong(is);
                    CDPFile file = new CDPFile(ename, this, base, dlen, doff);
                    element = file;
                } break;
                default:
                    continue;
            }
            elements.put(ename, element);
        }
    }
    
    private static CDPElement findElement(CDPFolder current, String[] parts)
    {
        CDPElement e = null;
        for(int i=0;i<parts.length;i++)
        {
            e = current.elements.get(parts[i]);
            if(i + 1 < parts.length)
            {
                if(!e.isFolder())
                    return null;
                current = (CDPFolder) e;
            }
        }
        return e;
    }

    @Override
    public final Iterator<CDPElement> iterator()
    {
        return elements.values().iterator();
    }
}
