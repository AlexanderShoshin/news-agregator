package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import agregator.io.NewsStorage;
import agregator.io.StateStorage;
import agregator.structure.NewsItem;
import agregator.utils.NewsParser;

public class NewsAdmin {
    public String getNewsTable(NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> news = newsStorage.parse();
        String htmlTable = NewsParser.getHtmlTable(news);
        
        return htmlTable;
    }
    
    public String getGreeting(StateStorage stateStorage) {
        Boolean pageVisited = stateStorage.getIsAdminVisited();
        if (pageVisited) {
            return "";
        } else {
            stateStorage.setIsAdminVisited(true);
            return "May the fource be with you!";
        }
    }
    
    public void addIncomingNews(HttpServletRequest request, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException, TransformerException {
        NewsItem newsItem = NewsParser.getNewsItem(request.getParameterMap());
        if (newsItem != null) newsStorage.add(newsItem);
    }
    
    public void changeCategoryFilter(HttpServletRequest request, StateStorage stateStorage) {
        String value = request.getParameter("categoryFilterValue");
        if (value != null) {
            stateStorage.setCategoryFilter(value);
            
            value = request.getParameter("categoryFilterEnabled");
            stateStorage.setCategoryFilterEnabled(value != null);
        }
    }
    
    public String getCatFilterStatus(StateStorage stateStorage) {
        String status = "";
        if (stateStorage.getCategoryFilterEnabled()) {
            status = "checked";
        }
        
        return status;
    }
    
    public String getCatFilterValue(StateStorage stateStorage) {
        return stateStorage.getCategoryFilter();
    }
}