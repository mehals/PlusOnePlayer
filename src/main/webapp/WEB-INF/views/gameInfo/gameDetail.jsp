<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<p>Details about this game</p>
<img src="${gameImageUrl}">
<ul>
<li>Game name: ${game.getGameName()}</li>
<li>Game size: ${game.getMaxPlayers() }</li>
</ul>
</html>