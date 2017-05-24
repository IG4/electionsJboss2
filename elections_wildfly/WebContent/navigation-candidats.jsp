<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<ul>
			<li><a href="<s:url action="list-candidats"/>"> Liste des candidats</a></li>
			<li><a href="<s:url action="edit-candidat"/>"> Ajout d'un candidat</a></li>
			<li><a href="<s:url action="welcome"/>"> Retour au menu principal</a></li>
		</ul>
	</body>
</html>