package femto.mode;

public class SpriteFiller implements LineFiller {

    int maxSprites = 100;
    SpriteData[] spriteBuffer = new SpriteData[maxSprites];
    int buffer = 0;
    ushort[] palette;
    
    SpriteFiller(ushort[] palette){
        this.palette = palette;
    }

    public void addSprite(pointer frame, float x, float y, boolean mirror, boolean flip){
        if(buffer >= maxSprites) return;
        int w;
        int h;
        pointer img;
        __inline_cpp__("
            w = ((char*)frame)[0];
            h = ((char*)frame)[1];
            img = (uint8_t *)frame+2;
        ");
        
        if(x+w < 0 || x >= 220)return;
        if(y+h < 0 || y >= 176)return;
        if(spriteBuffer[buffer] != null ){
            spriteBuffer[buffer].set(img, x, y, w, h, mirror, flip);
        } else {
            spriteBuffer[buffer] = new SpriteData(img, x, y, w, h, mirror, flip);
        }
        
        buffer++;
    }
    
    public void fillLine(ushort[] line, int y) {
        int each = buffer;
        int color;
        while(each > 0){
            SpriteData s = spriteBuffer[--each];
            if(y < s.y || y >= s.y+s.h) continue;
            
            // We always want to keep startX at 0
            int startX = 0;
            int endX = s.w;
            
            if(s.x < 0){
                startX -= s.x;
            }
            
            if(startX + endX + s.x > 220){
                endX = 220-(startX+s.x);
            }

            var indexY = (y-s.y)*s.w;
            
            if(s.mirror){
                if(s.flip){
                    fillFlipMirror(line,y,s,startX,endX,indexY,color);
                } else {
                    fillMirror(line,y,s,startX,endX,indexY,color);
                }
            } else {
                if(s.flip){
                    fillFlip(line,y,s,startX,endX,indexY,color);
                } else {
                    fill(line,y,s,startX,endX,indexY,color);
                }
            }
        }
        // Reset the buffer if we are done with every line.
        if(y == 175) buffer = 0;
    }
    
    void fillFlipMirror(ushort[] line,int y, SpriteData s, int startX, int endX,int indexY, int color){
        for(int x = startX; x < endX; ++x){
            __inline_cpp__("
            color = ((char*)s->frame)[(s->w-1-x)+(s->h-1-(y-s->y))*s->w];
            ");
            if(color <= 0)continue;
            line[s.x+x]=palette[color];
        }
    }
    
    void fillMirror(ushort[] line,int y, SpriteData s, int startX, int endX,int indexY, int color) {
        for(int x = startX; x < endX; ++x){
            __inline_cpp__("
            color = ((char*)s->frame)[s->w-1-x+indexY];
            ");
            if(color <= 0)continue;
            line[s.x+x]=palette[color];
        }
    }
    
    void fillFlip(ushort[] line,int y, SpriteData s, int startX, int endX,int indexY, int color) {
        for(int x = startX; x < endX; ++x){
            __inline_cpp__("
            color = ((char*)s->frame)[x+(s->h-1-(y-s->y))*s->w];
            ");
            if(color <= 0)continue;
            line[s.x+x]=palette[color];
        }
    }
    
    void fill(ushort[] line,int y, SpriteData s, int startX, int endX,int indexY, int color) {
        for(int x = startX; x < endX; ++x){
            __inline_cpp__("
            color = ((char*)s->frame)[x+indexY];
            ");
            if(color <= 0)continue;
            line[s.x+x]=palette[color];
        }
    }
}