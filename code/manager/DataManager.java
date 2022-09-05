package code.manager;

import femto.File;

/**
 * Manages the saved data for the game.
 * 
 * Field:
 * The array of byte data for the field itself. This marks the individual tiles state:
 * - Plowed
 * - Crop type
 * - Crop stage of growth
 * 
 * Items:
 * Determines which items have been collected or upgraded.
 * 
 * Other:
 *
 * 
 */ 
public class DataManager {
    DataManager() {
        System.out.println("Data Manager init");
    }
    
    /**
     * 120*2 - There are 120 field tiles, and 2 data points each
     * Crop Type , Crop Growth status
     * 
     */ 
    void reset() {
        byte[] field = new byte[120 * 2];
        for (int i = 0; i < (120 * 2); i++) {
            field[i] = (byte) 0;
        }
        File file = new File();
        if(file.openRW("/data/mdaled/field")){
            if(file.isOpen()){
                file.write(field);
            }
        }
    }
    
    // TODO - Make this take parameters such as a file name to save to and data to write to said file.
    void writeData(String name, byte[] data) {
        File file = new File();
        if(file.openRW("/data/mdaled/" + name)){
            if(file.isOpen()){
                file.write(data);
            }
        }
        file.close();
    }
    
    // TODO - Make this take a file name to read from and return a useful data type for use in the game.
    byte[] readData(String name) {
        File file = new File();
        byte[] result;
        if(file.openRO("/data/mdaled/" + name)){
            if(file.isOpen()){
                result = file.toArray();
            }
        }
        file.close();
        return result;
    }

}