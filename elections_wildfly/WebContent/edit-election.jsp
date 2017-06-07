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
			<s:select label="Candidats" name="candidatids" list="election.candidats" listKey="id" listValue="NomComplet" multiple="true" size="election.candidats.length()" />
			<s:select label="Electeurs" name="electeurids" list="election.electeurs" listKey="id" listValue="NomComplet" multiple="true" size="election.electeurs.length()" />
			<s:select label="CandidatsDispo" name="candidatdispoids" list="candidatsDispo" listKey="id" listValue="NomComplet" multiple="true" size="candidatsDispo.length()" />
			<s:select label="ElecteursDispo" name="electeurdispoids" list="electeursDispo" listKey="id" listValue="NomComplet" multiple="true" size="electeursDispo.length()" />
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
