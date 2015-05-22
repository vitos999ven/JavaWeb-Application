<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<head>
    <jsp:useBean id="productBean" class="beans.ProductBean" scope="session"/>
</head>    
<%
    String language = request.getParameter("language");
    String productId = request.getParameter("id");
%>
<jsp:setProperty name="productBean" property="language" value="<%=language%>"/>
<jsp:setProperty name="productBean" property="id" value="<%=productId%>"/>

<fmt:setBundle basename="props.locale" />
<div id="<%=productId%>" class="product">
    <div class='imgOuterTrimmer'>
        <div class='imgInnerTrimmer'>
            <img class='product-photo-lowimg' src="<jsp:getProperty name="productBean" property="photoUrl"/>"/>
        </div>
    </div>
    <div class="header">
        <jsp:getProperty name="productBean" property="title"/>
    </div>
    <div class="productContent">
        <jsp:getProperty name="productBean" property="content"/>
    </div>
    <div class="buySection">
        <div class="cost">
            <jsp:getProperty name="productBean" property="cost"/>
        </div>
        <div class="buyButton" id = "<%=productId%>">
            <fmt:message key="buy" />
        </div>
    </div>
</div>