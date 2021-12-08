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
    ColorFiller colorFiller = new ColorFiller();
    SpriteFiller spriteFiller = new SpriteFiller();
    // TileFiller tileFiller = new TileFiller();
    
    protected TASMode(){}
    
    public TASMode( pointer pal, pointer font ){
        fillers[0] = colorFiller;
        //fillers[1] = tilesFiller;
        fillers[2] = spriteFiller;
        initialize(pal);
    }
    
    protected void initialize( pointer pal ){
        this.font = font;
        line = new short[220];
        //buffer = new byte[(this.width()>>1)*this.height()];
        palette = new ushort[256]; // needs to be significantly larger for TASMode
        loadPalette( pal );
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
            for(int y = 0; y < frameHeight; ++y){
                for(int x = frameWidth-1; x >= 0; --x){
                    __inline_cpp__("
                    dat = ((char*)img)[x+y*frameWidth];
                    ");
                    data[frameWidth-1-x+y*frameWidth] = palette[dat];
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