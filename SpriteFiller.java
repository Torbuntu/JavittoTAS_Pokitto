package femto.mode;

public class SpriteFiller implements LineFiller {
    SpriteData[] spriteBuffer = new SpriteData[32];// lol, only 32 sprites.
    int buffer = 0;
    ushort[] palette;
    
    SpriteFiller(ushort[] palette){
        this.palette = palette;
    }
    
    
    public void addSprite(int[] sprite, float x, float y, int w, int h){
        if(buffer > 32)return;
        spriteBuffer[buffer] = new SpriteData(sprite, x, y, w, h);
        buffer++;
    }
    
    public void fillLine(ushort[] line, int y) {
        int each = buffer;
        int color;
        while(each > 0){
            SpriteData s = spriteBuffer[--each];
            if(y < s.y) continue;
            if(y >= s.y+s.height) continue;
            
            var indexY = (y-s.y);
            
            // We always want to keep startX at 0
            int startX = 0;
            int endX = s.width;
            
            if(s.x < 0){
                startX -= s.x;
                //endX += s.x;
            }
            
            if(startX + endX + s.x > 220){
                endX = 220-(startX+s.x);
            }

            var data = s.data;
            for(int x = startX; x < endX; ++x){
                color = data[x+indexY*s.width];
                if(color <= 0)continue;
                line[s.x+x]=color;
            }
        }
        // Reset the buffer if we are done with every line.
        if(y == 175) buffer = 0;
    }
    
}