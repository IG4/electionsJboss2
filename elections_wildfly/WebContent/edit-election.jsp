<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-election">
			<!-- Election -->
			<div>
				<s:set var="current" value="election.id"/>
				<s:hidden name="election.id"/>
				<table border="1">
					<tr>
						<th colspan="4">Détails de l'élection</th>
					</tr>
					<tr>
						<td colspan="2"><s:textfield label="Code" name="election.code"/></td>
						<td colspan="2"><s:textfield label="Nom" name="election.nom"/></td>
					</tr>
					<tr>
						<td colspan="2"><s:textfield type="date" label="Date de début" name="election.debut"/></td>
						<td colspan="2"><s:textfield type="date" label="Date de fin" name="election.fin"/></td>
					</tr>
				</table>
			</div>
			<!-- Electors -->
			<div>
				<table border="1">
					<tr>
						<th colspan="2">Affectation des électeurs</th>
					</tr>
					<tr>
						<th>Electeurs affectés</th>
						<th>Electeurs Disponibles</th>
					</tr>
					<tr>
						<td valign="top">
							<table border="1">
								<tr>
									<th>Nom</th><th>Prénom</th><th>Action</th>
								</tr>
								<s:iterator value="election.electeurs">
								<tr>
									<td><s:property value="nom" /></td>
									<td><s:property value="prenom" /></td>
									<td>
										<s:url action="edit-election" var="urlText" escapeAmp="false">
											<s:param name="removeElecteurId">
												<s:property value="id" />
											</s:param>
											<s:param name="removeElecteurElectionId">
												<s:property value="current" />
											</s:param>
										</s:url> 
										<a href="<s:property value="urlText" />">Retirer</a>
									</td>
								</tr>
								</s:iterator>
							</table>
						</td>
						<td valign="top">
							<table border="1">
								<tr>
									<th>Nom</th><th>Prénom</th><th>Action</th>
								</tr>
								<s:iterator value="electeurs">
								<tr>
									<td><s:property value="nom" /></td>
									<td><s:property value="prenom" /></td>
									<td>
										<s:url action="edit-election" var="urlText" escapeAmp="false">
											<s:param name="addElecteurId">
												<s:property value="id" />
											</s:param>
											<s:param name="addElecteurElectionId">
												<s:property value="current" />
											</s:param>
										</s:url> 
										<a href="<s:property value="urlText" />">Affecter</a>
									</td>
								</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<!-- Candidates -->
			<div>
				<table border="1">
					<tr>
						<th colspan="2">Affectation des candidats</th>
					</tr>
					<tr>
						<th>Candidats affectés</th>
						<th>Candidats Disponibles</th>
					</tr>
					<tr>
						<td valign="top">
							<table border="1">
								<tr>
									<th>Nom</th><th>Prénom</th><th>Action</th>
								</tr>
								<s:iterator value="election.candidats">
								<tr>
									<td><s:property value="nom" /></td>
									<td><s:property value="prenom" /></td>
									<td>
										<s:url action="edit-election" var="urlText" escapeAmp="false">
											<s:param name="removeCandidatId">
												<s:property value="id" />
											</s:param>
											<s:param name="removeCandidatElectionId">
												<s:property value="current" />
											</s:param>
										</s:url> 
										<a href="<s:property value="urlText" />">Retirer</a>
									</td>
								</tr>
								</s:iterator>
							</table>
						</td>
						<td valign="top">
							<table border="1">
								<tr>
									<th>Nom</th><th>Prénom</th><th>Action</th>
								</tr>
								<s:iterator value="candidats">
								<tr>
									<td><s:property value="nom" /></td>
									<td><s:property value="prenom" /></td>
									<td>
										<s:url action="edit-election" var="urlText" escapeAmp="false">
											<s:param name="addCandidatId">
												<s:property value="id" />
											</s:param>
											<s:param name="addCandidatElectionId">
												<s:property value="current" />
											</s:param>
										</s:url> 
										<a href="<s:property value="urlText" />">Affecter</a>
									</td>
								</tr>
								</s:iterator>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
