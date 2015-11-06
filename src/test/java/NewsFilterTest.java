import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import agregator.structure.NewsItem;
import agregator.structure.NewsPack;
import agregator.utils.NewsParser;
import agregator.utils.NewsProcessor;

public class NewsFilterTest {   
    @Test
    public void filterByCategory() {
        List<NewsItem> news = new ArrayList<NewsItem>();
        NewsPack startPack;
        NewsPack filteredPack;
        NewsPack expectPack;
        String filteredJson;
        String expectJson;
        
        news.add(newItem(1, "career"));
        expectPack = new NewsPack(news, new int[]{1}, new int[]{2000});
        expectJson = NewsParser.packToJson(expectPack);
        news.add(newItem(2, "sport"));
        startPack = new NewsPack(news, new int[]{1, 2}, new int[]{2000, 2000});
        filteredPack = NewsProcessor.filterByCategory(startPack, "career");
        filteredJson = NewsParser.packToJson(filteredPack);
        
        Assert.assertTrue(expectJson.equals(filteredJson));
    }

    private NewsItem newItem(int id, String category) {
        NewsItem newsItem = new NewsItem();
        newsItem.setCategory(category);
        newsItem.setId(id);
        return newsItem;
    }
}