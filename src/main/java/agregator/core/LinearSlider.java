package agregator.core;

import java.util.ArrayList;
import java.util.List;

import agregator.io.Config;
import agregator.io.NewsStorage;
import agregator.io.StateStorage;
import agregator.structure.NewsItem;

public class LinearSlider implements Slider {

    public List<NewsItem> getNextSlides(StateStorage stateStorage, NewsStorage newsStorage) throws Exception {
        List<NewsItem> news;
        List<NewsItem> selectedNews;
        int packCount;
        int selectedCount;
        int curId;
        int lastSentId;
        
        curId = 0;
        lastSentId = stateStorage.getLastItemSent();
        
        packCount = Config.getPackNewsCount();
        selectedCount = 0;
        
        news = newsStorage.parse();
        selectedNews = new ArrayList<NewsItem>();
        
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