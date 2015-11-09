package agregator.filters;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import agregator.core.StoragesKeeper;
import agregator.io.SettingsStorage;
import agregator.structure.NewsItem;
import agregator.structure.NewsPack;
import agregator.utils.NewsProcessor;

public class NewsFilter extends HandlerInterceptorAdapter {
    private SettingsStorage settingsStorage;
    
    @Autowired
    public NewsFilter(ServletContext context) {
        settingsStorage = StoragesKeeper.getSettingsStorage(context);
    }
    
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse responce,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        String category;
        
        if (settingsStorage.getCategoryFilterEnabled()) {
            category = settingsStorage.getCategoryFilter();
            filterNewsPack(modelAndView.getModel(), category);
        }
    }
    
    private void filterNewsPack(Map<String, Object> model, String category) {
        List<NewsItem> news = (List<NewsItem>) model.get("news");
        int[] order = (int[]) model.get("order");
        int[] delays = (int[]) model.get("delays");
        NewsPack newsPack = new NewsPack(news, order, delays);
        
        newsPack = NewsProcessor.filterByCategory(newsPack, category);
        model.put("news", newsPack.getNews());
        model.put("order", newsPack.getOrder());
        model.put("delays", newsPack.getDelays());
    }
}