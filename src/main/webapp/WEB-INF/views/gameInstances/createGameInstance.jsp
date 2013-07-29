<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<p>  What games do we have?  Well, there's
<ol>
<c:forEach var="game" items="${gameList}">
	<li>${game.getGameName()}</li>
</c:forEach>
</ol>
</p>

<sf:form method="POST" modelAttribute="gameInstance">
	<fieldset>
		<label>
			Game name
		</label>
		<sf:input path="gameName" size="100" id="game_name"/>
		<br/>
		<label>
			Game time
		</label>
		<sf:input path="gameDate" size="100" id="game_date"/>
		<br/>
		<label>
			Game host
		</label>
		<sf:input path="host" size="100" id="game_host"/>
		<br/>
		<label>
			Game location
		</label>
		<sf:input path="gameLocation" size="200" id="game_location"/>
		<input name="commit" type="submit" value="Lets get it on"/>
	</fieldset>
</sf:form>

</html>