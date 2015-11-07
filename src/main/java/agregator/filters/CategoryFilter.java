package agregator.filters;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import agregator.core.StoragesKeeper;
import agregator.io.SettingsStorage;

public class CategoryFilter extends HandlerInterceptorAdapter {
    private SettingsStorage settingsStorage;
    
    public CategoryFilter(ServletContext context) {
        settingsStorage = StoragesKeeper.getSettingsStorage(context);
    }
    
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse responce,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        String category;

    }
}