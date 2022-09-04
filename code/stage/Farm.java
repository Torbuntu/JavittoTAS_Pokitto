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
    
    Guy guy;
    Tools tools;
    
    Hand handIcon;
    
    ubyte movement = 0, direction = 0, selected = 0, use = 0, water = 0;
    
    ubyte tw = 10, th = 8;
    
    ubyte tilled = 55, watered = 56;
    
    // animated water
    ubyte animate = 15, id = 57;
    
    /**
     * Uses a collection of tile ID's to update the
     * water tiles to animate the waves.
     * 
     * 4 tile IDs used.
     */ 
    void updateAnimation(){
        if(animate > 0){
            animate--;
        } else {
            animate = 10;
            id++;
            if(id > 60){
                id = 57;
            }
            for(int y = 2; y < 22; y++){
                screen.setTile(id, 2, y);
            }
            System.out.println(screen.fps());
        }
    }
    
    void init() {
        screen = Globals.screen;
        
        dm = Globals.dataManager;
        
        guy = new Guy();
        guy.idle();
        
        handIcon = new Hand();
        handIcon.hand();
        
        tools = new Tools();
        
        x = 0;
        y = 0;
        px= 199;
        py= 68;
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
        // -- UPDATE --
        
        // Check the tile and data
        if( Button.A.justPressed() ) {
            System.out.println(getGuyTile());
            System.out.println(getGuyFarmTileData());
        }

        // Use equipped tool
        if( Button.B.justPressed() ){
            if(getGuyFarmTileData()==TileMaps.TRAVEL) {
                Game.changeState(new Travel());
            }
            
            if(selected == 0 && getGuyFarmTileData()==TileMaps.FIELD) {
                screen.setTile(tilled, (px+6)/tw, (py+8)/th);
                guy.hoe();
            }
            if(selected == 1) {
                // if on field and bucket not empty, water.
                if(getGuyFarmTileData()==TileMaps.FIELD) {
                    if(water > 0 && getGuyTile() == tilled) {
                        screen.setTile(watered, (px+6)/tw, (py+8)/th);
                        water--;
                    }
                }
                if(TileMaps.getFarmMapData((px/tw)-1, py/th)==TileMaps.WATER) {
                    water = 12;    
                }
                
                guy.water();
            }
            
            use=8;
        }
        
        // Open menu / Select from menu
        if( Button.C.justPressed() ){
            selected++;
            if(selected > 4) selected = 0;
            System.out.println("Selected: " + (int)selected);
        }
        
        // Movement
        if(menu){
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
        } else {
            moveGuy();
        }
        
        updateAnimation();
        
        // -- DRAW --
        screen.clear(0);
        
        // -- TOOLS --
        switch(water) {
            case 1: 
                tools.water1();
                break;
            case 2:
                tools.water2();
                break;
            case 3:
                tools.water3();
                break;
            case 4:
                tools.water4();
                break;
            case 5:
                tools.water5();
                break;
            case 6:
                tools.water6();
                break;
            case 7:
                tools.water7();
                break;
            case 8:
                tools.water8();
                break;
            case 9:
                tools.water9();
                break;
            case 10:
                tools.water10();
                break;
            case 11:
                tools.water11();
                break;
            case 12:
                tools.waterFull();
                break;
        }
        if(water > 0) tools.draw(screen, 2, 26);
        
        // draw selection box
        tools.selected();
        tools.draw(screen, 2, 6+selected*20);
        
        tools.hoe();
        tools.draw(screen, 2, 6);
        tools.can();
        tools.draw(screen, 2, 26);
        tools.planter();
        tools.draw(screen, 2, 46);
        tools.basket();
        tools.draw(screen, 2, 66);
        // TODO - unlock the rod to fish
        // if(rodUnlocked) {
        tools.rod();
        tools.draw(screen, 2, 86);
        // }
        // -- END TOOLS --

        guy.draw(screen);

        screen.drawMap(x, y);

        screen.flush();
    }
    
    int getGuyTile() {
        return screen.getTile((px+6)/tw, (py+8)/th);
    }
    
    int getGuyFarmTileData() {
        return TileMaps.getFarmMapData((px+6)/tw, (py+8)/th);
    }
    
    void moveGuy() {
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
                movement = 4;
                direction = 3;
                guy.down();
            } else
            if(Button.Up.isPressed()){
                movement = 4;
                direction = 1;
                guy.down();
            } else 
            if(Button.Right.isPressed()){
                movement = 5;
                direction = 2;
                guy.setMirrored(true);
                guy.down();
            } else
            if(Button.Left.isPressed()){
                if(TileMaps.getFarmMapData((px-8)/tw, py/th) != TileMaps.WATER){
                    movement = 5;
                }
                direction = 0;
                guy.setMirrored(false);
                guy.down();
            } else {
                guy.idle();
            }
        }
        guy.setPosition(px, py);
    }
}