package femto.mode;

public class TileFiller implements LineFiller {
    // Get the map, get the tiles, render
    pointer map;
    pointer tileSet;
    ushort[] palette;
    int width;
    int height;
    TileFiller(ushort[] palette){
        this.palette = palette;
    }
    void setMap(pointer map, pointer tileSet){
        this.tileSet = tileSet;
        
        __inline_cpp__("
            width = ((char*)map)[0];
            height = ((char*)map)[1];
            map = (uint8_t *)map+2;
        ");
    }
    
    int color = 0;
    // TODO: Read the map to get the width/height.
    // TODO: Get the correct tile based on the current y and x position
    // TODO: This does draw something to the screen, but it sure is not a tile :D
    void fillLine(short[] line, int y){
        for(int x = 0; x < 220; x++){
            __inline_cpp__("
                color = ((uint8_t (*)[256])tileSet)
                [((uint8_t*)map)[(x%16)+(y%16)]] // tile from map x,y
                [(x%16)+y*16]; // tile color
            ");
            color = palette[color];
            line[x] = color;
        }
    }
}