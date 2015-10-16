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

/*String json = "";
int newsDoneCount;

HttpSession session = request.getSession(true);
if (session.isNew()) {
    newsDoneCount = 0;
} else {
    newsDoneCount = (Integer)session.getAttribute("newsDoneCount");
}
session.setAttribute("newsDoneCount", ++newsDoneCount);

String contextPath = request.getSession().getServletContext().getRealPath("/");
try {
    XMLLoader xmlLoader = new XMLLoader();
    Document newsXml = xmlLoader.loadXML(contextPath + "data\\news.xml");
    NewsParser newsParser = new NewsParser();
    List<NewsItem> news = newsParser.parseFromXml(newsXml);
    json = newsParser.parseToJson(news.get(newsDoneCount%news.size()));
} catch (Exception e) {
    response.getWriter().append("Server error");
}

response.getWriter().append(json);*/