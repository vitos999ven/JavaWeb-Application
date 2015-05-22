<%@page import="hibernate.util.Factory"%>
<%@page import="hibernate.logic.User"%>
<%@page import="hibernate.logic.ProductInfo"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<head>
	<title>LoadPhotoForm</title>
	<link rel='stylesheet' type='text/css' href='myStyles.css' media='all' />
</head>
<body>
		<script src='jquery-2.1.1.min.js'></script>
        <script src='jQueryUI/jquery-ui.min.js'></script>
        <script src='myMethods.js'></script>
		<script src='navBarEvents.js'></script>
		<script src='loadPhotoEvents.js'></script>
<c:import url="/WEB-INF/pages/includes/NavBar.jsp">
</c:import>
<fmt:setBundle basename="props.locale" />
	<form enctype='multipart/form-data' action='LoadPhotoServlet' method='post' name='loadPhoto' target='hiddenframe'>
		<input type='hidden' name='MAX_FILE_SIZE' value='64000'>
		<input id='fileInput' type='file' name='photo' accept='image/jpeg'>
	</form> 
	<iframe id='hiddenframe' name='hiddenframe' style='width:0px; height:0px; border:0px'>
	</iframe>
	<button id='SendPhotoButton'><fmt:message key="send" /></button></li>
</body>