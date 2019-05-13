<%-- 
    Document   : login
    Created on : 07-05-2019, 12:07:51
    Author     : Martin Frederiksen
--%>
<%@page import="logic.GenerateSVG"%>
<%@include file = "header.jsp" %>


<a class="btn btn-primary" href="FrontController?command=showParts">Generate partslist</a>
<a class="btn btn-primary" href="FrontController?command=request">Request Carport</a>

<% GenerateSVG s = new GenerateSVG(780, 600);
        s.generateRafter(15, 55);    
%>
<%= s.getSVG() %>

<%@include file = "footer.jsp" %>