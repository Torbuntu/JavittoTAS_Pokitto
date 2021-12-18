import femto.mode.TASMode;
import femto.Game;
import femto.State;
import femto.input.Button;
import femto.font.TIC80;
import femto.palette.Miloslav;
import sprites.Dog;

class Main extends State {

    //0xD81F - Magenta
    // the screenmode we want to draw with
    TASMode screen;
    int color = 6000;
    Dog dog = new Dog();
    Dog[] dogs = new Dog[16];
    
    public static void main(String[] args){
        Game.run( TIC80.font(), new Main() );
    }
    
    void init(){
        screen = new TASMode(Miloslav.palette(), TIC80.font());
        dog.run();
        dog.setPosition(10, 10);
        
        for(int i = 0; i < 14; i++){
            dogs[i] = new Dog();
            dogs[i].run();
            dogs[i].setPosition(10+i*15, 32+i*4);
        }
        System.out.println(TileMaps.gardenPathData(0,0));
    }
    
    void shutdown(){
        screen = null;
    }
    public void resume(){
        screen.beforeFlush();
        screen.flush();
    }
    
    void update(){
        screen.clear(10);
        if( Button.A.justPressed() )
            Game.changeState( new Main() );
            
        if(Button.Down.isPressed()){
            dog.y=dog.y+2;
            dog.setFlipped(false);
        }
        if(Button.Up.isPressed()){
            dog.y=dog.y-2;
            dog.setFlipped(true);
        }
        if(Button.Right.isPressed()){
            dog.x=dog.x+2;
            dog.setMirrored(false);
        }
        if(Button.Left.isPressed()){
            dog.x=dog.x-2;
            dog.setMirrored(true);   
        }
        
        
        dog.draw(screen);
        for(byte i = 0; i < 14; i++){
            dogs[i].y=dogs[i].y+1.5f;
            if(dogs[i].y > 176)dogs[i].y = -12;
            dogs[i].x = dogs[i].x + 1.8f;
            if(dogs[i].x > 220)dogs[i].x = -15;
            dogs[i].draw(screen);
        }  
        
        screen.flush();
        
        System.out.println(screen.fps());
    }
    
}
