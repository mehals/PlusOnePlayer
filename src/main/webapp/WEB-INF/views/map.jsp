<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Simple Map</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <style>
      html, body, #map-canvas {
        margin: 0;
        padding: 0;
        height: 800px;
        width: 800px;
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script>
    var map;
    var geocoder;
    var infoWindow;
    function initialize() {
    	geocoder = new google.maps.Geocoder();
    	geocoder.geocode( {'address': 'Seattle WA'}, function(results, status) {    		
    		var mapOptions = {
    				zoom: 11,
    				center: results[0].geometry.location,
    				mapTypeId: google.maps.MapTypeId.ROADMAP
    		};
    		
    		map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    		
    		dropMarkers();
    	});
    } 
    
    function dropMarkers() {
    	<c:forEach var="upcomingGame" items="${upcomingGames}">
	    	geocoder.geocode( {'address': '${upcomingGame.getGameLocation()}' + ' Seattle WA'}, function(results, status) {
	    		var marker = new google.maps.Marker({
	    			position: results[0].geometry.location,
	    			map: map,
	    		})	    		
	    		google.maps.event.addListener(marker, "mouseover", function() {
	    			var infoWindowOptions = {
	    					content: "<b>" + '${upcomingGame.getGameName()}' + "</b> has been proposed",
	    					maxWidth: 200	    					
	    			}
	    			infoWindow = new google.maps.InfoWindow(infoWindowOptions);
	    			
	    			infoWindow.open(map, marker);
	    		});
	    		google.maps.event.addListener(marker, "mouseout", function() {
	    			if (infoWindow != null) {
	    				//window.setTimeout(function(){infoWindow.setMap(null);}, 2000);
	    				infoWindow.setMap(null);
	    			}
	    		})
	    	});
    	</c:forEach>    	    
    	
    	
    }

    google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
  </body>
</html>