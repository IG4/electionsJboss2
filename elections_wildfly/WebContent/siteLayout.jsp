<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<head>
    	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	</head> 
	<body>
		<table border="1" width="300" cellspacing="5">
			<tr>
				<td colspan="2"><tiles:insertAttribute name="header" /></td>
			</tr>
			<tr>
				<td width="150"><tiles:insertAttribute name="navigation" /></td>
				<td width="150"><tiles:insertAttribute name="body" /></td>
			</tr>
			<tr>
				<td colspan="2"><tiles:insertAttribute name="footer" /></td>
			</tr>
		</table>
	</body>
</html>
