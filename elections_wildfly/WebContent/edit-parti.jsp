<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form action="save-parti">
			<!-- Parti -->
			<div>
				<s:set var="current" value="parti.id"/>
				<s:hidden name="parti.id"/>
				<s:textfield label="Nom" name="parti.nom"/>
				<s:textfield type="date" label="Date de fondation" name="parti.ddf"/>
				<s:textfield label="Localité" name="parti.localite"/>
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
								<s:iterator value="parti.candidats">
								<tr>
									<td><s:property value="nom" /></td>
									<td><s:property value="prenom" /></td>
									<td>
										<s:url action="edit-parti" var="urlText" escapeAmp="false">
											<s:param name="removeCandidatId">
												<s:property value="id" />
											</s:param>
											<s:param name="removeCandidatPartiId">
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
										<s:url action="edit-parti" var="urlText" escapeAmp="false">
											<s:param name="addCandidatId">
												<s:property value="id" />
											</s:param>
											<s:param name="addCandidatPartiId">
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
