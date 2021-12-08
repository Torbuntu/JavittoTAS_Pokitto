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
    int color = 0;
    Dog dog = new Dog();
    Dog dog2 = new Dog();
    Dog dog3 = new Dog();
    public static void main(String[] args){
        Game.run( TIC80.font(), new Main() );
    }
    
    void init(){
        screen = new TASMode(Miloslav.palette(), TIC80.font());
        dog.run();
        dog2.run();
        dog3.run();
        dog.setPosition(10, 10);
        dog2.setPosition(10, 25);
        dog3.setPosition(10, 40);
    }
    
    void shutdown(){
        screen = null;
    }
    public void resume(){
        screen.beforeFlush();
        screen.flush();
    }
    
    void update(){
        screen.clear(color);
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
        
        //TODO: Currently, only the final sprite will be drawn.
        //      Need to add all sprites to a buffer to be drawn
        //      in the correct order of call.
        dog.draw(screen);
        dog2.draw(screen);
        dog3.draw(screen);
        
        screen.flush();
        
        System.out.println(screen.fps());
    }
    
}
