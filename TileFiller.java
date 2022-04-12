package femto.mode;

public class TileFiller implements LineFiller {
    // Get the map, get the tiles, render
    pointer tileMap;
    pointer tileSet;
    pointer tile;
    ushort[] palette;
    int width;
    int height;
    int color;
    int offsetX = 0;
    int offsetY = 0;

    int id;

    TileFiller(ushort[] palette) {
        this.palette = palette;
    }

    void setMap(pointer map, pointer tileSet) {
        this.tileSet = tileSet;

        __inline_cpp__("
            width = ((char * ) map)[0]; 
            height = ((char * ) map)[1]; 
            tileMap = (uint8_t * ) map + 2;
        ");
    }

    void draw(int x, int y) {
        offsetX = x;
        offsetY = y;
    }

// TODO: This thing needs massive work to be more performant...
    void fillLine(short[] line, int y) {
        if (offsetY + y < 0 || offsetY + y >= 176) return;
        
        var tileIndexW = ((y+offsetY) / 16) * width;
        var modY = ((y+offsetY) % 16) * 16;
        
        int startX = offsetX;
        int endX = 220;
        if(offsetX < 0){
            startX = 0;
            endX += offsetX;
        }
        if(startX + endX > 220) {
            endX = 220;
        }
        for (int x = startX; x < endX; x++) {
            // __inline_cpp__("
            //     // Get tile
            //     uint8_t tileIdx = ((uint8_t*)tileMap)[((x-offsetX) / 16) + tileIndexW];
            //     uint8_t tile = ((uint8_t*)tileSet)[tileIdx];
            // ");

            
            // for(int t = 0; t < 16; t++){
            //     __inline_cpp__("
            //         color = ((uint8_t*)tile)[t];
            //         color = palette[color];
            //         line[x+t]=color;
            //     ");
            // }
            
            // for(int t = 0; t < 16; t++){
                __inline_cpp__("
                color = ((uint8_t*) tileSet)
                    [(((uint8_t*)tileMap)[((x-offsetX) / 16) + tileIndexW]) // map tile index
                    * 256 + // width of tiles?
                    (((x-offsetX) % 16) + modY)]; // tile color index
                ");
                color = palette[color]; 
                line[x] = color;
            // }
            
            
        }
    }
}