<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<c:import url="/WEB-INF/pages/LogInForm.jsp">
</c:import>
<fmt:setBundle basename="props.locale" />
<div id="authError"><fmt:message key="wrongcomb" /></div>
