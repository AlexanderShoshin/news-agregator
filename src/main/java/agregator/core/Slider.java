package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.io.NewsStorage;
import agregator.structure.NewsItem;
import agregator.structure.NewsState;

public interface Slider {
    public List<NewsItem> getNextSlides(NewsState curState, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException;
}