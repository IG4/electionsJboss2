<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-parti">
			<s:hidden name="parti.id"/>
			<s:textfield label="Nom" name="parti.nom"/>
			<s:textfield type="date" label="Date de fondation" name="parti.ddf"/>
			<s:textfield label="Localité" name="parti.localite"/>
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
