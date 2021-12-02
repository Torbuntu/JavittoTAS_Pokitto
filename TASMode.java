package femto.mode;

import static femto.hardware.ST7775.*;

public class TASMode{
    public int width(){
        return 220;
    }

    public int height(){
        return 176;
    }
    
    ColorFiller colorFiller = new ColorFiller();
    // SpriteFiller spriteFiller = new SpriteFiller();
    // TileFiller tileFiller = new TileFiller();
    
    // oh boy, here we go. Lots to do here. *deep breaths* 
    public LineFiller[] fillers = new LineFiller[4];

    public TASMode(){
        fillers[0] = colorFiller;
        //fillers[1] = tilesFiller;
        //fillers[2] = spriteFiller;
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