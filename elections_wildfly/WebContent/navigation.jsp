<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<ul>
			<li><a href="<s:url action="list-electeurs"/>"> Gérer les électeurs</a></li>
			<li><a href="<s:url action="list-candidats"/>"> Gérer les candidats</a></li>
			<li><a href="<s:url action="list-partis"/>"> Gérer les partis</a></li>
			<li><a href="<s:url action="list-elections"/>"> Gérer les élections</a></li>
		</ul>
	</body>
</html>