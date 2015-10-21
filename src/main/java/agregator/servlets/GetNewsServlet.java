package agregator.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agregator.core.NewsWire;
import agregator.io.ContextStateStorage;
import agregator.io.FileNewsStorage;
import agregator.io.NewsStorage;
import agregator.io.StateStorage;

public class GetNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsWire newsWire;
    private StateStorage stateStorage;
    private NewsStorage newsStorage;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            stateStorage = new ContextStateStorage(config.getServletContext());
            newsStorage = new FileNewsStorage(config.getServletContext().getRealPath("/") + "data");
            newsWire = new NewsWire();
            newsWire.createNewSequense(stateStorage, newsStorage);
        } catch (Exception ignored) {}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.getWriter().append(newsWire.getNextPack(stateStorage, newsStorage));
        } catch (Exception e) {
            response.getWriter().append("Server error");
        }  
    }
}