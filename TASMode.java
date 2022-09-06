package femto.mode;

import static femto.hardware.ST7775.*;
import java.util.Arrays;

public class TASMode extends ScreenMode implements __stub__ {
    
    /// The screen buffer.
    public byte[] buffer;

    /// The screen mode's palette.
    public ushort[] palette;
    
    ushort[] line;
    
    // oh boy, here we go. Lots to do here. *deep breaths* 
    public LineFiller[] fillers = new LineFiller[3];
    ColorFiller colorFiller;
    SpriteFiller spriteFiller;
    TileFiller tileFiller;
    
    protected TASMode(){}
    
    public TASMode( pointer pal, pointer font ){
        initialize(pal);
    }
    
    protected void initialize( pointer pal ){
        this.font = font;
        line = new ushort[220];
        palette = new ushort[256];
        loadPalette( pal );
        spriteFiller = new SpriteFiller(palette);
        colorFiller = new ColorFiller(palette);
        tileFiller = new TileFiller(palette);
        fillers[0] = colorFiller;
        fillers[1] = tileFiller;
        fillers[2] = spriteFiller;
        clear(0);
        textRightLimit = width();
        return;
        beforeFlush(); // prevent function from being discarded
    }
    
    void disableFiller(int id) {
        fillers[id] = null;
    }
    
    private void beforeFlush(){
        beginStream();
    }
    
    /// Loads the specified palette.
    public void loadPalette( pointer pal ){
        if( pal == null )return;
        
        int len = Math.min(256, (int) System.memory.LDRH(pal));
        for( int i=0; i<len; ++i ){
            palette[i] = System.memory.LDRH(pal+2+(i<<1));
        }
    }
    
    public int width(){
        return 220;
    }

    public int height(){
        return 176;
    }
    
    public void setTile(int tileId, int x, int y){
        tileFiller.setTile(tileId, x, y);
    }
    
    public int getTile(int x, int y) {
        return tileFiller.getTile(x,y);
    }
    
    public void setMap(pointer map, pointer tileSet){
        tileFiller.setMap(map, tileSet);
    }
    
    public void drawMap(int x, int y){
        tileFiller.draw(x,y);
    }
    
    public void addSprite(pointer frame, float x, float y, boolean mirror, boolean flip){
        spriteFiller.addSprite(frame, x, y, mirror, flip);
    }
    
    public void clear( int color ){
        colorFiller.draw(color);
    }

    void flush() {
        super.flush();
        beginStream();
        for(int y = 0; y < 176; ++y){
            for(LineFiller lf : fillers){
                if(null == lf)continue;
                // fillLine populates the line variable with data
                lf.fillLine(line, y);
            }
            __inline_cpp__("
            // pokitto/libs/SystemInit.s
            // pokitto/begin.cpp
            flushLine16(line->elements);
            ");
        }
    }
}