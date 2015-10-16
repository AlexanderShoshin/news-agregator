package agregator.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agregator.core.NewsWire;

public class GetNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsWire newsWire;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            newsWire = new NewsWire();
            newsWire.createNewSequense(config.getServletContext());
        } catch (Exception ignored) {}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    ServletContext context = request.getSession().getServletContext();
	    try {
	        response.getWriter().append(newsWire.getNextPack(context));
	    } catch (Exception e) {
            response.getWriter().append("Server error");
        }  
	}
}