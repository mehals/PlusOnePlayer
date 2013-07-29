<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<p>  What games do we have?  Well, there's
<ol>
<c:forEach var="game" items="${games}">
	<li>${game.getGameName()}</li>
</c:forEach>
</ol>

</body>
</html>
