package agregator.filters;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import agregator.structure.NewsItem;
import agregator.structure.NewsPack;
import agregator.utils.NewsProcessor;

public class CategoryFilterInterceptor extends HandlerInterceptorAdapter { 
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse responce,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        Map<String, Object> model = modelAndView.getModel();
        List<NewsItem> news = (List<NewsItem>) model.get("news");
        int[] order = (int[]) model.get("order");
        int[] delays = (int[]) model.get("delays");
        NewsPack newsPack = new NewsPack(news, order, delays);
        
        newsPack = NewsProcessor.filterByCategory(newsPack, "career");
        model.put("news", newsPack.getNews());
        model.put("order", newsPack.getOrder());
        model.put("delays", newsPack.getDelays());
    }
}