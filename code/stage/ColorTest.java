package code.stage;

import code.Globals;

import femto.Game;
import femto.State;
import femto.input.Button;

import sprites.Guy;

public class ColorTest extends State {
        // the screenmode we want to draw with
    TASMode screen;
    
    Guy guy;
    
    int color = 0, count = 0;
    void init() {
        screen = Globals.screen;
        guy = new Guy();
        guy.idle();
        guy.setPosition(0, 0);
        // color = Math.random(20, 250);
        
        screen.setBGMap(TileMaps.getFarmMap(), TileMaps.getTiles());
    }
    boolean flip = false, mirror = false;
    void update() {
        count++;
        if(count>60){
            count=0;
            System.out.println(screen.fps());
        }
        
        if( Button.A.justPressed() ){
            Game.changeState(new ColorTest());
        }
        guy.hoe();
        
        for(int i = 0; i < 20; i++){
            guy.setMirrored(mirror);
            mirror = !mirror;
            for(int j = 0; j < 5; j++){
                guy.setFlipped(flip);
                flip = !flip;
                guy.draw(screen, i*10, count+j*14);
            }
        }
        
        screen.flush();
    }
    
    void resume() {
        screen.beforeFlush();
        screen.flush();
    }
    
    void shutdown() {
        screen = null;
    }
}