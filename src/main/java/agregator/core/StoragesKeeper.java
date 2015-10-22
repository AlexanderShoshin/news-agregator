package agregator.core;

import javax.servlet.ServletContext;

import agregator.io.ContextStateStorage;
import agregator.io.FileNewsStorage;
import agregator.io.NewsStorage;
import agregator.io.StateStorage;

public class StoragesKeeper {
    public static StateStorage getStateStorage(ServletContext context) {
        StateStorage storage = (StateStorage)context.getAttribute("state_storage");
        if (storage == null) {
            storage = new ContextStateStorage(context);
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
}