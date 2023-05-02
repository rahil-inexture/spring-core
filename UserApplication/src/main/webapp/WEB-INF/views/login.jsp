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
					<form:form name="loginForm" id="loginForm" method="POST" modelAttribute="login" onsubmit="return formValidate();">
					<div class="tab-pane fade show" id="profile" role="tabpanel"
						aria-labelledby="profile-tab">
							<c:if test="${Error ne null}">
								<div class="alert alert-danger">
	  								<strong>${Error}</strong> 
								</div>
							</c:if>
						<h3 class="register-heading">Login Page</h3>
						<div class="row register-form">
							<div class="col-md-6">
								<div class="form-group">
									<form:input type="email" id="email" path="email" class="form-control" placeholder="Email  *"/>
									<span class="has-error" id="err-email"></span>
								</div>
								<div class="form-group">
									<form:input type="password" id="password" path="password" class="form-control" placeholder="password *" value="" />
									<span class="has-error" id="err-password"></span>
									
								</div>
								<p class="text-center"><a href="${pageContext.request.contextPath}/forgot-password">Forget Password</a></p>		
							
								<input type="submit" class="btnRegister" value="Login" />
							</div>
						</div>
					</div>
					</form:form>
				</div>
			</div>
			<p class="text-center"><a href="${pageContext.request.contextPath}/registration">Create an Account</a></p>
		</div>
	</div>
	
<%@ include file="layout/footer.jsp" %>	
<script type="text/JavaScript">
	$(document).ready(function(){
    	$("#loginForm").trigger("reset");
    });


	$('#email').blur(function(){
    		emailValidation();
	});
	
	$('#password').blur(function(){
    		passwordValidation();
	});
		
	function formValidate(){
		let isEmail = emailValidation(), isPassword = passwordValidation();
		let isFormValid = isEmail && isPassword;
		
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
</script>	
</body>
</html>