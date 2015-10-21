package agregator.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import agregator.structure.NewsItem;

public class FileNewsStorage implements NewsStorage {
    private String path;
    
    public FileNewsStorage(String path) {
        this.path = path;
    }
    
    public List<NewsItem> parse() throws ParserConfigurationException, SAXException, IOException {
        List<NewsItem> news;
        NodeList nNews;
        NewsItem nItem;
        int newsCnt;
        List<String> images;
        Document newsXml = getDoc();
        
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
            nItem.setImagesFolder(getChildValue(nNews.item(i), "imagesFolder"));
            images = FileExtractor.extract(path + "/" + nItem.getImagesFolder());
            for (int j = 0; j < images.size(); j++) {
                nItem.addImage(images.get(j));
            }

            news.add(nItem);
        }
        return news;
    }
    
    private Document getDoc() throws ParserConfigurationException, SAXException, IOException {
        String file = Config.getLocalNewsDescriptor();
        
        XMLLoader xmlLoader = new XMLLoader();
        return xmlLoader.loadXML(path + "/" + file);
    }
    
    private String getChildValue(Node node, String childName) {
        try {
            return getChildNodes(node, childName).item(0).getTextContent();
        } catch (RuntimeException exc) {
            return "";
        }
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
    
    public void add(NewsItem item) throws Exception {
        Document newsXml = getDoc();
        
        Element newsItem = newsXml.createElement("newsItem");
        newsXml.getDocumentElement().appendChild(newsItem);
        
        newsItem.appendChild(createElement(newsXml, "category", item.getCategory()));
        newsItem.appendChild(createElement(newsXml, "title", item.getTitle()));
        newsItem.appendChild(createElement(newsXml, "description", item.getDescription()));
        newsItem.appendChild(createElement(newsXml, "imagesFolder", item.getImagesFolder()));
        newsItem.appendChild(createElement(newsXml, "publishedDate", item.getPublishedDate()));
        newsItem.appendChild(createElement(newsXml, "author", item.getAuthor()));
        newsItem.appendChild(createElement(newsXml, "source", item.getSource()));
        
        saveDoc(newsXml);
    }
    
    private Element createElement(Document doc, String tagName, String value) {
        Element elem = doc.createElement(tagName);
        elem.appendChild(doc.createTextNode(value));
        return elem;
    }
    
    private void saveDoc(Document xmlDoc)
            throws TransformerException, ParserConfigurationException {
        String file = Config.getLocalNewsDescriptor();
        XMLLoader xmlLoader = new XMLLoader();
        xmlLoader.saveXML(xmlDoc, path + "/" + file);
    }
}