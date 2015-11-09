package agregator.filters;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import agregator.structure.NewsPack;

public class NewsInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    @Qualifier("categoryFilter")
    private NewsFilter newsFilter;
    
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse responce,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (newsFilter.enabled()) {
            NewsPack newsPack = filterNews(modelAndView);
            updateModel(modelAndView.getModel(), newsPack);
        }
    }
    
    private NewsPack filterNews(ModelAndView modelAndView) {
        NewsPack newsPack = new NewsPack(modelAndView.getModel());
        return newsFilter.filter(newsPack);
    }
    
    private void updateModel(Map<String, Object> newsModel, NewsPack newsPack) {
        newsModel.put("news", newsPack.getNews());
        newsModel.put("order", newsPack.getOrder());
        newsModel.put("delays", newsPack.getDelays());
    }
}