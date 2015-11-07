package agregator.utils;

import java.util.List;

import agregator.io.EmptyParamException;
import agregator.structure.NewsItem;

public class NewsParser {
    public static String getHtmlTable(List<NewsItem> news) {
        String newsLines = "";
        
        for (NewsItem item: news) {
            newsLines += getHtmlLine(item);
        }
        
        return newsLines;
    }

    private static String getHtmlLine(NewsItem item) {    
        String line = "";
        
        line += "<tr>";
        line += getHtmlCell(item.getTitle());
        line += getHtmlCell(item.getImagesFolder());
        line += "</tr>";
        
        return line;
    }

    private static String getHtmlCell(String value) {
        return "<td>" + value + "</td>";
    }
    
    public static NewsItem getNewsItem(String title, String imagesFolder) {
        NewsItem newItem = new NewsItem();
        try {
            newItem.setTitle(getIfNotEmpty(title));
            newItem.setImagesFolder(getIfNotEmpty(imagesFolder));
            return newItem;
        } catch (EmptyParamException e) {
            return null;
        }
    }
    
    private static String getIfNotEmpty(String param) throws EmptyParamException {
        if (param != null && !param.isEmpty()) {
            return param;
        } else {
            throw new EmptyParamException();
        }
    }
}