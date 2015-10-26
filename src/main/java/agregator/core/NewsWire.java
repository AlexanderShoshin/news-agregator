package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.io.Config;
import agregator.io.NewsStorage;
import agregator.io.SettingsStorage;
import agregator.structure.NewsItem;
import agregator.utils.NewsParser;

public class NewsWire {
    public String getNextPack(SettingsStorage settingsStorage, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException {
        Slider newsSelector = selectSlider();
        List<NewsItem> newsPack = newsSelector.getNextSlides(settingsStorage, newsStorage);
        String jsonNewsPack = NewsParser.getJsonPack(newsPack);
        
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