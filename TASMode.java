package femto.mode;

import static femto.hardware.ST7775.*;
import java.util.Arrays;

public class TASMode extends ScreenMode implements __stub__ {
    
    /// The screen buffer.
    public byte[] buffer;

    /// The screen mode's palette.
    public ushort[] palette;
    
    short[] line;
    
    // oh boy, here we go. Lots to do here. *deep breaths* 
    public LineFiller[] fillers = new LineFiller[4];
    ColorFiller colorFiller;
    SpriteFiller spriteFiller;
    TileFiller tileFiller;
    
    protected TASMode(){}
    
    public TASMode( pointer pal, pointer font ){
        initialize(pal);
    }
    
    protected void initialize( pointer pal ){
        this.font = font;
        line = new short[220];
        //buffer = new byte[(this.width()>>1)*this.height()];
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
    
    public void setMap(pointer map, pointer tileSet){
        tileFiller.setMap(map, tileSet);
    }
    
    //TODO: these should probably be renamed to something more useful. And put somewhere more obvious.
    int dat;
    int[] data;
    int frameWidth;
    int frameHeight;
    //TODO: Somehow add frame image data to some sort of sprite buffer that will render in SpriteFiller.fillLine
    public void addSprite(pointer frame, float x, float y, boolean mirror, boolean flip){
        __inline_cpp__("
            frameWidth = ((char*)frame)[0];
            frameHeight = ((char*)frame)[1];
            const uint8_t *img = (uint8_t *)frame+2;
        ");
        data = new int[frameWidth*frameHeight];
        if(mirror){
            if(flip){
                for(int y = frameHeight-1; y >= 0; --y){
                    for(int x = frameWidth-1; x >= 0; --x){
                        __inline_cpp__("
                        dat = ((char*)img)[x+y*frameWidth];
                        ");
                        data[(frameWidth-1-x)+(frameHeight-1-y)*frameWidth] = palette[dat];
                    }
                }
            }else{
                for(int y = 0; y < frameHeight; ++y){
                    for(int x = frameWidth-1; x >= 0; --x){
                        __inline_cpp__("
                        dat = ((char*)img)[x+y*frameWidth];
                        ");
                        data[frameWidth-1-x+y*frameWidth] = palette[dat];
                    }
                }
            }
        }else{
            if(flip){
                for(int y = frameHeight-1; y >= 0; --y){
                    for(int x = 0; x < frameWidth; ++x){
                        __inline_cpp__("
                        dat = ((char*)img)[x+y*frameWidth];
                        ");
                        data[x+(frameHeight-1-y)*frameWidth] = palette[dat];
                    }
                }
            }else{
                for(int y = 0; y < frameHeight; ++y){
                    for(int x = 0; x < frameWidth; ++x){
                        __inline_cpp__("
                        dat = ((char*)img)[x+y*frameWidth];
                        ");
                        data[x+y*frameWidth] = palette[dat];
                    }
                }
            }
            
        }
        
        spriteFiller.addSprite(data, x, y, frameWidth, frameHeight);
    }
    
    
    public void clear( int color ){
        colorFiller.draw(color);
    }

    void flush() {
        super.flush();
        for(int y = 0; y < 176; ++y){
            for(LineFiller lf : fillers){
                if(null == lf)continue;
                // fillLine populates the line variable with data
                lf.fillLine(line, y);
            }
            flushLine(line, y);
        }
    }
    
    void flushLine(short[] line, int y){
        beginStream();
        for(int x = 0; x < line.length; ++x){
            writeData(line[x]);
        }
    }
    
    
}