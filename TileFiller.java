package femto.mode;

public class TileFiller implements LineFiller {
    // Get the map, get the tiles, render
    pointer map;
    pointer tileSet;
    
    void addMap(pointer map, pointer tileSet){
        this.map = map;
        this.tileSet = tileSet;
        
    }
    
    //TODO: ... everything
    void fillLine(short[] line, int y){
        if(y > 16)continue;
        
    }
}