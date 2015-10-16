package agregator.core;

import java.util.List;

import agregator.structure.NewsItem;

interface OrderGenerator {
    List<String> generate(List<NewsItem> news);
}