<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>UserList Page</title>
<%@ include file="./layout/common-css.jsp" %>
</head>
<body> 
		<jsp:include page="./layout/header.jsp" />
		<div class="heading">
			<p class="text-center"><b>View User List</b></p>
		</div>
		<c:if test="${msg ne null}">
			<div class="alert alert-success">
				<strong>${msg}</strong> 
			</div>
		</c:if>
		<c:if test="${not empty Error}">
			<div class="alert alert-warn">
				<strong>${Error}</strong> 
			</div>
		</c:if>
		
		
		<table class="table table-bordered table-sm">
		    <thead>
		        <tr>
		            <th>Sr No</th>
		            <th>Name</th>
		            <th>Gender</th>
		            <th>Email</th>
		            <th>Mobile</th>
		            <th>Create Date</th>
		            <th>Action</th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach items="${userLst}" var="list" varStatus="cnt"> 
		        <tr>
		            <td>${cnt.index + 1}</td>
		            <td>${list.username}</td>
		            <td>${list.gender}</td>
		            <td>${list.email}</td>
		            <td>${list.mobile}</td>
		            <td>${list.createOn}</td>
		            <td>
		            	<a class="btn btn-success" href="${pageContext.request.contextPath}/${list.id}/edit-profile" title="Edit" data-toggle="tooltip"><i class="fas fa-edit">&#xE254;</i></a>
                        <a class="btn btn-danger"  href="${pageContext.request.contextPath}/${list.id}/delete-profile" title="Delete" data-toggle="tooltip"><i class="far fa-trash-alt">&#xE872;</i></a>
		            </td>
		        </tr>
		        </c:forEach>
		    </tbody>
		</table>
		<jsp:include page="./layout/footer.jsp" />		
</body>
</html>