import femto.mode.TASMode;
import femto.Game;
import femto.State;
import femto.input.Button;
import femto.font.TIC80;
import femto.palette.Miloslav;
import sprites.Bot;
import sprites.BotHead;

class Main extends State {

    // the screenmode we want to draw with
    TASMode screen;
    
    int x,y, vy=0;
    
    boolean mapSwitch = false;
    
    Bot bot;
    BotHead botHead;
    
    public static void main(String[] args){
        Game.run( TIC80.font(), new Main() );
    }
    
    void init(){
        screen = new TASMode(Miloslav.palette(), TIC80.font());
        bot = new Bot();
        bot.setPosition(162,30);
        bot.idle();
        
        botHead = new BotHead();
        botHead.setPosition(162,22);
        
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
        
        if( Button.A.justPressed() ) {
            Game.changeState( new Main() );
        }
            
        if( Button.B.justPressed() ){
            mapSwitch = !mapSwitch;
            if(mapSwitch){
                screen.setMap(TileMaps.getTestMap(), TileMaps.getTiles());
            }else{
                screen.setMap(TileMaps.getGardenPath(), TileMaps.getTiles());
            }
        }
        
        if(Button.Down.isPressed()){
            // TODO down ladder / drop platform
        }
        if(Button.Up.isPressed()){
            // TODO, jump/ladder
        }
        if(Button.Right.isPressed()){
            // dog.x = dog.x+2;
            bot.x+=2;
            botHead.x+=2;
            bot.setMirrored(true);
            botHead.setMirrored(true);
            bot.walkHori();
        }else
        if(Button.Left.isPressed()){
            bot.x-=2;
            botHead.x-=2;
            bot.setMirrored(false);
            botHead.setMirrored(false);
            bot.walkHori();
        }else {
            bot.idle();
        }
        
        if(TileMaps.gardenPathData((int)bot.x/16, (int)(bot.y+1)/16) != 0){
            vy = 0;
            
        }else{
            vy = 3;
        }
        
        botHead.y+=vy;
        bot.y+=vy;
        
        bot.draw(screen);
        botHead.draw(screen);
        screen.drawMap(x, y);
        
        screen.flush();
        
        System.out.println(screen.fps());
    }
    
}
