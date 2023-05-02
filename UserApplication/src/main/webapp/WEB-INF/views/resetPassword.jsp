<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<c:if test="${msg ne null}">
						<div class="alert alert-info">
							<strong>${msg}</strong> 
						</div>
					</c:if>
					<c:if test="${Error ne null}">
						<div class="alert alert-danger">
							<strong>${Error}</strong> 
						</div>
					</c:if>
					
					<c:set var="actionUrl" value="${pageContext.request.contextPath}/reset-password" />
					<form name="resetPasswordForm" action="${actionUrl}" id="resetPasswordForm" method="POST" onsubmit="return formValidate();">
					<div class="tab-pane fade show" id="profile" role="tabpanel" aria-labelledby="profile-tab">
						<h3 class="register-heading">Reset Password Page</h3>
						<div class="row register-form">
							<div class="col-md-6">
								<div class="form-group">
									<input type="hidden" name="hdToken" value="${token}" />
									<input type="password" id="password" name="password" class="form-control" placeholder="New password *" />
									<span class="has-error" id="err-password"></span>
								</div>
								<div class="form-group">
									<input type="password" id="conPassword" name="conPassword" class="form-control" placeholder="Confirm password *"/>
									<span class="has-error" id="err-conPassword"></span>
								</div>
								<input type="submit" class="btnRegister" value="Save Password" />
							</div>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<p class="text-center"><a href="${pageContext.request.contextPath}/registration">Create an Account</a></p>
	</div>
	
<%@ include file="layout/footer.jsp" %>	
<script type="text/JavaScript">
	$(document).ready(function(){
    	$("#resetPasswordForm").trigger("reset");
    });
	
	$('#password').blur(function(){
    		passwordValidation();
	});
	
	$('#conPassword').blur(function(){
    		passwordCompare();
	});
	
	function passwordValidation(){
		var password = $('#password').val();
    		var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{6,20}$/;
    		var isBoolean = false;
    		
    		if(password.length < 6 || password.length > 20){
    			$('#err-password').html('invalid password length');
    			isBoolean = false;
    		}else{
    			if(!passwordPattern.test(password)){
    				$('#err-password').html('invalid password pattern');
    				isBoolean = false;
    			}else{
    				$('#err-password').html('');	
    				isBoolean = true;
    			}
    		}
    	return isBoolean;
	}
	
	function passwordCompare(){
		var password = $('#password').val();
		var conPassword = $('#conPassword').val();
    	var isBoolean = false;
    		
		if(conPassword.length < 6 || conPassword.length > 20){
			$('#err-conPassword').html('not match password');
			isBoolean = false;
		}else{
			if(password == conPassword){
				$('#err-conPassword').html('');
				isBoolean = true;
			}else{
				$('#err-conPassword').html('Not Match');
				isBoolean = false;
			}
		}
    	return isBoolean;
	}
				
	function formValidate(){
		let isPassword = passwordValidation();
		let isCompare = passwordCompare();
		let isFormValid = isPassword && isCompare;
		
		if(isFormValid){
			return true;
		}
		return false;
	}
</script>	
</body>
</html>