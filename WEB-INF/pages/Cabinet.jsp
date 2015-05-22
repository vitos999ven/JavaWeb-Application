<%@page import="hibernate.util.Factory"%>
<%@page import="hibernate.logic.User"%>
<%@page import="hibernate.logic.ProductInfo"%>
<%@page import="hibernate.logic.Booking"%>
<%@page import="hibernate.logic.Comment"%>
<%@page import="java.util.List"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>

<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	User user = Factory.getInstance().getUserDAO().getUser(request.getUserPrincipal().getName());
	String src = "/LAB3/resources/images/avatar" + user.getAvatar() + ".jpg";
	ProductInfo info = Factory.getInstance().getProductDAO().getProductInfo(user.getBookmark());
	List<Booking> bookings = Factory.getInstance().getBookingDAO().getAllBookings(user.getLogin());
	int size = bookings.size();
	List<Comment> comments = Factory.getInstance().getCommentDAO().getAllComments(user.getLogin());
%>
<head>
	<title>Cabinet</title>
	<link rel='stylesheet' type='text/css' href='myStyles.css' media='all' />
</head>
<body>
		<script src='jquery-2.1.1.min.js'></script>
        <script src='jQueryUI/jquery-ui.min.js'></script>
        <script src='myMethods.js'></script>
		<script src='navBarEvents.js'></script>
		<script src='photoEvents.js'></script>
		<script src='cabinetEvents.js'></script>
<c:import url="/WEB-INF/pages/includes/NavBar.jsp">
</c:import>
<fmt:setBundle basename="props.locale" />
<div class='imgOuterTrimmer'>
	<div class='imgInnerTrimmer'>
		<img class='product-photo-lowimg' src="<%=src %>"/>
	</div>
</div>
<div class="cabinetInfo">
<div id="userName"><fmt:message key="name" />: <%=user.getLogin() %></div>
<div id="userBookmark"><fmt:message key="standartbookmark" />: <a href="/LAB3/product"><%=info.getTitle() %></a></div>
<a href="/LAB3/load"><fmt:message key="avatar" /></a>
</div>
<div id="bookings">
<div class="header"><fmt:message key="orders" /></div>
<% for (Booking booking: bookings){ 
	int product = booking.getProduct();
	info = Factory.getInstance().getProductDAO().getProductInfo(product);
	long date = booking.getDate();
	Calendar cal=GregorianCalendar.getInstance();
	cal.setTimeInMillis(date);
	String shop = booking.getShop();
%>
	<div class="booking">
	<table>
		<tr>
			<td style="width:150px;">
				<fmt:message key="product" />
			</td>
			<td>
				<a href="/LAB3/product?id=<%=product %>"><%=info.getTitle() %></a>
			</td>	
		</tr>
		<tr>
			<td style="width:150px;">
				<fmt:message key="date" />
			</td>
			<td>
				<div class="date" style="display:inline"><%=booking.getDate()/*new SimpleDateFormat("yyyy/MM/dd, HH:mm:ss").format(cal.getTime())*/ %></div>
			</td>	
		</tr>
		
	<% if (shop.startsWith("shop")){ 
		int num = (shop.equals("shop1"))? 1 : 2;
	%>	<tr>
			<td style="width:150px;">
				<fmt:message key="shop" />
			</td>
			<td>
				<%=num %>
			</td>	
		</tr>
	<% }else{ 
		String adress = shop.substring(8);
	%>
		<tr>
			<td style="width:150px;">
				<fmt:message key="delivery" />
			</td>
			<td>
				<%=adress %>
			</td>	
		</tr>
	<% } %>
	</table>
	</div>
<% } %>
</div>
<div id="comments">
<div class="header"><fmt:message key="comments" /></div>
</br>
<textarea id="commentText" style="width:500px;"></textarea>
</br>
<button id="sendComment"><fmt:message key="send" /></button>
<% for (Comment comment: comments){ 
	Calendar cal=GregorianCalendar.getInstance();
	cal.setTimeInMillis(comment.getDate());
	String value = comment.getValue();
%>
<div class="comment">
	<div class="date" style="display:inline"><%=comment.getDate()/*new SimpleDateFormat("yyyy/MM/dd, HH:mm:ss").format(cal.getTime())*/ %></div>: <%=(" " + value) %>
</div>

<% } %>
</div>
<div id='currentTime' class='buyButton' style="position:fixed">
	
</div>
</body>