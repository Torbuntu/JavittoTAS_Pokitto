package code.manager;

/**
 * Manages the state of crops in the Field stage.
 * 
 * Lifecycles:
 * 
 * Field tile:
 * - Dry unplowed field
 * - Plowed
 * - Watered (cannot water unplowed field)
 * 
 * Crop tiles (requires plowed):
 * - Wet or Dry field tile (always one or the other for the following)
 * - Initialized (Seed planted)
 * - Day N Plant (based on crop type)
 * - Dead (Went 2 days without water. Requires Plowing to remove)
 * - Harvestable
 * 
 */
public class CropManager {
    public CropManager() {
        
    }
}