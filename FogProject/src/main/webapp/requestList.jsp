<%-- 
    Document   : requestList
    Created on : 06-05-2019, 11:57:36
    Author     : William Sehested Huusfeldt
--%>

<%@page import="java.util.List"%>
<%@page import="logic.facades.RequestFacade"%>
<%@page import="data.models.Request"%>
<%@page import="java.util.ArrayList"%>  
<%@include file = "header.jsp" %>

<%
    ArrayList<Request> requests = (ArrayList<Request>) session.getAttribute("requestList");
%>
<div class="greyBox">
    <table class="table">
        <thead class="thead-dark">
        <tr>
          <th scope="col">Kundenavn</th>
          <th scope="col">Tlf</th>
          <th scope="col">E-mail</th>
          <th scope="col">Note</th>
        </tr>
      </thead>
      <tbody>
        <% for(Request r : requests) { 
        %>
        <tr>
          <td><%=r.getName() %></td>
          <td><%=r.getPhone() %></td>
          <td><%=r.getEmail() %></td>
          <td><%=r.getNote()%></td>
        </tr>
      <%}%>
      </tbody>
    </table>
</div>
