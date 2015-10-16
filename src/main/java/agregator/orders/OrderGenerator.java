package agregator.orders;

import java.util.List;

import agregator.structure.NewsItem;

public interface OrderGenerator {
    List<Integer> generate(List<NewsItem> news);
}