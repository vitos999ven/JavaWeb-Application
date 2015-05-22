<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="service.ProductsFilter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
    String language = "russian", search = "";
    int pageNum = 1;
    for (Cookie cookie : request.getCookies()){
        if (cookie.getName().equals("language")) {
            language = cookie.getValue(); 
        }
        if (cookie.getName().equals("productsSearch")) {
            search = cookie.getValue(); 
        }
        if (cookie.getName().equals("productsPageNum")) {
            pageNum = Integer.parseInt(cookie.getValue()); 
        }
    }
	String parameterSearch = request.getParameter("search");
	
    search = search.replace("%20", " ");
	if (parameterSearch != null){
		System.out.println("OUT FROM JSP: SEARCHparam="+parameterSearch);
		
		if (!parameterSearch.equals(search)){
			search = parameterSearch;
			System.out.println("ADD cookie" + (!parameterSearch.equals(search)));
			response.addCookie(new Cookie("productsSearch", search));
		}
	}
	System.out.println("OUT FROM JSP: SEARCH="+search);
    if (language.equals("")) {
        language = getInitParameter("startLanguage");
    }
	
    ProductsFilter filter = new ProductsFilter(search,(byte) 1, 1);
%>
<html> 
    <head>
        <title>Products</title>
        <link rel='stylesheet' type='text/css' href='myStyles.css' media='all' />
    </head>
    <body>
        <script src='jquery-2.1.1.min.js'></script>
        <script src='jQueryUI/jquery-ui.min.js'></script>
        <script src='myMethods.js'></script>
        <script src='buyButtonEvents.js'></script>
        <script src='productsPageEvents.js'></script>
        <script src='photoEvents.js'></script>
        <script src='navBarEvents.js'></script>
        <jsp:include page="/WEB-INF/pages/includes/NavBar.jsp">
            <jsp:param name="language" value="<%=language%>" />
        </jsp:include>
		<fmt:setBundle basename="props.locale" />
        <div id="search-container">
            <input id="search-input"/>
            <button id="search-submit">
                <fmt:message key="search" />
            </button>
        </div>
        <% 
            for(String id: filter.getProductsIds()){ 
        %>
            <jsp:include page="/WEB-INF/pages/includes/ProductBean.jsp">
                <jsp:param name="language" value="<%=language%>" />
                <jsp:param name="id" value="<%=id%>" />
            </jsp:include>
        <%  } 
            /*int CountOfPages = filter.getCountOfPages();
            if (CountOfPages > 1){
                java.util.ArrayList<Integer> pages = new java.util.ArrayList();
                pages.add(1);
                for (int i = pageNum - 2; i <= pageNum + 2; i++){
                    if ((i > 1) && (i < CountOfPages))
                        pages.add(i);
                }
                pages.add(CountOfPages);
                out.print("<div id='page-links'>");
                for (int i = 0; i < pages.size(); i++){
                    out.print("<div id='link" + pages.get(i) + "' class='page-link " + ((pages.get(i) == pageNum)? "current": "") + "'>" + pages.get(i) + "</div>");
                }    
                out.print("</div>");
            }*/ %>
    </body>   
</html>    