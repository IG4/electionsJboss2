<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
	<s:if test="hasActionErrors()">
		<s:actionerror />
	</s:if>
</body>
</html>