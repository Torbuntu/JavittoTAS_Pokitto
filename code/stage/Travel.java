package code.stage;

import code.Globals;

import femto.Game;
import femto.State;
import femto.input.Button;

import sprites.Guy;

public class Travel extends State {
    
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
        // screen.setMap(TileMaps.getTravelMap(), TileMaps.getTiles());
    }
    
    void update() {
        if( Button.C.justPressed() ){
            Game.changeState(new Farm());   
        }
        
        guy.setPosition(px, py);
        // -- DRAW --
        screen.clear(0);
        guy.draw(screen);
        screen.drawMap(0,0);
        screen.flush();
    }
    
    void shutdown() {
        screen = null;
    }
    
    public void resume() {
        screen.beforeFlush();
        screen.flush();
    }
    
}