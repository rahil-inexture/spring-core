<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<%@ include file="layout/common-css.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/jquery-ui-sortable@1.0.0/jquery-ui.min.js"></script>
<link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.3.1/minty/bootstrap.min.css">
</head>
<body>
	<%@ include file="layout/header.jsp"%>
	<c:choose>
        <c:when test="${userForm.id eq 0 or userForm.id eq null}"><c:set var="actionUrl" value="${pageContext.request.contextPath}/registration" /></c:when>
        <c:otherwise><c:set var="actionUrl" value="${pageContext.request.contextPath}/${userForm.id}/updateProfile" /></c:otherwise>
    </c:choose>
	<div class="container register">
		<div class="row">
			<div class="col-md-9 register-right">				
				<div class="tab-content" id="myTabContent">
					<form:form method="POST" action="${actionUrl}" modelAttribute="userForm" onsubmit="return regFormValidate();">
					<div class="tab-pane fade show active" id="home" role="tabpanel"
						aria-labelledby="home-tab">
						<c:if test="${userForm.id eq null}">
		       				<h3 class="register-heading">Add New User</h3>
	            		</c:if>
	            		
	            		<c:if test="${not empty userForm.id}">
		      				<h3 class="register-heading">EDIT USER</h3>
		      				<form:hidden path="id"/>
	            		</c:if>
						
						<div class="row register-form">
							<div>
								<c:if test="${Error ne null}">
									<ul>
										<li>${Error}</li>
									</ul>
								</c:if>
								<c:if test="${msg ne null}">
									<div class="alert alert-success">
										<strong>${msg}</strong> 
									</div>
								</c:if>
								
							</div>
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" id="username" path="username" class="form-control" placeholder="User Name *"/>
									<form:errors path="username"></form:errors>
									<span class="has-error" id="err-username"></span>
								</div>
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<div class="maxl">
										<label class="radio inline"> 
											<form:radiobuttons path="gender" items="${genderOptions}"/> 
										</label>
										<form:errors path="gender"/>
									</div>
									<span class="has-error" id="err-gender"></span>
								</div>
								
								
								<c:if test="${userForm.id eq 0 or userForm.id eq null}">
			      					<div class="form-group ${status.error ? 'has-error' : ''}">
										<form:input type="password" id="password" path="password" class="form-control"
											placeholder="Password *"/>
										<form:errors path="password"></form:errors>
										<span class="has-error" id="err-password"></span>
									</div>
								
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<form:input type="password" id="conPassword" path="conPassword" class="form-control"
											placeholder="Confirm Password *"/>
										<form:errors path="conPassword"></form:errors>
										<span class="has-error" id="err-conPassword"></span>
									</div>
	         					</c:if>
	         					
							</div>
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<c:if test="${sessionScope.UserSession.userType eq 1}">
										<form:input type="email" id="email" path="email" class="form-control" placeholder="Your Email *" />
									</c:if>
									<c:if test="${sessionScope.UserSession.userType eq 2}">
										<form:input type="email" id="email" path="email" class="form-control" placeholder="Your Email *" readonly="true" />
									</c:if>
									<c:if test="${userForm.id eq 0 or userForm.id eq null}">
										<form:input type="email" id="email" path="email" class="form-control" placeholder="Your Email *"/>
									</c:if>
									
									<form:errors path="email"></form:errors>
									<span class="has-error" id="err-email"></span>
								</div>
								<div class="form-group">
									<form:input type="text" id ="mobile" path="mobile" class="form-control"
										placeholder="Your Phone *" />
									<span class="has-error" id="err-mobile"></span>	
								</div>
							</div>
							<!--  -->
								<div class="container">
									<h4>Manage Address</h4>
										<table data-dynamicrows class="table table-bordered table-striped">
										  <thead>
										    <tr>
										      <th>city</th>
										      <th>state</th>
										      <th>zip</th>
										      <th>Operation</th>
										    </tr>
										  </thead>
										  <tbody>
										  <c:choose>
											    <c:when test="${not empty userForm.address}">
											       <c:forEach items="${userForm.address}" varStatus="loop">
													  	<form:input type="hidden" path="address[${loop.index}].id" class="form-control" />
													  	<tr>
													  	  <td><form:input type="text" path="address[${loop.index}].city" class="form-control" /></td>
													      <td><form:input type="text" path="address[${loop.index}].state" class="form-control" /></td>
													      <td><form:input type="text" path="address[${loop.index}].pinCode" class="form-control" /></td>
													      <td>
													        <i class="fa fa-minus" data-remove></i>
													        <i class="fa fa-arrows" data-move></i>
													        <i class="fa fa-plus" data-add></i>
													      </td>
													    </tr>
										  			</c:forEach>
											    </c:when>
											    <c:otherwise>
											        <tr>
												      <td><input type="text" name="address[0].city" class="form-control"></td>
												      <td><input type="text" name="address[0].state" class="form-control"></td>
												      <td><input type="text" name="address[0].pinCode" class="form-control"></td>
												      <td>
												        <i class="fa fa-minus" data-remove></i>
												        <i class="fa fa-arrows" data-move></i>
												        <i class="fa fa-plus" data-add></i>
												      </td>
												    </tr>
											    </c:otherwise>
											</c:choose>
										  </tbody>
										
										
										</table>
									</div>
								
								<c:choose>
								    <c:when test="${userForm.id eq null}">
								       <input type="submit" name="submit" id="btn-add" class="btnRegister" value="Register" />
								    </c:when>
								    <c:otherwise>
								        <input type="submit" name="submit" id="btn-update" class="btnRegister" value="Update" />
								    </c:otherwise>
								</c:choose>									
						</div>
					</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="layout/footer.jsp"%>
	<script src="${pageContext.request.contextPath}/resources/js/dynamicrows.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/dynamicrows.js"></script>
</body>

<script type="text/javascript">
	$(function() {
	  $('[data-dynamicrows]').dynamicrows({
	    animation: 'fade',
	    copyValues: true,
	    minrows: 2
	  });
	});
</script>


<script type="text/javascript">
	$('#username').blur(function(){
    	 nameValidation();
	});
	
	
	$('input[type=radio][name=gender]').change(function() {
		genderValidate();    
	});
	
	$('#email').blur(function(){
    	emailValidation();
	});
	
	$('#password').blur(function(){
    	passwordValidation();
	});
	
	$('#conPassword').blur(function(){
    		passwordCompare();
	});
	
	$('#mobile').blur(function(){
    		mobileValidation();
	});
	
	function regFormValidate(){
		let isUsername = usernameValidation(), isGender = genderValidate(), isEmail = emailValidation(), isMobile = mobileValidation(), isPassword = passwordValidation() && passwordCompare();
		let isFormValid = isUsername && isGender && isEmail && isPassword && isMobile;
		
		if(isFormValid){
			return true;
		}
		return false;
	}
	
	function nameValidation(){
		var username = $('#username').val();
    	var isBoolean = false;
		
		if(username.length < 1 || username.length > 50){
			$('#err-username').html('name length not valid');
			isBoolean = false;
		}else{
			if(username ){
				$('#err-username').html('');	
				isBoolean = true;
			}
		}
		return isBoolean;
	}
	
	function mobileValidation(){
		var mobile = $('#mobile').val();
    	var phonePattern =/^\d{10}$/;
		var isBoolean = false;
		
		if(mobile.length < 10 || mobile.length > 10){
			$('#err-mobile').html('Invalid mobile no');
			isBoolean = false;
		}else{
			if(!phonePattern.test(mobile)){
				$('#err-mobile').html('invalid mobile');
				isBoolean = false;
			}else{
				$('#err-mobile').html('');	
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
    	return isBoolean;
			}
		}
	}
	
	function genderValidate(){
		var isBool = false;
		if (this.value == '') {
	    	$('#err-gender').html('please select gender');
	    	isBool = false;
	    }else{
	    	$('#err-gender').html('');
	    	isBool = true;
	    }
	    return isBool;
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
</html>