<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-electeur">
			<s:hidden name="electeur.id"/>
			<s:textfield label="Prénom" name="electeur.prenom"/>
			<s:textfield label="Nom" name="electeur.nom"/>
			<s:textfield type="date" label="Date de naissance" name="electeur.ddn"/>
			<s:textfield label="Localité" name="electeur.localite"/>
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
