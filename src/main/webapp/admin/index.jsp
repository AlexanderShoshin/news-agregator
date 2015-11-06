<%@ page import="agregator.core.NewsAdmin,
                 agregator.io.NewsStorage,
                 agregator.io.SettingsStorage,
                 agregator.core.StoragesKeeper"
%>
<%!
private NewsAdmin newsAdmin;
private NewsStorage newsStorage;
private SettingsStorage settingsStorage;
%>
<%
newsAdmin = new NewsAdmin();
settingsStorage = StoragesKeeper.getSettingsStorage(config.getServletContext());
newsStorage = StoragesKeeper.getNewsStorage(config.getServletContext());

newsAdmin.addIncomingNews(request.getParameter("title"),
                          request.getParameter("imagesFolder"),
                          newsStorage);
newsAdmin.changeCategoryFilter(request.getParameter("categoryFilterValue"),
                               request.getParameter("categoryFilterEnabled"),
                               settingsStorage);
%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <h1><%= newsAdmin.getGreeting(settingsStorage) %></h1>
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
                    <input type="checkbox" name="categoryFilterEnabled" <%= newsAdmin.getCatFilterStatus(settingsStorage) %>><br>
                    value
                    <input type="text" name="categoryFilterValue" value="<%= newsAdmin.getCatFilterValue(settingsStorage) %>">
                </p>
                <p>
                    <input type="submit" value="Save">
                </p>
            </form>
        </div>
    </body>
</html>