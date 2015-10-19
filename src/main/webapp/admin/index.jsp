<%@ page import="agregator.io.LocalNews" %>
<html>
<body>
<h2>Current count:</h2>
<em><%= request.getSession().getServletContext().getAttribute("itemsSentCount") %></em>
<%= LocalNews.parse(request.getSession().getServletContext().getRealPath("/") + "data\\news.xml").get(0).author %>
</body>
</html>