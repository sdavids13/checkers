<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<jsp:include page="header.jsp" flush="true" />
</head>

<body onload="document.f.j_username.focus();">
	<jsp:include page="menu.jsp" flush="true" />

	<div class="container">

		<form name="f" class="form-horizontal" action="<c:url value="/j_spring_security_check" />" method="POST">
	 		<fieldset>
	 		<legend>Checkers Login</legend>
	 		<div class="control-group">
	 		    <label class="control-label" for="username">Username</label>
	 		    <div class="controls">
	 				<input type="text" id="username" name="j_username" value="" placeholder="Enter Username">
	 			</div>
	 		</div>
	 		<div class="control-group">
	 		    <label class="control-label" for="password">Password</label>
	 		    <div class="controls">
	 			<input type="password" id="password" name="j_password" value="" placeholder="Password">
	 			</div>
	 		</div>
			<div class="control-group">
				<div class="controls">
					<label class="checkbox"> <input type="checkbox" name="_spring_security_remember_me">Remember me</label>
					<button type="submit" class="btn">Sign in</button>
				</div>
			</div>
	 		</fieldset>
	 	</form>
			
		<c:if test="${fn:toLowerCase(param.loginError) eq 'true'}">
			<div class="help-block has-error" style="display:inline-block;">
				<strong>Your login attempt was not successful, try again.</strong><br>
				<strong>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</strong>
			</div>
		</c:if>
	 	
	 	<p class="lead" style="padding-top:25px;">New to checkers? <a class="btn btn-success" href="<c:url value="/signup" />">Signup!</a></p>
 	</div>
</body>
</html>