package femto.mode;
public class SpriteData {
    int x;
    int y;
    int width;
    int height;
    int[] data;
    SpriteData(int[] data, float x, float y, int w, int h){
        this.x = (int)x;
        this.y = (int)y;
        this.data = data;
        this.width = w;
        this.height = h;
    }
}