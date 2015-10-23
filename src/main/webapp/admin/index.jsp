<%@ page import="agregator.core.NewsAdmin,
                 agregator.io.NewsStorage,
                 agregator.io.StateStorage,
                 agregator.core.StoragesKeeper"
%>
<%!
private NewsAdmin newsAdmin;
private NewsStorage newsStorage;
private StateStorage stateStorage;
%>
<%
newsAdmin = new NewsAdmin();
stateStorage = StoragesKeeper.getStateStorage(config.getServletContext());
newsStorage = StoragesKeeper.getNewsStorage(config.getServletContext());

newsAdmin.addIncomingNews(request, newsStorage);
newsAdmin.changeCategoryFilter(request, stateStorage);
%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <h1><%= newsAdmin.getGreeting(stateStorage) %></h1>
        <div class="table">
            <h2>Current news:</h2>
            <table class="news">
                <tr>
                    <th>Title</th>
                    <th>Images folder</th>
                </tr>
                <%= newsAdmin.getNewsTable(newsStorage) %>
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
        <div>
            <h2>Switch category filter:</h2>
            <form>
                <p>
                    enable
                    <input type="checkbox" name="categoryFilterEnabled" <%= newsAdmin.getCatFilterStatus(stateStorage) %>><br>
                    value
                    <input type="text" name="categoryFilterValue" value="<%= newsAdmin.getCatFilterValue(stateStorage) %>">
                </p>
                <p>
                    <input type="submit" value="Save">
                </p>
            </form>
        </div>
    </body>
</html>