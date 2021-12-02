package femto.mode;
import static femto.hardware.ST7775.*;
public class ColorFiller implements LineFiller {
    int color;
    public void draw(int color){
        this.color = color;
    }
    public void fillLine(short[] line, int y) {
        for(int x = 0; x < 220; ++x){
            line[x] = Math.random(0, 2147483647);
        }
    }
}
