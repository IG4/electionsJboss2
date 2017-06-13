<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-parti">
			<s:hidden name="parti.id"/>
			<table border="1">
				<tr><th colspan="2">Données du parti</th></tr>
				<tr><td colspan="2"><s:textfield label="Nom" name="parti.nom"/></td></tr>
				<tr><td colspan="2"><s:textfield type="date" label="Date de fondation" name="parti.ddf"/></td></tr>
				<tr><td colspan="2"><s:textfield label="Localité" name="parti.localite"/></td></tr>
			</table>
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
