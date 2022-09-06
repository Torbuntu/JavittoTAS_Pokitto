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
 * Crop graphics will need to be managed using Tiles since
 * the use of Sprites is limited and quite heavy on resources
 * with the current implementation. 
 * 
 * To save on overall tiles, they will need to reuse the same
 * tiles for many stages to avoid running out of available tile
 * space in memory. 
 * 
 * Since there needs to be 2 variations for every tile for wet/dry.
 * 
 */
public class CropManager {
    // 12x10 field size for 120 possible crops.
    public CropManager() {
        
    }
}