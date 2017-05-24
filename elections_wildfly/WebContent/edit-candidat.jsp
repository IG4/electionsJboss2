<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-candidat">
			<s:hidden name="candidat.id"/>
			<s:textfield label="Prénom" name="candidat.prenom"/>
			<s:textfield label="Nom" name="candidat.nom"/>
			<s:textfield type="date" label="Date de naissance" name="candidat.ddn"/>
			<s:textfield label="Localité" name="candidat.localite"/>
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
