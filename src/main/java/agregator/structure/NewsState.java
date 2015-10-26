package agregator.structure;

import javax.servlet.http.HttpSession;

public class NewsState {
    private final HttpSession session;
    
    public NewsState(HttpSession session) {
        this.session = session;
    }
    
    public void setLastItemSent(int id) {
        session.setAttribute("lastItemSent", id);
    }

    public int getLastItemSent() {
        Object id = session.getAttribute("lastItemSent");
        return id == null ? 0 : (Integer)id;
    }
}