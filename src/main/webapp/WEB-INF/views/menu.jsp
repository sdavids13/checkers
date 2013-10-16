<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="navbar">
  <div class="navbar-inner">
    <span class="brand">Checkers</span>
    <ul class="nav">
      <li><a href="<c:url value="/" />">Home</a></li>
      <li><a href="<c:url value="/users" />">Players</a></li>
      <li><a href="<c:url value="/games" />">Games</a></li>
      <li><a href="<c:url value="/logout" />">Logout</a></li>
    </ul>
  </div>
</div>