import femto.mode.TASMode;
import femto.Game;
import femto.State;
import femto.input.Button;
import femto.font.TIC80;

class Main extends State {

    // the screenmode we want to draw with
    TASMode screen;
    int color = 0xD81F;

    public static void main(String[] args){
        Game.run( TIC80.font(), new Main() );
    }
    
    void init(){
        screen = new TASMode();
    }
    
    void shutdown(){
        screen = null;
    }
    
    void update(){
        if( Button.A.justPressed() )
            Game.changeState( new Main() );
            
        if(Button.Down.justPressed())color+=0xDff0;
        if(Button.Up.justPressed())color=0xD0f0;
        //0xD81F
        screen.clear(color);
        screen.flush();
        
    }
    
}
