package agregator.core;

import java.util.ArrayList;
import java.util.List;

import agregator.structure.NewsItem;

public class LinearOrderGenerator implements OrderGenerator {
    public List<String> generate(List<NewsItem> news) {
        List<String> order = new ArrayList<String>();
        
        for (NewsItem item: news) {
            order.add(item.id);
        }
        
        return order;
    }
}