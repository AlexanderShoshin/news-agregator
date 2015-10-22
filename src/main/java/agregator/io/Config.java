package agregator.io;

import agregator.structure.SlideStrategy;

public class Config {
    public static SlideStrategy getSlideStrategy() {
        // todo: read value from config file
        return SlideStrategy.LINEAR;
    }
    
    public static int getPackNewsCount() {
        // todo: read value from config file
        return 2;
    }
    
    public static int getDefaultSlideDelay() {
        // todo: read value from config file
        return 2000;
    }
    
    public static String getLocalNewsDescriptor() {
        // todo: read value from config file
        return "news.xml";
    }
}