<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String language = "";
	for (Cookie cookie : request.getCookies()){
        if (cookie.getName().equals("language")) {
            language = cookie.getValue(); 
			break;
        }
	}
	if (language.equals("")) {
        language = "russian";
    }
%>
<div class='pageHeader'>
    <ul class='menuList'>
        <li id="language-li">
            <select id="language-select">
                <option value="russian" <%=(language.equals("russian"))? "selected" : ""%> >ru</option>
                <option value="english" <%=(language.equals("english"))? "selected" : ""%> >en</option>
                <option value="deutsch" <%=(language.equals("deutsch"))? "selected" : ""%> >de</option>
            </select>
        </li> 
	<% if (language.equals("russian")){ %>
		<fmt:setLocale value="ru_RU" scope="session"/>
	<% } else if (language.equals("english")){ %>
		<fmt:setLocale value="en_GB" scope="session"/>
	<% } else if (language.equals("deutsch")){ %>
		<fmt:setLocale value="de_DE" scope="session"/>
	<% } %>
	
	
	<fmt:setBundle basename="props.locale" />

		<% if (!request.isUserInRole("user")) { %>
			<li id="login-li"><a href='/LAB3/login'><fmt:message key="login" /></a></li>
		<% } else { %>
		
			<li id="cabinet-li"><a href='/LAB3/cabinet'><fmt:message key="cabinet" />(<%=request.getUserPrincipal().getName() %>)</a></li>
			<li id="logout-li"><a href='/LAB3/logout'><fmt:message key="loguot" /></a></li>
			<% if(request.getSession(true).getAttribute("products") != null) { %>
				<li id="booking-li"><a href='/LAB3/booking'><fmt:message key="booking" /></a></li>
			<% } %>
		<% } %>	
        <li id="shopping-cart-li"><a href='/LAB3/cart'><fmt:message key="cart" /></a></li>
        <li id="purchase-history-li"><a href='/LAB3/cart'><fmt:message key="history" /></a></li>
		<li id="search-li"><a href='/LAB3/products'><fmt:message key="search" /></a></li>
    </ul>
</div>