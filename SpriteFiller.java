package femto.mode;

public class SpriteFiller implements LineFiller {
    SpriteData[] spriteBuffer = new SpriteData[32];// lol, only 10 sprites.
    int buffer = 0;
    public void addSprite(int[] sprite, float x, float y, int w, int h){
        if(buffer > 32)return;
        spriteBuffer[buffer] = new SpriteData(sprite, x, y, w, h);
        buffer++;
    }
    
    public void fillLine(short[] line, int y) {
        int each = buffer;
        int color;
        while(each > 0){
            SpriteData s = spriteBuffer[--each];
            if(y < s.y) continue;
            if(y >= s.y+s.height) continue;
            for(int i = 0; i < s.width; ++i){
                color = s.data[i+(y-s.y)*s.width];
                if(color <= 0)continue;
                if(s.x+i < 0)continue;
                if(s.x+i >= 220)continue;
                
                line[s.x+i]=color;
            }
        }
        // Reset the buffer if we are done with every line.
        if(y == 175) buffer = 0;
    }
    
}