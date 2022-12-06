package code.manager;


import code.Globals;
/**
 * Manages the state of crops in the Field stage.
 * 
 * Lifecycles:
 * 
 * Field tile:
 * 0 - Dry unplowed field
 * 1 - Plowed
 * >= 2  - Crops (cannot water unplowed field)
 * 
 * Crop tiles (requires plowed):
 * - Initialized (Seed planted)
 * - Day N Plant (based on crop type)
 * - Dead (Went 2 days without water. Requires Plowing to remove)
 * - Harvestable
 * 
 * Example on disk:
 * 0,0, 2,3, 2,-1
 * Three tiles:
 * EMPTY, 
 * Dry plowed and with crop 2 at day 3, 
 * Dry plowed with dead plant 2
 * 
 * Crop graphics will need to be managed using Tiles since
 * the use of Sprites is limited and quite heavy on resources
 * with the current implementation. 
 * 
 * To save on overall tiles, they will need to reuse the same
 * tiles for many stages to avoid running out of available tile
 * space in memory. 
 * 
 * 
 * 
 */
public class CropManager {
    
    DataManager dm;
    TASMode screen;
    // 12x10 field size for 120 possible crops.
    // Field crop types and growth levels.
    byte[] type;
    byte[] growth;
    
    /**
     * I don't know if I'll make real names for the crops.
     * 
     * So far the game is made without word dialog, 
     * instead using symbols.
     * 
     * A system I'm playing with here is having the name
     * of the crop be a letter a -> z and a coresponding
     * quantity [letter]q. 
     * 
     * If this becomes too confusing (more like when) I will
     * begin naming the crops instead of this silliness. 
     */ 
    ubyte a, aq;
    
    // Duplicated from Farm.java
    ubyte watered = 5;
    
    public CropManager() {
        System.out.println("Crop Manager Begin Init");
        dm = Globals.dataManager;
        screen = Globals.screen;
        var field = dm.readData("field");
        type = new byte[120];
        growth = new byte[120];
        ubyte id = 0;
        for (int i = 0; i < 240; i += 2) {
            if (id > 120) break;
            type[id] = field[i];
            growth[id] = field[i + 1];
            id++;
        }
    }
    
    void setCrops() {
        ubyte id = 0;
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 12; x++){
                if(type[id] < 0) screen.setFGTile(6, x, y);
                else if(type[id] != 0) {
                    screen.setFGTile(type[id]+growth[id]+7, x, y);
                    screen.setBGTile(4, (50 + x*10)/10, (88+y*8)/8);
                }
                id++;
            }
        }
    }

    /**
     * TODO - This needs to actually use correct crop types.
     */ 
    void plant(byte fieldId) {
        // type, typeQ check
        type[fieldId] = 1;
        growth[fieldId] = 1;
    }
    
    /**
     * Ran at the end of the day cycle. 
     * Update the crops which are watered.
     * Set to dead those which were not. 
     * I think this time around we will be very strict on water.
     */ 
    void update() {
        ubyte id = 0;
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 12; x++){
                if(screen.getBGTile((50 + x*10)/10, (88+y*8)/8) == watered) {
                    screen.setBGTile(4, (50 + x*10)/10, (88+y*8)/8);
                    if(type[x+y*12] > 0) {
                        if(growth[x+y*12] > 0 && growth[x+y*12] < 10) {
                            // type[x+y*12]
                            System.out.println("Grow!!" + (int)growth[x+y*12]);
                            growth[x+y*12] = growth[x+y*12]+1;
                            screen.setFGTile(screen.getFGTile(x, y)+1, x, y);
                        }
                    }
                } else {
                    if(type[x+y*12] > 0) {
                    // Kill the dry plants
                        growth[x+y*12] = -1;
                        type[x+y*12] = -1;
                        screen.setFGTile(6, x, y);
                    }
                }
                id++;
            }
        }
    }
    
    void saveAndQuit() {
        // Collect field to byte array
        byte[] field = new byte[240];
        ubyte id = 0;
        for (int i = 0; i < 240; i += 2) {
            field[i] = (byte) type[id];
            field[i + 1] = (byte) growth[id];
            id++;
        }
        dm.writeData("field", field);
    }
}