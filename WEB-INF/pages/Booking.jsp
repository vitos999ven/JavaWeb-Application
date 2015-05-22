<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="cost" uri="/WEB-INF/tlds/cost.tld"%>
<%@taglib prefix="content" uri="/WEB-INF/tlds/content.tld"%>
<%@taglib prefix="photoUrl" uri="/WEB-INF/tlds/photoUrl.tld"%>
<%@taglib prefix="title" uri="/WEB-INF/tlds/title.tld"%>
<%@taglib prefix="bean" uri="/WEB-INF/tlds/bean.tld"%>
<html>
	<head>
		<title>Shopping Cart</title>
		<link rel='stylesheet' type='text/css' href='myStyles.css' media='all' />
	</head>
	<body>
        <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
		<script src='jquery-2.1.1.min.js'></script>
        <script src='jQueryUI/jquery-ui.min.js'></script>
        <script src='myMethods.js'></script>
		<script src='navBarEvents.js'></script>
        <script src='productsPageEvents.js'></script>
		<script src='bookingEvents.js'></script>
		<script src='deleteButtonEvents.js'></script>
		<script src='photoEvents.js'></script>
				
		<c:choose>
			<c:when test="${cookie.language.value eq 'russian'}">
				<c:set var="language" value="russian"/>
			</c:when>
			<c:when test="${cookie.language.value eq 'english'}">
				<c:set var="language" value="english"/>
			</c:when>
			<c:when test="${cookie.language.value eq 'deutsch'}">
				<c:set var="language" value="deutsch"/>
			</c:when>
		</c:choose>
		<c:import url="/WEB-INF/pages/includes/NavBar.jsp">
		    <c:param name="language" value="${language}"/>
		</c:import>
		<c:set var="sum" value="0.0"/>
		<c:set var="count" value="0"/>
		<fmt:setBundle basename="props.locale" />
		<c:forEach items="${pageContext.session.getAttribute('products')}" var="id">
			${bean:setBean(id, language)}
			<c:set var="title" value="${title:getTitle()}"/>
			<c:set var="content" value="${content:getContent()}"/>
			<c:set var="photoUrl" value="${photoUrl:getPhotoUrl()}"/>
			<c:set var="cost" value="${cost:getCost()}"/>
			<c:set var="sum" value="${sum + cost}"/>
			<c:set var="count" value="${count + 1}"/>
			<div id="${id}" class="product">
				<div class='imgOuterTrimmer'>
					<div class='imgInnerTrimmer'>
						<img class='product-photo-lowimg' src="${photoUrl}"/>
					</div>
				</div>
				<div class="header">${title}-${cost}</div>
				<div class="productContent">${content}</div>
				
				<div id='${id}' class='deleteButton'>
					<fmt:message key="delete" />
				</div>
				
			</div>
		</c:forEach>
		<div id="type" class='shoppingType'>
					<select id="shop-select">
						<option value="delivery" >
							<fmt:message key="delivery" />
						</option>
						<option value="shop1" selected >
							<fmt:message key="shop" /> №1
						</option>
						<option value="shop2" >
							<fmt:message key="shop" /> №2
						</option>
					</select>
					<input id='adress' style='display:none' placeholder='address'></input>
					<div id="map" style="width: 600px; height: 400px"></div>	
	    </div>
		<div class='bookingButton' style='top:30px; right:5px;'>
						<fmt:message key="order" />
	    </div>
		
	</body>
</html>