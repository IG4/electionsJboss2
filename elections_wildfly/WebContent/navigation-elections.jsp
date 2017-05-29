<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<ul>
			<li><a href="<s:url action="list-elections"/>"> Liste des élections</a></li>
			<li><a href="<s:url action="edit-election"/>"> Ajout d'un élection</a></li>
			<li><a href="<s:url action="welcome"/>"> Retour au menu principal</a></li>
		</ul>
	</body>
</html>