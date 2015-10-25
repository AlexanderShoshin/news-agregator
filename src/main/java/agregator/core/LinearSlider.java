package agregator.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.io.Config;
import agregator.io.NewsStorage;
import agregator.io.StateStorage;
import agregator.structure.NewsItem;

public class LinearSlider implements Slider {

    public List<NewsItem> getNextSlides(StateStorage stateStorage, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> news = newsStorage.parse();
        List<NewsItem> selectedNews = new ArrayList<NewsItem>();
        int packCount = Config.getPackNewsCount();
        int selectedCount = 0;
        int curId = 0;
        int lastSentId = stateStorage.getLastItemSent();
        
        while (selectedCount < packCount) {
            selectedCount++;
            curId = (lastSentId + selectedCount)%news.size();
            selectedNews.add(news.get(curId));
        }
        
        lastSentId = curId;
        stateStorage.setLastItemSent(lastSentId);
        
        return selectedNews;
    }
}