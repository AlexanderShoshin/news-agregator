package agregator.core;

import java.util.List;

import agregator.io.NewsStorage;
import agregator.io.StateStorage;
import agregator.structure.NewsItem;

public interface Slider {
    public List<NewsItem> getNextSlides(StateStorage stateStorage, NewsStorage newsStorage) throws Exception;
}