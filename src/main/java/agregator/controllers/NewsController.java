package agregator.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import agregator.core.NewsWire;
import agregator.io.NewsStorage;
import agregator.structure.NewsPack;
import agregator.structure.NewsState;

@Controller
public class NewsController {
    private NewsWire newsWire;
    @Autowired
    @Qualifier("defaultNewsStorage")
    private NewsStorage newsStorage;
    
    @Autowired
    public NewsController(NewsWire newsWire) {
        this.newsWire = newsWire;
    }
    
    @RequestMapping("/get-news")
    public String getNews(HttpSession session, Model model) throws IOException, ParserConfigurationException, SAXException {
        NewsState curState = new NewsState(session);
        NewsPack newsPack = newsWire.getNextPack(curState, newsStorage);
        
        model.addAttribute("delays", newsPack.getDelays());
        model.addAttribute("order", newsPack.getOrder());
        model.addAttribute("news", newsPack.getNews());
        
        return "jsonTemplate";
    }
}