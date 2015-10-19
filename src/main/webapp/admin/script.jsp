<%@ page import="agregator.io.LocalNews,
				 java.util.List,
				 agregator.structure.NewsItem,
				 javax.servlet.ServletContext,
				 agregator.io.Config,
				 javax.servlet.http.HttpServletRequest,
				 agregator.io.FileExtractor"
%>
<%!
private boolean notNull(String param) {
    return (param != null && param != "");
}

private String showCurrentNews(HttpServletRequest request) throws Exception {
    String newsLines = "";
	ServletContext context = request.getSession().getServletContext();
	List<NewsItem> news = LocalNews.parse(context);
	for (NewsItem item: news) {
	    newsLines += newLine(item);
	}
	
	return newsLines;
}

private String newLine(NewsItem item) {	
    String line = "";
    
    line += "<tr>";
    line += newCell(item.getTitle());
    line += newCell(item.getImagesFolder());
    line += "</tr>";
    
    return line;
}

private String newCell(String value) {
   	return "<td>" + value + "</td>";
}
%>
<%
List<String> images;
ServletContext context = request.getSession().getServletContext();
String path = Config.getLocalNewsLocation(context);
String itemTitle = request.getParameter("title");
String itemImagesFolder = request.getParameter("imagesFolder");



if (notNull(itemTitle) && notNull(itemImagesFolder)) {
    
    NewsItem newItem = new NewsItem();
    newItem.setTitle(itemTitle);
    newItem.setImagesFolder(itemImagesFolder);
    
    images = FileExtractor.extract(path + "/" + newItem.getImagesFolder());
    for (int j = 0; j < images.size(); j++) {
        newItem.addImage(images.get(j));
    }
    
    LocalNews.add(newItem, context);
}
%>