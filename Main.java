import femto.mode.TASMode;
import femto.Game;
import femto.State;
import femto.input.Button;
import femto.font.TIC80;
import femto.palette.Miloslav;
import sprites.Bot;
import sprites.BotHead;
import sprites.Tools;

class Main extends State {

    // the screenmode we want to draw with
    TASMode screen;
    
    int x,y,px,py;
    
    boolean mapSwitch = false, menu = false;
    
    int c = 0;
    
    Bot bot;
    BotHead botHead;
    Tools[] tools;
    
    ubyte movement = 0, direction = 0, selected = 0, use = 0;
    
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
        
        tools = new Tools[]{
            new Tools(),
            new Tools(),
            new Tools(),
            new Tools()
        };
        tools[0].hoe();
        tools[1].can();
        tools[2].planter();
        tools[3].rod();
        for(int i = 0; i < 4; i++){
            tools[i].setPosition(4,148);
        }
        
        x = 0;
        y = 0;
        px=96;
        py=80;
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
        // -- DEBUG --
        c++;
        if(c==100){
            c = 0;
            System.out.println((int)screen.fps());
        }
        
        // -- UPDATE --
        
        if( Button.A.justPressed() ) {
        }
            
        if( Button.B.justPressed() ){
            use=8;
        }
        
        if( Button.C.justPressed() ){
            if(menu) {
                for(int i = 0; i < 4; i++){
                    tools[i].setPosition(4,148);
                }
            } else {
                for(int i = 0; i < 4; i++){
                    tools[i].setPosition(4,148-i*24);
                }
            }
            
            menu = !menu;
        }
        
        // Movement
        if(!menu){
            if(movement > 0){
                movement--;
                switch(direction){
                    case 0: 
                        x-=2;
                        break;
                    case 1: 
                        y+=2;
                        break;
                    case 2:
                        x+=2;
                        break;
                    case 3: 
                        y-=2;
                        break;
                }
            } else if(use == 0) {
                if(Button.Down.isPressed()){
                    movement = 8;
                    direction = 3;
                } else
                if(Button.Up.isPressed()){
                    movement = 8;
                    direction = 1;
                } else 
                if(Button.Right.isPressed()){
                    movement = 8;
                    direction = 2;
                    bot.setMirrored(true);
                    botHead.setMirrored(true);
                    bot.walkHori();
                } else
                if(Button.Left.isPressed()){
                    movement = 8;
                    direction = 0;
                    bot.setMirrored(false);
                    botHead.setMirrored(false);
                    bot.walkHori();
                } else {
                    bot.idle();
                }
            }
            bot.setPosition(px, py);
            botHead.setPosition(px, py-8);
        } else {
            if(Button.Down.justPressed()){
                selected--;
                if(selected < 0)selected=0;
            }
            if(Button.Up.justPressed()){
                selected++;
                if(selected > 3)selected=3;
            }  
            if(Button.Right.justPressed()){
                // if on planter, show seeds.
            } 
            if(Button.Left.justPressed()){
                // if on planter, show seeds.
            }
        }
        
        
        // -- DRAW --
        screen.clear(0);

        if(menu){
            for(int i = 0; i < 4; i++){
                tools[i].draw(screen);
            }
        } else {
            tools[selected].draw(screen);
        }
        bot.draw(screen);
        botHead.draw(screen);
        
        if(use > 0){
            use--;
            if(direction == 0)tools[selected].setPosition(px-24,py);
            if(direction == 1)tools[selected].setPosition(px,py-24);
            if(direction == 2)tools[selected].setPosition(px+bot.width(),py);
            if(direction == 3)tools[selected].setPosition(px,py+bot.height());
            tools[selected].draw(screen);
            tools[selected].setPosition(4,148);
        }
        
        screen.drawMap(x, y);
        
        screen.flush();
    }
    
}
