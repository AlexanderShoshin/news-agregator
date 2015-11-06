package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.io.NewsStorage;
import agregator.structure.NewsItem;
import agregator.structure.NewsPack;
import agregator.structure.NewsState;
import agregator.utils.NewsProcessor;

public class NewsWire {
    private Slider newsSelector;
    
    public NewsWire(Slider newsSelector) {
        this.newsSelector = newsSelector;
    }
    
    public NewsPack getNextPack(NewsState curState, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> news = newsSelector.getNextSlides(curState, newsStorage);
        int[] order = NewsProcessor.getOrder(news);
        int[] delays = NewsProcessor.getDelays(news);
        NewsPack newsPack = new NewsPack(news, order, delays);
        
        return newsPack;
    }
}