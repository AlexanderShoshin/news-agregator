<%@ page import="agregator.io.LocalNews,
				 agregator.structure.NewsItem,
				 javax.servlet.ServletContext,
				 agregator.io.Config,
				 agregator.io.EmptyParamException"
%>
<%!
private boolean notNull(String param) {
    return (param != null && param != "");
}

private String getParam(HttpServletRequest request, String paramName) throws EmptyParamException {
    String param = request.getParameter(paramName);
    if (notNull(param)) {
        return param;
    } else {
        throw new EmptyParamException();
    }
}
%>
<%
ServletContext context = request.getSession().getServletContext();

try {
    NewsItem newItem = new NewsItem();
    newItem.setTitle(getParam(request, "title"));
    newItem.setImagesFolder(getParam(request, "imagesFolder"));
    LocalNews.add(newItem, context);
} catch(EmptyParamException ignored) {}
%>