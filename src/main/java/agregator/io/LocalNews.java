package agregator.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import agregator.structure.NewsItem;

public class LocalNews {
    public static List<NewsItem> parse(String path, String file) throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> news;
        NodeList nNews;
        NewsItem nItem;
        int newsCnt;
        
        XMLLoader xmlLoader = new XMLLoader();
        Document newsXml = xmlLoader.loadXML(path + "/" + file);
        
        news = new ArrayList<NewsItem>();
        
        nNews = newsXml.getElementsByTagName("newsItem");
        newsCnt = nNews.getLength();
        
        for (int i = 0; i < newsCnt; i++) {
            nItem = new NewsItem();
            nItem.setId(Integer.toString(i));
            nItem.setCategory(getChildValue(nNews.item(i), "category"));
            nItem.setAuthor(getChildValue(nNews.item(i), "author"));
            nItem.setDescription(getChildValue(nNews.item(i), "description"));
            nItem.setPublishedDate(getChildValue(nNews.item(i), "publishedDate"));
            nItem.setSource(getChildValue(nNews.item(i), "source"));
            nItem.setTitle(getChildValue(nNews.item(i), "title"));
            images = getChildNodes(getChildNodes(nNews.item(i), "images").item(0), "image");
            for (int j = 0; j < images.getLength(); j++) {
                nItem.addImage(images.item(j).getTextContent());
            }
            news.add(nItem);
        }
        return news;
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
}