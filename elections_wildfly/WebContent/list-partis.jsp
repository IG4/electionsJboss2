<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
	<table border="1">
		<s:iterator value="partis">
			<tr>
				<td><s:property value="nom" /></td>
				<td><s:property value="ddf" /></td>
				<td><s:property value="localite" /></td>
				<td>
					<s:url action="edit-parti" var="urlText">
						<s:param name="partiId">
							<s:property value="id" />
						</s:param>
					</s:url> 
					<a href="<s:property value="urlText" />">editer</a>
				</td>
				<td>
					<s:url action="delete-parti" var="urlText">
						<s:param name="partiId">
							<s:property value="id" />
						</s:param>
					</s:url> 
					<a href="<s:property value="urlText" />">supprimer</a>
				</td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>
