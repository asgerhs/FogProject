<%-- 
    Document   : order
    Created on : 14-05-2019, 11:51:24
    Author     : Martin Frederiksen
--%>
<%@page import="data.models.Order"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp" %>

<%
    List<Order> orders = (List<Order>) session.getAttribute("orders"); 
%>

<div class="greyBox">
<table class="table">
    <thead class="thead-dark">
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Email</th>
      <th scope="col">Carport brede</th>
      <th scope="col">Carport Længde</th>
      <th scope="col">Skur brede</th>
      <th scope="col">Skur Længde</th>
      <th scope="col">Tag</th>
      <th scope="col">Vinkel</th>
    </tr>
  </thead>
  <tbody>
    <% for(Order o : orders) { 
    %>
    <tr>
      <td><%=o.getId()%></td>
      <td><%=o.getEmail()%></td>
      <td><%=o.getWidth()%></td>
      <td><%=o.getLength()%></td>
      <td><%=o.getShedWidth()%></td>
      <td><%=o.getShedLength()%></td>
      <td><%=o.getRoof()%></td>
      <td><%=o.getAngle()%></td>
    </tr>
  <%}%>
  </tbody>
</table>
</div>

<%@include file="footer.jsp" %>