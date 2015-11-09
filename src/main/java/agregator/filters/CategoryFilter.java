package agregator.filters;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import agregator.core.StoragesKeeper;
import agregator.io.SettingsStorage;
import agregator.structure.NewsPack;
import agregator.utils.NewsProcessor;

@Component
public class CategoryFilter implements NewsFilter {
    private SettingsStorage settingsStorage;
    
    @Autowired
    public CategoryFilter(ServletContext context) {
        settingsStorage = StoragesKeeper.getSettingsStorage(context);
    }
    
    @Override
    public boolean enabled() {
        return settingsStorage.getCategoryFilterEnabled();
    }
    
    @Override
    public NewsPack filter(NewsPack newsPack) {
        String category = settingsStorage.getCategoryFilter();
        return NewsProcessor.filterByCategory(newsPack, category);
    }
}