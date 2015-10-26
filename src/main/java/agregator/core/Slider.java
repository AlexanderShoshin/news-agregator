package agregator.core;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import agregator.io.NewsStorage;
import agregator.io.SettingsStorage;
import agregator.structure.NewsItem;

public interface Slider {
    public List<NewsItem> getNextSlides(SettingsStorage settingsStorage, NewsStorage newsStorage)
            throws ParserConfigurationException, SAXException, IOException;
}