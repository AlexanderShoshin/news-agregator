package agregator.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

import agregator.io.EmptyParamException;
import agregator.structure.NewsItem;
import agregator.structure.NewsPack;

public class NewsParser {
    public static NewsPack jsonToPack(String pack) {
        JSONObject jsonPack = new JSONObject(pack);
        JSONArray jsonOrder = (JSONArray)jsonPack.get("order");
        JSONArray jsonDelays = (JSONArray)jsonPack.get("delays");
        JSONArray jsonNews = (JSONArray)jsonPack.get("news");
        int[] order = toIntArray(jsonOrder);
        int[] delays = toIntArray(jsonDelays);
        List<NewsItem> news = jsonToNews(jsonNews);
        
        return new NewsPack(news, order, delays);
    }
    
    private static int[] toIntArray(JSONArray jsonArray) {
        int[] array = new int[jsonArray.length()];
        
        for (int i = 0; i < array.length; i++) {
            array[i] = jsonArray.getInt(i);
        }
        
        return array;
    }
    
    private static List<NewsItem> jsonToNews(JSONArray jsonNews) {
        List<NewsItem> news = new ArrayList<NewsItem>();
        NewsItem newsItem;
        JSONObject jsonNewsItem;
        JSONArray jsonImages;
        
        for (int i = 0; i < jsonNews.length(); i++) {
            newsItem = new NewsItem();
            jsonNewsItem = (JSONObject) jsonNews.get(i);
            newsItem.setId((Integer) jsonNewsItem.get("id"));
            newsItem.setTitle((String) jsonNewsItem.get("title"));
            newsItem.setAuthor((String) jsonNewsItem.get("author"));
            newsItem.setCategory((String) jsonNewsItem.get("category"));
            newsItem.setDescription((String) jsonNewsItem.get("description"));
            newsItem.setSource((String) jsonNewsItem.get("source"));
            newsItem.setPublishedDate((String) jsonNewsItem.get("publishedDate"));
            jsonImages = (JSONArray) jsonNewsItem.get("images");
            for (int j = 0; j < jsonImages.length(); j++) {
                newsItem.addImage(jsonImages.getString(j));
            }
            news.add(newsItem);
        }
        
        return news;
    }
    
    public static String packToJson(NewsPack newsPack) {
        JSONObject jsonPack = new JSONObject();
        
        jsonPack.put("order", new JSONArray(newsPack.getOrder()));
        jsonPack.put("delays", new JSONArray(newsPack.getDelays()));
        jsonPack.put("news", getJsonNews(newsPack.getNews()));
        
        return jsonPack.toString();
    }

    private static JSONArray getJsonNews(List<NewsItem> news) {
        JSONArray jsonNews = new JSONArray();
        Set<Integer> uniqueItemsIds = new TreeSet<Integer>();
        
        for (NewsItem item: news) {
            if (!uniqueItemsIds.contains(item.getId())) {
                uniqueItemsIds.add(item.getId());
                jsonNews.put(NewsParser.getJsonNewsItem(item));
            }
        }
        
        return jsonNews;
    }
    
    public static JSONObject getJsonNewsItem(NewsItem newsItem) {
        JSONObject jsonNewsItem = new JSONObject();
        JSONArray jsonImages = new JSONArray();

        jsonNewsItem.put("id", newsItem.getId());
        jsonNewsItem.put("title", newsItem.getTitle());
        jsonNewsItem.put("author", newsItem.getAuthor());
        jsonNewsItem.put("category", newsItem.getCategory());
        jsonNewsItem.put("description", newsItem.getDescription());
        jsonNewsItem.put("source", newsItem.getSource());
        jsonNewsItem.put("publishedDate", newsItem.getPublishedDate());

        for (int i = 0; i < newsItem.getImages().size(); i++) {
            jsonImages.put(newsItem.getImagesFolder() + "/" + newsItem.getImage(i));

        }
        jsonNewsItem.put("images", jsonImages);
        
        return jsonNewsItem;
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