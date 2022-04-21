package femto.mode;

public class TileFiller implements LineFiller {
    // Get the map, get the tiles, render
    pointer tileMap;
    pointer tileWindow;
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


// TODO: Fix camera clip
    void fillLine(ushort[] line, int y) {
        
        // Set the Y for the map and tileset lookup
        var tileMapIndexY = ((y+cameraY) / 16) * mapWidth;
        var tileSetIndexY = ((y+cameraY) % 16) * 16;
        var tileX = cameraX;
        // Loop the map width to collect the tiles
        for (int i = 0; i < 14;i++) {
            __inline_cpp__("
            // Get tile ID from the map. Then use that to find the tile itself from the tileset
            auto tileId = ((uint8_t*)tileMap)[(i+tileX) + tileMapIndexY];
            auto tile = ((uint8_t*)tileSet) + tileId * 256 + tileSetIndexY;
            ");
            
            // Loop over the Tile color IDs and put them in the line array.
            for(int t = 0; t < 16; t++){
                __inline_cpp__("
                color = tile[t];
                ");
                line[(i*16)+t] = palette[color];
            }
        }
        
    }
    
}