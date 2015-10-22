package agregator.io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
    
    public void saveXML(Document xmlDoc, String path) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(xmlDoc);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);
    }
}