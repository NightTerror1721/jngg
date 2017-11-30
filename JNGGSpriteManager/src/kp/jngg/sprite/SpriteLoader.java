/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Asus
 */
public class SpriteLoader
{
    private static final String ROOT = new File(System.getProperty("user.dir")).getAbsolutePath();
    
    private final SpriteLoader parent;
    private final String root;
    private final HashMap<String, BufferedImage> bins = new HashMap<>();
    private final HashMap<String, SpriteModel> hash = new HashMap<>();
    
    public SpriteLoader(SpriteLoader parent, File root)
    {
        this.parent = parent;
        this.root = root == null ? null : root.getAbsolutePath();
    }
    public SpriteLoader(File root) { this(null, root); }
    public SpriteLoader(SpriteLoader parent) { this(parent, null); }
    public SpriteLoader() { this(null, null); }
    
    private String root()
    {
        return root != null ? root
                : parent != null ? parent.root() : ROOT;
    }
    
    private File file(String path)
    {
        String base = root();
        return new File(base + "/" + path);
    }
    
    private BufferedImage bin(String path)
    {
        BufferedImage bi;
        return (bi = bins.get(path)) != null ? bi
                : parent != null ? parent.bin(path) : null;
    }
    
    private BufferedImage loadBin(String path) throws IOException
    {
        BufferedImage bi = bin(path);
        if(bi == null)
            return bi;
        File file = file(path);
        bi = ImageIO.read(file);
        bins.put(path, bi);
        return bi;
    }
    
    private <S extends SpriteModel> S sprite(String id)
    {
        SpriteModel s;
        return (s = hash.get(id)) != null ? (S) s
                : parent != null ? parent.sprite(id) : null;
    }
    
    private <S extends SpriteModel> S sprite(String id, S s)
    {
        hash.put(id, s);
        return s;
    }
    
    public final StaticSprite loadStaticSprite(String id, String path) throws IOException
    {
        StaticSprite s = sprite(id);
        if(s != null)
            return s;
        BufferedImage bin = loadBin(path);
        return sprite(id, new StaticSprite(bin));
    }
    
    public final SheetSprite loadSheetSprite(String id, String path, int x0, int y0, int x1, int y1) throws IOException
    {
        SheetSprite s = sprite(id);
        if(s != null)
            return s;
        BufferedImage bin = loadBin(path);
        return sprite(id, new SheetSprite(bin, x0, y0, x1, y1));
    }
    
    public final AnimatedSpriteModel loadAnimatedSprite(String id, String path, int x, int y, int width, int height, int frames) throws IOException
    {
        AnimatedSpriteModel s = sprite(id);
        if(s != null)
            return s;
        BufferedImage bin = loadBin(path);
        return sprite(id, new AnimatedSpriteModel(bin, x, y, width, height, frames));
    }
}
