package femto.mode;
import static femto.hardware.ST7775.*;
public class ColorFiller implements LineFiller {
    int color;
    ushort[] palette;
    ColorFiller(ushort[] palette){
        this.palette = palette;
    }
    public void draw(int color){
        this.color = palette[color];
    }
    public void fillLine(short[] line, int y) {
        for(int x = 0; x < 220; ++x){
            // line[x] = (int)palette[Math.random(0, 250)];
            line[x] = color;
        }
    }
}
