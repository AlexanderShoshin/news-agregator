package agregator.core;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import agregator.io.ContextSettingsStorage;
import agregator.io.FileNewsStorage;
import agregator.io.NewsStorage;
import agregator.io.SettingsStorage;

public class StoragesKeeper {
    public static SettingsStorage getSettingsStorage(ServletContext context) {
        SettingsStorage storage = (SettingsStorage)context.getAttribute("state_storage");
        if (storage == null) {
            storage = new ContextSettingsStorage(context);
            context.setAttribute("state_storage", storage);
        }
        return storage;
    }
    
    public static NewsStorage getNewsStorage(ServletContext context) {
        NewsStorage storage = (NewsStorage)context.getAttribute("news_storage");
        if (storage == null) {
            storage = new FileNewsStorage(context.getRealPath("/") + "data");
            context.setAttribute("news_storage", storage);
        }
        return storage;
    }
    
    public static ApplicationContext getSpringContext(ServletContext context) {
        ApplicationContext storage = (ApplicationContext)context.getAttribute("spring_context");
        if (storage == null) {
            storage = new ClassPathXmlApplicationContext("spring.xml");
            context.setAttribute("spring_context", storage);
        }
        return storage;
    }
}