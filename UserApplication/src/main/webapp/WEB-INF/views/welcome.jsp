<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
<%@ include file="layout/common-css.jsp" %>
</head>
<body>
	<%@ include file="layout/header.jsp"%>
		<div>
			<c:if test="${not empty Welcome}">
				<p>${Welcome}</p>
			</c:if>
		</div>	
	<%@ include file="layout/footer.jsp"%>
</body> 
</html>