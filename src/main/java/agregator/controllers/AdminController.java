package agregator.controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import agregator.core.NewsAdmin;
import agregator.core.StoragesKeeper;
import agregator.io.NewsStorage;
import agregator.io.SettingsStorage;

@Controller
public class AdminController {
    private NewsAdmin newsAdmin;
    private NewsStorage newsStorage;
    private SettingsStorage settingsStorage;
    
    @Autowired
    public AdminController(ServletContext context) {
        newsAdmin = new NewsAdmin();
        settingsStorage = StoragesKeeper.getSettingsStorage(context);
        newsStorage = StoragesKeeper.getNewsStorage(context);
    }
    
    @RequestMapping(value = "/admin-page", params = {"categoryFilterValue"})
    public String setNewsFilter(Model model,
                               @RequestParam String categoryFilterValue,
                               @RequestParam(required = false) String categoryFilterEnabled) throws ParserConfigurationException, SAXException, IOException {
        newsAdmin.changeCategoryFilter(categoryFilterValue, categoryFilterEnabled, settingsStorage);
        return getAdminPage(model);
    }
    
    @RequestMapping(value = "/admin-page", params = {"title", "imagesFolder"})
    public String addNewsItem(Model model,
                              @RequestParam String title,
                              @RequestParam String imagesFolder) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        newsAdmin.addIncomingNews(title, imagesFolder, newsStorage);
        return getAdminPage(model);
    }
    
    @RequestMapping(value = "/admin-page")
    public String getAdminPage(Model model) throws ParserConfigurationException, SAXException, IOException{
        model.addAttribute("greeting", newsAdmin.getGreeting(settingsStorage));
        model.addAttribute("newsTable", newsAdmin.getNewsTable(newsStorage));
        model.addAttribute("filterValue", newsAdmin.getCatFilterValue(settingsStorage));
        model.addAttribute("filterStatus", newsAdmin.getCatFilterStatus(settingsStorage));
        return "adminPage";
    }
}