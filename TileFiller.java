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
    int tileH = 16;
    int tileW = 16;
    
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
        // Clip top and bottom of map.
        if(y-cameraY < 0 || y-cameraY >= mapHeight*tileH)return;
        
        // Set the Y for the map and tileset lookup
        var mapY = ((y-cameraY) / 16) * mapWidth;
        var tileY = ((y-cameraY) % 16) * tileW;
        
        // Divide the current X position by the width of the Tiles.
        var mapX = cameraX / tileW;
        
        // Get the position on the first X Tile.
        var tileX = cameraX % tileW;
        
        // Loop the map width to collect the tiles
        for (int i = 0; i < 220;) {
            // Clip the right hand side of the map. Whee~
            if(mapX >= mapWidth)return;
            
            int iter = Math.min(tileW - tileX, 220 - i);
            // Trying to clip the left side of the map...
            if(mapX < 0){
                mapX++;
                tileX = 0;
                i+=iter;
                continue;
            }
            
            __inline_cpp__("
            // Get tile ID from the map. Then use that to find the tile itself from the tileset
            auto tileId = ((uint8_t*)tileMap)[mapX + mapY];
            auto tile = ((uint8_t*)tileSet) + tileId * 256 + tileY;
            ");
            

            // Loop over the Tile color IDs and put them in the line array.
            for(int t = 0; t < iter; t++){
                __inline_cpp__("
                color = tile[tileX + t];
                ");
                line[i+t] = palette[color];
            }
            i+=iter;
            tileX = 0;
            mapX++;
        }
    }
    
}