package code;

import femto.File;

public class DataManager {
    DataManager() {
        System.out.println("Data Manager init");
    }
    
    // TODO - Make this take parameters such as a file name to save to and data to write to said file.
    void writeData() {
        byte[] field = new byte[120 * 2];
        for (int i = 0; i < (120 * 2); i++) {
            field[i] = (byte) 4;
        }
        File file = new File();
        if(file.openRW("/data/mdaled/field")){
            System.out.println("Trying to write");
            file.write(field);
        } else {
            System.out.println("Failure to write");
        }
        file.close();
    }
    
    // TODO - Make this take a file name to read from and return a useful data type for use in the game.
    void readData() {
        File file = new File();
        byte[] result;
        if(file.openRO("/data/mdaled/field")){
            System.out.println("Trying to read");
            result = file.toArray();
        } else {
            System.out.println("Failure to read ");
        }
        System.out.println(file.size());
        file.close();
        
        
        for(byte i: result) {
            System.out.println(i);
        }
        
    }
}