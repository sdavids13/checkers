<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<jsp:include page="header.jsp" flush="true" />
</head>

<body>
	<jsp:include page="menu.jsp" flush="true" />

	<div class="container">
		<h1>User Profile for <c:out value="${user.username}" /></h1>
		<p class="lead">Need to enter previous games played along with historical game stats.</p>
 	</div>
</body>
</html>