<%@ page import="agregator.io.LocalNews, java.util.List, agregator.structure.NewsItem" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
<h2>Current news:</h2>
<table class="news">
	<tr>
		<th>Title</th>
		<th>Images folder</th>
	</tr>
	<%
		List<NewsItem> news = LocalNews.parse(request.getSession().getServletContext().getRealPath("/") + "data", "news.xml");
		for (NewsItem item: news) {
		    out.println("<tr>");
		    out.println("<td>");
		    out.println(item.title);
		    out.println("</td>");
		    out.println("<td>");
		    out.println(item.imagesFolder);
		    out.println("</td>");
		    out.println("</tr>");
		}
	%>
	
</table>
</body>
</html>