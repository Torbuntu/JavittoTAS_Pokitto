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
        if(buffer > maxSprites) return;
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
        spriteBuffer[buffer] = new SpriteData(img, x, y, w, h, mirror, flip);
        buffer++;
    }
    
    // TODOD - Fix mirror and flip
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
                //endX += s.x;
            }
            
            if(startX + endX + s.x > 220){
                endX = 220-(startX+s.x);
            }

            var indexY = (y-s.y)*s.w;
            
            for(int x = startX; x < endX; ++x){
                __inline_cpp__("
                color = ((char*)s.frame)[x+indexY];
                ");
                
                if(color <= 0)continue;
                line[s.x+x]=palette[color];
            }
        }
        // Reset the buffer if we are done with every line.
        if(y == 175) buffer = 0;
    }
    
    
    /*

        data = new int[frameWidth*frameHeight];
        if(mirror){
            if(flip){
                for(int y = frameHeight-1; y >= 0; --y){
                    for(int x = frameWidth-1; x >= 0; --x){
                        __inline_cpp__("
                        dat = ((char*)img)[x+y*frameWidth];
                        ");
                        data[(frameWidth-1-x)+(frameHeight-1-y)*frameWidth] = palette[dat];
                    }
                }
            }else{
                for(int y = 0; y < frameHeight; ++y){
                    for(int x = frameWidth-1; x >= 0; --x){
                        __inline_cpp__("
                        dat = ((char*)img)[x+y*frameWidth];
                        ");
                        data[frameWidth-1-x+y*frameWidth] = palette[dat];
                    }
                }
            }
        }else{
            if(flip){
                for(int y = frameHeight-1; y >= 0; --y){
                    for(int x = 0; x < frameWidth; ++x){
                        __inline_cpp__("
                        dat = ((char*)img)[x+y*frameWidth];
                        ");
                        data[x+(frameHeight-1-y)*frameWidth] = palette[dat];
                    }
                }
            }else{
                for(int y = 0; y < frameHeight; ++y){
                    for(int x = 0; x < frameWidth; ++x){
                        __inline_cpp__("
                        dat = ((char*)img)[x+y*frameWidth];
                        ");
                        data[x+y*frameWidth] = palette[dat];
                    }
                }
            }
            
        }
        */
    
    
}