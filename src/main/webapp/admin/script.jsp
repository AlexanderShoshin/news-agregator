<%@ page import="agregator.io.LocalNews,
				 java.util.List,
				 agregator.structure.NewsItem,
				 javax.servlet.ServletContext,
				 agregator.io.Config,
				 javax.servlet.http.HttpServletRequest"
%>
<%!
private void addLocalNewsItem() {
    
}

private String showCurrentNews(HttpServletRequest request) throws Exception {
    String newsLines = "";
	ServletContext context = request.getSession().getServletContext();
	List<NewsItem> news = LocalNews.parse(Config.getLocalNewsLocation(context), 
	        							  Config.getLocalNewsDescriptor());
	for (NewsItem item: news) {
	    newsLines += newLine(item);
	}
	
	return newsLines;
}

private String newLine(NewsItem item) {	
    String line = "";
    
    line += "<tr>";
    line += newCell(item.title);
    line += newCell(item.imagesFolder);
    line += "</tr>";
    
    return line;
}

private String newCell(String value) {
   	return "<td>" + value + "</td>";
}
%>