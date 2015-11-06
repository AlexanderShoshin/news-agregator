package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import agregator.io.NewsStorage;
import agregator.io.SettingsStorage;
import agregator.structure.NewsItem;
import agregator.utils.NewsParser;

public class NewsAdmin {
    public String getNewsTable(NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> news = newsStorage.parse();
        String htmlTable = NewsParser.getHtmlTable(news);
        
        return htmlTable;
    }
    
    public String getGreeting(SettingsStorage settingsStorage) {
        Boolean pageVisited = settingsStorage.getIsAdminVisited();
        if (pageVisited) {
            return "";
        } else {
            settingsStorage.setIsAdminVisited(true);
            return "May the fource be with you!";
        }
    }
    
    public void addIncomingNews(String title, String imagesFolder, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException, TransformerException {
        NewsItem newsItem = NewsParser.getNewsItem(title, imagesFolder);
        if (newsItem != null) newsStorage.add(newsItem);
    }
    
    public void changeCategoryFilter(String categoryFilterValue, String categoryFilterEnabled, SettingsStorage settingsStorage) {
        if (categoryFilterValue != null) {
            settingsStorage.setCategoryFilter(categoryFilterValue);
            settingsStorage.setCategoryFilterEnabled(categoryFilterEnabled != null);
        }
    }
    
    public String getCatFilterStatus(SettingsStorage settingsStorage) {
        String status = "";
        if (settingsStorage.getCategoryFilterEnabled()) {
            status = "checked";
        }
        
        return status;
    }
    
    public String getCatFilterValue(SettingsStorage settingsStorage) {
        return settingsStorage.getCategoryFilter();
    }
}