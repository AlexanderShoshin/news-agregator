package agregator.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.io.Config;
import agregator.io.NewsStorage;
import agregator.io.SettingsStorage;
import agregator.structure.NewsItem;

public class LinearSlider implements Slider {

    public List<NewsItem> getNextSlides(SettingsStorage settingsStorage, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> news = newsStorage.parse();
        if (news.size() > 0) {
            return getSlides(news, settingsStorage);
        } else {
            return new ArrayList<NewsItem>();
        }
    }
    
    private List<NewsItem> getSlides(List<NewsItem> news, SettingsStorage settingsStorage) {
        List<NewsItem> selectedNews = new ArrayList<NewsItem>();
        int packCount = Config.getPackNewsCount();
        int selectedCount = 0;
        int curId = 0;
        int lastSentId = settingsStorage.getLastItemSent();
        
        while (selectedCount < packCount) {
            selectedCount++;
            curId = (lastSentId + selectedCount)%news.size();
            selectedNews.add(news.get(curId));
        }
        
        lastSentId = curId;
        settingsStorage.setLastItemSent(lastSentId);
        
        return selectedNews;
    }
}