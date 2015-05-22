<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<head>
<link rel='stylesheet' type='text/css' href='myStyles.css' media='all' />
</head>
<body>
<script src='jquery-2.1.1.min.js'></script>
<script src='jQueryUI/jquery-ui.min.js'></script>
<script src='myMethods.js'></script>
<script src='navBarEvents.js'></script>
<c:import url="/WEB-INF/pages/includes/NavBar.jsp">
</c:import>
<fmt:setBundle basename="props.locale" />
<div class="header" style="position:relative; left:10px;"><fmt:message key="login" /></div>
<form id="loginForm" action="j_security_check" method="post" name="loginForm">
<fmt:message key="name" />
<input type="text" name="j_username" size="20" /><br>
<fmt:message key="password" />
<input type="password" name="j_password" size="20"/><br>
<input type="submit" value="<fmt:message key="submit" />"/>
</form>
</body>