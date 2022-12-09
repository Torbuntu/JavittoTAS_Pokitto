package code.stage;

import code.Globals;

import femto.Game;
import femto.State;
import femto.input.Button;

import sprites.Guy;

public class Town extends State {
    
    // the screenmode we want to draw with
    TASMode screen;
    Guy guy;
    
    ubyte px, py;
    
    void init(){
        screen = Globals.screen;
        guy = new Guy();
        guy.idle();
        px=32;
        py=100;
        screen.setBGMap(TileMaps.getTownMap(), TileMaps.getTiles());
        screen.disableFiller(2);
    }
    
    void update() {
        if( Button.C.justPressed() ){
            Game.changeState(new Farm());   
        }
        
        guy.setPosition(px, py);
        // -- DRAW --
        guy.draw(screen);
        
        screen.flush();
    }
    
    void shutdown() {
        screen.enableFGFiller();
        screen = null;
    }
    
    public void resume() {
        screen.beforeFlush();
        screen.flush();
    }
    
}