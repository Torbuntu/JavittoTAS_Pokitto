package femto.mode;
public class SpriteData {
    int x;
    int y;
    int w;
    int h;
    boolean mirror;
    boolean flip;
    pointer frame;
    
    // TODO: use the frame pointer directly instead of an int[] data?
    SpriteData(pointer frame, float x, float y, int w, int h, boolean mirror, boolean flip){
        this.x = (int)x;
        this.y = (int)y;
        this.w = w;
        this.h = h;
        this.frame = frame;
        this.mirror = mirror;
        this.flip = flip;
    }
}