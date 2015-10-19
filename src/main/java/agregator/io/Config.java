package agregator.io;

import agregator.structure.SlideOrder;

public class Config {
    public static SlideOrder getSlideOrder() {
        // todo: read value from config file
        return SlideOrder.LINEAR;
    }
    
    public static int getPackNewsCount() {
        // todo: read value from config file
        return 1;
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