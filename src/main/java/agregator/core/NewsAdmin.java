package agregator.core;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import agregator.io.EmptyParamException;
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
        try {
            NewsItem newsItem = NewsParser.getNewsItem(request.getParameterMap());
            newsStorage.add(newsItem);
        } catch (EmptyParamException ignored) {}
    }
}