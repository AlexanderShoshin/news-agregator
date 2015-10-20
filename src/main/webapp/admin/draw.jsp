<%@ page import="agregator.io.LocalNews,
                 java.util.List,
                 agregator.structure.NewsItem"
%>
<%!
private String showGreeting(ServletContext context) {
    Boolean pageVisited = (Boolean)context.getAttribute("visited");
    if (pageVisited != null) {
        return "";
    } else {
        context.setAttribute("visited", new Boolean(true));
        return "May the fource be with you!";
    }
}

private String showCurrentNews(ServletContext context) throws Exception {
    String newsLines = "";
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