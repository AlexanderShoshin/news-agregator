<%@ include file="add-news-item.jsp" %>
<%@ include file="draw.jsp" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <h1><%= showGreeting(request.getSession().getServletContext()) %></h1>
        <div class="table">
            <h2>Current news:</h2>
            <table class="news">
                <tr>
                    <th>Title</th>
                    <th>Images folder</th>
                </tr>
                <%= showCurrentNews(request.getSession().getServletContext()) %>
            </table>
        </div>
        <div>
            <h2>Add new:</h2>
            <form>
                <p>
                    Title:<br>
                    <input type="text" name="title">
                </p>
                <p>
                Images folder:<br>
                <input type="text" name="imagesFolder">
                </p>
                <p>
                    <input type="submit" value="add">
                </p>
            </form>
        </div>
    </body>
</html>