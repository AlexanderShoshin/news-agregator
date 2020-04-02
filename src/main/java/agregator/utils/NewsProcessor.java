package agregator.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import agregator.io.Config;
import agregator.structure.NewsItem;
import agregator.structure.NewsPack;

public class NewsProcessor {
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
    
    public static int[] getOrder(List<NewsItem> news) {
        int[] order = new int[news.size()];
        
        for (int i = 0; i < order.length; i++) {
            order[i] = news.get(i).getId();
        }
        
        return order;
    }
    
    public static int[] getDelays(List<NewsItem> news) {
        int[] delays = new int[news.size()];
        int defaultDelay = Config.getDefaultSlideDelay();
        
        Arrays.fill(delays,  defaultDelay);
        
        return delays;
    }
}