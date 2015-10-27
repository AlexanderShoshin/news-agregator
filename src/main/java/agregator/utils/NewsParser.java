package agregator.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

import agregator.io.Config;
import agregator.io.EmptyParamException;
import agregator.structure.NewsItem;

public class NewsParser {
    public static String getJsonPack(List<NewsItem> news) {
        JSONObject pack = new JSONObject();
        
        pack.put("order", getOrderPack(news));
        pack.put("delays", getDelaysPack(news));
        pack.put("news", getNewsPack(news));
        
        return pack.toString();
    }
    
    private static JSONArray getOrderPack(List<NewsItem> news) {
        JSONArray orderPack = new JSONArray();
        
        for (NewsItem item: news) {
            orderPack.put(item.getId());
        }
        
        return orderPack;
    }
    
    private static JSONArray getDelaysPack(List<NewsItem> news) {
        JSONArray delaysPack = new JSONArray();
        int delay = Config.getDefaultSlideDelay();
        
        for (int i = 0; i < news.size(); i++) {
            delaysPack.put(delay);
        }
        return delaysPack;
    }
    
    private static JSONArray getNewsPack(List<NewsItem> news) {
        JSONArray newsPack = new JSONArray();
        Set<Integer> uniqueItemsIds = new TreeSet<Integer>();
        
        for (NewsItem item: news) {
            if (!uniqueItemsIds.contains(item.getId())) {
                uniqueItemsIds.add(item.getId());
                newsPack.put(NewsParser.getJsonNewsItem(item));
            }
        }
        
        return newsPack;
    }
    
    public static JSONObject getJsonNewsItem(NewsItem newsItem) {
        JSONObject item = new JSONObject();
        JSONArray images = new JSONArray();

        item.put("id", newsItem.getId());
        item.put("title", newsItem.getTitle());
        item.put("author", newsItem.getAuthor());
        item.put("category", newsItem.getCategory());
        item.put("description", newsItem.getDescription());
        item.put("source", newsItem.getSource());
        item.put("publishedDate", newsItem.getPublishedDate());

        for (int i = 0; i < newsItem.getImagesCount(); i++) {
            images.put(newsItem.getImagesFolder() + "/" + newsItem.getImage(i));

        }
        item.put("images", images);
        
        return item;
    }
    
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
    
    public static NewsItem getNewsItem(Map<String, String[]> params) {
        NewsItem newItem = new NewsItem();
        try {
            newItem.setTitle(getParam(params, "title"));
            newItem.setImagesFolder(getParam(params, "imagesFolder"));
            return newItem;
        } catch (EmptyParamException e) {
            return null;
        }
    }
    
    private static String getParam(Map<String, String[]> params, String paramName) throws EmptyParamException {
        if (params.containsKey(paramName) && params.get(paramName)[0] != "") {
            return params.get(paramName)[0];
        } else {
            throw new EmptyParamException();
        }
    }
}