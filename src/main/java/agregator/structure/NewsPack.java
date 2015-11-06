package agregator.structure;

import java.util.List;

public class NewsPack {
    private int[] displayOrder;
    private int[] delays;
    private List<NewsItem> news;
    
    public NewsPack(List<NewsItem> news, int[] order, int[] delays) {
        this.displayOrder = order;
        this.delays = delays;
        this.news = news;
    }
    
    public int[] getOrder() {
        return displayOrder;
    }
    
    public int[] getDelays() {
        return delays;
    }
    
    public List<NewsItem> getNews() {
        return news;
    }
}