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
    // TODO: ... everything
    // TODO: This does draw something to the screen, but it sure is not a tile :D
    void fillLine(short[] line, int y){
        if(y > 16){
            id++;
            if(id>17)id=0;
            return;
        }
        int color = 0;
        int color2 = 0;
        for(int i = 0; i < 220; i++){
            if(i>16)continue;
            __inline_cpp__("
                color = ((uint8_t (*)[256])tileSet)[0x11][i+y*10];
            ");
            color = palette[color];
            line[i] = color;
        }
    }
}