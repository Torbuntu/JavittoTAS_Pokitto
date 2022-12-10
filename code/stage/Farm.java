package code.stage;

import code.Globals;

import code.manager.CropManager;
import code.manager.DataManager;

import femto.Game;
import femto.State;
import femto.input.Button;

import sprites.Guy;
import sprites.Tools;
import sprites.DialogCorner;

import sprites.Sun;

public class Farm extends State {
    
    // the screenmode we want to draw with
    TASMode screen;
    
    DataManager dm;
    CropManager cropManager;
    
    DialogCorner dialogCorner;
    boolean dayEnd = false, talkTree = false, dayStart = false;
    
    // Player x and y coordinates.
    int px,py;
    
    Guy guy;
    Tools tools;
    Sun sun;
    
    ubyte movement = 0, direction = 0, selected = 0, use = 0, water = 0;
    

    // Tile width and height
    ubyte tw = 10, th = 8;
    
    // Tiles for field tilled/watered
    ubyte tilled = 4, watered = 5, deadPlant = 6;
    
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
            for(int y = 4; y < 22; y++){
                screen.setBGTile(ids[id], 3, y);
            }
        }
    }

    void init() {
        screen = Globals.screen;
        cropManager = Globals.cropManager;
        dialogCorner = new DialogCorner();
        
        guy = new Guy();
        guy.idle();
        
        sun = new Sun();
        sun.idle();
        
        tools = new Tools();
        
        // Player initial position
        px = 199;
        py = 68;
        
        // Set the farm background and foreground maps
        screen.setBGMap(TileMaps.getFarmMap(), TileMaps.getTiles());
        screen.setFGMap(TileMaps.getFieldMap(), TileMaps.getTiles());
        
        cropManager.setCrops();
        
        screen.drawFGMap(-50, 88);
    }
    
    
    /**
     * The dialog box. It is quite chunky. 
     * 
     * The sprites could be significantly reduced 
     * (lots of solid colors right now)
     */
    void drawDialogBox() {
        // Corners
        dialogCorner.corner();
        dialogCorner.setFlipped(false);
        dialogCorner.setMirrored(false);
        dialogCorner.draw(screen, 0,0);
        dialogCorner.setMirrored(true);
        dialogCorner.draw(screen, 210, 0);
        
        dialogCorner.setFlipped(true);
        dialogCorner.setMirrored(false);
        dialogCorner.draw(screen, 0,16);
        dialogCorner.setMirrored(true);
        dialogCorner.draw(screen, 210, 16);
        
        // Side Edges
        dialogCorner.edge();
        dialogCorner.setMirrored(true);
        dialogCorner.draw(screen, 0, 8);
        
        dialogCorner.setMirrored(false);
        dialogCorner.draw(screen, 210, 8);
        
        // Top and Bottom Edges
        dialogCorner.topBottom();
        for(int i = 0; i < 20; i++) {
            dialogCorner.draw(screen, 10+i*10, 0);
        }
        dialogCorner.setFlipped(false);
        for(int i = 0; i < 20; i++) {
            dialogCorner.draw(screen, 10+i*10, 16);
        }
        
        // Fill
        dialogCorner.fill();
        for(int i = 0; i < 20; i++) {
            dialogCorner.draw(screen, 10+i*10, 8);
        }
        
    }
    
    void updateEndOfDay() {
        
    }
    
    void updateTreeDialog() {
        
    }

    
    void update() {
        // -- UPDATE --
        
        
        if(dayStart) {
            // Do something?
            drawDialogBox();
            if(Button.A.justPressed() || Button.B.justPressed()) {
                dayStart = false;
            }
            screen.flush();
            return;
        }
        // TODO - End Day Dialog. Do not update time in dialog.
        if(dayEnd) {
            drawDialogBox();
            if(Button.A.justPressed()) {
                dayEnd = false;
                cropManager.update();
                TimePiece.init();
                dayStart = true;
                cropManager.saveAndQuit();
                // End day, update progress
            }
            if(Button.B.justPressed()) {
                dayEnd = false;
                // Just close the dialog.
            }
            
            screen.flush();
            return;
        }
        
        
        // TODO - Talk to Tree dialog. Do not update time in dialog.
        if(talkTree) {
            drawDialogBox();
            if(Button.A.justPressed() || Button.B.justPressed()) {
                talkTree = false;
            }
            
            screen.flush();
            return;
        }
        
        
        // Check the tile and data
        if( Button.A.justPressed() ) {
            if(TileMaps.getFarmMapData((px+2)/tw, (py+14)/th) == TileMaps.TRAVEL) {
                Game.changeState(new Town());
            }
            
            if(py == 44 && px >= 159 && px <= 169) {
                // End day dialog
                dayEnd = true;
            }
            if(py == 52 && px >= 189 && px <= 199) {
                // Talk to the tree 
                talkTree = true;
                // doTreeEvent thing here.
            }
            
            // debug info
            if(true){
                System.out.println(screen.fps());
            }
            // saveAndQuit();
        }

        // Use equipped tool
        if( Button.B.justPressed() ){
            // if(getGuyFarmTileData()==TileMaps.TRAVEL) {
            //     Game.changeState(new Travel());
            // }
            
            if(selected == 0 && getGuyFarmTileData()==TileMaps.FIELD) {
                var x = (px-49) / 10;
                var y = (py-84) / 8;
                if(screen.getFGTile(x, y) == deadPlant) {
                    cropManager.clearCrop(x, y);
                    setGuyTile(tilled-1);
                } else {
                    setGuyTile(tilled);
                }
                guy.hoe();
            }
            if(selected == 1) {
                // if on field and bucket not empty, water.
                if(getGuyFarmTileData()==TileMaps.FIELD) {
                    if(water > 0 && getGuyTile() == tilled) {
                        setGuyTile(watered);
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
                    cropManager.plant(id);
                    var x = (px-49) / 10;
                    var y = (py-84) / 8;
                    screen.setFGTile(7, x, y);
                }
            }
            
            use=8;
        }
        
        if( Button.C.justPressed() ){
            selected++;
            if(selected > 4) selected = 0;
        }
        
        // Time management 
        if(TimePiece.updateDayProgress()) {
            cropManager.update();
            dayStart = true;
        }
        
        // Movement
        moveGuy();
        updateAnimation();
        
        // -- DRAW --
        
        // SUN
        sun.draw(screen, screen.width()-TimePiece.getTime(), 2);
        
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
        
        screen.flush();
    }
    
    void setGuyTile(int tileId) {
        screen.setBGTile(tileId, (px+6)/tw, (py+8)/th);
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
                var tileDown = TileMaps.getFarmMapData((px+6)/tw, (py+16)/th);
                if(tileDown != TileMaps.SOLID){
                    movement = 4;
                }
                direction = 3;
                guy.down();
            } else
            if(Button.Up.isPressed() && py > 32){
                var tileUp = TileMaps.getFarmMapData((px+6)/tw, (py)/th);
                if(tileUp != TileMaps.SOLID){
                    movement = 4;
                }
                direction = 1;
                guy.down();
            } else 
            if(Button.Right.isPressed()){
                var tileRight = TileMaps.getFarmMapData((px+16)/tw, (py+6)/th);
                if(tileRight != TileMaps.SOLID){
                    movement = 5;
                }
                direction = 2;
                guy.setMirrored(true);
                guy.down();
            } else
            if(Button.Left.isPressed()){
                var tileLeft = TileMaps.getFarmMapData((px-8)/tw, (py+8)/th);
                if(tileLeft != TileMaps.WATER && tileLeft != TileMaps.SOLID){
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
        cropManager.saveAndQuit();
    }
    
    void shutdown() {
        cropManager = null;
        screen = null;
    }
    
    public void resume() {
        screen.beforeFlush();
        screen.flush();
    }
}