<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Address ADD</title>
<%@ include file="layout/common-css.jsp" %>
</head>
<body>
	<%@ include file="layout/header.jsp"%>
	<div class="container register">
		<div class="row">
			<div class="col-md-9 register-right">				
				<div class="tab-content" id="myTabContent">
					<form:form method ="POST" modelAttribute="addressForm">
					<div class="tab-pane fade show active" id="home" role="tabpanel"
						aria-labelledby="home-tab">
						<h3 class="register-heading">Add Address</h3>
						<div class="row register-form">
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="house" class="form-control" placeholder="House no *" value="" />
									<form:errors path="house"></form:errors>
								</div>
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="city" class="form-control" placeholder="City *"/>
									<form:errors path="city"></form:errors>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="state" class="form-control"
										placeholder="state *"/>
									<form:errors path="state"></form:errors>
								</div>
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<form:input type="text" path="pinCode" class="form-control" placeholder="Pin code *"/>
									<form:errors path="pinCode"></form:errors>
								</div>
								
								<input type="submit" name="submit" class="btnRegister" value="ADD Address" />
							</div>
						</div>
					</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="layout/footer.jsp"%>
</body>
</html>