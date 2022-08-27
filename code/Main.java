package code;

import femto.Game;
import femto.State;
import femto.sound.Mixer;
import femto.font.TIC80;

import code.Globals;

import code.stage.Farm;

class Main extends State {
    
    public static void main(String[] args){
        // TODO - Create Globals manager
        Globals.init();
        // TODO - Start on "new code.stage.Title"
        Mixer.init(8000);
        Game.run( TIC80.font(), new Farm() );
    }
    
    void init(){

    }
    
    void shutdown(){
        
    }

    void update(){
        
    }
    
}
