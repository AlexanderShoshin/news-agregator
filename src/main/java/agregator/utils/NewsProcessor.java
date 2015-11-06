package agregator.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import agregator.structure.NewsItem;
import agregator.structure.NewsPack;

public class NewsProcessor {
    public static NewsPack getNewsPack(String pack) {
        JSONObject jsonPack = new JSONObject(pack);
        JSONArray jsonOrder = (JSONArray)jsonPack.get("order");
        JSONArray jsonDelays = (JSONArray)jsonPack.get("delays");
        JSONArray jsonNews = (JSONArray)jsonPack.get("news");
        int[] order = new int[jsonOrder.length()];
        int[] delays = new int[jsonDelays.length()];
        List<NewsItem> news = new ArrayList<NewsItem>();
        NewsItem newsItem;
        JSONObject jsonNewsItem;
        JSONArray jsonImages;
        
        for (int i = 0; i < order.length; i++) {
            order[i] = jsonOrder.getInt(i);
        }
        
        for (int i = 0; i < delays.length; i++) {
            delays[i] = jsonDelays.getInt(i);
        }
        
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
        
        return new NewsPack(news, order, delays);
    }
    
    public static NewsPack filterByCategory(NewsPack newsPack, String category) {
        int[] order = newsPack.getOrder();
        int[] delays = newsPack.getDelays();
        List<NewsItem> news = newsPack.getNews();
        List<Integer> wasteItemIds;
        List<Integer> filteredIndexes;
        
        wasteItemIds = searchWasteNews(news, category);
        news = filterWasteNews(news, wasteItemIds);
        
        filteredIndexes = getCorrectOrderIndexes(order, wasteItemIds);
        order = filterArray(order, filteredIndexes);
        delays = filterArray(delays, filteredIndexes);
        
        return new NewsPack(news, order, delays);
    }
    
    private static List<Integer> searchWasteNews(List<NewsItem> news, String category) {
        List<Integer> wasteItemIds = new ArrayList<Integer>();
        NewsItem newsItem;
        
        for (int i = 0; i < news.size(); i++) {
            newsItem = news.get(i);
            if (isWaste(newsItem, category)) {
                wasteItemIds.add(newsItem.getId());
            }
        }
        
        return wasteItemIds;
    }
    
    private static boolean isWaste(NewsItem newsItem, String category) {
        return !newsItem.getCategory().equals(category);
    }
    
    private static List<NewsItem> filterWasteNews(List<NewsItem> news, List<Integer> wasteItemIds) {
        List<NewsItem> filteredNews = new ArrayList<NewsItem>();
        NewsItem newsItem;
        
        for (int i = 0; i < news.size(); i++) {
            newsItem = news.get(i);
            if (!hasVal(wasteItemIds, newsItem.getId())) {
                filteredNews.add(newsItem);
            }
        }
        return filteredNews;
    }
    
    private static <E> boolean hasVal(List<E> list, E val) {
        for (E item: list) {
            if (item.equals(val)) return true;
        }
        return false;
    }
    
    private static List<Integer> getCorrectOrderIndexes(int[] order, List<Integer> wasteItemIds) {
        List<Integer> wasteIndexes = new ArrayList<Integer>();
        
        for (int i = 0; i < order.length; i++) {
            if (!hasVal(wasteItemIds, order[i])) {
                wasteIndexes.add(i);
            }
        }
        
        return wasteIndexes;
    }
    
    private static int[] filterArray(int[] array, List<Integer> filteredIndexes) {
        List<Integer> filteredList = new ArrayList<Integer>();
        int[] filteredArray;
        
        for (int i = 0; i < filteredIndexes.size(); i++) {
            filteredList.add(array[filteredIndexes.get(i)]);
        }
        
        filteredArray = new int[filteredList.size()];
        
        for (int i = 0; i < filteredArray.length; i++) {
            filteredArray[i] = filteredList.get(i);
        }
        
        return filteredArray;
    }
}