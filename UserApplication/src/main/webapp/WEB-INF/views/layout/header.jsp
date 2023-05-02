<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-md navbar-light bg-light">
	<a href="./" class="navbar-brand">Brand</a>
	<button type="button" class="navbar-toggler" data-toggle="collapse"
		data-target="#navbarCollapse">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
		<div class="navbar-nav">
			<a href="./" class="nav-item nav-link active">Home</a>
			<c:if test="${sessionScope.UserSession.userType eq 1}">
				<a href="${pageContext.request.contextPath}/admin/view-user-list" class="nav-item nav-link active">ALL UserList</a>
			</c:if>
		</div>
		
		<!-- <form class="form-inline">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search">
				<div class="input-group-append">
					<button type="button" class="btn btn-secondary">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</div>
		</form> -->
		
		<c:choose>
			<c:when test="${not empty sessionScope.UserSession and sessionScope.UserSession ne null}"> 
				<div class="nav-item dropdown">
					<a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown"><c:out value="${sessionScope.UserSession.email}" /></a>
					<div class="dropdown-menu">
						<a href="${pageContext.request.contextPath}/${sessionScope.UserSession.userId}/view-profile" class="dropdown-item">View Profile</a> 
						<a href="${pageContext.request.contextPath}/${sessionScope.UserSession.userId}/edit-profile" class="dropdown-item">Edit Profile</a> 
						<a href="${pageContext.request.contextPath}/logout" class="dropdown-item">Logout</a>		
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="navbar-nav">
					<a href="${pageContext.request.contextPath}/login" class="nav-item nav-link">Login</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</nav>
