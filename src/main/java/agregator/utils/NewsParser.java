package agregator.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import agregator.structure.NewsItem;

public class NewsParser {
    public static JSONObject parseToJson(NewsItem newsItem, String newsLocation) {
        JSONObject item = new JSONObject();
        JSONArray images = new JSONArray();

        item.put("title", newsItem.getTitle());
        item.put("author", newsItem.getAuthor());
        item.put("category", newsItem.getCategory());
        item.put("description", newsItem.getDescription());
        item.put("source", newsItem.getSource());
        item.put("publishedDate", newsItem.getPublishedDate());
        for (int i = 0; i < newsItem.getImagesCount(); i++) {
            images.put(newsLocation + "/" + newsItem.getImagesFolder() + "/" + newsItem.getImage(i));
        }
        item.put("images", images);
        
        return item;
    }
}