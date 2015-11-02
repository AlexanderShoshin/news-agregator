package agregator.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.context.ApplicationContext;
import org.xml.sax.SAXException;

import agregator.core.NewsWire;
import agregator.core.StoragesKeeper;
import agregator.io.NewsStorage;
import agregator.structure.NewsState;

@WebServlet("/")
public class GetNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsWire newsWire;
    private NewsStorage newsStorage;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        newsStorage = StoragesKeeper.getNewsStorage(config.getServletContext());
        
        ApplicationContext springContext = StoragesKeeper.getSpringContext(config.getServletContext());
        newsWire = (NewsWire) springContext.getBean("newsWire");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        NewsState curState = new NewsState(request.getSession());
        try {
            response.getOutputStream().write(newsWire.getNextPack(curState, newsStorage).getBytes());
        } catch (ParserConfigurationException | SAXException e) {
            response.getOutputStream().write("Server error".getBytes());
        }
    }
}