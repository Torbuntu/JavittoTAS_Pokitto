package code.stage;

import code.Globals;

import code.manager.DataManager;

import femto.Game;
import femto.State;
import femto.input.Button;

import sprites.Guy;
import sprites.Tools;

public class Farm extends State {
    
    // the screenmode we want to draw with
    TASMode screen;
    
    DataManager dm;
    
    // Player x and y coordinates.
    int px,py;
    
    Guy guy;
    Tools tools;
    
    // Field crop types and growth levels.
    // TODO ? - Move to crop manager?
    byte[] type;
    byte[] growth;
    
    ubyte movement = 0, direction = 0, selected = 0, use = 0, water = 0;
    
    ubyte hour = 0, dayProgress;
    
    // Tile width and height
    ubyte tw = 10, th = 8;
    
    // Tiles for field tilled/watered
    ubyte tilled = 4, watered = 5;
    
    // animated water
    ubyte animate = 15;
    ubyte id = 0;
    ubyte[] ids = {0, 1, 2, 1};
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
            if(id > 3){
                id = 0;
            }
            for(int y = 2; y < 22; y++){
                screen.setBGTile(ids[id], 3, y);
            }
        }
    }
    
    /**
     * Day progression. 
     * Hours increment dayProgress on 30 tics. 
     * Day progresses when 200 hours pass. 
     * hehe 200 hours. Busy days
    */
    void updateDayProgress() {
        hour++;
        if (hour > 30) {
            dayProgress++;
            hour = 0;
        }
        if(dayProgress >= 200) {
            dayProgress = 0;
            // TODO - New day dialog
        }
    }
    
    void init() {
        screen = Globals.screen;

        dm = Globals.dataManager;
        var field = dm.readData("field");
        type = new byte[120];
        growth = new byte[120];
        ubyte id = 0;
        for (int i = 0; i < (120 * 2); i += 2) {
            if (id > 120) return;
            type[id] = field[i];
            growth[id] = field[i + 1];
            id++;
        }
        
        guy = new Guy();
        guy.idle();
        
        tools = new Tools();
        
        // Player initial position
        px = 199;
        py = 68;
        
        // Set the farm background and foreground maps
        screen.setBGMap(TileMaps.getFarmMap(), TileMaps.getTiles());
        screen.setFGMap(TileMaps.getFieldMap(), TileMaps.getTiles());
    }
    

    
    void update(){
        // -- UPDATE --
        
        // Check the tile and data
        if( Button.A.justPressed() ) {
            System.out.println(screen.fps());
            System.out.println(px + ","+py);
            System.out.println(getFieldId());
            // saveAndQuit();
        }

        // Use equipped tool
        if( Button.B.justPressed() ){
            // if(getGuyFarmTileData()==TileMaps.TRAVEL) {
            //     Game.changeState(new Travel());
            // }
            
            if(selected == 0 && getGuyFarmTileData()==TileMaps.FIELD) {
                screen.setBGTile(tilled, (px+6)/tw, (py+8)/th);
                guy.hoe();
            }
            if(selected == 1) {
                // if on field and bucket not empty, water.
                if(getGuyFarmTileData()==TileMaps.FIELD) {
                    if(water > 0 && getGuyTile() == tilled) {
                        screen.setBGTile(watered, (px+6)/tw, (py+8)/th);
                        water--;
                    }
                }
                // if water on the left, fill it up.
                if(TileMaps.getFarmMapData((px/tw)-1, py/th)==TileMaps.WATER) {
                    water = 12;    
                }
                guy.water();
            }
            if(selected == 2) {
                // if on tilled land, plant seed
                if(getGuyFarmTileData()==TileMaps.FIELD && getGuyTile() >= tilled){
                    byte id = getFieldId();
                    // TODO - inventory system
                    type[id] = 2;
                    growth[id] = 1;
                    var x = (px-49) / 10;
                    var y = (py-84) / 8;
                    screen.setFGTile(80, x, y);
                }
            }
            
            use=8;
        }
        
        if( Button.C.justPressed() ){
            selected++;
            if(selected > 4) selected = 0;
        }
        
        // Movement
        updateDayProgress();

        moveGuy();
        
        updateAnimation();
        
        // -- DRAW --
        
        // -- TOOLS --
        if(water > 0) {
            updateWaterLevel();
            tools.draw(screen, -2, 126);
        }
        
        // draw selection box
        tools.selected();
        tools.draw(screen, -2, 150+-selected*24);

        // -- END TOOLS --

        guy.draw(screen);

        screen.drawBGMap(0, 0);
        screen.drawFGMap(-50, 88);
        screen.flush();
    }
    
    int getGuyTile() {
        return screen.getBGTile((px+6)/tw, (py+8)/th);
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
    
    /**
     * Returns the position ID of the field
     */ 
    byte getFieldId() {
        var x = (px-49) / 10;
        var y = (py-84) / 8;
        return  x + y * 12;
    }
    
    // TODO - I hate this. Seems really wasteful but I'm unsure how to do a better water level indicator.
    void updateWaterLevel() {
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
    }
    
    void saveAndQuit() {
        // Collect field to byte array
        byte[] field = new byte[120 * 2];
        ubyte id = 0;
        for (int i = 0; i < (120 * 2); i += 2) {
            field[i] = (byte) type[id];
            field[i + 1] = (byte) growth[id];
            id++;
        }
        dm.writeData("field", field);
    }
    
    
    
    
    
    void shutdown() {
        screen = null;
    }
    
    public void resume() {
        screen.beforeFlush();
        screen.flush();
    }
}