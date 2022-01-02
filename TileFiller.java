package femto.mode;

public class TileFiller implements LineFiller {
    // Get the map, get the tiles, render
    pointer tileMap;
    pointer tileSet;
    ushort[] palette;
    int width;
    int height;
    int color = 0;
    int offsetX = 0;
    int offsetY = 0;
    
    TileFiller(ushort[] palette){
        this.palette = palette;
    }
    
    void setMap(pointer map, pointer tileSet){
        this.tileSet = tileSet;
        
        __inline_cpp__("
            width = ((char*)map)[0];
            height = ((char*)map)[1];
            tileMap = (uint8_t *)map+2;
        ");
    }
    
    void draw(int x, int y){
        offsetX = x;
        offsetY = y;
    }
    
    void fillLine(short[] line, int y){
       // if(y > 16)return;
        for(int x = 0; x < 220; x++){
            if(offsetX+x < 0 || offsetY+y < 0)continue;
            if(offsetX+x >= 220 || offsetY+y >= 176)continue;
            __inline_cpp__("
                color = ((uint8_t*)tileSet)
                [(((uint8_t*)tileMap)[(x/16)+(y/16)*width]) // map tile index
                *256+
                ((x%16)+(y%16)*16)]; // tile color index
            ");
            color = palette[color];
            line[x] = color;
        }
    }
}