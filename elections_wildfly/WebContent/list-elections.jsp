<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
	<table border="1">
		<s:iterator value="elections">
			<tr>
				<td><s:property value="code" /></td>
				<td><s:property value="nom" /></td>
				<td><s:property value="debut" /></td>
				<td><s:property value="fin" /></td>
				<td>
					<s:url action="edit-election" var="urlText">
						<s:param name="electionId">
							<s:property value="id" />
						</s:param>
					</s:url> 
					<a href="<s:property value="urlText" />">editer</a>
				</td>
				<td>
					<s:url action="delete-election" var="urlText">
						<s:param name="electionId">
							<s:property value="id" />
						</s:param>
					</s:url> 
					<a href="<s:property value="urlText" />">supprimer</a>
				</td>
				<td>
					<s:url action="add-vote" var="urlText">
						<s:param name="electionId">
							<s:property value="id" />
						</s:param>
					</s:url> 
					<a href="<s:property value="urlText" />">Ajouter vote</a>
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>
