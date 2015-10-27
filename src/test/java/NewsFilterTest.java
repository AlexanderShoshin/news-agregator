import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import agregator.structure.NewsItem;
import agregator.utils.NewsParser;
import agregator.utils.NewsProcessor;

public class NewsFilterTest {   
    @Test
    public void filterByCategory() {
        List<NewsItem> newsPack = new ArrayList<NewsItem>();
        String packBefore;
        String packAfter;
        
        newsPack.add(newItem(1, "career2"));
        packAfter = NewsParser.getJsonPack(newsPack);
        newsPack.add(newItem(2, "sport"));
        packBefore = NewsParser.getJsonPack(newsPack);
        
        Assert.assertTrue(packAfter.equals(NewsProcessor.filterByField(packBefore, "category", "career")));
    }

    private NewsItem newItem(int id, String category) {
        NewsItem newsItem = new NewsItem();
        newsItem.setCategory(category);
        newsItem.setId(id);
        return newsItem;
    }
}