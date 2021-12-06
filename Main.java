import femto.mode.TASMode;
import femto.Game;
import femto.State;
import femto.input.Button;
import femto.font.TIC80;
import femto.palette.Miloslav;
import sprites.Dog;

class Main extends State {

    // the screenmode we want to draw with
    TASMode screen;
    int color = 0xD81F;
    Dog dog = new Dog();
    
    public static void main(String[] args){
        Game.run( TIC80.font(), new Main() );
    }
    
    void init(){
        screen = new TASMode(Miloslav.palette(), TIC80.font());
        dog.run();
        dog.setPosition(10, 10);
    }
    
    void shutdown(){
        screen = null;
    }
    
    void update(){
        screen.clear(0);
        if( Button.A.justPressed() )
            Game.changeState( new Main() );
            
        if(Button.Down.isPressed())dog.y=dog.y+1;
        if(Button.Up.isPressed())dog.y=dog.y-1;
        if(Button.Right.isPressed()){
            dog.x=dog.x+1;
            dog.setMirrored(false);
        }
        if(Button.Left.isPressed()){
            dog.x=dog.x-1;
            dog.setMirrored(true);   
        }
        //0xD81F
        
        dog.draw(screen);
        
        screen.flush();
    }
    
}
