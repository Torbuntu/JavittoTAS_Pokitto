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
    int color = 6000;
    Dog dog = new Dog();
    Dog[] dogs = new Dog[16];
    
    int x,y;
    
    boolean mapSwitch = false;
    
    public static void main(String[] args){
        Game.run( TIC80.font(), new Main() );
    }
    
    void init(){
        screen = new TASMode(Miloslav.palette(), TIC80.font());
        dog.run();
        dog.setPosition(80, 100);
        
        for(int i = 0; i < 14; i++){
            dogs[i] = new Dog();
            dogs[i].run();
            dogs[i].setPosition(10+i*15, 32+i*4);
        }
        x = 0;
        y = 0;
        screen.setMap(TileMaps.getGardenPath(), TileMaps.getTiles());
    }
    
    void shutdown(){
        screen = null;
    }
    public void resume(){
        screen.beforeFlush();
        screen.flush();
    }
    
    void update(){
        screen.clear(0);
        
        if( Button.A.justPressed() )
            Game.changeState( new Main() );
            
        if( Button.B.justPressed() ){
            mapSwitch = !mapSwitch;
            if(mapSwitch){
                screen.setMap(TileMaps.getTestMap(), TileMaps.getTiles());
            }else{
                screen.setMap(TileMaps.getGardenPath(), TileMaps.getTiles());
            }
        }
        
        if(Button.Down.isPressed()){
            dog.y = dog.y+2;
        }
        if(Button.Up.isPressed()){
            dog.y = dog.y-2;
        }
        if(Button.Right.isPressed()){
            dog.x = dog.x+2;
            dog.setMirrored(false);
        }
        if(Button.Left.isPressed()){
            dog.x = dog.x-2;
            dog.setMirrored(true);
        }
        
        if(TileMaps.gardenPathData((int)dog.x/16, (int)dog.y/16) != 0){
            dog.setFlipped(true);
        }else{
            dog.setFlipped(false);
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
