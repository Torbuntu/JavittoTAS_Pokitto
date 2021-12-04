package femto.mode;

import static femto.hardware.ST7775.*;
import java.util.Arrays;

public class TASMode extends ScreenMode implements __stub__ {
    
    /// The screen buffer.
    public byte[] buffer;

    /// The screen mode's palette.
    public ushort[] palette;
    
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
        buffer = new byte[(this.width()>>1)*this.height()];
        palette = new ushort[256]; // needs to be significantly larger for TASMode
        loadPalette( pal );
        clear(0);
        textRightLimit = width();
        return;
        beforeFlush(); // prevent function from being discarded
    }
    
    /// Loads the specified palette.
    public void loadPalette( pointer pal ){
        if( pal == null )
            return;
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
    
    
    /* within femto.Sprite.java
    
    public void draw( TASMode screen ){
        updateTasAnimation();
        float x = this.x;
        float y = this.y;
        boolean mirror = (flags&2) != 0;
        boolean flip = (flags&4) != 0;

        if( (flags&1) == 0 ){
            x -= screen.cameraX;
            y -= screen.cameraY;
        }
        
        int frameWidth;
        int frameHeight;
		pointer frame;
        __inline_cpp__("
		const auto &f = *(const up_femto::uc_FrameRef*)getFrameDataForScreen(currentFrame, screen);
		frameWidth = ((char*)f.frame)[0];
		frameHeight= ((char*)f.frame)[1];
		frame = f.frame;
		
		");
		screen.addSprite(frame, frameWidth, frameHeight);
        return;
        getFrameDataForScreen(0, (TASMode)null);
        width();
        height();
    }
    
    
    */
    
    public void addSprite(pointer frame, int width, int height){
        //TODO: Somehow add frame to some sort of sprite buffer
    }
    
    
    private void beforeFlush(){
        beginStream();
    }

    public void clear( int color ){
        colorFiller.draw(color);
    }
    
    void flush() {
        short[] line = new short[220];
        for(int y = 0; y < 176; ++y){
            for(LineFiller lf : fillers){
                if(null == lf)continue;
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