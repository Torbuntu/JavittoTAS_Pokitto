package femto.mode;

public class SpriteFiller implements LineFiller {
    SpriteData[] spriteBuffer = new SpriteData[10];// lol, only 10 sprites.
    int buffer = 0;
    public void addSprite(int[] sprite, float x, float y, int w, int h){
        if(buffer > 10)return;
        spriteBuffer[buffer] = new SpriteData(sprite, x, y, w, h);
        buffer++;
    }
    
    //TODO: Fix the position if off the screen. 
    //      We can't draw off the screen.
    public void fillLine(short[] line, int y) {
        int each = buffer;
        
        while(each > 0){
            SpriteData s = spriteBuffer[--each];
            if(y < s.y) continue;
            if(y >= s.y+s.height) continue;
            for(int i = 0; i < s.width; ++i){
                if(s.data[i+(y-s.y)*s.width] <= 0)continue;
                //System.out.println("data:"+data[i+(y-this.y)*width]);
                line[s.x+i]=s.data[i+(y-s.y)*s.width];
            }
        }
        // Reset the buffer if we are done with every line.
        if(y == 175) buffer = 0;
    }
    
}