<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<ul>
			<li><a href="<s:url action="list-electeurs"/>"> G�rer les �lecteurs</a></li>
			<li><a href="<s:url action="list-candidats"/>"> G�rer les candidats</a></li>
			<li><a href="<s:url action="list-partis"/>"> G�rer les partis</a></li>
			<li><a href="<s:url action="list-elections"/>"> G�rer les �lections</a></li>
		</ul>
	</body>
</html>