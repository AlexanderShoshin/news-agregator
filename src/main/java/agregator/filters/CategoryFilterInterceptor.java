package agregator.filters;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import agregator.structure.NewsItem;

public class CategoryFilterInterceptor extends HandlerInterceptorAdapter { 
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse responce,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("modelAndView - " + ((List<NewsItem>) modelAndView.getModel().get("news")).get(0).getId()  );
        // TODO Auto-generated method stub
    }
}