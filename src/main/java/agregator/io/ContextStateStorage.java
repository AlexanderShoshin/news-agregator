package agregator.io;

import java.util.List;

import javax.servlet.ServletContext;

public class ContextStateStorage implements StateStorage {
    private ServletContext context;
    
    public ContextStateStorage(ServletContext context) {
        this.context = context;
    }

    public void setNewsSequense(List<Integer> newsSequense) {
        context.setAttribute("newsSequense", newsSequense);
    }

    public void setItemsSent(int cnt) {
        context.setAttribute("itemsSentCount", cnt);
    }

    public List<Integer> getNewsSequense() {
        return (List<Integer>)context.getAttribute("newsSequense");
    }

    public int getItemsSent() {
        return (Integer)context.getAttribute("itemsSentCount");
    }
}