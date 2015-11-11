package agregator.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import agregator.io.SettingsStorage;
import agregator.structure.NewsPack;
import agregator.utils.NewsProcessor;

@Component
public class CategoryFilter implements NewsFilter {
    @Autowired
    @Qualifier("defaultSettingsStorage")
    private SettingsStorage settingsStorage;
    
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