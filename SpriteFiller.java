package femto.mode;

public class SpriteFiller implements LineFiller {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int[] data;
    
    public void setSprite(int[] sprite, int x, int y, int w, int h){
        width = w;
        height = h;
        this.x = x;
        this.y = y;
        data = sprite;
    }
    
    public void fillLine(short[] line, int y) {
        if(y < this.y) return;
        if(y >= this.y+height) return;
        for(int i = 0; i < width; ++i){
            if(data[i+(y-this.y)*width] <= 0)continue;
            //System.out.println("data:"+data[i+(y-this.y)*width]);
            line[x+i]=data[i+(y-this.y)*width];
        }
    }
}