package agregator;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.Document;

public class GetNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetNewsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String json = "";
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
        
		response.getWriter().append(json);
	}
}