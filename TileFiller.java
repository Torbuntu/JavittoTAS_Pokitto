package femto.mode;

public class TileFiller implements LineFiller {
    // Get the map, get the tiles, render
    pointer tileMap;
    pointer tileSet;
    ushort[] palette;
    int mapWidth;
    int mapHeight;
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
            mapWidth = ((char * ) map)[0]; 
            mapHeight = ((char * ) map)[1]; 
            tileMap = (uint8_t * ) map + 2;
        ");
    }

    void draw(int x, int y) {
        offsetX = x;
        offsetY = y;
    }

// TODO: This thing needs massive work to be more performant...
    void fillLine(ushort[] line, int y) {
        if (offsetY + y < 0 || offsetY + y >= 176) return;
        
        var tileIndexY = ((y+offsetY) / 16) * mapWidth;
        var modY = ((y+offsetY) % 16) * 16;
        int modX;
        
        int tileIdx;
        
        for (int x = 0; x < mapWidth; x++) {
            __inline_cpp__("
                // Get tile index
                tileIdx = ((uint8_t*)tileMap)[(x) + tileIndexY];
                auto tile = ((uint8_t*)tileSet) + tileIdx * 256 + modY;
            ");
            
            modX = x * 16;
            for(int t = 0; t < 16; t++){
                __inline_cpp__("
                color = tile[t];
                ");
                line[modX+t] = palette[color];
            }
            
        }
    }
}