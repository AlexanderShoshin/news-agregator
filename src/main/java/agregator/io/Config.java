package agregator.io;

import javax.servlet.ServletContext;

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
    
    public static String getLocalNewsLocation(ServletContext context) {
        // todo: read value from config file
        return context.getRealPath("/") + "data";
    }
}