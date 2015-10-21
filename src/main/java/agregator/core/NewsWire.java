package agregator.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import agregator.io.Config;
import agregator.io.NewsStorage;
import agregator.io.StateStorage;
import agregator.orders.LinearOrderGenerator;
import agregator.orders.OrderGenerator;
import agregator.structure.NewsItem;
import agregator.utils.NewsParser;

public class NewsWire {
    
    public void createNewSequense(StateStorage stateStorage, NewsStorage localNews) throws SAXException, IOException, ParserConfigurationException {
        List<NewsItem> news;
        OrderGenerator orderGenerator;
        
        news = localNews.parse();
        orderGenerator = selectOrderGenerator();
        
        stateStorage.setNewsSequense(orderGenerator.generate(news));
        stateStorage.setItemsSent(0);
    }
    
    private OrderGenerator selectOrderGenerator() {
        switch (Config.getSlideOrder()) {
        case LINEAR:
            return new LinearOrderGenerator();
        default:
            return new LinearOrderGenerator();
        }
    }
    
    public String getNextPack(StateStorage stateStorage, NewsStorage newsStorage) throws SAXException, IOException, ParserConfigurationException {
        JSONObject pack;
        int itemsSentCount;
        List<Integer> newsSequense;
        
        itemsSentCount = stateStorage.getItemsSent();
        newsSequense = stateStorage.getNewsSequense();
        
        List<Integer> packSequense = getPackSequense(Config.getPackNewsCount(),
                                                     itemsSentCount,
                                                     newsSequense);
        itemsSentCount += packSequense.size();
        stateStorage.setItemsSent(itemsSentCount);
        
        pack = new JSONObject();
        pack.put("order", getOrderPack(packSequense));
        pack.put("delays", getDelaysPack(packSequense));
        pack.put("news", getNewsPack(packSequense, newsStorage));
        
        return pack.toString();
    }
    
    private List<Integer> getPackSequense(int packLength, int itemsSentCount, List<Integer> newsSequense) {
        int sequenseIndex;
        int newsItemId;
        List<Integer> packSequense;
        
        packSequense = new ArrayList<Integer>();
        for (int i = 0; i < packLength; i++) {
            sequenseIndex = (itemsSentCount + i) % newsSequense.size();
            newsItemId = newsSequense.get(sequenseIndex);
            packSequense.add(newsItemId);
        }
        
        return packSequense;
    }
    
    private JSONArray getOrderPack(List<Integer> packSequense) {
        JSONArray orderPack = new JSONArray(packSequense.toString());
        return orderPack;
    }
    
    private JSONArray getDelaysPack(List<Integer> packSequense) {
        JSONArray delaysPack = new JSONArray();
        int delay = Config.getDefaultSlideDelay();
        
        for (int i = 0; i < packSequense.size(); i++) {
            delaysPack.put(delay);
        }
        return delaysPack;
    }
    
    private JSONArray getNewsPack(List<Integer> packSequense, NewsStorage newsStorage)
            throws SAXException, IOException, ParserConfigurationException {
        List<NewsItem> news = newsStorage.parse();
        JSONArray newsPack = new JSONArray();
        Set<Integer> uniqueItems = new TreeSet<Integer>();
        
        for (Integer i: packSequense) {
            uniqueItems.add(i);
        }
        
        for (int i: uniqueItems) {
            newsPack.put(NewsParser.parseToJson(news.get(i)));
        }
        
        return newsPack;
    }
}