package agregator.core;

import java.util.List;

import agregator.io.Config;
import agregator.io.NewsStorage;
import agregator.io.StateStorage;
import agregator.structure.NewsItem;
import agregator.utils.NewsParser;

public class NewsWire {
    public String getNextPack(StateStorage stateStorage, NewsStorage newsStorage) throws Exception {
        Slider newsSelector;
        List<NewsItem> newsPack;
        String jsonNewsPack;
        
        newsSelector = selectSlider();
        newsPack =  newsSelector.getNextSlides(stateStorage, newsStorage);
        jsonNewsPack = NewsParser.getJsonPack(newsPack);
        
        return jsonNewsPack;
    }
    
    private Slider selectSlider() {
        switch (Config.getSlideStrategy()) {
        case LINEAR:
            return new LinearSlider();
        default:
            return new LinearSlider();
        }
    }
}