package agregator.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import agregator.core.LinearSlider;
import agregator.core.NewsWire;
import agregator.core.Slider;
import agregator.core.StoragesKeeper;
import agregator.io.NewsStorage;
import agregator.structure.NewsItem;
import agregator.structure.NewsState;

@Controller
public class NewsController {
    private NewsWire newsWire;
    private NewsStorage newsStorage;
    
    @Autowired
    public NewsController(ServletContext context, NewsWire newsWire) {
        newsStorage = StoragesKeeper.getNewsStorage(context);
        this.newsWire = newsWire;
    }
    
    @RequestMapping("/get-news")
    public String getNews(HttpSession session, Model model) throws IOException, ParserConfigurationException, SAXException {
        NewsState curState = new NewsState(session);
        Slider slider = new LinearSlider();
        List<NewsItem> newsPack = slider.getNextSlides(curState, newsStorage);
        model.addAttribute("news", newsPack);
        return "jsonTemplate";
    }
}