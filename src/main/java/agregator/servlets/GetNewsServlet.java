package agregator.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agregator.core.NewsWire;
import agregator.core.StoragesKeeper;
import agregator.io.NewsStorage;
import agregator.io.StateStorage;

@WebServlet("/GetNewsServlet")
public class GetNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsWire newsWire;
    private StateStorage stateStorage;
    private NewsStorage newsStorage;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        stateStorage = StoragesKeeper.getStateStorage(config.getServletContext());
        newsStorage = StoragesKeeper.getNewsStorage(config.getServletContext());
        newsWire = new NewsWire();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.getOutputStream().write(newsWire.getNextPack(stateStorage, newsStorage).getBytes());
        } catch (Exception e) {
            response.getOutputStream().write("Server error".getBytes());
        }  
    }
}