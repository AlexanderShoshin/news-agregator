<html>
    <head>
        <link rel="stylesheet" type="text/css" href="resources/css/main.css">
    </head>
    <body>
        <h1>${greeting}</h1>
        <div class="table">
            <h2>Current news:</h2>
            <table class="news">
                <tr>
                    <th>Title</th>
                    <th>Images folder</th>
                </tr>
                ${newsTable}
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
                    <input type="checkbox" name="categoryFilterEnabled" ${filterStatus}><br>
                    value
                    <input type="text" name="categoryFilterValue" value="${filterValue}">
                </p>
                <p>
                    <input type="submit" value="Save">
                </p>
            </form>
        </div>
    </body>
</html>