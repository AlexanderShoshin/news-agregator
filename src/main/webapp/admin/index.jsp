<%@ include file="script.jsp" %>
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
			<%= showCurrentNews(request) %>
		</table>
		
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
	</body>
</html>