package femto.mode;

public class TileFiller implements LineFiller {
    // Get the map, get the tiles, render
    pointer map;
    pointer tileSet;
    ushort[] palette;
    TileFiller(ushort[] palette){
        this.palette = palette;
    }
    void setMap(pointer map, pointer tileSet){
        this.map = map;
        this.tileSet = tileSet;
    }
    
    int id=0;
    // TODO: Read the map to get the width/height.
    // TODO: Get the correct tile based on the current y and x position
    // TODO: This does draw something to the screen, but it sure is not a tile :D
    void fillLine(short[] line, int y){
        if(y>=16)return;
        int color = 0;
        id=0;
        for(int x = 0; x < 220; x++){
            if(x%16==0){
                id++;
            };
            __inline_cpp__("
                color = ((uint8_t (*)[256])tileSet)[id][(x%16)+y*16];
            ");
            color = palette[color];
            line[x] = color;
        }
    }
}