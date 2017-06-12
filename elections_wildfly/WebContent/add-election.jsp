<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-election">
			<s:hidden name="election.id"/>
			<table border="1">
				<tr>
					<th colspan="4">D�tails de l'�lection</th>
				</tr>
				<tr>
					<td colspan="2"><s:textfield label="Code" name="election.code"/></td>
					<td colspan="2"><s:textfield label="Nom" name="election.nom"/></td>
				</tr>
				<tr>
					<td colspan="2"><s:textfield type="date" label="Date de d�but" name="election.debut"/></td>
					<td colspan="2"><s:textfield type="date" label="Date de fin" name="election.fin"/></td>
				</tr>
			</table>
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
