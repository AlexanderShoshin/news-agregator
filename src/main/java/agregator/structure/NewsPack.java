package agregator.structure;

import java.util.List;

public class NewsPack {
    private int[] order;
    private int[] delays;
    private List<NewsItem> news;
    
    public NewsPack(List<NewsItem> news, int[] order, int[] delays) {
        this.order = order;
        this.delays = delays;
        this.news = news;
    }
    
    public int[] getOrder() {
        return order;
    }
    
    public int[] getDelays() {
        return delays;
    }
    
    public List<NewsItem> getNews() {
        return news;
    }
}