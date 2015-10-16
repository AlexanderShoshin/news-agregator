package agregator.io;

import agregator.structure.SlideStrategys;

public class Config {
    public static SlideStrategys getSlideStrategy() {
        // todo: read value from config file
        return SlideStrategys.LINEAR;
    }
    
    public static int getPackNewsCount() {
        // todo: read value from config file
        return 3;
    }
    
    public static int getDefaultSlideDelay() {
        // todo: read value from config file
        return 2000;
    }
}