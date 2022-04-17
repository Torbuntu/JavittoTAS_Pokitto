package femto.mode;

public class TileFiller implements LineFiller {
    // Get the map, get the tiles, render
    pointer tileMap;
    pointer tileSet;
    ushort[] palette;
    int mapWidth;
    int mapHeight;
    int color;
    int cameraX = 0;
    int cameraY = 0;

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
        cameraX = x;
        cameraY = y;
    }

// TODO: implement camera adjustment...
    void fillLine(ushort[] line, int y) {
        if (cameraY + y < 0 || cameraY + y >= 176) return;
        
        var tileMapIndexY = ((y+cameraY) / 16) * mapWidth;
        var tileSetIndexY = ((y+cameraY) % 16) * 16;
        
        for (int x = 0; x < mapWidth; x++) {
            __inline_cpp__("
            // Get tile index
            auto tileId = ((uint8_t*)tileMap)[(x) + tileMapIndexY];
            auto tile = ((uint8_t*)tileSet) + tileId * 256 + tileSetIndexY;
            ");
            
            var lineX = (x * 16);

            for(int t = 0; t < 16; t++){
                __inline_cpp__("
                color = tile[t];
                ");
                line[lineX+t] = palette[color];
            }
        }
    }
}