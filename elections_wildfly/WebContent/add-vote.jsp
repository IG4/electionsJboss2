<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-vote">
			<!-- Election -->
			<div>
				<s:set var="current" value="election.id"/>
				<s:hidden name="election.id"/>
				<table border="1">
					<tr><th colspan="4">Ajouter un vote pour l'élection</th></tr>
					<tr>
						<td><s:property value="election.code" /></td>
						<td><s:property value="election.nom" /></td>
						<td><s:property value="election.debut" /></td>
						<td><s:property value="election.fin" /></td>
					</tr>
				</table>
			</div>
			<!-- Votes -->
			<div>
				<table border="1">
					<tr><th colspan="3">Votes effectués</th></tr>
					<tr><th>Electeur</th><th>Candidat</th><th>Action</th></tr>
					<s:iterator value="election.votes">
					<tr>
						<td><s:property value="electeur.nom" />&nbsp;<s:property value="electeur.prenom" /></td>
						<td><s:property value="candidat.nom" />&nbsp;<s:property value="candidat.prenom" /></td>
						<td>
							<s:url action="add-vote" var="urlText" escapeAmp="false">
								<s:param name="removeVoteId">
									<s:property value="id" />
								</s:param>
								<s:param name="removeVoteElectionId">
									<s:property value="current" />
								</s:param>
							</s:url>
							<a href="<s:property value="urlText" />">Supprimer</a>
						</td>
					</tr>
					</s:iterator>
				</table>
			</div>
			<!-- Electors and Candidates -->
			<div>
				<table border="1">
					<tr>
						<th colspan="2">Affectation des votes</th>
					</tr>
					<tr>
						<th>Electeurs</th><th>Candidats</th>
					</tr>
					<tr>
						<td valign="top">
							<table border="1">
								<tr><th>Nom</th><th>Prénom</th><th>Action</th></tr>
								<s:iterator value="election.electeurs">
								<tr>
									<td><s:property value="nom" /></td>
									<td><s:property value="prenom" /></td>
									<td>
										<s:url action="add-vote" var="urlText" escapeAmp="false">
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
						<td valign="top">
							<table border="1">
								<tr><th>Nom</th><th>Prénom</th><th>Action</th></tr>
								<s:iterator value="election.candidats">
								<tr>
									<td><s:property value="nom" /></td>
									<td><s:property value="prenom" /></td>
									<td>
										<s:url action="add-vote" var="urlText" escapeAmp="false">
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
			<div>
				<table border="1">
					<tr>
						<th colspan="2">Vote à ajouter</th>
					</tr>
					<tr>
						<th>Electeur</th><th>Candidat</th>
					</tr>
					<tr>
						<td><s:property value="vote.electeur.nom" />&nbsp;<s:property value="vote.electeur.prenom" /></td>
						<td><s:property value="vote.candidat.nom" />&nbsp;<s:property value="vote.candidat.prenom" /></td>
					</tr>
				</table>
			</div>
			<s:submit key="submit" name="submit"/>
		</s:form>
	</body>
</html>
