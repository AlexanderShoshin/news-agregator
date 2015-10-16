package agregator.parsers;

import agregator.structure.NewsSources;
import agregator.structure.SlideStrategy;

public class ConfigParser {
    public static SlideStrategy getStrategy() {
        return SlideStrategy.LINEAR;
    }
    
    public static int getPackNewsCount() {
        return 1;
    }
    
    public static int getDefaultSlideDelay() {
        return 2000;
    }
    
    public static NewsSources[] getNewsSources() {
        return new NewsSources[]{NewsSources.LOCAL};
    }
}