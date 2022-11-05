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
    // 12x10 field size for 120 possible crops.
    // Field crop types and growth levels.
    byte[] type;
    byte[] growth;
    
    public CropManager() {
        
        dm = Globals.dataManager;
        var field = dm.readData("field");
        type = new byte[120];
        growth = new byte[120];
        ubyte id = 0;
        for (int i = 0; i < (120 * 2); i += 2) {
            if (id > 120) break;
            type[id] = field[i];
            growth[id] = field[i + 1];
            id++;
        }
    }
    
    void plow(byte fieldId) {
        type[fieldId] = 2;
        growth[fieldId] = 1;
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
}