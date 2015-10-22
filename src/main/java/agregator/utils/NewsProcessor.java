package agregator.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class NewsProcessor {
    public static String filterByField(String jsonNewsPack, String fieldName, String fieldValue) {
        JSONObject newsPack = new JSONObject(jsonNewsPack);
        JSONArray order = (JSONArray)newsPack.get("order");
        JSONArray delays = (JSONArray)newsPack.get("delays");
        JSONArray news = (JSONArray)newsPack.get("news");
        List<Integer> wasteItemIds;
        List<Integer> filteredIndexes;
        JSONObject filteredPack;
        
        wasteItemIds = searchWasteNews(news, fieldName, fieldValue);
        news = filterWasteNews(news, wasteItemIds);
        
        filteredIndexes = getCorrectOrderIndexes(order, wasteItemIds);
        order = filterArray(order, filteredIndexes);
        delays = filterArray(delays, filteredIndexes);
        
        filteredPack = new JSONObject();
        filteredPack.put("order", order);
        filteredPack.put("delays", delays);
        filteredPack.put("news", news);
        
        return filteredPack.toString();
    }
    
    private static List<Integer> searchWasteNews(JSONArray news, String fieldName, String fieldValue) {
        List<Integer> wasteItemIds = new ArrayList<Integer>();
        JSONObject newsItem;
        
        for (int i = 0; i < news.length(); i++) {
            newsItem = (JSONObject)news.get(i);
            if (isWaste(newsItem, fieldName, fieldValue)) {
                wasteItemIds.add((Integer)newsItem.get("id"));
            }
        }
        
        return wasteItemIds;
    }
    
    private static boolean isWaste(JSONObject newsItem, String fieldName, String fieldValue) {
        return !newsItem.get(fieldName).toString().equals(fieldValue);
    }
    
    private static JSONArray filterWasteNews(JSONArray news, List<Integer> wasteItemIds) {
        JSONArray filteredNews = new JSONArray();
        JSONObject newsItem;
        
        for (int i = 0; i < news.length(); i++) {
            newsItem = (JSONObject)news.get(i);
            if (!hasVal(wasteItemIds, (Integer)newsItem.get("id"))) {
                filteredNews.put(newsItem);
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
    
    private static List<Integer> getCorrectOrderIndexes(JSONArray order, List<Integer> wasteItemIds) {
        List<Integer> wasteIndexes = new ArrayList<Integer>();
        
        for (int i = 0; i < order.length(); i++) {
            if (!hasVal(wasteItemIds, (Integer)order.get(i))) {
                wasteIndexes.add(i);
            }
        }
        
        return wasteIndexes;
    }
    
    private static JSONArray filterArray(JSONArray array, List<Integer> filteredIndexes) {
        JSONArray filteredArray = new JSONArray();
        
        for (int i = 0; i < filteredIndexes.size(); i++) {
            filteredArray.put(array.get(filteredIndexes.get(i)));
        }
        
        return filteredArray;
    }
}