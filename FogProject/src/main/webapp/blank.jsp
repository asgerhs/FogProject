<%-- 
    Document   : login
    Created on : 07-05-2019, 12:07:51
    Author     : Martin Frederiksen
--%>
<%@page import="logic.AdvancedCalculator"%>
<%@page import="logic.GenerateSVG"%>
<%@include file = "header.jsp" %>

<!--
<a class="btn btn-primary" href="FrontController?command=showParts">Generate partslist</a>
<a class="btn btn-primary" href="FrontController?command=request">Request Carport</a>
-->

<% 
    AdvancedCalculator ac = new AdvancedCalculator(7800, 6000, true, 2100, 6000, false);
%>
<%= ac.getTopViewSVG().getSideViewSVG()%>

<%@include file = "footer.jsp" %>