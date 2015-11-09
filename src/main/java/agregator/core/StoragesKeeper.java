package agregator.core;

import javax.servlet.ServletContext;

import agregator.io.ContextSettingsStorage;
import agregator.io.FileNewsStorage;
import agregator.io.NewsStorage;
import agregator.io.SettingsStorage;

public class StoragesKeeper {
    public static NewsStorage getNewsStorage(ServletContext context) {
        NewsStorage storage = (NewsStorage)context.getAttribute("news_storage");
        if (storage == null) {
            storage = new FileNewsStorage(context.getRealPath("/") + "data");
            context.setAttribute("news_storage", storage);
        }
        return storage;
    }
}