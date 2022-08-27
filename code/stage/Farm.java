package code.stage;

import code.Globals;

import code.manager.DataManager;

import femto.Game;
import femto.State;
import femto.input.Button;

import sprites.Guy;
import sprites.Tools;
import sprites.Hand;

public class Farm extends State {
    
    // the screenmode we want to draw with
    TASMode screen;
    
    DataManager dm;
    
    int x,y,px,py;
    
    boolean mapSwitch = false, menu = false;
    
    int c = 0;
    
    Guy guy;
    Tools[] tools;
    
    Hand handIcon;
    
    ubyte movement = 0, direction = 0, selected = 0, use = 0, water = 0;
    
    void init() {
        screen = Globals.screen;
        
        dm = Globals.dataManager;
        
        guy = new Guy();
        guy.idle();
        
        
        handIcon = new Hand();
        handIcon.hand();
        
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
        px=114;
        py=82;
        screen.setMap(TileMaps.getFarmMap(), TileMaps.getTiles());
    }
    
    void shutdown() {
        screen = null;
    }
    
    public void resume() {
        screen.beforeFlush();
        screen.flush();
    }
    
    void update(){
        // -- DEBUG --
        // c++;
        // if(c==100){
        //     c = 0;
        //     System.out.println((int)screen.fps());
        //     System.out.println(selected);
        //     System.out.println(px + ","+py);
        // }
        
        // -- UPDATE --
        
        // Check
        if( Button.A.justPressed() ) {
            System.out.println(screen.getTile(px/16, py/16));
            System.out.println(TileMaps.getFarmMapData(px/16, py/16));
        }

        // Use equipped tool
        if( Button.B.justPressed() ){
            if(TileMaps.getFarmMapData((px/16), py/16)==TileMaps.TRAVEL) {
                Game.changeState(new Travel());
            }
            
            if(selected == 0 && TileMaps.getFarmMapData(px/16, py/16)==TileMaps.FIELD) {
                screen.setTile(19, px/16, py/16);
                guy.hoe();
            }
            if(selected == 1) {
                // if on field and bucket not empty, water.
                if(TileMaps.getFarmMapData(px/16, py/16)==TileMaps.FIELD) {
                    if(water > 0 && screen.getTile(px/16, py/16) == 19) {
                        screen.setTile(18, px/16, py/16);
                        water--;
                    }
                }
                if(TileMaps.getFarmMapData((px/16)-1, py/16)==TileMaps.WATER) {
                    water = 7;    
                }
                
                guy.water();
            }
            
            use=8;
        }
        
        // Open menu / Select from menu
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
            System.out.println("Selected: " + (int)selected);
            menu = !menu;
        }
        
        // Movement
        if(!menu){
            if(use > 0){
                use--;
                if(selected == 0) {
                    guy.hoe();
                }
                if(selected == 1) {
                    guy.water();
                }
            }
            else if(movement > 0){
                movement--;
                switch(direction){
                    case 0: 
                        px-=2;
                        break;
                    case 1: 
                        py-=2;
                        break;
                    case 2:
                        px+=2;
                        break;
                    case 3:
                        py+=2;
                        break;
                }
            } else if(use == 0) {
                if(Button.Down.isPressed()){
                    movement = 8;
                    direction = 3;
                    guy.down();
                } else
                if(Button.Up.isPressed()){
                    movement = 8;
                    direction = 1;
                    guy.down();
                } else 
                if(Button.Right.isPressed()){
                    movement = 8;
                    direction = 2;
                    guy.setMirrored(true);
                    guy.down();
                } else
                if(Button.Left.isPressed()){
                    movement = 8;
                    direction = 0;
                    guy.setMirrored(false);
                    guy.down();
                } else {
                    guy.idle();
                }
            }
            guy.setPosition(px, py);
        } else {
            if(Button.Down.justPressed()){
                if(selected > 0)selected--;
            }
            if(Button.Up.justPressed()){
                if(selected < 3)selected++;
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
                if(i==selected){
                    handIcon.setPosition(32, 155-i*24);
                }
            }
            handIcon.draw(screen);
        } else {
            tools[selected].draw(screen);
        }
        guy.draw(screen);

        screen.drawMap(x, y);
        
        screen.flush();
    }
}