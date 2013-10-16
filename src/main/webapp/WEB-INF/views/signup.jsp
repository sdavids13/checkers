<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
<jsp:include page="header.jsp" flush="true" />
</head>

<body>
	<jsp:include page="menu.jsp" flush="true" />
	
	<div class="container">
		<form:form cssClass="form-horizontal" commandName="user" method="post">
			<fieldset>
				<legend>User Signup</legend>
				
				<div class="control-group">
					<form:label for="username" path="username" cssClass="control-label">Username</form:label>
					<div class="controls">
						<form:input path="username" size="30" maxlength="15" placeholder="Username" /> <form:errors path="username" cssClass="text-error" />
					</div>
				</div>
				
				<div class="control-group">
					<form:label for="password" path="password" cssClass="control-label">Password</form:label>
					<div class="controls">
						<form:input path="password" size="30" maxlength="20" placeholder="Password" type="password" /> <form:errors path="password" cssClass="text-error" />
					</div>
				</div>
				
			</fieldset>
			
			<div class="form-actions">
				<input type="submit" value="Submit" class="btn btn-primary" />
				<a class="btn" href="<c:url value="/" />">Cancel</a>
			</div>
		</form:form>
	</div>
</body>
</html>
