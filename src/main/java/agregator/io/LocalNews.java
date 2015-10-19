package agregator.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import agregator.structure.NewsItem;

public class LocalNews {
    public static List<NewsItem> parse(ServletContext context) throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> news;
        NodeList nNews;
        NewsItem nItem;
        int newsCnt;
        String path = Config.getLocalNewsLocation(context);
        Document newsXml = getDoc(context);
        
        news = new ArrayList<NewsItem>();
        
        nNews = newsXml.getElementsByTagName("newsItem");
        newsCnt = nNews.getLength();
        
        for (int i = 0; i < newsCnt; i++) {
            nItem = new NewsItem();
            nItem.id = Integer.toString(i);
            nItem.category = getChildValue(nNews.item(i), "category");
            nItem.author = getChildValue(nNews.item(i), "author");
            nItem.description = getChildValue(nNews.item(i), "description");
            nItem.publishedDate = getChildValue(nNews.item(i), "publishedDate");
            nItem.source = getChildValue(nNews.item(i), "source");
            nItem.title = getChildValue(nNews.item(i), "title");
            nItem.imagesFolder = getChildValue(nNews.item(i), "imagesFolder");
            nItem.images = new ArrayList<String>();
            nItem.images = FileExtractor.extract(path + "/" + nItem.imagesFolder);
            news.add(nItem);
        }
        return news;
    }
    
    private static Document getDoc(ServletContext context) throws ParserConfigurationException, SAXException, IOException {
        String path = Config.getLocalNewsLocation(context);
        String file = Config.getLocalNewsDescriptor();
        
        XMLLoader xmlLoader = new XMLLoader();
        return xmlLoader.loadXML(path + "/" + file);
    }
    
    private static String getChildValue(Node node, String childName) {
        return getChildNodes(node, childName).item(0).getTextContent();
    }
    
    private static NodeList getChildNodes(Node node, String childName) {
        Element element;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            element = (Element) node;
            return element.getElementsByTagName(childName);
        } else {
            return null;
        }
    }
    
    public static void add(NewsItem item, ServletContext context) {
        
    }
}