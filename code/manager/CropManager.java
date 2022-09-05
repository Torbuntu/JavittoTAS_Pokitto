package code.manager;

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
 */
public class CropManager {
    // 8x6 field size for 48 possible crops.
    public CropManager() {
        
    }
}