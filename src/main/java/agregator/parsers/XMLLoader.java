package agregator.parsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLLoader {
    private DocumentBuilder docBuilder;
    
    public XMLLoader() throws ParserConfigurationException {
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    
    public Document loadXML(String path) throws SAXException, IOException {
        Document doc;
        
        doc = docBuilder.parse(new File(path));
        doc.getDocumentElement().normalize();
        
        return doc;
    }
}