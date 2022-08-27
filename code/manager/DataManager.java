package code.manager;

import femto.File;

/**
 * Manages the saved data for the game.
 * 
 * Field:
 * The array of byte data for the field itself. This marks the individual tiles state:
 * - Plowed
 * - Wet/dry
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
    
    // TODO - Make this take parameters such as a file name to save to and data to write to said file.
    void writeData(String name) {
        byte[] field = new byte[120 * 2];
        for (int i = 0; i < (120 * 2); i++) {
            field[i] = (byte) 4;
        }
        File file = new File();
        if(file.openRW("/data/mdaled/" + name)){
            System.out.println("Trying to write");
            if(file.isOpen()){
                file.write(field);
            } else {
                // Stub 
            }
        } else {
            System.out.println("Failure to write");
        }
        file.close();
    }
    
    // TODO - Make this take a file name to read from and return a useful data type for use in the game.
    byte[] readData(String name) {
        File file = new File();
        byte[] result;
        if(file.openRO("/data/mdaled/" + name)){
            System.out.println("Trying to read");
            result = file.toArray();
            if(file.isOpen()){
                result = file.toArray();
            } else {
                // Stub debug options. 
                result = name == "Field" ? getDebugField() : getDebugItems();
            }
        } else {
            System.out.println("Failure to read ");
        }
        System.out.println(file.size());
        file.close();
        return result;
    }
    
    byte[] getDebugField() {
        byte[] field = new byte[144];
        return field;
    }
    
    byte[] getDebugItems() {
        return new byte[0];
    }
}