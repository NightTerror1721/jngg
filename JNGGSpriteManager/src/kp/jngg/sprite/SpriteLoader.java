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
import java.util.Map;
import java.util.function.BiConsumer;
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
    private final HashMap<String, RawBitmap> bins = new HashMap<>();
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
    
    private RawBitmap bin(String path)
    {
        RawBitmap raw;
        return (raw = bins.get(path)) != null ? raw
                : parent != null ? parent.bin(path) : null;
    }
    
    private RawBitmap loadBin(String path) throws IOException
    {
        RawBitmap raw = bin(path);
        if(raw == null)
            return raw;
        raw = new RawBitmap(this, path);
        bins.put(path, raw);
        return raw;
    }
    public final RawBitmap loadRaw(String path) throws IOException { return loadBin(path); }
    
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
        RawBitmap bin = loadBin(path);
        return sprite(id, new StaticSprite(bin));
    }
    public static final StaticSprite createStaticSprite(BufferedImage source) { return new StaticSprite(new RawBitmap(source)); }
    
    public final SheetSprite loadSheetSprite(String id, String path, int x0, int y0, int x1, int y1) throws IOException
    {
        SheetSprite s = sprite(id);
        if(s != null)
            return s;
        RawBitmap bin = loadBin(path);
        return sprite(id, new SheetSprite(bin, x0, y0, x1, y1));
    }
    public final SheetSprite createSheetSprite(BufferedImage source, int x0, int y0, int x1, int y1) { return new SheetSprite(new RawBitmap(source), x0, y0, x1, y1); }
    
    public final AnimatedSpriteModel loadAnimatedSprite(String id, String path, int x, int y, int width, int height, int frames) throws IOException
    {
        AnimatedSpriteModel s = sprite(id);
        if(s != null)
            return s;
        RawBitmap bin = loadBin(path);
        return sprite(id, new AnimatedSpriteModel(bin, x, y, width, height, frames));
    }
    public static final AnimatedSpriteModel createAnimatedSprite(BufferedImage source, int x, int y, int width, int height, int frames)
    {
        return new AnimatedSpriteModel(new RawBitmap(source), x, y, width, height, frames);
    }
    
    public final <S extends Sprite> S getSprite(String id)
    {
        S s = sprite(id);
        if(s == null)
            throw new IllegalArgumentException("Sprite " + id + " not found");
        return s;
    }
    
    
    public final void forEachModel(BiConsumer<String, SpriteModel> consumer)
    {
        for(Map.Entry<String, SpriteModel> e : hash.entrySet())
            consumer.accept(e.getKey(), e.getValue());
    }
    
    
    
    public static final class RawBitmap
    {
        final String path;
        final BufferedImage raw;

        private RawBitmap(SpriteLoader loader, String path) throws IOException
        {
            this.path = path;
            this.raw = ImageIO.read(loader.file(path));
        }
        
        private RawBitmap(BufferedImage bi)
        {
            this.path = "";
            this.raw = bi;
        }

        public final String getPath() { return path; }
        public final BufferedImage getBufferedImage() { return raw; }
    }
}
