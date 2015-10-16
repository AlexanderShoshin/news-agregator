package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.parsers.ConfigParser;
import agregator.parsers.LocalNews;
import agregator.structure.NewsItem;

public class NewsWire {
    private List<String> newsSequense;
    private int lastItem;
    
    public NewsWire() throws ParserConfigurationException {
       
    }
    
    public void createNewSequense(ServletContext context) throws SAXException, IOException, ParserConfigurationException {
        List<NewsItem> news;
        OrderGenerator orderGenerator;
        
        news = getStoredNews(context);
        orderGenerator = selectOrderGenerator();
        
        newsSequense = orderGenerator.generate(news);
        lastItem = 0;
    }
    
    private List<NewsItem> getStoredNews(ServletContext context)
            throws SAXException, IOException, ParserConfigurationException {
        return LocalNews.parse(context.getRealPath("/") + "data\\news.xml");
    }
    
    private OrderGenerator selectOrderGenerator() {
        switch (ConfigParser.getStrategy()) {
        case LINEAR:
            return new LinearOrderGenerator();
        default:
            return new LinearOrderGenerator();
        }
    }
    
    public String getNextPack(ServletContext context) throws SAXException, IOException, ParserConfigurationException {
        String newsPack;
        
        /*for (int i = lastItem; i < newsSequense.size(); i++) {
            
        }*/
        List<NewsItem> news;
        news = getStoredNews(context);
        
        newsPack = LocalNews.parseToJson(news.get(0));
        
        return newsPack;
    }
}