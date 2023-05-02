<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="layout/common-css.jsp" %>
<title>Login Page</title>	
</head>
<body>
	<div class="container register">
		<div class="row">
			<div class="col-md-9 register-right">
				<div class="tab-content" id="myTabContent">
					<c:set var="actionUrl" value="${pageContext.request.contextPath}/forgot-password" />
					<form:form method="POST" action="${actionUrl}" modelAttribute="forget"  id="forgotPasswordForm" onsubmit="return formValidate();">
					<div class="tab-pane fade show" id="profile" role="tabpanel" aria-labelledby="profile-tab">
							<c:if test="${Error ne null}">
								<div class="alert alert-danger">
	  								<strong>${Error}</strong> 
								</div>
							</c:if>
						<h3 class="register-heading">Forgot Password Page</h3>
						<div class="row register-form">
							<div class="col-md-6">
								<div class="form-group">
									<form:input path="email" id="email" class="form-control" placeholder="Email  *"/>
									<span class="has-error" id="err-email"></span>
								</div>
								<input type="submit" class="btnRegister" value="Forgot Password" />
							</div>
						</div>
					</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	
<%@ include file="layout/footer.jsp" %>	
<script type="text/JavaScript">
	$(document).ready(function(){
    	$("#forgotPasswordForm").trigger("reset");
    });

	$('#email').blur(function(){
    		emailValidation();
	});
	function formValidate(){
		let isEmail = emailValidation(), isPassword = passwordValidation();
		let isFormValid = isEmail;
		
		if(isFormValid){
			return true;
		}
		return false;
	}
	
	function emailValidation(){
		var email = $('#email').val();
    	var emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/;
		var isBoolean = false;
		
		if(email.length < 5 || email.length > 120){
			$('#err-email').html('invalid email length');
			isBoolean = false;
		}else{
			if(!emailPattern.test(email)){
				$('#err-email').html('invalid email pattern');
				isBoolean = false;
			}else{
				$('#err-email').html('');	
				isBoolean = true;
			}
		}
		return isBoolean;
	}
</script>	
</body>
</html>