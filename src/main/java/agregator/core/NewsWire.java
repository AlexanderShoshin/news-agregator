package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.io.NewsStorage;
import agregator.structure.NewsItem;
import agregator.structure.NewsState;
import agregator.utils.NewsParser;

public class NewsWire {
    private Slider newsSelector;
    
    public NewsWire(Slider newsSelector) {
        this.newsSelector = newsSelector;
    }
    
    public String getNextPack(NewsState curState, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> newsPack = newsSelector.getNextSlides(curState, newsStorage);
        String jsonNewsPack = NewsParser.getJsonPack(newsPack);
        
        return jsonNewsPack;
    }
}