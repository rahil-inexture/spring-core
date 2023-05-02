<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>UserList Page</title>
<%@ include file="./layout/common-css.jsp" %>
</head>
<body> 
<jsp:include page="./layout/header.jsp" />
<table class="table table-hover table-fixed">
	<thead>
		<tr>
			<th scope="row" class="text-center">View Profile</th>
		</tr>
	</thead>
	<c:choose>
		<c:when test="${not empty userLst}">
			<tbody>
			<c:forEach items="${userLst}" var="usrLst">
				<tr>
					<th scope="row">Name</th>
					<td>${usrLst.username}</td>
				</tr>
				<tr>
					<th scope="row">Gender</th>
					<td>${usrLst.gender}</td>
				</tr>
				<tr>
					<th scope="row">email</th>
					<td>${usrLst.email}</td>
				</tr>
				<tr>
					<th scope="row">Mobile</th>
					<td>${usrLst.mobile}</td>
				</tr>
				<tr>
					<th scope="row">CreatedOn</th>
					<td>${usrLst.createOn}</td>
				</tr>			
				<tr>
					<th scope="row">Sr no</th>
					<th scope="row">Address</th>
				</tr>
				<c:forEach items="${usrAddressLst}" var="addLst" varStatus="cnt">
					<tr>
						<td><c:out value="${cnt.index + 1}"/></th>
						<td>City : ${addLst.city}, State : ${addLst.state}, ${addLst.pinCode}</th>
					</tr>
				</c:forEach>
			</c:forEach>
			<tbody>	
		</c:when>
		<c:otherwise>
			<tbody>
				<tr>
					<td rowspan="5" class="text-center">Records Not Found</td>	
				</tr>
			</tbody>
		</c:otherwise>
	</c:choose>
	
</table>
<jsp:include page="./layout/footer.jsp" />
</body>
</html>
