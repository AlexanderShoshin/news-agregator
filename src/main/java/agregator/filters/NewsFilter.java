package agregator.filters;

import agregator.structure.NewsPack;

public interface NewsFilter {
    public boolean enabled();
    public NewsPack filter(NewsPack newsPack);
}