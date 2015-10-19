package agregator.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import agregator.io.Config;
import agregator.io.LocalNews;
import agregator.orders.LinearOrderGenerator;
import agregator.orders.OrderGenerator;
import agregator.structure.NewsItem;
import agregator.utils.NewsParser;

public class NewsWire {
    
    public void createNewSequense(ServletContext context) throws SAXException, IOException, ParserConfigurationException {
        List<NewsItem> news;
        OrderGenerator orderGenerator;
        
        news = getStoredNews(context);
        orderGenerator = selectOrderGenerator();
        
        context.setAttribute("newsSequense", orderGenerator.generate(news));
        context.setAttribute("itemsSentCount", 0);
    }
    
    private List<NewsItem> getStoredNews(ServletContext context)
            throws SAXException, IOException, ParserConfigurationException {
        return LocalNews.parse(Config.getLocalNewsLocation(context), "news.xml");
    }
    
    private OrderGenerator selectOrderGenerator() {
        switch (Config.getSlideStrategy()) {
        case LINEAR:
            return new LinearOrderGenerator();
        default:
            return new LinearOrderGenerator();
        }
    }
    
    @SuppressWarnings("unchecked")
    public String getNextPack(ServletContext context) throws SAXException, IOException, ParserConfigurationException {
        JSONObject pack;
        int itemsSentCount;
        List<Integer> newsSequense;
        
        itemsSentCount = (Integer)context.getAttribute("itemsSentCount");
        newsSequense = (List<Integer>)context.getAttribute("newsSequense");
        List<Integer> packSequense = getPackSequense(Config.getPackNewsCount(),
                                                     itemsSentCount,
                                                     newsSequense);
        itemsSentCount += packSequense.size();
        context.setAttribute("itemsSentCount", itemsSentCount);
        
        pack = new JSONObject();
        pack.put("order", getOrderPack(packSequense));
        pack.put("delays", getDelaysPack(packSequense));
        pack.put("news", getNewsPack(packSequense, context));
        
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
    
    private JSONArray getNewsPack(List<Integer> packSequense, ServletContext context)
            throws SAXException, IOException, ParserConfigurationException {
        List<NewsItem> news = getStoredNews(context);
        JSONArray newsPack = new JSONArray();
        Set<Integer> uniqueItems = new TreeSet<Integer>();
        
        for (Integer i: packSequense) {
            uniqueItems.add(i);
        }
        
        for (int i: uniqueItems) {
            newsPack.put(NewsParser.parseToJson(news.get(i), Config.getLocalNewsLocation(context)));
        }
        
        return newsPack;
    }
}