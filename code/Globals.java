package code;


import code.manager.CropManager;
import code.manager.DataManager;

import femto.mode.TASMode;
import femto.font.TIC80;
import femto.palette.Miloslav;


public class Globals {
    
    // The screen for the game.
    public static final TASMode screen = new TASMode(Miloslav.palette(), TIC80.font());
    
    static final DataManager dataManager = new DataManager();
    static final CropManager cropManager = new CropManager();
    
    public static void init() {
        
        
    }
    
}