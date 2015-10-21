package agregator.io;

import javax.servlet.ServletContext;

public class ContextStateStorage implements StateStorage {
    private ServletContext context;
    
    public ContextStateStorage(ServletContext context) {
        this.context = context;
    }

    public void setLastItemSent(int id) {
        context.setAttribute("lastItemSent", id);
    }

    public int getLastItemSent() {
        Object id = context.getAttribute("lastItemSent");
        return id == null ? 0 : (Integer)id;
    }

    public void setIsAdminVisited(boolean isVisited) {
        context.setAttribute("isAdminVisited", isVisited);
    }

    public boolean getIsAdminVisited() {
        Object id = context.getAttribute("isAdminVisited");
        return id == null ? false : (Boolean)id;
    }
}