<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-election">
			<s:hidden name="election.id"/>
			<s:textfield label="Code" name="election.code"/>
			<s:textfield label="Nom" name="election.nom"/>
			<s:textfield type="date" label="Date de début" name="election.debut"/>
			<s:textfield type="date" label="Date de fin" name="election.fin"/>
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
