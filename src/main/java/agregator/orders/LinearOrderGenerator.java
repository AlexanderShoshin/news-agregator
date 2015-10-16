package agregator.orders;

import java.util.ArrayList;
import java.util.List;

import agregator.structure.NewsItem;

public class LinearOrderGenerator implements OrderGenerator {
    public List<Integer> generate(List<NewsItem> news) {
        List<Integer> order = new ArrayList<Integer>();
        
        for (NewsItem item: news) {
            order.add(Integer.parseInt(item.id));
            order.add(Integer.parseInt(item.id));
        }
        
        return order;
    }
}