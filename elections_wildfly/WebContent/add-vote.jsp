<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<body>
		<s:form>
  		<s:select name="candidats" id="id" list="election.getCandidats()"/>
		</s:form>
		<s:form>
  		<s:select name="electeurs" id="id" list="election.getElecteurs()"/>
		</s:form>
	</body>
</html>
