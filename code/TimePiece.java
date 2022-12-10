package code;



public class TimePiece {
    static ubyte hour = 0, dayProgress;
    
    static void init() {
        hour = 0;
        dayProgress = 0;
    }
    
    
    /**
     * Day progression. 
     * Hours increment dayProgress on 30 tics. 
     * Day progresses when 200 hours pass. 
     * hehe 200 hours. Busy days
    */
    static boolean updateDayProgress() {
        hour++;
        if (hour > 30) {
            dayProgress++;
            hour = 0;
        }
        if(dayProgress >= 200) {
            dayProgress = 0;
            return true;
        }
        return false;
    }
    
    static ubyte getTime() {
        return dayProgress;
    }
}