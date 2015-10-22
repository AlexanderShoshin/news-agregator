package agregator.core;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import agregator.io.NewsStorage;
import agregator.io.StateStorage;
import agregator.structure.NewsItem;
import agregator.utils.NewsParser;

public class NewsAdmin {
    public String getNewsTable(NewsStorage newsStorage) throws Exception {
        String htmlTable;
        List<NewsItem> news;
        
        news = newsStorage.parse();
        htmlTable = NewsParser.getHtmlTable(news);
        
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
    
    public void addIncomingNews(HttpServletRequest request, NewsStorage newsStorage) throws Exception {
        NewsItem newsItem = NewsParser.getNewsItem(request.getParameterMap());
        if (newsItem != null) newsStorage.add(newsItem);
    }
    
    public void changeCategoryFilter(HttpServletRequest request, StateStorage stateStorage) {
        String value;
        
        value = request.getParameter("categoryFilterValue");
        if (value != null) {
            stateStorage.setCategoryFilter(value);
            
            value = request.getParameter("categoryFilterEnabled");
            if (value != null) {
                stateStorage.setCategoryFilterEnabled(true);
            } else {
                stateStorage.setCategoryFilterEnabled(false);
            }
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