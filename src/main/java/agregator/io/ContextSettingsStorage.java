package agregator.io;

import javax.servlet.ServletContext;

public class ContextSettingsStorage implements SettingsStorage {
    private ServletContext context;
    
    public ContextSettingsStorage(ServletContext context) {
        this.context = context;
    }

    public void setIsAdminVisited(boolean isVisited) {
        context.setAttribute("isAdminVisited", isVisited);
    }

    public boolean getIsAdminVisited() {
        Object id = context.getAttribute("isAdminVisited");
        return id == null ? false : (Boolean)id;
    }

    public void setCategoryFilterEnabled(boolean isEnabled) {
        context.setAttribute("categoryFilterEnabled", isEnabled);
    }

    public void setCategoryFilter(String category) {
        context.setAttribute("categoryFilter", category);
    }

    public boolean getCategoryFilterEnabled() {
        Object value = context.getAttribute("categoryFilterEnabled");
        return value == null ? false : (Boolean)value;
    }

    public String getCategoryFilter() {
        Object value = context.getAttribute("categoryFilter");
        return value == null ? "career" : (String)value;
    }
}