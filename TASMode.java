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

		pointer frame;
        __inline_cpp__("
		const auto &f = *(const up_femto::uc_FrameRef*)getFrameDataForScreen(currentFrame, (up_femto::up_mode::uc_LowRes256Color*)nullptr);

		frame = f.frame;
		
		");
		screen.addSprite(frame);
        return;
        getFrameDataForScreen(0, (LowRes256Color)null);
        width();
        height();
    }
    
    
    */
    
    //TODO: Somehow add frame image data to some sort of sprite buffer that will render in SpriteFiller.fillLine
    public void addSprite(pointer frame){
        
        System.out.println("Begin populating sprite buffer: ");
        
    //     __inline_cpp__("
    //         printf(\"in C++ land \\n\");
    //         const uint8_t *img = (uint8_t *)frame+2;
            
    //         int frameWidth = ((char*)frame)[0];
		  //  int frameHeight= ((char*)frame)[1];
    //         for(int y = 0; y < frameHeight; ++y){
    //             for(int x = 0; x < frameWidth; ++x){
    //                 //TODO: printf isn't doing anything as far as I can tell...
    //                 printf(\"data: %c  \\n\",((char*)img)[x+y*frameWidth]);
    //             }
    //         }
    //     ");
        int dat;
        int[] data;
        int width;
        int height;
        __inline_cpp__("
            width = ((char*)frame)[0];
            height = ((char*)frame)[1];
            const uint8_t *img = (uint8_t *)frame+2;
        ");
        data = new int[width*height];
        for(int y = 0; y < height; ++y){
            for(int x = 0; x < width; ++x){
                __inline_cpp__("
                dat = ((char*)img)[x+y*width];
                ");
                data[x+y*width] = palette[dat];
                System.out.print(","+data[x+y*width]);
            }
        }
        
        spriteFiller.setSprite(data, 100, 70, width, height);
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