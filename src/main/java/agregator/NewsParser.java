package agregator;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NewsParser {
    public List<NewsItem> parseNews(Document newsDocument) {
        List<NewsItem> news;
        NodeList nNews;
        NewsItem nItem;
        NodeList images;
        int newsCnt;
        
        news = new ArrayList<NewsItem>();
        
        nNews = newsDocument.getElementsByTagName("newsItem");
        newsCnt = nNews.getLength();
        
        for (int i = 0; i < newsCnt; i++) {
            nItem = new NewsItem();
            nItem.category = getChildValue(nNews.item(i), "category");
            nItem.author = getChildValue(nNews.item(i), "author");
            nItem.description = getChildValue(nNews.item(i), "description");
            nItem.publishedDate = getChildValue(nNews.item(i), "publishedDate");
            nItem.source = getChildValue(nNews.item(i), "source");
            nItem.title = getChildValue(nNews.item(i), "title");
            nItem.images = new ArrayList<String>();
            images = getChildNodes(getChildNodes(nNews.item(i), "images").item(0), "image");
            for (int j = 0; j < images.getLength(); j++) {
                nItem.images.add(images.item(j).getTextContent());
            }
            news.add(nItem);
        }
        return news;
    }
    
    private String getChildValue(Node node, String childName) {
        return getChildNodes(node, childName).item(0).getTextContent();
    }
    
    private NodeList getChildNodes(Node node, String childName) {
        Element element;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            element = (Element) node;
            return element.getElementsByTagName(childName);
        } else {
            return null;
        }
    }
}