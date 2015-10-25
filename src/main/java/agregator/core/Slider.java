package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.io.NewsStorage;
import agregator.io.StateStorage;
import agregator.structure.NewsItem;

public interface Slider {
    public List<NewsItem> getNextSlides(StateStorage stateStorage, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException;
}