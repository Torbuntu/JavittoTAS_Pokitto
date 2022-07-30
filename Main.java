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
    
    int c = 0;
    
    Bot bot;
    BotHead botHead;
    
    ubyte movement = 0, direction = 0;
    
    public static void main(String[] args){
        Game.run( TIC80.font(), new Main() );
    }
    
    void init(){
        screen = new TASMode(Miloslav.palette(), TIC80.font());
        bot = new Bot();
        bot.setPosition(112,80);
        bot.idle();
        
        botHead = new BotHead();
        botHead.setPosition(112,72);
        
        x = 0;
        y = 0;
        screen.setMap(TileMaps.getFarmMap(), TileMaps.getTiles());
    }
    
    void shutdown(){
        screen = null;
    }
    public void resume(){
        screen.beforeFlush();
        screen.flush();
    }
    
    void update(){
        c++;
        if(c==100){
            c = 0;
            System.out.println((int)screen.fps());
            System.out.println("X: " + x);
        }
        screen.clear(0);
        
        if( Button.A.justPressed() ) {
        }
            
        if( Button.B.justPressed() ){
        }
        
        if( Button.C.justPressed() ){
        }
        
        if(movement > 0){
            movement--;
            switch(direction){
                case 0: x--;break;
                case 1: y++;break;
                case 2: x++;break;
                case 3: y--;break;
            }
        } else {
            if(Button.Down.isPressed()){
                // TODO down ladder / drop platform
                // y-=16;
                movement = 16;
                direction = 3;
            } else
            if(Button.Up.isPressed()){
                // TODO, jump/ladder
                // y+=16;
                movement = 16;
                direction = 1;
            } else 
            if(Button.Right.isPressed()){
                // dog.x = dog.x+2;
                // bot.x+=2;
                // botHead.x+=2;
                // x+=16;
                movement = 16;
                direction = 2;
                bot.setMirrored(true);
                botHead.setMirrored(true);
                bot.walkHori();
            } else
            if(Button.Left.isPressed()){
                // bot.x-=2;
                // botHead.x-=2;
                // x-=16;
                movement = 16;
                direction = 0;
                bot.setMirrored(false);
                botHead.setMirrored(false);
                bot.walkHori();
            } else {
                bot.idle();
            }
        }
        
        

        
        botHead.y+=vy;
        bot.y+=vy;
        
        bot.draw(screen);
        botHead.draw(screen);
        screen.drawMap(x, y);
        
        screen.flush();
    }
    
}
