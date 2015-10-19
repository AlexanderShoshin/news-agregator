package agregator.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import agregator.structure.NewsItem;

public class NewsParser {
    public static JSONObject parseToJson(NewsItem newsItem, String newsLocation) {
        JSONObject item = new JSONObject();
        JSONArray images = new JSONArray();
                
        item.put("title", newsItem.title);
        item.put("author", newsItem.author);
        item.put("category", newsItem.category);
        item.put("description", newsItem.description);
        item.put("source", newsItem.source);
        item.put("publishedDate", newsItem.publishedDate);
        for (int i = 0; i < newsItem.images.size(); i++) {
            images.put(newsLocation + "/" + newsItem.imagesFolder + "/" + newsItem.images.get(i));
        }
        item.put("images", images);
        
        return item;
    }
}