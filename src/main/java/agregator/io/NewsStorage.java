package agregator.io;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import agregator.structure.NewsItem;

public interface NewsStorage {
    public List<NewsItem> parse() throws ParserConfigurationException, SAXException, IOException;
    public void add(NewsItem item) throws ParserConfigurationException, SAXException, IOException, TransformerException;
}