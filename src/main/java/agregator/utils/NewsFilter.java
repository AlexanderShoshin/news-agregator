package agregator.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class NewsFilter {
    public static String filterByField(String jsonNewsPack, String fieldName, String fieldValue) {
        JSONObject newsPack = new JSONObject(jsonNewsPack);
        JSONArray order = (JSONArray)newsPack.get("order");
        JSONArray delays = (JSONArray)newsPack.get("delays");
        JSONArray news = (JSONArray)newsPack.get("news");
        List<Integer> filteredIndexes;
        JSONObject filteredPack;
        
        filteredIndexes = filterNewsIndexes(news, fieldName, fieldValue);
        news = filterNews(news, filteredIndexes);
        
        filteredIndexes = filterOrdersIndexes(order, filteredIndexes);
        order = filterArray(order, filteredIndexes);
        delays = filterArray(delays, filteredIndexes);
        
        filteredPack = new JSONObject();
        filteredPack.put("order", order);
        filteredPack.put("delays", delays);
        filteredPack.put("news", news);
        
        return filteredPack.toString();
    }
    
    private static List<Integer> filterNewsIndexes(JSONArray news, String fieldName, String fieldValue) {
        List<Integer> indexes = new ArrayList<Integer>();
        
        for (int i = 0; i < news.length(); i++) {
            if (!isWaste((JSONObject)news.get(i), fieldName, fieldValue)) {
                indexes.add(i);
            }
        }
        
        return indexes;
    }
    
    private static boolean isWaste(JSONObject newsItem, String fieldName, String fieldValue) {
        return !newsItem.get(fieldName).toString().equals(fieldValue);
    }
    
    private static JSONArray filterNews(JSONArray news, List<Integer> filteredIndexes) {
        JSONArray filteredNews = new JSONArray();
        
        for (int i = 0; i < filteredIndexes.size(); i++) {
            filteredNews.put(news.get(filteredIndexes.get(i)));
        }
        
        return filteredNews;
    }
    
    private static List<Integer> filterOrdersIndexes(JSONArray order, List<Integer> filteredNewsIndexes) {
        List<Integer> indexes = new ArrayList<Integer>();
        
        for (int i = 0; i < filteredNewsIndexes.size(); i++) {
            for (int j = 0; j < order.length(); j++) {
                if (order.get(j).toString().equals(filteredNewsIndexes.get(i).toString())) {
                    indexes.add(j);
                }
            }
        }
        
        return indexes;
    }
    
    private static JSONArray filterArray(JSONArray array, List<Integer> filteredIndexes) {
        JSONArray filteredArray = new JSONArray();
        
        for (int i = 0; i < filteredIndexes.size(); i++) {
            filteredArray.put(array.get(filteredIndexes.get(i)));
        }
        
        return filteredArray;
    }
}