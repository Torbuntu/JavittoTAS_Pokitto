package code;

import femto.Game;
import femto.State;
import femto.sound.Mixer;
import femto.font.TIC80;

import code.Globals;

import code.stage.Farm;
import code.stage.ColorTest;
import code.stage.RandomTest;

class Main extends State {
    
    public static void main(String[] args){
        // TODO - Create Globals manager
        Globals.init();
        // TODO - Start on "new code.stage.Title"
        Mixer.init(8000);
        // Game.limitFPS(30);
        // Game.run( TIC80.font(), new ColorTest() );
        Game.run( TIC80.font(), new Farm() );
    }
    
    void init(){

    }
    
    void shutdown(){
        
    }

    void update(){
        
    }
    
}
