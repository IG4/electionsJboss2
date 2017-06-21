<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<link rel="stylesheet" type="text/css" href="style.css">
<html>
	<head>
    	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	</head> 
	<body>
		<header>
			<tiles:insertAttribute name="header" />
		</header>
		<nav class="dropdownmenu">
			<tiles:insertAttribute name="navigation" />
		</nav>
		<section id="main">
			<tiles:insertAttribute name="body" />
		</section>
		<footer>
			<tiles:insertAttribute name="footer" />
		</footer>
	</body>
</html>
